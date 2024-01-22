package com.touchgui.sdk;

import com.touchgui.sdk.TGDialManager;
import com.touchgui.sdk.bean.TGMyDials;
import com.touchgui.sdk.internal.ea;
import com.touchgui.sdk.internal.g5;
import com.touchgui.sdk.internal.n8;
import com.touchgui.sdk.internal.r2;
import com.touchgui.sdk.internal.u8;
import com.touchgui.sdk.internal.v8;
import com.touchgui.sdk.internal.w3;
import com.touchgui.sdk.internal.y8;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public final class j implements TGDialManager {

    /* renamed from: a  reason: collision with root package name */
    public final b f13854a;
    public final CopyOnWriteArrayList b = new CopyOnWriteArrayList();

    public j(com.touchgui.sdk.internal.a0 a0Var) {
        i iVar = new i(this);
        b bVar = new b(a0Var);
        this.f13854a = bVar;
        bVar.a(iVar);
    }

    @Override // com.touchgui.sdk.TGDialManager
    public final void addOnSyncDialListener(TGDialManager.OnSyncDialListener onSyncDialListener) {
        if (this.b.contains(onSyncDialListener)) {
            return;
        }
        this.b.add(onSyncDialListener);
    }

    @Override // com.touchgui.sdk.TGDialManager
    public final TGCommand applyDial(int i) {
        b bVar = this.f13854a;
        com.touchgui.sdk.internal.a0 a0Var = bVar.f13731a;
        TGLogger.d(a0Var, "apply watch face, id=" + i);
        com.touchgui.sdk.internal.a0 a0Var2 = bVar.f13731a;
        HashMap hashMap = u8.f13829a;
        n8 n8Var = new n8();
        n8Var.b(18).putInt(i);
        return new v8(a0Var2, n8Var);
    }

    @Override // com.touchgui.sdk.TGDialManager
    public final boolean checkDial(TGDial tGDial) {
        b bVar = this.f13854a;
        bVar.getClass();
        if (tGDial instanceof ea) {
            return bVar.a((ea) tGDial, true);
        }
        return false;
    }

    @Override // com.touchgui.sdk.TGDialManager
    public final TGCommand deleteDial(int i) {
        b bVar = this.f13854a;
        com.touchgui.sdk.internal.a0 a0Var = bVar.f13731a;
        TGLogger.d(a0Var, "delete watch face, id=" + i);
        return new v8(bVar.f13731a, u8.a(i, 2, null));
    }

    @Override // com.touchgui.sdk.TGDialManager
    public final int getDialCount() {
        byte[] bArr;
        w3 w3Var = this.f13854a.f13731a.v;
        if (w3Var == null || (bArr = w3Var.c) == null || bArr.length < 3) {
            return 0;
        }
        return bArr[2] & 15;
    }

    @Override // com.touchgui.sdk.TGDialManager
    public final int getDynamicDialCount() {
        byte[] bArr;
        w3 w3Var = this.f13854a.f13731a.v;
        if (w3Var == null || (bArr = w3Var.c) == null || bArr.length < 13) {
            return 0;
        }
        return bArr[12] & 15;
    }

    @Override // com.touchgui.sdk.TGDialManager
    public final TGCommand getMyDials() {
        return new g5(this.f13854a.f13731a, new r2());
    }

    @Override // com.touchgui.sdk.TGDialManager
    public final void removeOnSyncDialListener(TGDialManager.OnSyncDialListener onSyncDialListener) {
        this.b.remove(onSyncDialListener);
    }

    @Override // com.touchgui.sdk.TGDialManager
    public final TGCommand setMyDials(TGMyDials tGMyDials) {
        com.touchgui.sdk.internal.a0 a0Var = this.f13854a.f13731a;
        y8 y8Var = new y8((byte) 14, (byte) 1);
        int size = tGMyDials.getItems() != null ? tGMyDials.getItems().size() : 0;
        ByteBuffer b = y8Var.b((size * 5) + 1);
        b.put((byte) size);
        for (int i = 0; i < size; i++) {
            TGMyDials.ItemBean itemBean = tGMyDials.getItems().get(i);
            b.putInt(itemBean.getDialId());
            b.put((byte) itemBean.getFlag());
        }
        return new g5(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGDialManager
    public final void syncDial(TGDial tGDial) {
        b bVar = this.f13854a;
        if (bVar.j.getAndSet(true)) {
            TGLogger.w(bVar.f13731a, "Watch face transfer in progress");
        } else if (tGDial instanceof TGCloudDial) {
            bVar.a((TGCloudDial) tGDial);
        } else if (tGDial instanceof ea) {
            bVar.a((ea) tGDial, false);
        }
    }
}
