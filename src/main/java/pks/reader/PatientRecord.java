package pks.reader;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
}
