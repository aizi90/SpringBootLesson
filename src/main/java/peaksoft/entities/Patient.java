package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enam.Gender;

import java.util.List;

import static jakarta.persistence.CascadeType. *;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Patient {
    @Id
    @GeneratedValue(strategy = SEQUENCE,
            generator = "patient_id_gen")
    @SequenceGenerator(name = "patient_id_gen",
            sequenceName = "patient_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    private Gender gender;

    @Column(unique = true)
    private String email;

    @ManyToOne(cascade = {REFRESH, DETACH, MERGE, PERSIST}, fetch = FetchType.EAGER)
    private Hospital hospital;

    @OneToMany(mappedBy = "patient", cascade = {REFRESH, MERGE, DETACH, PERSIST}, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    public Patient(String firstName, String lastName, String phoneNumber, Gender gender, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;
    }

}