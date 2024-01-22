package com.touchgui.sdk;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes12.dex */
public class TGHealthData extends f {
    public static final int DATA_SCOPE_ALL = 3;
    public static final int DATA_SCOPE_HISTORY = 2;
    public static final int DATA_SCOPE_TODAY = 1;
    public static final int DATA_TYPE_BREATH_TRAIN = 6;
    public static final int DATA_TYPE_HEART_RATE = 1;
    public static final int DATA_TYPE_SLEEP = 3;
    public static final int DATA_TYPE_SPO2 = 4;
    public static final int DATA_TYPE_STEP = 2;
    public static final int DATA_TYPE_STRESS = 5;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    public @interface TGHealthDataScope {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    public @interface TGHealthDataType {
    }

    public TGHealthData(int i) {
        this(i, 3);
    }

    public TGHealthData(int i, int i2) {
        super(i, i2);
    }
}
