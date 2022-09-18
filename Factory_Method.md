# Design-patterns
## 객체 생성 관련 디자인 패턴

---
### 팩토리 메소드(Factory method) 패턴 2. 구체적으로 어떤 인스턴스를 만들지는 서브 클래스가 정한다.
####  팩토리 메소드 (Factory method) 패턴
* 다양한 구현체 (Product)가 있고, 그중에서 특정한 구현체를 만들 수 있는 다양한 팩토리(Creator)를 제공할 수 있다.
  ![image](https://user-images.githubusercontent.com/60100532/190903173-7ec46333-296f-41b2-ae55-c505463cb130.png)

다음 샘플 코드를 팩토리 메소드패턴을 활용하여 OCP(Open-Closed Principle) 원칙을 지키도록 바꿔보자.
### Client
```java
public class Client {

  public static void main(String[] args) {
    Ship whiteship = ShipFactory.orderShip("Whiteship", "keesun@mail.com");
    System.out.println(whiteship);

    Ship blackship = ShipFactory.orderShip("Blackship", "keesun@mail.com");
    System.out.println(blackship);
  }

}
```
### Ship
```java
public class Ship {

    private String name;

    private String color;

    private String logo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}

```

### ShipFactory
```java

public class ShipFactory {

    public static Ship orderShip(String name, String email) {
        // validate
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("배 이름을 지어주세요.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("연락처를 남겨주세요.");
        }

        prepareFor(name);

        Ship ship = new Ship();
        ship.setName(name);

        // Customizing for specific name
        if (name.equalsIgnoreCase("whiteship")) {
            ship.setLogo("\uD83D\uDEE5️");
        } else if (name.equalsIgnoreCase("blackship")) {
            ship.setLogo("⚓");
        }

        // coloring
        if (name.equalsIgnoreCase("whiteship")) {
            ship.setColor("whiteship");
        } else if (name.equalsIgnoreCase("blackship")) {
            ship.setColor("black");
        }

        // notify
        sendEmailTo(email, ship);

        return ship;
    }

    private static void prepareFor(String name) {
        System.out.println(name + " 만들 준비 중");
    }

    private static void sendEmailTo(String email, Ship ship) {
        System.out.println(ship.getName() + " 다 만들었습니다.");
    }

}
```

<br />  

위 코드에서 만약 whiteship, blackship 말고 다른 ship을 만들어야 한다면....
```java
   // Customizing for specific name
        if (name.equalsIgnoreCase("whiteship")) {
            ship.setLogo("\uD83D\uDEE5️");
        } else if (name.equalsIgnoreCase("blackship")) {
            ship.setLogo("⚓");
        }

        // coloring
        if (name.equalsIgnoreCase("whiteship")) {
            ship.setColor("whiteship");
        } else if (name.equalsIgnoreCase("blackship")) {
            ship.setColor("black");
        }
```
이곳에 if문으로 로직을 추가해야 할 것이다.   
즉 요구사항이 변경될 때마다 내부 코드가 계속 수정돼야 한다.   
즉 변경에 닫혀있지 않은 것이고 OCP 원칙에 위배 된다.   

___

다음은 팩토리 메소드 패턴으로 변경한 코드를 살펴보자.

### Client
```java

public class Client {

    public static void main(String[] args) {
        Ship whiteship = new WhiteShipFactory().orderShip("Whiteship", "jay@mail.com");
        System.out.println(whiteship);

        Ship blackship = new BlackShipFactory().orderShip("Blackship", "jay@mail.com");
        System.out.println(blackship);
    }

}

```
### Ship
```java

public class Ship {

    private String name;

    private String color;

    private String logo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
```

### ShipFactory
```java

public interface ShipFactory {

	default Ship orderShip(String name, String email){
		validate(name, email);
		prepareFor(name);
		Ship ship = createShip();
		sendEmailTo(email, ship);
		return ship;
	}

	private void sendEmailTo(String email, Ship ship) {
		System.out.println(ship.getName() + " 다 만들었습니다.");
	}
	Ship createShip();

	private void validate(String name, String email) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("배 이름을 지어주세요.");
		}
		if (email == null || email.isBlank()) {
			throw new IllegalArgumentException("연락처를 남겨주세요.");
		}
	}

	private void prepareFor(String name) {
		System.out.println(name + " 만들 준비 중");
	}


}
```
### Whiteship
```java
public class Whiteship extends Ship {

	public Whiteship() {
		setName("whiteship");
		setLogo("\uD83D\uDEE5️");
		setColor("white");
	}
}
```
### WhiteshipFactory
```java
public class WhiteShipFactory implements ShipFactory{

    @Override
    public Ship createShip() {
        return new Whiteship();
    }
}

```

### Blackship
```java
public class Blackship extends Ship {

	public Blackship() {
		setName("blackship");
		setColor("black");
		setLogo("⚓");
	}


}

```
### BlackshipFactory
```java

public class BlackShipFactory implements ShipFactory {

	@Override
	public Ship createShip() {
		return new Blackship();
	}
}

```

ShipFactory와 Ship을 각가 구현, 상속받아 기존의 코드의 변경 없이    
Blackship과 whiteship을 추가했다.   

<br />  

![image](https://user-images.githubusercontent.com/60100532/190911416-16ecb45a-d0bf-48c9-9c10-61cd7755c45d.png)

<br />  

하지만 클라이언트 코드가 변경된 것이 아니냐??  
라는 생각이 들 수도 있다.  
  

이 부분은 다음에 알아보자.  
