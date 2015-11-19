package systemhealth.data;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JAXBTest {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(JAXBTest.class);

    private static JAXBContext context = null;
    private static Marshaller marshaller = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        context = JAXBContext.newInstance(ServerThresholdConfigData.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test serialization of Threshold obj
     *
     * @throws JAXBException
     */
    @Test
    public void test() throws JAXBException {

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

        StringWriter writer = new StringWriter();
        marshaller.marshal(tcd, writer);

        LOGGER.info("Threshold object: " + writer.toString());

    }

}
