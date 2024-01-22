package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AgpsUpdateRequest extends BleBaseRequest {
    @Nullable
    public String f;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public String f3472a;

        @NotNull
        public final AgpsUpdateRequest build() {
            AgpsUpdateRequest agpsUpdateRequest = new AgpsUpdateRequest();
            agpsUpdateRequest.setAgpsFilePath(this.f3472a);
            return agpsUpdateRequest;
        }

        @Nullable
        public final String getAgpsFilePath() {
            return this.f3472a;
        }

        /* renamed from: setAgpsFilePath  reason: collision with other method in class */
        public final void m34setAgpsFilePath(@Nullable String str) {
            this.f3472a = str;
        }

        @NotNull
        public final Builder setAgpsFilePath(@Nullable String str) {
            this.f3472a = str;
            return this;
        }
    }

    @Nullable
    public final String getAgpsFilePath() {
        return this.f;
    }

    public final void setAgpsFilePath(@Nullable String str) {
        this.f = str;
    }
}
