package tpfinalcomplementPOO_2;

import java.util.List;

public abstract class AbstractModelEcoutable implements ModelEcoutable {
	protected List<EcouteurModel> ecouteurs;
	
	@Override
	public void ajoutEcouteur(EcouteurModel ec) {
		ecouteurs.add(ec);
	} 
	
	@Override
	public void retraitEcouteur(ModelEcoutable ec) {
		ecouteurs.remove(ec);
	}
	
	protected abstract void moveChangement ();
}
