package com.ck.aopTest.dao.quizarium;

import java.util.List;

import com.ck.aopTest.bean.quizarium.QuizariumPro;

public interface QuizariumProMapper {

    QuizariumPro selectByPrimaryKey(Integer proNo);

    int saveQuizariumProBatch(List<QuizariumPro> pros);
    
    int deleteAll();
}