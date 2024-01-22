package com.google.android.recaptcha.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class zzao implements zzaj {
    @NotNull
    public static final zzak zza = new zzak(null);
    @Nullable
    private static Timer zzb;
    @NotNull
    private final zzap zzc;
    @NotNull
    private final CoroutineScope zzd;
    @NotNull
    private final zzad zze;

    public /* synthetic */ zzao(Context context, zzap zzapVar, CoroutineScope coroutineScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        zzad zzadVar;
        zzp zzpVar = zzp.zza;
        CoroutineScope zza2 = zzp.zza();
        this.zzc = zzapVar;
        this.zzd = zza2;
        zzadVar = zzad.zzc;
        zzadVar = zzadVar == null ? new zzad(context, null) : zzadVar;
        zzad.zzc = zzadVar;
        this.zze = zzadVar;
        zzh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzg() {
        zzmy zzk;
        int zzH;
        int i;
        for (List<zzae> list : CollectionsKt___CollectionsKt.windowed(this.zze.zzd(), 20, 20, true)) {
            zzlq zzi = zzlr.zzi();
            ArrayList arrayList = new ArrayList();
            for (zzae zzaeVar : list) {
                try {
                    zzk = zzmy.zzk(zzek.zzg().zzj(zzaeVar.zzc()));
                    zzH = zzk.zzH();
                    i = zzH - 1;
                } catch (Exception unused) {
                    this.zze.zzf(zzaeVar);
                }
                if (zzH != 0) {
                    if (i == 0) {
                        zzi.zzp(zzk.zzf());
                    } else if (i == 1) {
                        zzi.zzq(zzk.zzg());
                    }
                    arrayList.add(zzaeVar);
                } else {
                    throw null;
                }
            }
            if (zzi.zzd() + zzi.zze() != 0) {
                if (this.zzc.zza(((zzlr) zzi.zzj()).zzd())) {
                    this.zze.zza(arrayList);
                }
            }
        }
    }

    private final void zzh() {
        if (zzb == null) {
            Timer timer = new Timer();
            zzb = timer;
            timer.schedule(new zzal(this), 120000L, 120000L);
        }
    }

    public final void zzf(@NotNull zzmy zzmyVar) {
        e.e(this.zzd, null, null, new zzan(zzmyVar, this, null), 3, null);
        zzh();
    }
}
