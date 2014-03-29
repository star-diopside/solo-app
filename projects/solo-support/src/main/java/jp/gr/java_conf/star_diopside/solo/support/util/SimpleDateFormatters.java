package jp.gr.java_conf.star_diopside.solo.support.util;

import java.text.SimpleDateFormat;
import java.util.function.Supplier;

public final class SimpleDateFormatters {

    private SimpleDateFormatters() {
    }

    private static final ThreadLocal<SimpleDateFormat> THREADLOCAL_ISO_LOCAL_DATE = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static final Supplier<SimpleDateFormat> ISO_LOCAL_DATE = () -> THREADLOCAL_ISO_LOCAL_DATE.get();

}
