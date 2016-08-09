package com.trendrr.beanstalk;

public class TestUseMain {

	public static void main(String[] args) throws BeanstalkException {
		BeanstalkPool pool = new BeanstalkPool("10.0.0.171", 11300, 100, "test-tube");
		BeanstalkClient client = null;
		
		for(int i =0; i<10000; i++) {
			client = pool.getClient();
			client.put(1l, 0, 5000, ("this is some data" + i).getBytes());
			client.close();
		}
		

		System.out.println("Ready to test thread");
		
		for(int i=0; i<10; i++) {
			new ThreadTest(pool, i+1).start();
		}

	}
	
}
