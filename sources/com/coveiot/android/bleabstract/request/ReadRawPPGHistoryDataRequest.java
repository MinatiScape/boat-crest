package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import java.util.Date;
/* loaded from: classes2.dex */
public class ReadRawPPGHistoryDataRequest extends BleBaseRequest {
    public Date f;
    public Date g;
    public int h;
    public int i;
    public int j = 30000;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Date f3511a;
        public Date b;
        public int c = -1;
        public int d = -1;
        public int e = 30000;
        public Object f;

        public ReadRawPPGHistoryDataRequest build() {
            ReadRawPPGHistoryDataRequest readRawPPGHistoryDataRequest = new ReadRawPPGHistoryDataRequest();
            readRawPPGHistoryDataRequest.g = this.b;
            readRawPPGHistoryDataRequest.h = this.c;
            readRawPPGHistoryDataRequest.f = this.f3511a;
            readRawPPGHistoryDataRequest.i = this.d;
            readRawPPGHistoryDataRequest.j = this.e;
            return readRawPPGHistoryDataRequest;
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
            this.f = obj;
        }

        public Builder setStartDate(Date date) {
            this.f3511a = date;
            return this;
        }

        public Builder setStartHour(int i) {
            this.c = i;
            return this;
        }

        public Builder setTimeout(int i) {
            this.e = i;
            return this;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public BleCommand getBleCommand() {
        return BleCommand.GET_RAW_PPG_HISTORY;
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

    public int getTimeout() {
        return this.j;
    }
}
