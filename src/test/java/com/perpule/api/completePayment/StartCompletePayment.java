package com.perpule.api.completePayment;

import org.testng.annotations.Test;
import com.perpule.ultraPos.common.GetDetails;

public class StartCompletePayment {
	
	@Test
	public void verifyAppUpdate() throws Exception {
		GetDetails start =new GetDetails();
		start.checkCompletePayment();
	}

}
