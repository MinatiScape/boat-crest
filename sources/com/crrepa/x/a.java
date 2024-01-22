package com.crrepa.x;

import com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener;
import com.crrepa.i0.f;
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public com.crrepa.x.b f7855a;

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static a f7856a = new a();
    }

    public a() {
        this.f7855a = new com.crrepa.x.b(f.a());
    }

    public static a a() {
        return b.f7856a;
    }

    public void a(CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener) {
        this.f7855a.a(cRPBleFirmwareUpgradeListener);
    }

    public void a(String str) {
        this.f7855a.a(str);
    }
}
