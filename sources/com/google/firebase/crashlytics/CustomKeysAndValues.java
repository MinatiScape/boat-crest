package com.google.firebase.crashlytics;

import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class CustomKeysAndValues {

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, String> f11116a;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Map<String, String> f11117a = new HashMap();

        @NonNull
        public CustomKeysAndValues build() {
            return new CustomKeysAndValues(this);
        }

        @NonNull
        public Builder putBoolean(@NonNull String str, boolean z) {
            this.f11117a.put(str, Boolean.toString(z));
            return this;
        }

        @NonNull
        public Builder putDouble(@NonNull String str, double d) {
            this.f11117a.put(str, Double.toString(d));
            return this;
        }

        @NonNull
        public Builder putFloat(@NonNull String str, float f) {
            this.f11117a.put(str, Float.toString(f));
            return this;
        }

        @NonNull
        public Builder putInt(@NonNull String str, int i) {
            this.f11117a.put(str, Integer.toString(i));
            return this;
        }

        @NonNull
        public Builder putLong(@NonNull String str, long j) {
            this.f11117a.put(str, Long.toString(j));
            return this;
        }

        @NonNull
        public Builder putString(@NonNull String str, @NonNull String str2) {
            this.f11117a.put(str, str2);
            return this;
        }
    }

    public CustomKeysAndValues(@NonNull Builder builder) {
        this.f11116a = builder.f11117a;
    }
}
