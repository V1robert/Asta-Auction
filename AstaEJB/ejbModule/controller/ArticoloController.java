package controller;

import interfaces.ArticoloInterface;
import mapping.ArticoloMapping;
import models.Articolo;
import sqlfactory.SqlMapFactory;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import crud.ArticoloCrud;

@LocalBean
@Stateless(name = "ArticoloInterface")
public class ArticoloController implements ArticoloInterface {

	@Override
	public Articolo insert(Articolo articolo) {
		System.out.println("insert Controller articolo --> " + articolo);
		try {
			SqlMapFactory.instance().openSession();
			ArticoloMapping mapper = SqlMapFactory.instance().getMapper(ArticoloMapping.class);
			ArticoloCrud articoloCrud = new ArticoloCrud();
			articolo = articoloCrud.insert(articolo, mapper);
			SqlMapFactory.instance().commitSession();
			return articolo;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public Articolo update(Articolo articolo) {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Articolo findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Articolo> findArticoliUtente(Articolo articolo) {
		System.out.println("trova articoli Controller articolo --> " + articolo);
		try {
			SqlMapFactory.instance().openSession();
			ArticoloMapping mapper = SqlMapFactory.instance().getMapper(ArticoloMapping.class);
			ArticoloCrud articoloCrud = new ArticoloCrud();
			List<Articolo> articoli = articoloCrud.findArticoliUtente(articolo, mapper);
			SqlMapFactory.instance().commitSession();
			return articoli;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public Articolo findArticoloByNome(Articolo articolo) {
		System.out.println("trova articoli per nome --> " + articolo);
		try {
			SqlMapFactory.instance().openSession();
			ArticoloMapping mapper = SqlMapFactory.instance().getMapper(ArticoloMapping.class);
			ArticoloCrud articoloCrud = new ArticoloCrud();
	        articolo = articoloCrud.findArticoloByNome(articolo, mapper);
			SqlMapFactory.instance().commitSession();
			return articolo;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public List<Articolo> findAll() {
		System.out.println("trova Tuttiarticoli Controller articolo --> " );
		try {
			SqlMapFactory.instance().openSession();
			ArticoloMapping mapper = SqlMapFactory.instance().getMapper(ArticoloMapping.class);
			ArticoloCrud articoloCrud = new ArticoloCrud();
			List<Articolo> articoli = articoloCrud.findAll(mapper);
			SqlMapFactory.instance().commitSession();
			return articoli;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}
}
