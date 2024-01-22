package com.bumptech.glide.manager;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Util;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;
/* loaded from: classes2.dex */
public class RequestTracker {

    /* renamed from: a  reason: collision with root package name */
    public final Set<Request> f2504a = Collections.newSetFromMap(new WeakHashMap());
    public final Set<Request> b = new HashSet();
    public boolean c;

    public boolean clearAndRemove(@Nullable Request request) {
        boolean z = true;
        if (request == null) {
            return true;
        }
        boolean remove = this.f2504a.remove(request);
        if (!this.b.remove(request) && !remove) {
            z = false;
        }
        if (z) {
            request.clear();
        }
        return z;
    }

    public void clearRequests() {
        for (Request request : Util.getSnapshot(this.f2504a)) {
            clearAndRemove(request);
        }
        this.b.clear();
    }

    public boolean isPaused() {
        return this.c;
    }

    public void pauseAllRequests() {
        this.c = true;
        for (Request request : Util.getSnapshot(this.f2504a)) {
            if (request.isRunning() || request.isComplete()) {
                request.clear();
                this.b.add(request);
            }
        }
    }

    public void pauseRequests() {
        this.c = true;
        for (Request request : Util.getSnapshot(this.f2504a)) {
            if (request.isRunning()) {
                request.pause();
                this.b.add(request);
            }
        }
    }

    public void restartRequests() {
        for (Request request : Util.getSnapshot(this.f2504a)) {
            if (!request.isComplete() && !request.isCleared()) {
                request.clear();
                if (!this.c) {
                    request.begin();
                } else {
                    this.b.add(request);
                }
            }
        }
    }

    public void resumeRequests() {
        this.c = false;
        for (Request request : Util.getSnapshot(this.f2504a)) {
            if (!request.isComplete() && !request.isRunning()) {
                request.begin();
            }
        }
        this.b.clear();
    }

    public void runRequest(@NonNull Request request) {
        this.f2504a.add(request);
        if (!this.c) {
            request.begin();
            return;
        }
        request.clear();
        if (Log.isLoggable("RequestTracker", 2)) {
            Log.v("RequestTracker", "Paused, delaying request");
        }
        this.b.add(request);
    }

    public String toString() {
        return super.toString() + "{numRequests=" + this.f2504a.size() + ", isPaused=" + this.c + "}";
    }
}
