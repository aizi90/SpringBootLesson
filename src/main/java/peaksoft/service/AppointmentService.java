package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.entities.Appointment;

import java.util.List;
@Service

public interface AppointmentService {
    void saveAppointment(Long hospitalId, Appointment appointment);


    Appointment getAppointmentById(Long id);


    List<Appointment> getAllAppointments(Long id);


    void updateAppointment(Long id, Appointment appointment);


    void deleteAppointmentById(Long id,Long hospitalId);
}
