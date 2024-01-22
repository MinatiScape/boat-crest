package com.abupdate.iot_libs.inter;
/* loaded from: classes.dex */
public interface IStatusListener extends IListener {
    void onAbnormalDisconnected(int i);

    void onConnected();

    void onDisconnected();

    void onError(int i);
}
