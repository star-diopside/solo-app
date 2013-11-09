package jp.gr.java_conf.star_diopside.solo.core.exception;

/**
 * 業務例外が発生したことを表す検査例外クラス
 */
@SuppressWarnings("serial")
public class ApplicationException extends Exception implements IBusinessException {

    private boolean resource = false;
    private Object[] arguments = null;

    /**
     * コンストラクタ
     */
    public ApplicationException() {
        super();
    }

    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     * 
     * @param message 例外クラス
     * @param cause 原因例外
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ
     * 
     * @param cause 原因例外
     */
    public ApplicationException(Throwable cause) {
        super(cause);
    }

    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ
     * @param resource messageをリソースキーとする場合はtrue、メッセージ文字列の場合はfalse
     * @param arguments messageにリソースキーを指定した場合の埋め字配列
     */
    public ApplicationException(String message, boolean resource, Object... arguments) {
        super(message);
        this.resource = resource;
        this.arguments = arguments;
    }

    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ
     * @param cause 原因例外
     * @param resource messageをリソースキーとする場合はtrue、メッセージ文字列の場合はfalse
     * @param arguments messageにリソースキーを指定した場合の埋め字配列
     */
    public ApplicationException(String message, Throwable cause, boolean resource, Object... arguments) {
        super(message, cause);
        this.resource = resource;
        this.arguments = arguments;
    }

    @Override
    public boolean isResource() {
        return resource;
    }

    @Override
    public Object[] getArguments() {
        return this.arguments;
    }
}
