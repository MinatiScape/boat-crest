package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
/* loaded from: classes2.dex */
public class TodaysStepsDataRequest extends BleBaseRequest {

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f3574a;

        public TodaysStepsDataRequest build() {
            return new TodaysStepsDataRequest();
        }

        public void setId(Object obj) {
            this.f3574a = obj;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public BleCommand getBleCommand() {
        return BleCommand.GET_TODAYS_TOTAL_WALK_DATA;
    }
}
