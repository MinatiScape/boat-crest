package com.google.firebase.messaging;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
/* loaded from: classes10.dex */
public final /* synthetic */ class k implements Continuation {

    /* renamed from: a  reason: collision with root package name */
    public static final Continuation f11347a = new k();

    @Override // com.google.android.gms.tasks.Continuation
    public Object then(Task task) {
        return FcmBroadcastProcessor.e(task);
    }
}
