package com.perpule.api.customerdetails;

import org.testng.annotations.Test;
//import com.perpule.api.utils.CustomerDetailsDataprovider;
import com.perpule.ultraPos.common.GetDetails;

public class CustomerDetails {
	
	@Test//(dataProviderClass = CustomerDetailsDataprovider.class, dataProvider = "debug")
	public void getData() throws Exception {
		GetDetails start =new GetDetails();
		start.customerData();
	}

}
