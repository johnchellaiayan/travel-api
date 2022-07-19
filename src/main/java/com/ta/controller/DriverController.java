package com.ta.controller;

import com.ta.dao.DriverDao;
import com.ta.dto.ResponseMessage;
import com.ta.entity.Driver;
import com.ta.entity.model.DriverModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/driver/")
public class DriverController {

	@Autowired
	private DriverDao driverDao;

	@PostMapping("drivers")
	public ResponseEntity<ResponseMessage<Driver>> saveUser(@RequestBody DriverModel driverModel) {
		ResponseMessage<Driver> rm = new ResponseMessage<>();

		try {
			Driver driver = driverDao.saveDriver(driverModel);
			if (driver != null) {
				rm.setMessage("Driver Information saved successfully");
				rm.setResults(driver);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(driver);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@CrossOrigin
	@PutMapping("drivers/{id}")
	public ResponseEntity<ResponseMessage<Driver>> updateUser(@RequestBody DriverModel driverModel,
			@PathVariable Long id) {
		ResponseMessage<Driver> rm = new ResponseMessage<>();

		try {
			Driver driver = driverDao.updateDriver(driverModel,id);
			if (driver != null) {
				rm.setMessage("Driver Information saved successfully");
				rm.setResults(driver);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(driver);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@GetMapping("drivers/{limit}/{offset}")
	public ResponseEntity<ResponseMessage<List<Driver>>> getDrivers(@PathVariable int limit,@PathVariable int offset) {
		ResponseMessage<List<Driver>> rm = new ResponseMessage<>();

		try {
			List<Driver> drivers = driverDao.getAllDrivers(limit,offset);
			if (drivers != null) {
				rm.setMessage("Drivers are available");
				rm.setResults(drivers);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Drivers are not available.");
				rm.setResults(drivers);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	@GetMapping("drivers/{id}")
	public ResponseEntity<ResponseMessage<Driver>> getDriverDetail(@PathVariable Long id) {
		ResponseMessage<Driver> rm = new ResponseMessage<>();

		try {
			Driver driver = driverDao.getDriverDetail(id).get(0);
			if (driver != null) {
				rm.setMessage("Driver details are available");
				rm.setResults(driver);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Driver details are not available.");
				rm.setResults(driver);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@GetMapping("search/{field}/{value}")
	public ResponseEntity<ResponseMessage<List<Driver>>> searchDriverDetail(@PathVariable String field, 
			@PathVariable String value) {
		ResponseMessage<List<Driver>> rm = new ResponseMessage<>();

		try {
			List<Driver> drivers = driverDao.searchDriverInfo(field, value);
			if (drivers != null) {
				rm.setMessage("Drivers are available");
				rm.setResults(drivers);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Drivers are not available.");
				rm.setResults(drivers);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@GetMapping("search/activedrivers/{value}")
	public ResponseEntity<ResponseMessage<List<Driver>>> getActiveDrivers(@PathVariable String value) {
		ResponseMessage<List<Driver>> rm = new ResponseMessage<>();

		try {
			List<Driver> drivers = driverDao.getActiveDrivers(value);
			if (drivers != null) {
				rm.setMessage("Active Drivers are available");
				rm.setResults(drivers);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Active Drivers are not available.");
				rm.setResults(drivers);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@GetMapping("getLiscenseExpiredDrivers")
	public ResponseEntity<ResponseMessage<List<Driver>>> getLiscenseExpiredDrivers() {
		ResponseMessage<List<Driver>> rm = new ResponseMessage<>();

		try {
			List<Driver> drivers = driverDao.getLiscenseExpiredDrivers();
			if (drivers != null) {
				rm.setMessage("Drivers are available");
				rm.setResults(drivers);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Drivers are not available.");
				rm.setResults(drivers);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@GetMapping("all-drivers")
	public ResponseEntity<ResponseMessage<Map<String, Object>>> getAllDriversPage(
			@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size,
			@RequestParam(defaultValue = "id,desc") String[] sort) {

		ResponseMessage<Map<String, Object>> rm = new ResponseMessage<>();
		try {
			Map<String, Object> response = driverDao.getAllDriversPage(name, page,size);
			if (response != null) {
				rm.setMessage("Drivers are available");
				rm.setResults(response);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Drivers are not available.");
				rm.setResults(response);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

}
