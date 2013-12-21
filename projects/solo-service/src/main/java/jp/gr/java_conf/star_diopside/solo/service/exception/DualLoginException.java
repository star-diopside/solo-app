package jp.gr.java_conf.star_diopside.solo.service.exception;

import org.springframework.security.authentication.AccountStatusException;

/**
 * 二重ログインエラーが発生したことを示す例外クラス
 */
@SuppressWarnings("serial")
public class DualLoginException extends AccountStatusException {

    public DualLoginException() {
        super("Dual login exception has occurred.");
    }

    public DualLoginException(Throwable cause) {
        super("Dual login exception has occurred.", cause);
    }
}
