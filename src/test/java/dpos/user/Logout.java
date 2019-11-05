package dpos.user;

import org.testng.annotations.Test;

import com.perpule.ultraPos.common.GetDetails;

public class Logout {
	
	@Test
	public void staffLogout() throws Exception {
		GetDetails start =new GetDetails();
		start.logout();
	}

}
