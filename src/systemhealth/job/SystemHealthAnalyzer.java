/**
 * 
 */
package systemhealth.job;

import systemhealth.data.ServerHealthStat;

/**
 * 
 * @author 1062992
 *
 */
public class SystemHealthAnalyzer implements Runnable {
	
	private ServerHealthStat serverHealthStat;
	
	
	public SystemHealthAnalyzer(ServerHealthStat stat) {
		serverHealthStat = stat;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		

	}

}
