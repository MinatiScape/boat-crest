package com.google.android.play.core.integrity;

import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
public abstract class IntegrityTokenRequest {

    /* loaded from: classes10.dex */
    public static abstract class Builder {
        public abstract IntegrityTokenRequest build();

        public abstract Builder setCloudProjectNumber(long j);

        public abstract Builder setNonce(String str);
    }

    public static Builder builder() {
        return new a();
    }

    @Nullable
    public abstract Long cloudProjectNumber();

    public abstract String nonce();
}
