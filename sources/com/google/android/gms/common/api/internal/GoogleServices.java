package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.R;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.internal.zzah;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import javax.annotation.concurrent.GuardedBy;
@KeepForSdk
@Deprecated
/* loaded from: classes6.dex */
public final class GoogleServices {
    public static final Object e = new Object();
    @Nullable
    @GuardedBy("lock")
    public static GoogleServices f;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final String f8255a;
    public final Status b;
    public final boolean c;
    public final boolean d;

    @VisibleForTesting
    @KeepForSdk
    public GoogleServices(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", TypedValues.Custom.S_INT, resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
        if (identifier != 0) {
            int integer = resources.getInteger(identifier);
            boolean z = integer == 0;
            r2 = integer != 0;
            this.d = z;
        } else {
            this.d = false;
        }
        this.c = r2;
        String zzb = zzah.zzb(context);
        zzb = zzb == null ? new StringResourceValueReader(context).getString("google_app_id") : zzb;
        if (TextUtils.isEmpty(zzb)) {
            this.b = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.f8255a = null;
            return;
        }
        this.f8255a = zzb;
        this.b = Status.RESULT_SUCCESS;
    }

    @VisibleForTesting
    @KeepForSdk
    public GoogleServices(String str, boolean z) {
        this.f8255a = str;
        this.b = Status.RESULT_SUCCESS;
        this.c = z;
        this.d = !z;
    }

    @KeepForSdk
    public static GoogleServices b(String str) {
        GoogleServices googleServices;
        synchronized (e) {
            googleServices = f;
            if (googleServices == null) {
                throw new IllegalStateException("Initialize must be called before " + str + ".");
            }
        }
        return googleServices;
    }

    @Nullable
    @KeepForSdk
    public static String getGoogleAppId() {
        return b("getGoogleAppId").f8255a;
    }

    @NonNull
    @KeepForSdk
    public static Status initialize(@NonNull Context context) {
        Status status;
        Preconditions.checkNotNull(context, "Context must not be null.");
        synchronized (e) {
            if (f == null) {
                f = new GoogleServices(context);
            }
            status = f.b;
        }
        return status;
    }

    @KeepForSdk
    public static boolean isMeasurementEnabled() {
        GoogleServices b = b("isMeasurementEnabled");
        return b.b.isSuccess() && b.c;
    }

    @KeepForSdk
    public static boolean isMeasurementExplicitlyDisabled() {
        return b("isMeasurementExplicitlyDisabled").d;
    }

    @VisibleForTesting
    @KeepForSdk
    public Status a(String str) {
        String str2 = this.f8255a;
        if (str2 == null || str2.equals(str)) {
            return Status.RESULT_SUCCESS;
        }
        String str3 = this.f8255a;
        return new Status(10, "Initialize was called with two different Google App IDs.  Only the first app ID will be used: '" + str3 + "'.");
    }

    @NonNull
    @ResultIgnorabilityUnspecified
    @KeepForSdk
    public static Status initialize(@NonNull Context context, @NonNull String str, boolean z) {
        Preconditions.checkNotNull(context, "Context must not be null.");
        Preconditions.checkNotEmpty(str, "App ID must be nonempty.");
        synchronized (e) {
            GoogleServices googleServices = f;
            if (googleServices != null) {
                return googleServices.a(str);
            }
            GoogleServices googleServices2 = new GoogleServices(str, z);
            f = googleServices2;
            return googleServices2.b;
        }
    }
}
