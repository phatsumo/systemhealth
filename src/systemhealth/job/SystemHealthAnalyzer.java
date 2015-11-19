/**
 *
 */
package systemhealth.job;

import systemhealth.data.ServerHealthStat;
import systemhealth.data.ServerThresholdConfigData;

/**
 *
 * @author 1062992
 *
 */
public class SystemHealthAnalyzer implements Runnable {

    private ServerHealthStat serverHealthStat;

    private ServerThresholdConfigData thresholdData;

    public SystemHealthAnalyzer(ServerHealthStat stat,
            ServerThresholdConfigData thresholdData) {
        this.serverHealthStat = stat;
        this.thresholdData = thresholdData;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {

    }

}
