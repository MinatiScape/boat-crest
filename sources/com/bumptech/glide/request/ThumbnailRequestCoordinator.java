package com.bumptech.glide.request;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.RequestCoordinator;
/* loaded from: classes2.dex */
public class ThumbnailRequestCoordinator implements RequestCoordinator, Request {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final RequestCoordinator f2527a;
    public final Object b;
    public volatile Request c;
    public volatile Request d;
    @GuardedBy("requestLock")
    public RequestCoordinator.RequestState e;
    @GuardedBy("requestLock")
    public RequestCoordinator.RequestState f;
    @GuardedBy("requestLock")
    public boolean g;

    public ThumbnailRequestCoordinator(Object obj, @Nullable RequestCoordinator requestCoordinator) {
        RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
        this.e = requestState;
        this.f = requestState;
        this.b = obj;
        this.f2527a = requestCoordinator;
    }

    @GuardedBy("requestLock")
    public final boolean a() {
        RequestCoordinator requestCoordinator = this.f2527a;
        return requestCoordinator == null || requestCoordinator.canNotifyCleared(this);
    }

    @GuardedBy("requestLock")
    public final boolean b() {
        RequestCoordinator requestCoordinator = this.f2527a;
        return requestCoordinator == null || requestCoordinator.canNotifyStatusChanged(this);
    }

    @Override // com.bumptech.glide.request.Request
    public void begin() {
        synchronized (this.b) {
            this.g = true;
            if (this.e != RequestCoordinator.RequestState.SUCCESS) {
                RequestCoordinator.RequestState requestState = this.f;
                RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
                if (requestState != requestState2) {
                    this.f = requestState2;
                    this.d.begin();
                }
            }
            if (this.g) {
                RequestCoordinator.RequestState requestState3 = this.e;
                RequestCoordinator.RequestState requestState4 = RequestCoordinator.RequestState.RUNNING;
                if (requestState3 != requestState4) {
                    this.e = requestState4;
                    this.c.begin();
                }
            }
            this.g = false;
        }
    }

    @GuardedBy("requestLock")
    public final boolean c() {
        RequestCoordinator requestCoordinator = this.f2527a;
        return requestCoordinator == null || requestCoordinator.canSetImage(this);
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean canNotifyCleared(Request request) {
        boolean z;
        synchronized (this.b) {
            z = a() && request.equals(this.c) && this.e != RequestCoordinator.RequestState.PAUSED;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean canNotifyStatusChanged(Request request) {
        boolean z;
        synchronized (this.b) {
            z = b() && request.equals(this.c) && !isAnyResourceSet();
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean canSetImage(Request request) {
        boolean z;
        synchronized (this.b) {
            z = c() && (request.equals(this.c) || this.e != RequestCoordinator.RequestState.SUCCESS);
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public void clear() {
        synchronized (this.b) {
            this.g = false;
            RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
            this.e = requestState;
            this.f = requestState;
            this.d.clear();
            this.c.clear();
        }
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public RequestCoordinator getRoot() {
        RequestCoordinator root;
        synchronized (this.b) {
            RequestCoordinator requestCoordinator = this.f2527a;
            root = requestCoordinator != null ? requestCoordinator.getRoot() : this;
        }
        return root;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator, com.bumptech.glide.request.Request
    public boolean isAnyResourceSet() {
        boolean z;
        synchronized (this.b) {
            z = this.d.isAnyResourceSet() || this.c.isAnyResourceSet();
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isCleared() {
        boolean z;
        synchronized (this.b) {
            z = this.e == RequestCoordinator.RequestState.CLEARED;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isComplete() {
        boolean z;
        synchronized (this.b) {
            z = this.e == RequestCoordinator.RequestState.SUCCESS;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isEquivalentTo(Request request) {
        if (request instanceof ThumbnailRequestCoordinator) {
            ThumbnailRequestCoordinator thumbnailRequestCoordinator = (ThumbnailRequestCoordinator) request;
            if (this.c == null) {
                if (thumbnailRequestCoordinator.c != null) {
                    return false;
                }
            } else if (!this.c.isEquivalentTo(thumbnailRequestCoordinator.c)) {
                return false;
            }
            if (this.d == null) {
                if (thumbnailRequestCoordinator.d != null) {
                    return false;
                }
            } else if (!this.d.isEquivalentTo(thumbnailRequestCoordinator.d)) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override // com.bumptech.glide.request.Request
    public boolean isRunning() {
        boolean z;
        synchronized (this.b) {
            z = this.e == RequestCoordinator.RequestState.RUNNING;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public void onRequestFailed(Request request) {
        synchronized (this.b) {
            if (!request.equals(this.c)) {
                this.f = RequestCoordinator.RequestState.FAILED;
                return;
            }
            this.e = RequestCoordinator.RequestState.FAILED;
            RequestCoordinator requestCoordinator = this.f2527a;
            if (requestCoordinator != null) {
                requestCoordinator.onRequestFailed(this);
            }
        }
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public void onRequestSuccess(Request request) {
        synchronized (this.b) {
            if (request.equals(this.d)) {
                this.f = RequestCoordinator.RequestState.SUCCESS;
                return;
            }
            this.e = RequestCoordinator.RequestState.SUCCESS;
            RequestCoordinator requestCoordinator = this.f2527a;
            if (requestCoordinator != null) {
                requestCoordinator.onRequestSuccess(this);
            }
            if (!this.f.isComplete()) {
                this.d.clear();
            }
        }
    }

    @Override // com.bumptech.glide.request.Request
    public void pause() {
        synchronized (this.b) {
            if (!this.f.isComplete()) {
                this.f = RequestCoordinator.RequestState.PAUSED;
                this.d.pause();
            }
            if (!this.e.isComplete()) {
                this.e = RequestCoordinator.RequestState.PAUSED;
                this.c.pause();
            }
        }
    }

    public void setRequests(Request request, Request request2) {
        this.c = request;
        this.d = request2;
    }
}
