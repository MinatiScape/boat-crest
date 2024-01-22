package com.touchgui.sdk;
/* loaded from: classes12.dex */
public interface TGOTAManager {

    @Deprecated
    /* loaded from: classes12.dex */
    public interface OTACallback extends TGOTACallback {
    }

    void setCallback(TGOTACallback tGOTACallback);

    void start(String str, boolean z);
}
