package com.google.firebase.messaging;

import android.content.Intent;
import android.os.Binder;
import android.os.Process;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.z0;
/* loaded from: classes10.dex */
public class w0 extends Binder {

    /* renamed from: a  reason: collision with root package name */
    public final a f11368a;

    /* loaded from: classes10.dex */
    public interface a {
        Task<Void> a(Intent intent);
    }

    public w0(a aVar) {
        this.f11368a = aVar;
    }

    public void b(final z0.a aVar) {
        if (Binder.getCallingUid() == Process.myUid()) {
            if (Log.isLoggable(Constants.TAG, 3)) {
                Log.d(Constants.TAG, "service received new intent via bind strategy");
            }
            this.f11368a.a(aVar.f11373a).addOnCompleteListener(u0.h, new OnCompleteListener(aVar) { // from class: com.google.firebase.messaging.v0

                /* renamed from: a  reason: collision with root package name */
                public final z0.a f11366a;

                {
                    this.f11366a = aVar;
                }

                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task task) {
                    this.f11366a.b();
                }
            });
            return;
        }
        throw new SecurityException("Binding only allowed within app");
    }
}
