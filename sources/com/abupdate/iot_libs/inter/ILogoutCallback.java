package com.abupdate.iot_libs.inter;
/* loaded from: classes.dex */
public interface ILogoutCallback {
    void onLogoutFail(int i);

    void onLogoutSuccess();

    void onLogoutTimeout();
}
