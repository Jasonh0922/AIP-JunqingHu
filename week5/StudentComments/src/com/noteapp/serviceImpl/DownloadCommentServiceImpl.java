package com.noteapp.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import com.noteapp.model.Comment;
import com.noteapp.service.DownloadCommentService;

public class DownloadCommentServiceImpl implements DownloadCommentService{
	
	private List<Comment> commentList = new ArrayList<Comment>();
	
	/*
	 * initialize the data into comment objects
	 */
	public DownloadCommentServiceImpl(){
	
		Comment williamComment = new Comment();
		williamComment.setCreateBy("william");
		williamComment.setClassName("IT");
		williamComment.setScore("HIGH");
		
		Comment peterComment = new Comment();
		peterComment.setCreateBy("peter");
		peterComment.setClassName("IT");
		peterComment.setScore("HIGH");
		
		Comment yuviaComment = new Comment();
		yuviaComment.setCreateBy("yuvia");
		yuviaComment.setClassName("IT");
		yuviaComment.setScore("HIGH");
		
		Comment tomComment = new Comment();
		tomComment.setCreateBy("tom");
		tomComment.setClassName("IT");
		tomComment.setScore("HIGH");
		
		commentList.add(williamComment);
		commentList.add(peterComment);
		commentList.add(yuviaComment);
		commentList.add(tomComment);
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	public void deleteCommentList(Comment comment){
		this.commentList.remove(comment);

	}

	public void createNewComment(Comment comment){
		this.commentList.add(comment);
	}
}
