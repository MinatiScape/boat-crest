package com.touchgui.sdk;

import android.content.Context;
import android.content.IntentFilter;
import com.touchgui.sdk.TGScanner;
import com.touchgui.sdk.internal.ga;
import com.touchgui.sdk.internal.ha;
import com.touchgui.sdk.internal.ia;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public final class n implements TGScanner {

    /* renamed from: a  reason: collision with root package name */
    public final Context f13858a;
    public final com.touchgui.sdk.internal.j b;
    public final int d;
    public ha e;
    public boolean f;
    public String g;
    public final CopyOnWriteArrayList c = new CopyOnWriteArrayList();
    public final ga h = new ga(this);

    public n(ia iaVar) {
        Context context;
        int i;
        boolean z;
        String str;
        context = iaVar.f13778a;
        this.f13858a = context;
        i = iaVar.d;
        this.d = i;
        z = iaVar.b;
        this.f = z;
        str = iaVar.c;
        this.g = str;
        this.b = new com.touchgui.sdk.internal.j(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(com.touchgui.sdk.n r3, android.bluetooth.BluetoothDevice r4, int r5, byte[] r6) {
        /*
            r3.getClass()
            java.lang.String r0 = r4.getName()
            boolean r1 = r3.f
            if (r1 == 0) goto L12
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L12
            goto L7d
        L12:
            java.lang.String r1 = r4.getAddress()
            java.lang.String r2 = r3.g
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L1f
            goto L54
        L1f:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L36
            java.lang.String r0 = r0.toUpperCase()
            java.lang.String r2 = r3.g
            java.lang.String r2 = r2.toUpperCase()
            boolean r0 = r0.contains(r2)
            if (r0 == 0) goto L36
            goto L54
        L36:
            java.lang.String r0 = ":"
            java.lang.String r2 = ""
            java.lang.String r0 = r1.replace(r0, r2)
            java.lang.String r0 = r0.toUpperCase()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L56
            java.lang.String r1 = r3.g
            java.lang.String r1 = r1.toUpperCase()
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L56
        L54:
            r0 = 1
            goto L57
        L56:
            r0 = 0
        L57:
            if (r0 != 0) goto L5a
            goto L7d
        L5a:
            com.touchgui.sdk.bean.TGScanDevice r0 = new com.touchgui.sdk.bean.TGScanDevice
            java.lang.String r1 = r4.getName()
            java.lang.String r4 = r4.getAddress()
            r0.<init>(r1, r4, r5, r6)
            java.util.concurrent.CopyOnWriteArrayList r3 = r3.c
            java.util.Iterator r3 = r3.iterator()
        L6d:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L7d
            java.lang.Object r4 = r3.next()
            com.touchgui.sdk.TGScanner$OnScanListener r4 = (com.touchgui.sdk.TGScanner.OnScanListener) r4
            r4.onScanResult(r0)
            goto L6d
        L7d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.touchgui.sdk.n.a(com.touchgui.sdk.n, android.bluetooth.BluetoothDevice, int, byte[]):void");
    }

    @Override // com.touchgui.sdk.TGScanner
    public final void addOnScanListener(TGScanner.OnScanListener onScanListener) {
        if (this.c.contains(onScanListener)) {
            return;
        }
        this.c.add(onScanListener);
    }

    @Override // com.touchgui.sdk.TGScanner
    public final boolean isScanning() {
        return this.b.e.get();
    }

    @Override // com.touchgui.sdk.TGScanner
    public final void removeOnScanListener(TGScanner.OnScanListener onScanListener) {
        this.c.remove(onScanListener);
    }

    @Override // com.touchgui.sdk.TGScanner
    public final void setContainsKeyword(String str) {
        this.g = str;
    }

    @Override // com.touchgui.sdk.TGScanner
    public final void setFilterEmptyName(boolean z) {
        this.f = z;
    }

    @Override // com.touchgui.sdk.TGScanner
    public final boolean startScan() {
        if (this.b.b()) {
            if (this.e == null) {
                this.e = new ha(this);
            }
            this.f13858a.registerReceiver(this.e, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            com.touchgui.sdk.internal.j jVar = this.b;
            ga gaVar = this.h;
            synchronized (jVar) {
                if (!jVar.f.contains(gaVar)) {
                    jVar.f.add(gaVar);
                }
            }
            if (this.b.c()) {
                this.b.a(null, this.d, true);
            } else {
                this.b.a();
            }
            return true;
        }
        return false;
    }

    @Override // com.touchgui.sdk.TGScanner
    public final boolean stopScan() {
        if (this.b.b()) {
            ha haVar = this.e;
            if (haVar != null) {
                this.f13858a.unregisterReceiver(haVar);
            }
            this.e = null;
            this.b.a(true).booleanValue();
            com.touchgui.sdk.internal.j jVar = this.b;
            ga gaVar = this.h;
            synchronized (jVar) {
                jVar.f.remove(gaVar);
            }
            return true;
        }
        return false;
    }
}
