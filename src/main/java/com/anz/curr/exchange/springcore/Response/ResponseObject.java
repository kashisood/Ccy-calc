package com.anz.curr.exchange.springcore.Response;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component("respObj")
public class ResponseObject {

	private boolean status = false;
	private String respObj = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(respObj, status);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseObject other = (ResponseObject) obj;
		return Objects.equals(respObj, other.respObj) && status == other.status;
	}

	/**
	 * @return the respObj
	 */
	public String getRespObj() {
		return respObj;
	}

	/**
	 * @param respObj the respObj to set
	 */
	public void setRespObj(String respObj) {
		this.respObj = respObj;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		if (isStatus()) {
			return getRespObj();
		} else {
			return "RateMappingNotFound";
		}

	}
}
