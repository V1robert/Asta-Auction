package model;

import lombok.Data;

public @Data class FileCaricato {

	private String nomeFile;
	private String contentType;
	private String content;

}
