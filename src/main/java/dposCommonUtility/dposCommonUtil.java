package dposCommonUtility;

import com.perpule.ultraPos.common.GetDetails;

public class dposCommonUtil {

	public static void staffLogin() throws Exception {
		GetDetails start =new GetDetails();
		start.login();
	}
	
	public static void staffLogout() throws Exception {
		GetDetails start =new GetDetails();
		start.logout();
	}
	
	public static void cartCreate() throws Exception {
		GetDetails start =new GetDetails();
		start.cartCreate();
	}
	
	public static void addItem() throws Exception {
		GetDetails start =new GetDetails();
		start.addItem();
	}
	
	public static void multipleItems() throws Exception {
		GetDetails start =new GetDetails();
		start.addMultipleItems();
	}
	
	public static void changeQuantity() throws Exception {
		GetDetails start =new GetDetails();
		start.changeQuantity();
	}
	
	public static void holdCart() throws Exception {
		GetDetails start =new GetDetails();
		start.cartHold();
	}
	
	public static void recallCart() throws Exception {
		GetDetails start =new GetDetails();
		start.cartRecall();
	}
	
	public static void tokenValidation() throws Exception {
		GetDetails start =new GetDetails();
		start.verifyToken();
	}
	
	
	public static void voidLine() throws Exception {
		GetDetails start =new GetDetails();
		start.getVoid();
	}
	
	public static void clear() throws Exception {
		GetDetails start =new GetDetails();
		start.clearCart();
	}
	
	public static void checkout() throws Exception {
		GetDetails start =new GetDetails();
		start.checkout();
	}
	
	public static void paymentCash() throws Exception {
		GetDetails start =new GetDetails();
		start.payment();
	}
	
	
}
