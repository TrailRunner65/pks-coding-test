package pks.mapper;

import org.apache.commons.lang3.tuple.ImmutablePair;
import pks.domain.Episode;
import pks.domain.Gender;
import pks.reader.PatientRecord;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientRecordToEpisodeMapper {
    public Map<ImmutablePair<Date, Integer>, Episode> mapToEpisodes(List<PatientRecord> patientRecords) {
        final Map<ImmutablePair<Date, Integer>, Episode> episodes = new HashMap<>();
        for (PatientRecord record : patientRecords) {
            ImmutablePair<Date, Integer> key = new ImmutablePair(record.getDate(), record.getPatientId());
            if (episodes.containsKey(key)) {
                mapAttribute(record.getAttributeName(), record.getAttributeValue(), episodes.get(key));
            } else {
              Episode newEpisode = Episode.builder()
                      .patientId(Integer.valueOf(record.getPatientId().trim()))  // FIXME - remove trim
                      .date(new Date()) // FIXME - Format and set real date
                      .build();
              mapAttribute(record.getAttributeName(), record.getAttributeValue(), newEpisode);
              episodes.put(key, newEpisode);
            }
        }
        return episodes;
    }

    private Episode mapAttribute(String sourceKey, String sourceValue, Episode target) {
        switch (sourceKey) {
            case " Age":
                target.setAge(Integer.valueOf(sourceValue.trim())); // FIXME - remove trim
                break;
            case " Gender":
                target.setGender(Gender.valueOf(sourceValue));
                break;
            case " Blood pressure":
                target.setBloodPressure(Integer.valueOf(sourceValue.trim())); // FIXME - remove trim
                break;
        }
        return target;
    }
}
