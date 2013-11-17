package jp.gr.java_conf.star_diopside.solo.test.support;

import java.net.MalformedURLException;

import javax.sql.DataSource;

import jp.gr.java_conf.star_diopside.solo.test.exception.TestException;
import jp.gr.java_conf.star_diopside.solo.test.util.TestUtils;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
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
            dataSet = builder.build(TestUtils.findTestDataFile(tester, testFile));
        } catch (MalformedURLException | DataSetException e) {
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
}
