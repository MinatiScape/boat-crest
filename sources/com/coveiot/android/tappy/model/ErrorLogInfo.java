package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ErrorLogInfo implements Serializable {
    @Nullable
    private String dateUTC;
    @Nullable
    private String details;
    @Nullable
    private String errorGUID;
    @Nullable
    private ExtraInfo extraInfo;
    @Nullable
    private String logSeverity;
    @Nullable
    private String module;

    @Nullable
    public final String getDateUTC() {
        return this.dateUTC;
    }

    @Nullable
    public final String getDetails() {
        return this.details;
    }

    @Nullable
    public final String getErrorGUID() {
        return this.errorGUID;
    }

    @Nullable
    public final ExtraInfo getExtraInfo() {
        return this.extraInfo;
    }

    @Nullable
    public final String getLogSeverity() {
        return this.logSeverity;
    }

    @Nullable
    public final String getModule() {
        return this.module;
    }

    public final void setDateUTC(@Nullable String str) {
        this.dateUTC = str;
    }

    public final void setDetails(@Nullable String str) {
        this.details = str;
    }

    public final void setErrorGUID(@Nullable String str) {
        this.errorGUID = str;
    }

    public final void setExtraInfo(@Nullable ExtraInfo extraInfo) {
        this.extraInfo = extraInfo;
    }

    public final void setLogSeverity(@Nullable String str) {
        this.logSeverity = str;
    }

    public final void setModule(@Nullable String str) {
        this.module = str;
    }
}
