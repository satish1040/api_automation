package com.perpule.api.utils;

import org.testng.annotations.DataProvider;

public class CustomerDetailsDataprovider {
	
	@DataProvider//(parallel = true)
	public static Object[][] debug() throws Exception {
		return new Object[][] {
				{"7996424956"}
			};
	}

}
