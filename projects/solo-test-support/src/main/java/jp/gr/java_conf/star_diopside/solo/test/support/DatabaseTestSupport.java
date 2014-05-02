package jp.gr.java_conf.star_diopside.solo.test.support;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;

/**
 * データベースを使用するテストをサポートする機能を提供するインタフェース
 */
public interface DatabaseTestSupport {

    /**
     * テストデータセットを取得する。
     * 
     * @return テストデータセット
     */
    IDataSet getDataSet();

    /**
     * フラットXMLデータセットを設定する。
     * 
     * @param testFile テストデータファイル名
     */
    void setFlatXmlDataSet(String testFile);

    /**
     * CSVデータセットを設定する。
     * 
     * @param testDirectory テストデータディレクトリ名
     */
    void setCsvDataSet(String testDirectory);

    /**
     * テストデータのセットアップを行う。
     */
    void onSetup();

    /**
     * データベースをテスト前の状態に戻す。
     */
    void onTearDown();

    /**
     * データベースコネクションを取得する。
     * 
     * @return データベースコネクション
     */
    IDatabaseConnection getConnection();

}
