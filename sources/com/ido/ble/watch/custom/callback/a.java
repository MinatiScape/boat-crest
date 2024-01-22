package com.ido.ble.watch.custom.callback;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack;
import com.ido.ble.watch.custom.callback.WatchPlateCallBack;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class a {
    private static a d;

    /* renamed from: a  reason: collision with root package name */
    private List<WatchPlateCallBack.IOperateCallBack> f12320a = new ArrayList();
    private List<PhotoWallpaperOperateCallBack.ICallBack> b = new ArrayList();
    private Handler c = new Handler(Looper.getMainLooper());

    private a() {
    }

    public static a c() {
        if (d == null) {
            d = new a();
        }
        return d;
    }

    public List<WatchPlateCallBack.IOperateCallBack> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f12320a);
        return arrayList;
    }

    public void a(PhotoWallpaperOperateCallBack.ICallBack iCallBack) {
        this.b.add(iCallBack);
    }

    public void a(WatchPlateCallBack.IOperateCallBack iOperateCallBack) {
        this.f12320a.add(iOperateCallBack);
    }

    public void a(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            this.c.post(runnable);
        }
    }

    public List<PhotoWallpaperOperateCallBack.ICallBack> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.b);
        return arrayList;
    }

    public void b(PhotoWallpaperOperateCallBack.ICallBack iCallBack) {
        this.b.remove(iCallBack);
    }

    public void b(WatchPlateCallBack.IOperateCallBack iOperateCallBack) {
        this.f12320a.remove(iOperateCallBack);
    }
}
