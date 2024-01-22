package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
/* loaded from: classes6.dex */
public final class d implements OnCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8272a;
    public final /* synthetic */ zaad b;

    public d(zaad zaadVar, TaskCompletionSource taskCompletionSource) {
        this.b = zaadVar;
        this.f8272a = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(@NonNull Task task) {
        Map map;
        map = this.b.b;
        map.remove(this.f8272a);
    }
}
