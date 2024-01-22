package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ApduCommand implements Serializable {
    @Nullable
    private String apdu;
    @Nullable
    private Boolean ignoreFailureResponse;
    @Nullable
    private String name;
    @Nullable
    private Boolean saveResponseData;

    @Nullable
    public final String getApdu() {
        return this.apdu;
    }

    @Nullable
    public final Boolean getIgnoreFailureResponse() {
        return this.ignoreFailureResponse;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final Boolean getSaveResponseData() {
        return this.saveResponseData;
    }

    public final void setApdu(@Nullable String str) {
        this.apdu = str;
    }

    public final void setIgnoreFailureResponse(@Nullable Boolean bool) {
        this.ignoreFailureResponse = bool;
    }

    public final void setName(@Nullable String str) {
        this.name = str;
    }

    public final void setSaveResponseData(@Nullable Boolean bool) {
        this.saveResponseData = bool;
    }
}
