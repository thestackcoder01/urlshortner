package com.sheryians.urlshortner.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sheryians.urlshortner.model.LongShortURL;
import com.sheryians.urlshortner.repository.URLRepository;

@Service
public class URLService {

	@Autowired
	URLRepository repo;
	
	List<LongShortURL> userlist = new ArrayList<>();
	
	Map<String,Integer> usermap = new HashMap<>();
	Map<String,String> urlmap = new HashMap<>();
	
	public void makeShortURL (LongShortURL urlModel) {
		userlist.add(urlModel);
		String shorturl = getShortURL();
		int days = urlModel.getDays();
		LocalDate currentdate = LocalDate.now();
		LocalDate expirydate = currentdate.plusDays(days);
		urlModel.setExpirydate(expirydate);
		urlModel.setShorturl( "http://localhost:8081/" +shorturl);
		repo.save(urlModel);
		usermap.put( urlModel.getLongurl(),urlModel.getId());
		urlmap.put(shorturl, urlModel.getLongurl());
	}
	
	public String getOriginalURL(String shorturl) {
		String longurl = urlmap.get(shorturl);
		return longurl;
	}
	public String getShortURL() {
		String shorturl = "";
		String allChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for (int i = 0; i < 5; i++)
			shorturl += allChar.charAt((int) Math.floor(Math.random() * allChar.length()));

		return shorturl;
	}
	
   public void checkValidity(String name ) { 
	   String longurl = urlmap.get(name);
	   int id = usermap.get(longurl);
	   Optional<LongShortURL> url = repo.findById(id);
	   LocalDate expirydate = url.get().getExpirydate();
	   LocalDate currentdate= LocalDate.now();

	   if(expirydate.equals(currentdate)) {
		   repo.deleteById(id);
	   }
	 
   }
}
