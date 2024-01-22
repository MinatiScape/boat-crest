package com.google.firebase;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Strings;
/* loaded from: classes10.dex */
public final class FirebaseOptions {

    /* renamed from: a  reason: collision with root package name */
    public final String f11068a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;

    /* loaded from: classes10.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f11069a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;

        public Builder() {
        }

        @NonNull
        public FirebaseOptions build() {
            return new FirebaseOptions(this.b, this.f11069a, this.c, this.d, this.e, this.f, this.g);
        }

        @NonNull
        public Builder setApiKey(@NonNull String str) {
            this.f11069a = Preconditions.checkNotEmpty(str, "ApiKey must be set.");
            return this;
        }

        @NonNull
        public Builder setApplicationId(@NonNull String str) {
            this.b = Preconditions.checkNotEmpty(str, "ApplicationId must be set.");
            return this;
        }

        @NonNull
        public Builder setDatabaseUrl(@Nullable String str) {
            this.c = str;
            return this;
        }

        @NonNull
        @KeepForSdk
        public Builder setGaTrackingId(@Nullable String str) {
            this.d = str;
            return this;
        }

        @NonNull
        public Builder setGcmSenderId(@Nullable String str) {
            this.e = str;
            return this;
        }

        @NonNull
        public Builder setProjectId(@Nullable String str) {
            this.g = str;
            return this;
        }

        @NonNull
        public Builder setStorageBucket(@Nullable String str) {
            this.f = str;
            return this;
        }

        public Builder(@NonNull FirebaseOptions firebaseOptions) {
            this.b = firebaseOptions.b;
            this.f11069a = firebaseOptions.f11068a;
            this.c = firebaseOptions.c;
            this.d = firebaseOptions.d;
            this.e = firebaseOptions.e;
            this.f = firebaseOptions.f;
            this.g = firebaseOptions.g;
        }
    }

    @Nullable
    public static FirebaseOptions fromResource(@NonNull Context context) {
        StringResourceValueReader stringResourceValueReader = new StringResourceValueReader(context);
        String string = stringResourceValueReader.getString("google_app_id");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return new FirebaseOptions(string, stringResourceValueReader.getString("google_api_key"), stringResourceValueReader.getString("firebase_database_url"), stringResourceValueReader.getString("ga_trackingId"), stringResourceValueReader.getString("gcm_defaultSenderId"), stringResourceValueReader.getString("google_storage_bucket"), stringResourceValueReader.getString("project_id"));
    }

    public boolean equals(Object obj) {
        if (obj instanceof FirebaseOptions) {
            FirebaseOptions firebaseOptions = (FirebaseOptions) obj;
            return Objects.equal(this.b, firebaseOptions.b) && Objects.equal(this.f11068a, firebaseOptions.f11068a) && Objects.equal(this.c, firebaseOptions.c) && Objects.equal(this.d, firebaseOptions.d) && Objects.equal(this.e, firebaseOptions.e) && Objects.equal(this.f, firebaseOptions.f) && Objects.equal(this.g, firebaseOptions.g);
        }
        return false;
    }

    @NonNull
    public String getApiKey() {
        return this.f11068a;
    }

    @NonNull
    public String getApplicationId() {
        return this.b;
    }

    @Nullable
    public String getDatabaseUrl() {
        return this.c;
    }

    @Nullable
    @KeepForSdk
    public String getGaTrackingId() {
        return this.d;
    }

    @Nullable
    public String getGcmSenderId() {
        return this.e;
    }

    @Nullable
    public String getProjectId() {
        return this.g;
    }

    @Nullable
    public String getStorageBucket() {
        return this.f;
    }

    public int hashCode() {
        return Objects.hashCode(this.b, this.f11068a, this.c, this.d, this.e, this.f, this.g);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("applicationId", this.b).add("apiKey", this.f11068a).add("databaseUrl", this.c).add("gcmSenderId", this.e).add("storageBucket", this.f).add("projectId", this.g).toString();
    }

    public FirebaseOptions(@NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
        Preconditions.checkState(!Strings.isEmptyOrWhitespace(str), "ApplicationId must be set.");
        this.b = str;
        this.f11068a = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
    }
}
