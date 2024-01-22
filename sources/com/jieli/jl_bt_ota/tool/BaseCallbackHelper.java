package com.jieli.jl_bt_ota.tool;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public abstract class BaseCallbackHelper<T> {
    public final ArrayList<T> callbacks = new ArrayList<>();

    /* renamed from: a  reason: collision with root package name */
    private final Handler f12357a = new Handler(Looper.getMainLooper());

    public boolean addCallback(T t) {
        if (t == null) {
            return false;
        }
        boolean contains = this.callbacks.contains(t);
        return !contains ? this.callbacks.add(t) : contains;
    }

    public void callbackEvent(ICallbackHandler<T> iCallbackHandler) {
        if (iCallbackHandler == null) {
            return;
        }
        CallbackRunnable callbackRunnable = new CallbackRunnable(this.callbacks, iCallbackHandler);
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            callbackRunnable.run();
        } else {
            this.f12357a.post(callbackRunnable);
        }
    }

    public void release() {
        this.callbacks.clear();
        this.f12357a.removeCallbacksAndMessages(null);
    }

    public boolean removeCallback(T t) {
        if (t == null || this.callbacks.isEmpty()) {
            return false;
        }
        return this.callbacks.remove(t);
    }
}
