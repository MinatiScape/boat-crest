package com.touchgui.sdk;

import android.location.Location;
import com.touchgui.sdk.bean.TGGpsInfo;
import com.touchgui.sdk.bean.TGGpsStatus;
import java.util.Map;
/* loaded from: classes12.dex */
public interface TGGPSManager {
    void abortSyncAgpsFile();

    void addOnSyncAgpsFileListener(TGSyncAgpsFileListener tGSyncAgpsFileListener);

    TGCommand<TGGpsInfo> getGpsInfo();

    TGCommand<TGGpsStatus> getGpsStatus();

    void removeOnSyncAgpsFileListener(TGSyncAgpsFileListener tGSyncAgpsFileListener);

    void syncAgpsFile(String str);

    void syncAgpsFile(Map<String, String> map);

    TGCommand<Void> syncAppGpsData(Location location, int i);

    TGCommand<Void> syncAppGpsStatus(int i, int i2);
}
