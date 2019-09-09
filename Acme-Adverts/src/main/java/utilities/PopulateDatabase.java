package utilities;

import utilities.internal.DatabasePopulator;

public class PopulateDatabase {

	public static void main(final String[] args) {
		DatabasePopulator.run("PopulateDatabase 1.18.2", "classpath:PopulateDatabase.xml");
	}

}