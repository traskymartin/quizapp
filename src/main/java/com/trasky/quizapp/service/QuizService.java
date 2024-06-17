package com.trasky.quizapp.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.trasky.quizapp.model.Question;
import com.trasky.quizapp.model.QuestionWapper;
import com.trasky.quizapp.model.Quiz;
import com.trasky.quizapp.model.Response;
import com.trasky.quizapp.repository.QuestionRepo;
import com.trasky.quizapp.repository.QuizRepo;

@Service
public class QuizService {
    @Autowired
    QuizRepo quizrepo;
    @Autowired
    QuestionRepo questionRepo;
    
    public ResponseEntity<String> createQuiz(String category, int noQ, String title) {
        List<Question> questions=questionRepo.findRandomQuestionByCategory(category,noQ);
        try {
            Quiz quiz=new Quiz();
            quiz.setTitle(title);
            quiz.setQuestion(questions);
    
            quizrepo.save(quiz);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("not succed,",HttpStatus.BAD_REQUEST)   ;
    }

    public ResponseEntity<List<QuestionWapper>> getQuizQuestion(int id) {
        
        Optional<Quiz>quiz=quizrepo.findById(id);
        List<Question> questionFromDB=quiz.get().getQuestion();
        List<QuestionWapper> questionWappersForUser=new ArrayList<>();  
        for (Question q : questionFromDB) {
            QuestionWapper qw=new QuestionWapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionWappersForUser.add(qw);
        }

        return new ResponseEntity<>(questionWappersForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz=quizrepo.findById(id).get();    
        List<Question> questions=quiz.getQuestion();
        int i=0;
        int right=0;
        for(Response response:responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
