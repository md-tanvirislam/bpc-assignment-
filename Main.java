import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Clinic clinic = new Clinic();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Sample data for testing
        Physiotherapist physio1 = new Physiotherapist(1, "Dr. Cheng", "Baker Street", "123456");
        physio1.addExpertise("Physiotherapy");
        physio1.addExpertise("Sports Therapy");
        physio1.addTreatment(new Treatment("Massage", "Physiotherapy"));
        physio1.addTreatment(new Treatment("Sports Injury Rehab", "Sports Therapy"));

        Physiotherapist physio2 = new Physiotherapist(2, "Dr. Hussain", "Ruskin Avenue", "098765");
        physio2.addExpertise("Orthopedic Therapy");
        physio2.addTreatment(new Treatment("Joint Mobilization", "Orthopedic Therapy"));

        Physiotherapist physio3 = new Physiotherapist(3, "Dr. Narendra", "Turnpike Lane", "456789");
        physio3.addExpertise("Neurological Therapy");
        physio3.addTreatment(new Treatment("Neuro Rehab", "Neurological Therapy"));

        clinic.getPhysiotherapists().add(physio1);
        clinic.getPhysiotherapists().add(physio2);
        clinic.getPhysiotherapists().add(physio3);

        while (true) {
            System.out.println("=== Boost Physio Clinic ===");
            System.out.println("1. ADD PATIENT");
            System.out.println("2. REMOVE PATIENT");
            System.out.println("3. BOOK APPOINTMENT BY EXPERTISE");
            System.out.println("4. BOOK APPOINTMENT BY PHYSIOTHERAPIST");
            System.out.println("5. CHECK IN APPOINTMENT");
            System.out.println("6. CANCEL APPOINTMENT");
            System.out.println("7. GENERATE REPORT");
            System.out.println("8. EXIT");
            System.out.println("CHOOSE AN OPTION:");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.println("Enter patient ID:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter patient name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter patient address:");
                    String address = scanner.nextLine();
                    System.out.println("Enter patient phone:");
                    String phone = scanner.nextLine();
                    Patient patient = new Patient(id, name, address, phone);
                    clinic.addPatient(patient);
                    System.out.println("Patient added successfully.");
                    break;

                case 2:
                    System.out.println("Enter patient ID to remove:");
                    int removeId = scanner.nextInt();
                    Patient patientToRemove = clinic.findPatientById(removeId);
                    if (patientToRemove != null) {
                        clinic.removePatient(patientToRemove);
                        System.out.println("Patient removed successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;

                case 3:
                    System.out.println("Enter expertise (e.g., Physiotherapy):");
                    String expertise = scanner.nextLine();
                    List<Physiotherapist> physios = clinic.findPhysiotherapistsByExpertise(expertise);
                    if (physios.isEmpty()) {
                        System.out.println("No physiotherapists found with that expertise.");
                        break;
                    }
                    System.out.println("Available physiotherapists:");
                    for (Physiotherapist p : physios) {
                        System.out.println(p.getId() + ": " + p.getName());
                    }
                    System.out.println("Enter physiotherapist ID:");
                    int physioId = scanner.nextInt();
                    scanner.nextLine();
                    Physiotherapist selectedPhysio = null;
                    for (Physiotherapist p : physios) {
                        if (p.getId() == physioId) {
                            selectedPhysio = p;
                            break;
                        }
                    }
                    if (selectedPhysio == null) {
                        System.out.println("Invalid physiotherapist ID.");
                        break;
                    }
                    System.out.println("Enter start date (yyyy-MM-dd HH:mm):");
                    LocalDateTime startDate = LocalDateTime.parse(scanner.nextLine(), formatter);
                    System.out.println("Enter end date (yyyy-MM-dd HH:mm):");
                    LocalDateTime endDate = LocalDateTime.parse(scanner.nextLine(), formatter);
                    List<LocalDateTime> slots = selectedPhysio.getAvailableSlots(startDate, endDate);
                    if (slots.isEmpty()) {
                        System.out.println("No available slots.");
                        break;
                    }
                    System.out.println("Available slots:");
                    for (LocalDateTime slot : slots) {
                        System.out.println(slot.format(formatter));
                    }
                    System.out.println("Enter appointment time (yyyy-MM-dd HH:mm):");
                    LocalDateTime appointmentTime = LocalDateTime.parse(scanner.nextLine(), formatter);
                    System.out.println("Enter patient ID:");
                    int patientId = scanner.nextInt();
                    scanner.nextLine();
                    Patient patientForBooking = clinic.findPatientById(patientId);
                    if (patientForBooking == null) {
                        System.out.println("Patient not found.");
                        break;
                    }
                    Appointment appointment = new Appointment(appointmentTime, selectedPhysio, selectedPhysio.getTreatments().get(0));
                    selectedPhysio.addAppointment(appointment);
                    clinic.bookAppointment(patientForBooking, appointment);
                    System.out.println("Appointment booked successfully.");
                    break;

                case 4:
                    System.out.println("Available Physiotherapists:");
                    for (Physiotherapist p : clinic.getPhysiotherapists()) {
                        System.out.println("- " + p.getName());
                    }
                    System.out.println("Enter physiotherapist name:");
                    String physioName = scanner.nextLine();
                    Physiotherapist physioByName = clinic.findPhysiotherapistByName(physioName);
                    if (physioByName == null) {
                        System.out.println("Physiotherapist not found.");
                        break;
                    }
                    System.out.println("Enter start date (yyyy-MM-dd HH:mm):");
                    startDate = LocalDateTime.parse(scanner.nextLine(), formatter);
                    System.out.println("Enter end date (yyyy-MM-dd HH:mm):");
                    endDate = LocalDateTime.parse(scanner.nextLine(), formatter);
                    slots = physioByName.getAvailableSlots(startDate, endDate);
                    if (slots.isEmpty()) {
                        System.out.println("No available slots.");
                        break;
                    }
                    System.out.println("Available slots:");
                    for (LocalDateTime slot : slots) {
                        System.out.println(slot.format(formatter));
                    }
                    System.out.println("Enter appointment time (yyyy-MM-dd HH:mm):");
                    appointmentTime = LocalDateTime.parse(scanner.nextLine(), formatter);
                    System.out.println("Enter patient ID:");
                    patientId = scanner.nextInt();
                    scanner.nextLine();
                    patientForBooking = clinic.findPatientById(patientId);
                    if (patientForBooking == null) {
                        System.out.println("Patient not found.");
                        break;
                    }
                    appointment = new Appointment(appointmentTime, physioByName, physioByName.getTreatments().get(0));
                    physioByName.addAppointment(appointment);
                    clinic.bookAppointment(patientForBooking, appointment);
                    System.out.println("Appointment booked successfully.");
                    break;

                case 5:
                    System.out.println("Available Physiotherapists:");
                    for (Physiotherapist p : clinic.getPhysiotherapists()) {
                        System.out.println("- " + p.getName());
                    }
                    System.out.println("Enter physiotherapist name:");
                    physioName = scanner.nextLine();
                    physioByName = clinic.findPhysiotherapistByName(physioName);
                    if (physioByName == null) {
                        System.out.println("Physiotherapist not found.");
                        break;
                    }
                    System.out.println("Enter appointment time (yyyy-MM-dd HH:mm):");
                    appointmentTime = LocalDateTime.parse(scanner.nextLine(), formatter);
                    appointment = clinic.findAppointmentByPhysioAndTime(physioByName, appointmentTime);
                    if (appointment == null) {
                        System.out.println("Appointment not found.");
                        break;
                    }
                    appointment.checkIn();
                    System.out.println("Appointment checked in successfully.");
                    break;

                case 6:
                    System.out.println("Enter physiotherapist name:");
                    physioName = scanner.nextLine();
                    physioByName = clinic.findPhysiotherapistByName(physioName);
                    if (physioByName == null) {
                        System.out.println("Physiotherapist not found.");
                        break;
                    }
                    System.out.println("Enter appointment time (yyyy-MM-dd HH:mm):");
                    appointmentTime = LocalDateTime.parse(scanner.nextLine(), formatter);
                    appointment = clinic.findAppointmentByPhysioAndTime(physioByName, appointmentTime);
                    if (appointment == null) {
                        System.out.println("Appointment not found.");
                        break;
                    }
                    clinic.cancelAppointment(appointment);
                    System.out.println("Appointment canceled successfully.");
                    break;

                case 7:
                    System.out.println("=== Clinic Report ===");
                    for (Physiotherapist p : clinic.getPhysiotherapists()) {
                        System.out.println("Physiotherapist: " + p.getName());
                        for (Appointment app : p.getAppointments()) {
                            System.out.println("  " + app.getDateTime().format(formatter) + " - " + app.getStatus() + " - " + (app.getPatient() != null ? app.getPatient().getName() : "No patient"));
                        }
                    }
                    break;

                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}