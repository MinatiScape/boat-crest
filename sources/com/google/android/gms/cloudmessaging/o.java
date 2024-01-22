package com.google.android.gms.cloudmessaging;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
/* loaded from: classes6.dex */
public final /* synthetic */ class o implements Continuation {

    /* renamed from: a  reason: collision with root package name */
    public static final Continuation f8227a = new o();

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task task) {
        return Rpc.a(task);
    }
}
