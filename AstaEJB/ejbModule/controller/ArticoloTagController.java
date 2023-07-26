package controller;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import crud.ArticoloTagCrud;
import interfaces.ArticoloTagInterface;
import mapping.ArticoloTagMapping;
import models.ArticoloTag;
import sqlfactory.SqlMapFactory;
@LocalBean
@Stateless(name="ArticoloTagInterface")
public class ArticoloTagController implements ArticoloTagInterface {

	@Override
	public ArticoloTag insert(ArticoloTag articoloTag) {
		System.out.println("insert Controller articoloTag --> " + articoloTag);
		try {
			SqlMapFactory.instance().openSession();
			ArticoloTagMapping mapper= SqlMapFactory.instance().getMapper(ArticoloTagMapping.class);
			ArticoloTagCrud articoloTagCrud = new ArticoloTagCrud();
			articoloTag=articoloTagCrud.insert(articoloTag,mapper );
			SqlMapFactory.instance().commitSession();
			return articoloTag;
		}catch(Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public ArticoloTag update(ArticoloTag articoloTag) {
		// TODO Auto-generated method stub
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
	public List<ArticoloTag> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
