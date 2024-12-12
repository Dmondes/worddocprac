package sg.edu.nus.iss.worddoc;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.worddoc.service.RedisService;

@SpringBootApplication
public class WorddocApplication implements CommandLineRunner {
	@Autowired
	private RedisService redisService;

	public static void main(String[] args) {
		SpringApplication.run(WorddocApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try (JsonReader reader = Json.createReader(new FileReader("/Users/desmond/Desktop/Visa/02_ssf/wkshps/wkshps/worddoc/src/main/resources/todos.json"))) {
			JsonArray todoArray = reader.readArray();
			List<String> todoList = new ArrayList<>();
	
			// Convert JsonArray items to List
			todoArray.forEach(item -> {
				todoList.add(item.toString());
			});

			// Store in Redis with key "todos"
			redisService.addAllToBackList("todos", todoList);
			
			// Verify data was loaded by retrieving and printing it
			List<String> retrievedTodos = redisService.getList("todos");
			System.out.println("Loaded todos into Redis: " + retrievedTodos);
			
		} catch (Exception e) {
			System.err.println("Error loading todo data: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
