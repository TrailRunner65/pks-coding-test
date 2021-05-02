package pks.reader;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class PatientRecordReader {
    public List<PatientRecord> readPatientRecords(String fileName) throws IOException {
        final List records = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(PatientRecord.class)
                .withSkipLines(1)
                .build()
                .parse();

        return records;
    }
}
