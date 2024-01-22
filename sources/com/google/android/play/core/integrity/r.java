package com.google.android.play.core.integrity;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class r extends com.google.android.play.integrity.internal.l {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ byte[] f10462a;
    public final /* synthetic */ Long b;
    public final /* synthetic */ TaskCompletionSource c;
    public final /* synthetic */ IntegrityTokenRequest d;
    public final /* synthetic */ t e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(t tVar, TaskCompletionSource taskCompletionSource, byte[] bArr, Long l, TaskCompletionSource taskCompletionSource2, IntegrityTokenRequest integrityTokenRequest) {
        super(taskCompletionSource);
        this.e = tVar;
        this.f10462a = bArr;
        this.b = l;
        this.c = taskCompletionSource2;
        this.d = integrityTokenRequest;
    }

    @Override // com.google.android.play.integrity.internal.l
    public final void a(Exception exc) {
        if (exc instanceof com.google.android.play.integrity.internal.w) {
            super.a(new IntegrityServiceException(-9, exc));
        } else {
            super.a(exc);
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [android.os.IInterface, com.google.android.play.integrity.internal.h] */
    @Override // com.google.android.play.integrity.internal.l
    public final void b() {
        com.google.android.play.integrity.internal.k kVar;
        try {
            this.e.f10464a.e().c(t.a(this.e, this.f10462a, this.b), new s(this.e, this.c));
        } catch (RemoteException e) {
            kVar = this.e.b;
            kVar.c(e, "requestIntegrityToken(%s)", this.d);
            this.c.trySetException(new IntegrityServiceException(-100, e));
        }
    }
}
