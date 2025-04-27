import java.time.LocalDateTime;

public class Appointment {
    private LocalDateTime dateTime;
    private String status; // "available", "booked", "cancelled", "attended"
    private Physiotherapist physiotherapist;
    private Patient patient;
    private Treatment treatment;

    public Appointment(LocalDateTime dateTime, Physiotherapist physiotherapist, Treatment treatment) {
        this.dateTime = dateTime;
        this.status = "available";
        this.physiotherapist = physiotherapist;
        this.treatment = treatment;
        this.patient = null;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void checkIn() {
        this.status = "attended";
    }

    public void changeAppointment(LocalDateTime newDateTime) {
        this.status = "cancelled";
        // New appointment should be created by Clinic
    }

    // Getters
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getStatus() {
        return status;
    }

    public Physiotherapist getPhysiotherapist() {
        return physiotherapist;
    }

    public Patient getPatient() {
        return patient;
    }

    public Treatment getTreatment() {
        return treatment;
    }
}
