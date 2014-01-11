package jp.gr.java_conf.star_diopside.common.web.session.servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.SerializationException;

/**
 * セッションの永続化に必要な属性を追加したセッションラッパークラス
 */
public class StoredHttpSession extends HttpSessionWrapper {

    /** セッション属性変更時刻タイムスタンプ (volatile変数とする) */
    private volatile long modifiedTime;

    /** 永続化時のセッション属性変更時刻タイムスタンプ */
    private long serializedModifiedTime;

    /**
     * コンストラクタ
     * 
     * @param session セッションオブジェクト
     */
    public StoredHttpSession(HttpSession session) {
        super(session);
        modifiedTime = System.currentTimeMillis();
    }

    /**
     * セッション属性の変更時刻のタイムスタンプを取得する。
     * 
     * @return セッション属性の変更時刻のタイムスタンプ
     */
    public final long getModifiedTime() {
        return modifiedTime;
    }

    /**
     * 永続化時のセッション属性変更時刻タイムスタンプを取得する。
     * 
     * @return 永続化時のセッション属性変更時刻タイムスタンプ
     */
    public final long getSerializedModifiedTime() {
        return serializedModifiedTime;
    }

    @Override
    public Object getAttribute(String name) {
        Object attribute = super.getAttribute(name);
        if (isMutable(attribute)) {
            modifiedTime = System.currentTimeMillis();
        }
        return attribute;
    }

    @Override
    public void setAttribute(String name, Object value) {
        super.setAttribute(name, value);
        modifiedTime = System.currentTimeMillis();
    }

    @Override
    public void removeAttribute(String name) {
        super.removeAttribute(name);
        modifiedTime = System.currentTimeMillis();
    }

    /**
     * セッション情報をシリアライズする。
     * 
     * @return シリアライズしたバイト列
     */
    public synchronized byte[] serialize() {

        HttpSession session = getSession();

        // セッション属性情報を列挙する。
        HashMap<String, Object> attributes = new HashMap<String, Object>();

        for (Enumeration<String> e = session.getAttributeNames(); e.hasMoreElements();) {
            String key = e.nextElement();
            attributes.put(key, session.getAttribute(key));
        }

        // セッション情報をシリアライズする。
        byte[] data;

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                oos.writeInt(session.getMaxInactiveInterval());
                oos.writeObject(attributes);
                oos.writeLong(modifiedTime);
            }
            data = bos.toByteArray();

        } catch (IOException e) {
            throw new SerializationException(e);
        }

        // 永続化時のセッション属性変更時刻タイムスタンプを更新する。
        serializedModifiedTime = modifiedTime;

        return data;
    }

    /**
     * セッション情報をデシリアライズする。
     * 
     * @param data シリアライズされたバイト列
     */
    public synchronized void deserialize(byte[] data) {

        HttpSession session = getSession();

        // セッション情報をデシリアライズする。
        int interval;
        Map<String, Object> attributes;
        long timestamp;

        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            interval = ois.readInt();
            @SuppressWarnings("unchecked")
            Map<String, Object> attr = (Map<String, Object>) ois.readObject();
            attributes = attr;
            timestamp = ois.readLong();
        } catch (IOException | ClassNotFoundException e) {
            throw new SerializationException(e);
        }

        // セッション情報を全削除する。
        for (Enumeration<String> e = session.getAttributeNames(); e.hasMoreElements();) {
            String key = e.nextElement();
            session.removeAttribute(key);
        }

        // デシリアライズしたセッション情報を設定する。
        for (Map.Entry<String, Object> e : attributes.entrySet()) {
            session.setAttribute(e.getKey(), e.getValue());
        }
        session.setMaxInactiveInterval(interval);
        modifiedTime = timestamp;

        // 永続化時のセッション属性変更時刻タイムスタンプを更新する。
        serializedModifiedTime = timestamp;
    }

    private boolean isMutable(Object obj) {
        if (obj == null || obj instanceof Boolean || obj instanceof Character || obj instanceof Byte
                || obj instanceof Short || obj instanceof Integer || obj instanceof Long || obj instanceof Float
                || obj instanceof Double || obj instanceof String) {
            return false;
        } else {
            return true;
        }
    }
}
