package com.touchgui.sdk;
@Deprecated
/* loaded from: classes12.dex */
public interface TGLogManager {

    /* loaded from: classes12.dex */
    public interface Listener {
        void onCompleted();

        void onError(Throwable th);

        void onResponse(byte[] bArr);

        void onStart();
    }

    void abortExport();

    void addListener(Listener listener);

    default void export(int i) {
        export(i, i == 1 ? 2 : 0);
    }

    void export(int i, int i2);

    void removeListener(Listener listener);
}
