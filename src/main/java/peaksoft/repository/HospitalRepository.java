package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entities.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
