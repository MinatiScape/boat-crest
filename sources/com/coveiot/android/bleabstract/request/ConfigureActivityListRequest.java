package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.ActivityConfigMetaData;
import java.util.List;
/* loaded from: classes2.dex */
public class ConfigureActivityListRequest extends BleBaseRequest {
    public List<ActivityConfigMetaData> f;

    public ConfigureActivityListRequest(List<ActivityConfigMetaData> list) {
        this.f = list;
    }

    public List<ActivityConfigMetaData> getActivityConfigMetaDataList() {
        return this.f;
    }
}
