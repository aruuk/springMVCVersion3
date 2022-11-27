package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.model.Student;
import peaksoft.repository.StudentRepository;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;
import peaksoft.service.InstructorService;
import peaksoft.service.StudentService;


@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final GroupService groupService;
    private final InstructorService instructorService;
    private final StudentService studentService;

    @Autowired
    public CourseController(CourseService courseService, GroupService groupService,
                            InstructorService instructorService, StudentService studentService) {
        this.courseService = courseService;
        this.groupService = groupService;
        this.instructorService = instructorService;
        this.studentService = studentService;
    }

    @GetMapping("/courses/{id}")
    private String getAllCourses(@PathVariable Long id, Model model,
                                 @ModelAttribute("group") Group group,
                                 @ModelAttribute("instructor")  Instructor instructor,
                                 @ModelAttribute("student") Student student){
        model.addAttribute("courses", courseService.getAllCourses(id));
        model.addAttribute("instructors", instructorService.getInstructors());
        model.addAttribute("companyId", id);
        model.addAttribute("groups", groupService.getAllGroup(id));
        model.addAttribute("students", studentService.getStudents());
        return "course/courses";
    }

    @GetMapping("/{id}/addCourse")
    private String addCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("companyId", id);
        return "course/addCourse";
    }


    @PostMapping("/{id}/saveCourse")
    private String saveCourse(@ModelAttribute("course") Course course,
                              @PathVariable Long id) {
        courseService.saveCourse(course, id);
        return "redirect:/courses/courses/ " + id;
    }


    @GetMapping("/edit/{id}")
    private String updateCourse(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("companyId", course.getCompany().getId());
        return "/course/updateCourse";
    }


    @PostMapping("{id}/{courseId}/update")
    private String saveUpdateCourse(@PathVariable("courseId") Long courseId,
                                    @PathVariable("id") Long id,
                                    @ModelAttribute("course") Course course){
        courseService.updateCourse(courseId, course);
        return "redirect:/courses/courses/ " + id;
    }


    @PostMapping("/{id}/{compId}")
    private String deleteCourse(@PathVariable("id") Long id,
                                @PathVariable("compId") Long compId) {
        courseService.deleteCourse(id);
        return "redirect:/courses/courses/ " + compId;
    }

    @PostMapping("/{courseId}/assignGroup")
    private String assignGroupToCourse(@PathVariable("courseId") Long courseId,
                                @ModelAttribute("group") Group group){
        groupService.assignGroupToCourse(courseId, group.getId());
        return "redirect:/courses/courses/ " + courseId;
    }

    @PostMapping("/{courseId}/assignInstructor")
    private String assignInstructorToCourse(@PathVariable("courseId") Long courseId,
                                        @ModelAttribute("instructor") Instructor instructor) {
        instructorService.assignedInstructorToCourse(instructor.getId(), courseId);
        return "redirect:/courses/courses/ " + courseId;
    }

}
