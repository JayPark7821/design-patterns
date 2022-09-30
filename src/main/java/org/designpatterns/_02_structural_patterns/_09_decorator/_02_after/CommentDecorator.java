package org.designpatterns._02_structural_patterns._09_decorator._02_after;

// 데코레이터 무언가를 감싸서 감싸고있는 컴포넌트를 그대로 호출
public class CommentDecorator implements CommentService{

	private CommentService commentService;

	public CommentDecorator(CommentService commentService) {
		this.commentService = commentService;
	}

	@Override
	public void addComment(String comment) {
		commentService.addComment(comment);

	}
}
