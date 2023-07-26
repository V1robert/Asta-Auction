package interfaces;

import java.util.List;

import javax.ejb.Local;

import models.ArticoloTag;

@Local
public interface ArticoloTagInterface {

	
	public ArticoloTag insert(ArticoloTag articoloTag);

	public ArticoloTag update(ArticoloTag articoloTag);

	public void delete(Integer id);
	
	public ArticoloTag findById(Integer id);

	public List<ArticoloTag> findAll();
}
