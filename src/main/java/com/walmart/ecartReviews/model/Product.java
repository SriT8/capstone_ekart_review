package com.walmart.ecartReviews.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection ="product_collection")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@MongoId(FieldType.OBJECT_ID)
	private String _id;
	@Field("id")
	private int id;
	private String title;
	private String productName;
	private Ratings ratings;
	

}
