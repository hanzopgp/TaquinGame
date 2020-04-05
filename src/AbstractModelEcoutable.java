package tpfinalcomplementPOO_2;

import java.util.List;

public abstract class AbstractModelEcoutable implements ModelEcoutable {
	protected List<EcouteurModel> ecouteurs;
	
	/**
	* Override de ajoutEcouteur;
	* @param ec objet EcouteurModel;
	*/
	@Override
	public void ajoutEcouteur(EcouteurModel ec) {
		ecouteurs.add(ec);
	} 
	
	/**
	* Override de retraitEcouteur;
	* @param ec objet ModelEcoutable;
	*/
	@Override
	public void retraitEcouteur(ModelEcoutable ec) {
		ecouteurs.remove(ec);
	}
	
	/**
	* Methode abstraite moveChangement;
	*/
	protected abstract void moveChangement ();
}
