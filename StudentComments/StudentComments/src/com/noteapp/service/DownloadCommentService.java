package com.noteapp.service;

import java.util.List;

import com.noteapp.model.Comment;

public interface DownloadCommentService {
	List<Comment> getCommentList();
	void setCommentList(List<Comment> noteList);
	void deleteCommentList(Comment note);
	void createNewComment(Comment note);
}
