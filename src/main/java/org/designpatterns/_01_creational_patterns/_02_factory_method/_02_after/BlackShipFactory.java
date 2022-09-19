package org.designpatterns._01_creational_patterns._02_factory_method._02_after;

public class BlackShipFactory extends DefaultShipFactory {

	@Override
	public Ship createShip() {
		return new Blackship();
	}
}
