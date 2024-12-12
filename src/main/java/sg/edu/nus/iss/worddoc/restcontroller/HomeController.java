package sg.edu.nus.iss.worddoc.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import sg.edu.nus.iss.worddoc.model.Task;
import sg.edu.nus.iss.worddoc.service.RedisService;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private RedisService redisService;

    @GetMapping("/listing")
    public String getListing(@RequestParam(required = false) String status, Model model) {
        List<Task> tasks = redisService.getAllTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("selectedStatus", status);
        return "listing";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("task", new Task());
        return "add";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task) {
        redisService.save(task);
        return "redirect:/";
    }

    
}
