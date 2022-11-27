package peaksoft.service;

import peaksoft.model.Instructor;

import java.io.IOException;
import java.util.List;

public interface InstructorService {

    void saveInstructor(Long id, Instructor instructor) ;

    void updateInstructor(Long id, Instructor instructor);

    void deleteInstructor(Long id);

    Instructor getInstructorById(Long id);

    List<Instructor> getAllInstructors(Long id);

    void assignedInstructorToCourse(Long instructorId, Long courseId);

    List<Instructor> getInstructors();

}
