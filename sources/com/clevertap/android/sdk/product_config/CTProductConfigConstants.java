package com.clevertap.android.sdk.product_config;

import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public interface CTProductConfigConstants {
    public static final int DEFAULT_NO_OF_CALLS = 5;
    public static final String DEFAULT_VALUE_FOR_STRING = "";
    public static final int DEFAULT_WINDOW_LENGTH_MINS = 60;
    public static final String DIR_PRODUCT_CONFIG = "Product_Config";
    public static final String FILE_NAME_ACTIVATED = "activated.json";
    public static final String FILE_NAME_CONFIG_SETTINGS = "config_settings.json";
    public static final String KEY_LAST_FETCHED_TIMESTAMP = "ts";
    public static final String PRODUCT_CONFIG_JSON_KEY_FOR_KEY = "n";
    public static final String PRODUCT_CONFIG_JSON_KEY_FOR_VALUE = "v";
    public static final String PRODUCT_CONFIG_MIN_INTERVAL_IN_SECONDS = "fetch_min_interval_seconds";
    public static final String PRODUCT_CONFIG_NO_OF_CALLS = "rc_n";
    public static final String PRODUCT_CONFIG_WINDOW_LENGTH_MINS = "rc_w";
    public static final String TAG_PRODUCT_CONFIG = "[Product Config]";
    public static final long DEFAULT_MIN_FETCH_INTERVAL_SECONDS = TimeUnit.MINUTES.toSeconds(12);
    public static final Boolean DEFAULT_VALUE_FOR_BOOLEAN = Boolean.FALSE;
    public static final Long DEFAULT_VALUE_FOR_LONG = 0L;
    public static final Double DEFAULT_VALUE_FOR_DOUBLE = Double.valueOf(0.0d);
}
