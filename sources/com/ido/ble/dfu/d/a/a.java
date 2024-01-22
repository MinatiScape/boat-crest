package com.ido.ble.dfu.d.a;

import android.text.TextUtils;
import com.ido.ble.callback.EnterDfuModeCallback;
import com.ido.ble.dfu.BleDFUConfig;
import com.ido.ble.dfu.BleDFUState;
/* loaded from: classes11.dex */
public class a implements c {

    /* renamed from: a  reason: collision with root package name */
    private long f12167a = 0;
    private com.ido.ble.event.stat.one.b b = new com.ido.ble.event.stat.one.b();
    private String c;

    public a(BleDFUConfig bleDFUConfig) {
        if (bleDFUConfig == null || TextUtils.isEmpty(bleDFUConfig.getDeviceId()) || TextUtils.isEmpty(bleDFUConfig.getMacAddress())) {
            return;
        }
        this.c = bleDFUConfig.getDeviceId() + bleDFUConfig.getMacAddress();
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void a() {
        StringBuilder sb = new StringBuilder();
        BleDFUState.FailReason failReason = BleDFUState.FailReason.NOT_FIND_TARGET_DEVICE;
        sb.append(failReason);
        sb.append("");
        com.ido.ble.event.stat.one.c.f(sb.toString());
        com.ido.ble.event.stat.one.c.e(failReason + "");
        com.ido.ble.dfu.b.f().a(failReason);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void a(int i) {
        com.ido.ble.dfu.b.f().b(i);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void a(int i, String str) {
        String str2 = "[" + i + "]" + str;
        this.b.a(str2);
        com.ido.ble.event.stat.one.c.e(str2);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void a(EnterDfuModeCallback.DfuError dfuError) {
        StringBuilder sb = new StringBuilder();
        BleDFUState.FailReason failReason = BleDFUState.FailReason.ENTER_DFU_MODE_FAILED;
        sb.append(failReason);
        sb.append(":");
        sb.append(dfuError);
        com.ido.ble.event.stat.one.c.f(sb.toString());
        com.ido.ble.event.stat.one.c.e(failReason + ":" + dfuError);
        if (dfuError == EnterDfuModeCallback.DfuError.LOW_BATTERY) {
            com.ido.ble.dfu.b.f().a(BleDFUState.FailReason.DEVICE_IN_LOW_BATTERY);
        } else {
            com.ido.ble.dfu.b.f().a(failReason);
        }
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void b() {
        StringBuilder sb = new StringBuilder();
        BleDFUState.FailReason failReason = BleDFUState.FailReason.DEVICE_NOT_REBOOT;
        sb.append(failReason);
        sb.append("");
        com.ido.ble.event.stat.one.c.f(sb.toString());
        com.ido.ble.event.stat.one.c.e(failReason + "");
        com.ido.ble.dfu.b.f().a(failReason);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void c() {
        com.ido.ble.event.stat.one.c.f(this.b.b());
        com.ido.ble.dfu.b.f().a(BleDFUState.FailReason.OTHER);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void d() {
        StringBuilder sb = new StringBuilder();
        BleDFUState.FailReason failReason = BleDFUState.FailReason.ENTER_DFU_MODE_FAILED;
        sb.append(failReason);
        sb.append(":timeout");
        com.ido.ble.event.stat.one.c.f(sb.toString());
        com.ido.ble.event.stat.one.c.e(failReason + ":timeout");
        com.ido.ble.dfu.b.f().a(failReason);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void e() {
        StringBuilder sb = new StringBuilder();
        BleDFUState.FailReason failReason = BleDFUState.FailReason.OPERATION_FAILED;
        sb.append(failReason);
        sb.append("");
        com.ido.ble.event.stat.one.c.f(sb.toString());
        com.ido.ble.event.stat.one.c.e(failReason + "");
        com.ido.ble.dfu.b.f().a(failReason);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void f() {
        com.ido.ble.event.stat.one.c.f(this.b.b());
        com.ido.ble.dfu.b.f().a(BleDFUState.FailReason.OTHER);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void g() {
        StringBuilder sb = new StringBuilder();
        BleDFUState.FailReason failReason = BleDFUState.FailReason.OPERATION_NOT_PERMITTED;
        sb.append(failReason);
        sb.append("");
        com.ido.ble.event.stat.one.c.f(sb.toString());
        com.ido.ble.event.stat.one.c.e(failReason + "");
        com.ido.ble.dfu.b.f().a(failReason);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void h() {
        StringBuilder sb = new StringBuilder();
        BleDFUState.FailReason failReason = BleDFUState.FailReason.PHONE_BLUETOOTH_ERROR;
        sb.append(failReason);
        sb.append("");
        com.ido.ble.event.stat.one.c.f(sb.toString());
        com.ido.ble.event.stat.one.c.e(failReason + "");
        com.ido.ble.dfu.b.f().a(failReason);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void i() {
        StringBuilder sb = new StringBuilder();
        BleDFUState.FailReason failReason = BleDFUState.FailReason.CONFIG_PARAS_ERROR;
        sb.append(failReason);
        sb.append("");
        com.ido.ble.event.stat.one.c.f(sb.toString());
        com.ido.ble.event.stat.one.c.e(failReason + "");
        com.ido.ble.dfu.b.f().a(failReason);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void j() {
        StringBuilder sb = new StringBuilder();
        BleDFUState.FailReason failReason = BleDFUState.FailReason.FILE_ERROR;
        sb.append(failReason);
        sb.append("");
        com.ido.ble.event.stat.one.c.f(sb.toString());
        com.ido.ble.event.stat.one.c.e(failReason + "");
        com.ido.ble.dfu.b.f().a(failReason);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void onCancel() {
        com.ido.ble.dfu.b.f().a();
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void onDeviceInDFUMode() {
        com.ido.ble.dfu.b.f().b();
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void onPrepare() {
        this.f12167a = System.currentTimeMillis();
        com.ido.ble.dfu.b.f().c();
        if (TextUtils.isEmpty(this.c)) {
            return;
        }
        com.ido.ble.event.stat.one.c.h(this.c);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void onProgress(int i) {
        com.ido.ble.dfu.b.f().a(i);
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void onSuccess() {
        com.ido.ble.event.stat.one.c.a((System.currentTimeMillis() - this.f12167a) / 1000, this.b.b());
        this.f12167a = 0L;
        if (!TextUtils.isEmpty(this.c)) {
            com.ido.ble.event.stat.one.c.g(this.c);
        }
        com.ido.ble.dfu.b.f().d();
    }

    @Override // com.ido.ble.dfu.d.a.c
    public void onSuccessAndNeedToPromptUser() {
        com.ido.ble.event.stat.one.c.a((System.currentTimeMillis() - this.f12167a) / 1000, this.b.b());
        this.f12167a = 0L;
        if (!TextUtils.isEmpty(this.c)) {
            com.ido.ble.event.stat.one.c.g(this.c);
        }
        com.ido.ble.dfu.b.f().e();
    }
}
