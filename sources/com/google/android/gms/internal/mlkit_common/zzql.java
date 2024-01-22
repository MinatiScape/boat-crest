package com.google.android.gms.internal.mlkit_common;

import android.os.SystemClock;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
/* loaded from: classes8.dex */
public final class zzql {

    /* renamed from: a  reason: collision with root package name */
    public static final GmsLogger f9360a = new GmsLogger("RemoteModelUtils", "");

    @WorkerThread
    public static zzlu zza(RemoteModel remoteModel, SharedPrefManager sharedPrefManager, zzqb zzqbVar) {
        zzlw zzlwVar;
        ModelType zzb = zzqbVar.zzb();
        String modelHash = remoteModel.getModelHash();
        zzma zzmaVar = new zzma();
        zzlv zzlvVar = new zzlv();
        zzlvVar.zzc(remoteModel.getModelNameForBackend());
        zzlvVar.zzd(zzlx.CLOUD);
        zzlvVar.zza(zzag.zzb(modelHash));
        int ordinal = zzb.ordinal();
        if (ordinal == 2) {
            zzlwVar = zzlw.BASE_TRANSLATE;
        } else if (ordinal == 4) {
            zzlwVar = zzlw.CUSTOM;
        } else if (ordinal != 5) {
            zzlwVar = zzlw.TYPE_UNKNOWN;
        } else {
            zzlwVar = zzlw.BASE_DIGITAL_INK;
        }
        zzlvVar.zzb(zzlwVar);
        zzmaVar.zzb(zzlvVar.zzg());
        zzmd zzc = zzmaVar.zzc();
        zzlr zzlrVar = new zzlr();
        zzlrVar.zzd(zzqbVar.zzc());
        zzlrVar.zzc(zzqbVar.zzd());
        zzlrVar.zzb(Long.valueOf(zzqbVar.zza()));
        zzlrVar.zzf(zzc);
        if (zzqbVar.zzg()) {
            long modelDownloadBeginTimeMs = sharedPrefManager.getModelDownloadBeginTimeMs(remoteModel);
            if (modelDownloadBeginTimeMs == 0) {
                f9360a.w("RemoteModelUtils", "Model downloaded without its beginning time recorded.");
            } else {
                long modelFirstUseTimeMs = sharedPrefManager.getModelFirstUseTimeMs(remoteModel);
                if (modelFirstUseTimeMs == 0) {
                    modelFirstUseTimeMs = SystemClock.elapsedRealtime();
                    sharedPrefManager.setModelFirstUseTimeMs(remoteModel, modelFirstUseTimeMs);
                }
                zzlrVar.zzg(Long.valueOf(modelFirstUseTimeMs - modelDownloadBeginTimeMs));
            }
        }
        if (zzqbVar.zzf()) {
            long modelDownloadBeginTimeMs2 = sharedPrefManager.getModelDownloadBeginTimeMs(remoteModel);
            if (modelDownloadBeginTimeMs2 == 0) {
                f9360a.w("RemoteModelUtils", "Model downloaded without its beginning time recorded.");
            } else {
                zzlrVar.zze(Long.valueOf(SystemClock.elapsedRealtime() - modelDownloadBeginTimeMs2));
            }
        }
        return zzlrVar.zzi();
    }
}
