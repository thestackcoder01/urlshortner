package com.sheryians.urlshortner.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class LongShortURL {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String longurl;
	private String shorturl;
	private int days;
	private LocalDate expirydate;
	
	public LongShortURL() {
		super();
	}

	public LongShortURL(int id, String longurl, String shorturl, int days, LocalDate expirydate) {
		super();
		this.id = id;
		this.longurl = longurl;
		this.shorturl = shorturl;
		this.days = days;
		this.expirydate = expirydate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLongurl() {
		return longurl;
	}

	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}

	public String getShorturl() {
		return shorturl;
	}

	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public LocalDate getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(LocalDate expirydate) {
		this.expirydate = expirydate;
	}
	
	
}
