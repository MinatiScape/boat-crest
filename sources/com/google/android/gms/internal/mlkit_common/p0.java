package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import androidx.core.content.PermissionChecker;
/* loaded from: classes8.dex */
public final class p0 extends zzs {
    @Override // com.google.android.gms.internal.mlkit_common.zzs
    public final int zza(Context context, zzr zzrVar, boolean z) {
        return (zzrVar.zza.getAuthority().lastIndexOf(64) < 0 || PermissionChecker.checkSelfPermission(context, "android.permission.INTERACT_ACROSS_USERS") != 0) ? 3 : 2;
    }
}
