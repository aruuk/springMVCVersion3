package peaksoft.model;

import javax.persistence.*;
import javax.validation.constraints.Email;

import lombok.*;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "instructor_gen")
    @SequenceGenerator(name = "instructor_seq",sequenceName = "instructor_seq",allocationSize = 1)
    private Long id;

    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Email
    private String email;

    private String specialization;

    @ManyToOne(cascade = {MERGE, DETACH, CascadeType.REFRESH})
    private Course course;


    private int students = 0;

    public void plusStudent(Course course1){
        for (Group group : course1.getGroups()) {
            for (Student student: group.getStudents()) {
                students++;
            }
        }
    }

    public void plus(){
        students++;
    }

    public void minus(){
        students--;
    }

    public Instructor(String firstName, String lastName, String phoneNumber, String email, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.specialization = specialization;
    }
}
