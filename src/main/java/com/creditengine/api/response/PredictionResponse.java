package com.creditengine.api.response;

import java.util.List;

public class PredictionResponse {
PredictionList prediction;
String message;
String status;
public PredictionResponse(PredictionList prediction, String message, String status) {
	super();
	this.prediction = prediction;
	this.message = message;
	this.status = status;
}
public PredictionList getPrediction() {
	return prediction;
}
public void setPrediction(PredictionList prediction) {
	this.prediction = prediction;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}


}
