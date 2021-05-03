package pks.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import pks.domain.Episode;
import pks.domain.Gender;
import pks.reader.PatientRecord;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientRecordToEpisodeMapper {

    @SneakyThrows
    public Map<RecordKey, Episode> mapToEpisodes(List<PatientRecord> patientRecords) {
        final Map<RecordKey, Episode> episodes = new HashMap<>();
        for (PatientRecord record : patientRecords) {
            RecordKey key = new RecordKey(record.getDate(), record.getPatientId());
            if (episodes.containsKey(key)) {
                mapAttribute(record.getAttributeName(), record.getAttributeValue(), episodes.get(key));
            } else {
              Episode newEpisode = Episode.builder()
                      .patientId(Integer.valueOf(record.getPatientId()))
                      .date(new SimpleDateFormat("yyyy-MM-dd").parse(record.getDate()))
                      .build();
              mapAttribute(record.getAttributeName(), record.getAttributeValue(), newEpisode);
              episodes.put(key, newEpisode);
            }
        }
        return episodes;
    }

    private Episode mapAttribute(String sourceKey, String sourceValue, Episode target) {
        switch (sourceKey) {
            case "Age":
                target.setAge(Integer.valueOf(sourceValue));
                break;
            case "Gender":
                target.setGender(Gender.valueOf(sourceValue));
                break;
            case "Blood pressure":
                target.setBloodPressure(Integer.valueOf(sourceValue));
                break;
            case "Diabetes":
                target.setDiabetes(Boolean.valueOf(sourceValue));
                break;
            case "WCG":
                target.setWcg(Integer.valueOf(sourceValue));
                break;
            case "Glucose":
                target.setGlucose(Float.valueOf(sourceValue));
                break;
        }
        return target;
    }

    @Data
    @AllArgsConstructor
    public static
    class RecordKey {
       String date;
       String patientId;
    }
}
