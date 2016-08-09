package com.trendrr.beanstalk;

public class ThreadTest extends Thread {
	private BeanstalkPool pool = null;
	
	public ThreadTest(BeanstalkPool pool, int number) {
		super("Thread" + number);
		this.pool = pool;
	}


	@Override
	public void run() {
		while(true) {
			BeanstalkClient client = null;
			BeanstalkJob job = null;
			try {
				client = pool.getClient();
				long start = System.currentTimeMillis();
				job = client.reserve(30);
				System.out.println(Thread.currentThread().getName() + ":2,use time:" 
						+ (System.currentTimeMillis() - start)/1000 + " seconds");
				if(job != null) {
					System.out.println(Thread.currentThread().getName() + ",job id:" + job.getId() + ",job data:" 
							+ new String(job.getData()));
					client.deleteJob(job);
				}else{
					System.out.println(Thread.currentThread().getName() + ",job is null");
				}
			} catch (BeanstalkException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(client != null) {
					client.close();
				}
			}
		}
	}
}
