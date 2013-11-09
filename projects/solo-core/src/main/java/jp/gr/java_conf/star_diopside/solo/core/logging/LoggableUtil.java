package jp.gr.java_conf.star_diopside.solo.core.logging;

import java.util.Collection;

/**
 * ログ出力情報編集用ユーティリティクラス
 */
public final class LoggableUtil {

    /**
     * プライベートコンストラクタ
     */
    private LoggableUtil() {
    }

    /**
     * ログ出力用文字列を編集し、ログリストに追加する。
     * 
     * @param log ログ出力用文字列を設定するコレクション
     * @param item ログ出力項目
     * @param itemName ログ出力項目名称
     * @param enabled ログ出力が有効な場合はtrue、無効な場合はfalse。
     */
    public static void addLog(Collection<String> log, Object item, String itemName, boolean enabled) {
        if (enabled) {
            addLog(log, item, itemName);
        }
    }

    /**
     * ログ出力用文字列を編集し、ログリストに追加する。
     * 
     * @param log ログ出力用文字列を設定するコレクション
     * @param item ログ出力項目
     * @param itemName ログ出力項目名称
     */
    public static void addLog(Collection<String> log, Object item, String itemName) {

        if (item == null) {
            log.add(itemName + " = " + item);

        } else if (item instanceof Loggable) {
            for (String s : ((Loggable) item).toLogText()) {
                log.add(itemName + "." + s);
            }

        } else {
            log.add(itemName + " = " + item.toString());
        }
    }

    /**
     * リスト項目をログ出力用文字列に編集し、ログリストに追加する。
     * 
     * @param log ログ出力用文字列を設定するコレクション
     * @param itemList ログ出力リスト項目
     * @param itemName ログ出力リスト項目名称
     * @param enabled ログ出力が有効な場合はtrue、無効な場合はfalse。
     */
    public static void addLogList(Collection<String> log, Collection<?> itemList, String itemName, boolean enabled) {
        if (enabled) {
            addLogList(log, itemList, itemName);
        }
    }

    /**
     * リスト項目をログ出力用文字列に編集し、ログリストに追加する。
     * 
     * @param log ログ出力用文字列を設定するコレクション
     * @param itemList ログ出力リスト項目
     * @param itemName ログ出力リスト項目名称
     */
    public static void addLogList(Collection<String> log, Collection<?> itemList, String itemName) {

        if (itemList == null) {
            addLog(log, itemList, itemName);

        } else {
            int count = 0;

            for (Object o : itemList) {
                addLog(log, o, itemName + "[" + count + "]");
                count++;
            }
        }
    }
}
