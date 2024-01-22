package com.ido.ble.firmware.log;

import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.BasicInfo;
/* loaded from: classes11.dex */
public class a {
    private static a b;

    /* renamed from: a  reason: collision with root package name */
    private boolean f12271a = false;

    /* renamed from: com.ido.ble.firmware.log.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0601a implements ICollectDeviceRebootLogListener {
        public C0601a() {
        }

        @Override // com.ido.ble.firmware.log.ICollectDeviceRebootLogListener
        public void onFailed() {
            com.ido.ble.f.a.f.a.g0().e(true);
            LogTool.d(c.f12275a, "[AutoCollectFirmwareLogPresenter] collect reboot log failed, will collect next.");
        }

        @Override // com.ido.ble.firmware.log.ICollectDeviceRebootLogListener
        public void onStart() {
            a.this.f12271a = false;
            com.ido.ble.f.a.f.a.g0().e(true);
        }

        @Override // com.ido.ble.firmware.log.ICollectDeviceRebootLogListener
        public void onSuccess(String str) {
            com.ido.ble.f.a.f.a.g0().e(false);
            LogTool.d(c.f12275a, "[AutoCollectFirmwareLogPresenter] collect reboot log success.");
        }
    }

    private a() {
    }

    public static a c() {
        if (b == null) {
            b = new a();
        }
        return b;
    }

    private boolean d() {
        BasicInfo h = com.ido.ble.f.a.f.a.g0().h();
        if (h == null || h.deivceId != 301) {
            return this.f12271a || com.ido.ble.f.a.f.a.g0().f0();
        }
        LogTool.b(c.f12275a, "[CollectDeviceRebootLogPresenter] did = 301");
        return false;
    }

    public void a() {
        if (!d()) {
            LogTool.d(c.f12275a, "[AutoCollectFirmwareLogPresenter] is not need to collect reboot log.");
            return;
        }
        LogTool.d(c.f12275a, "[AutoCollectFirmwareLogPresenter] collect log...,[reboot=" + this.f12271a + ",isNeed=" + com.ido.ble.f.a.f.a.g0().f0() + "]");
        d.a(true, new C0601a());
    }

    public void a(boolean z) {
        LogTool.d(c.f12275a, "[AutoCollectFirmwareLogPresenter] set reboot = " + z);
        this.f12271a = z;
        if (z) {
            com.ido.ble.f.a.f.a.g0().e(true);
        }
    }

    public boolean b() {
        return this.f12271a;
    }
}
