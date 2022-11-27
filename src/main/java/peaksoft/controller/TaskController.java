package peaksoft.controller;

import peaksoft.model.Task;
import peaksoft.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/{lessId}")
    private String getAllTask(@PathVariable Long lessId, Model model) {
        model.addAttribute("tasks",taskService.getTasks(lessId));
        model.addAttribute("lid", lessId);
        return "task/tasks";
    }

    @GetMapping("/{id}/addTask")
    private String addTask(@PathVariable ("id") Long id, Model model) {
        model.addAttribute("task",new Task());
        model.addAttribute("id",id);
        return "task/addTask";
    }

    @PostMapping("{id}/saveTask")
    private String saveTask(@ModelAttribute("task") Task task,
                            @PathVariable("id") Long id) {
        taskService.saveTask(task,id );
        return "redirect:/tasks/tasks/ " +id;
    }

    @GetMapping("/edit/{id}")
    private String updateTask(@PathVariable("id") Long id , Model model) {
        model.addAttribute("task",new Task());
        Task task = taskService.getTaskById(id);
        model.addAttribute("task",task);
        model.addAttribute("lessonIdT",task.getLesson().getId());
        return "task/updateTask";
    }


    @PostMapping("{lessId}/{taskId}/update")
    private String saveUpdateTask(@PathVariable("lessId") Long lessId ,
                                  @PathVariable ("taskId") Long taskId,
                                  @ModelAttribute("task") Task task) {
        taskService.updateTask(taskId,task);
        return "redirect:/tasks/tasks/ " +lessId;
    }

    @PostMapping("/{lid}/{taskId}/delete")
    private String deleteTask(@PathVariable("lid") Long lid ,
                              @PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/tasks/tasks/ " + lid;
    }
}
