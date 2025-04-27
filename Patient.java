import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private List<Appointment> appointments;

    public Patient(int id, String name, String address, String phone) {
        super(id, name, address, phone);
        this.appointments = new ArrayList<>();
    }

    public void bookAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setStatus("booked");
    }

    public void cancelAppointment(Appointment appointment) {
        appointment.setStatus("cancelled");
        appointments.remove(appointment);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
