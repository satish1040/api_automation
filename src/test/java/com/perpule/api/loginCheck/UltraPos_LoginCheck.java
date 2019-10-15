package com.perpule.api.loginCheck;

import org.testng.annotations.Test;

import com.perpule.ultraPos.common.GetDetails;

public class UltraPos_LoginCheck {
	
	@Test
	public void verifyLogin() throws Exception {
		GetDetails start =new GetDetails();
		start.checkLogin();
	}

}
