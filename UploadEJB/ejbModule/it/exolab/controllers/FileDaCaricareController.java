package it.exolab.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.apache.log4j.Logger;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;

import costanti.CostantiArchivio;
import it.exolab.interfaces.FileDaCaricareInterface;
import model.FileCaricato;

@Stateless(name = "FileDaCaricareInterface")
@LocalBean
public class FileDaCaricareController implements FileDaCaricareInterface {

	static final Logger log = Logger.getLogger(FileDaCaricareController.class.getName());

	@Override // controllo che l'archivio sia di tipo rar 7 zip o zip poi estraggo i file all
				// interno per ulteriori controlli
	public List<String> verificaArchivio(FileCaricato zip) throws IOException, RarException {

		log.trace("nel metodo verificaArchivio");

		try {
			byte[] body = Base64.getDecoder().decode(zip.getContent());
			String fileZip = convertiDaBytesAHex(body);
			List<String> nomiFile = new ArrayList<String>();
			nomiFile.add(zip.getNomeFile());
			switch (controllaMagicNumberDelArchivio(fileZip)) {
			case CostantiArchivio.VALORE_FILE_RAR:
				nomiFile = controllaContenutoFileRar(body);
				break;
			case CostantiArchivio.VALORE_FILE_ZIP:
				nomiFile = controllaContenutoFileZip(body);
				break;
			case CostantiArchivio.VALORE_FILE_7ZIP:
				nomiFile = controllaContenutoFile7zip(body);
				break;
			default:
				throw new IOException(CostantiArchivio.EXCEPTION_ARCHIVIO);
			}

			return nomiFile;

		} catch (IOException io) {
			throw new IOException(io.getMessage());
		}
	}

	public Integer controllaMagicNumberDelArchivio(String cartella) throws IOException {
		log.trace("nel metodo controllaMagicNumberDelArchivio");

		if (cartella.startsWith(CostantiArchivio.VERSIONE_RAR_NON_SUPPORTATA)) {
			log.error("la cartella rar 5 non è supportata");
			throw new IOException(CostantiArchivio.EXCEPTION_VERSIONE_RAR);
		} else if (cartella.startsWith(CostantiArchivio.MAGIC_NUMBER_RAR)) {
			log.debug("la cartella è rar");
			return CostantiArchivio.VALORE_FILE_RAR;
		} else if (cartella.startsWith(CostantiArchivio.MAGIC_NUMBER_ZIP)) {
			log.debug("la cartella è zip");
			return CostantiArchivio.VALORE_FILE_ZIP;
		} else if (cartella.startsWith(CostantiArchivio.MAGIC_NUMBER_7_ZIP)) {
			log.debug("la cartella è 7zip");
			return CostantiArchivio.VALORE_FILE_7ZIP;
		} else {
			return CostantiArchivio.VALORE_FILE_ERRATO;
		}
	}

	public List<String> controllaContenutoFileZip(byte[] zip) throws IOException {
		log.trace("nel metodo controllaContenutoFileZip");
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(zip);
				ZipInputStream zipStream = new ZipInputStream(byteArrayInputStream);) {

			List<String> nomiFileZip = new ArrayList<String>();

			ZipEntry zipEntry;
			// lo zipEntry contiene la grandezza del file che sto controllando
			while ((zipEntry = zipStream.getNextEntry()) != null) {

				nomiFileZip.add(zipEntry.getName());
				byte[] content = convertiInputStreamAByteArray(zipStream, zipEntry.getSize());
				// il byte[] diventa in formato hex per applicare il controllo del suo magic
				// number
				String hex = convertiDaBytesAHex(content);
				log.info(hex);
				// se trovo un file non jpg png xls docx mando un Exception
				if (!controllaMagicNumberFile(hex)) {

					throw new IOException(CostantiArchivio.MAGIC_NUMBER_NON_TROVATO);
				}

			}

			return nomiFileZip;
		} catch (IOException io) {
			throw new IOException(CostantiArchivio.MAGIC_NUMBER_NON_TROVATO);
		}

	}

	public List<String> controllaContenutoFileRar(byte[] zip) throws RarException, IOException {
		log.trace("nel metodo controllaContenutoFileRar");
		try (ByteArrayInputStream zipInArrivo = new ByteArrayInputStream(zip);
				Archive arch = new Archive(zipInArrivo);) {

			List<String> nomiFileRar = new ArrayList<String>();

			FileHeader fileHeader;

			while ((fileHeader = arch.nextFileHeader()) != null) {

				nomiFileRar.add(fileHeader.getFileName());
				InputStream stream = arch.getInputStream(fileHeader);

				byte[] content = convertiInputStreamAByteArray(stream, fileHeader.getDataSize());

				stream.close();
				String hex = convertiDaBytesAHex(content);
				log.info(hex);
				if (!controllaMagicNumberFile(hex)) {
					throw new IOException(CostantiArchivio.MAGIC_NUMBER_NON_TROVATO);
				}

			}
			return nomiFileRar;

		} catch (IOException io) {
			throw new IOException(CostantiArchivio.MAGIC_NUMBER_NON_TROVATO);
		}
	}

	public List<String> controllaContenutoFile7zip(byte[] zip) throws IOException {
		log.trace("nel metodo controllaContenutoFile7zip");
		try (SevenZFile sevenZFile = new SevenZFile(new SeekableInMemoryByteChannel(zip))) {

			SevenZArchiveEntry entry;

			List<String> nomiFile7zip = new ArrayList<String>();

			while ((entry = sevenZFile.getNextEntry()) != null) {

				nomiFile7zip.add(entry.getName());
				entry.getName();
				byte[] content = new byte[(int) entry.getSize()];
				sevenZFile.read(content, 0, content.length);

				String hex = convertiDaBytesAHex(content);
				log.info(hex);
				if (!controllaMagicNumberFile(hex)) {
					throw new IOException(CostantiArchivio.MAGIC_NUMBER_NON_TROVATO);
				}

			}

			return nomiFile7zip;

		} catch (IOException e) {
			throw new IOException(CostantiArchivio.MAGIC_NUMBER_NON_TROVATO);
		}

	}

	public boolean controllaMagicNumberFile(String file) {
		log.trace("nel metodo controllaMagicNumberFile");
		// ciclo l'array contenente i magic number permessi
		for (int x = 0; x < CostantiArchivio.LISTA_MAGIC_NUMBER.size(); x++) {
			if (file.startsWith(CostantiArchivio.LISTA_MAGIC_NUMBER.get(x))) {
				return true;
			}
		}

		return false;

	}

	// metodo per convertire lo stream di un file per poi vedere il suo magic number
	public byte[] convertiInputStreamAByteArray(InputStream stream, Long grandezzaByte) throws IOException {
		log.trace("nel metodo convertiInputStreamAByteArray");
		try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();) {
			if (grandezzaByte == -1) {
				throw new IOException(CostantiArchivio.EXCEPTION_GRANDEZZA_FILE);
			}
			int nRead;
			byte[] data = new byte[Math.toIntExact(grandezzaByte)];

			while ((nRead = stream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			return buffer.toByteArray();
		} catch (IOException e) {
			throw new IOException(CostantiArchivio.ERRORE_NEL_CONVERTIRE_INPUT_A_BYTEARRAY);
		}

	}

	public byte[] convertiInputStreamAByteArray(ZipInputStream stream, Long grandezzaByte) throws IOException {
		log.trace("nel metodo convertiInputStreamAByteArray");
		try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();) {
			if (grandezzaByte == -1) {
				throw new IOException(CostantiArchivio.EXCEPTION_GRANDEZZA_FILE);
			}
			int nRead;
			byte[] data = new byte[Math.toIntExact(grandezzaByte)];

			while ((nRead = stream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			return buffer.toByteArray();
		} catch (IOException io) {
			throw new IOException(CostantiArchivio.ERRORE_NEL_CONVERTIRE_INPUT_A_BYTEARRAY);
		}
	}

	private String convertiDaBytesAHex(byte[] bytes) {
		log.trace("nel metodo convertiDaBytesAHex");
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			hexString.append(String.format(CostantiArchivio.STRING_FORMAT, b));
		}
		return hexString.toString();
	}
}