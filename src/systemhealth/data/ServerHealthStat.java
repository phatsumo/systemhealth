/**
 * 
 */
package systemhealth.data;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 1062992
 *
 */
public class ServerHealthStat {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerHealthStat.class);
		
	private String serverName;
	
	private List<Disk> disks;
	
	private float percentCPUUsage;
	
	private float serverUpTimeDays;
	
	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}


	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}


	/**
	 * @return the disks
	 */
	public List<Disk> getDisks() {
		return disks;
	}


	/**
	 * @param disks the disks to set
	 */
	public void setDisks(List<Disk> disks) {
		this.disks = disks;
	}


	/**
	 * @return the percentCPUUsage
	 */
	public float getPercentCPUUsage() {
		return percentCPUUsage;
	}

	/**
	 * @param percentCPUUsage the percentCPUUsage to set
	 */
	public void setPercentCPUUsage(float percentCPUUsage) {
		this.percentCPUUsage = percentCPUUsage;
	}
	
	/**
	 * @return the serverUpTimeDays
	 */
	public float getServerUpTimeDays() {
		return serverUpTimeDays;
	}


	/**
	 * @param serverUpTimeDays the serverUpTimeDays to set
	 */
	public void setServerUpTimeDays(float serverUpTimeDays) {
		this.serverUpTimeDays = serverUpTimeDays;
	}


	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("serverName="+serverName+"\n");
		for (Disk disk : disks) {
			sb.append("deviceID="+disk.getDeviceID()+", ");
			sb.append("totalSizeKB="+disk.getTotalSizeKB()+", ");
			sb.append("freeSizeKB="+disk.getFreeSizeKB()+", ");			
			sb.append("percentDiskFree="+disk.getPercentDiskFree()+"\n");
		}
		sb.append("serverUpTimeDays="+serverUpTimeDays+",\n");
		sb.append("percentCPUUsage="+percentCPUUsage+"\n");
		return sb.toString();
	}

}
