package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="doctors")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Doctor {
    @Id
    @GeneratedValue(
            strategy=SEQUENCE,
            generator="doc_id_gen")

    @SequenceGenerator(name="doc_id_gen",
            sequenceName="doc_id_seg",
            allocationSize = 1)

    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private  String lastName;
    private String position;
    @Column(unique = true)
    private String email;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Department> departments;
    public void addDepartment(Department department) {
        departments.add(department);
    }

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @ManyToOne(cascade = {REFRESH, MERGE, DETACH, PERSIST})
    private Hospital hospital;

    public Doctor(String firstName, String lastName, String position, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
    }

    public void setDepartment(Department department) {
        this.departments.add(department);
    }







}