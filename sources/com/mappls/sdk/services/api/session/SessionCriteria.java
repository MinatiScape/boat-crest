package com.mappls.sdk.services.api.session;

import androidx.annotation.Keep;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Keep
/* loaded from: classes8.dex */
public class SessionCriteria {
    public static final String SESSION_TYPE_GLOBAL = "global";
    public static final String SESSION_TYPE_NAVIGATION = "navigation";

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes8.dex */
    public @interface SessionTypeCriteria {
    }
}
