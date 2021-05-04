package pks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PatientDataImplTest {

    private PatientData patientData;

    @Before
    public void init() {
        patientData = new PatientDataImpl();
    }

    @Test
    public void shouldGetAverageAgeOfMalePatients() {
        double averageAge = patientData.getAverageAgeByGender("M");
        assertEquals(24.0, averageAge, 0);
    }

    @Test
    public void shouldGetAverageAgeOfFemalePatients() {
        double averageAge = patientData.getAverageAgeByGender("F");
        assertEquals(24.5, averageAge, 0);
    }

    @Test
    public void shouldGetNumberOfEpisodes() {
        long numEpisodes = patientData.getNumberOfEpisodes();
        assertEquals(4, numEpisodes);
    }

    @Test
    public void shouldGetNumberOfPatients() {
       long numPatients = patientData.getNumberOfPatients();
       assertEquals(3, numPatients);
    }
}