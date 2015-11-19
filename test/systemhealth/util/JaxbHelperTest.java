package systemhealth.util;

import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systemhealth.data.ServerThreshold;
import systemhealth.data.ServerThresholdConfigData;

/**
 *
 * @author 1062992
 *
 */
public class JaxbHelperTest {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(JaxbHelperTest.class);

    private static InputStream inputStream = null;

    /**
     * @throws Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        inputStream = JaxbHelperTest.class
                .getResourceAsStream("/thresholds.xml");
    }

    /**
     *
     * @throws Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        if (inputStream != null) {
            inputStream.close();
            inputStream = null;
        }
    }

    /**
     *
     * @throws JAXBException
     */
    @Test
    public void testUnmarshal() throws JAXBException {

        ServerThresholdConfigData serverThresholdConfigData = JaxbHelper
                .unmarshal(inputStream, ServerThresholdConfigData.class);

        Assert.assertNotNull(serverThresholdConfigData);

    }

    /**
     *
     * @throws JAXBException
     */
    @Test
    public void testMarshal() throws JAXBException {
        ServerThresholdConfigData tcd = new ServerThresholdConfigData();

        ServerThreshold threshold1 = new ServerThreshold();
        threshold1.setServerName("XYZ");
        threshold1.setCriticalCPUUsagePercent(0.95f);
        threshold1.setCriticalDiskFreePercent(0.05f); // 5% free is critical
        // level
        threshold1.setWarningCPUUsagePercent(0.80f);
        threshold1.setWarningDiskFreePercent(0.10f);

        ServerThreshold threshold2 = new ServerThreshold();
        threshold2.setServerName("ABC");
        threshold2.setCriticalCPUUsagePercent(0.95f);
        threshold2.setCriticalDiskFreePercent(0.05f); // 5% free is critical
        // level
        threshold2.setWarningCPUUsagePercent(0.80f);
        threshold2.setWarningDiskFreePercent(0.10f);

        tcd.getThresholds().add(threshold1);
        tcd.getThresholds().add(threshold2);

        String marshalledString = JaxbHelper.marshal(tcd,
                ServerThresholdConfigData.class);

        LOGGER.info("Threshold object: " + marshalledString);
    }

}
