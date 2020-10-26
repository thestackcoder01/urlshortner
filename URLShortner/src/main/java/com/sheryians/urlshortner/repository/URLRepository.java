package com.sheryians.urlshortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheryians.urlshortner.model.LongShortURL;

public interface URLRepository extends JpaRepository<LongShortURL, Integer>{

}
