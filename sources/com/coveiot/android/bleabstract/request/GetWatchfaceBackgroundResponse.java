package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.WatchFaceBackgroundInfo;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public class GetWatchfaceBackgroundResponse extends BleBaseResponse {
    public ArrayList<WatchFaceBackgroundInfo> d;

    public GetWatchfaceBackgroundResponse(@NotNull BleBaseRequest bleBaseRequest, ArrayList<WatchFaceBackgroundInfo> arrayList) {
        super(bleBaseRequest);
        this.d = arrayList;
    }

    public ArrayList<WatchFaceBackgroundInfo> getWatchFaceBackgroundInfos() {
        return this.d;
    }
}
