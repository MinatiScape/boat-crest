package com.coveiot.android.bleabstract.request;

import java.util.Date;
/* loaded from: classes2.dex */
public class ActivityModeSummaryRequest extends BleBaseRequest {
    public Date f;
    public Date g;
    public String h;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Date f3470a;
        public Date b;
        public String c;

        public ActivityModeSummaryRequest build() {
            ActivityModeSummaryRequest activityModeSummaryRequest = new ActivityModeSummaryRequest();
            activityModeSummaryRequest.g = this.b;
            activityModeSummaryRequest.f = this.f3470a;
            activityModeSummaryRequest.h = this.c;
            return activityModeSummaryRequest;
        }

        public Builder setActivityMode(String str) {
            this.c = str;
            return this;
        }

        public Builder setEndDate(Date date) {
            this.b = date;
            return this;
        }

        public Builder setStartDate(Date date) {
            this.f3470a = date;
            return this;
        }
    }

    public String getActivityMode() {
        return this.h;
    }

    public Date getEndDate() {
        return this.g;
    }

    public Date getStartDate() {
        return this.f;
    }
}
