package com.lab.batch.listener;

import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class CustomJobExecutionListener implements JobExecutionListener {

	private static final ExitStatus WARNING_STATUS = new ExitStatus("WARNING",
			"File processing failures encountered");

	@Override
	public void beforeJob(JobExecution jobExecution) {}

	@Override
	public void afterJob(JobExecution jobExecution) {
		ExitStatus currentStatus = jobExecution.getExitStatus();
		if (currentStatus.equals(ExitStatus.FAILED)) {
			List<Throwable> exceptions = jobExecution.getFailureExceptions();
			if (!exceptions.isEmpty()) {
				// Log details of the first failure
				Throwable firstFailure = exceptions.get(0);
				// log.error("First file processing failure:", firstFailure);
			}
		}
		jobExecution.setExitStatus(currentStatus);
	}
}
