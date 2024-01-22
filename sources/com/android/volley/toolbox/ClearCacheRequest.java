package com.android.volley.toolbox;

import android.os.Handler;
import android.os.Looper;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
/* loaded from: classes.dex */
public class ClearCacheRequest extends Request<Object> {
    public final Cache h;
    public final Runnable i;

    public ClearCacheRequest(Cache cache, Runnable runnable) {
        super(0, null, null);
        this.h = cache;
        this.i = runnable;
    }

    @Override // com.android.volley.Request
    public void deliverResponse(Object obj) {
    }

    @Override // com.android.volley.Request
    public Request.Priority getPriority() {
        return Request.Priority.IMMEDIATE;
    }

    @Override // com.android.volley.Request
    public boolean isCanceled() {
        this.h.clear();
        if (this.i != null) {
            new Handler(Looper.getMainLooper()).postAtFrontOfQueue(this.i);
            return true;
        }
        return true;
    }

    @Override // com.android.volley.Request
    public Response<Object> parseNetworkResponse(NetworkResponse networkResponse) {
        return null;
    }
}
