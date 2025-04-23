package com.tsg.test.payloads.response;

public class SignUpDataResponse {
    private String data;

	public SignUpDataResponse(String _data) {
	    this.data = _data;
	  }

	public String getData() {
		return data;
	}

	public void setData(String _data) {
		this.data = _data;
	}
}
