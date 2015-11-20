/**
 *
 */
package systemhealth.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systemhealth.data.Disk;
import systemhealth.data.ServerHealthStat;
import systemhealth.data.ServerThreshold;
import systemhealth.data.ServerThresholdConfigData;

/**
 * Runnable that checks performance levels for disks and CPU in
 * {@link ServerHealthStat} against the configured thresholds in
 * {@link ServerThresholdConfigData}.
 *
 * @author 1062992
 *
 */
public class SystemHealthAnalyzer implements Runnable {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(SystemHealthAnalyzer.class);

    private ServerHealthStat serverHealthStat;

    private ServerThresholdConfigData thresholdData;

    /**
     * Constructor
     *
     * @param stat
     * @param thresholdData
     */
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

        // check each field
        ServerThreshold serverThreshold = thresholdData
                .findByServerName(serverHealthStat.getServerName());

        if (serverThreshold == null) {
            LOGGER.warn("Threshold configuration not defined for server name: "
                    + serverHealthStat.getServerName());
            return;
        }

        // check disk usage level
        for (Disk disk : serverHealthStat.getDisks()) {

            if (serverThreshold.getCriticalDiskFreePercent() >= disk
                    .getPercentDiskFree()) {
                // need to send critical or urgent email to SA, disk is almost
                // full
                LOGGER.info(String
                        .format("DiskId %s disk percent free (%f) is below critical level (%f)",
                                disk.getDeviceID(), disk.getPercentDiskFree(),
                                serverThreshold.getWarningDiskFreePercent()));

                // TODO - We've breach critical disk usage mark. Just send the
                // email notification

            }

            if (serverThreshold.getWarningDiskFreePercent() >= disk
                    .getPercentDiskFree()) {
                // need to send warning email
                LOGGER.info(String
                        .format("DiskId %s disk percent free (%f) is below warning level (%f)",
                                disk.getDeviceID(), disk.getPercentDiskFree(),
                                serverThreshold.getWarningDiskFreePercent()));

                // TODO - mark that warning email needs to be sent: indicate in
                // email servername and the problem.

            }

        }

        // check CPU usage
        if (serverThreshold.getCriticalCPUUsagePercent() >= serverHealthStat
                .getPercentCPUUsage()) {

        }

        if (serverThreshold.getWarningCPUUsagePercent() >= serverHealthStat
                .getPercentCPUUsage()) {

        }

    }

}
