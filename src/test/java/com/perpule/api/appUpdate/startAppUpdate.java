package com.perpule.api.appUpdate;

import org.testng.annotations.Test;
import com.perpule.ultraPos.common.GetDetails;

public class startAppUpdate {
	
	@Test
	public void verifyAppUpdate() throws Exception {
		GetDetails start =new GetDetails();
		start.checkAppUpdate();
	}

}
