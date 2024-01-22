package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class RequestQueue {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicInteger f2147a;
    public final Set<Request<?>> b;
    public final PriorityBlockingQueue<Request<?>> c;
    public final PriorityBlockingQueue<Request<?>> d;
    public final Cache e;
    public final Network f;
    public final ResponseDelivery g;
    public final NetworkDispatcher[] h;
    public CacheDispatcher i;
    public final List<RequestFinishedListener> j;

    /* loaded from: classes.dex */
    public interface RequestFilter {
        boolean apply(Request<?> request);
    }

    /* loaded from: classes.dex */
    public interface RequestFinishedListener<T> {
        void onRequestFinished(Request<T> request);
    }

    /* loaded from: classes.dex */
    public class a implements RequestFilter {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f2148a;

        public a(RequestQueue requestQueue, Object obj) {
            this.f2148a = obj;
        }

        @Override // com.android.volley.RequestQueue.RequestFilter
        public boolean apply(Request<?> request) {
            return request.getTag() == this.f2148a;
        }
    }

    public RequestQueue(Cache cache, Network network, int i, ResponseDelivery responseDelivery) {
        this.f2147a = new AtomicInteger();
        this.b = new HashSet();
        this.c = new PriorityBlockingQueue<>();
        this.d = new PriorityBlockingQueue<>();
        this.j = new ArrayList();
        this.e = cache;
        this.f = network;
        this.h = new NetworkDispatcher[i];
        this.g = responseDelivery;
    }

    public <T> void a(Request<T> request) {
        synchronized (this.b) {
            this.b.remove(request);
        }
        synchronized (this.j) {
            for (RequestFinishedListener requestFinishedListener : this.j) {
                requestFinishedListener.onRequestFinished(request);
            }
        }
    }

    public <T> Request<T> add(Request<T> request) {
        request.setRequestQueue(this);
        synchronized (this.b) {
            this.b.add(request);
        }
        request.setSequence(getSequenceNumber());
        request.addMarker("add-to-queue");
        if (!request.shouldCache()) {
            this.d.add(request);
            return request;
        }
        this.c.add(request);
        return request;
    }

    public <T> void addRequestFinishedListener(RequestFinishedListener<T> requestFinishedListener) {
        synchronized (this.j) {
            this.j.add(requestFinishedListener);
        }
    }

    public void cancelAll(RequestFilter requestFilter) {
        synchronized (this.b) {
            for (Request<?> request : this.b) {
                if (requestFilter.apply(request)) {
                    request.cancel();
                }
            }
        }
    }

    public Cache getCache() {
        return this.e;
    }

    public int getSequenceNumber() {
        return this.f2147a.incrementAndGet();
    }

    public <T> void removeRequestFinishedListener(RequestFinishedListener<T> requestFinishedListener) {
        synchronized (this.j) {
            this.j.remove(requestFinishedListener);
        }
    }

    public void start() {
        stop();
        CacheDispatcher cacheDispatcher = new CacheDispatcher(this.c, this.d, this.e, this.g);
        this.i = cacheDispatcher;
        cacheDispatcher.start();
        for (int i = 0; i < this.h.length; i++) {
            NetworkDispatcher networkDispatcher = new NetworkDispatcher(this.d, this.f, this.e, this.g);
            this.h[i] = networkDispatcher;
            networkDispatcher.start();
        }
    }

    public void stop() {
        NetworkDispatcher[] networkDispatcherArr;
        CacheDispatcher cacheDispatcher = this.i;
        if (cacheDispatcher != null) {
            cacheDispatcher.quit();
        }
        for (NetworkDispatcher networkDispatcher : this.h) {
            if (networkDispatcher != null) {
                networkDispatcher.quit();
            }
        }
    }

    public void cancelAll(Object obj) {
        if (obj != null) {
            cancelAll((RequestFilter) new a(this, obj));
            return;
        }
        throw new IllegalArgumentException("Cannot cancelAll with a null tag");
    }

    public RequestQueue(Cache cache, Network network, int i) {
        this(cache, network, i, new ExecutorDelivery(new Handler(Looper.getMainLooper())));
    }

    public RequestQueue(Cache cache, Network network) {
        this(cache, network, 4);
    }
}
