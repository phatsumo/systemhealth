/**
 * 
 */
package systemhealth.job;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Quartz job that scans a configured directory and processes server system health.
 * 
 * @author 1062992
 *
 */
@DisallowConcurrentExecution
public class ScannerJob implements Job {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScannerJob.class);
	
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		LOGGER.debug("executing...");
		//1.  get list of files to process.
		
		//2.  foreach $file in list of files: parse and do something
		

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

        try {
            // Grab the Scheduler instance from the Factory 
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            
            JobDetail job = newJob(ScannerJob.class).withIdentity("scannerjob", "group1").build();

            //trigger every 1 minutes and repeat indefinitely
            SimpleTrigger trigger = newTrigger().withIdentity("trigger", "group1").startNow()
                .withSchedule(simpleSchedule().withIntervalInMinutes(1).repeatForever()).build();

            Date scheduleDate = scheduler.scheduleJob(job, trigger);
            LOGGER.info(job.getKey() + " will run at: " + scheduleDate + " and repeat: " + trigger.getRepeatCount() + " times, every "
                     + trigger.getRepeatInterval() / 1000 + " seconds");

            LOGGER.info("------- Starting Scheduler ----------------");

            // All of the jobs have been added to the scheduler, but none of the jobs
            // will run until the scheduler has been started

            // and start it off
            scheduler.start();

            LOGGER.info("------- Started Scheduler -----------------");

//            scheduler.shutdown();

        } catch (SchedulerException se) {
        	LOGGER.error("Failed to schedule job.", se);

        }
		

	}

}
