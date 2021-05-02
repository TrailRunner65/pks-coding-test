package pks.mapper;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Before;
import org.junit.Test;
import pks.domain.Episode;
import pks.reader.PatientRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PatientRecordToEpisodeMapperTest {

    private PatientRecordToEpisodeMapper patientRecordToEpisodeMapper;

    @Before
    public void init() {
        patientRecordToEpisodeMapper = new PatientRecordToEpisodeMapper();
    }

    @Test
    public void shouldMapSingleRecordToEpisode() {
        Map<ImmutablePair<Date, Integer>, Episode> episodes = patientRecordToEpisodeMapper.mapToEpisodes(getMockRecord());
        assertNotNull(episodes);
        assertEquals(1, episodes.size());

        ImmutablePair<Date, Integer> key = new ImmutablePair<>(new Date(2019, 11, 9), 1);
        Integer bloodPressure = episodes.get(key).getBloodPressure();
        assertEquals(160, bloodPressure.intValue());
    }

    @Test
    public void shouldMapMultipleRecordsToDistinctEpisodes() {
        Map<ImmutablePair<Date, Integer>, Episode> episodes = patientRecordToEpisodeMapper.mapToEpisodes(getMockUniquePatientRecords());
        assertNotNull(episodes);
        assertEquals(3, episodes.size());

        ImmutablePair<Date, Integer> key = new ImmutablePair<>(new Date(2019, 11, 9), 1);
        Integer bloodPressure = episodes.get(key).getBloodPressure();
        assertEquals(160, bloodPressure.intValue());
    }

    @Test
    public void shouldMapMultipleRecordsToNonDistinctEpisodes() {
        Map<ImmutablePair<Date, Integer>, Episode> episodes = patientRecordToEpisodeMapper.mapToEpisodes(getMockNonUniquePatientRecords());
        assertNotNull(episodes);
        assertEquals(3, episodes.size());

        ImmutablePair<Date, Integer> key = new ImmutablePair<>(new Date(2019, 11, 9), 1);
        Integer bloodPressure = episodes.get(key).getBloodPressure();
        assertEquals(160, bloodPressure.intValue());
    }

    private List<PatientRecord> getMockRecord() {
        List<PatientRecord> records = new ArrayList<>();
        records.add(PatientRecord.builder()
                .attributeName(" Blood pressure")
                .attributeValue(" 160")
                .date(" 2019-12-09")
                .patientId(" 1").build());
        return records;
    }

    private List<PatientRecord> getMockUniquePatientRecords() {
        List<PatientRecord> records = new ArrayList<>();
        records.add(PatientRecord.builder()
                .attributeName(" Blood pressure")
                .attributeValue(" 160")
                .date(" 2019-12-09")
                .patientId(" 1").build());
        records.add(PatientRecord.builder()
                .attributeName(" Blood pressure")
                .attributeValue(" 140")
                .date(" 2019-12-09")
                .patientId(" 2").build());
        records.add(PatientRecord.builder()
                .attributeName(" Age")
                .attributeValue(" 40")
                .date(" 2019-12-10")
                .patientId(" 1").build());
        return records;
    }

    private List<PatientRecord> getMockNonUniquePatientRecords() {
        List<PatientRecord> records = new ArrayList<>();
        records.add(PatientRecord.builder()
                .attributeName(" Blood pressure")
                .attributeValue(" 150")
                .date(" 2019-12-09")
                .patientId(" 1").build());
        records.add(PatientRecord.builder()
                .attributeName(" Blood pressure")
                .attributeValue(" 140")
                .date(" 2019-12-09")
                .patientId(" 2").build());
        records.add(PatientRecord.builder()
                .attributeName(" Age")
                .attributeValue(" 40")
                .date(" 2019-12-09")
                .patientId(" 1").build());
        records.add(PatientRecord.builder()
                .attributeName(" Glucose")
                .attributeValue(" 11.1")
                .date(" 2019-12-10")
                .patientId(" 2").build());
        return records;
    }
}