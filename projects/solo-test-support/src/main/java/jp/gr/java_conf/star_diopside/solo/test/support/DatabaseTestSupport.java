package jp.gr.java_conf.star_diopside.solo.test.support;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import javax.sql.DataSource;

import jp.gr.java_conf.star_diopside.solo.test.dataset.csv.CsvProducerEx;
import jp.gr.java_conf.star_diopside.solo.test.exception.TestException;
import jp.gr.java_conf.star_diopside.solo.test.util.TestUtils;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CachedDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

/**
 * データベースを使用するテストサポートクラス
 */
public class DatabaseTestSupport {

    /** テストクラスのインスタンス */
    private Object tester;

    /** データソース */
    private DataSource dataSource;

    /** データベーステスター */
    private IDatabaseTester databaseTester;

    /** テストデータセット */
    private IDataSet dataSet;

    /**
     * コンストラクタ
     * 
     * @param tester テストクラスのインスタンス
     * @param dataSource データソース
     */
    public DatabaseTestSupport(Object tester, DataSource dataSource) {
        this.tester = tester;
        this.dataSource = dataSource;
    }

    /**
     * テストデータセットを取得する。
     * 
     * @return テストデータセット
     */
    public IDataSet getDataSet() {
        return dataSet;
    }

    /**
     * フラットXMLデータセットを設定する。
     * 
     * @param testFile テストデータファイル名
     */
    public void setFlatXmlDataSet(String testFile) {

        try {
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            dataSet = builder.build(Files.newInputStream(TestUtils.findTestDataFile(tester, testFile)));
        } catch (DataSetException | IOException e) {
            throw new TestException(e);
        }
    }

    /**
     * CSVデータセットを設定する。
     * 
     * @param testDirectory テストデータディレクトリ名
     */
    public void setCsvDataSet(String testDirectory) {

        try {
            dataSet = new CachedDataSet(new CsvProducerEx(TestUtils.findTestDataFile(tester, testDirectory),
                    Charset.forName("UTF-8")));
        } catch (DataSetException e) {
            throw new TestException(e);
        }
    }

    /**
     * テストデータのセットアップを行う。
     */
    public void onSetup() {

        databaseTester = new DataSourceDatabaseTester(dataSource);
        databaseTester.setDataSet(dataSet);

        try {
            databaseTester.onSetup();
        } catch (Exception e) {
            throw new TestException(e);
        }
    }

    /**
     * データベースをテスト前の状態に戻す。
     */
    public void onTearDown() {

        try {
            databaseTester.onTearDown();
        } catch (Exception e) {
            throw new TestException(e);
        }
    }

    /**
     * データベースコネクションを取得する。
     * 
     * @return データベースコネクション
     */
    public IDatabaseConnection getConnection() {

        try {
            return databaseTester.getConnection();
        } catch (Exception e) {
            throw new TestException(e);
        }
    }
}
