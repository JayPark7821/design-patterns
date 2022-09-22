package org.designpatterns._01_creational_patterns._05_prototype._01_before;

public class App {

    public static void main(String[] args) {
        GithubRepository repository = new GithubRepository();
        repository.setUser("whiteship");
        repository.setName("live-study");

        GithubIssue githubIssue = new GithubIssue(repository);
        githubIssue.setId(1);
        githubIssue.setTitle("1주차 과제: JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가.");

        String url = githubIssue.getUrl();
        System.out.println(url);

        /**
         *     GithubRepository repository = new GithubRepository();
         *         repository.setUser("whiteship");
         *         repository.setName("live-study");
         *
         *         GithubIssue githubIssue = new GithubIssue(repository);
         *         githubIssue.setId(1);
         *         githubIssue.setTitle("1주차 과제: JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가.");
         */

        // 인스턴스를 새로 만드는 것이 아닌 기존에 있는 githubIssue를 활용해 복사한다음 원하는 데이터만 변경
//        GithubIssue clone = githubIssue.clone();
        // clone != githubIssue
        // clone.equals(githubIssue) true

//        clone.setId(2);
//        clone.setTitle("2주차 과제");

    }

}
