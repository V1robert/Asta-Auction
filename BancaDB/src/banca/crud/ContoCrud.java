package banca.crud;

import java.util.List;

import banca.mapper.ContoMapper;

import banca.models.Conto;
import banca.mybatis.SqlMapFactory;

public class ContoCrud {

	public Conto inserisciConto(Conto conto) {
		try {

			SqlMapFactory.instance().openSession();
			ContoMapper mapper = SqlMapFactory.instance().getMapper(ContoMapper.class);
			mapper.inserisciConto(conto);
			SqlMapFactory.instance().commitSession();

			return conto;
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

	public List<Conto> findContoByCf(String cf) {
		try {

			SqlMapFactory.instance().openSession();
			ContoMapper mapper = SqlMapFactory.instance().getMapper(ContoMapper.class);
			List<Conto> listaConti = mapper.findContoByCf(cf);
			SqlMapFactory.instance().commitSession();

			return listaConti;
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

	public void modificaSaldo(Conto conto) {

		try {

			SqlMapFactory.instance().openSession();
			ContoMapper mapper = SqlMapFactory.instance().getMapper(ContoMapper.class);
			mapper.modificaSaldo(conto.getSaldo(), conto.getnConto());
			SqlMapFactory.instance().commitSession();
		

		} catch (Exception e) {

			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();

		} finally {

			SqlMapFactory.instance().closeSession();

		}
	}

	public List<Conto> findContiByStato(Integer id) {

		try {
			SqlMapFactory.instance().openSession();
			ContoMapper mapper = SqlMapFactory.instance().getMapper(ContoMapper.class);
			List<Conto> listaConti = mapper.findContiByStato(id);
			SqlMapFactory.instance().commitSession();
			return listaConti;

		} catch (Exception e) {
			e.printStackTrace();
			SqlMapFactory.instance().rollbackSession();
		} finally {
			SqlMapFactory.instance().closeSession();
		}
		return null;
	}

	public void attivaConto(Integer id) {
		try {
			SqlMapFactory.instance().openSession();
			ContoMapper mapper = SqlMapFactory.instance().getMapper(ContoMapper.class);
			mapper.attivaConto(id);
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

	public void bloccaConto(Integer id) {
		try {
			SqlMapFactory.instance().openSession();
			ContoMapper mapper = SqlMapFactory.instance().getMapper(ContoMapper.class);
			mapper.bloccaConto(id);
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

}
