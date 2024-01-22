package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class EncryptionKey implements Serializable {
    @Nullable
    private String e;
    @Nullable
    private String kty;
    @Nullable
    private String n;
    @Nullable
    private String use;

    @Nullable
    public final String getE() {
        return this.e;
    }

    @Nullable
    public final String getKty() {
        return this.kty;
    }

    @Nullable
    public final String getN() {
        return this.n;
    }

    @Nullable
    public final String getUse() {
        return this.use;
    }

    public final void setE(@Nullable String str) {
        this.e = str;
    }

    public final void setKty(@Nullable String str) {
        this.kty = str;
    }

    public final void setN(@Nullable String str) {
        this.n = str;
    }

    public final void setUse(@Nullable String str) {
        this.use = str;
    }
}
