package com.perpule.api.staffDetails;

import org.testng.annotations.Test;

import com.perpule.ultraPos.common.GetDetails;

public class UltraPos_StaffDeatils {
	
	@Test
	public void staffDetails() throws Exception {
		GetDetails start =new GetDetails();
		start.staff();
	}

}
