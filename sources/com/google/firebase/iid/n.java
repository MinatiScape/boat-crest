package com.google.firebase.iid;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
/* loaded from: classes10.dex */
public final /* synthetic */ class n implements Continuation {

    /* renamed from: a  reason: collision with root package name */
    public static final Continuation f11298a = new n();

    @Override // com.google.android.gms.tasks.Continuation
    public Object then(Task task) {
        String token;
        token = ((InstanceIdResult) task.getResult()).getToken();
        return token;
    }
}
