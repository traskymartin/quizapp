package com.trasky.quizapp.model;

//import jakarta.persistence.Entity;
import lombok.Data;

//@Entity
@Data
public class QuestionWapper {
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    public QuestionWapper(Integer id, String questionTitle, String option1, String option2, String option3,
            String option4) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
    private String option3;
    private String option4;
}
