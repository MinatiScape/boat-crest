package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import java.util.Date;
/* loaded from: classes2.dex */
public class PeriodicSPO2BleRequest extends BleBaseRequest {
    public Date f;
    public Date g;
    public int h = -1;
    public int i = -1;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Date f3506a;
        public Date b;
        public int c = -1;
        public int d = -1;
        public Object e;

        public PeriodicSPO2BleRequest build() {
            PeriodicSPO2BleRequest periodicSPO2BleRequest = new PeriodicSPO2BleRequest();
            periodicSPO2BleRequest.g = this.b;
            periodicSPO2BleRequest.h = this.c;
            periodicSPO2BleRequest.f = this.f3506a;
            periodicSPO2BleRequest.i = this.d;
            return periodicSPO2BleRequest;
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
            this.f3506a = date;
            return this;
        }

        public Builder setStartHour(int i) {
            this.c = i;
            return this;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public BleCommand getBleCommand() {
        return BleCommand.GET_PERIODIC_SPO2;
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

    public void setEndDate(Date date) {
        this.g = date;
    }

    public void setEndHour(int i) {
        this.i = i;
    }

    public void setStartDate(Date date) {
        this.f = date;
    }

    public void setStartHour(int i) {
        this.h = i;
    }
}
