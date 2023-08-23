package peaksoft.service.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import peaksoft.entities.Hospital;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.HospitalService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;
    @Override
    public void saveHospital(Hospital hospital) {
        hospitalRepository.save(hospital);

    }

    @Override
    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findById(id).orElseThrow(
                ()->new NullPointerException(
                        "Hospital with id: "+id+" is not found"
                )
        );

    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalRepository.findAll();
    }

    @Override
    public void updateHospital(Long id, Hospital hospital) {
      Hospital Hospital1=getHospitalById(id);
      Hospital1.setName(hospital.getName());
      Hospital1.setAddress(hospital.getAddress());
        hospitalRepository.save(Hospital1);//жаны нерсе келип жатса save koldonobuz




    }

    @Override
    public void deleteHospitalById(Long hospitalId) {
        if(hospitalRepository.existsById(hospitalId)) {//existsByid repositorynin metodu
            hospitalRepository.deleteById(hospitalId);//обьект  очурот deletebyid
        }else throw new NullPointerException(
                "Hospital with id: " + hospitalId+"doesn't exists!"
        );

        }


    }



