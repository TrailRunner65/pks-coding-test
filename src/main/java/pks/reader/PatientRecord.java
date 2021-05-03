package pks.reader;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientRecord {
    @CsvBindByPosition(position = 0)
    private String date;

    @CsvBindByPosition(position = 1)
    private String patientId;

    @CsvBindByPosition(position = 2)
    private String attributeName;

    @CsvBindByPosition(position = 3)
    private String attributeValue;

    public void setDate(String date) {
        this.date = date.trim();
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId.trim();
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName.trim();
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue.trim();
    }
}
