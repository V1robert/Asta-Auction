package interfaces;

import java.util.List;



import javax.ejb.Local;
import models.Tag;

@Local
public interface TagInterface {

	public Tag insert(Tag tag);

	public Tag update(Tag tag);

	public void delete(Integer id);
	
	public Tag findByDescrizione(Tag tag);

	public List<Tag> findAll();
}
