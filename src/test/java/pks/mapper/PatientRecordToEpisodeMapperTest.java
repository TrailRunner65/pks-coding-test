package pks.mapper;

import org.junit.Before;
import org.junit.Test;
import pks.domain.Episode;
import pks.reader.PatientRecord;
import pks.reader.PatientRecordReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PatientRecordToEpisodeMapperTest {

    private PatientRecordToEpisodeMapper patientRecordToEpisodeMapper;
    private File testFile;

    @Before
    public void init() {
        patientRecordToEpisodeMapper = new PatientRecordToEpisodeMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        testFile = new File(classLoader.getResource("messages.csv").getFile());
    }

    @Test
    public void shouldMapSingleRecordToEpisode() {
        Map<PatientRecordToEpisodeMapper.RecordKey, Episode> episodes = patientRecordToEpisodeMapper.mapToEpisodes(getMockRecord());
        assertNotNull(episodes);
        assertEquals(1, episodes.size());

        PatientRecordToEpisodeMapper.RecordKey key = new PatientRecordToEpisodeMapper.RecordKey("2020-12-09", "1");
        assertNotNull(episodes.get(key));
        Integer bloodPressure = episodes.get(key).getBloodPressure();
        assertEquals(160, bloodPressure.intValue());
    }

    @Test
    public void shouldMapMultipleRecordsToDistinctEpisodes() {
        Map<PatientRecordToEpisodeMapper.RecordKey, Episode> episodes = patientRecordToEpisodeMapper.mapToEpisodes(getMockUniquePatientRecords());
        assertNotNull(episodes);
        assertEquals(3, episodes.size());

        PatientRecordToEpisodeMapper.RecordKey key = new PatientRecordToEpisodeMapper.RecordKey("2019-12-09", "1");
        assertNotNull(episodes.get(key));
        Integer bloodPressure = episodes.get(key).getBloodPressure();
        assertEquals(160, bloodPressure.intValue());

        key = new PatientRecordToEpisodeMapper.RecordKey("2019-12-10", "1");
        assertNotNull(episodes.get(key));
        Integer age = episodes.get(key).getAge();
        assertEquals(40, age.intValue());
    }

    @Test
    public void shouldMapMultipleRecordsToNonDistinctEpisodes() {
        Map<PatientRecordToEpisodeMapper.RecordKey, Episode> episodes = patientRecordToEpisodeMapper.mapToEpisodes(getMockNonUniquePatientRecords());
        assertNotNull(episodes);
        assertEquals(3, episodes.size());

        PatientRecordToEpisodeMapper.RecordKey key = new PatientRecordToEpisodeMapper.RecordKey("2019-12-09", "2");
        assertNotNull(episodes.get(key));
        Integer bloodPressure = episodes.get(key).getBloodPressure();
        assertEquals(140, bloodPressure.intValue());
    }

    @Test
    public void shouldMapMultipleRecordsToOneEpisode() {
        Map<PatientRecordToEpisodeMapper.RecordKey, Episode> episodes = patientRecordToEpisodeMapper.mapToEpisodes(getMockMultipPatientRecordsForOneEpisode());
        assertNotNull(episodes);
        assertEquals(1, episodes.size());

        PatientRecordToEpisodeMapper.RecordKey key = new PatientRecordToEpisodeMapper.RecordKey("2019-12-09", "1");
        assertNotNull(episodes.get(key));

        Integer bloodPressure = episodes.get(key).getBloodPressure();
        assertEquals(130, bloodPressure.intValue());
        
        Float glucose = episodes.get(key).getGlucose();
        assertEquals(11.1f, glucose, 1);
    }

    @Test
    public void shouldMapEntireFile() {
        PatientRecordReader recordReader = new PatientRecordReader();
        List<PatientRecord> patientRecords = recordReader.readPatientRecords(testFile.getAbsolutePath());
        Map<PatientRecordToEpisodeMapper.RecordKey, Episode> episodes = patientRecordToEpisodeMapper.mapToEpisodes(patientRecords);

        assertNotNull(episodes);
        assertEquals(1, episodes.size());

        PatientRecordToEpisodeMapper.RecordKey key = new PatientRecordToEpisodeMapper.RecordKey("2020-12-09", "1");
        assertNotNull(episodes.get(key));
        Integer bloodPressure = episodes.get(key).getBloodPressure();
        assertEquals(160, bloodPressure.intValue());
    }

    private List<PatientRecord> getMockRecord() {
        List<PatientRecord> records = new ArrayList<>();
        records.add(PatientRecord.builder()
                .attributeName("Blood pressure")
                .attributeValue("160")
                .date("2020-12-09")
                .patientId("1").build());
        return records;
    }

    private List<PatientRecord> getMockUniquePatientRecords() {
        List<PatientRecord> records = new ArrayList<>();
        records.add(PatientRecord.builder()
                .attributeName("Blood pressure")
                .attributeValue("160")
                .date("2019-12-09")
                .patientId("1").build());
        records.add(PatientRecord.builder()
                .attributeName("Blood pressure")
                .attributeValue("140")
                .date("2019-12-09")
                .patientId("2").build());
        records.add(PatientRecord.builder()
                .attributeName("Age")
                .attributeValue("40")
                .date("2019-12-10")
                .patientId("1").build());
        return records;
    }

    private List<PatientRecord> getMockNonUniquePatientRecords() {
        List<PatientRecord> records = new ArrayList<>();
        records.add(PatientRecord.builder()
                .attributeName("Blood pressure")
                .attributeValue("150")
                .date("2019-12-09")
                .patientId("1").build());
        records.add(PatientRecord.builder()
                .attributeName("Blood pressure")
                .attributeValue("140")
                .date("2019-12-09")
                .patientId("2").build());
        records.add(PatientRecord.builder()
                .attributeName("Age")
                .attributeValue("40")
                .date("2019-12-09")
                .patientId("1").build());
        records.add(PatientRecord.builder()
                .attributeName("Glucose")
                .attributeValue("11.1")
                .date("2019-12-10")
                .patientId("2").build());
        return records;
    }

    private List<PatientRecord> getMockMultipPatientRecordsForOneEpisode() {
        List<PatientRecord> records = new ArrayList<>();
        records.add(PatientRecord.builder()
                .attributeName("Blood pressure")
                .attributeValue("130")
                .date("2019-12-09")
                .patientId("1").build());
        records.add(PatientRecord.builder()
                .attributeName("Diabetes")
                .attributeValue("TRUE")
                .date("2019-12-09")
                .patientId("1").build());
        records.add(PatientRecord.builder()
                .attributeName("Age")
                .attributeValue("43")
                .date("2019-12-09")
                .patientId("1").build());
        records.add(PatientRecord.builder()
                .attributeName("Glucose")
                .attributeValue("11.1")
                .date("2019-12-09")
                .patientId("1").build());
        return records;
    }
}