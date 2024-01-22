package com.coveiot.android.tappy.model;

import java.io.Serializable;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ConfirmProvisioningRequest implements Serializable {
    @Nullable
    private List<APDUResponse> apduResponses;
    @Nullable
    private String failureReason;
    private boolean hasSucceeded;
    @Nullable
    private String initUpdateResponse;

    @Nullable
    public final List<APDUResponse> getApduResponses() {
        return this.apduResponses;
    }

    @Nullable
    public final String getFailureReason() {
        return this.failureReason;
    }

    public final boolean getHasSucceeded() {
        return this.hasSucceeded;
    }

    @Nullable
    public final String getInitUpdateResponse() {
        return this.initUpdateResponse;
    }

    public final void setApduResponses(@Nullable List<APDUResponse> list) {
        this.apduResponses = list;
    }

    public final void setFailureReason(@Nullable String str) {
        this.failureReason = str;
    }

    public final void setHasSucceeded(boolean z) {
        this.hasSucceeded = z;
    }

    public final void setInitUpdateResponse(@Nullable String str) {
        this.initUpdateResponse = str;
    }
}
