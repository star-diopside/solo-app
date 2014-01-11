package jp.gr.java_conf.star_diopside.common.web.session.service;

import jp.gr.java_conf.star_diopside.common.web.session.servlet.SessionStoreHttpServletRequest;
import jp.gr.java_conf.star_diopside.common.web.session.servlet.StoredHttpSession;

/**
 * セッション永続化機能を示すインタフェース
 */
public interface SessionStoreService {

    /**
     * セッション情報をストアから復元する。
     * 
     * @param request サーブレットリクエスト
     */
    public void readSession(SessionStoreHttpServletRequest request);

    /**
     * セッション情報をストアに永続化する。
     * 
     * @param request サーブレットリクエスト
     */
    public void storeSession(SessionStoreHttpServletRequest request);

    /**
     * セッション情報をストアから削除する。
     * 
     * @param session セッション
     */
    void removeSession(StoredHttpSession session);

}
