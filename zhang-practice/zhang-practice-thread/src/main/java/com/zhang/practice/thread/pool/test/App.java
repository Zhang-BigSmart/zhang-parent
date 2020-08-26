package com.zhang.practice.thread.pool.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class App {

	// 初始化线程池
	private static ThreadPoolExecutor threadPool = new TestThreadPoll(
			8,
			8,
			1000,
			TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(1000));

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		
		int cores = Runtime.getRuntime().availableProcessors();

		int requestNum = 100;
		System.out.println("CPU核数 " + cores);

		List<Future<?>> futureList = new ArrayList<Future<?>>();
		
		Vector<Long> wholeTimeList = new Vector<Long>();
		Vector<Long> runTimeList = new Vector<Long>();
		
		for (int i = 0; i < requestNum; i++) {
			Future<?> future = threadPool.submit(new CPUTypeTest(runTimeList, wholeTimeList));
			
			//Future<?> future = threadPool.submit(new IOTypeTest(runTimeList, wholeTimeList));
			futureList.add(future);
		}

		for (Future<?> future : futureList) {
			//获取线程执行结果
			future.get(requestNum, TimeUnit.SECONDS);
		}
		
		long wholeTime = 0;
		for (int i = 0; i < wholeTimeList.size(); i++) {
			wholeTime = wholeTimeList.get(i) + wholeTime;
		}
		
		long runTime = 0;
		for (int i = 0; i < runTimeList.size(); i++) {
			runTime = runTimeList.get(i) + runTime;
		}

		System.out.println("平均每个线程整体花费时间： " +wholeTime/wholeTimeList.size());
		System.out.println("平均每个线程执行花费时间： " +runTime/runTimeList.size());

		System.out.println("test2 and");
	}
}
