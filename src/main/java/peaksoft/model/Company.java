package peaksoft.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_gen")
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", allocationSize = 1)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "located_country")
    private String locatedCountry;

    @OneToMany(cascade = {MERGE, REFRESH, DETACH, REMOVE, REFRESH}, fetch = LAZY, mappedBy ="company")
    private List<Course> courses;

    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }

    public void addCourse(Course newCourse) {
        if (courses == null){
            courses = new ArrayList<>();
        }
        courses.add(newCourse);
    }

    private int student;

    public void plus(){
        student++;
    }
    public void minus(){
        student--;
    }

}
