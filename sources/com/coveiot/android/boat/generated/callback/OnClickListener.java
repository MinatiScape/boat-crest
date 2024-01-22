package com.coveiot.android.boat.generated.callback;

import android.view.View;
/* loaded from: classes3.dex */
public final class OnClickListener implements View.OnClickListener {
    public final Listener h;
    public final int i;

    /* loaded from: classes3.dex */
    public interface Listener {
        void _internalCallbackOnClick(int i, View view);
    }

    public OnClickListener(Listener listener, int i) {
        this.h = listener;
        this.i = i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.h._internalCallbackOnClick(this.i, view);
    }
}
