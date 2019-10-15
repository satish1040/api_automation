package com.perpule.api.shopConfigCheck;

import org.testng.annotations.Test;
import com.perpule.ultraPos.common.GetDetails;
public class StartShopConfigCheck {

	@Test
	public void verifyShopConfig() throws Exception {
		GetDetails start =new GetDetails();
		start.checkShopConfig();
	}
	
}
