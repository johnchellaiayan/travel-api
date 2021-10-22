package twilight;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.ta.controller.DriverController;
import com.ta.dao.DriverDao;
import com.ta.entity.Driver;

@WebMvcTest
public class DriverControllerTest {

	 @Autowired
	private MockMvc mvc;
	 
	 @Autowired
	 private DriverDao driverDao;

	/*
	 * @Before public void setup() { this.mvc = MockMvcBuilders.standaloneSetup(new
	 * DriverController()).build(); }
	 */
	
	@Test
	public void testGetDrivers() throws Exception {
		List<Driver> drivers = new ArrayList<>();
		Driver dd = new Driver();
		dd.setId(new Long(1));
		dd.setName("jpsjohn");
		drivers.add(dd);
		if(driverDao == null)
			System.out.println("------------");
		Mockito.when(driverDao.getDriverDetail(new Long(1))).thenReturn(drivers);
		MockHttpServletResponse response = mvc.perform(get("/api/driver/drivers")).andReturn().getResponse();
        
	

		fail("Not yet implemented");
	}

	@Test
	public void testSearchDriverDetail() {
		fail("Not yet implemented");
	}

}
