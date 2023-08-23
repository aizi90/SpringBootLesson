package peaksoft.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Doctor;
import peaksoft.entities.Hospital;

import java.util.List;

@Service

public interface HospitalService {

    void saveHospital(Hospital hospital);



    Hospital getHospitalById(Long id);
    List<Hospital> getAllHospital();


    void updateHospital(Long id,Hospital hospital);


    void deleteHospitalById(Long hospitalId);
}





