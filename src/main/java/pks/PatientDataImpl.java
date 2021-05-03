package pks;

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
 /*       patentDataService.getPatientData()
            .entrySet().stream()
                .filter(x -> {
                    if (!x.getValue().getPatientId().contains("amazon") && !x.getValue().contains("digital")) {
                        return true;
                    }
                    return false;
                })
                .map(map -> map.getValue());*/
        return 0;
    }

    @Override
    public double getAverageAgeByGender(String gender) {
        return patentDataService.getPatientData()
                .entrySet().stream()
                .filter(x -> x.getValue().getGender().equals(gender))
                .mapToDouble(x->x.getValue().getAge()).average().getAsDouble();
    }
}
