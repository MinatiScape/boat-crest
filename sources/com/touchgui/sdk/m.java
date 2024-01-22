package com.touchgui.sdk;

import android.os.Handler;
import android.os.Looper;
import com.touchgui.sdk.TGLogManager;
import com.touchgui.sdk.internal.ca;
import com.touchgui.sdk.internal.da;
import com.touchgui.sdk.internal.h6;
import com.touchgui.sdk.internal.i6;
import com.touchgui.sdk.internal.v8;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public final class m implements TGLogManager, TGResponseListener {

    /* renamed from: a  reason: collision with root package name */
    public final com.touchgui.sdk.internal.a0 f13857a;
    public final Handler b = new Handler(Looper.getMainLooper());
    public final CopyOnWriteArrayList c = new CopyOnWriteArrayList();
    public int d = 2000;
    public final Runnable e = new Runnable() { // from class: com.touchgui.sdk.b0
        @Override // java.lang.Runnable
        public final void run() {
            m.this.a();
        }
    };

    public m(com.touchgui.sdk.internal.a0 a0Var) {
        this.f13857a = a0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        new v8(this.f13857a, new i6()).execute(new da(this, true));
    }

    @Override // com.touchgui.sdk.TGLogManager
    public final void abortExport() {
        TGLogger.d(this.f13857a, "abortExport");
        new v8(this.f13857a, new i6()).execute(new da(this, false));
    }

    @Override // com.touchgui.sdk.TGLogManager
    public final void addListener(TGLogManager.Listener listener) {
        if (this.c.contains(listener)) {
            return;
        }
        this.c.add(listener);
    }

    @Override // com.touchgui.sdk.TGLogManager
    public final void export(int i, int i2) {
        com.touchgui.sdk.internal.a0 a0Var = this.f13857a;
        TGLogger.d(a0Var, "export, logType=" + i);
        this.d = i2 * 1000;
        new v8(this.f13857a, new h6((byte) i)).execute(new ca(this));
    }

    @Override // com.touchgui.sdk.TGResponseListener
    public final void onResponse(byte[] bArr, int i) {
        if (i != 1) {
            return;
        }
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((TGLogManager.Listener) it.next()).onResponse(bArr);
        }
        if (this.d > 0) {
            this.b.removeCallbacks(this.e);
            this.b.postDelayed(this.e, this.d);
        }
    }

    @Override // com.touchgui.sdk.TGLogManager
    public final void removeListener(TGLogManager.Listener listener) {
        this.c.remove(listener);
    }
}
