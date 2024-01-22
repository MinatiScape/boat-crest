package com.ido.ble.dfu.d.a;

import com.ido.ble.callback.EnterDfuModeCallback;
/* loaded from: classes11.dex */
public interface c {
    void a();

    void a(int i);

    void a(int i, String str);

    void a(EnterDfuModeCallback.DfuError dfuError);

    void b();

    void c();

    void d();

    void e();

    void f();

    void g();

    void h();

    void i();

    void j();

    void onCancel();

    void onDeviceInDFUMode();

    void onPrepare();

    void onProgress(int i);

    void onSuccess();

    void onSuccessAndNeedToPromptUser();
}
