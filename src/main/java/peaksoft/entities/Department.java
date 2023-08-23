
package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="departments")
@Getter
@Setter
@NoArgsConstructor
@ToString


public class Department {
    @Id
    @GeneratedValue(strategy=SEQUENCE,
            generator="department_id_gen")

    @SequenceGenerator(name="department_gen",
            sequenceName="department_id_seg",
            allocationSize=1)

    private Long id;
    private String name;
    private String image;

    @ManyToMany(fetch=FetchType.LAZY,cascade = {DETACH,PERSIST,REFRESH,MERGE}, mappedBy = "departments")
    private List<Doctor> doctors;

    public void addDoctor(Doctor doctor){
        doctors.add(doctor);
    }
    @ManyToOne(cascade = {DETACH,REFRESH,MERGE,PERSIST},fetch=FetchType.LAZY)
    private Hospital hospital;

    public Department(String name,String image){
        this.name=name;
        this.image=image;
    }
    @OneToMany(mappedBy = "department")
    private List<Appointment> appointments;
    public void setDoctor(Doctor doctor){
        this.doctors.add(doctor);
    }


}
