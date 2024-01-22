package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class APDUResponse implements Serializable {
    @Nullable
    private String apduId;
    @Nullable
    private String apduReasonCode;
    @Nullable
    private String apduResponse;

    @Nullable
    public final String getApduId() {
        return this.apduId;
    }

    @Nullable
    public final String getApduReasonCode() {
        return this.apduReasonCode;
    }

    @Nullable
    public final String getApduResponse() {
        return this.apduResponse;
    }

    public final void setApduId(@Nullable String str) {
        this.apduId = str;
    }

    public final void setApduReasonCode(@Nullable String str) {
        this.apduReasonCode = str;
    }

    public final void setApduResponse(@Nullable String str) {
        this.apduResponse = str;
    }
}
