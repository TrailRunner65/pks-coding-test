package pks;

public class App {

	public static void main(String[] args) {

		PatientDataService patiendDataService = new PatientDataService();

		PatientData patientData = new PatientDataImpl();
		//patiendDataService.getPatientData(); // TODO read messages.csv

		System.out.println("Number of patients: " + patientData.getNumberOfPatients());
		System.out.println("Number of episodes: " + patientData.getNumberOfEpisodes());

		System.out.println("Average age male: " + patientData.getAverageAgeByGender("M") );
		System.out.println("Average age male: " + patientData.getAverageAgeByGender("F"));

	}

}
