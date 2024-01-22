package com.mappls.sdk.services.api.nearby;

import androidx.annotation.Keep;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Keep
/* loaded from: classes6.dex */
public final class NearbyCriteria {
    public static final String DISTANCE = "dist";
    public static final String DISTANCE_ASCENDING = "dist:asc";
    public static final String DISTANCE_DESCENDING = "dist:desc";
    public static final String IMPORTANCE = "imp";
    public static final String NAME_ASCENDING = "name:asc";
    public static final String NAME_DESCENDING = "name:desc";
    public static final String POD_CITY = "CITY";
    public static final String POD_LOCALITY = "LC";
    public static final String POD_STATE = "STATE";
    public static final String POD_SUB_LOCALITY = "SLC";

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    public @interface PodCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    public @interface SearchingCriteria {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    public @interface SortingCriteria {
    }

    private NearbyCriteria() {
    }
}
