/**
 * 
 */
package systemhealth.util;

import java.io.File;

import org.junit.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import systemhealth.data.ServerHealthStat;

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
        jsonFileToParse = new File("serverhealth.json");
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

        ServerHealthStat stat = JSONHelper.toServerHealthStat(jsonFileToParse);

        Assert.assertNotNull(stat);

        LOGGER.info("\nServer health stat: \n" + stat);
    }

}
