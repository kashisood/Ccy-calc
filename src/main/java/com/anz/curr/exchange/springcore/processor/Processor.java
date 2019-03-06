package com.anz.curr.exchange.springcore.processor;

import com.anz.curr.exchange.springcore.Response.ResponseObject;

public interface Processor <T>{

	 ResponseObject process(T t1);
}
