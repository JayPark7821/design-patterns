package org.designpatterns._02_structural_patterns._09_decorator._02_after;

public class SpamFilteringCommentDecorator extends CommentDecorator{
	public SpamFilteringCommentDecorator(CommentService commentService) {
		super(commentService);
	}

	@Override
	public void addComment(String comment) {
		if (isNoSpam(comment)) {
			super.addComment(comment);
		}

	}

	private boolean isNoSpam(String comment) {
		return !comment.contains("http");
	}
}
