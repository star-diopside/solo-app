package jp.gr.java_conf.star_diopside.solo.batch.job;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

import jp.gr.java_conf.star_diopside.solo.test.support.CommitTransactionDatabaseTestSupport;
import jp.gr.java_conf.star_diopside.solo.test.support.DatabaseTestSupport;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BA01J010Test {

    @Resource(name = "dbunitDataSource")
    private DataSource dataSource;

    @Resource
    private PlatformTransactionManager transactionManager;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    private DatabaseTestSupport databaseTestSupport;

    @Before
    public void before() {
        databaseTestSupport = new CommitTransactionDatabaseTestSupport(this, dataSource, transactionManager);
        databaseTestSupport.setCsvDataSet("dataset");
        databaseTestSupport.onSetup();
    }

    @After
    public void after() {
        databaseTestSupport.onTearDown();
    }

    @Test
    public void testJob() throws Exception {
        JobParametersBuilder jpb = new JobParametersBuilder(jobLauncherTestUtils.getUniqueJobParameters());
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jpb.toJobParameters());
        assertThat(jobExecution.getExitStatus(), is(ExitStatus.COMPLETED));
    }
}
