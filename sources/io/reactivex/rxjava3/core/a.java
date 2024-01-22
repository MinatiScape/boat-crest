package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;
/* loaded from: classes12.dex */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ Disposable h;

    @Override // java.lang.Runnable
    public final void run() {
        this.h.dispose();
    }
}
