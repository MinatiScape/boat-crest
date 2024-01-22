package com.android.volley;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.BlockingQueue;
/* loaded from: classes.dex */
public class NetworkDispatcher extends Thread {
    public final BlockingQueue<Request<?>> h;
    public final Network i;
    public final Cache j;
    public final ResponseDelivery k;
    public volatile boolean l = false;

    public NetworkDispatcher(BlockingQueue<Request<?>> blockingQueue, Network network, Cache cache, ResponseDelivery responseDelivery) {
        this.h = blockingQueue;
        this.i = network;
        this.j = cache;
        this.k = responseDelivery;
    }

    @TargetApi(14)
    public final void a(Request<?> request) {
        if (Build.VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
        }
    }

    public final void b(Request<?> request, VolleyError volleyError) {
        this.k.postError(request, request.parseNetworkError(volleyError));
    }

    public final void c() throws InterruptedException {
        d(this.h.take());
    }

    @VisibleForTesting
    public void d(Request<?> request) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            request.addMarker("network-queue-take");
            if (request.isCanceled()) {
                request.finish("network-discard-cancelled");
                request.notifyListenerResponseNotUsable();
                return;
            }
            a(request);
            NetworkResponse performRequest = this.i.performRequest(request);
            request.addMarker("network-http-complete");
            if (performRequest.notModified && request.hasHadResponseDelivered()) {
                request.finish("not-modified");
                request.notifyListenerResponseNotUsable();
                return;
            }
            Response<?> parseNetworkResponse = request.parseNetworkResponse(performRequest);
            request.addMarker("network-parse-complete");
            if (request.shouldCache() && parseNetworkResponse.cacheEntry != null) {
                this.j.put(request.getCacheKey(), parseNetworkResponse.cacheEntry);
                request.addMarker("network-cache-written");
            }
            request.markDelivered();
            this.k.postResponse(request, parseNetworkResponse);
            request.notifyListenerResponseReceived(parseNetworkResponse);
        } catch (VolleyError e) {
            e.setNetworkTimeMs(SystemClock.elapsedRealtime() - elapsedRealtime);
            b(request, e);
            request.notifyListenerResponseNotUsable();
        } catch (Exception e2) {
            VolleyLog.e(e2, "Unhandled exception %s", e2.toString());
            VolleyError volleyError = new VolleyError(e2);
            volleyError.setNetworkTimeMs(SystemClock.elapsedRealtime() - elapsedRealtime);
            this.k.postError(request, volleyError);
            request.notifyListenerResponseNotUsable();
        }
    }

    public void quit() {
        this.l = true;
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                c();
            } catch (InterruptedException unused) {
                if (this.l) {
                    Thread.currentThread().interrupt();
                    return;
                }
                VolleyLog.e("Ignoring spurious interrupt of NetworkDispatcher thread; use quit() to terminate it", new Object[0]);
            }
        }
    }
}
