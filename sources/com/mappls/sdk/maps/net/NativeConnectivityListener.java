package com.mappls.sdk.maps.net;

import androidx.annotation.Keep;
import com.mappls.sdk.maps.LibraryLoader;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class NativeConnectivityListener implements ConnectivityListener {
    @Keep
    private boolean invalidated;
    @Keep
    private long nativePtr;

    static {
        LibraryLoader.load();
    }

    @Keep
    public NativeConnectivityListener(long j) {
        this.nativePtr = j;
    }

    @Keep
    public native void finalize() throws Throwable;

    @Keep
    public native void initialize();

    @Keep
    public native void nativeOnConnectivityStateChanged(boolean z);

    @Override // com.mappls.sdk.maps.net.ConnectivityListener
    public void onNetworkStateChanged(boolean z) {
        nativeOnConnectivityStateChanged(z);
    }

    public NativeConnectivityListener() {
        initialize();
    }
}
