package com.touchgui.sdk;
/* loaded from: classes12.dex */
public interface TGResponseListener {
    public static final int TYPE_DATA = 0;
    public static final int TYPE_LOG = 1;

    void onResponse(byte[] bArr, int i);
}
