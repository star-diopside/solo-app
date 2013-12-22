package jp.gr.java_conf.star_diopside.common.web.session;

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
 * セッション永続化ユーティリティクラス
 */
public final class SessionSerializationUtils {

    private SessionSerializationUtils() {
    }

    /**
     * セッション情報をシリアライズする。
     * 
     * @param session セッション
     * @return シリアライズしたバイト列
     */
    public static byte[] serializeSession(HttpSession session) {

        HashMap<String, Object> attributes = new HashMap<String, Object>();

        for (Enumeration<String> e = session.getAttributeNames(); e.hasMoreElements();) {
            String key = e.nextElement();
            attributes.put(key, session.getAttribute(key));
        }

        // セッション情報をシリアライズする。
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                oos.writeInt(session.getMaxInactiveInterval());
                oos.writeObject(attributes);
            }
            return bos.toByteArray();

        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    /**
     * セッション情報をデシリアライズする。
     * 
     * @param data シリアライズされたバイト列
     * @param session デシリアライズした情報を設定するセッション
     */
    public static void deserializeSession(byte[] data, HttpSession session) {

        int interval;
        Map<String, Object> attributes;

        // セッション情報をデシリアライズする。
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            interval = ois.readInt();
            @SuppressWarnings("unchecked")
            Map<String, Object> attr = (Map<String, Object>) ois.readObject();
            attributes = attr;
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
    }
}
