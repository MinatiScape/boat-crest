package com.google.mlkit.common.sdkinternal;

import com.google.mlkit.common.sdkinternal.Cleaner;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Set;
/* loaded from: classes10.dex */
public final class a extends PhantomReference implements Cleaner.Cleanable {
    public final Set h;
    public final Runnable i;

    public /* synthetic */ a(Object obj, ReferenceQueue referenceQueue, Set set, Runnable runnable, zzc zzcVar) {
        super(obj, referenceQueue);
        this.h = set;
        this.i = runnable;
    }

    @Override // com.google.mlkit.common.sdkinternal.Cleaner.Cleanable
    public final void clean() {
        if (this.h.remove(this)) {
            clear();
            this.i.run();
        }
    }
}
