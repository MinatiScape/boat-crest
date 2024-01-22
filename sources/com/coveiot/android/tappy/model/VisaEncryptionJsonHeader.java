package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class VisaEncryptionJsonHeader implements Serializable {
    @Nullable
    private String alg;
    @Nullable
    private String channelSecurityContext;
    @Nullable
    private String enc;
    @Nullable
    private String iat;
    @Nullable
    private String kid;
    @Nullable
    private String typ;

    @Nullable
    public final String getAlg() {
        return this.alg;
    }

    @Nullable
    public final String getChannelSecurityContext() {
        return this.channelSecurityContext;
    }

    @Nullable
    public final String getEnc() {
        return this.enc;
    }

    @Nullable
    public final String getIat() {
        return this.iat;
    }

    @Nullable
    public final String getKid() {
        return this.kid;
    }

    @Nullable
    public final String getTyp() {
        return this.typ;
    }

    public final void setAlg(@Nullable String str) {
        this.alg = str;
    }

    public final void setChannelSecurityContext(@Nullable String str) {
        this.channelSecurityContext = str;
    }

    public final void setEnc(@Nullable String str) {
        this.enc = str;
    }

    public final void setIat(@Nullable String str) {
        this.iat = str;
    }

    public final void setKid(@Nullable String str) {
        this.kid = str;
    }

    public final void setTyp(@Nullable String str) {
        this.typ = str;
    }
}
