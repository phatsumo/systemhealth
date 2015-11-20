package systemhealth.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

<<<<<<< HEAD
/**
 *
 * @author 1062992
 *
 */
public class EmailNotifierTest {

    /**
     * @throws Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     *
     */
    @Test
    public void testSendMail() {
        EmailNotifier em = new EmailNotifier();

        String subject = "Testing";
        String msg = "Just a test";
        String from = "phatsumo@gmail.com";
        String recipients = "dragons236@gmail.com";

=======
public class EmailNotifierTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
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

    @Test
    public void testSendMail() {
        EmailNotifier em = new EmailNotifier();
        
        String subject = "Testing";
        String msg = "Just a test";
        String from = "phatsumo@gmail.com";
        String recipients = "dragons236@gmail.com";
        
>>>>>>> branch 'master' of https://github.com/phatsumo/systemhealth
        em.sendMail(msg, from, recipients, subject);
    }

}
