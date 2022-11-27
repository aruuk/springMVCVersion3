package peaksoft.controller;

import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Student;
import peaksoft.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/{id}")
    private String getAllStudents(@PathVariable Long id, Model model,
                                  @ModelAttribute("course") Course course,
                                  @ModelAttribute("group") Group group) {
        model.addAttribute("students", studentService.getAllStudents(id));
        model.addAttribute("groupId", id);
        return "/student/students";
    }


    @GetMapping("/{id}/addStudent")
    private String addStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("groupId", id);
        return "/student/addStudent";
    }

    @PostMapping("/{id}/saveStudent")
    private String saveStudent(@ModelAttribute("student") Student student,
                               @PathVariable Long id) {
        studentService.saveStudent(student, id);
        return "redirect:/students/students/ " + id;
    }

    @GetMapping("/edit/{id}")
    private String updateStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("groupId", student.getGroup().getId());
        return "/student/updateStudent";
    }

    @PostMapping("/{groupId}/{studentId}/updateStudent")
    private String saveUpdateStudent(@PathVariable("studentId") Long studentId,
                                     @PathVariable("groupId") Long id,
                                     @ModelAttribute("student") Student student) {
        studentService.updateStudent(studentId, student);
        return "redirect:/students/students/ " + id;
    }


    @PostMapping("/{id}/{groupId}")
    private String deleteStudent(@PathVariable("id") Long id,
                                 @PathVariable("groupId") Long groupId) {
        studentService.deleteStudent(id);
        return "redirect:/students/students/ " + groupId;
    }

    @PostMapping("/{groupId}/assignGroupToStudent")
    private String assignGroupToStudent(@PathVariable("groupId") Long groupId,
                                        @ModelAttribute("student") Student student) {
        studentService.assignStudentToGroup(groupId, student.getId());
        return "redirect:/students/students/ " + groupId;
    }
}