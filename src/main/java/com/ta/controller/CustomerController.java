package com.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.dao.CustomerDao;
import com.ta.dto.ErrorLogDto;
import com.ta.dto.ResponseMessage;
import com.ta.entity.Customer;
import com.ta.entity.model.CustomerModel;
import com.ta.enumeration.LogOperation;
import com.ta.util.LogWrapper;

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
			LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.DELETE).errorMessage(e.getMessage())
					.exception(e).build());
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

}
