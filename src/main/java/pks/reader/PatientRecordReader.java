package pks.reader;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.util.List;

public class PatientRecordReader {
    @SneakyThrows
    public List<PatientRecord> readPatientRecords(String fileName) {
        final List records = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(PatientRecord.class)
                .withSkipLines(1)
                .build()
                .parse();

        return records;
    }
}
