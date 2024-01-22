package com.ido.ble.gps.callback;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.gps.callback.GpsCallBack;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class a {
    private static a i;

    /* renamed from: a  reason: collision with root package name */
    private List<GpsCallBack.IGetGpsInfoCallBack> f12297a = new ArrayList();
    private List<GpsCallBack.IDeviceReplySetGpsCallBack> b = new ArrayList();
    private List<GpsCallBack.ISyncGpsDataCallBack> c = new ArrayList();
    private List<GpsCallBack.ITranAgpsFileCallBack> d = new ArrayList();
    private List<GpsCallBack.ISPPTranFileCallBack> e = new ArrayList();
    private List<GpsCallBack.IMp3ConvertCallBack> f = new ArrayList();
    private List<GpsCallBack.ITranAgpsWatchErrorCallBack> g = new ArrayList();
    private Handler h = new Handler(Looper.getMainLooper());

    public static a h() {
        if (i == null) {
            i = new a();
        }
        return i;
    }

    public List<GpsCallBack.IDeviceReplySetGpsCallBack> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.b);
        return arrayList;
    }

    public void a(GpsCallBack.IDeviceReplySetGpsCallBack iDeviceReplySetGpsCallBack) {
        this.b.add(iDeviceReplySetGpsCallBack);
    }

    public void a(GpsCallBack.IGetGpsInfoCallBack iGetGpsInfoCallBack) {
        this.f12297a.add(iGetGpsInfoCallBack);
    }

    public void a(GpsCallBack.IMp3ConvertCallBack iMp3ConvertCallBack) {
        this.f.add(iMp3ConvertCallBack);
    }

    public void a(GpsCallBack.ISPPTranFileCallBack iSPPTranFileCallBack) {
        this.e.add(iSPPTranFileCallBack);
    }

    public void a(GpsCallBack.ISyncGpsDataCallBack iSyncGpsDataCallBack) {
        this.c.add(iSyncGpsDataCallBack);
    }

    public void a(GpsCallBack.ITranAgpsFileCallBack iTranAgpsFileCallBack) {
        this.d.add(iTranAgpsFileCallBack);
    }

    public void a(GpsCallBack.ITranAgpsWatchErrorCallBack iTranAgpsWatchErrorCallBack) {
        this.g.add(iTranAgpsWatchErrorCallBack);
    }

    public void a(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            this.h.post(runnable);
        }
    }

    public List<GpsCallBack.IGetGpsInfoCallBack> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f12297a);
        return arrayList;
    }

    public void b(GpsCallBack.IDeviceReplySetGpsCallBack iDeviceReplySetGpsCallBack) {
        this.b.remove(iDeviceReplySetGpsCallBack);
    }

    public void b(GpsCallBack.IGetGpsInfoCallBack iGetGpsInfoCallBack) {
        this.f12297a.remove(iGetGpsInfoCallBack);
    }

    public void b(GpsCallBack.IMp3ConvertCallBack iMp3ConvertCallBack) {
        this.f.remove(iMp3ConvertCallBack);
    }

    public void b(GpsCallBack.ISPPTranFileCallBack iSPPTranFileCallBack) {
        this.e.remove(iSPPTranFileCallBack);
    }

    public void b(GpsCallBack.ISyncGpsDataCallBack iSyncGpsDataCallBack) {
        this.c.remove(iSyncGpsDataCallBack);
    }

    public void b(GpsCallBack.ITranAgpsFileCallBack iTranAgpsFileCallBack) {
        this.d.remove(iTranAgpsFileCallBack);
    }

    public void b(GpsCallBack.ITranAgpsWatchErrorCallBack iTranAgpsWatchErrorCallBack) {
        this.g.remove(iTranAgpsWatchErrorCallBack);
    }

    public List<GpsCallBack.IMp3ConvertCallBack> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f);
        return arrayList;
    }

    public List<GpsCallBack.ISPPTranFileCallBack> d() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.e);
        return arrayList;
    }

    public List<GpsCallBack.ISyncGpsDataCallBack> e() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.c);
        return arrayList;
    }

    public List<GpsCallBack.ITranAgpsFileCallBack> f() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.d);
        return arrayList;
    }

    public List<GpsCallBack.ITranAgpsWatchErrorCallBack> g() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.g);
        return arrayList;
    }
}
