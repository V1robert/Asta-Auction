package banca.crud;


import java.util.List;

import banca.mapper.TransazioneMapper;

import banca.models.Transazione;
import banca.mybatis.SqlMapFactory;

public class TransazioneCrud {

	public List<Transazione> findByStatoTransazione(Integer idStato) {
		try {
			SqlMapFactory.instance().openSession();
			TransazioneMapper mapper = SqlMapFactory.instance().getMapper(TransazioneMapper.class);
			List<Transazione> listaTransazioni = mapper.findByStatoTransazione(idStato);
			SqlMapFactory.instance().commitSession();
			return listaTransazioni;
		}

		catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();

		}

		finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	public List<Transazione> findByConto(Integer conto) {
		try {
			SqlMapFactory.instance().openSession();
			TransazioneMapper mapper = SqlMapFactory.instance().getMapper(TransazioneMapper.class);

			List<Transazione> listaTransazioni = mapper.findByConto(conto);
			SqlMapFactory.instance().commitSession();
			return listaTransazioni;
		}

		catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();

		}

		finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	public Transazione insertTransazione(Transazione transazione) {
		try {
			SqlMapFactory.instance().openSession();
			TransazioneMapper mapper = SqlMapFactory.instance().getMapper(TransazioneMapper.class);
			mapper.insertTransazione(transazione);
			SqlMapFactory.instance().commitSession();
			return transazione;
		}

		catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();

		}

		finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	public void rifiutaTransazione(Transazione transazione) {

		try {
			SqlMapFactory.instance().openSession();
			TransazioneMapper mapper = SqlMapFactory.instance().getMapper(TransazioneMapper.class);
			mapper.rifiutaTransazione(transazione.getId());
			SqlMapFactory.instance().commitSession();

		}

		catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();

		}

		finally {
			SqlMapFactory.instance().closeSession();
		}

	}

	public void accettaTransazione(Transazione transazione) {
		try {

			SqlMapFactory.instance().openSession();
			TransazioneMapper mapper = SqlMapFactory.instance().getMapper(TransazioneMapper.class);
			mapper.accettaTransazione(transazione.getId());
			SqlMapFactory.instance().commitSession();

		} catch (Exception e) {

			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();

		} finally {

			SqlMapFactory.instance().closeSession();

		}
	}
}
