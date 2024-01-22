package com.clevertap.android.sdk.variables.callbacks;
/* loaded from: classes2.dex */
public abstract class VariablesChangedCallback implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        variablesChanged();
    }

    public abstract void variablesChanged();
}
