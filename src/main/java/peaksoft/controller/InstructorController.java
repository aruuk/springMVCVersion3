package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Instructor;
import peaksoft.service.InstructorService;


@Controller
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/instructors/{id}")
    public String getAllInstructors(@PathVariable Long id, Model model) {
        model.addAttribute("instructors", instructorService.getAllInstructors(id));
        model.addAttribute("courseId",id);
        return "/instructor/instructors";
    }

    @GetMapping("/getInstructor/{id}")
    public String getInstructorById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("instructors", instructorService.getInstructorById(id));
        return "/instructor/instructors";
    }

    @GetMapping("/instructors/{id}/addInstructor")
    private String newInstructor(@PathVariable Long id, Model model) {
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("courseId", id);
        return "/instructor/addInstructor";
    }

    @PostMapping("/{id}/saveInstructor")
    private String saveInstructor(@ModelAttribute("instructor") Instructor instructor,
                                  @PathVariable Long id) {
        instructorService.saveInstructor(id, instructor);
        return "redirect:/instructors/ " + id;
    }

    @GetMapping("/updateInstructor/{id}")
    private String editInstructor(@PathVariable("id") Long id, Model model){
        Instructor instructor = instructorService.getInstructorById(id);
        model.addAttribute("instructor", instructor);
        model.addAttribute("courseId", instructor.getCourse().getId());
        return "instructor/updateInstructor";
    }


    @PostMapping("/{courseId}/{id}/updateInstructor")
    private String updateInstructor(@PathVariable("courseId") Long courseId,
                                    @PathVariable("id") Long id,
                                    @ModelAttribute("instructor") Instructor instructor) {
        instructorService.updateInstructor(id, instructor);
        return "redirect:/instructors/ " + courseId;

    }

    @GetMapping("/{courseId}/{id}/deleteInstructor")
    private String deleteInstructor(@PathVariable("id") Long id,
                                    @PathVariable("courseId") Long courseId) {
        instructorService.deleteInstructor(id);
        return "redirect:/instructors/ " + courseId;
    }

}
