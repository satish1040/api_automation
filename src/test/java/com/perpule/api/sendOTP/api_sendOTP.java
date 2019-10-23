package com.perpule.api.sendOTP;

import org.testng.annotations.Test;

import com.perpule.ultraPos.common.GetDetails;

public class api_sendOTP {
	
	@Test
	public void startGetOTP() throws Exception {
		GetDetails start =new GetDetails();
		start.sendOTP();
	}

}
