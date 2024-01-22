package com.realsil.sdk.dfu.image.wrapper;

import android.os.Parcel;
import android.os.Parcelable;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.i.a;
import com.realsil.sdk.dfu.image.BinIndicator;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.utils.DfuUtils;
import java.util.Locale;
/* loaded from: classes12.dex */
public final class SocImageWrapper extends a implements Parcelable {
    public static final Parcelable.Creator<SocImageWrapper> CREATOR = new Parcelable.Creator<SocImageWrapper>() { // from class: com.realsil.sdk.dfu.image.wrapper.SocImageWrapper.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SocImageWrapper createFromParcel(Parcel parcel) {
            return new SocImageWrapper(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SocImageWrapper[] newArray(int i) {
            return new SocImageWrapper[i];
        }
    };
    public static boolean VDBG = false;

    /* renamed from: a  reason: collision with root package name */
    public int f13609a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public String m;
    public String n;

    /* loaded from: classes12.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f13610a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g = 15;
        public OtaDeviceInfo h;

        public SocImageWrapper build() {
            OtaDeviceInfo otaDeviceInfo = this.h;
            if (otaDeviceInfo != null) {
                this.f13610a = otaDeviceInfo.getProtocolType();
                OtaDeviceInfo otaDeviceInfo2 = this.h;
                this.b = otaDeviceInfo2.specVersion;
                this.c = otaDeviceInfo2.icType;
                this.g = otaDeviceInfo2.getActiveBank();
            }
            return new SocImageWrapper(this.f13610a, this.b, this.c, this.d, this.e, this.f, this.g);
        }

        public Builder setBankIndicator(int i) {
            this.g = i;
            return this;
        }

        public Builder setBitNumber(int i) {
            this.e = i;
            return this;
        }

        public Builder setDeviceInfo(OtaDeviceInfo otaDeviceInfo) {
            this.h = otaDeviceInfo;
            return this;
        }

        public Builder setIcType(int i) {
            this.c = i;
            return this;
        }

        public Builder setImageId(int i) {
            this.f = i;
            return this;
        }

        public Builder setImageVersion(int i) {
            this.d = i;
            return this;
        }

        public Builder setProtocolType(int i) {
            this.f13610a = i;
            return this;
        }

        public Builder setSpecVersion(int i) {
            this.b = i;
            return this;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0038 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(int r4, int r5, boolean r6) {
        /*
            r3 = this;
            r0 = 5
            r1 = 3
            if (r4 > r1) goto L6
            r0 = 7
            goto L3d
        L6:
            if (r4 == r0) goto L3c
            r2 = 9
            if (r4 == r2) goto L3c
            r2 = 12
            if (r4 != r2) goto L11
            goto L3c
        L11:
            r2 = 10145(0x27a1, float:1.4216E-41)
            if (r5 == r2) goto L3a
            r2 = 10148(0x27a4, float:1.422E-41)
            if (r5 == r2) goto L3c
            switch(r5) {
                case 10128: goto L36;
                case 10129: goto L3a;
                case 10130: goto L3a;
                case 10131: goto L3d;
                case 10132: goto L33;
                case 10133: goto L33;
                case 10134: goto L3c;
                case 10135: goto L20;
                default: goto L1c;
            }
        L1c:
            switch(r5) {
                case 10140: goto L3a;
                case 10141: goto L3a;
                case 10142: goto L3a;
                default: goto L1f;
            }
        L1f:
            goto L38
        L20:
            r5 = 11
            if (r4 == r5) goto L30
            r5 = 13
            if (r4 == r5) goto L30
            r5 = 10
            if (r4 != r5) goto L2d
            goto L30
        L2d:
            r0 = 514(0x202, float:7.2E-43)
            goto L3d
        L30:
            r0 = 516(0x204, float:7.23E-43)
            goto L3d
        L33:
            r0 = 515(0x203, float:7.22E-43)
            goto L3d
        L36:
            if (r6 == 0) goto L3c
        L38:
            r0 = 1
            goto L3d
        L3a:
            r0 = r1
            goto L3d
        L3c:
            r0 = 2
        L3d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.image.wrapper.SocImageWrapper.a(int, int, boolean):int");
    }

    public final void a() {
        if (this.f13609a == 20) {
            c();
        } else {
            b();
        }
    }

    public final void b() {
        int i = this.h;
        if (i == 1) {
            if (this.b <= 0) {
                int i2 = this.d;
                this.i = i2;
                this.j = 0;
                this.k = 0;
                this.l = 0;
                this.m = String.valueOf(i2);
            } else {
                int i3 = this.d;
                int i4 = i3 & 255;
                this.i = i4;
                this.j = (i3 >> 8) & 255;
                this.k = (i3 >> 16) & 255;
                this.l = (i3 >> 24) & 255;
                this.m = String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i4), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l));
            }
        } else if (i == 2) {
            if (this.b <= 0) {
                int i5 = this.d;
                this.i = i5;
                this.j = 0;
                this.k = 0;
                this.l = 0;
                this.m = String.valueOf(i5);
            } else {
                int i6 = this.d;
                int i7 = (i6 >> 24) & 255;
                this.i = i7;
                this.j = (i6 >> 16) & 255;
                this.k = (i6 >> 8) & 255;
                this.l = i6 & 255;
                this.m = String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i7), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l));
            }
        } else if (i == 3) {
            if (this.b <= 0) {
                int i8 = this.d;
                this.i = i8;
                this.j = 0;
                this.k = 0;
                this.l = 0;
                this.m = String.valueOf(i8);
            } else {
                int i9 = this.d;
                int i10 = i9 & 15;
                this.i = i10;
                this.j = (i9 >> 4) & 255;
                this.k = (i9 >> 12) & 32767;
                this.l = (i9 >> 27) & 31;
                this.m = String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i10), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l));
            }
        } else if (i == 5) {
            if (this.b <= 0) {
                int i11 = this.d;
                this.i = i11;
                this.j = 0;
                this.k = 0;
                this.l = 0;
                this.m = String.valueOf(i11);
            } else {
                int i12 = this.d;
                int i13 = i12 & 15;
                this.i = i13;
                this.j = (i12 >> 4) & 255;
                this.k = (i12 >> 12) & 511;
                this.l = (i12 >> 21) & 2047;
                this.m = String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i13), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l));
            }
        } else if (i == 515) {
            if (this.b <= 0) {
                int i14 = this.d;
                this.i = i14;
                this.j = 0;
                this.k = 0;
                this.l = 0;
                this.m = String.valueOf(i14);
            } else {
                int i15 = this.d;
                int i16 = (i15 >> 24) & 255;
                this.i = i16;
                this.j = (i15 >> 16) & 255;
                this.k = (i15 >> 8) & 255;
                this.l = i15 & 255;
                this.m = String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i16), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l));
            }
        } else if (i == 4) {
            int i17 = this.d;
            this.i = i17;
            this.j = 0;
            this.k = 0;
            this.l = 0;
            this.m = String.valueOf(i17);
        } else if (i == 7) {
            int i18 = this.d;
            this.i = i18;
            this.j = 0;
            this.k = 0;
            this.l = 0;
            this.m = String.valueOf(i18);
        } else if (i == 514) {
            if (this.b <= 0) {
                int i19 = this.d;
                this.i = i19;
                this.j = 0;
                this.k = 0;
                this.l = 0;
                this.m = String.valueOf(i19);
            } else {
                int i20 = this.d;
                int i21 = (i20 >> 8) & 255;
                this.i = i21;
                this.j = i20 & 255;
                this.k = (i20 >> 24) & 255;
                this.l = (i20 >> 16) & 255;
                this.m = String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i21), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l));
            }
        } else if (i == 516) {
            int i22 = this.d;
            int i23 = (i22 >> 24) & 255;
            this.i = i23;
            this.j = (i22 >> 16) & 255;
            this.k = (i22 >> 8) & 255;
            this.l = i22 & 255;
            this.m = String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i23), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l));
        } else {
            int i24 = this.d;
            this.i = i24;
            this.j = 0;
            this.k = 0;
            this.l = 0;
            this.m = String.valueOf(i24);
        }
        if (this.d == -1) {
            this.m = "";
        }
    }

    public final void c() {
        int i = this.h;
        if (i == 1) {
            int i2 = this.d;
            int i3 = i2 & 255;
            this.i = i3;
            this.j = (i2 >> 8) & 255;
            this.k = (i2 >> 16) & 255;
            this.l = (i2 >> 24) & 255;
            this.m = String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i3), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l));
        } else if (i == 2) {
            int i4 = this.d;
            int i5 = (i4 >> 24) & 255;
            this.i = i5;
            this.j = (i4 >> 16) & 255;
            this.k = (i4 >> 8) & 255;
            this.l = i4 & 255;
            this.m = String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i5), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l));
        } else if (i == 3) {
            int i6 = this.d;
            int i7 = i6 & 15;
            this.i = i7;
            this.j = (i6 >> 4) & 255;
            this.k = (i6 >> 12) & 32767;
            this.l = (i6 >> 27) & 31;
            this.m = String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i7), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l));
        } else if (i == 5) {
            int i8 = this.d;
            int i9 = i8 & 15;
            this.i = i9;
            this.j = (i8 >> 4) & 255;
            this.k = (i8 >> 12) & 511;
            this.l = (i8 >> 21) & 2047;
            this.m = String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i9), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l));
        } else if (i == 515) {
            int i10 = this.d;
            int i11 = (i10 >> 24) & 255;
            this.i = i11;
            this.j = (i10 >> 16) & 255;
            this.k = (i10 >> 8) & 255;
            this.l = i10 & 255;
            this.m = String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i11), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l));
        } else if (i == 4) {
            int i12 = this.d;
            this.i = i12;
            this.j = 0;
            this.k = 0;
            this.l = 0;
            this.m = String.valueOf(i12);
        } else if (i == 7) {
            int i13 = this.d;
            this.i = i13;
            this.j = 0;
            this.k = 0;
            this.l = 0;
            this.m = String.valueOf(i13);
        } else if (i == 514) {
            int i14 = this.d;
            int i15 = (i14 >> 8) & 255;
            this.i = i15;
            this.j = i14 & 255;
            this.k = (i14 >> 24) & 255;
            this.l = (i14 >> 16) & 255;
            this.m = String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i15), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l));
        } else if (i == 516) {
            int i16 = this.d;
            int i17 = (i16 >> 24) & 255;
            this.i = i17;
            this.j = (i16 >> 16) & 255;
            this.k = (i16 >> 8) & 255;
            this.l = i16 & 255;
            this.m = String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i17), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l));
        } else {
            int i18 = this.d;
            this.i = i18;
            this.j = 0;
            this.k = 0;
            this.l = 0;
            this.m = String.valueOf(i18);
        }
        if (this.d == -1) {
            this.m = "";
        }
    }

    public int compare(SocImageWrapper socImageWrapper) {
        if (this.i > socImageWrapper.getMajor()) {
            return 1;
        }
        if (this.i == socImageWrapper.getMajor()) {
            if (this.j > socImageWrapper.getMinor()) {
                return 1;
            }
            if (this.j == socImageWrapper.getMinor()) {
                if (this.k > socImageWrapper.getRevision()) {
                    return 1;
                }
                if (this.k == socImageWrapper.getRevision()) {
                    if (this.l > socImageWrapper.getBuildnum()) {
                        return 1;
                    }
                    if (this.l == socImageWrapper.getBuildnum()) {
                        return 0;
                    }
                }
            }
        }
        return -1;
    }

    public final void d() {
        if (-1 == this.d) {
            this.g = 0;
            this.i = 0;
            this.j = 0;
            this.k = 0;
            this.l = 0;
            this.m = "";
            return;
        }
        if (VDBG) {
            ZLogger.v(String.format("protocolType=%04X,specVersion=%02X", Integer.valueOf(this.f13609a), Integer.valueOf(this.b)));
        }
        int i = this.f13609a;
        if (i == 20) {
            this.h = a(this.c, this.f, this.g != 15);
            this.n = BinIndicator.parseImageId(this.c, this.f);
        } else if (i == 17) {
            if (this.b >= 6) {
                this.h = a(this.c, this.f, this.g != 15);
                this.n = BinIndicator.parseImageId(this.c, this.f);
            } else {
                this.h = DfuUtils.getImageVersionFormatWithBitNumber(this.c, this.e);
                this.n = BinIndicator.parseBitNumber(this.c, this.e);
            }
        } else {
            this.h = DfuUtils.getImageVersionFormatWithBitNumber(this.c, this.e);
            this.n = BinIndicator.parseBitNumber(this.c, this.e);
        }
        a();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getBankIndicator() {
        return this.g;
    }

    public int getBitNumber() {
        return this.e;
    }

    public int getBuildnum() {
        return this.l;
    }

    public String getFlashLayoutName() {
        return this.n;
    }

    public int getFormat() {
        return this.h;
    }

    public String getFormatedVersion() {
        return this.m;
    }

    public int getIcType() {
        return this.c;
    }

    public int getImageId() {
        return this.f;
    }

    public int getImageVersion() {
        return this.d;
    }

    public int getMajor() {
        return this.i;
    }

    public int getMinor() {
        return this.j;
    }

    public int getProtocolType() {
        return this.f13609a;
    }

    public int getRevision() {
        return this.k;
    }

    public int getSpecVersion() {
        return this.b;
    }

    public String toString() {
        return String.format(Locale.US, "0x%04X(%s), I%02XPT%04XSV%02X, imageVersion=[%08X], format=[%d]\nformatedVersion=(%d.%d.%d.%d)->[%s]", Integer.valueOf(this.f), this.n, Integer.valueOf(this.c), Integer.valueOf(this.f13609a), Integer.valueOf(this.b), Integer.valueOf(this.d), Integer.valueOf(this.h), Integer.valueOf(this.i), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l), this.m);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f13609a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
    }

    public SocImageWrapper(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.g = 15;
        this.f13609a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
        this.g = i7;
        d();
    }

    public SocImageWrapper(Parcel parcel) {
        this.g = 15;
        this.f13609a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.readInt();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readString();
        this.n = parcel.readString();
    }
}
