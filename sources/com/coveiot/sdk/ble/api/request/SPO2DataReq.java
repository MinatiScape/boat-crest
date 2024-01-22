package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/* loaded from: classes9.dex */
public class SPO2DataReq extends BaseRequest {
    public Date f;
    public Date g;
    public int h;
    public int i;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Date f7524a;
        public Date b;
        public int c;
        public int d;
        public Object e;

        public static Builder Builder() {
            return new Builder();
        }

        public SPO2DataReq build() {
            SPO2DataReq sPO2DataReq = new SPO2DataReq(this.e);
            sPO2DataReq.g = this.b;
            sPO2DataReq.h = this.c;
            sPO2DataReq.f = this.f7524a;
            sPO2DataReq.i = this.d;
            sPO2DataReq.f7474a = this.e;
            return sPO2DataReq;
        }

        public Builder setEndDate(Date date) {
            this.b = date;
            return this;
        }

        public Builder setEndHour(int i) {
            this.d = i;
            return this;
        }

        public Builder setId(Object obj) {
            this.e = obj;
            return this;
        }

        public Builder setStartDate(Date date) {
            this.f7524a = date;
            return this;
        }

        public Builder setStartHour(int i) {
            this.c = i;
            return this;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0091  */
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.coveiot.sdk.ble.api.request.CommandBytes> a() {
        /*
            Method dump skipped, instructions count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.sdk.ble.api.request.SPO2DataReq.a():java.util.List");
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return a();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.GET_MANUAL_SPO2;
    }

    public Date getEndDate() {
        Date date = this.g;
        return date != null ? date : new Date();
    }

    public int getEndHour() {
        int i = this.i;
        if (i == -1) {
            return 23;
        }
        return i;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public Date getStartDate() {
        if (this.f == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(5, -6);
            this.f = calendar.getTime();
        }
        return this.f;
    }

    public int getStartHour() {
        int i = this.h;
        if (i == -1) {
            return 0;
        }
        return i;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return false;
    }

    public SPO2DataReq(Object obj) {
        super(obj);
    }
}
