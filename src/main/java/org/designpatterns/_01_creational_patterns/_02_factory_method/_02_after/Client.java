package org.designpatterns._01_creational_patterns._02_factory_method._02_after;

public class Client {

    public static void main(String[] args) {
        Ship whiteship = new WhiteShipFactory().orderShip("Whiteship", "jay@mail.com");
        System.out.println(whiteship);

        Ship blackship = new BlackShipFactory().orderShip("Blackship", "jay@mail.com");
        System.out.println(blackship);
    }

}
