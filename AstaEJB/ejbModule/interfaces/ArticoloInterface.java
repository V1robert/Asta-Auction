package interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;


import models.Articolo;
import models.ArticoloTag;
import models.Tag;

@Local
public interface ArticoloInterface {

	public Articolo insert(Articolo articolo);

	public Articolo update(Articolo articolo);

	public void delete(Integer id);

	public Articolo findById(Integer id);

	public List<Articolo> findArticoliUtente(Articolo articolo);

	public Articolo findArticoloByNome(Articolo articolo);

	public List<Articolo> findAll();

	public byte[] inserisciFoto(byte[] file);

	public Integer inserisciArticolo(Articolo articolo, Integer idUtente) throws  Exception;

	public void creaBridgeArticoloTag(ArticoloTag articoloTag, Integer idArticoloTrovato,
			ArrayList<Tag> listaDeiTagArticolo, ArticoloTagInterface articoloTagInterface, TagInterface tagInterface);

}
