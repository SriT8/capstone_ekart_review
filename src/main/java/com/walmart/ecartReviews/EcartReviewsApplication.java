package com.walmart.ecartReviews;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableDiscoveryClient
public class EcartReviewsApplication {
	private static final Logger logger = LoggerFactory.getLogger(EcartReviewsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EcartReviewsApplication.class, args);
		logger.info("dummy====");
	}
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate()
	{ return new RestTemplate();
	}
}
