package peaksoft.serviceImpl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Instructor;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.InstructorService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public void saveInstructor(Long id, Instructor instructor) {
        instructorRepository.saveInstructor(id, instructor);
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        instructorRepository.updateInstructor(id, instructor);
    }

    @Override
    public void deleteInstructor(Long id) {
        instructorRepository.deleteInstructor(id);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.getInstructorById(id);
    }

    @Override
    public List<Instructor> getAllInstructors(Long id) {
        return instructorRepository.getAllInstructor(id);
    }

    @SneakyThrows
    @Override
    public void assignedInstructorToCourse(Long instructorId, Long courseId) {
//        Instructor instructor = entityManager.find(Instructor.class, instructorId);
//        Course course = entityManager.find(Course.class, courseId);
//        if (course.getInstructors()!=null){
//            for (Instructor g : course.getInstructors()) {
//                if (g.getId() == instructorId) {
//                    throw new IOException("This instructor already exists!");
//                }
//            }
//        }
//        for (Group g:instructor.getCourse().getGroups()) {
//            for (Student s:g.getStudents()) {
//                instructor.minus();
//            }
//        }
//        for (Group g : course.getGroups()) {
//            for (Student s : g.getStudents()) {
//                instructor.plus();
//            }
//        }
        instructorRepository.assignedInstructorToCourse(instructorId, courseId);
    }

    @Override
    public List<Instructor> getInstructors() {
        return instructorRepository.getInstructors();
    }
}
