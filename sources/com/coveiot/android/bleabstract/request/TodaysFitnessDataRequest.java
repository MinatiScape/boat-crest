package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
/* loaded from: classes2.dex */
public class TodaysFitnessDataRequest extends BleBaseRequest {

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f3573a;

        public TodaysFitnessDataRequest build() {
            return new TodaysFitnessDataRequest();
        }

        public void setId(Object obj) {
            this.f3573a = obj;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public BleCommand getBleCommand() {
        return BleCommand.GET_TODAY_FITNESS_VALUE;
    }
}
