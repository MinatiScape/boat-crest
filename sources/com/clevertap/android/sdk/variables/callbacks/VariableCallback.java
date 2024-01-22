package com.clevertap.android.sdk.variables.callbacks;

import com.clevertap.android.sdk.variables.Var;
/* loaded from: classes2.dex */
public abstract class VariableCallback<T> implements Runnable {
    public Var<T> h;

    public abstract void onValueChanged(Var<T> var);

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.h) {
            onValueChanged(this.h);
        }
    }

    public void setVariable(Var<T> var) {
        this.h = var;
    }
}
