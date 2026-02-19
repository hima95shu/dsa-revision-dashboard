package com.himanshu.dsatracker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himanshu.dsatracker.entity.Question;
import com.himanshu.dsatracker.entity.Status;
import com.himanshu.dsatracker.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*") // Allows your HTML file to communicate with this API without CORS errors
public class QuestionController {

    private final QuestionService questionService;

    // Constructor Injection
    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

  // GET: Fetch all questions for the dashboard
    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // GET: Fetch the stats (Total, Solved, Attempted)
    @GetMapping("/stats")
    public Map<String, Long> getDashboardStats() {
        return questionService.getDashboardStats();
    }

    // POST: Add a new question
    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.saveQuestion(question);
        return ResponseEntity.ok(savedQuestion);
    }

    // PUT: Update an existing question completely
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        // Ensure the ID in the path matches the entity
        question.setId(id);
        Question updatedQuestion = questionService.saveQuestion(question);
        return ResponseEntity.ok(updatedQuestion);
    }

    // PATCH: Update just the status of a question
    @PatchMapping("/{id}/status")
    public ResponseEntity<Question> updateStatus(@PathVariable Long id, @RequestParam Status status) {
        Question updatedQuestion = questionService.updateStatus(id, status);
        return ResponseEntity.ok(updatedQuestion);
    }

    // DELETE: Remove a question
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

}
