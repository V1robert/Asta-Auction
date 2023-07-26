package interfaces;

import javax.ejb.Local;

import models.Ruolo;
@Local
public interface RuoloInterface {


	public Ruolo insert(Ruolo ruolo);
}
