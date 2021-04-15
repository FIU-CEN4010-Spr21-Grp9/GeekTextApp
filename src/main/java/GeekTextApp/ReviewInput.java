package GeekTextApp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ItemListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.xml.stream.events.Comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ReviewInput {
	
	private int reviewID;
	private Review review;
	private float anon;
	private int bookID;
	private int userID;
	private int rating;
	private String title;
	private String description;
	private String rootURL;
	private String fullURL;
	private String text;
	private final RestTemplate restTemplate = new RestTemplate();
	private List<Review> reviews;
	private Book book;
	
	public ReviewInput(Integer reviewID) 
	{
		// internal values
		this.rootURL = GeekTextApp.ROOT_URL;
		GetReviewByBookId(reviewID);
		PutReviewByReviewId(reviewID);
	}
	
	// functions
	// main browsing return
	
	/*
	// list by book
	//http://localhost:8080/reviews/query/viaproc/bybook?bookID=1
	public void GetReviewByBookId(int bookID)
	{
		fullURL = rootURL + "/review/query/viaproc/bybook?bookID={id}";
		
		ResponseEntity<List<Review>> responseEntity = restTemplate.exchange(
				fullURL,
			    HttpMethod.GET,
			    null,
			    new ParameterizedTypeReference<List<Review>>() {}
				, bookID
			  );
		if(review != null)
		{
			review = null;
		}
		
		review = responseEntity.getBody().get(0);
	}
	*/
	
	public void GetReviewByBookId(Integer reviewID)
	{
		fullURL = rootURL + "/reviews/query/viaproc/byReviewId?reviewID={id}";
		
		ResponseEntity<List<Review>> responseEntity = restTemplate.exchange(
				fullURL,
			    HttpMethod.GET,
			    null,
			    new ParameterizedTypeReference<List<Review>>() {}
				, reviewID
			  );
		
		if (reviews != null)
		{
			reviews = null;
		}
		
		reviews = responseEntity.getBody();

	}
	
	public void PutReviewByReviewId(Integer reviewID)
	{
		fullURL = rootURL + "/review/query/viaproc/EnterbyReviewID?reviewID={id}";
		
		ResponseEntity<List<Review>> responseEntity = restTemplate.exchange(
				fullURL,
			    HttpMethod.PUT,
			    null,
			    new ParameterizedTypeReference<List<Review>>() {}
				, reviewID
			  );
		
		if (review != null)
		{
			review = null;
		}
		
		review = responseEntity.getBody().get(0);

	}
	
	
	// get/set
	
	public String getReviewTitle()
	{
		return review.getTitle();
	}
	
	public int getReviewCount()
	{
		return reviews.size();
	}
	
	public Review getReview(int i)
	{
		return reviews.get(i);
	}
	
	public Float getIsAnonymous(int i)
	{
		return reviews.get(i).getIsAnonymous();
	}
	
	public void setTitle() {
		this.review.setTitle(title);;
	}
	
	public String getTitle(int i)
	{
		return reviews.get(i).getTitle();
	}
	
	public void setDescription() {
		this.review.setDescription(description);
	}
	
	public String getDescription(int i)
	{
		return reviews.get(i).getDescription();
	}
	
	
	public Float getRating(int i)
	{
		return reviews.get(i).getRating();
	}	
	
}
