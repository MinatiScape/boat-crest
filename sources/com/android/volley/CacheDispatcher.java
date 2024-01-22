package com.android.volley;

import android.os.Process;
import androidx.annotation.VisibleForTesting;
import com.android.volley.Cache;
import com.android.volley.Request;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
/* loaded from: classes.dex */
public class CacheDispatcher extends Thread {
    public static final boolean n = VolleyLog.DEBUG;
    public final BlockingQueue<Request<?>> h;
    public final BlockingQueue<Request<?>> i;
    public final Cache j;
    public final ResponseDelivery k;
    public volatile boolean l = false;
    public final b m = new b(this);

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ Request h;

        public a(Request request) {
            this.h = request;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                CacheDispatcher.this.i.put(this.h);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Request.b {

        /* renamed from: a  reason: collision with root package name */
        public final Map<String, List<Request<?>>> f2143a = new HashMap();
        public final CacheDispatcher b;

        public b(CacheDispatcher cacheDispatcher) {
            this.b = cacheDispatcher;
        }

        @Override // com.android.volley.Request.b
        public void a(Request<?> request, Response<?> response) {
            List<Request<?>> remove;
            Cache.Entry entry = response.cacheEntry;
            if (entry != null && !entry.isExpired()) {
                String cacheKey = request.getCacheKey();
                synchronized (this) {
                    remove = this.f2143a.remove(cacheKey);
                }
                if (remove != null) {
                    if (VolleyLog.DEBUG) {
                        VolleyLog.v("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(remove.size()), cacheKey);
                    }
                    for (Request<?> request2 : remove) {
                        this.b.k.postResponse(request2, response);
                    }
                    return;
                }
                return;
            }
            b(request);
        }

        @Override // com.android.volley.Request.b
        public synchronized void b(Request<?> request) {
            String cacheKey = request.getCacheKey();
            List<Request<?>> remove = this.f2143a.remove(cacheKey);
            if (remove != null && !remove.isEmpty()) {
                if (VolleyLog.DEBUG) {
                    VolleyLog.v("%d waiting requests for cacheKey=%s; resend to network", Integer.valueOf(remove.size()), cacheKey);
                }
                Request<?> remove2 = remove.remove(0);
                this.f2143a.put(cacheKey, remove);
                remove2.setNetworkRequestCompleteListener(this);
                try {
                    this.b.i.put(remove2);
                } catch (InterruptedException e) {
                    VolleyLog.e("Couldn't add request to queue. %s", e.toString());
                    Thread.currentThread().interrupt();
                    this.b.quit();
                }
            }
        }

        public final synchronized boolean d(Request<?> request) {
            String cacheKey = request.getCacheKey();
            if (this.f2143a.containsKey(cacheKey)) {
                List<Request<?>> list = this.f2143a.get(cacheKey);
                if (list == null) {
                    list = new ArrayList<>();
                }
                request.addMarker("waiting-for-response");
                list.add(request);
                this.f2143a.put(cacheKey, list);
                if (VolleyLog.DEBUG) {
                    VolleyLog.d("Request for cacheKey=%s is in flight, putting on hold.", cacheKey);
                }
                return true;
            }
            this.f2143a.put(cacheKey, null);
            request.setNetworkRequestCompleteListener(this);
            if (VolleyLog.DEBUG) {
                VolleyLog.d("new request, sending to network %s", cacheKey);
            }
            return false;
        }
    }

    public CacheDispatcher(BlockingQueue<Request<?>> blockingQueue, BlockingQueue<Request<?>> blockingQueue2, Cache cache, ResponseDelivery responseDelivery) {
        this.h = blockingQueue;
        this.i = blockingQueue2;
        this.j = cache;
        this.k = responseDelivery;
    }

    public final void c() throws InterruptedException {
        d(this.h.take());
    }

    @VisibleForTesting
    public void d(Request<?> request) throws InterruptedException {
        request.addMarker("cache-queue-take");
        if (request.isCanceled()) {
            request.finish("cache-discard-canceled");
            return;
        }
        Cache.Entry entry = this.j.get(request.getCacheKey());
        if (entry == null) {
            request.addMarker("cache-miss");
            if (this.m.d(request)) {
                return;
            }
            this.i.put(request);
        } else if (entry.isExpired()) {
            request.addMarker("cache-hit-expired");
            request.setCacheEntry(entry);
            if (this.m.d(request)) {
                return;
            }
            this.i.put(request);
        } else {
            request.addMarker("cache-hit");
            Response<?> parseNetworkResponse = request.parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
            request.addMarker("cache-hit-parsed");
            if (!entry.refreshNeeded()) {
                this.k.postResponse(request, parseNetworkResponse);
                return;
            }
            request.addMarker("cache-hit-refresh-needed");
            request.setCacheEntry(entry);
            parseNetworkResponse.intermediate = true;
            if (!this.m.d(request)) {
                this.k.postResponse(request, parseNetworkResponse, new a(request));
            } else {
                this.k.postResponse(request, parseNetworkResponse);
            }
        }
    }

    public void quit() {
        this.l = true;
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (n) {
            VolleyLog.v("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.j.initialize();
        while (true) {
            try {
                c();
            } catch (InterruptedException unused) {
                if (this.l) {
                    Thread.currentThread().interrupt();
                    return;
                }
                VolleyLog.e("Ignoring spurious interrupt of CacheDispatcher thread; use quit() to terminate it", new Object[0]);
            }
        }
    }
}
