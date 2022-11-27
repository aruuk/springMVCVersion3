package peaksoft.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Student;
import peaksoft.repository.StudentRepository;
import peaksoft.service.StudentService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents(Long id) {
        return studentRepository.getAllStudents(id);
    }

    @Override
    public void saveStudent(Student student, Long id){
        studentRepository.saveStudent(student, id);
    }

    @Override
    public void updateStudent(Long id, Student student) {
        studentRepository.updateStudent(id, student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteStudent(id);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public void assignStudentToGroup(Long studentId, Long groupId) {
        studentRepository.assignStudentToGroup(studentId, groupId);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }
}
