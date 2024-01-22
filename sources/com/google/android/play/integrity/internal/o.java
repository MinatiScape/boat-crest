package com.google.android.play.integrity.internal;

import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes10.dex */
public final class o extends l {
    public final /* synthetic */ l h;
    public final /* synthetic */ v i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(v vVar, TaskCompletionSource taskCompletionSource, l lVar) {
        super(taskCompletionSource);
        this.i = vVar;
        this.h = lVar;
    }

    @Override // com.google.android.play.integrity.internal.l
    public final void b() {
        v.m(this.i, this.h);
    }
}
