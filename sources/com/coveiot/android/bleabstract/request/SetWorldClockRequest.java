package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SetWorldClockRequest extends BleBaseRequest {
    public int f;
    @Nullable
    public String g;
    public int h;
    public int i;
    public int j;
    public double k;
    public double l;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3558a;
        @Nullable
        public String b;
        public int c;
        public int d;
        public int e;
        public double f;
        public double g;

        @NotNull
        public final SetWorldClockRequest build() {
            SetWorldClockRequest setWorldClockRequest = new SetWorldClockRequest();
            setWorldClockRequest.setId(this.f3558a);
            setWorldClockRequest.setCityName(this.b);
            setWorldClockRequest.setTimeZoneOffsetMinutes(this.c);
            setWorldClockRequest.setSunRise(this.e);
            setWorldClockRequest.setSunSet(this.d);
            setWorldClockRequest.setLatitude(this.f);
            setWorldClockRequest.setLongitude(this.g);
            return setWorldClockRequest;
        }

        @Nullable
        public final String getCityName() {
            return this.b;
        }

        public final int getId() {
            return this.f3558a;
        }

        public final double getLatitude() {
            return this.f;
        }

        public final double getLongitude() {
            return this.g;
        }

        public final int getSunRise() {
            return this.e;
        }

        public final int getSunSet() {
            return this.d;
        }

        public final int getTimeZoneOffsetMinutes() {
            return this.c;
        }

        /* renamed from: setCityName  reason: collision with other method in class */
        public final void m63setCityName(@Nullable String str) {
            this.b = str;
        }

        /* renamed from: setId  reason: collision with other method in class */
        public final void m64setId(int i) {
            this.f3558a = i;
        }

        /* renamed from: setLatitude  reason: collision with other method in class */
        public final void m65setLatitude(double d) {
            this.f = d;
        }

        /* renamed from: setLongitude  reason: collision with other method in class */
        public final void m66setLongitude(double d) {
            this.g = d;
        }

        /* renamed from: setSunRise  reason: collision with other method in class */
        public final void m67setSunRise(int i) {
            this.e = i;
        }

        /* renamed from: setSunSet  reason: collision with other method in class */
        public final void m68setSunSet(int i) {
            this.d = i;
        }

        @NotNull
        public final Builder setTimeZoneOffset(int i) {
            this.c = i;
            return this;
        }

        public final void setTimeZoneOffsetMinutes(int i) {
            this.c = i;
        }

        @NotNull
        public final Builder setCityName(@Nullable String str) {
            this.b = str;
            return this;
        }

        @NotNull
        public final Builder setId(int i) {
            this.f3558a = i;
            return this;
        }

        @NotNull
        public final Builder setLatitude(double d) {
            this.f = d;
            return this;
        }

        @NotNull
        public final Builder setLongitude(double d) {
            this.g = d;
            return this;
        }

        @NotNull
        public final Builder setSunRise(int i) {
            this.e = i;
            return this;
        }

        @NotNull
        public final Builder setSunSet(int i) {
            this.d = i;
            return this;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.SET_WORLD_CLOCK;
    }

    @Nullable
    public final String getCityName() {
        return this.g;
    }

    public final int getId() {
        return this.f;
    }

    public final double getLatitude() {
        return this.k;
    }

    public final double getLongitude() {
        return this.l;
    }

    public final int getSunRise() {
        return this.j;
    }

    public final int getSunSet() {
        return this.i;
    }

    public final int getTimeZoneOffsetMinutes() {
        return this.h;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public void setBleCommand(@Nullable BleCommand bleCommand) {
        super.setBleCommand(bleCommand);
    }

    public final void setCityName(@Nullable String str) {
        this.g = str;
    }

    public final void setId(int i) {
        this.f = i;
    }

    public final void setLatitude(double d) {
        this.k = d;
    }

    public final void setLongitude(double d) {
        this.l = d;
    }

    public final void setSunRise(int i) {
        this.j = i;
    }

    public final void setSunSet(int i) {
        this.i = i;
    }

    public final void setTimeZoneOffsetMinutes(int i) {
        this.h = i;
    }
}
