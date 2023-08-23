package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Appointment;
import peaksoft.entities.Hospital;
import peaksoft.repository.AppointmentRepository;
import peaksoft.service.*;

import java.io.IOException;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final AppointmentService appointmentService;

    private final PatientService patientService;

    private final HospitalService hospitalService;

    private final DoctorService doctorService;

    private final DepartmentService departmentService;


    @Override
    public void saveAppointment(Long hospitalId, Appointment appointment) {
        try {
            Hospital hospital = hospitalService.getHospitalById(hospitalId);
            Appointment newAppointment = new Appointment();
            newAppointment.setDate(appointment.getDate());
            newAppointment.setPatient(patientService.getPatientById(appointment.getPatientId()));
            newAppointment.setDoctor(doctorService.getDoctorById(appointment.getDoctorId()));
            newAppointment.setDepartment(departmentService.getDepartmentById(appointment.getDepartmentId()));
            hospital.addAppointment(newAppointment);
            appointmentRepository.save(newAppointment);

            int d = appointment.getDate().getYear();
            if (d <= 23) {
                throw new IOException("You cannot register!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).get();
    }



    @Override
    public List<Appointment> getAllAppointment(Long id) {
        return appointmentRepository.findAll();
    }

    @Override
    public void updateAppointment(Long id, Appointment appointment) {
        Appointment oldAppointment = appointmentRepository.findById(id).get();
        oldAppointment.setDate(appointment.getDate());
        appointmentRepository.save(oldAppointment);

    }

    @Override
    public void deleteAppointmentById(Long id, Long hospitalId) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        hospitalService.getHospitalById(hospitalId).getAppointments().remove(appointment);
        appointment.getDoctor().getAppointments().remove(appointment);
        appointment.getPatient().getAppointments().remove(appointment);
        appointmentService.deleteAppointmentById(id, hospitalId);

    }

}