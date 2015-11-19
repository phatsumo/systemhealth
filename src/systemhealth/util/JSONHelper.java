package systemhealth.util;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import systemhealth.data.ServerHealthStat;

/**
 * @author 1062992
 *
 */
public class JSONHelper {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(JSONHelper.class);

    /**
     * Parses the JSON file and binds it to {@link ServerHealthStat}. Returns
     * ServerHealthStat.
     * 
     * @param jsonFile
     *            the JSON file to parse
     * @return the ServerHealthStat
     */
    public static ServerHealthStat toServerHealthStat(File jsonFile) {

        if (jsonFile == null) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();

        ServerHealthStat stat = new ServerHealthStat();
        try {

            stat = objectMapper.readValue(jsonFile,
                    new TypeReference<ServerHealthStat>() {
                    });

            // keep the commented out section below, it illustrates an alternate
            // way for deserializing the json file to java object.
            // JsonNode on = objectMapper.readTree(jsonFile);
            // String serverName = on.get("serverName").textValue();
            // float percentCPUUsage = on.get("percentCPUUsage").floatValue();
            // float serverUpTimeDays = on.get("serverUpTimeDays").floatValue();
            //
            // //parse "disks" section
            // String disksAsString = on.path("disks").toString();
            // List<Disk> diskList = objectMapper.readValue(disksAsString, new
            // TypeReference<List<Disk>>() { });
            //
            // stat.setDisks(diskList);
            // stat.setServerName(serverName);
            // stat.setPercentCPUUsage(percentCPUUsage);
            // stat.setServerUpTimeDays(serverUpTimeDays);

        } catch (IOException e) {
            LOGGER.error("Failed to unmarshall json file: " + jsonFile, e);
        }

        return stat;

    }

}
