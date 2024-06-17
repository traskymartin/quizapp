package com.trasky.quizapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.trasky.quizapp.model.Question;
import com.trasky.quizapp.model.QuestionWapper;
import com.trasky.quizapp.model.Response;
import com.trasky.quizapp.service.QuizService;

@RestController
@RequestMapping("quiz")

public class QuizController {
    @Autowired  
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam(name="category") String category,@RequestParam(name = "noQ") int noQ,
                                               @RequestParam(name = "title") String title){
        return quizService.createQuiz(category,noQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWapper>> getQuizQuestion(@PathVariable("id") int id){
       return quizService.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable("id") Integer id,@RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);

    }
}
