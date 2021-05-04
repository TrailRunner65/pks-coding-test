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
    @Builder.Default
    private String gender = "U";
    private Integer bloodPressure;
    private Float glucose;
    private Integer wcc;
    private Boolean diabetes;
}
