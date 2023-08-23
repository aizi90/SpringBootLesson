package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entities.Appointment;

public interface AppointmentRepository  extends JpaRepository<Appointment, Long>{
}
