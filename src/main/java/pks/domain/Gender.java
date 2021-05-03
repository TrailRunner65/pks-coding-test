package pks.domain;

public enum Gender {
    FEMALE("F"),
    MALE("M"),
    UNKNOWN("U"),
    OTHER("O");

    private String value;
    
    Gender(String genderString) {
        this.value = genderString;
    }

    public String getValue() {
        return this.value;
    }

    public static Gender fromValue(String v) {
        for (Gender g: Gender.values()) {
            if (g.value.equals(v)) {
                return g;
            }
        } throw new IllegalArgumentException(v);
    }

}
