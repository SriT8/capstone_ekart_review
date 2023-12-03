package com.walmart.ecartReviews.service;

import java.util.*;

import com.walmart.ecartReviews.model.*;
import com.walmart.ecartReviews.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import org.springframework.stereotype.Service;
import com.walmart.ecartReviews.repository.ReviewRepository;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;


    @Autowired
    private JavaMailSender javaMailSender;
    private final ProductRepository productRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    public List<ReviewSearch> getAllreviews() {
        return reviewRepository.findAll();
    }
    
    public Optional<ReviewSearch> findById(String id) {
        return reviewRepository.findById(id);
    }   
    
    public List<ReviewSearch> findByReviewSearchId(int reviewSearchId) {
        return reviewRepository.findByReviewSearchId(reviewSearchId);
    }  

    public List<ReviewSearch> getProductsByAverageRatings(double averageRatings) {
    	return reviewRepository.findProductsByAverageRatings(averageRatings);
    	
    }
   /* List<ReviewSearch> allReviews = getAllreviews();
        return allReviews.stream()	
                .filter(reviews -> {Ratings ratings = reviews.getRatings();
                    if (ratings != null) {
                        Double averageRatingsFromReview = ratings.getAverageRatings();
                        return averageRatingsFromReview != null && averageRatingsFromReview == averageRatings;
                    }
                    return false; // Filter out reviews with null ratings
                })
                .collect(Collectors.toList());
    
    	}
*/
    public List<ReviewSearch> getProductsWithHigherRatings(double averageRatings) {
        //double threshold = 4.0; // The minimum averageRatings value
        return reviewRepository.findByAverageRatingsGreaterThan(averageRatings);
    }
/*
	public ReviewSearch save(ReviewSearch review) {
		// TODO Auto-generated method stub
		return null;
	}
*/

    public ReviewSearch addCommentToReview(int reviewSearchId, Comment comment) {
        List<ReviewSearch> existingReview = reviewRepository.findByReviewSearchId(reviewSearchId);

        if (existingReview != null) {
            // Add the comment to the existing review
            ((ReviewSearch) existingReview).getRatings().getComments().add(comment);
            // Save the updated review to the database
            return reviewRepository.save(existingReview);
        } else {
            // Handle the case when no review is found with the given reviewSearchId
            // You can throw an exception or return an appropriate response.
            return null; // For simplicity, returning null here.
        }
    }

    public Object getCommentsForGivenRating(int productId, int averageRatings) {
        List<Product> product = productRepository.findById(productId);
        if(product==null || product.isEmpty())
        {
            System.out.println("No product found ");
            return "No product found ";
        }
        Ratings ratings =  !product.isEmpty()?product.get(0).getRatings():null;
        List<User> resultObj = new ArrayList<>();
    if(ratings!=null && ratings.getComments()!=null) {
        for (Comment comment : ratings.getComments()) {
            User user = comment.getUser();

            if (user.getRate() >= averageRatings) {
                resultObj.add(user);
            }
        }
    }
    else{
        return "No ratings found ";
    }

        return resultObj.size()>0?resultObj:"No ratings found";

    }

    public Object addComments(int productId, Comment comment, String mailId) {
//        List<Product> productListw = productRepository.findAll();
//        List<Product> productList2 = productRepository.findById(productId);
        List<Product> productList = productRepository.findById(productId);
        if(productList==null)
        {
            return "No product found ";

        }
        Product product = productList.get(0);

        Ratings ratings =  product.getRatings();
        if(ratings!=null)
        {
            if (ratings.getComments().isEmpty()) {
                ratings.setComments(Arrays.asList(comment));
            }
            else {
                ratings.getComments().add(comment);
            }
            productRepository.save(product);
        }

//        sendmail("rajesh.ramakrishnan16589@gmail.com");
        sendEmail(mailId,productList.get(0).getProductName());
return "Comments added ";
    }




    public void sendEmail(String mailId, String productName) {




        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("danicoolbug@gmail.com");
        message.setTo(mailId);
        message.setSubject("Comments added to the product :: "+ productName);
        message.setText("Thanks for sharing your feedback, your comment has been published to the product page successfully");
        javaMailSender.send(message);
    }




//    public void sendEmail1() {
//        jakarta.mail.internet.MimeMessage mail = javaMailSender.createMimeMessage();
//        try{
//            MimeMessageHelper helper = new MimeMessageHelper(mail,true);
//helper.setFrom("danicoolbug@gmail.com");
//            helper.setTo("rajesh.ramakrishnan16589@gmail.com");
//            helper.setSubject("Test Email");
//            helper.setText("This is a test email sent from Spring Boot.");
//
//            javaMailSender.send(helper.getMimeMessage());
//        }
//        catch (Exception e ){
//            System.out.println(e);
//
//        }
////        SimpleMailMessage message = new SimpleMailMessage();
////        message.setTo("rajesh.ramakrishnan16589@gmail.com");
////        message.setSubject("Test Email");
////        message.setText("This is a test email sent from Spring Boot.");
//
////        javaMailSender.send(message);
//    }
//    private void sendmail(String mail) {
//        String to = "rajesh.ramakrishnan16589@gmail.com";
//        String from = "danicoolbug@gmail.com";
//        String host = "smtp.gmail.com";
//
//
////        MimeMessage maill = javaMailSender.createMimeMessage();
//
//
//        //Get the session object
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
//        properties.setProperty("spring.mail.port","587");
//        properties.setProperty("spring.mail.properties.mail.smtp.auth","true");
//        properties.setProperty("spring.mail.properties.mail.smtp.starttls.enable","true");
//
//        Session session = Session.getDefaultInstance(properties);
//
//        //compose the message
//        try{
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
//            message.setSubject("Ping");
//            message.setText("Hello, this is example of sending email  ");
//
//            // Send message
//            Transport.send(message);
//            System.out.println("message sent successfully....");
//
//        }catch (MessagingException mex) {
//            System.out.println(""+mex);}
//    }
}
