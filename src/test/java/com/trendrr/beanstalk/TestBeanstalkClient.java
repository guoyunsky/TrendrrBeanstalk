package com.trendrr.beanstalk;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * function test
 * 
 * @author guoyun
 * created on 2016-8-1
 */
public class TestBeanstalkClient {
	private String host = "10.0.0.171";
	private int port = 11300;
	
	@SuppressWarnings("deprecation")
	@Test
	public void testListTubes() throws BeanstalkException {
		BeanstalkClient client = new BeanstalkClient(host, port, "example1");
		client.put(1l, 0, 5000, "this is some data".getBytes());
		client.close();
		
		client = new BeanstalkClient(host, port, "example2");
		client.put(1l, 0, 5000, "this is some data".getBytes());
		client.close();
		
		client = new BeanstalkClient(host, port);
		List<String> tubes = client.listTubes();
		client.close();
		Assert.assertTrue(tubes.size() >= 3);
		
	}
}
