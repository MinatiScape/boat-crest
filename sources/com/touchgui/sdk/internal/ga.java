package com.touchgui.sdk.internal;

import android.bluetooth.BluetoothDevice;
import com.touchgui.sdk.TGScanner;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class ga implements g8 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.n f13766a;

    public ga(com.touchgui.sdk.n nVar) {
        this.f13766a = nVar;
    }

    @Override // com.touchgui.sdk.internal.g8
    public final void a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        com.touchgui.sdk.n.a(this.f13766a, bluetoothDevice, i, bArr);
    }

    @Override // com.touchgui.sdk.internal.g8
    public final void onScanFailed(int i) {
        Iterator it = this.f13766a.c.iterator();
        while (it.hasNext()) {
            ((TGScanner.OnScanListener) it.next()).onScanFailed(i);
        }
    }

    @Override // com.touchgui.sdk.internal.g8
    public final void onScanFinished() {
        Iterator it = this.f13766a.c.iterator();
        while (it.hasNext()) {
            ((TGScanner.OnScanListener) it.next()).onScanFinished();
        }
    }
}
