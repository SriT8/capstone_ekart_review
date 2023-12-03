package com.walmart.ecartReviews.controller;


import com.walmart.ecartReviews.model.Comment;
import com.walmart.ecartReviews.model.NewComment;
import com.walmart.ecartReviews.service.NewCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/approval")
public class NewCommentController {

    private static final Logger logger = LoggerFactory.getLogger(NewCommentController.class);
    private final NewCommentService newCommentService;

    @Autowired
    public NewCommentController(NewCommentService newCommentService) {
        this.newCommentService = newCommentService;
    }

    @GetMapping("/")
    public List<NewComment> getAllreview() {
        return newCommentService.getAllreview();
    }


    @PostMapping("/{productId}/comment")
    public Object addNewComment(@PathVariable int productId, @RequestBody NewComment newComment, @RequestHeader Map<String, String> headers) {
        logger.info("" + headers);
        if (headers.get("user-id-email") != null)
            return newCommentService.addComment(productId, newComment, headers.get("user-id-email"));
        else {
            logger.error("==== No user email id details found in the header===== ");

            return "No user details found";
        }
    }
    @DeleteMapping("/delete/{productId}/{userId}")
    public ResponseEntity<String> deleteComment(@PathVariable int productId, @PathVariable String userId) {
        boolean deleted = newCommentService.deleteComment(productId, userId);

        if (deleted) {
            return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Comment not found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/productId/{productId}")
    public List<NewComment> getByproductId(@PathVariable int productId) {
        return newCommentService.findByproductId(productId);
    }

}
