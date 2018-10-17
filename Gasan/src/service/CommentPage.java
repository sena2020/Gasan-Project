package service;

import java.util.List;

import model.Comment;

public class CommentPage {

	private int startPage;
	private int endPage;
	private int currentPage;
	private int totalPages;
	private List<Comment> commentList;

	public CommentPage(int currentPage, int totalComments, int commentPerPage, int pageBlock, List<Comment> commentList) {
		this.currentPage = currentPage;
		this.commentList = commentList;

		if (totalComments == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = totalComments / commentPerPage;
			if ((totalComments % commentPerPage) > 0) {
				totalPages++;
			}

			startPage = currentPage - pageBlock / 2;
			if (startPage < 1) {
				startPage = 1;
			} else if (startPage > totalPages - pageBlock + 1) {
				startPage = totalPages - pageBlock + 1;
			}

			endPage = startPage + pageBlock - 1;
			if (endPage > totalPages) {
				endPage = totalPages;
			}
		}
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

}
