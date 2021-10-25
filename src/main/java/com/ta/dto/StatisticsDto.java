package com.ta.dto;

import java.math.BigInteger;

import lombok.Data;
@Data
public class StatisticsDto {
	
	private BigInteger totalCustomers;
	
	private BigInteger totalDrivers;
	
	private BigInteger totalUsers;
	
	private BigInteger totalCurrentBookings;

}
