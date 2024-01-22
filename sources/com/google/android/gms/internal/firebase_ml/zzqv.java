package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzqg;
/* loaded from: classes7.dex */
public final class zzqv implements zzqg.zzb {
    public static final GmsLogger b = new GmsLogger("MlStatsLogger", "");

    /* renamed from: a  reason: collision with root package name */
    public final ClearcutLogger f8799a;

    public zzqv(Context context) {
        this.f8799a = ClearcutLogger.anonymousLogger(context, "FIREBASE_ML_SDK");
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzqg.zzb
    public final void zza(zzns.zzad zzadVar) {
        GmsLogger gmsLogger = b;
        String valueOf = String.valueOf(zzadVar);
        StringBuilder sb = new StringBuilder(valueOf.length() + 30);
        sb.append("Logging FirebaseMlSdkLogEvent ");
        sb.append(valueOf);
        gmsLogger.d("MlStatsLogger", sb.toString());
        this.f8799a.newEvent(zzadVar.toByteArray()).log();
    }
}
