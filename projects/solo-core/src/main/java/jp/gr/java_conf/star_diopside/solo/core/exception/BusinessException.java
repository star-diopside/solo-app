package jp.gr.java_conf.star_diopside.solo.core.exception;

/**
 * 業務例外が発生したことを表す例外クラス
 */
@SuppressWarnings("serial")
public class BusinessException extends RuntimeException implements IBusinessException {

    private boolean resource = false;
    private Object[] arguments = null;

    /**
     * コンストラクタ
     */
    public BusinessException() {
        super();
    }

    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     * 
     * @param message 例外クラス
     * @param cause 原因例外
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ
     * 
     * @param cause 原因例外
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }

    /**
     * コンストラクタ
     * 
     * @param message 例外メッセージ
     * @param resource messageをリソースキーとする場合はtrue、メッセージ文字列の場合はfalse
     * @param arguments messageにリソースキーを指定した場合の埋め字配列
     */
    public BusinessException(String message, boolean resource, Object... arguments) {
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
    public BusinessException(String message, Throwable cause, boolean resource, Object... arguments) {
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
