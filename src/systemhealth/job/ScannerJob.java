/**
 * 
 */
package systemhealth.job;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;

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

import systemhealth.data.ServerHealthStat;
import systemhealth.util.JSONHelper;

/**
 * Quartz job that scans a configured directory and processes server system health.
 * 
 * @author 1062992
 *
 */
@DisallowConcurrentExecution
public class ScannerJob implements Job {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScannerJob.class);
	
	private static String directoryToScan = "";
	
	private static String fileExtensionToFilter = "";
	
	public ScannerJob() {
		Properties scannerProperties = new Properties();
		try {
			scannerProperties.load(ScannerJob.class.getResourceAsStream("/scanner.properties"));
			directoryToScan = scannerProperties.getProperty("directoryToScan");
			fileExtensionToFilter = scannerProperties.getProperty("fileExtensionToFilter");
			
		} catch (IOException e) {
			LOGGER.error("Failed to initialize ScannerJob", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		LOGGER.debug("executing scanner job...");
		//1.  get list of files to process.
		DirectoryStream<Path> ds = scan(directoryToScan);
		
		//2.  foreach $file in list of files: parse and do something
				
		for(Path p : ds) {
			File fileToProcess = p.toFile();
			LOGGER.debug("File to process: " + fileToProcess.getAbsolutePath());
			
			//TODO - do something with this 
			ServerHealthStat serverHealthStat = JSONHelper.toServerHealthStat(fileToProcess);
		
			
			
		}

	}
	
	private DirectoryStream<Path> scan(String directory) {
		if(directory == null || directory.isEmpty()) {
			LOGGER.warn("Directory to scan not initialized.");
			return null;
		}
		
		DirectoryStream<Path> ds = null;
		try {
			ds = Files.newDirectoryStream(Paths.get(directory), new Filter<Path>() {
			
				@Override
				public boolean accept(Path arg0) throws IOException {
					LOGGER.debug("Path = " + arg0.toString());
					//accept if file ends with the correct file extension
					return arg0.toString().endsWith(fileExtensionToFilter);
				}				
			});
			
		} catch (IOException e) {
			LOGGER.error("Failed to scan directory, " + directory);
		}
		
		return ds;
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
