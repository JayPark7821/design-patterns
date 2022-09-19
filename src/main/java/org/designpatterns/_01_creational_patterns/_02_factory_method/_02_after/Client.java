package org.designpatterns._01_creational_patterns._02_factory_method._02_after;

public class Client {

    public static void main(String[] args) {

        Client client = new Client();
        client.orderShip(new WhiteShipFactory(), "whiteship", "jay@mail.com");
        client.orderShip(new BlackShipFactory(), "blackship", "jay@mail.com");

//        Ship whiteship = new WhiteShipFactory().orderShip("Whiteship", "jay@mail.com");
//        System.out.println(whiteship);
//
//        Ship blackship = new BlackShipFactory().orderShip("Blackship", "jay@mail.com");
//        System.out.println(blackship);
    }

    // interface ShipFactory로 선언해놓았다. 위에서 어떠한 팩토리를 호출하더라로 client 코드는 변경할 필요 X
    private void orderShip(ShipFactory shipFactory, String name, String email) {
        System.out.println(shipFactory.orderShip(name, email));

    }

}
