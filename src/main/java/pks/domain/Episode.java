package pks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Episode {
    private Date date;
    private Integer patientId;
    private Integer age;
    private Gender gender;
    private Integer bloodPressure;
    private Float glucose;
    private Integer wcg;
    private Boolean diabetes;
}
