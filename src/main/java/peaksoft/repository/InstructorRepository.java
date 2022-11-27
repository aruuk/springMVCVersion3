package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Instructor;

import java.util.List;

@Repository
public interface InstructorRepository {

    List<Instructor> getAllInstructor(Long courseId);

    void saveInstructor(Long companyId, Instructor instructor) ;

    void updateInstructor(Long id, Instructor instructor) ;

    void deleteInstructor(Long id);

    Instructor getInstructorById(Long id);

    void assignedInstructorToCourse(Long instructorId, Long courseId);

    List<Instructor> getInstructors();
}
