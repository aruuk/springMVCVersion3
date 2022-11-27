package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Student;

import java.io.IOException;
import java.util.List;

@Repository
public interface StudentRepository {

    List<Student> getAllStudents(Long id);

    void saveStudent(Student student, Long id) ;

    void updateStudent(Long id, Student student);

    void deleteStudent(Long id);

    Student getStudentById(Long id);

    void assignStudentToGroup(Long studentId, Long groupId);

    List<Student> getStudents();
}
