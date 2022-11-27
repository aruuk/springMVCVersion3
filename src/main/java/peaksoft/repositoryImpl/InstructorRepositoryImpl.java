package peaksoft.repositoryImpl;

import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.model.Student;
import peaksoft.repository.InstructorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Instructor> getAllInstructor(Long courseId) {
        return entityManager.createQuery("select g from Instructor g where " +
                "g.course.id = :id", Instructor.class)
                .setParameter("id", courseId).getResultList();
    }

    @Override
    public void saveInstructor(Long companyId, Instructor instructor) {
        Course course = entityManager.find(Course.class, companyId);
        if (course.getGroups()!=null){
            for (Group group : course.getGroups()) {
                for (Student student : group.getStudents()) {
                    instructor.plus();
                }
            }
        }
        course.addInstructor(instructor);
        instructor.setCourse(course);
        entityManager.merge(course);
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        Instructor instructor1 = entityManager.find(Instructor.class, id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setSpecialization(instructor.getSpecialization());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        entityManager.merge(instructor1);
    }

    @Override
    public void deleteInstructor(Long id) {
        entityManager.remove(entityManager.find(Instructor.class, id));
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return entityManager.find(Instructor.class, id);
    }

    @SneakyThrows
    @Override
    public void assignedInstructorToCourse(Long instructorId, Long courseId) {
        Instructor instructor = entityManager.find(Instructor.class, instructorId);
        Course course = entityManager.find(Course.class, courseId);
        if (course.getInstructors()!=null){
            for (Instructor g : course.getInstructors()) {
                if (Objects.equals(g.getId(), instructorId)) {
                    throw new IOException("This instructor already exists!");
                }
            }
        }
        for (Group g:instructor.getCourse().getGroups()) {
            for (Student s:g.getStudents()) {
                instructor.minus();
            }
        }
        for (Group g : course.getGroups()) {
            for (Student s : g.getStudents()) {
                instructor.plus();
            }
        }
        instructor.getCourse().getInstructors().remove(instructor);
        instructor.setCourse(course);
        course.addInstructor(instructor);
        entityManager.merge(instructor);
        entityManager.merge(course);
    }

    @Override
    public List<Instructor> getInstructors() {
        return entityManager.createQuery("select i from Instructor i", Instructor.class).getResultList();
    }

}
