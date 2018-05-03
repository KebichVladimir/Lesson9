import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashSet;

public class PatientBase {
    private LinkedHashSet<Patient> basePatient = new LinkedHashSet<>();
    private String nameFile;

    public PatientBase(String nameFile) {
        this.nameFile = nameFile;
    }

    public void addAll(LinkedHashSet<Patient> patientBase) {
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(nameFile))) {
            for (Patient patient : patientBase) {
                outputStream.writeUTF(patient.getFirstName());
                outputStream.writeUTF(patient.getSecondName());
                outputStream.writeLong(patient.getBirthday().getTime());
                outputStream.writeBoolean(patient.isHealthy());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedHashSet<Patient> getAll() {
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(nameFile))) {
            while (inputStream.available() > 0) {
                String firstName = inputStream.readUTF();
                String secondName = inputStream.readUTF();
                Date birthday = new Date(inputStream.readLong());
                boolean healthy = inputStream.readBoolean();
                basePatient.add(new Patient(firstName, secondName, birthday, healthy));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return basePatient;
    }
}

