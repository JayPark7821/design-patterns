package org.designpatterns._03_behavioral_patterns._15_interpreter._02_after;

import java.util.Map;

public interface PostfixExpression {

    int interpret(Map<Character, Integer> context);

    static PostfixExpression plus(PostfixExpression left, PostfixExpression right) {
        return new PostfixExpression() {
            @Override
            public int interpret(Map<Character, Integer> context) {
                return left.interpret(context) + right.interpret(context);
            }
        };
    }

}
