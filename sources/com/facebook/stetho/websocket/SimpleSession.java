package com.facebook.stetho.websocket;
/* loaded from: classes9.dex */
public interface SimpleSession {
    void close(int i, String str);

    boolean isOpen();

    void sendBinary(byte[] bArr);

    void sendText(String str);
}
