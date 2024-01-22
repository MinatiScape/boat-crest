package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.VibrationModel;
import java.util.List;
/* loaded from: classes2.dex */
public class SetVibrationRequest extends BleBaseRequest {
    public List<VibrationModel> f;

    public SetVibrationRequest(List<VibrationModel> list) {
        this.f = list;
    }

    public List<VibrationModel> getVibrationModelList() {
        return this.f;
    }
}
