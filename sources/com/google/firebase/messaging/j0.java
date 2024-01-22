package com.google.firebase.messaging;

import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public class j0 {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f11346a;
    @GuardedBy("this")
    public final Map<String, Task<String>> b = new ArrayMap();

    /* loaded from: classes10.dex */
    public interface a {
        Task<String> start();
    }

    public j0(Executor executor) {
        this.f11346a = executor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized Task<String> a(final String str, a aVar) {
        Task<String> task = this.b.get(str);
        if (task != null) {
            if (Log.isLoggable(Constants.TAG, 3)) {
                String valueOf = String.valueOf(str);
                Log.d(Constants.TAG, valueOf.length() != 0 ? "Joining ongoing request for: ".concat(valueOf) : new String("Joining ongoing request for: "));
            }
            return task;
        }
        if (Log.isLoggable(Constants.TAG, 3)) {
            String valueOf2 = String.valueOf(str);
            Log.d(Constants.TAG, valueOf2.length() != 0 ? "Making new request for: ".concat(valueOf2) : new String("Making new request for: "));
        }
        Task continueWithTask = aVar.start().continueWithTask(this.f11346a, new Continuation(this, str) { // from class: com.google.firebase.messaging.i0

            /* renamed from: a  reason: collision with root package name */
            public final j0 f11345a;
            public final String b;

            {
                this.f11345a = this;
                this.b = str;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public Object then(Task task2) {
                this.f11345a.b(this.b, task2);
                return task2;
            }
        });
        this.b.put(str, continueWithTask);
        return continueWithTask;
    }

    public final /* synthetic */ Task b(String str, Task task) throws Exception {
        synchronized (this) {
            this.b.remove(str);
        }
        return task;
    }
}
