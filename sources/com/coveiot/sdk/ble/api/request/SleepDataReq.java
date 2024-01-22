package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.utils.CommandNames;
import com.szabh.smable3.entity.BleDeviceInfo;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/* loaded from: classes9.dex */
public class SleepDataReq extends BaseRequest {
    public Date f;
    public Date g;
    public int h;
    public int i;
    public String j;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Date f7536a;
        public Date b;
        public int c;
        public int d;
        public Object e;
        public String f = BleDeviceInfo.PROTOTYPE_V2;

        public static Builder Builder() {
            return new Builder();
        }

        public SleepDataReq build() {
            SleepDataReq sleepDataReq = new SleepDataReq(this.e);
            sleepDataReq.g = this.b;
            sleepDataReq.h = this.c;
            sleepDataReq.f = this.f7536a;
            sleepDataReq.i = this.d;
            sleepDataReq.f7474a = this.e;
            sleepDataReq.j = this.f;
            return sleepDataReq;
        }

        public Builder setDeviceType(String str) {
            this.f = str;
            return this;
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
            this.f7536a = date;
            return this;
        }

        public Builder setStartHour(int i) {
            this.c = i;
            return this;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c2, code lost:
        if (r0 > 6) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c8  */
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.coveiot.sdk.ble.api.request.CommandBytes> a() {
        /*
            Method dump skipped, instructions count: 320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.sdk.ble.api.request.SleepDataReq.a():java.util.List");
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return a();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.GET_1MIN_SLEEP_DATA;
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

    public SleepDataReq(Object obj) {
        super(obj);
        this.h = -1;
        this.i = -1;
    }
}
