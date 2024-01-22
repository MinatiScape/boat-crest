package com.google.mlkit.common.sdkinternal;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_common.zzle;
import com.google.android.gms.internal.mlkit_common.zzlf;
import com.google.android.gms.internal.mlkit_common.zzln;
import com.google.android.gms.internal.mlkit_common.zzlo;
import com.google.android.gms.internal.mlkit_common.zzpz;
import com.google.android.gms.internal.mlkit_common.zzqc;
import com.google.android.gms.internal.mlkit_common.zzqk;
import com.google.mlkit.common.sdkinternal.Cleaner;
import java.io.Closeable;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
@KeepForSdk
/* loaded from: classes10.dex */
public class CloseGuard implements Closeable {
    @KeepForSdk
    public static final int API_TRANSLATE = 1;
    public final AtomicBoolean h = new AtomicBoolean();
    public final String i;
    public final Cleaner.Cleanable j;

    @KeepForSdk
    /* loaded from: classes10.dex */
    public static class Factory {

        /* renamed from: a  reason: collision with root package name */
        public final Cleaner f11584a;

        public Factory(@NonNull Cleaner cleaner) {
            this.f11584a = cleaner;
        }

        @NonNull
        @KeepForSdk
        public CloseGuard create(@NonNull Object obj, int i, @NonNull Runnable runnable) {
            return new CloseGuard(obj, i, this.f11584a, runnable, zzqk.zzb("common"));
        }
    }

    public CloseGuard(Object obj, final int i, Cleaner cleaner, final Runnable runnable, final zzpz zzpzVar) {
        this.i = obj.toString();
        this.j = cleaner.register(obj, new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zze
            @Override // java.lang.Runnable
            public final void run() {
                CloseGuard.this.a(i, zzpzVar, runnable);
            }
        });
    }

    public final /* synthetic */ void a(int i, zzpz zzpzVar, Runnable runnable) {
        if (!this.h.get()) {
            Log.e("MlKitCloseGuard", String.format(Locale.ENGLISH, "%s has not been closed", this.i));
            zzlo zzloVar = new zzlo();
            zzlf zzlfVar = new zzlf();
            zzlfVar.zzb(zzle.zzb(i));
            zzloVar.zzh(zzlfVar.zzc());
            zzpzVar.zzd(zzqc.zzf(zzloVar), zzln.HANDLE_LEAKED);
        }
        runnable.run();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.h.set(true);
        this.j.clean();
    }
}
