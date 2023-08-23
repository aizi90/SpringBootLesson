package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Doctor;
import peaksoft.entities.Hospital;
import peaksoft.repository.DoctorRepository;
import peaksoft.service.DoctorService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor

public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    @Override
    public void saveDoctor(Long hospitalId, Doctor doctor) {
        doctorRepository.findById(hospitalId);
        Hospital hospital = new Hospital();
        hospital
        doctor.setHospital(hospital);
        doctorRepository.save(doctor);


    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElseThrow(
                ()->new NullPointerException(
                        "Doctor with id: "+id+" is not found"
                )
        );

    }


    @Override
    public List<Doctor> getAllDoctor(Long id) {
        return doctorRepository.findAll();
    }

    @Override
    public void updateDoctor(Long id, Doctor doctor) {
        Doctor Doctor1 = getDoctorById(id);
        Doctor1.setFirstName(doctor.getFirstName());
        Doctor1.setLastName(doctor.getLastName());
        Doctor1.setEmail(doctor.getEmail());
        Doctor1.setPosition(doctor.getPosition());
        doctorRepository.save(doctor);



    }




    @Override
    public void deleteDoctorById(Long id) {
        doctorRepository.deleteById(id);

    }
}
