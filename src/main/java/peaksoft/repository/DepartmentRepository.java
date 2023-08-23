package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entities.Department;

public interface DepartmentRepository extends JpaRepository <Department, Long> {
}
