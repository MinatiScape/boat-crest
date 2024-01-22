package com.polidea.rxandroidble2.scan;

import android.os.Parcel;
import android.os.Parcelable;
import com.polidea.rxandroidble2.internal.scan.ExternalScanSettingsExtension;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes12.dex */
public class ScanSettings implements Parcelable, ExternalScanSettingsExtension<ScanSettings> {
    public static final int CALLBACK_TYPE_ALL_MATCHES = 1;
    public static final int CALLBACK_TYPE_FIRST_MATCH = 2;
    public static final int CALLBACK_TYPE_MATCH_LOST = 4;
    public static final Parcelable.Creator<ScanSettings> CREATOR = new a();
    public static final int MATCH_MODE_AGGRESSIVE = 1;
    public static final int MATCH_MODE_STICKY = 2;
    public static final int MATCH_NUM_FEW_ADVERTISEMENT = 2;
    public static final int MATCH_NUM_MAX_ADVERTISEMENT = 3;
    public static final int MATCH_NUM_ONE_ADVERTISEMENT = 1;
    public static final int SCAN_MODE_BALANCED = 1;
    public static final int SCAN_MODE_LOW_LATENCY = 2;
    public static final int SCAN_MODE_LOW_POWER = 0;
    public static final int SCAN_MODE_OPPORTUNISTIC = -1;
    public int h;
    public int i;
    public long j;
    public int k;
    public int l;
    public boolean m;
    public boolean n;

    /* loaded from: classes12.dex */
    public static final class Builder implements ExternalScanSettingsExtension.Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f13528a = 0;
        public int b = 1;
        public long c = 0;
        public int d = 1;
        public int e = 3;
        public boolean f = true;
        public boolean g = true;

        public static boolean a(int i) {
            return i == 1 || i == 2 || i == 4 || i == 6;
        }

        public ScanSettings build() {
            return new ScanSettings(this.f13528a, this.b, this.c, this.d, this.e, this.f, this.g);
        }

        public Builder setCallbackType(int i) {
            if (a(i)) {
                this.b = i;
                return this;
            }
            throw new IllegalArgumentException("invalid callback type - " + i);
        }

        public Builder setLegacy(boolean z) {
            this.f = z;
            return this;
        }

        public Builder setScanMode(int i) {
            if (i >= -1 && i <= 2) {
                this.f13528a = i;
                return this;
            }
            throw new IllegalArgumentException("invalid scan mode " + i);
        }

        @Override // com.polidea.rxandroidble2.internal.scan.ExternalScanSettingsExtension.Builder
        public Builder setShouldCheckLocationServicesState(boolean z) {
            this.g = z;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    public @interface CallbackType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    public @interface MatchMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    public @interface MatchNum {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    public @interface ScanMode {
    }

    /* loaded from: classes12.dex */
    public class a implements Parcelable.Creator<ScanSettings> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ScanSettings createFromParcel(Parcel parcel) {
            return new ScanSettings(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public ScanSettings[] newArray(int i) {
            return new ScanSettings[i];
        }
    }

    public ScanSettings(int i, int i2, long j, int i3, int i4, boolean z, boolean z2) {
        this.h = i;
        this.i = i2;
        this.j = j;
        this.l = i4;
        this.k = i3;
        this.m = z;
        this.n = z2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getCallbackType() {
        return this.i;
    }

    public boolean getLegacy() {
        return this.m;
    }

    public int getMatchMode() {
        return this.k;
    }

    public int getNumOfMatches() {
        return this.l;
    }

    public long getReportDelayMillis() {
        return this.j;
    }

    public int getScanMode() {
        return this.h;
    }

    @Override // com.polidea.rxandroidble2.internal.scan.ExternalScanSettingsExtension
    public boolean shouldCheckLocationProviderState() {
        return this.n;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeLong(this.j);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m ? 1 : 0);
        parcel.writeInt(this.n ? 1 : 0);
    }

    @Override // com.polidea.rxandroidble2.internal.scan.ExternalScanSettingsExtension
    public ScanSettings copyWithCallbackType(int i) {
        return new ScanSettings(this.h, i, this.j, this.k, this.l, this.m, this.n);
    }

    public ScanSettings(Parcel parcel) {
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = parcel.readLong();
        this.k = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readInt() != 0;
        this.n = parcel.readInt() != 0;
    }
}
