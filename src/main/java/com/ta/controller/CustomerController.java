package com.ta.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ta.dao.CustomerDao;
import com.ta.dto.ResponseMessage;
import com.ta.entity.Customer;
import com.ta.entity.model.CustomerModel;

@CrossOrigin(origins = "*") // this line
@RestController
@RequestMapping("api/customer/")
public class CustomerController {

	@Autowired
	private CustomerDao customerDao;

	@PostMapping("customers")
	public ResponseEntity<ResponseMessage<Customer>> saveUser(@RequestBody CustomerModel customerModel) {
		ResponseMessage<Customer> rm = new ResponseMessage<>();

		try {
			Customer customer = customerDao.saveCustomer(customerModel);
			if (customer != null) {
				rm.setMessage("Customer Information saved successfully");
				rm.setResults(customer);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Customer Record not saved");
				rm.setResults(customer);
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
	@PutMapping("customers/{id}")
	public ResponseEntity<ResponseMessage<Customer>> updateUser(@RequestBody CustomerModel customerModel,
			@PathVariable Long id) {
		ResponseMessage<Customer> rm = new ResponseMessage<>();

		try {
			System.out.println("CustomerId=" + id);
			Customer customer = customerDao.updateCustomer(customerModel, id);
			if (customer != null) {
				rm.setMessage("Customer Information saved successfully");
				rm.setResults(customer);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(customer);
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

	@GetMapping("customers/{limit}/{offset}")
	public ResponseEntity<ResponseMessage<List<Customer>>> getCustomers(@PathVariable int limit,@PathVariable int offset) {
		ResponseMessage<List<Customer>> rm = new ResponseMessage<>();

		try {
			List<Customer> customers = customerDao.getAllCustomers(limit,offset);
			if (customers != null) {
				rm.setMessage("Customers are available");
				rm.setResults(customers);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Customers are not available.");
				rm.setResults(customers);
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

	@GetMapping("customers/{id}")
	public ResponseEntity<ResponseMessage<Customer>> getCustomerDetail(@PathVariable Long id) {
		ResponseMessage<Customer> rm = new ResponseMessage<>();

		try {
			Customer customers = customerDao.getCustomerDetail(id).get(0);
			if (customers != null) {
				rm.setMessage("Customer details are available");
				rm.setResults(customers);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Customer details are not available.");
				rm.setResults(customers);
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

	@GetMapping("search/{value}")
	public ResponseEntity<ResponseMessage<List<Customer>>> searchCustomerDetail(
			@PathVariable String value) {
		ResponseMessage<List<Customer>> rm = new ResponseMessage<>();

		try {
			List<Customer> customers = customerDao.searchCustomerInfo(value);
			if (customers != null) {
				rm.setMessage("Customers are available");
				rm.setResults(customers);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Customers are not available.");
				rm.setResults(customers);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@GetMapping("all-customers")
	public ResponseEntity<ResponseMessage<Map<String, Object>>> getAllCustomersPage(
			@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size,
			@RequestParam(defaultValue = "id,desc") String[] sort) {

		ResponseMessage<Map<String, Object>> rm = new ResponseMessage<>();
		try {
			Map<String, Object> response = customerDao.getAllCustomersPage(name, page,size);
			if (response != null) {
				rm.setMessage("Customers are available");
				rm.setResults(response);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Customers are not available.");
				rm.setResults(response);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}


}
