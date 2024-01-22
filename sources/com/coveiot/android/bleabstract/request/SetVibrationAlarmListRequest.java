package com.coveiot.android.bleabstract.request;

import java.util.List;
/* loaded from: classes2.dex */
public class SetVibrationAlarmListRequest extends BleBaseRequest {
    public List<SetVibrationAlarmRequest> f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public List<SetVibrationAlarmRequest> f3552a;

        public SetVibrationAlarmListRequest build() {
            SetVibrationAlarmListRequest setVibrationAlarmListRequest = new SetVibrationAlarmListRequest();
            setVibrationAlarmListRequest.f = this.f3552a;
            return setVibrationAlarmListRequest;
        }

        public Builder setSedentaryReminderList(List<SetVibrationAlarmRequest> list) {
            this.f3552a = list;
            return this;
        }
    }

    public List<SetVibrationAlarmRequest> getVibrationAlarmRequests() {
        return this.f;
    }
}
