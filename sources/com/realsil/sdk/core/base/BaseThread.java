package com.realsil.sdk.core.base;

import java.util.concurrent.LinkedBlockingDeque;
/* loaded from: classes12.dex */
public abstract class BaseThread<T> extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public volatile boolean f13553a = false;
    public LinkedBlockingDeque<T> b = new LinkedBlockingDeque<>();

    public void addQueue(T t) {
        synchronized (this.b) {
            this.b.add(t);
        }
    }

    public void cancel(boolean z) {
        this.f13553a = z;
    }

    public void clearQueue() {
        synchronized (this.b) {
            this.b.clear();
        }
    }

    public boolean isCanceled() {
        return this.f13553a;
    }

    public T peek() {
        T peek;
        synchronized (this.b) {
            peek = this.b.peek();
        }
        return peek;
    }

    public T poll() {
        T poll;
        synchronized (this.b) {
            poll = this.b.poll();
        }
        return poll;
    }

    public void push(T t) {
        synchronized (this.b) {
            this.b.push(t);
        }
    }

    public T take() {
        try {
            return this.b.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
