package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.DynamicSportsField;
import java.util.List;
/* loaded from: classes2.dex */
public class SportsNotificationRequest extends BleBaseRequest {
    public List<DynamicSportsField> f;

    public SportsNotificationRequest(List<DynamicSportsField> list) {
        this.f = list;
    }

    public List<DynamicSportsField> getDynamicSportsFieldList() {
        return this.f;
    }
}
