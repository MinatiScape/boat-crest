package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
/* loaded from: classes8.dex */
public final class v extends r0 {
    public final /* synthetic */ String l;
    public final /* synthetic */ String m;
    public final /* synthetic */ Context n;
    public final /* synthetic */ Bundle o;
    public final /* synthetic */ zzee p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(zzee zzeeVar, String str, String str2, Context context, Bundle bundle) {
        super(zzeeVar, true);
        this.p = zzeeVar;
        this.l = str;
        this.m = str2;
        this.n = context;
        this.o = bundle;
    }

    @Override // com.google.android.gms.internal.measurement.r0
    public final void a() {
        boolean f;
        String str;
        String str2;
        String str3;
        zzcc zzccVar;
        int remoteVersion;
        zzcc zzccVar2;
        String str4;
        String str5;
        try {
            zzee zzeeVar = this.p;
            f = zzee.f(this.l, this.m);
            if (f) {
                String str6 = this.m;
                String str7 = this.l;
                str5 = this.p.f8949a;
                str2 = str7;
                str3 = str6;
                str = str5;
            } else {
                str = null;
                str2 = null;
                str3 = null;
            }
            Preconditions.checkNotNull(this.n);
            zzee zzeeVar2 = this.p;
            zzeeVar2.g = zzeeVar2.zzf(this.n, true);
            zzccVar = this.p.g;
            if (zzccVar == null) {
                str4 = this.p.f8949a;
                Log.w(str4, "Failed to connect to measurement client.");
                return;
            }
            int localVersion = DynamiteModule.getLocalVersion(this.n, ModuleDescriptor.MODULE_ID);
            zzcl zzclVar = new zzcl(42097L, Math.max(localVersion, remoteVersion), DynamiteModule.getRemoteVersion(this.n, ModuleDescriptor.MODULE_ID) < localVersion, str, str2, str3, this.o, com.google.android.gms.measurement.internal.zzfk.zza(this.n));
            zzccVar2 = this.p.g;
            ((zzcc) Preconditions.checkNotNull(zzccVar2)).initialize(ObjectWrapper.wrap(this.n), zzclVar, this.h);
        } catch (Exception e) {
            this.p.c(e, true, false);
        }
    }
}
