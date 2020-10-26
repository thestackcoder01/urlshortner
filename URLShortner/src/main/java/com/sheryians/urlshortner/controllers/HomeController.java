package com.sheryians.urlshortner.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sheryians.urlshortner.model.LongShortURL;
import com.sheryians.urlshortner.service.URLService;

@Controller
public class HomeController {
	
	@Autowired
	URLService urlService;

	@GetMapping("/")
    public String getHome() {
	    return  "index";
  }
	@PostMapping("/")
	public String getData(@ModelAttribute("url") LongShortURL url, Model model) throws IOException {
		  urlService.makeShortURL(url);
		  model.addAttribute("shorturl" , url.getShorturl());
		  return "index";
	}
	
	@GetMapping("{randomString}")
	public void getRedirect(HttpServletResponse response ,@PathVariable("randomString") String randomString) throws Throwable {
		    urlService.checkValidity(randomString);
			String originalURL = urlService.getOriginalURL(randomString);
			
			if(originalURL.contains("https://")) {
				response.sendRedirect(originalURL);
				
			}else if(originalURL.contains("www")) {
				response.sendRedirect("https://"+originalURL);
			}
			else {
				response.setStatus(404);
			}
}
}
