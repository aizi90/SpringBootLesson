package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entities.Patient;

public interface PatientRepository extends JpaRepository <Patient ,Long>{
}
