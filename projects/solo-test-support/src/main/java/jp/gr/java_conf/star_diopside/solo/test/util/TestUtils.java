package jp.gr.java_conf.star_diopside.solo.test.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import jp.gr.java_conf.star_diopside.solo.test.exception.TestException;

/**
 * テスト用ユーティリティクラス
 */
public final class TestUtils {

    private TestUtils() {
    }

    /**
     * テストデータディレクトリを取得する。
     * 
     * @param tester テストクラスのインスタンス
     * @return テストデータを格納するディレクトリ
     */
    public static File findTestDataDir(Object tester) {

        Class<?> clazz = tester.getClass();
        URL url = clazz.getResource(clazz.getSimpleName() + "-data");

        try {
            return new File(url.toURI());
        } catch (URISyntaxException e) {
            throw new TestException(e);
        }
    }

    /**
     * テストデータファイルを取得する。
     * 
     * @param tester テストクラスのインスタンス
     * @param path テストファイルパス (テストデータディレクトリからの相対パス)
     * @return テストデータファイル
     */
    public static File findTestDataFile(Object tester, String path) {
        return new File(findTestDataDir(tester), path);
    }
}
