package filesUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeggiFile {

	
	public   List<String> vediFile() throws Exception {
		try {
			
			//path assoluto del file da leggere 
			File file = new File("C:\\Users\\RobertVM\\.eclipse\\workspace2023\\filedelcliente\\testo.txt");
			
			//controllo che esista e lo posso leggere 
		if(file.exists()&&file.canRead()) {
			
			return leggiFile(file);
			
			
		}else {
			throw new FileNotFoundException();
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		throw new Exception();
	}
	
	public List<String> leggiFile(File file) throws IOException {
		BufferedReader br = new BufferedReader (new FileReader(file));
		//finche il file ha dati il while continua
		String dati;
		
		List<String> valori = new ArrayList<String>();
		
		while((dati=br.readLine()) != null) {
			
			//voglio controllare che non inizi con # la stringa perche forse è un commento ,
			//controllo anche che non ci siano spazzi 
			if(!dati.startsWith("#")&&dati.length()>0) {
		    System.out.println(dati);
		    valori.add(dati);
			}	
			
			
		}
		br.close();
		
		return valori;
	}
	
}
