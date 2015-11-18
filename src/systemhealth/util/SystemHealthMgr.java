/**
 * 
 */
package systemhealth.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import systemhealth.job.SystemHealthAnalyzer;

/**
 * @author 1062992
 *
 */
public class SystemHealthMgr {

	private ExecutorService executor;
	
	private static int numThreads = 5; //TODO - externalize this (put in properties file)
	
	private static SystemHealthMgr instance;
	
	private SystemHealthMgr() {
		executor = Executors.newFixedThreadPool(numThreads);
	}
	
	public synchronized static SystemHealthMgr getInstance() {
		if(instance == null) {
			instance = new SystemHealthMgr();
		}
		
		return instance;
	}
	
	public void submitJob(SystemHealthAnalyzer job) {
		executor.execute(job);
	}
}
