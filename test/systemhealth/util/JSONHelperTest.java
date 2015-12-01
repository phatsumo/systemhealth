/**
 *
 */
package systemhealth.util;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systemhealth.data.ServerHealthStat;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author 1062992
 *
 */
public class JSONHelperTest {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(JSONHelperTest.class);

    private static File jsonFileToParse = null;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        jsonFileToParse = new File("./test-resources/serverhealth_windows.json");
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        jsonFileToParse = null;
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link systemhealth.util.JSONHelper#toServerHealthStat(java.io.File)}.
     */
    @Test
    public void testToServerHealthStat() {

        ServerHealthStat stat = createStat();

        Assert.assertNotNull(stat);

        LOGGER.info("\nServer health stat: \n" + stat);
    }

    /**
     * Test method for
     * {@link systemhealth.util.JSONHelper#toJSON(ServerHealthStat)}
     */
    @Test
    public void testToJSON() {
        ServerHealthStat stat = createStat();

        try {
            LOGGER.info("\n ServerHealthStat as JSON: \n"
                    + JSONHelper.toJSON(stat));

        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to serialize ServerHealthStat: " + stat, e);
        }
    }

    private ServerHealthStat createStat() {

        return JSONHelper.toServerHealthStat(jsonFileToParse);
    }

}
