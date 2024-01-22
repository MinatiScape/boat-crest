package com.ido.ble.watch.custom.callback;

import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.ble.watch.custom.model.WatchPlateFileInfo;
import com.ido.ble.watch.custom.model.WatchPlateScreenInfo;
/* loaded from: classes11.dex */
public class WatchPlateCallBack {

    /* loaded from: classes11.dex */
    public interface IAutoSetPlateCallBack {
        void onFailed();

        void onProgress(int i);

        void onStart();

        void onSuccess();
    }

    /* loaded from: classes11.dex */
    public interface IOperateCallBack {
        void onDeletePlate(boolean z);

        void onGetCurrentPlate(String str);

        void onGetDialPlateParam(DialPlateParam dialPlateParam);

        void onGetPlateFileInfo(WatchPlateFileInfo watchPlateFileInfo);

        void onGetScreenInfo(WatchPlateScreenInfo watchPlateScreenInfo);

        void onSetPlate(boolean z);
    }

    /* loaded from: classes11.dex */
    public interface ISetPlatErrorCallback {
        void onFailed(int i);
    }

    public static void a(final DialPlateParam dialPlateParam) {
        a.c().a(new Runnable() { // from class: com.ido.ble.watch.custom.callback.WatchPlateCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (IOperateCallBack iOperateCallBack : a.c().a()) {
                    iOperateCallBack.onGetDialPlateParam(DialPlateParam.this);
                }
            }
        });
    }

    public static void a(final WatchPlateFileInfo watchPlateFileInfo) {
        a.c().a(new Runnable() { // from class: com.ido.ble.watch.custom.callback.WatchPlateCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (IOperateCallBack iOperateCallBack : a.c().a()) {
                    iOperateCallBack.onGetPlateFileInfo(WatchPlateFileInfo.this);
                }
            }
        });
    }

    public static void a(final WatchPlateScreenInfo watchPlateScreenInfo) {
        a.c().a(new Runnable() { // from class: com.ido.ble.watch.custom.callback.WatchPlateCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (IOperateCallBack iOperateCallBack : a.c().a()) {
                    iOperateCallBack.onGetScreenInfo(WatchPlateScreenInfo.this);
                }
            }
        });
    }

    public static void a(final String str) {
        a.c().a(new Runnable() { // from class: com.ido.ble.watch.custom.callback.WatchPlateCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                for (IOperateCallBack iOperateCallBack : a.c().a()) {
                    iOperateCallBack.onGetCurrentPlate(str);
                }
            }
        });
    }

    public static void a(final boolean z) {
        a.c().a(new Runnable() { // from class: com.ido.ble.watch.custom.callback.WatchPlateCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                for (IOperateCallBack iOperateCallBack : a.c().a()) {
                    iOperateCallBack.onDeletePlate(z);
                }
            }
        });
    }

    public static void b(final boolean z) {
        a.c().a(new Runnable() { // from class: com.ido.ble.watch.custom.callback.WatchPlateCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                for (IOperateCallBack iOperateCallBack : a.c().a()) {
                    iOperateCallBack.onSetPlate(z);
                }
            }
        });
    }
}
