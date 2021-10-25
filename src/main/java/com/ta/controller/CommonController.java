package com.ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.dao.CommonDAO;
import com.ta.dto.ResponseMessage;
import com.ta.dto.StatisticsDto;
import com.ta.entity.Customer;

@CrossOrigin(origins = "*") // this line
@RestController
@RequestMapping("api/common/")
public class CommonController {

	@Autowired
	CommonDAO commonDAO;

	
	@GetMapping("statistics")
	public ResponseEntity<ResponseMessage<StatisticsDto>> getStatistics() {
		ResponseMessage<StatisticsDto> rm = new ResponseMessage<>();

		try {
			StatisticsDto statisticsDto = commonDAO.getStatistics();
			if (statisticsDto != null) {
				rm.setMessage("Statistics are available");
				rm.setResults(statisticsDto);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Statistics are not available.");
				rm.setResults(statisticsDto);
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

}
