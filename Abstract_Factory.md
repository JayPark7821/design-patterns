# Design-patterns
## 객체 생성 관련 디자인 패턴

---
### 추상 팩토리(Abstract Factory) 패턴 3. 서로 관련있는 여러 객체를 만들어주는 인터페이스.
#### 추상 팩토리(Abstract Factory) 패턴
* 구체적으로 어떤 클래스의 인스턴스를(concrete product)를 사용하는지 감출 수 있다.

![image](https://user-images.githubusercontent.com/60100532/191276398-ec597b7b-a710-43f0-9747-feea311daa86.png)

 여러 관련있는 인스턴스를 만들어주는 팩토리를 추상화된 형태(interface, abstract)로 정의하는 패턴  
 구체적인 팩토리에서 구체적인 인스턴스를 만드는것은 팩토리메소드패턴과 굉장히 비슷하지만  
 초점이 (팩토리를 사용하는) 클라이언트 쪽에 있다. 
 
> ###  목적  
> 클라이언트(팩토리에서 인스턴스를 만들어서 쓰는) 코드를 인터페이스 기반으로 코딩을 할 수 있게끔 도와주는 패턴


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
	void sendEmailTo(String email, Ship ship);
}

```
### DefaultShipFactory
```java

public abstract class DefaultShipFactory implements ShipFactory{
    @Override
    public void sendEmailTo(String email, Ship ship) {
        System.out.println(ship.getName() + " 다 만들었습니다.");
    }
}

```
### WhiteshipFactory
```java

public class WhiteshipFactory extends DefaultShipFactory {

    @Override
    public Ship createShip() {
        Ship ship = new Whiteship();
        ship.setAnchor(new WhiteAnchor());
        ship.setWheel(new WhiteWheel());
        return ship;
    }
}
```
WhiteshipFactory 코드를 보면 직접 구체적인 클래스 타입을 생성해서 전달해주고 있다. (new WhiteAnchor, new WhiteWheel)
이떄 문제는 만약 미래에 WhiteWheel이 아닌 BlackWheel이 필요하다면..... 코드를 수정해야한다.  

이제 코드를 변경하지 않고 그때그때 필요한 클래스타입을 전달하는 방법을 알아보자.

WhiteshipFactory <- 클라이언트 코드에 해당함.

먼저 비슷한류의 제품들을 만들어주는 인터페이스를 정의한다.  
예제에선 Anchor들과 , Wheel들이 될 것이다. 

ShipPartsFactory라는 추상 팩토리를 정의하자.
```java
public interface ShipPartsFactory {
    Anchor createAnchor();
    Wheel createWheel();
}
```
```java
public interface Wheel {
}
```
```java
public interface Anchor {
}
```

이제 추상 팩토리의 구체적인 팩토리를 만들어 보자.

```java
public class WhiteshipPartsFactory implements ShipPartsFactory {

    @Override
    public Anchor createAnchor() {
        return new WhiteAnchor();
    }

    @Override
    public Wheel createWheel() {
        return new WhiteWheel();
    }
}
```
WhiteAnchor와 WhiteWheel은 각각 Anchor와 Wheel의 특징을 가지고 있을 테니   
Anchor를 Implements함으로서.
각각 Anchor와 Wheel Interface, 규약을 따른다고 명시해 준다. 
```java
public class WhiteAnchor implements Anchor {
}
```
```java
public class WhiteWheel implements Wheel {
}

```

이제 클라이언트 코드에서 팩토리를 만들었으니 사용해보자.

```java
public class WhiteshipFactory extends DefaultShipFactory {

    private ShipPartsFactory shipPartsFactory;

    public WhiteshipFactory(ShipPartsFactory shipPartsFactory) {
        this.shipPartsFactory = shipPartsFactory;
    }

    @Override
    public Ship createShip() {
        Ship ship = new Whiteship();
        ship.setAnchor(shipPartsFactory.createAnchor());
        ship.setWheel(shipPartsFactory.createWheel());
        return ship;
    }
}
```
WhiteshipFactory 생성자에 필요한 제품군(Anchor와 Wheel의 묶음)을 만들어주는 팩토리를 넣어주면 클라이언트 코드 변경 없이  
원하는 Ship을 생성할 수 있게 되었다.

WhiteAnchorPro, WhiteWheelPro를 추가해보자.
```java
public class WhiteAnchorPro implements Anchor {
}
```

```java
public class WhiteWheelPro implements Wheel{
}
```

```java
public class WhitePartsProFactory implements ShipPartsFactory {
	@Override
	public Anchor createAnchor() {
		return new WhiteAnchorPro();
	}

	@Override
	public Wheel createWheel() {
		return new WhiteWheelPro();
	}
}
```

결과 확인  
![image](https://user-images.githubusercontent.com/60100532/191292252-2bb10eb8-c559-4fd8-a39d-870848756fd1.png)

WhiteshipFactory의 내부 코드 변경 없이  
그때그때 필요한 Factory를 생성자에 전달해 원하는 값을 얻을 수 있게 되었다.