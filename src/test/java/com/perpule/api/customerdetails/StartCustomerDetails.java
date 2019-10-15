package com.perpule.api.customerdetails;

import org.testng.annotations.Test;
import com.perpule.api.utils.CustomerDetailsDataprovider;
import com.perpule.ultraPos.common.GetDetails;

public class StartCustomerDetails {
	
	@Test(dataProviderClass = CustomerDetailsDataprovider.class, dataProvider = "debug")
	public void debugTest(String mobileNo) throws Exception {
		GetDetails start =new GetDetails();
		start.getData(mobileNo);
	}

}
