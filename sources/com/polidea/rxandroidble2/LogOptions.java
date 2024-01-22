package com.polidea.rxandroidble2;

import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public class LogOptions {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Integer f13369a;
    @Nullable
    public final Integer b;
    @Nullable
    public final Integer c;
    @Nullable
    public final Boolean d;
    @Nullable
    public final Boolean e;
    @Nullable
    public final Logger f;

    /* loaded from: classes9.dex */
    public static class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public Integer f13370a;
        @Nullable
        public Integer b;
        @Nullable
        public Integer c;
        @Nullable
        public Boolean d;
        @Nullable
        public Boolean e;
        @Nullable
        public Logger f;

        public LogOptions build() {
            return new LogOptions(this.f13370a, this.b, this.c, this.d, this.e, this.f);
        }

        public Builder setLogLevel(@Nullable Integer num) {
            this.f13370a = num;
            return this;
        }

        public Builder setLogger(@Nullable Logger logger) {
            this.f = logger;
            return this;
        }

        public Builder setMacAddressLogSetting(@Nullable Integer num) {
            this.b = num;
            return this;
        }

        public Builder setShouldLogAttributeValues(@Nullable Boolean bool) {
            this.d = bool;
            return this;
        }

        public Builder setShouldLogScannedPeripherals(@Nullable Boolean bool) {
            this.e = bool;
            return this;
        }

        public Builder setUuidsLogSetting(@Nullable Integer num) {
            this.c = num;
            return this;
        }
    }

    /* loaded from: classes9.dex */
    public interface Logger {
        void log(int i, String str, String str2);
    }

    public LogOptions(@Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Logger logger) {
        this.f13369a = num;
        this.b = num2;
        this.c = num3;
        this.d = bool;
        this.e = bool2;
        this.f = logger;
    }

    @Nullable
    public Integer getLogLevel() {
        return this.f13369a;
    }

    @Nullable
    public Logger getLogger() {
        return this.f;
    }

    @Nullable
    public Integer getMacAddressLogSetting() {
        return this.b;
    }

    @Nullable
    public Boolean getShouldLogAttributeValues() {
        return this.d;
    }

    @Nullable
    public Boolean getShouldLogScannedPeripherals() {
        return this.e;
    }

    @Nullable
    public Integer getUuidLogSetting() {
        return this.c;
    }

    public String toString() {
        return "LogOptions{logLevel=" + this.f13369a + ", macAddressLogSetting=" + this.b + ", uuidLogSetting=" + this.c + ", shouldLogAttributeValues=" + this.d + ", shouldLogScannedPeripherals=" + this.e + ", logger=" + this.f + '}';
    }
}
