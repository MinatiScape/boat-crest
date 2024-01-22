package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
/* loaded from: classes10.dex */
public final class zzfa {

    /* renamed from: a  reason: collision with root package name */
    public final zzfs f10152a;

    public zzfa(zzkn zzknVar) {
        this.f10152a = zzknVar.O();
    }

    @VisibleForTesting
    public final boolean a() {
        try {
            PackageManagerWrapper packageManager = Wrappers.packageManager(this.f10152a.zzau());
            if (packageManager != null) {
                return packageManager.getPackageInfo("com.android.vending", 128).versionCode >= 80837300;
            }
            this.f10152a.zzay().zzj().zza("Failed to get PackageManager for Install Referrer Play Store compatibility check");
            return false;
        } catch (Exception e) {
            this.f10152a.zzay().zzj().zzb("Failed to retrieve Play Store version for Install Referrer", e);
            return false;
        }
    }
}
