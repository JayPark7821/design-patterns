# Design-patterns
## 객체 생성 관련 디자인 패턴

---
### 프로토타입 (Prototype) 패턴 5. 기존 인스턴스를 복제하여 새로운 인스턴스를 만드는 방법
#### 프로토타입 (Prototype) 패턴
* 복제 기능을 갖추고 있는 기존 인스턴스를 프로토타입으로 사용해 새 인스턴스를 만들 수 있다.


보통 기존의 객체를 응용해서 새로운 인스턴스를 만들때 유용하게 쓸 수 있다.  
특히나 기존에 인스턴스를 만들때 시간이 오래 걸리는 작업 (db에서 데이터르 읽어와서 인스턴스를 만든다거나, Http 요청으로 데이터를 받아와서 인스턴스를 만들때 )  
이런경우 인스턴스를만들때 마다 db와 네트웍을 타야하지만  
기존에 db와 네트웍을 호출해 만들어진 객체를 가지고 있는 데이터를 복제를해서 인스턴스를 만들고 원하는값만 일부 변경해서 사용한다면  
훨씬 효율적일 수 있다.  

![image](https://user-images.githubusercontent.com/60100532/191623744-85b85c95-5c52-4117-92bd-f1787267fcd4.png)

### 코드로 프로토타입 패턴을 알아보자.

```java

public class GithubRepository {

    private String user;

    private String name;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

```
```java

public class GithubIssue {

  private int id;

  private String title;

  private GithubRepository repository;

  public GithubIssue(GithubRepository repository) {
    this.repository = repository;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public GithubRepository getRepository() {
    return repository;
  }

  public String getUrl() {
    return String.format("https://github.com/%s/%s/issues/%d",
            repository.getUser(),
            repository.getName(),
            this.getId());
  }
}

 ```
```java

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
        GithubIssue clone = githubIssue.clone();
        // clone != githubIssue
        // clone.equals(githubIssue) true

        clone.setId(2);
        clone.setTitle("2주차 과제");
    }
}
```