package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;


@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_gen")
    @SequenceGenerator(name = "group_seq", sequenceName = "group_seq", allocationSize = 1)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "date_of_start")
    private String dateOfStart;

    @Column(length = 10000, name = "image")
    private String image;

    @ManyToMany(cascade = {MERGE, REFRESH, DETACH, REMOVE}, mappedBy ="groups")
    private List<Course> courses;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "group")
    private List<Student> students;

    @ManyToOne
    private Company company;

    private int student;

    public void plus(){
        student++;
    }

    public void minus(){
        student--;
    }


    public Group(String groupName, String dateOfStart, String image) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.image = image;
    }

    public void addStudent(Student student) {
        if (students==null) {
            students = new ArrayList<>();
        }
        else {
            students.add(student);
            this.getCompany().plus();
        }
    }

    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        plus();
    }
}