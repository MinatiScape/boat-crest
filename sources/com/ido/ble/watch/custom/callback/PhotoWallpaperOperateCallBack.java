package com.ido.ble.watch.custom.callback;

import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
/* loaded from: classes11.dex */
public class PhotoWallpaperOperateCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onOperateResult(PhotoWallpaperOperation.ResponseInfo responseInfo);
    }

    public static void a(final PhotoWallpaperOperation.ResponseInfo responseInfo) {
        a.c().a(new Runnable() { // from class: com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : a.c().b()) {
                    iCallBack.onOperateResult(PhotoWallpaperOperation.ResponseInfo.this);
                }
            }
        });
    }
}
