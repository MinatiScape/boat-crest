package com.bumptech.glide.request;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.RequestCoordinator;
/* loaded from: classes2.dex */
public final class ErrorRequestCoordinator implements RequestCoordinator, Request {

    /* renamed from: a  reason: collision with root package name */
    public final Object f2525a;
    @Nullable
    public final RequestCoordinator b;
    public volatile Request c;
    public volatile Request d;
    @GuardedBy("requestLock")
    public RequestCoordinator.RequestState e;
    @GuardedBy("requestLock")
    public RequestCoordinator.RequestState f;

    public ErrorRequestCoordinator(Object obj, @Nullable RequestCoordinator requestCoordinator) {
        RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
        this.e = requestState;
        this.f = requestState;
        this.f2525a = obj;
        this.b = requestCoordinator;
    }

    @GuardedBy("requestLock")
    public final boolean a(Request request) {
        RequestCoordinator.RequestState requestState;
        RequestCoordinator.RequestState requestState2 = this.e;
        RequestCoordinator.RequestState requestState3 = RequestCoordinator.RequestState.FAILED;
        if (requestState2 != requestState3) {
            return request.equals(this.c);
        }
        return request.equals(this.d) && ((requestState = this.f) == RequestCoordinator.RequestState.SUCCESS || requestState == requestState3);
    }

    @GuardedBy("requestLock")
    public final boolean b() {
        RequestCoordinator requestCoordinator = this.b;
        return requestCoordinator == null || requestCoordinator.canNotifyCleared(this);
    }

    @Override // com.bumptech.glide.request.Request
    public void begin() {
        synchronized (this.f2525a) {
            RequestCoordinator.RequestState requestState = this.e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
            if (requestState != requestState2) {
                this.e = requestState2;
                this.c.begin();
            }
        }
    }

    @GuardedBy("requestLock")
    public final boolean c() {
        RequestCoordinator requestCoordinator = this.b;
        return requestCoordinator == null || requestCoordinator.canNotifyStatusChanged(this);
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean canNotifyCleared(Request request) {
        boolean z;
        synchronized (this.f2525a) {
            z = b() && request.equals(this.c);
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean canNotifyStatusChanged(Request request) {
        boolean z;
        synchronized (this.f2525a) {
            z = c() && a(request);
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean canSetImage(Request request) {
        boolean d;
        synchronized (this.f2525a) {
            d = d();
        }
        return d;
    }

    @Override // com.bumptech.glide.request.Request
    public void clear() {
        synchronized (this.f2525a) {
            RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
            this.e = requestState;
            this.c.clear();
            if (this.f != requestState) {
                this.f = requestState;
                this.d.clear();
            }
        }
    }

    @GuardedBy("requestLock")
    public final boolean d() {
        RequestCoordinator requestCoordinator = this.b;
        return requestCoordinator == null || requestCoordinator.canSetImage(this);
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public RequestCoordinator getRoot() {
        RequestCoordinator root;
        synchronized (this.f2525a) {
            RequestCoordinator requestCoordinator = this.b;
            root = requestCoordinator != null ? requestCoordinator.getRoot() : this;
        }
        return root;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator, com.bumptech.glide.request.Request
    public boolean isAnyResourceSet() {
        boolean z;
        synchronized (this.f2525a) {
            z = this.c.isAnyResourceSet() || this.d.isAnyResourceSet();
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isCleared() {
        boolean z;
        synchronized (this.f2525a) {
            RequestCoordinator.RequestState requestState = this.e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.CLEARED;
            z = requestState == requestState2 && this.f == requestState2;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isComplete() {
        boolean z;
        synchronized (this.f2525a) {
            RequestCoordinator.RequestState requestState = this.e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.SUCCESS;
            z = requestState == requestState2 || this.f == requestState2;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isEquivalentTo(Request request) {
        if (request instanceof ErrorRequestCoordinator) {
            ErrorRequestCoordinator errorRequestCoordinator = (ErrorRequestCoordinator) request;
            return this.c.isEquivalentTo(errorRequestCoordinator.c) && this.d.isEquivalentTo(errorRequestCoordinator.d);
        }
        return false;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isRunning() {
        boolean z;
        synchronized (this.f2525a) {
            RequestCoordinator.RequestState requestState = this.e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
            z = requestState == requestState2 || this.f == requestState2;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public void onRequestFailed(Request request) {
        synchronized (this.f2525a) {
            if (!request.equals(this.d)) {
                this.e = RequestCoordinator.RequestState.FAILED;
                RequestCoordinator.RequestState requestState = this.f;
                RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
                if (requestState != requestState2) {
                    this.f = requestState2;
                    this.d.begin();
                }
                return;
            }
            this.f = RequestCoordinator.RequestState.FAILED;
            RequestCoordinator requestCoordinator = this.b;
            if (requestCoordinator != null) {
                requestCoordinator.onRequestFailed(this);
            }
        }
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public void onRequestSuccess(Request request) {
        synchronized (this.f2525a) {
            if (request.equals(this.c)) {
                this.e = RequestCoordinator.RequestState.SUCCESS;
            } else if (request.equals(this.d)) {
                this.f = RequestCoordinator.RequestState.SUCCESS;
            }
            RequestCoordinator requestCoordinator = this.b;
            if (requestCoordinator != null) {
                requestCoordinator.onRequestSuccess(this);
            }
        }
    }

    @Override // com.bumptech.glide.request.Request
    public void pause() {
        synchronized (this.f2525a) {
            RequestCoordinator.RequestState requestState = this.e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
            if (requestState == requestState2) {
                this.e = RequestCoordinator.RequestState.PAUSED;
                this.c.pause();
            }
            if (this.f == requestState2) {
                this.f = RequestCoordinator.RequestState.PAUSED;
                this.d.pause();
            }
        }
    }

    public void setRequests(Request request, Request request2) {
        this.c = request;
        this.d = request2;
    }
}
