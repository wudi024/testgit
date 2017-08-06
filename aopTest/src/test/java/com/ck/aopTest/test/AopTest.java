package com.ck.aopTest.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ck.aopTest.bean.quizarium.QuizariumPro;
import com.ck.aopTest.dao.MyAopDao;
import com.ck.aopTest.dao.quizarium.QuizariumProMapper;
import com.ck.aopTest.utils.RedisAPI;

import junit.framework.TestCase;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml","classpath:spring-mybatis.xml"})
public class AopTest extends TestCase
{
	@Autowired
	private MyAopDao myAopDao;
	
	@Autowired
	private QuizariumProMapper proMapper;

	@Test
	public void aopTest2() {
		myAopDao.getName("またなやはなはやらは");

		List<String> quizariumPros = RedisAPI.getMapValue("quizariumPros");
		JSONArray array = JSONArray.fromObject(quizariumPros);
		List<QuizariumPro> pros = new ArrayList<>();
		JSONObject json;
		try {
			for (int i = 0; i < array.size(); i++) {
				QuizariumPro quizariumPro = new QuizariumPro();
				json = JSONObject.fromObject(array.getString(i));
				quizariumPro.setCreatetime(json.getString("createtime"));
				quizariumPro.setOpenid(json.getString("openid"));
				quizariumPro.setProTele(json.getString("proTele"));
				quizariumPro.setProDetails(json.getString("proDetails"));
				quizariumPro.setProGrade(json.getInt("proGrade"));
				quizariumPro.setNote1(json.getString("note1"));
				pros.add(quizariumPro);
			}
			if (pros.size() > 0) {
				proMapper.deleteAll();
				int count = proMapper.saveQuizariumProBatch(pros);
				System.out.println("提交了【"+count+"】笔");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}

}
