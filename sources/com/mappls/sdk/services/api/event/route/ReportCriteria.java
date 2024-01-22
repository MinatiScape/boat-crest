package com.mappls.sdk.services.api.event.route;

import androidx.annotation.Keep;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Keep
/* loaded from: classes4.dex */
public class ReportCriteria {
    public static final int DARK_MODE = 1;
    public static final String ICON_24_PX = "24px";
    public static final String ICON_36_PX = "36px";
    public static final String ICON_48_PX = "48px";
    public static final String ICON_54_PX = "54px";
    public static final int LIGHT_MODE = 0;

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface ReportIconModeCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface ReportIconSizeCriteria {
    }
}
