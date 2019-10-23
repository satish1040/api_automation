package com.perpule.api.validateOTP;

import org.testng.annotations.Test;

import com.perpule.ultraPos.common.GetDetails;

public class api_validateOTP {
	
	@Test
	public void startValidateOTP() throws Exception {
		GetDetails start =new GetDetails();
		start.validateOTP();
	}

}
