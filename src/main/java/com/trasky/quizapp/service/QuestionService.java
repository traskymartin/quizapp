package com.trasky.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.trasky.quizapp.model.Question;
import com.trasky.quizapp.repository.QuestionRepo;

@Service
public class QuestionService {
    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<List<Question>> getallQuestion() {
        try{
            return new ResponseEntity<>( questionRepo.findAll(),HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
        return new ResponseEntity<>(questionRepo.findByCategory(category),HttpStatus.OK);
        }catch (Exception e) {
            e.getStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> addQuestion(Question question) {
        try {
            
        questionRepo.save(question);
        return new ResponseEntity<>( "success",HttpStatus.CREATED);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return new ResponseEntity<>("not Succed",HttpStatus.NOT_IMPLEMENTED);
    }
    
}
