package com.touchgui.sdk.internal;

import android.location.Location;
import com.touchgui.sdk.TGCommand;
import com.touchgui.sdk.TGGPSManager;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.TGSyncAgpsFileListener;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public final class aa implements TGGPSManager {

    /* renamed from: a  reason: collision with root package name */
    public final x3 f13742a;

    public aa(a0 a0Var) {
        this.f13742a = a0Var.e();
    }

    @Override // com.touchgui.sdk.TGGPSManager
    public final void abortSyncAgpsFile() {
        com.touchgui.sdk.c cVar = (com.touchgui.sdk.c) this.f13742a;
        if (cVar.l.get()) {
            cVar.d.abortTransfer();
        }
    }

    @Override // com.touchgui.sdk.TGGPSManager
    public final void addOnSyncAgpsFileListener(TGSyncAgpsFileListener tGSyncAgpsFileListener) {
        com.touchgui.sdk.c cVar = (com.touchgui.sdk.c) this.f13742a;
        if (cVar.c.contains(tGSyncAgpsFileListener)) {
            return;
        }
        cVar.c.add(tGSyncAgpsFileListener);
    }

    @Override // com.touchgui.sdk.TGGPSManager
    public final TGCommand getGpsInfo() {
        return new v8(((com.touchgui.sdk.c) this.f13742a).f13733a, new t6());
    }

    @Override // com.touchgui.sdk.TGGPSManager
    public final TGCommand getGpsStatus() {
        return new v8(((com.touchgui.sdk.c) this.f13742a).f13733a, new u6());
    }

    @Override // com.touchgui.sdk.TGGPSManager
    public final void removeOnSyncAgpsFileListener(TGSyncAgpsFileListener tGSyncAgpsFileListener) {
        ((com.touchgui.sdk.c) this.f13742a).c.remove(tGSyncAgpsFileListener);
    }

    @Override // com.touchgui.sdk.TGGPSManager
    public final void syncAgpsFile(String str) {
        ((com.touchgui.sdk.c) this.f13742a).b(str);
    }

    @Override // com.touchgui.sdk.TGGPSManager
    public final TGCommand syncAppGpsData(Location location, int i) {
        com.touchgui.sdk.c cVar = (com.touchgui.sdk.c) this.f13742a;
        a0 a0Var = cVar.f13733a;
        h8 h8Var = new h8((byte) 5, (byte) 5, 2);
        if (h8Var.d == null) {
            ByteBuffer allocate = ByteBuffer.allocate(18);
            h8Var.d = allocate;
            allocate.order(ByteOrder.LITTLE_ENDIAN);
        }
        ByteBuffer byteBuffer = h8Var.d;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) (i & 255));
        byteBuffer.putDouble(location.getLongitude());
        byteBuffer.putDouble(location.getLatitude());
        byteBuffer.put((byte) 0);
        return new d4(cVar, a0Var, h8Var);
    }

    @Override // com.touchgui.sdk.TGGPSManager
    public final TGCommand syncAppGpsStatus(int i, int i2) {
        com.touchgui.sdk.c cVar = (com.touchgui.sdk.c) this.f13742a;
        a0 a0Var = cVar.f13733a;
        h8 h8Var = new h8((byte) 7, (byte) 3, 2);
        if (h8Var.d == null) {
            ByteBuffer allocate = ByteBuffer.allocate(2);
            h8Var.d = allocate;
            allocate.order(ByteOrder.LITTLE_ENDIAN);
        }
        h8Var.d.put((byte) i).put((byte) i2);
        return new c4(cVar, a0Var, h8Var);
    }

    @Override // com.touchgui.sdk.TGGPSManager
    public final void syncAgpsFile(Map map) {
        com.touchgui.sdk.c cVar = (com.touchgui.sdk.c) this.f13742a;
        TGLogger.d(cVar.f13733a, "syncAgpsFile");
        if (cVar.l.getAndSet(true)) {
            TGLogger.w(cVar.f13733a, "Update AGPS in progress");
            return;
        }
        cVar.e.clear();
        cVar.f.clear();
        long j = 0;
        for (Map.Entry entry : map.entrySet()) {
            File file = new File((String) entry.getValue());
            if (file.exists()) {
                j += file.length();
                cVar.f.put((String) entry.getKey(), file);
                cVar.e.add((String) entry.getKey());
            }
        }
        Collections.sort(cVar.e);
        cVar.g = 0;
        cVar.i = 0L;
        cVar.h = j;
        if (cVar.e.size() > 0) {
            cVar.d.setTotalFileSize(j, new y3(cVar));
            return;
        }
        cVar.m = 1;
        TGLogger.d(cVar.f13733a, "Number of polling device status for write AGPS file: " + cVar.m);
        a0 a0Var = cVar.f13733a;
        HashMap hashMap = u8.f13829a;
        m8 m8Var = new m8();
        ByteBuffer b = m8Var.b(18);
        b.put((byte) 2);
        b.put((byte) 3);
        new v8(a0Var, m8Var).execute(new z3(cVar));
    }
}
