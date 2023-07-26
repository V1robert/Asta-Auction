package crud;

import java.util.List;

import mapping.TagMapping;
import models.Tag;

public class TagCrud extends BaseCrud<Tag, TagMapping> {

	@Override
	public Tag insert(Tag tag, TagMapping mapper) {
		try {
			mapper.insert(tag);
			return tag;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Tag findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	public Tag findByDescrizione(Tag tag,TagMapping mapper) {
		try {
			tag=mapper.findByDescrizione(tag);
			return tag;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Tag> findAll(TagMapping mapper) {
		try {
			List<Tag> tage=mapper.findAll();
			return tage;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Tag update(Tag model, TagMapping modeli) {
		// TODO Auto-generated method stub
		return null;
	}

}
