package crud;

import java.util.List;

import mapping.ArticoloTagMapping;
import models.ArticoloTag;

public class ArticoloTagCrud extends BaseCrud<ArticoloTag,ArticoloTagMapping> {

	@Override
	public ArticoloTag insert(ArticoloTag articoloTag, ArticoloTagMapping mapper) {
		try {
			mapper.insert(articoloTag);
			return articoloTag;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArticoloTag findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticoloTag> findAll(ArticoloTagMapping mapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArticoloTag update(ArticoloTag model, ArticoloTagMapping modeli) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
