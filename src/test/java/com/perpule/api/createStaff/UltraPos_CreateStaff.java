package com.perpule.api.createStaff;

import org.testng.annotations.Test;

import com.perpule.ultraPos.common.GetDetails;

public class UltraPos_CreateStaff {
	
	@Test
	public void staffCreation() throws Exception {
		GetDetails start =new GetDetails();
		start.createStaff();
	}

}
