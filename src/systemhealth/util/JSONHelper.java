package systemhealth.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import systemhealth.data.Disk;
import systemhealth.data.ServerHealthStat;

public class JSONHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(JSONHelper.class);
		
	/**
	 * Parses the JSON file and binds it to {@link ServerHealthStat}.  Returns ServerHealthStat.
	 * @param jsonInput the JSON file
	 * @return the ServerHealthStat
	 */
	public static ServerHealthStat toServerHealthStat(File jsonFile) {
		
		if(jsonFile == null) {
			return null;
		}
				
		ObjectMapper objectMapper = new ObjectMapper();
		
		ServerHealthStat stat = new ServerHealthStat();
		try {
			JsonNode on = objectMapper.readTree(jsonFile);
			
			String serverName = on.get("serverName").textValue();
			float percentCPUUsage = on.get("percentCPUUsage").floatValue();
			float serverUpTimeDays = on.get("serverUpTimeDays").floatValue();
			
			//parse "disks" section
			String disksAsString = on.path("disks").toString();						
			List<Disk> diskList = objectMapper.readValue(disksAsString, new TypeReference<List<Disk>>() { });
			
			stat.setDisks(diskList);
			stat.setServerName(serverName);
			stat.setPercentCPUUsage(percentCPUUsage);
			stat.setServerUpTimeDays(serverUpTimeDays);			

		} catch (IOException e) {
			LOGGER.error("Failed to unmarshall json file: " + jsonFile, e);
		}

		return stat;

	}

	public static void main(String [] args) {
			File jsonFile = new File("serverhealth.json");
			ServerHealthStat stat = toServerHealthStat(jsonFile);
			LOGGER.info("\nServer health stat: \n" + stat);
	}

}
