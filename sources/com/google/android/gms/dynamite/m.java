package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;
/* loaded from: classes6.dex */
public final class m implements DynamiteModule.VersionPolicy.IVersions {

    /* renamed from: a  reason: collision with root package name */
    public final int f8392a;

    public m(int i, int i2) {
        this.f8392a = i;
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
    public final int zza(Context context, String str) {
        return this.f8392a;
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.IVersions
    public final int zzb(Context context, String str, boolean z) {
        return 0;
    }
}
