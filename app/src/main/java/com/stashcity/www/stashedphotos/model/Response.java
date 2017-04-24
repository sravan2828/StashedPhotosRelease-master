package com.stashcity.www.stashedphotos.model;

/**
 * Created by sravan on 11/10/16.
 */

public class Response {
    String Error;

    public String getStatus() {
        return Status;
    }

    @Override
    public String toString() {
        return "Response{" +
                "Error='" + Error + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    String Status;
}
