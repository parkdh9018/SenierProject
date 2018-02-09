package com.mongo.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.*;

import com.mongodb.MongoClient;

public class MongoDAO {
	
	 MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "project"));
	
	
	//final String COLLECTION = "Rnews";
	
	public MongoDAO() {}
	
	public void insert(News news) {
		mongoOps.insert(news);
		System.out.println("success : " + mongoOps);
	}
	public List<News> findAll() {
		List<News> news = mongoOps.findAll(News.class);		
		return news;
	}
	
	public List<News> find(String where, String value){
		Query query = new Query();
		query.addCriteria(Criteria.where(where).is(value));
		
		List<News> list = mongoOps.find(query, News.class);
		return list;
	}
	
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
