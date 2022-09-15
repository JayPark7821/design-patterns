# Design-patterns
## 객체 생성 관련 디자인 패턴

---
### 싱글톤 패턴 1. 싱글톤 패턴을 가장 단순히 구현하는 방법
####  싱글톤 (Singleton) 패턴
인스턴스를 오직 한개만 제공하는 클래스

  * 시스템 런타임, 환경 세팅에 대한 정보 등, 인스턴스가 여러개 일 때 문제가 생길 수 있는 경우가 있다. 인스턴스를 오직 한개만 만들어 제공하는 클래스가 필요하다.
  
|Singleton|
|----|
| - instance : Singleton|
| + getInstance():Singleton|

```java
public class Settings{
    
}
```

```java
public class App{
    public static void main(String[]args){
        Settings = new Settings();
        Settings1 = new Settings();
        System.out.println(settings != settings1);
        // ture 
    } 
}
```
싱글톤 패턴을 구현 시 new를 사용해서 인스턴스를 만들게 허용하면 싱글톤 패턴을 만족시킬 수 없다.

해당 클래스 밖에서 new를 사용해 생성자를 사용하지 하지 못하게 하려면 
private 생성자를 만들어 준다.

```java
public class Settings{
    private Settings(){}
}
```
```java
public class App{
    public static void main(String[]args){
        Settings = new Settings(); //-- 컴파일 에러
        Settings1 = new Settings();//-- 컴파일 에러
        System.out.println(settings != settings1);
      
    } 
}
```
이렇게 되면 밖에서 settings의 인스턴스를 만들 수 없기 때문에
Settings 클래스 안에서 인스턴스를 만들어서 글로벌하게 접근 가능한 방법을 제공해야 한다.
```java
public class Settings{
    private Settings(){}
    
    public static Settings getInstance(){
        return new Settings();
    }
}
```
```java
public class App{
    public static void main(String[]args){
        Settings = Settings.getInstance();
        Settings1 = Settings.getInstance(); 
        System.out.println(settings != settings1);
        // true
    } 
}
```
하지만 이경우에도 Settings의 getInstance() 내부에서 new를 사용해 호출될 때마다 Settings 인스턴스를 생성해 return하기 때문에 
싱글톤 패턴을 만족하지 못한다.

```java
public class Settings{
    
    private static Settings instance;
    
    private Settings(){}
    
    public static Settings getInstance(){
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
}
```
```java
public class App{
    public static void main(String[]args){
        Settings = Settings.getInstance();
        System.out.println(settings ==  Settings.getInstance());
        // true
    } 
}
```
위와같이 싱글톤 패턴을 구현할 수 있다.
하지만 위같은 방법은 치명적인 단점이 있다.
과연 멀티쓰레드 환경에서 위코드가 안전할까???