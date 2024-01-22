package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGClient;
import com.touchgui.sdk.TGCommandBuilder;
import com.touchgui.sdk.TGConnection;
import com.touchgui.sdk.TGConnectionListener;
import com.touchgui.sdk.TGDialManager;
import com.touchgui.sdk.TGEventListener;
import com.touchgui.sdk.TGFileTransfer;
import com.touchgui.sdk.TGGPSManager;
import com.touchgui.sdk.TGHealthData;
import com.touchgui.sdk.TGHealthDataCallback;
import com.touchgui.sdk.TGHealthDataManager;
import com.touchgui.sdk.TGLogCallback;
import com.touchgui.sdk.TGLogManager;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.TGOTAManager;
import com.touchgui.sdk.TGOTAManagerProxy;
import com.touchgui.sdk.TGSportDataManager;
import com.touchgui.sdk.TGWorkoutDataCallback;
import com.touchgui.sdk.bean.TGFunctions;
import java.io.File;
/* loaded from: classes12.dex */
public final class b0 implements TGClient, TGConnection {

    /* renamed from: a  reason: collision with root package name */
    public final a0 f13744a;
    public final TGOTAManagerProxy b;
    public final aa c;
    public String d;

    public b0(a0 a0Var) {
        this.f13744a = a0Var;
        this.b = new TGOTAManagerProxy(a0Var);
        this.c = new aa(a0Var);
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final void abortDumpLog() {
        this.f13744a.f().abortExport();
    }

    @Override // com.touchgui.sdk.TGClient
    public final void addConnectionListener(TGConnectionListener tGConnectionListener) {
        a0 a0Var = this.f13744a;
        if (a0Var.g.contains(tGConnectionListener)) {
            return;
        }
        a0Var.g.add(tGConnectionListener);
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final void addEventListener(TGEventListener tGEventListener) {
        t9 t9Var = this.f13744a.i;
        if (t9Var.b.contains(tGEventListener)) {
            return;
        }
        t9Var.b.add(tGEventListener);
    }

    @Override // com.touchgui.sdk.TGConnection
    public final boolean connect() {
        return this.f13744a.a(this.d, true);
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final void disconnect() {
        this.f13744a.a();
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final void dumpLog(File file, TGLogCallback tGLogCallback) {
        TGLogManager f = this.f13744a.f();
        j3 j3Var = new j3(f);
        j3Var.c = file.getAbsolutePath();
        j3Var.d = tGLogCallback;
        f.addListener(j3Var);
        f.export(1);
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final TGCommandBuilder getCommandBuilder() {
        a0 a0Var = this.f13744a;
        if (a0Var.k == null) {
            a0Var.k = new l9(a0Var.j);
        }
        return a0Var.k;
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final int getContactCount() {
        byte[] bArr;
        a0 a0Var = this.f13744a;
        w3 w3Var = a0Var.v;
        int b = (w3Var == null || (bArr = w3Var.c) == null) ? 0 : ((s.b(bArr, 10, 2) & 896) >> 7) * 5;
        if (b == 0 && a0Var.a(TGFunctions.FUNC_BT)) {
            return 10;
        }
        return b;
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final int getDeviceId() {
        return this.f13744a.d();
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final int getDevicePlatform() {
        return this.f13744a.b;
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final TGDialManager getDialManager() {
        a0 a0Var = this.f13744a;
        if (a0Var.r == null) {
            a0Var.r = new com.touchgui.sdk.j(a0Var);
        }
        return a0Var.r;
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final TGFileTransfer getFileTransfer() {
        a0 a0Var = this.f13744a;
        if (a0Var.q == null) {
            a0Var.q = new com.touchgui.sdk.k(a0Var);
        }
        return a0Var.q;
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final TGGPSManager getGPSManager() {
        return this.c;
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final TGHealthDataManager getHealthDataManager() {
        a0 a0Var = this.f13744a;
        if (a0Var.l == null) {
            a0Var.l = new com.touchgui.sdk.l(a0Var);
        }
        return a0Var.l;
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final TGLogManager getLogManager() {
        return this.f13744a.f();
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final TGOTAManager getOtaManager() {
        return this.b;
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final int getProtocolVersion() {
        return this.f13744a.c;
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final TGSportDataManager getSportDataManager() {
        return this.f13744a.m;
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final boolean hasFunction(int i) {
        return this.f13744a.a(i);
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final boolean isConnected() {
        return this.f13744a.h();
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final boolean isReady() {
        return this.f13744a.e.f;
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final boolean isReconnecting() {
        o oVar = this.f13744a.e.b.t;
        return oVar != null && oVar.f13804a;
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final void registerHealthDataCallback(TGHealthDataCallback tGHealthDataCallback) {
        a0 a0Var = this.f13744a;
        if (a0Var.l == null) {
            a0Var.l = new com.touchgui.sdk.l(a0Var);
        }
        a0Var.l.addOnHealthDataListener(tGHealthDataCallback);
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final void registerWorkoutDataCallback(TGWorkoutDataCallback tGWorkoutDataCallback) {
        a0 a0Var = this.f13744a;
        (a0Var.i() ? a0Var.o : a0Var.n).b(tGWorkoutDataCallback);
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final void release() {
        a0 a0Var = this.f13744a;
        TGLogger.d(a0Var.c(), "release");
        q qVar = a0Var.e.b;
        synchronized (qVar.l) {
            if (qVar.c) {
                qVar.c = false;
                TGLogger.d("unregister bluetooth receiver");
                qVar.f13815a.unregisterReceiver(qVar.v);
            }
        }
        this.d = null;
    }

    @Override // com.touchgui.sdk.TGClient
    public final void removeConnectionListener(TGConnectionListener tGConnectionListener) {
        this.f13744a.g.remove(tGConnectionListener);
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final void removeEventListener(TGEventListener tGEventListener) {
        this.f13744a.i.b.remove(tGEventListener);
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final boolean syncHealthData() {
        a0 a0Var = this.f13744a;
        if (a0Var.l == null) {
            a0Var.l = new com.touchgui.sdk.l(a0Var);
        }
        a0Var.l.queryAllData();
        return true;
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final boolean syncWorkoutData() {
        a0 a0Var = this.f13744a;
        return (a0Var.i() ? a0Var.o : a0Var.n).a();
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final void unregisterHealthDataCallback(TGHealthDataCallback tGHealthDataCallback) {
        a0 a0Var = this.f13744a;
        if (a0Var.l == null) {
            a0Var.l = new com.touchgui.sdk.l(a0Var);
        }
        a0Var.l.b.remove(tGHealthDataCallback);
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final void unregisterWorkoutDataCallback(TGWorkoutDataCallback tGWorkoutDataCallback) {
        a0 a0Var = this.f13744a;
        (a0Var.i() ? a0Var.o : a0Var.n).a(tGWorkoutDataCallback);
    }

    @Override // com.touchgui.sdk.TGClient
    public final boolean connect(String str) {
        return this.f13744a.a(str, true);
    }

    @Override // com.touchgui.sdk.TGClient
    public final boolean connect(String str, boolean z) {
        return this.f13744a.a(str, z);
    }

    @Override // com.touchgui.sdk.TGClient, com.touchgui.sdk.TGConnection
    public final boolean syncHealthData(TGHealthData... tGHealthDataArr) {
        a0 a0Var = this.f13744a;
        if (a0Var.l == null) {
            a0Var.l = new com.touchgui.sdk.l(a0Var);
        }
        return a0Var.l.syncHealthData(tGHealthDataArr);
    }
}
