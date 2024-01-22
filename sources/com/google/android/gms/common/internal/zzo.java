package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
/* loaded from: classes6.dex */
public final class zzo {
    public static final Uri f = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").build();
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final String f8346a;
    @Nullable
    public final String b;
    @Nullable
    public final ComponentName c;
    public final int d;
    public final boolean e;

    public zzo(ComponentName componentName, int i) {
        this.f8346a = null;
        this.b = null;
        Preconditions.checkNotNull(componentName);
        this.c = componentName;
        this.d = 4225;
        this.e = false;
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzo) {
            zzo zzoVar = (zzo) obj;
            return Objects.equal(this.f8346a, zzoVar.f8346a) && Objects.equal(this.b, zzoVar.b) && Objects.equal(this.c, zzoVar.c) && this.e == zzoVar.e;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f8346a, this.b, this.c, 4225, Boolean.valueOf(this.e));
    }

    public final String toString() {
        String str = this.f8346a;
        if (str == null) {
            Preconditions.checkNotNull(this.c);
            return this.c.flattenToString();
        }
        return str;
    }

    @Nullable
    public final ComponentName zza() {
        return this.c;
    }

    public final Intent zzb(Context context) {
        Bundle bundle;
        if (this.f8346a != null) {
            if (this.e) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("serviceActionBundleKey", this.f8346a);
                try {
                    bundle = context.getContentResolver().call(f, "serviceIntentCall", (String) null, bundle2);
                } catch (IllegalArgumentException e) {
                    Log.w("ConnectionStatusConfig", "Dynamic intent resolution failed: ".concat(e.toString()));
                    bundle = null;
                }
                r2 = bundle != null ? (Intent) bundle.getParcelable("serviceResponseIntentKey") : null;
                if (r2 == null) {
                    Log.w("ConnectionStatusConfig", "Dynamic lookup for intent failed for action: ".concat(String.valueOf(this.f8346a)));
                }
            }
            return r2 == null ? new Intent(this.f8346a).setPackage(this.b) : r2;
        }
        return new Intent().setComponent(this.c);
    }

    @Nullable
    public final String zzc() {
        return this.b;
    }

    public zzo(String str, int i, boolean z) {
        this(str, "com.google.android.gms", 4225, false);
    }

    public zzo(String str, String str2, int i, boolean z) {
        Preconditions.checkNotEmpty(str);
        this.f8346a = str;
        Preconditions.checkNotEmpty(str2);
        this.b = str2;
        this.c = null;
        this.d = 4225;
        this.e = z;
    }
}
