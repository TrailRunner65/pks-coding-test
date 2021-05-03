package pks;

import lombok.SneakyThrows;
import pks.domain.Episode;
import pks.mapper.PatientRecordToEpisodeMapper;
import pks.reader.PatientRecord;
import pks.reader.PatientRecordReader;

import java.io.File;
import java.util.List;
import java.util.Map;

public class PatientDataService {
    PatientRecordReader reader;
    PatientRecordToEpisodeMapper mapper;

    public PatientDataService() {
        reader = new PatientRecordReader();
        mapper = new PatientRecordToEpisodeMapper();
    }

    @SneakyThrows
    public Map<PatientRecordToEpisodeMapper.RecordKey, Episode> getPatientData() {
        ClassLoader classLoader = getClass().getClassLoader();
        File rawDataFile = new File(classLoader.getResource("messages.csv").getFile());
        List<PatientRecord> records = reader.readPatientRecords(rawDataFile.getAbsolutePath());
        Map<PatientRecordToEpisodeMapper.RecordKey, Episode> episodes = mapper.mapToEpisodes(records);

        return episodes;
    }
}
