package com.google.android.gms.common.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
/* loaded from: classes6.dex */
public final class zaad {

    /* renamed from: a  reason: collision with root package name */
    public final Map f8296a = Collections.synchronizedMap(new WeakHashMap());
    public final Map b = Collections.synchronizedMap(new WeakHashMap());

    public final void c(BasePendingResult basePendingResult, boolean z) {
        this.f8296a.put(basePendingResult, Boolean.valueOf(z));
        basePendingResult.addStatusListener(new c(this, basePendingResult));
    }

    public final void d(TaskCompletionSource taskCompletionSource, boolean z) {
        this.b.put(taskCompletionSource, Boolean.valueOf(z));
        taskCompletionSource.getTask().addOnCompleteListener(new d(this, taskCompletionSource));
    }

    public final void e(int i, @Nullable String str) {
        StringBuilder sb = new StringBuilder("The connection to Google Play services was lost");
        if (i == 1) {
            sb.append(" due to service disconnection.");
        } else if (i == 3) {
            sb.append(" due to dead object exception.");
        }
        if (str != null) {
            sb.append(" Last reason for disconnect: ");
            sb.append(str);
        }
        g(true, new Status(20, sb.toString()));
    }

    public final boolean f() {
        return (this.f8296a.isEmpty() && this.b.isEmpty()) ? false : true;
    }

    public final void g(boolean z, Status status) {
        HashMap hashMap;
        HashMap hashMap2;
        synchronized (this.f8296a) {
            hashMap = new HashMap(this.f8296a);
        }
        synchronized (this.b) {
            hashMap2 = new HashMap(this.b);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((BasePendingResult) entry.getKey()).forceFailureUnlessReady(status);
            }
        }
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((TaskCompletionSource) entry2.getKey()).trySetException(new ApiException(status));
            }
        }
    }

    public final void zaf() {
        g(false, GoogleApiManager.zaa);
    }
}
