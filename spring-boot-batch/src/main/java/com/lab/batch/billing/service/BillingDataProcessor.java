package com.lab.batch.billing.service;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.lab.batch.billing.pojo.BillingData;
import com.lab.batch.billing.pojo.ReportingData;

public class BillingDataProcessor implements ItemProcessor<BillingData, ReportingData> {
	@Autowired
	private PricingService pricingService;

    @Value("${spring.cellular.spending.threshold:150}")
    private float spendingThreshold;

   @Override
   public ReportingData process(BillingData item) {
	   double billingTotal =
               item.dataUsage() * pricingService.getDataPricing() +
               item.callDuration() * pricingService.getCallPricing() +
               item.smsCount() * pricingService.getSmsPricing();
       if (billingTotal < spendingThreshold) {
           return null;
       }
       return new ReportingData(item, billingTotal);
   }
}
