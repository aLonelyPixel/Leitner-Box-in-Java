package ai.data;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import ai.domains.LearningSchedule;
import ai.domains.algorithm.CalendarAlgorithm;


public class LearningScheduleStorage implements Storage {

	private String jsonSourcePath;

	/**
	 * Permet d'initialiser un objet LearningScheduleStorage permettant de charger et de sauver le fichier passé lors de l'initialisation
	 * Le chemin passé démarre à la source du projet. Exemple : src/ressources/SavedFile.json
	 * */
	public LearningScheduleStorage(String jsonSource) {
		jsonSourcePath = jsonSource;
	}
	
	/**
	 * Permet de charger le fichier dont le chemin à été passé dans le constructeur
	 * @return le LearningSchedule qui se trouvait dans le fichier
	 * */
	public LearningSchedule load(CalendarAlgorithm ca) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").create();
		File jsonSource = new File(jsonSourcePath);
		if (jsonSource != null && jsonSource.exists()) {
			FileReader reader = null;
			try {
				reader = new FileReader(jsonSource);
			} catch (IOException ex) {
				ex.printStackTrace();
				return null;
			}
			try {
				LearningSchedule learningSchedule = gson.fromJson(reader, LearningSchedule.class);
				learningSchedule.setCalendarAlgorithm(ca);
				return learningSchedule;
			} catch (JsonSyntaxException e) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("[LearningScheduleStorage]::load() => Syntax JSON incorrect");
				return null;
			}
		} else {
			System.out.println("[LearningScheduleStorage]::load() => Fichier source JSON non trouvé");
			return null;
		}
	}

	/**
	 * Permet de sauvegarder un LearningSchedule dans le fichier
	 * @param le LearningSchedule a sauvé
	 * */
	public void save(LearningSchedule learningSchedule) {
		try {
			ExclusionStrategy strategy = new ExclusionStrategy() {
			    @Override
			    public boolean shouldSkipField(FieldAttributes field) {
					if (field.getDeclaredClass() == CalendarAlgorithm.class) {
			            return true;
			        }
			        return false;
			    }
			 
			    @Override
			    public boolean shouldSkipClass(Class<?> clazz) {
			        return false;
			    }
			};
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
					  .addSerializationExclusionStrategy(strategy)
					  .create();
			File jsonDestination = new File(jsonSourcePath);
			FileWriter writer = new FileWriter(jsonDestination);
			gson.toJson(learningSchedule, writer);
			writer.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

//	@Override
//	public LearningSchedule load(CalendarAlgorithm ca) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
