package com.android.volley;

import android.os.Handler;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class ExecutorDelivery implements ResponseDelivery {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f2145a;

    /* loaded from: classes.dex */
    public class a implements Executor {
        public final /* synthetic */ Handler h;

        public a(ExecutorDelivery executorDelivery, Handler handler) {
            this.h = handler;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.h.post(runnable);
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Runnable {
        public final Request h;
        public final Response i;
        public final Runnable j;

        public b(Request request, Response response, Runnable runnable) {
            this.h = request;
            this.i = response;
            this.j = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.h.isCanceled()) {
                this.h.finish("canceled-at-delivery");
                return;
            }
            if (this.i.isSuccess()) {
                this.h.deliverResponse(this.i.result);
            } else {
                this.h.deliverError(this.i.error);
            }
            if (this.i.intermediate) {
                this.h.addMarker("intermediate-response");
            } else {
                this.h.finish("done");
            }
            Runnable runnable = this.j;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public ExecutorDelivery(Handler handler) {
        this.f2145a = new a(this, handler);
    }

    @Override // com.android.volley.ResponseDelivery
    public void postError(Request<?> request, VolleyError volleyError) {
        request.addMarker("post-error");
        this.f2145a.execute(new b(request, Response.error(volleyError), null));
    }

    @Override // com.android.volley.ResponseDelivery
    public void postResponse(Request<?> request, Response<?> response) {
        postResponse(request, response, null);
    }

    @Override // com.android.volley.ResponseDelivery
    public void postResponse(Request<?> request, Response<?> response, Runnable runnable) {
        request.markDelivered();
        request.addMarker("post-response");
        this.f2145a.execute(new b(request, response, runnable));
    }

    public ExecutorDelivery(Executor executor) {
        this.f2145a = executor;
    }
}
