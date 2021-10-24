package com.ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.dao.DriverDao;
import com.ta.dto.ResponseMessage;
import com.ta.entity.Driver;
import com.ta.entity.model.DriverModel;

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
	
	@GetMapping("drivers")
	public ResponseEntity<ResponseMessage<List<Driver>>> getDrivers() {
		ResponseMessage<List<Driver>> rm = new ResponseMessage<>();

		try {
			List<Driver> drivers = driverDao.getAllDrivers();
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
	
	@GetMapping("activedrivers")
	public ResponseEntity<ResponseMessage<List<Driver>>> getActiveDrivers() {
		ResponseMessage<List<Driver>> rm = new ResponseMessage<>();

		try {
			List<Driver> drivers = driverDao.getActiveDrivers();
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
}
