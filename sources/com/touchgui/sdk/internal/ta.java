package com.touchgui.sdk.internal;

import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class ta extends cb {
    public ArrayList e;
    public final /* synthetic */ gb f;

    public ta(gb gbVar) {
        this.f = gbVar;
    }

    @Override // com.touchgui.sdk.internal.sa
    public final void a() {
        new g5(this.f.f13767a, new j5()).execute(this.c);
    }

    @Override // com.touchgui.sdk.internal.sa
    public final void b() {
        new g5(this.f.f13767a, new k5()).execute(this.d);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x006d, code lost:
        if (r0 != null) goto L22;
     */
    @Override // com.touchgui.sdk.internal.cb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a(java.lang.Object r7) {
        /*
            r6 = this;
            com.touchgui.sdk.bean.TGSyncBlockGps r7 = (com.touchgui.sdk.bean.TGSyncBlockGps) r7
            int r0 = r7.getTotalPkg()
            if (r0 <= 0) goto L70
            int r0 = r7.getPkgIndex()
            if (r0 <= 0) goto L70
            boolean r0 = r7.isLastBlock()
            if (r0 == 0) goto L56
            java.util.ArrayList r0 = r6.e
            com.touchgui.sdk.bean.TGSyncGps r1 = r7.getGpsData()
            r0.add(r1)
            com.touchgui.sdk.internal.gb r0 = r6.f
            java.util.ArrayList r1 = r6.e
            r0.getClass()
            r2 = 0
            java.lang.Object r2 = r1.get(r2)
            com.touchgui.sdk.bean.TGSyncGps r2 = (com.touchgui.sdk.bean.TGSyncGps) r2
            if (r2 == 0) goto L70
            java.util.List r3 = r2.getItems()
            if (r3 == 0) goto L70
            r3 = 1
        L34:
            int r4 = r1.size()
            if (r3 >= r4) goto L4e
            java.lang.Object r4 = r1.get(r3)
            com.touchgui.sdk.bean.TGSyncGps r4 = (com.touchgui.sdk.bean.TGSyncGps) r4
            java.util.List r5 = r2.getItems()
            java.util.List r4 = r4.getItems()
            r5.addAll(r4)
            int r3 = r3 + 1
            goto L34
        L4e:
            com.touchgui.sdk.internal.ua r0 = r0.d
            if (r0 == 0) goto L70
            r0.onGpsData(r2)
            goto L70
        L56:
            boolean r0 = r7.isFirstBlock()
            if (r0 == 0) goto L6b
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r6.e = r0
        L63:
            com.touchgui.sdk.bean.TGSyncGps r1 = r7.getGpsData()
            r0.add(r1)
            goto L70
        L6b:
            java.util.ArrayList r0 = r6.e
            if (r0 == 0) goto L70
            goto L63
        L70:
            boolean r7 = r7.isHaveMoreData()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.touchgui.sdk.internal.ta.a(java.lang.Object):boolean");
    }
}
