package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.model.Student;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;


@Controller
public class GroupController {

    private final GroupService groupService;
    private final CourseService courseService;

    @Autowired
    public GroupController(GroupService groupService, CourseService courseService) {
        this.groupService = groupService;
        this.courseService = courseService;
    }

    @GetMapping("/groups/{id}")
    public String getGroupCourse(Model model, @PathVariable("id") Long id) {
        model.addAttribute("groupCourses", groupService.getAllGroupByCourseId(id));
        model.addAttribute("courseId", id);
        return "/group/getGroupsByCourse";
    }

    @GetMapping("/groups/{id}/addGroup")
    public String addGroup(Model model, @PathVariable Long id) {
        model.addAttribute("group", new Group());
        model.addAttribute("courseId", id);
        return "/group/addGroup";
    }

    @PostMapping("/{id}/saveGroup")
    public String saveGroup(@ModelAttribute("group") Group group,
                            @PathVariable Long id) {
        groupService.saveGroup(group, id);
        return "redirect:/groups/ " + id;
    }

    @GetMapping("/updateGroup/{courseId}")
    private String editGroup(@PathVariable("courseId") Long id, Model model){
        Group group = groupService.getById(id);
        model.addAttribute("group", group);
        model.addAttribute("courseId", courseService.getCourseById(id));
        return "group/updateGroup";
    }

    @PostMapping("/{id}/saveUpdateGroup")
    public String saveUpdateGroup(@PathVariable("id") Long id,
                                  @ModelAttribute("group") Group group) {
        groupService.updateGroup(id, group);
        return "redirect:/groups/group/";
    }

    @GetMapping("/{courseId}/{id}/deleteGroup")
    public String deleteGroup(@PathVariable Long courseId, @PathVariable Long id) {
        groupService.deleteGroup(id);
        return "redirect:/groups/ " + courseId;
    }

    //by course id

    @GetMapping("/groups/{id}/addGroupByCourseId")
    public String addGroupByCourseId( @PathVariable("id") Long id, Model model) {
        model.addAttribute("newGroup", new Group());
        model.addAttribute("courseId", id);
        return "/group/addGroup";
    }

    @PostMapping("/{courseId}/{id}/saveGroupByCourseId")
    public String saveGroupByCourseId(@ModelAttribute("group") Group group,
                                      @PathVariable("id") Long id, @PathVariable("courseId") Long courseId) {
        groupService.addGroupByCourseId(id,courseId, group);
        return "redirect:/groups/ " + courseId + "/" + id;
    }

    @GetMapping("/updateGroupByCourseId/{courseId}/{id}")
    public String updateGroupByCourseId(@PathVariable("id") Long id, @PathVariable("courseId") Long courseId, Model model) {
        Group group = groupService.getById(id);
        model.addAttribute("group", group);
        model.addAttribute("courseId", courseId);
        return "/group/updateGroup";
    }

    @PostMapping("/{courseId}/{id}/saveUpdateGroupByCourseId")
    public String saveUpdateGroupByCourseId(@PathVariable("courseId") Long courseId,
                                            @PathVariable("id") Long id,
                                            @ModelAttribute("group") Group group) {
        groupService.updateGroup(id, group);
        return "redirect:/groups/ " + courseId;

    }

    @GetMapping("/{courseId}/{id}/deleteGroupByCourseId")
    public String deleteGroupCourseId(@PathVariable("id") Long id, @PathVariable("courseId") Long courseId) {
        groupService.deleteGroup(id);
        return "redirect:/groups/ " + courseId;
    }

}
