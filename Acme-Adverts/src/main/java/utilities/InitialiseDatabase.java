package utilities;

import utilities.internal.DatabasePopulator;

public class InitialiseDatabase {

	public static void main(final String[] args) {
		DatabasePopulator.run("InitialiseDatabase 1.18.2", "classpath:InitialiseDatabase.xml");
	}

}