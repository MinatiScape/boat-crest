package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.RawPPGHistoryData;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public class ReadRawPPGDataResponse extends BleBaseResponse {
    public ArrayList<RawPPGHistoryData> d;
    public boolean e;

    public ReadRawPPGDataResponse(@NotNull BleBaseRequest bleBaseRequest, ArrayList<RawPPGHistoryData> arrayList) {
        super(bleBaseRequest);
        this.d = arrayList;
    }

    public ArrayList<RawPPGHistoryData> getPpgHistoryDataArrayList() {
        return this.d;
    }

    public boolean isComplete() {
        return this.e;
    }

    public void setComplete(boolean z) {
        this.e = z;
    }
}
