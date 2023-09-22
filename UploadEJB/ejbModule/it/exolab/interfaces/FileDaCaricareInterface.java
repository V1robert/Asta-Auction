package it.exolab.interfaces;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;

import com.github.junrar.exception.RarException;

import model.FileCaricato;

@Local
public interface FileDaCaricareInterface {

	public List<String> verificaArchivio(FileCaricato file) throws IOException, RarException;
}
