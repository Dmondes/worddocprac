package sg.edu.nus.iss.worddoc.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import sg.edu.nus.iss.worddoc.model.Task;
import sg.edu.nus.iss.worddoc.repo.ListRepo;
import sg.edu.nus.iss.worddoc.repo.MapRepo;

@Service
public class RedisService {

    @Autowired 
    private ListRepo listRepo;
    @Autowired
    private MapRepo mapRepo;

    public void setList(String key, String value, int index) {
        listRepo.setList(key, value, index);
    }

    public void setMap(String key, String mapkey, String value) {
        mapRepo.setMap(key, mapkey, value);
    }

    public void addToBackList(String key, String value) {
        listRepo.addToBackList(key, value);
    }

    public void addAllToBackList(String key, List<String> values) {
        listRepo.addAllToBackList(key, values);
    }

    public List<String> getList(String key) {
        return listRepo.getList(key);
    }

    public List<Task> getAllTasks() {
        List<String> tasks = getList("todos");
        List<Task> taskList = new ArrayList<>();
        
        if (tasks != null) {
            for (String taskJson : tasks) {
                try {
                    // Convert JSON string into ToDo object using JSONObject
                    JSONObject jsonObject = new JSONObject(taskJson); // Changed 'json' to 'taskJson'
                    Task todo = new Task();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MM/dd/yyyy");
                    todo.setId(jsonObject.optString("id"));
                    todo.setName(jsonObject.optString("name"));
                    todo.setDescription(jsonObject.optString("description"));
                    Date dueDate = dateFormat.parse(jsonObject.optString("due_date"));
                    todo.setDueDate(dueDate); // Changed opt to optString
                    todo.setPriorityLevel(jsonObject.optString("priority_level"));
                    todo.setStatus(jsonObject.optString("status"));
                    Date createdAt = dateFormat.parse(jsonObject.optString("created_at"));
                    todo.setCreatedAt(createdAt);
                    Date updatedAt = dateFormat.parse(jsonObject.optString("updated_at"));
                    todo.setUpdatedAt(updatedAt);
                    taskList.add(todo); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return taskList;
    }

    public void save(Task task) {
        // Create JSON object from task
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MM/dd/yyyy");
        
        jsonObject.put("id", task.getId());
        jsonObject.put("name", task.getName());
        jsonObject.put("description", task.getDescription());
        jsonObject.put("due_date", dateFormat.format(task.getDueDate()));
        jsonObject.put("priority_level", task.getPriorityLevel());
        jsonObject.put("status", task.getStatus());
        
        // Set created_at and updated_at
        Date now = new Date();
        jsonObject.put("created_at", dateFormat.format(now));
        jsonObject.put("updated_at", dateFormat.format(now));

        // Add to Redis list using the existing addToBackList method
        addToBackList("todos", jsonObject.toString());
    }

}
