package mapping;

import java.util.List;

import models.Tag;

public interface TagMapping {

	public void insert(Tag tag);

	public void update(Tag tag);

	public void delete(Integer id);
	
	public Tag findByDescrizione(Tag tag);

	public List<Tag> findAll();
}
