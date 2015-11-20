/**
 *
 */
package systemhealth.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systemhealth.data.ServerHealthStat;
import systemhealth.data.ServerThresholdConfigData;

/**
 *
 * @author 1062992
 *
 */
public class SystemHealthAnalyzer implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemHealthAnalyzer.class);
    
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
        LOGGER.debug("SystemHealthAnalyzer.run()");
    }

}
