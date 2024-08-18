package za.co.duma.demo_spring_batch.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
    private final JdbcTemplate jdbcTemplate;
    private final BatchProcessOverviewRepository repository;

    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate, BatchProcessOverviewRepository batchProcessOverviewRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.repository = batchProcessOverviewRepository;
    }

    @Override
    public void beforeJob(JobExecution jobExecution){
        //Code to execute before the job starts
        BatchProcessOverview overview = new BatchProcessOverview();
        overview.setJobName(jobExecution.getJobInstance().getJobName());
        overview.setJobInstanceId(jobExecution.getJobInstance().getInstanceId());
        overview.setJobExecutionId(jobExecution.getId());
        overview.setStatus(jobExecution.getStatus().toString());
        overview.setStartTime(new Timestamp(Objects.requireNonNull(jobExecution.getStartTime()).getTime()));
        LOGGER.info("JOB #: {} started",jobExecution.getJobInstance().getInstanceId());
        repository.save(overview);
    }

    @Override
    public void afterJob(JobExecution jobExecution){
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            LOGGER.info("JOB FINISHED! Time to verify the results");

            String query = "SELECT brand, origin, characteristics FROM coffee";
            jdbcTemplate.query(query, (rs, row) -> new Coffee(rs.getString(1), rs.getString(2), rs.getString(3)))
                    .forEach(coffee -> LOGGER.info("Found < {} > in the database.", coffee.toString()));
        }else if(jobExecution.getStatus() == BatchStatus.FAILED){
            LOGGER.error("JOB FAILED! Investigate the issue");
        }

        BatchProcessOverview overview = repository.findById(jobExecution.getId()).orElse(null);
        if(overview != null){
            overview.setStatus(jobExecution.getStatus().toString());
            overview.setEndTime(new Timestamp(Objects.requireNonNull(jobExecution.getEndTime()).getTime()));
            overview.setExitMassage(jobExecution.getExitStatus().getExitDescription());
            repository.save(overview);
        }
    }
}
