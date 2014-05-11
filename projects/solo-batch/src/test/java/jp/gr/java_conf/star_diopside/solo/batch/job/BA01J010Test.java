package jp.gr.java_conf.star_diopside.solo.batch.job;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import jp.gr.java_conf.star_diopside.solo.test.support.CommitTransactionDatabaseTestSupport;
import jp.gr.java_conf.star_diopside.solo.test.support.DatabaseTestSupport;
import jp.gr.java_conf.star_diopside.solo.test.util.DataSetUtils;
import jp.gr.java_conf.star_diopside.solo.test.util.TestUtils;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.SortedDataSet;
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
    private Map<Object, Object> replacementObjectMap;

    @Before
    public void before() {
        HashMap<Object, Object> objectMap = new HashMap<>();
        LocalDateTime today = LocalDateTime.now();
        objectMap.put("${TODAY}", Timestamp.valueOf(today));
        objectMap.put("${YESTERDAY}", Timestamp.valueOf(today.minusDays(1)));
        databaseTestSupport = new CommitTransactionDatabaseTestSupport(this, dataSource, transactionManager);
        databaseTestSupport.setCsvDataSet("dataset");
        databaseTestSupport.setReplacementObjectMap(objectMap);
        databaseTestSupport.onSetup();
        replacementObjectMap = objectMap;
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

        IDataSet expectedDataSet = new ReplacementDataSet(DataSetUtils.createCsvDataSet(TestUtils.findTestDataFile(
                this, "expected")), replacementObjectMap, null);
        IDataSet actualDataSet = databaseTestSupport.getConnection().createDataSet(expectedDataSet.getTableNames());
        Assertion.assertEquals(new SortedDataSet(expectedDataSet), new SortedDataSet(actualDataSet));
    }
}
