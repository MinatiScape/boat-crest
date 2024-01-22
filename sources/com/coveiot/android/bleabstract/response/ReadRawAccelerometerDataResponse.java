package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.RawAccelerometerHistoryData;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public class ReadRawAccelerometerDataResponse extends BleBaseResponse {
    public ArrayList<RawAccelerometerHistoryData> d;
    public boolean e;

    public ReadRawAccelerometerDataResponse(@NotNull BleBaseRequest bleBaseRequest, ArrayList<RawAccelerometerHistoryData> arrayList) {
        super(bleBaseRequest);
        this.d = arrayList;
    }

    public ArrayList<RawAccelerometerHistoryData> getRawAccelerometerHistoryData() {
        return this.d;
    }

    public boolean isComplete() {
        return this.e;
    }

    public void setComplete(boolean z) {
        this.e = z;
    }
}
