package com.google.firebase.messaging;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
/* loaded from: classes10.dex */
public final /* synthetic */ class i implements Continuation {

    /* renamed from: a  reason: collision with root package name */
    public static final Continuation f11344a = new i();

    @Override // com.google.android.gms.tasks.Continuation
    public Object then(Task task) {
        return FcmBroadcastProcessor.c(task);
    }
}
