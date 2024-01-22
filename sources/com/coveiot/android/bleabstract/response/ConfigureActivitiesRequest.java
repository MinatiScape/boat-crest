package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.ActivityConfigMetaData;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import java.util.List;
/* loaded from: classes2.dex */
public class ConfigureActivitiesRequest extends BleBaseRequest {
    public List<ActivityConfigMetaData> f;

    public ConfigureActivitiesRequest(List<ActivityConfigMetaData> list) {
        this.f = list;
    }

    public List<ActivityConfigMetaData> getActivityConfigMetaData() {
        return this.f;
    }
}
