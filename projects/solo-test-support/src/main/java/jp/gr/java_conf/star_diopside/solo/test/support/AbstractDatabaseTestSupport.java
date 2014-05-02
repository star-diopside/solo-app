package jp.gr.java_conf.star_diopside.solo.test.support;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.SQLException;

import javax.sql.DataSource;

import jp.gr.java_conf.star_diopside.solo.test.dataset.csv.CsvProducerEx;
import jp.gr.java_conf.star_diopside.solo.test.exception.TestException;
import jp.gr.java_conf.star_diopside.solo.test.util.TestUtils;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CachedDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

/**
 * データベースを使用するテストをサポートする機能のベースを実装する抽象クラス
 */
public abstract class AbstractDatabaseTestSupport implements DatabaseTestSupport {

    /** テストクラスのインスタンス */
    private Object tester;

    /** データソース */
    private DataSource dataSource;

    /** テストデータセット */
    private IDataSet dataSet;

    /**
     * コンストラクタ
     * 
     * @param tester テストクラスのインスタンス
     * @param dataSource データソース
     */
    protected AbstractDatabaseTestSupport(Object tester, DataSource dataSource) {
        this.tester = tester;
        this.dataSource = dataSource;
    }

    /**
     * データソースを取得する。
     * 
     * @return データソース
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public IDataSet getDataSet() {
        return dataSet;
    }

    @Override
    public void setFlatXmlDataSet(String testFile) {
        try {
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            dataSet = builder.build(Files.newInputStream(TestUtils.findTestDataFile(tester, testFile)));
        } catch (DataSetException | IOException e) {
            throw new TestException(e);
        }
    }

    @Override
    public void setCsvDataSet(String testDirectory) {
        try {
            dataSet = new CachedDataSet(new CsvProducerEx(TestUtils.findTestDataFile(tester, testDirectory),
                    StandardCharsets.UTF_8));
        } catch (DataSetException e) {
            throw new TestException(e);
        }
    }

    @Override
    public IDatabaseConnection getConnection() {
        try {
            return new DatabaseConnection(getDataSource().getConnection());
        } catch (DatabaseUnitException | SQLException e) {
            throw new TestException(e);
        }
    }
}
