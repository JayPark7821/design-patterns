package org.designpatterns._02_structural_patterns._09_decorator._02_after;

// 기존에 CommentService에서 하던일을 가져왔다.
public class DefaultCommentService implements CommentService{
	@Override
	public void addComment(String comment) {
		System.out.println(comment);
	}
}
