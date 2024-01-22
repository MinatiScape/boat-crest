package com.google.mlkit.common.sdkinternal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes10.dex */
public class Cleaner {

    /* renamed from: a  reason: collision with root package name */
    public final ReferenceQueue f11583a = new ReferenceQueue();
    public final Set b = Collections.synchronizedSet(new HashSet());

    /* loaded from: classes10.dex */
    public interface Cleanable {
        @KeepForSdk
        void clean();
    }

    @NonNull
    @KeepForSdk
    public static Cleaner create() {
        Cleaner cleaner = new Cleaner();
        cleaner.register(cleaner, new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zzb
            @Override // java.lang.Runnable
            public final void run() {
            }
        });
        final ReferenceQueue referenceQueue = cleaner.f11583a;
        final Set set = cleaner.b;
        Thread thread = new Thread(new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zza
            @Override // java.lang.Runnable
            public final void run() {
                ReferenceQueue referenceQueue2 = referenceQueue;
                Set set2 = set;
                while (!set2.isEmpty()) {
                    try {
                        ((a) referenceQueue2.remove()).clean();
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }, "MlKitCleaner");
        thread.setDaemon(true);
        thread.start();
        return cleaner;
    }

    @NonNull
    @KeepForSdk
    public Cleanable register(@NonNull Object obj, @NonNull Runnable runnable) {
        a aVar = new a(obj, this.f11583a, this.b, runnable, null);
        this.b.add(aVar);
        return aVar;
    }
}
