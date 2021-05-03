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
        assertEquals(4, averageAge);
    }

    @Test
    public void shouldGetAverageAgeOfFemalePatients() {

    }

    @Test
    public void shouldGetNumberOfEpisodes() {

    }

    @Test
    public void shouldGetNumberOfPatients() {

    }
}