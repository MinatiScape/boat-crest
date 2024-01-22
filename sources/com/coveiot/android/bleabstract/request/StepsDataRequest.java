package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import java.util.Date;
/* loaded from: classes2.dex */
public class StepsDataRequest extends BleBaseRequest {
    public Date f;
    public Date g;
    public int h;
    public int i;
    public int j;
    public int k;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Date f3564a;
        public Date b;
        public Object e;
        public int c = -1;
        public int d = -1;
        public int f = -1;
        public int g = -1;

        public StepsDataRequest build() {
            StepsDataRequest stepsDataRequest = new StepsDataRequest();
            stepsDataRequest.g = this.b;
            stepsDataRequest.h = this.c;
            stepsDataRequest.f = this.f3564a;
            stepsDataRequest.i = this.d;
            stepsDataRequest.j = this.f;
            stepsDataRequest.k = this.g;
            return stepsDataRequest;
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

        public Builder setRunStrideLength(int i) {
            this.g = i;
            return this;
        }

        public Builder setStartDate(Date date) {
            this.f3564a = date;
            return this;
        }

        public Builder setStartHour(int i) {
            this.c = i;
            return this;
        }

        public Builder setStrideLength(int i) {
            this.f = i;
            return this;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public BleCommand getBleCommand() {
        return BleCommand.GET_STEPS_DATA;
    }

    public Date getEndDate() {
        return this.g;
    }

    public int getEndHour() {
        return this.i;
    }

    public int getRunStrideLength() {
        return this.k;
    }

    public Date getStartDate() {
        return this.f;
    }

    public int getStartHour() {
        return this.h;
    }

    public int getStrideLength() {
        return this.j;
    }
}
