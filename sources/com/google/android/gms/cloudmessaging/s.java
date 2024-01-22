package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
/* loaded from: classes6.dex */
public final /* synthetic */ class s implements SuccessContinuation {

    /* renamed from: a  reason: collision with root package name */
    public static final SuccessContinuation f8229a = new s();

    @Override // com.google.android.gms.tasks.SuccessContinuation
    public final Task then(Object obj) {
        return Rpc.b((Bundle) obj);
    }
}
