/**
 *
 */
package systemhealth.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author 1062992
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Threshold")
public class ServerThreshold {

    /**
     * Default constructor
     */
    public ServerThreshold() {

    }

    @XmlElement(name = "serverName", required = true)
    private String serverName;

    @XmlElement(name = "warningDiskFreePercent", required = true)
    private float warningDiskFreePercent;

    @XmlElement(name = "criticalDiskFreePercent", required = true)
    private float criticalDiskFreePercent;

    @XmlElement(name = "warningCPUUsagePercent", required = true)
    private float warningCPUUsagePercent;

    @XmlElement(name = "criticalCPUUsagePercent", required = true)
    private float criticalCPUUsagePercent;

    /**
     * @return the serverName
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * @param serverName
     *            the serverName to set
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * @return the warningDiskFreePercent
     */
    public float getWarningDiskFreePercent() {
        return warningDiskFreePercent;
    }

    /**
     * @param warningDiskFreePercent
     *            the warningDiskFreePercent to set
     */
    public void setWarningDiskFreePercent(float warningDiskFreePercent) {
        this.warningDiskFreePercent = warningDiskFreePercent;
    }

    /**
     * @return the criticalDiskFreePercent
     */
    public float getCriticalDiskFreePercent() {
        return criticalDiskFreePercent;
    }

    /**
     * @param criticalDiskFreePercent
     *            the criticalDiskFreePercent to set
     */
    public void setCriticalDiskFreePercent(float criticalDiskFreePercent) {
        this.criticalDiskFreePercent = criticalDiskFreePercent;
    }

    /**
     * @return the warningCPUUsagePercent
     */
    public float getWarningCPUUsagePercent() {
        return warningCPUUsagePercent;
    }

    /**
     * @param warningCPUUsagePercent
     *            the warningCPUUsagePercent to set
     */
    public void setWarningCPUUsagePercent(float warningCPUUsagePercent) {
        this.warningCPUUsagePercent = warningCPUUsagePercent;
    }

    /**
     * @return the criticalCPUUsagePercent
     */
    public float getCriticalCPUUsagePercent() {
        return criticalCPUUsagePercent;
    }

    /**
     * @param criticalCPUUsagePercent
     *            the criticalCPUUsagePercent to set
     */
    public void setCriticalCPUUsagePercent(float criticalCPUUsagePercent) {
        this.criticalCPUUsagePercent = criticalCPUUsagePercent;
    }
}
