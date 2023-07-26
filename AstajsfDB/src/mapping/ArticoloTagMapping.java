package mapping;

import java.util.List;

import models.ArticoloTag;

public interface ArticoloTagMapping {

	public void insert(ArticoloTag articoloTag);

	public void update(ArticoloTag articoloTag);

	public void delete(Integer id);
	
	public ArticoloTag findById(Integer id);
	
	public List<ArticoloTag> findAllAste();
}
