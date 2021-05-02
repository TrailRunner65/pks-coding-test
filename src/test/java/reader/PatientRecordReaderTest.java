package reader;

import org.junit.Before;
import org.junit.Test;
import pks.reader.PatientRecord;
import pks.reader.PatientRecordReader;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PatientRecordReaderTest {

    private PatientRecordReader patientRecordReader;
    private File testFile;

    @Before
    public void init() {
        patientRecordReader = new PatientRecordReader();

        ClassLoader classLoader = getClass().getClassLoader();
        testFile = new File(classLoader.getResource("messages.csv").getFile());
    }

    @Test
    public void shouldReadAllFilesIntoPatientRecord() throws Exception {
        List<PatientRecord> patientRecords = patientRecordReader.readPatientRecords(testFile.getAbsolutePath());

        assertNotNull(patientRecords);
        assertEquals(23, patientRecords.size());

        PatientRecord firstRecord = patientRecords.get(0);
        assertEquals("1", firstRecord.getPatientId());
        //assertEquals(firstRecord.getPatientId(), );

    }

}