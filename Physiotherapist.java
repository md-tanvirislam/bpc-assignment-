import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Physiotherapist extends Person {
    private List<Treatment> treatments;
    private List<String> expertise;
    private List<Appointment> appointments;

    public Physiotherapist(int id, String name, String address, String phone) {
        super(id, name, address, phone);
        this.treatments = new ArrayList<>();
        this.expertise = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
    }

    public void addExpertise(String expertiseArea) {
        expertise.add(expertiseArea);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<String> getExpertise() {
        return expertise;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public Appointment findAppointmentByTime(LocalDateTime dateTime) {
        for (Appointment appointment : appointments) {
            if (appointment.getDateTime().equals(dateTime)) {
                return appointment;
            }
        }
        return null;
    }

    // New method to get available slots between startDate and endDate
    public List<LocalDateTime> getAvailableSlots(LocalDateTime startDate, LocalDateTime endDate) {
        List<LocalDateTime> availableSlots = new ArrayList<>();
        // Generate hourly slots between startDate and endDate
        LocalDateTime current = startDate;
        while (!current.isAfter(endDate)) {
            boolean isAvailable = true;
            // Check if this time slot is already taken by an appointment
            for (Appointment appointment : appointments) {
                if (appointment.getDateTime().equals(current)) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                availableSlots.add(current);
            }
            // Move to the next hour
            current = current.plus(1, ChronoUnit.HOURS);
        }
        return availableSlots;
    }
}