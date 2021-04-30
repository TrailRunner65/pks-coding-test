package pks;

public interface PatientData {

	public long getNumberOfEpisodes();

	public long getNumberOfPatients();

	public double getAverageAgeByGender(String gender);
}
