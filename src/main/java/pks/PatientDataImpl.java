package pks;

import pks.mapper.PatientRecordToEpisodeMapper;

import java.util.HashSet;
import java.util.Set;

public class PatientDataImpl implements PatientData {

    private final PatientDataService patentDataService;

    public PatientDataImpl() {
        this.patentDataService = new PatientDataService();
    }

    @Override
    public long getNumberOfEpisodes() {
        return patentDataService.getPatientData().size();
    }

    @Override
    public long getNumberOfPatients() {
        Set<PatientRecordToEpisodeMapper.RecordKey> keys = patentDataService.getPatientData().keySet();
        Set patientSet = new HashSet();
        for(PatientRecordToEpisodeMapper.RecordKey key: keys) {
            patientSet.add(key.getPatientId());
        }
        return patientSet.size();
    }

    @Override
    public double getAverageAgeByGender(String gender) {
        return patentDataService.getPatientData()
                .entrySet().stream()
                .filter(x -> x.getValue().getGender().equals(gender))
                .mapToDouble(x->x.getValue().getAge()).average().getAsDouble();
    }
}
