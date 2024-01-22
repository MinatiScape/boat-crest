package com.ido.ble.watch.custom;

import android.text.TextUtils;
import com.clevertap.android.sdk.leanplum.Constants;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.common.k;
import com.ido.ble.logs.LogTool;
import com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack;
import com.ido.ble.watch.custom.callback.WatchPlateCallBack;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import com.ido.ble.watch.custom.model.WatchPlateFileInfo;
import com.ido.ble.watch.custom.model.WatchPlateOperation;
import com.ido.ble.watch.custom.model.WatchPlateScreenInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class c {
    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 5035) {
            a(bArr);
        } else if (i == 5036) {
            c(bArr);
        } else if (i == 5501) {
            b(bArr);
        } else {
            switch (i) {
                case 5006:
                    e(bArr);
                    return;
                case 5007:
                    f(bArr);
                    return;
                case 5008:
                    d(bArr);
                    return;
                default:
                    return;
            }
        }
    }

    private static void a(byte[] bArr) {
        DialPlateParam dialPlateParam;
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            dialPlateParam = null;
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] [handleDialPlateParam] " + d);
            dialPlateParam = (DialPlateParam) k.c(d, DialPlateParam.class);
        }
        WatchPlateCallBack.a(dialPlateParam);
    }

    public static boolean a(int i) {
        if (i == 5035 || i == 5036 || i == 5501) {
            return true;
        }
        switch (i) {
            case 5006:
            case 5007:
            case 5008:
            case 5009:
                return true;
            default:
                return false;
        }
    }

    private static void b(byte[] bArr) {
        String str;
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            str = "[IDO_WATCH_PLATE] [handleGpsResult] handleGpsResult is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] [handleGpsResult] " + d);
            WatchPlateOperation watchPlateOperation = null;
            try {
                watchPlateOperation = (WatchPlateOperation) k.c(d, WatchPlateOperation.class);
            } catch (Exception e) {
                LogTool.b(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] [handleGpsResult] " + e.getMessage());
            }
            if (watchPlateOperation != null) {
                OtherProtocolCallBack.onSetCallBack(watchPlateOperation.errCode == 0 ? Boolean.TRUE : Boolean.FALSE, OtherProtocolCallBack.SettingType.GPSMAKEFILE);
                return;
            }
            str = "[IDO_WATCH_PLATE] [handleGpsResult] operation is null";
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, str);
    }

    private static void c(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            WatchPlateCallBack.a((DialPlateParam) null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] [handlePhotoWallpaperOperation] " + d);
        PhotoWallpaperOperateCallBack.a((PhotoWallpaperOperation.ResponseInfo) k.c(d, PhotoWallpaperOperation.ResponseInfo.class));
    }

    private static void d(byte[] bArr) {
        String str;
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            str = "[IDO_WATCH_PLATE] [handleWatchOperation] jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] [handleWatchOperation] " + d);
            WatchPlateOperation watchPlateOperation = null;
            try {
                watchPlateOperation = (WatchPlateOperation) k.c(d, WatchPlateOperation.class);
            } catch (Exception e) {
                LogTool.b(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] [handleWatchOperation] " + e.getMessage());
            }
            if (watchPlateOperation != null) {
                int i = watchPlateOperation.operate;
                if (i == 0) {
                    WatchPlateCallBack.a(watchPlateOperation.fileName);
                    return;
                }
                if (1 == i) {
                    WatchPlateCallBack.b(watchPlateOperation.errCode == 0);
                    return;
                } else if (2 == i) {
                    WatchPlateCallBack.a(watchPlateOperation.errCode == 0);
                    return;
                } else {
                    return;
                }
            }
            str = "[IDO_WATCH_PLATE] [handleWatchOperation] operation is null";
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, str);
    }

    private static void e(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            WatchPlateCallBack.a((WatchPlateFileInfo) null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] [handleWatchPlateInfo] " + d);
        WatchPlateFileInfo watchPlateFileInfo = new WatchPlateFileInfo();
        watchPlateFileInfo.fileNameList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(d);
            watchPlateFileInfo.availableCount = jSONObject.getInt("availableCount");
            watchPlateFileInfo.fileMaxSize = jSONObject.getInt("fileMaxSize");
            watchPlateFileInfo.version = jSONObject.getInt("version");
            JSONArray optJSONArray = jSONObject.optJSONArray(Constants.IAP_ITEM_PARAM);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    if (jSONObject2.has("fileName")) {
                        watchPlateFileInfo.fileNameList.add(jSONObject2.getString("fileName"));
                    }
                }
            }
            WatchPlateCallBack.a(watchPlateFileInfo);
        } catch (JSONException e) {
            LogTool.b(com.ido.ble.logs.a.f12307a, com.ido.ble.logs.a.m + e.getMessage());
            WatchPlateCallBack.a((WatchPlateFileInfo) null);
        }
    }

    private static void f(byte[] bArr) {
        WatchPlateScreenInfo watchPlateScreenInfo;
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            watchPlateScreenInfo = null;
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[IDO_WATCH_PLATE] [handleWatchScreenInfo] " + d);
            watchPlateScreenInfo = (WatchPlateScreenInfo) k.c(d, WatchPlateScreenInfo.class);
        }
        WatchPlateCallBack.a(watchPlateScreenInfo);
    }
}
