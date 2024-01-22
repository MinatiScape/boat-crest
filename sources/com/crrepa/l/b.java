package com.crrepa.l;

import androidx.annotation.NonNull;
import com.crrepa.ble.trans.upgrade.bean.FirmwareVersionInfo;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReference<FirmwareVersionInfo> f7749a;

    /* renamed from: com.crrepa.l.b$b  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0343b {

        /* renamed from: a  reason: collision with root package name */
        public static final b f7750a = new b();
    }

    public b() {
        this.f7749a = new AtomicReference<>();
    }

    public static b b() {
        return C0343b.f7750a;
    }

    public FirmwareVersionInfo a() {
        return this.f7749a.get();
    }

    public void a(@NonNull FirmwareVersionInfo firmwareVersionInfo) {
        com.crrepa.t.a.a(firmwareVersionInfo);
        this.f7749a.set(firmwareVersionInfo);
    }
}
