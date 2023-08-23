package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Hospital;
import peaksoft.entities.Patient;
import peaksoft.repository.PatientRepository;
import peaksoft.service.PatientService;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor

public class PatientServiceImpl implements PatientService  {
    private final PatientRepository patientRepository;

    @Override
    public void savePatient(Long hospitalId, Patient patient) {
        patientRepository.findById(hospitalId);
        Hospital hospital = new Hospital();
        hospital.setPatient(patient);
        patient.setHospital(hospital);
        patientRepository.save(patient);

    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).get();
    }


    @Override
    public List<Patient> getAllPatient(Long id) {
        return patientRepository.findAll();
    }

    @Override
    public void updatePatient(Long id, Patient patient) {
        Patient Patient1 = getPatientById(id);
        Patient1.setFirstName(patient.getFirstName());
        Patient1.setLastName(patient.getLastName());
        Patient1.setPhoneNumber(patient.getPhoneNumber());
        Patient1.setGender(patient.getGender());
        patientRepository.save(Patient1);
    }




    @Override
    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);



    }
}
