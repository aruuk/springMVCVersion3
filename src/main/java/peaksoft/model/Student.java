package peaksoft.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_gen")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    private Long id;

    @Pattern(message = "Bad formed person name: ${validatedValue}",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$")
    @Length(min = 2)
    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

//    @Pattern(message = "Bad formed person name: ${validatedValue}",
//            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$")
//    @Length(min = 2)
//    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private int phoneNumber;

//    @Email(message = "Email address has invalid format: ${validatedValue}",
//            regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    @Column(name = "email", length = 120)
    private String email;

    @Column(name = "study_format")
    private StudyFormat studyFormat;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Group group;

}
