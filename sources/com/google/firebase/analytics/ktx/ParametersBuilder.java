package com.google.firebase.analytics.ktx;

import android.os.Bundle;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\bJ\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\tJ\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002J#\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\t0\n¢\u0006\u0004\b\u0007\u0010\u000bR\u0019\u0010\f\u001a\u00020\t8\u0006@\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/google/firebase/analytics/ktx/ParametersBuilder;", "", "", Constants.KEY_KEY, "", "value", "", "param", "", "Landroid/os/Bundle;", "", "(Ljava/lang/String;[Landroid/os/Bundle;)V", "bundle", "Landroid/os/Bundle;", "getBundle", "()Landroid/os/Bundle;", "<init>", "()V", "java.com.google.android.libraries.firebase.firebase_analytics_ktx_granule"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes10.dex */
public final class ParametersBuilder {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Bundle f11086a = new Bundle();

    @NotNull
    public final Bundle getBundle() {
        return this.f11086a;
    }

    public final void param(@NotNull String key, double d) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.f11086a.putDouble(key, d);
    }

    public final void param(@NotNull String key, long j) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.f11086a.putLong(key, j);
    }

    public final void param(@NotNull String key, @NotNull Bundle value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.f11086a.putBundle(key, value);
    }

    public final void param(@NotNull String key, @NotNull String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.f11086a.putString(key, value);
    }

    public final void param(@NotNull String key, @NotNull Bundle[] value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.f11086a.putParcelableArray(key, value);
    }
}
