package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.entities.Doctor;

import java.util.List;

@Service

public interface DoctorService {
    void saveDoctor(Long hospitalId, Doctor doctor);


    Doctor getDoctorById(Long id);


    List<Doctor> getAllDoctor(Long id);


    void updateDoctor(Long id, Doctor doctor);


    void deleteDoctorById(Long id);


}
