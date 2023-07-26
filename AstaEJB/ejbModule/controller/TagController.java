package controller;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import crud.TagCrud;
import crud.TagCrud;
import interfaces.TagInterface;
import mapping.TagMapping;
import mapping.TagMapping;
import models.Tag;
import models.Tag;
import sqlfactory.SqlMapFactory;


@LocalBean
@Stateless(name="TagInterface")
public class TagController implements TagInterface {

	@Override
	public Tag insert(Tag tag) {
		System.out.println("insert Controller tag --> " + tag);
		try {
			SqlMapFactory.instance().openSession();
			TagMapping mapper= SqlMapFactory.instance().getMapper(TagMapping.class);
			TagCrud tagCrud = new TagCrud();
			tag=tagCrud.insert(tag,mapper );
			SqlMapFactory.instance().commitSession();
			return tag;
		}catch(Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public Tag update(Tag tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Tag> findAll() {
		System.out.println("findAllLista tag --> " );
		try {
			SqlMapFactory.instance().openSession();
			TagMapping mapper = SqlMapFactory.instance().getMapper(TagMapping.class);
			TagCrud tagCrud = new TagCrud();
			List<Tag>tage = tagCrud.findAll(mapper);
			SqlMapFactory.instance().commitSession();
			return tage;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public Tag findByDescrizione(Tag tag) {
		System.out.println("find by desc tag  --> " + tag);
		try {
			SqlMapFactory.instance().openSession();
			TagMapping mapper= SqlMapFactory.instance().getMapper(TagMapping.class);
			TagCrud tagCrud = new TagCrud();
			tag=tagCrud.findByDescrizione(tag,mapper);
			SqlMapFactory.instance().commitSession();
			return tag;
		}catch(Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		}finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

}
