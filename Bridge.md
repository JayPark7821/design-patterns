# Design-patterns
## 구조적인 패턴(Structural Patterns)

---
### 브릿지 (Bridge) 패턴
* 추상적인 것과 구체적인 것을 분리하여 연결하는 패턴
* 하나의 계층 구조일 때 보다 각기 나누었을 때 독립적인 게층 구조로 발전 시킬 수 있다.

> 어댑터 패턴과 마찬가지로 어떠한 인스턴스를 생성하는 것이 아닌 구조적인 디자인 패턴이다.
> 어댑터 패턴은 서로 상이한 인터페이스를 연결했지만.
> 브릿지 패턴은 추상적인 것과 구체적인 것을 연결한다.
>
![image](https://user-images.githubusercontent.com/60100532/192248590-8e85c666-6cf2-4245-95e8-7b8e8052ecb4.png)


### 코드로 브릿지 패턴을 알아보자.

#### client code 
```java
public class App {

    public static void main(String[] args) {
        Champion kda아리 = new KDA아리();
        kda아리.skillQ();
        kda아리.skillR();
    }
}
```
#### Champion 
```java
public interface Champion {

    void move();

    void skillQ();

    void skillW();

    void skillE();

    void skillR();

}

```

#### KDA아리
```java
public class KDA아리 implements Champion {

    @Override
    public void move() {
        System.out.println("KDA 아리 move");
    }

    @Override
    public void skillQ() {
        System.out.println("KDA 아리 Q");
    }

    @Override
    public void skillW() {
        System.out.println("KDA 아리 W");
    }

    @Override
    public void skillE() {
        System.out.println("KDA 아리 E");
    }

    @Override
    public void skillR() {
        System.out.println("KDA 아리 R");
    }

}

```
#### PoolParty아리
```java

public class PoolParty아리 implements Champion {

    @Override
    public void move() {
        System.out.println("PoolParty move");
    }

    @Override
    public void skillQ() {
        System.out.println("PoolParty Q");
    }

    @Override
    public void skillW() {
        System.out.println("PoolParty W");
    }

    @Override
    public void skillE() {
        System.out.println("PoolParty E");
    }

    @Override
    public void skillR() {
        System.out.println("PoolParty R");
    }

}

```

> 지금 현재 코드를 보면 스킨 또는 챔피언이 추가될 때마다 move(),skillQ(),skillW()....등 중볻코드가 발생한다.
> 이제 위 코드를 브릿지 패턴을 사용하여 변경해보자.
>


### 브릿지 패턴을 적용.

--- 
### 챔피언 관련 코드 
#### DefaultChampion
```java

public class DefaultChampion implements Champion {

	private Skin skin;
	private String name;

	public DefaultChampion(Skin skin, String name) {
		this.skin = skin;
		this.name = name;
	}

	@Override
	public void move() {
		System.out.printf("%s %s move\n", skin.getName(), this.name);
	}

	@Override
	public void skillQ() {
		System.out.printf("%s %s Q\n", skin.getName(), this.name);
	}

	@Override
	public void skillW() {
		System.out.printf("%s %s W\n", skin.getName(), this.name);
	}

	@Override
	public void skillE() {
		System.out.printf("%s %s E\n", skin.getName(), this.name);
	}

	@Override
	public void skillR() {
		System.out.printf("%s %s R\n", skin.getName(), this.name);
	}
}
```
#### 아리 
```java
public class 아리 extends DefaultChampion {

	public 아리(Skin skin) {
		super(skin, "아리");
	}
}

```
#### 아칼리
```java

public class 아칼리 extends DefaultChampion {

	public 아칼리(Skin skin) {
		super(skin, "아칼리");
	}
}

```
---
### 스킨 관련 코드
#### Skin
```java

public interface Skin {
	String getName();
}

```

#### KDA
````java

public class KDA implements Skin{
	@Override
	public String getName() {
		return "KDA";
	}
}

````

#### PoolParty
```java

public class PoolParty implements Skin{
	@Override
	public String getName() {
		return "PoolParty";
	}
}
```
---

### client code
```java
public class App {
	public static void main(String[] args) {
		Champion kda아리 = new 아리(new KDA());
		kda아리.skillQ();
		kda아리.skillR();

		Champion poolParty = new 아리(new PoolParty());
		kda아리.skillQ();
		kda아리.skillR();
	}
}
```



![image](https://user-images.githubusercontent.com/60100532/192248590-8e85c666-6cf2-4245-95e8-7b8e8052ecb4.png)
위의 사진을 다시 한번 보면

* Interface Implementation -> Skin   
* Concrete Implementation -> 각각의 스킨 ( KDA, PoolParty)
* Abstraction -> DefaultChampion
* Refined Abstraction -> 각각의 챔피언 ( 아리, 아칼리)


___
___

<br/> 

<br/> 

### 브릿지 (Bridge) 패턴 장단점
* 장점
    * 추상적인 코드를 구체적인 코드 변경 없이도 독립적으로 확장할 수 있다.
    * 추상적인 코드와 구체적인 코드를 분리할 수 있다.
* 단점
    * 계층 구조가 늘어나 복잡도가 증가할 수 있다.



