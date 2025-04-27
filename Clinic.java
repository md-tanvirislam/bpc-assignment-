import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Clinic {
    private List<Physiotherapist> physiotherapists;
    private List<Patient> patients;

    public Clinic() {
        this.physiotherapists = new ArrayList<>();
        this.patients = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void removePatient(Patient patient) {
        // Cancel all appointments for this patient
        for (Physiotherapist physio : physiotherapists) {
            for (Appointment appointment : physio.getAppointments()) {
                if (appointment.getPatient() != null && appointment.getPatient().equals(patient)) {
                    appointment.setStatus("cancelled");
                    appointment.setPatient(null); // Optional: Clear the patient reference
                }
            }
        }
        // Remove the patient from the clinic's patient list
        patients.remove(patient);
        // Also remove the patient’s reference to appointments
        patient.getAppointments().clear();
    }

    public void bookAppointment(Patient patient, Appointment appointment) {
        appointment.setPatient(patient);
        patient.bookAppointment(appointment);
    }

    public List<Physiotherapist> findPhysiotherapistsByExpertise(String expertise) {
        List<Physiotherapist> matchingPhysios = new ArrayList<>();
        for (Physiotherapist physio : physiotherapists) {
            if (physio.getExpertise().contains(expertise)) {
                matchingPhysios.add(physio);
            }
        }
        return matchingPhysios;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Physiotherapist> getPhysiotherapists() {
        return physiotherapists;
    }

    // Additional methods for Main.java functionality
    public Physiotherapist findPhysiotherapistByName(String name) {
        for (Physiotherapist physio : physiotherapists) {
            if (physio.getName().equalsIgnoreCase(name)) {
                return physio;
            }
        }
        return null;
    }

    public Patient findPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    public Appointment findAppointmentByPhysioAndTime(Physiotherapist physio, LocalDateTime dateTime) {
        for (Appointment appointment : physio.getAppointments()) {
            if (appointment.getDateTime().equals(dateTime)) {
                return appointment;
            }
        }
        return null;
    }
}
