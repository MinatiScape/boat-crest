package com.google.firebase.iid;

import android.util.Log;
import android.util.Pair;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public class p {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f11300a;
    @GuardedBy("this")
    public final Map<Pair<String, String>, Task<InstanceIdResult>> b = new ArrayMap();

    /* loaded from: classes10.dex */
    public interface a {
        Task<InstanceIdResult> start();
    }

    public p(Executor executor) {
        this.f11300a = executor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized Task<InstanceIdResult> a(String str, String str2, a aVar) {
        final Pair pair = new Pair(str, str2);
        Task<InstanceIdResult> task = this.b.get(pair);
        if (task != null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(pair);
                StringBuilder sb = new StringBuilder(valueOf.length() + 29);
                sb.append("Joining ongoing request for: ");
                sb.append(valueOf);
                Log.d("FirebaseInstanceId", sb.toString());
            }
            return task;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf2 = String.valueOf(pair);
            StringBuilder sb2 = new StringBuilder(valueOf2.length() + 24);
            sb2.append("Making new request for: ");
            sb2.append(valueOf2);
            Log.d("FirebaseInstanceId", sb2.toString());
        }
        Task continueWithTask = aVar.start().continueWithTask(this.f11300a, new Continuation(this, pair) { // from class: com.google.firebase.iid.o

            /* renamed from: a  reason: collision with root package name */
            public final p f11299a;
            public final Pair b;

            {
                this.f11299a = this;
                this.b = pair;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public Object then(Task task2) {
                this.f11299a.b(this.b, task2);
                return task2;
            }
        });
        this.b.put(pair, continueWithTask);
        return continueWithTask;
    }

    public final /* synthetic */ Task b(Pair pair, Task task) throws Exception {
        synchronized (this) {
            this.b.remove(pair);
        }
        return task;
    }
}
