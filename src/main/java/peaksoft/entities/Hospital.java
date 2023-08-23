package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="hospitals")
@Getter
@Setter
@NoArgsConstructor
@ToString//D

public class Hospital {
    @Id
    @GeneratedValue(strategy = SEQUENCE,
            generator = "hospital_gen")

    @SequenceGenerator(name = "hospital_gen",
            sequenceName = "hospital_seq",
            allocationSize = 1)

    private Long id;
    @Column(name = "first_name")
    private String name;
    @Column(name = "address")

    private String address;

    private String image;
    private String email;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Department> departments;

    public void addDepartment(Department department) {
        departments.add(department);
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "hospital")
    private List<Patient> patients;

    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctors;
    public Hospital(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address=address;

    }

    public void setDepartment(Department department) {
        this.departments.add(department);
    }

}