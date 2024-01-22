package com.jieli.jl_filebrowse.tool;

import android.os.Handler;
import android.os.Looper;
import com.jieli.jl_filebrowse.interfaces.lrc.LrcReadObserver;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public class LrcReadObserverHelper implements LrcReadObserver {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<LrcReadObserver> f12418a = new ArrayList<>();
    private final Handler b = new Handler(Looper.getMainLooper());

    public void addLrcReadObserver(LrcReadObserver lrcReadObserver) {
        if (lrcReadObserver == null || this.f12418a.contains(lrcReadObserver)) {
            return;
        }
        this.f12418a.add(lrcReadObserver);
    }

    @Override // com.jieli.jl_filebrowse.interfaces.lrc.LrcReadObserver
    public void onLrcReadFailed(final int i) {
        a(new CallbackImpl() { // from class: com.jieli.jl_filebrowse.tool.g
            @Override // com.jieli.jl_filebrowse.tool.CallbackImpl
            public final void onCallback(Object obj) {
                ((LrcReadObserver) obj).onLrcReadFailed(i);
            }
        });
    }

    @Override // com.jieli.jl_filebrowse.interfaces.lrc.LrcReadObserver
    public void onLrcReadStart() {
        a(new CallbackImpl() { // from class: com.jieli.jl_filebrowse.tool.i
            @Override // com.jieli.jl_filebrowse.tool.CallbackImpl
            public final void onCallback(Object obj) {
                ((LrcReadObserver) obj).onLrcReadStart();
            }
        });
    }

    @Override // com.jieli.jl_filebrowse.interfaces.lrc.LrcReadObserver
    public void onLrcReadStop(final String str) {
        a(new CallbackImpl() { // from class: com.jieli.jl_filebrowse.tool.h
            @Override // com.jieli.jl_filebrowse.tool.CallbackImpl
            public final void onCallback(Object obj) {
                ((LrcReadObserver) obj).onLrcReadStop(str);
            }
        });
    }

    public void release() {
        this.f12418a.clear();
        this.b.removeCallbacksAndMessages(null);
    }

    public void removeLrcReadObserver(LrcReadObserver lrcReadObserver) {
        if (lrcReadObserver == null || this.f12418a.isEmpty()) {
            return;
        }
        this.f12418a.remove(lrcReadObserver);
    }

    private void a(CallbackImpl<LrcReadObserver> callbackImpl) {
        if (callbackImpl == null) {
            return;
        }
        CallbackRunnable callbackRunnable = new CallbackRunnable(this.f12418a, callbackImpl);
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            callbackRunnable.run();
        } else {
            this.b.post(callbackRunnable);
        }
    }
}
