package com.google.android.play.core.integrity;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.mappls.android.lms.MapplsLMSDbAdapter;
/* loaded from: classes10.dex */
final class s extends com.google.android.play.integrity.internal.i {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ t f10463a;
    private final com.google.android.play.integrity.internal.k b = new com.google.android.play.integrity.internal.k("OnRequestIntegrityTokenCallback");
    private final TaskCompletionSource c;

    public s(t tVar, TaskCompletionSource taskCompletionSource) {
        this.f10463a = tVar;
        this.c = taskCompletionSource;
    }

    @Override // com.google.android.play.integrity.internal.j
    public final void b(Bundle bundle) {
        this.f10463a.f10464a.r(this.c);
        this.b.d("onRequestIntegrityToken", new Object[0]);
        int i = bundle.getInt("error");
        if (i != 0) {
            this.c.trySetException(new IntegrityServiceException(i, null));
            return;
        }
        String string = bundle.getString(MapplsLMSDbAdapter.KEY_TOKEN);
        if (string == null) {
            this.c.trySetException(new IntegrityServiceException(-100, null));
            return;
        }
        TaskCompletionSource taskCompletionSource = this.c;
        d dVar = new d();
        dVar.a(string);
        taskCompletionSource.trySetResult(dVar.b());
    }
}
