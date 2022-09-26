package org.designpatterns._02_structural_patterns._07_bridge._02_after;

import org.designpatterns._02_structural_patterns._07_bridge._01_before.Champion;
import org.designpatterns._02_structural_patterns._07_bridge._01_before.KDA아리;

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
