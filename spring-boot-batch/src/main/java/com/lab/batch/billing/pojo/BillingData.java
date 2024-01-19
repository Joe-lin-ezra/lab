package com.lab.batch.billing.pojo;

public record BillingData(
	int dataYear,
	int dataMonth,
	int accountId,
	String phoneNumber,
	float dataUsage,
	int callDuration,
	int smsCount) 
{

}
