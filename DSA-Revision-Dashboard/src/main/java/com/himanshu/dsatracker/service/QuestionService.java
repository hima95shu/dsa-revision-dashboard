package com.himanshu.dsatracker.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.himanshu.dsatracker.entity.Question;
import com.himanshu.dsatracker.entity.Status;
import com.himanshu.dsatracker.repository.QuestionRepository;

@Service
public class QuestionService {

    public final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    //  1. Save a new Question (or update an existing one)
    public Question saveQuestion(Question question){
        if(question.getHints() != null){
            question.getHints().forEach(hint -> hint.setQuestion(question));
        }
        return questionRepository.save(question);
    }

    // 2. Get all questions for the dashboard
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }
    // 3. Delete a question
    public void deleteQuestion(Long id){
        questionRepository.deleteById(id);
    }

  // 4. Update just the status of a question
    public Question updateStatus(Long id, Status newStatus){
        Question question = questionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Question not Found with id: "+ id));

        // YOU WERE MISSING THESE TWO LINES:
        question.setStatus(newStatus); // Actually update the status
        return questionRepository.save(question); // Save it and return it
    }

    // 5. Calculate the statistics for your top dashboard cards
    public Map<String, Long> getDashboardStats() {
        Map<String, Long> stats = new HashMap<>();
        
        // Using the built-in and custom repository methods to count
        stats.put("total", questionRepository.count());
        stats.put("solved", questionRepository.countByStatus(Status.SOLVED));
        stats.put("attempted", questionRepository.countByStatus(Status.ATTEMPTED));
        
        return stats;
    }
    

}
