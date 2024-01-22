package com.abupdate.iot_libs.inter;
/* loaded from: classes.dex */
public interface ILoginCallback {
    void onLoginFail(int i);

    void onLoginSuccess();

    void onLoginTimeout();
}
