package controller;

import interfaces.ArticoloInterface;
import interfaces.ArticoloTagInterface;
import interfaces.TagInterface;
import mapping.ArticoloMapping;
import models.Articolo;
import models.ArticoloTag;
import models.Tag;
import sqlfactory.SqlMapFactory;

import java.util.ArrayList;
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
		System.out.println("trova Tuttiarticoli Controller articolo --> ");
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

	@Override
	public byte[] inserisciFoto(byte[] file) {
		System.out.println("inserisci foto controller blob");
		try {
			SqlMapFactory.instance().openSession();
			ArticoloMapping mapper = SqlMapFactory.instance().getMapper(ArticoloMapping.class);
			ArticoloCrud articoloCrud = new ArticoloCrud();
			byte[] bytes = articoloCrud.inserisciFoto(file, mapper);
			SqlMapFactory.instance().commitSession();
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	@Override
	public Integer inserisciArticolo(Articolo articolo, Integer idUtente) throws Exception  {

		try {
		articolo.setIdUtente(idUtente);

		insert(articolo);

		// select per trovare il id del articolo generato da mybatis


		
		Integer idArticoloTrovato = findArticoloByNome(articolo).getId();

		return idArticoloTrovato;
		}catch(Exception e) {
			e.printStackTrace();
		}
		throw new Exception();
		
	}

	public void creaBridgeArticoloTag(ArticoloTag articoloTag, Integer idArticoloTrovato,
			ArrayList<Tag> listaDeiTagArticolo, ArticoloTagInterface articoloTagInterface, TagInterface tagInterface) {
		articoloTag.setIdArticolo(idArticoloTrovato);
		if (listaDeiTagArticolo.size() > 1) {
			for (int x = 0; x < listaDeiTagArticolo.size(); x++) {

				// trovo ogni id dei tag creati dal utente contenuti nell arrayList

				Integer idDelTagTrovato = tagInterface.findByDescrizione(listaDeiTagArticolo.get(x)).getId();
				// setto l'id trovato
				articoloTag.setIdTag(idDelTagTrovato);

				articoloTagInterface.insert(articoloTag);
			}
		} else {
			Integer idDelTag = tagInterface.findByDescrizione(listaDeiTagArticolo.get(0)).getId();

			articoloTag.setIdTag(idDelTag);

			articoloTagInterface.insert(articoloTag);
		}
	}

	

}
