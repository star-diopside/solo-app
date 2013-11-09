package jp.gr.java_conf.star_diopside.solo.core.exception;

/**
 * 業務例外が発生したことを表すオブジェクトインタフェース
 */
public interface IBusinessException {

    /**
     * 例外メッセージにリソースファイルを使用するかどうかを示す値を取得する。
     * 
     * @return messageをリソースキーとする場合はtrue、メッセージ文字列の場合はfalse
     */
    boolean isResource();

    /**
     * 例外メッセージを取得する。
     * 
     * @return 例外メッセージ
     */
    String getMessage();

    /**
     * 例外メッセージの埋め字配列を取得する。
     * 
     * @return messageにリソースキーを指定した場合の埋め字配列
     */
    Object[] getArguments();

}
