package com.clevertap.android.sdk.product_config;

import com.clevertap.android.sdk.CleverTapInstanceConfig;
@Deprecated
/* loaded from: classes2.dex */
public class b {
    @Deprecated
    public static String a(CleverTapInstanceConfig cleverTapInstanceConfig) {
        StringBuilder sb = new StringBuilder();
        sb.append(cleverTapInstanceConfig != null ? cleverTapInstanceConfig.getAccountId() : "");
        sb.append(CTProductConfigConstants.TAG_PRODUCT_CONFIG);
        return sb.toString();
    }

    @Deprecated
    public static boolean b(Object obj) {
        return (obj instanceof String) || (obj instanceof Number) || (obj instanceof Boolean);
    }
}
