package ai.data;

import ai.domains.LearningSchedule;
import ai.domains.algorithm.CalendarAlgorithm;

/**
 * Définit les méthodes d'interaction avec le support de stockage.
 * */
public interface Storage {

	/**
	 * Permet de charger le fichier dont le chemin à été passé dans le constructeur
	 * 
	 * @return l'objet qui se trouvait dans le fichier
	 */
	public LearningSchedule load(CalendarAlgorithm ca);

	/**
	 * Permet de sauvegarder un objet dans le fichier
	 * 
	 * @param la box a sauvé
	 */
	public void save(LearningSchedule ls);
}
