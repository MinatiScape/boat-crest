package com.realsil.sdk.dfu.image;

import android.content.Context;
import android.text.TextUtils;
import com.realsil.sdk.dfu.k.a;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import java.util.Locale;
/* loaded from: classes12.dex */
public final class LoadParams {

    /* renamed from: a  reason: collision with root package name */
    public Context f13604a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public String h;
    public String i;
    public int j;
    public OtaDeviceInfo k;
    public boolean l;
    public int m;
    public boolean n;
    public boolean o;
    public boolean p;
    public int q;
    public boolean r;
    public boolean s;
    public int t;

    /* loaded from: classes12.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Context f13605a;
        public String c;
        public String d;
        public OtaDeviceInfo k;
        public int q;
        public int b = 0;
        public int e = 3;
        public int f = 0;
        public int g = 0;
        public int h = 0;
        public int i = 1;
        public int j = -1;
        public boolean l = false;
        public int m = 0;
        public boolean n = false;
        public boolean o = true;
        public boolean p = false;
        public boolean r = false;
        public boolean s = false;
        public int t = 15;

        public LoadParams build() {
            OtaDeviceInfo otaDeviceInfo = this.k;
            if (otaDeviceInfo != null) {
                this.e = otaDeviceInfo.icType;
                this.f = otaDeviceInfo.protocolType;
                this.g = otaDeviceInfo.specVersion;
                this.t = otaDeviceInfo.getUpdateBank();
                boolean isBankEnabled = this.k.isBankEnabled();
                this.s = isBankEnabled;
                OtaDeviceInfo otaDeviceInfo2 = this.k;
                if (otaDeviceInfo2.protocolType == 17) {
                    if (isBankEnabled) {
                        this.o = false;
                    }
                    if (otaDeviceInfo2.specVersion >= 6) {
                        if (otaDeviceInfo2.getRwsMode() == 2) {
                            this.h = 4;
                            if (this.k.getBudRole() == 1) {
                                this.i = 1;
                            } else if (this.k.getBudRole() == 2) {
                                this.i = 0;
                            }
                        } else if (this.k.getRwsMode() == 1) {
                            this.h = 4;
                            if (this.k.getBudRole() == 1) {
                                this.i = 1;
                            } else if (this.k.getBudRole() == 2) {
                                this.i = 0;
                            }
                            if (this.k.isBankEnabled()) {
                                this.h |= 2;
                            } else {
                                int i = this.h | 1;
                                this.h = i;
                                this.h = i | 2;
                            }
                        } else if (this.k.isBankEnabled()) {
                            this.h = 2;
                        } else {
                            this.h = 3;
                        }
                        if (this.q == 19) {
                            int i2 = this.h | 1;
                            this.h = i2;
                            this.h = i2 | 2;
                            if (this.k.isBankEnabled()) {
                                this.t = this.k.getActiveBank();
                            }
                        }
                    } else {
                        if (otaDeviceInfo2.getRwsMode() == 2) {
                            this.h = 4;
                            if (this.k.getBudRole() == 1) {
                                this.i = 1;
                            } else if (this.k.getBudRole() == 2) {
                                this.i = 0;
                            }
                        } else if (this.k.getRwsMode() == 1) {
                            this.h = 4;
                            if (this.k.getBudRole() == 1) {
                                this.i = 1;
                            } else if (this.k.getBudRole() == 2) {
                                this.i = 0;
                            }
                            if (this.k.isBankEnabled()) {
                                this.h |= 2;
                            } else {
                                int i3 = this.h | 1;
                                this.h = i3;
                                this.h = i3 | 2;
                            }
                        } else if (this.k.isBankEnabled()) {
                            this.h = 2;
                        } else {
                            this.h = 3;
                        }
                        OtaDeviceInfo otaDeviceInfo3 = this.k;
                        if (otaDeviceInfo3.specVersion >= 5 && this.q == 19) {
                            int i4 = this.h | 1;
                            this.h = i4;
                            this.h = i4 | 2;
                            if (otaDeviceInfo3.isBankEnabled()) {
                                this.t = this.k.getActiveBank();
                            }
                        }
                    }
                } else {
                    int i5 = otaDeviceInfo2.icType;
                    if (i5 <= 3 && this.q == 0) {
                        this.s = false;
                    }
                    if (!this.s) {
                        this.h = 3;
                    } else if (i5 != 5 && i5 != 9 && i5 != 12) {
                        this.h = 2;
                    } else {
                        this.h = 2;
                    }
                }
            } else {
                this.n = false;
                this.l = false;
                this.o = false;
                this.h = 3;
            }
            return new LoadParams(this.f13605a, this.e, this.f, this.g, this.h, this.b, this.c, this.d, this.j, this.s, this.t, this.k, this.l, this.m, this.n, this.o, this.p, this.i, this.q, this.r);
        }

        public Builder dataImageValidateEnabled(boolean z) {
            this.r = z;
            return this;
        }

        public Builder fileLocation(int i) {
            this.b = i;
            return this;
        }

        public void preferredFileType(int i) {
            this.h = i;
        }

        public Builder preferredIcType(int i) {
            this.e = i;
            return this;
        }

        public Builder primaryBudRole(int i) {
            this.i = i;
            return this;
        }

        public Builder setFileIndicator(int i) {
            this.j = i;
            return this;
        }

        public Builder setFilePath(String str) {
            this.c = str;
            return this;
        }

        public Builder setFileSuffix(String str) {
            this.d = str;
            return this;
        }

        public Builder setIcCheckEnabled(boolean z) {
            this.n = z;
            return this;
        }

        public Builder setIgnoreException(boolean z) {
            this.p = z;
            return this;
        }

        public Builder setOtaDeviceInfo(OtaDeviceInfo otaDeviceInfo) {
            this.k = otaDeviceInfo;
            return this;
        }

        public Builder setPreferredBudRole(int i) {
            this.i = i;
            return this;
        }

        public Builder setPreferredIcType(int i) {
            this.e = i;
            return this;
        }

        public Builder setPrimaryIcType(int i) {
            this.e = i;
            return this;
        }

        public Builder setSectionSizeCheckEnabled(boolean z) {
            this.o = z;
            return this;
        }

        public Builder setVersionCheckEnabled(boolean z) {
            return versionCheckEnabled(z);
        }

        public Builder setWorkMode(int i) {
            this.q = i;
            return this;
        }

        public Builder versionCheckEnabled(boolean z) {
            this.l = z;
            return this;
        }

        public Builder with(Context context) {
            this.f13605a = context;
            return this;
        }

        public Builder versionCheckEnabled(boolean z, int i) {
            this.l = z;
            this.m = i;
            return this;
        }
    }

    public Context a() {
        return this.f13604a;
    }

    public int b() {
        return this.j;
    }

    public int c() {
        return this.g;
    }

    public String d() {
        return this.h;
    }

    public String e() {
        return TextUtils.isEmpty(this.i) ? a.FILE_SUFFIX : this.i;
    }

    public OtaDeviceInfo f() {
        return this.k;
    }

    public int g() {
        return this.f;
    }

    public int h() {
        return this.b;
    }

    public int i() {
        return this.c;
    }

    public int j() {
        return this.d;
    }

    public int k() {
        return this.t;
    }

    public int l() {
        return this.m;
    }

    public int m() {
        return this.q;
    }

    public boolean n() {
        return (this.e & 4) == 4;
    }

    public boolean o() {
        return this.r;
    }

    public boolean p() {
        return this.s;
    }

    public boolean q() {
        return this.n;
    }

    public boolean r() {
        return (this.e & 2) == 2;
    }

    public boolean s() {
        return this.o;
    }

    public boolean t() {
        return (this.e & 1) == 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        OtaDeviceInfo otaDeviceInfo = this.k;
        if (otaDeviceInfo != null) {
            sb.append(String.format("icType=0x%02X, protocolType=0x%04X, specVersion=0x%02X", Integer.valueOf(otaDeviceInfo.icType), Integer.valueOf(this.k.protocolType), Integer.valueOf(this.k.specVersion)));
        } else {
            sb.append(String.format("protocolType=0x%04X", 0));
        }
        sb.append(String.format("\ndualBank=%b, updateBank=0x%02X", Boolean.valueOf(this.s), Integer.valueOf(this.t)));
        sb.append(String.format("\nworkMode=0x%02X, preferredIcType=0x%02X, preferredFileType=0x%02X, preferredBudRole=0x%02X", Integer.valueOf(this.q), Integer.valueOf(this.b), Integer.valueOf(this.e), Integer.valueOf(this.f)));
        sb.append(String.format("\nFile: location=0x%02X, indicator=0x%08X, path=%s", Integer.valueOf(this.g), Integer.valueOf(this.j), this.h));
        sb.append(String.format(Locale.US, "\nvalidate: versionCheck=%b(%d), icCheck=%b, sectionSizeCheck=%b,ignoreException=%b, dataImageValidate=%b", Boolean.valueOf(this.l), Integer.valueOf(this.m), Boolean.valueOf(this.n), Boolean.valueOf(this.o), Boolean.valueOf(this.p), Boolean.valueOf(this.r)));
        return sb.toString();
    }

    public boolean u() {
        OtaDeviceInfo otaDeviceInfo = this.k;
        return otaDeviceInfo != null && otaDeviceInfo.getUpdateMechanism() == 3;
    }

    public boolean v() {
        return this.l;
    }

    public LoadParams(Context context, int i, int i2, int i3, int i4, int i5, String str, String str2, int i6, boolean z, int i7, OtaDeviceInfo otaDeviceInfo, boolean z2, int i8, boolean z3, boolean z4, boolean z5, int i9, int i10, boolean z6) {
        this.b = 3;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 1;
        this.i = a.FILE_SUFFIX;
        this.m = 0;
        this.s = false;
        this.t = 15;
        this.f13604a = context;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.g = i5;
        this.h = str;
        this.i = str2;
        this.j = i6;
        this.s = z;
        this.t = i7;
        this.k = otaDeviceInfo;
        this.l = z2;
        this.m = i8;
        this.n = z3;
        this.o = z4;
        this.p = z5;
        this.f = i9;
        this.q = i10;
        this.r = z6;
    }
}
