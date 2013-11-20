package jp.gr.java_conf.star_diopside.solo.data.repository;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import jp.gr.java_conf.star_diopside.solo.data.entity.User;
import jp.gr.java_conf.star_diopside.solo.test.support.DatabaseTestSupport;

import org.apache.commons.lang3.StringUtils;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.ITable;
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

        assertUserEntity(user);
    }

    @Test
    public void testFindAll() throws Exception {

        List<User> users = userRepository.findAll();

        for (User user : users) {
            assertUserEntity(user);
        }
    }

    private void assertUserEntity(User user) throws DataSetException {

        ITable table = databaseTestSupport.getDataSet().getTable("users");
        int count = 0;

        for (int i = 0; i < table.getRowCount(); i++) {
            if (StringUtils.equals(user.getUserId(), (String) table.getValue(i, "user_id"))) {
                count++;
                assertThat(user.getUserId(), is(table.getValue(i, "user_id")));
                assertThat(user.getUsername(), is(table.getValue(i, "username")));
                assertThat(user.getPassword(), is(table.getValue(i, "password")));
                assertThat(user.getPasswordUpdatedTimestamp(),
                        is(Timestamp.valueOf((String) table.getValue(i, "password_updated_timestamp"))));
                assertThat(user.getEnabled(), is(Boolean.valueOf((String) table.getValue(i, "enabled"))));
                assertThat(user.getInterimRegister(),
                        is(Boolean.valueOf((String) table.getValue(i, "interim_register"))));
                assertThat(user.getLoginErrorCount(),
                        is(Integer.valueOf((String) table.getValue(i, "login_error_count"))));
                assertThat(user.getLockoutTimestamp(),
                        is(Timestamp.valueOf((String) table.getValue(i, "lockout_timestamp"))));
                assertThat(user.getLastLoginTimestamp(),
                        is(Timestamp.valueOf((String) table.getValue(i, "last_login_timestamp"))));
                assertThat(user.getLogoutTimestamp(),
                        is(Timestamp.valueOf((String) table.getValue(i, "logout_timestamp"))));
                assertThat(user.getRegisterTimestamp(),
                        is(Timestamp.valueOf((String) table.getValue(i, "register_timestamp"))));
                assertThat(user.getRegisterUserId(), is(table.getValue(i, "register_user_id")));
                assertThat(user.getUpdatedTimestamp(),
                        is(Timestamp.valueOf((String) table.getValue(i, "updated_timestamp"))));
                assertThat(user.getUpdatedUserId(), is(table.getValue(i, "updated_user_id")));
                assertThat(user.getVersion(), is(Integer.valueOf((String) table.getValue(i, "version"))));
            }
        }

        assertThat(count, is(1));
    }
}