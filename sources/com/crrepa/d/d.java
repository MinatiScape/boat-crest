package com.crrepa.d;

import android.os.Build;
import androidx.annotation.MainThread;
import androidx.annotation.RequiresApi;
import com.crrepa.ble.conn.listener.CRPBleConnectionStateListener;
import com.crrepa.ble.conn.type.CRPProtocolVersion;
/* loaded from: classes9.dex */
public class d implements a {

    /* renamed from: a  reason: collision with root package name */
    public CRPBleConnectionStateListener f7700a;

    public d(CRPBleConnectionStateListener cRPBleConnectionStateListener) {
        this.f7700a = cRPBleConnectionStateListener;
    }

    @RequiresApi(api = 21)
    @MainThread
    public final void a() {
        com.crrepa.p.c.b().a(new com.crrepa.p.a(5, com.crrepa.i0.e.a(512)));
    }

    @Override // com.crrepa.d.a
    public void a(CRPProtocolVersion cRPProtocolVersion) {
        com.crrepa.l.a.b().a(cRPProtocolVersion);
        if (c(cRPProtocolVersion)) {
            a();
            return;
        }
        com.crrepa.l.a.b().h();
        b();
    }

    public final void b() {
        CRPBleConnectionStateListener cRPBleConnectionStateListener = this.f7700a;
        if (cRPBleConnectionStateListener != null) {
            cRPBleConnectionStateListener.onConnectionStateChange(2);
        }
    }

    public final boolean c(CRPProtocolVersion cRPProtocolVersion) {
        return cRPProtocolVersion != CRPProtocolVersion.V1 && Build.VERSION.SDK_INT >= 21;
    }
}
