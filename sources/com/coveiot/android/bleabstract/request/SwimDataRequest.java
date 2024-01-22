package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import java.util.Date;
/* loaded from: classes2.dex */
public class SwimDataRequest extends BleBaseRequest {
    public Date f;
    public Date g;
    public int h;
    public int i;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Date f3568a;
        public Date b;
        public int c;
        public int d;
        public Object e;

        public SwimDataRequest build() {
            SwimDataRequest swimDataRequest = new SwimDataRequest();
            swimDataRequest.g = this.b;
            swimDataRequest.h = this.c;
            swimDataRequest.f = this.f3568a;
            swimDataRequest.i = this.d;
            return swimDataRequest;
        }

        public Builder setEndDate(Date date) {
            this.b = date;
            return this;
        }

        public Builder setEndHour(int i) {
            this.d = i;
            return this;
        }

        public void setId(Object obj) {
            this.e = obj;
        }

        public Builder setStartDate(Date date) {
            this.f3568a = date;
            return this;
        }

        public Builder setStartHour(int i) {
            this.c = i;
            return this;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public BleCommand getBleCommand() {
        return BleCommand.GET_SWIM_DATA;
    }

    public Date getEndDate() {
        return this.g;
    }

    public int getEndHour() {
        return this.i;
    }

    public Date getStartDate() {
        return this.f;
    }

    public int getStartHour() {
        return this.h;
    }
}
