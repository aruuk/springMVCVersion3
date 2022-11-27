package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.model.Student;
import peaksoft.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> getAllStudents(Long id) {
        return entityManager.createQuery("select s from Student s " +
                "where s.group.id = :id", Student.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public void saveStudent(Student student, Long groupId) {
    Group group = entityManager.find(Group.class, groupId);
    group.addStudent(student);
    student.setGroup(group);
    entityManager.merge(student);
        for (Course c:student.getGroup().getCompany().getCourses()) {
            for (Instructor i: c.getInstructors()) {
                i.plus();
            }
        }
    }


    @Override
    public void updateStudent(Long id, Student student) {
        Student student1 = getStudentById(id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = entityManager.find(Student.class, id);
        student.getGroup().getCompany().minus();
        for (Course c:student.getGroup().getCompany().getCourses()) {
            for (Group g:c.getGroups()) {
                for (Student s:g.getStudents()) {
                    if (s.equals(student)){
                        for (Instructor i:c.getInstructors()) {
                            i.minus();
                        }
                    }
                }
            }
        }
        student.setGroup(null);
        entityManager.remove(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void assignStudentToGroup(Long studentId, Long groupId) {
        Student student = entityManager.find(Student.class, studentId);
        Group group = entityManager.find(Group.class, groupId);
        student.setGroup(group);
        group.addStudent(student);
        entityManager.merge(student);
    }

    @Override
    public List<Student> getStudents() {
        return entityManager.createQuery("select s from Student s", Student.class).getResultList();
    }

}
