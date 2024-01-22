package com.google.android.gms.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes6.dex */
public abstract class w<T> {

    /* renamed from: a  reason: collision with root package name */
    public final int f8491a;
    public final TaskCompletionSource<T> b = new TaskCompletionSource<>();
    public final int c;
    public final Bundle d;

    public w(int i, int i2, Bundle bundle) {
        this.f8491a = i;
        this.c = i2;
        this.d = bundle;
    }

    public final void a(zzaa zzaaVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(zzaaVar);
            StringBuilder sb = new StringBuilder(valueOf.length() + 14 + valueOf2.length());
            sb.append("Failing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.b.setException(zzaaVar);
    }

    public abstract void b(Bundle bundle);

    public abstract boolean c();

    public String toString() {
        int i = this.c;
        int i2 = this.f8491a;
        c();
        StringBuilder sb = new StringBuilder(55);
        sb.append("Request { what=");
        sb.append(i);
        sb.append(" id=");
        sb.append(i2);
        sb.append(" oneWay=false}");
        return sb.toString();
    }
}
