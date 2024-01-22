package com.realsil.sdk.core;
/* loaded from: classes12.dex */
public final class Constants {
    public static final String FLAVOR_CUSTOMER = "customer";
    public static final String FLAVOR_HOTFIX = "hotfix";
    public static final String FLAVOR_QC = "qc";

    public boolean isLogEnabled(boolean z, String str) {
        return z || FLAVOR_QC.equals(str);
    }
}
