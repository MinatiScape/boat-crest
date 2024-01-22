package com.coveiot.android.remotecommandframework.alexa.request.model;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class Creds {
    @SerializedName("caPem")
    @Nullable
    private String caPem;
    @SerializedName("certPem")
    @Nullable
    private String certPem;
    @SerializedName("pvtKeyPem")
    @Nullable
    private String pvtKeyPem;

    @Nullable
    public final String getCaPem() {
        return this.caPem;
    }

    @Nullable
    public final String getCertPem() {
        return this.certPem;
    }

    @Nullable
    public final String getPvtKeyPem() {
        return this.pvtKeyPem;
    }

    public final void setCaPem(@Nullable String str) {
        this.caPem = str;
    }

    public final void setCertPem(@Nullable String str) {
        this.certPem = str;
    }

    public final void setPvtKeyPem(@Nullable String str) {
        this.pvtKeyPem = str;
    }
}
