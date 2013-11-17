package jp.gr.java_conf.star_diopside.solo.data.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import jp.gr.java_conf.star_diopside.solo.data.entity.User;
import jp.gr.java_conf.star_diopside.solo.test.support.DatabaseTestSupport;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@TransactionConfiguration
@Transactional
public class UserRepositoryTest {

    @Resource
    private DataSource dataSource;

    @Autowired
    private UserRepository userRepository;

    private DatabaseTestSupport databaseTestSupport;

    @Before
    public void before() {
        databaseTestSupport = new DatabaseTestSupport(this, dataSource);
        databaseTestSupport.setFlatXmlDataSet("dataset.xml");
        databaseTestSupport.onSetup();
    }

    @After
    public void after() {
        databaseTestSupport.onTearDown();
    }

    @Test
    public void testCount() throws Exception {

        long count = userRepository.count();

        long rowCount = databaseTestSupport.getDataSet().getTable("users").getRowCount();
        assertThat(count, is(rowCount));
    }

    @Test
    public void testFindOne() throws Exception {

        User user = userRepository.findOne("user01");

        System.out.println(user);
    }

    @Test
    public void testFindAll() {

        List<User> users = userRepository.findAll();

        for (User user : users) {
            System.out.println(user);
        }
    }
}
