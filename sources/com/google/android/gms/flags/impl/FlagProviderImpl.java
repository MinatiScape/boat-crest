package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
@DynamiteApi
/* loaded from: classes6.dex */
public class FlagProviderImpl extends com.google.android.gms.flags.zzd {

    /* renamed from: a  reason: collision with root package name */
    public boolean f8470a = false;
    public SharedPreferences b;

    @Override // com.google.android.gms.flags.zzc
    public boolean getBooleanFlagValue(String str, boolean z, int i) {
        return !this.f8470a ? z : zzb.zza(this.b, str, Boolean.valueOf(z)).booleanValue();
    }

    @Override // com.google.android.gms.flags.zzc
    public int getIntFlagValue(String str, int i, int i2) {
        return !this.f8470a ? i : zzd.zza(this.b, str, Integer.valueOf(i)).intValue();
    }

    @Override // com.google.android.gms.flags.zzc
    public long getLongFlagValue(String str, long j, int i) {
        return !this.f8470a ? j : zzf.zza(this.b, str, Long.valueOf(j)).longValue();
    }

    @Override // com.google.android.gms.flags.zzc
    public String getStringFlagValue(String str, String str2, int i) {
        return !this.f8470a ? str2 : zzh.zza(this.b, str, str2);
    }

    @Override // com.google.android.gms.flags.zzc
    public void init(IObjectWrapper iObjectWrapper) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        if (this.f8470a) {
            return;
        }
        try {
            this.b = zzj.zza(context.createPackageContext("com.google.android.gms", 0));
            this.f8470a = true;
        } catch (PackageManager.NameNotFoundException unused) {
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("FlagProviderImpl", valueOf.length() != 0 ? "Could not retrieve sdk flags, continuing with defaults: ".concat(valueOf) : new String("Could not retrieve sdk flags, continuing with defaults: "));
        }
    }
}
