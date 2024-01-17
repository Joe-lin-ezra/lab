package com.lab.batch.utility;

import org.springframework.batch.core.ExitStatus;

public class CustomExitStatuses {

    public static ExitStatus networkError(String message) {
	return new ExitStatus("ERROR", "Error: " + message);
    }

    public static ExitStatus warning(String message) {
	return new ExitStatus("WARNING", "Warning: " + message);
    }
}
