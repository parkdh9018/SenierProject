package com.mongo.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.*;

import com.mongodb.MongoClient;


//MVC 증 Model을 담당
public class MongoDAO {
	
	 MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "project"));
	
	
	//final String COLLECTION = "Rnews";
	
	public MongoDAO() {}
	
	//데이터베이스에 삽입
	public void insert(News news) {
		mongoOps.insert(news);
		System.out.println("success : " + mongoOps);
	}
	//collection내의 모든 raw를 List에 저장
	public List<News> findAll() {
		List<News> news = mongoOps.findAll(News.class);		
		return news;
	}
	
	//where의 조건이 value에 해당하는 raw를 list에 저장
	public List<News> find(String where, String value){
		Query query = new Query();
		query.addCriteria(Criteria.where(where).is(value));
		
		List<News> list = mongoOps.find(query, News.class);
		return list;
	}
	
    //
    public void update(News news) {
    	mongoOps.save(news);
    }
 
    public void delete(News news) {
    	mongoOps.remove(news);
    }
 
    public void deleteAll() {
    	mongoOps.remove(new Query());
    }
}
