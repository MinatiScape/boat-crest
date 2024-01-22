package com.google.android.gms.common.api.internal;

import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Iterator;
import java.util.Set;
/* loaded from: classes6.dex */
public final class zal {
    public int d;
    public final ArrayMap b = new ArrayMap();
    public final TaskCompletionSource c = new TaskCompletionSource();
    public boolean e = false;

    /* renamed from: a  reason: collision with root package name */
    public final ArrayMap f8311a = new ArrayMap();

    public zal(Iterable iterable) {
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            this.f8311a.put(((HasApiKey) it.next()).getApiKey(), null);
        }
        this.d = this.f8311a.keySet().size();
    }

    public final Task zaa() {
        return this.c.getTask();
    }

    public final Set zab() {
        return this.f8311a.keySet();
    }

    public final void zac(ApiKey apiKey, ConnectionResult connectionResult, @Nullable String str) {
        this.f8311a.put(apiKey, connectionResult);
        this.b.put(apiKey, str);
        this.d--;
        if (!connectionResult.isSuccess()) {
            this.e = true;
        }
        if (this.d == 0) {
            if (this.e) {
                this.c.setException(new AvailabilityException(this.f8311a));
                return;
            }
            this.c.setResult(this.b);
        }
    }
}
