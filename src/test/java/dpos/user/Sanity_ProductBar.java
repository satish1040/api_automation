package dpos.user;

import org.testng.annotations.Test;

import dposCommonUtility.dposCommonUtil;

public class Sanity_ProductBar extends dposCommonUtil{
	
	@Test
	public static void sanity() throws Exception {
		staffLogin();
		tokenValidation();
		cartCreate();
		addItem();
		changeQuantity();
		holdCart();
		recallCart();
		multipleItems();
		checkout();
		paymentCash();
		staffLogout();
			
	}
	
}
