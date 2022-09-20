package org.designpatterns._01_creational_patterns._03_abstract_factory._02_after;

import org.designpatterns._01_creational_patterns._02_factory_method._02_after.Ship;
import org.designpatterns._01_creational_patterns._02_factory_method._02_after.ShipFactory;

public class ShipInventory {
	public static void main(String[] args) {
		ShipFactory shipFactory = new WhiteshipFactory(new WhitePartsProFactory());
		Ship ship = shipFactory.createShip();
		System.out.println("ship.getAnchor().getClass() = " + ship.getAnchor().getClass());
		System.out.println("ship.getWheel().getClass() = " + ship.getWheel().getClass());

		ShipFactory shipFactory1 = new WhiteshipFactory(new WhiteshipPartsFactory());
		Ship ship1 = shipFactory1.createShip();
		System.out.println("ship.getAnchor().getClass() = " + ship1.getAnchor().getClass());
		System.out.println("ship.getWheel().getClass() = " + ship1.getWheel().getClass());
	}
}
