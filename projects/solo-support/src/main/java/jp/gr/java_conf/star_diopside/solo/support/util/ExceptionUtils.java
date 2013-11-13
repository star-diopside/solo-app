package jp.gr.java_conf.star_diopside.solo.support.util;

import jp.gr.java_conf.star_diopside.solo.core.exception.BusinessException;

import org.springframework.validation.Errors;

/**
 * 例外処理ユーティリティクラス
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
    }

    /**
     * 業務例外メッセージをエラー情報に設定する。
     * 
     * @param errors エラー情報
     * @param ex 業務例外
     */
    public static void reject(Errors errors, BusinessException ex) {

        if (ex.isResource()) {
            errors.reject(ex.getMessage(), ex.getArguments(), null);
        } else {
            errors.reject(null, ex.getMessage());
        }
    }
}
