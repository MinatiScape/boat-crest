package com.polidea.rxandroidble2.scan;

import android.bluetooth.BluetoothAdapter;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.polidea.rxandroidble2.internal.ScanResultInterface;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.scan.ScanFilterInterface;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
/* loaded from: classes12.dex */
public class ScanFilter implements Parcelable, ScanFilterInterface {
    @Nullable
    public final String h;
    @Nullable
    public final String i;
    @Nullable
    public final ParcelUuid j;
    @Nullable
    public final ParcelUuid k;
    @Nullable
    public final ParcelUuid l;
    @Nullable
    public final ParcelUuid m;
    @Nullable
    public final ParcelUuid n;
    @Nullable
    public final byte[] o;
    @Nullable
    public final byte[] p;
    public final int q;
    @Nullable
    public final byte[] r;
    @Nullable
    public final byte[] s;
    public static final ScanFilter t = new Builder().build();
    public static final Parcelable.Creator<ScanFilter> CREATOR = new a();

    /* loaded from: classes12.dex */
    public class a implements Parcelable.Creator<ScanFilter> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ScanFilter createFromParcel(Parcel parcel) {
            Builder builder = new Builder();
            if (parcel.readInt() == 1) {
                builder.setDeviceName(parcel.readString());
            }
            if (parcel.readInt() == 1) {
                builder.setDeviceAddress(parcel.readString());
            }
            if (parcel.readInt() == 1) {
                ParcelUuid parcelUuid = (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader());
                builder.setServiceUuid(parcelUuid);
                if (parcel.readInt() == 1) {
                    builder.setServiceUuid(parcelUuid, (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader()));
                }
            }
            if (parcel.readInt() == 1) {
                ParcelUuid parcelUuid2 = (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader());
                builder.setServiceSolicitationUuid(parcelUuid2);
                if (parcel.readInt() == 1) {
                    builder.setServiceSolicitationUuid(parcelUuid2, (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader()));
                }
            }
            if (parcel.readInt() == 1) {
                ParcelUuid parcelUuid3 = (ParcelUuid) parcel.readParcelable(ParcelUuid.class.getClassLoader());
                if (parcel.readInt() == 1) {
                    byte[] bArr = new byte[parcel.readInt()];
                    parcel.readByteArray(bArr);
                    if (parcel.readInt() == 0) {
                        builder.setServiceData(parcelUuid3, bArr);
                    } else {
                        byte[] bArr2 = new byte[parcel.readInt()];
                        parcel.readByteArray(bArr2);
                        builder.setServiceData(parcelUuid3, bArr, bArr2);
                    }
                }
            }
            int readInt = parcel.readInt();
            if (parcel.readInt() == 1) {
                byte[] bArr3 = new byte[parcel.readInt()];
                parcel.readByteArray(bArr3);
                if (parcel.readInt() == 0) {
                    builder.setManufacturerData(readInt, bArr3);
                } else {
                    byte[] bArr4 = new byte[parcel.readInt()];
                    parcel.readByteArray(bArr4);
                    builder.setManufacturerData(readInt, bArr3, bArr4);
                }
            }
            return builder.build();
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public ScanFilter[] newArray(int i) {
            return new ScanFilter[i];
        }
    }

    public ScanFilter(@Nullable String str, @Nullable String str2, @Nullable ParcelUuid parcelUuid, @Nullable ParcelUuid parcelUuid2, @Nullable ParcelUuid parcelUuid3, @Nullable ParcelUuid parcelUuid4, @Nullable ParcelUuid parcelUuid5, @Nullable byte[] bArr, @Nullable byte[] bArr2, int i, @Nullable byte[] bArr3, @Nullable byte[] bArr4) {
        this.h = str;
        this.j = parcelUuid;
        this.k = parcelUuid2;
        this.l = parcelUuid3;
        this.m = parcelUuid4;
        this.i = str2;
        this.n = parcelUuid5;
        this.o = bArr;
        this.p = bArr2;
        this.q = i;
        this.r = bArr3;
        this.s = bArr4;
    }

    public static boolean a(byte[] bArr, byte[] bArr2) {
        return bArr == bArr2 || !(bArr == null || bArr2 == null || !Arrays.equals(bArr, bArr2));
    }

    public static boolean b(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean c(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr3 == null || bArr3.length < bArr.length) {
            return false;
        }
        if (bArr2 == null) {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr3[i] != bArr[i]) {
                    return false;
                }
            }
            return true;
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if ((bArr2[i2] & bArr3[i2]) != (bArr2[i2] & bArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public static boolean d(ParcelUuid parcelUuid, ParcelUuid parcelUuid2, List<ParcelUuid> list) {
        if (parcelUuid == null) {
            return true;
        }
        if (list == null) {
            return false;
        }
        for (ParcelUuid parcelUuid3 : list) {
            if (e(parcelUuid.getUuid(), parcelUuid2 == null ? null : parcelUuid2.getUuid(), parcelUuid3.getUuid())) {
                return true;
            }
        }
        return false;
    }

    public static boolean e(UUID uuid, UUID uuid2, UUID uuid3) {
        if (uuid2 == null) {
            return uuid.equals(uuid3);
        }
        if ((uuid.getLeastSignificantBits() & uuid2.getLeastSignificantBits()) != (uuid3.getLeastSignificantBits() & uuid2.getLeastSignificantBits())) {
            return false;
        }
        return (uuid.getMostSignificantBits() & uuid2.getMostSignificantBits()) == (uuid2.getMostSignificantBits() & uuid3.getMostSignificantBits());
    }

    public static ScanFilter empty() {
        return new Builder().build();
    }

    public static boolean f(ParcelUuid parcelUuid, ParcelUuid parcelUuid2, List<ParcelUuid> list) {
        if (parcelUuid == null) {
            return true;
        }
        if (list == null) {
            return false;
        }
        for (ParcelUuid parcelUuid3 : list) {
            if (e(parcelUuid.getUuid(), parcelUuid2 == null ? null : parcelUuid2.getUuid(), parcelUuid3.getUuid())) {
                return true;
            }
        }
        return false;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ScanFilter scanFilter = (ScanFilter) obj;
        return b(this.h, scanFilter.h) && b(this.i, scanFilter.i) && this.q == scanFilter.q && a(this.r, scanFilter.r) && a(this.s, scanFilter.s) && b(this.n, scanFilter.n) && a(this.o, scanFilter.o) && a(this.p, scanFilter.p) && b(this.j, scanFilter.j) && b(this.k, scanFilter.k) && b(this.l, scanFilter.l) && b(this.m, scanFilter.m);
    }

    @Nullable
    public String getDeviceAddress() {
        return this.i;
    }

    @Nullable
    public String getDeviceName() {
        return this.h;
    }

    @Nullable
    public byte[] getManufacturerData() {
        return this.r;
    }

    @Nullable
    public byte[] getManufacturerDataMask() {
        return this.s;
    }

    public int getManufacturerId() {
        return this.q;
    }

    @Nullable
    public byte[] getServiceData() {
        return this.o;
    }

    @Nullable
    public byte[] getServiceDataMask() {
        return this.p;
    }

    @Nullable
    public ParcelUuid getServiceDataUuid() {
        return this.n;
    }

    @Nullable
    public ParcelUuid getServiceSolicitationUuid() {
        return this.l;
    }

    @Nullable
    public ParcelUuid getServiceSolicitationUuidMask() {
        return this.m;
    }

    @Nullable
    public ParcelUuid getServiceUuid() {
        return this.j;
    }

    @Nullable
    public ParcelUuid getServiceUuidMask() {
        return this.k;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.h, this.i, Integer.valueOf(this.q), Integer.valueOf(Arrays.hashCode(this.r)), Integer.valueOf(Arrays.hashCode(this.s)), this.n, Integer.valueOf(Arrays.hashCode(this.o)), Integer.valueOf(Arrays.hashCode(this.p)), this.j, this.k, this.l, this.m});
    }

    @Override // com.polidea.rxandroidble2.internal.scan.ScanFilterInterface
    public boolean isAllFieldsEmpty() {
        return equals(t);
    }

    @Override // com.polidea.rxandroidble2.internal.scan.ScanFilterInterface
    public boolean matches(ScanResultInterface scanResultInterface) {
        if (scanResultInterface == null) {
            return false;
        }
        String address = scanResultInterface.getAddress();
        String str = this.i;
        if (str == null || str.equals(address)) {
            ScanRecord scanRecord = scanResultInterface.getScanRecord();
            String str2 = this.h;
            if (str2 == null || str2.equals(scanResultInterface.getDeviceName()) || (scanRecord != null && this.h.equals(scanRecord.getDeviceName()))) {
                if (scanRecord == null) {
                    return this.j == null && this.r == null && this.o == null;
                }
                ParcelUuid parcelUuid = this.j;
                if (parcelUuid == null || f(parcelUuid, this.k, scanRecord.getServiceUuids())) {
                    ParcelUuid parcelUuid2 = this.l;
                    if (parcelUuid2 == null || d(parcelUuid2, this.m, scanRecord.getServiceSolicitationUuids())) {
                        ParcelUuid parcelUuid3 = this.n;
                        if (parcelUuid3 == null || c(this.o, this.p, scanRecord.getServiceData(parcelUuid3))) {
                            int i = this.q;
                            return i < 0 || c(this.r, this.s, scanRecord.getManufacturerSpecificData(i));
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BluetoothLeScanFilter [mDeviceName=");
        sb.append(this.h);
        sb.append(", ");
        sb.append(LoggerUtil.commonMacMessage(this.i));
        sb.append(", mUuid=");
        ParcelUuid parcelUuid = this.j;
        sb.append(parcelUuid == null ? null : LoggerUtil.getUuidToLog(parcelUuid.getUuid()));
        sb.append(", mUuidMask=");
        ParcelUuid parcelUuid2 = this.k;
        sb.append(parcelUuid2 == null ? null : LoggerUtil.getUuidToLog(parcelUuid2.getUuid()));
        sb.append(", mSolicitedUuid=");
        ParcelUuid parcelUuid3 = this.l;
        sb.append(parcelUuid3 == null ? null : LoggerUtil.getUuidToLog(parcelUuid3.getUuid()));
        sb.append(", mSolicitedUuidMask=");
        ParcelUuid parcelUuid4 = this.m;
        sb.append(parcelUuid4 == null ? null : LoggerUtil.getUuidToLog(parcelUuid4.getUuid()));
        sb.append(", mServiceDataUuid=");
        ParcelUuid parcelUuid5 = this.n;
        sb.append(parcelUuid5 != null ? LoggerUtil.getUuidToLog(parcelUuid5.getUuid()) : null);
        sb.append(", mServiceData=");
        sb.append(Arrays.toString(this.o));
        sb.append(", mServiceDataMask=");
        sb.append(Arrays.toString(this.p));
        sb.append(", mManufacturerId=");
        sb.append(this.q);
        sb.append(", mManufacturerData=");
        sb.append(Arrays.toString(this.r));
        sb.append(", mManufacturerDataMask=");
        sb.append(Arrays.toString(this.s));
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.h == null ? 0 : 1);
        String str = this.h;
        if (str != null) {
            parcel.writeString(str);
        }
        parcel.writeInt(this.i == null ? 0 : 1);
        String str2 = this.i;
        if (str2 != null) {
            parcel.writeString(str2);
        }
        parcel.writeInt(this.j == null ? 0 : 1);
        ParcelUuid parcelUuid = this.j;
        if (parcelUuid != null) {
            parcel.writeParcelable(parcelUuid, i);
            parcel.writeInt(this.k == null ? 0 : 1);
            ParcelUuid parcelUuid2 = this.k;
            if (parcelUuid2 != null) {
                parcel.writeParcelable(parcelUuid2, i);
            }
        }
        parcel.writeInt(this.l == null ? 0 : 1);
        ParcelUuid parcelUuid3 = this.l;
        if (parcelUuid3 != null) {
            parcel.writeParcelable(parcelUuid3, i);
            parcel.writeInt(this.m == null ? 0 : 1);
            ParcelUuid parcelUuid4 = this.m;
            if (parcelUuid4 != null) {
                parcel.writeParcelable(parcelUuid4, i);
            }
        }
        parcel.writeInt(this.n == null ? 0 : 1);
        ParcelUuid parcelUuid5 = this.n;
        if (parcelUuid5 != null) {
            parcel.writeParcelable(parcelUuid5, i);
            parcel.writeInt(this.o == null ? 0 : 1);
            byte[] bArr = this.o;
            if (bArr != null) {
                parcel.writeInt(bArr.length);
                parcel.writeByteArray(this.o);
                parcel.writeInt(this.p == null ? 0 : 1);
                byte[] bArr2 = this.p;
                if (bArr2 != null) {
                    parcel.writeInt(bArr2.length);
                    parcel.writeByteArray(this.p);
                }
            }
        }
        parcel.writeInt(this.q);
        parcel.writeInt(this.r == null ? 0 : 1);
        byte[] bArr3 = this.r;
        if (bArr3 != null) {
            parcel.writeInt(bArr3.length);
            parcel.writeByteArray(this.r);
            parcel.writeInt(this.s != null ? 1 : 0);
            byte[] bArr4 = this.s;
            if (bArr4 != null) {
                parcel.writeInt(bArr4.length);
                parcel.writeByteArray(this.s);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f13526a;
        public String b;
        public ParcelUuid c;
        public ParcelUuid d;
        public ParcelUuid e;
        public ParcelUuid f;
        public ParcelUuid g;
        public byte[] h;
        public byte[] i;
        public int j = -1;
        public byte[] k;
        public byte[] l;

        public ScanFilter build() {
            return new ScanFilter(this.f13526a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l);
        }

        public Builder setDeviceAddress(String str) {
            if (str != null && !BluetoothAdapter.checkBluetoothAddress(str)) {
                throw new IllegalArgumentException("invalid device address " + str);
            }
            this.b = str;
            return this;
        }

        public Builder setDeviceName(String str) {
            this.f13526a = str;
            return this;
        }

        public Builder setManufacturerData(int i, byte[] bArr) {
            if (bArr != null && i < 0) {
                throw new IllegalArgumentException("invalid manufacture id");
            }
            this.j = i;
            this.k = bArr;
            this.l = null;
            return this;
        }

        public Builder setServiceData(ParcelUuid parcelUuid, byte[] bArr) {
            if (parcelUuid != null) {
                this.g = parcelUuid;
                this.h = bArr;
                this.i = null;
                return this;
            }
            throw new IllegalArgumentException("serviceDataUuid is null");
        }

        public Builder setServiceSolicitationUuid(ParcelUuid parcelUuid) {
            this.e = parcelUuid;
            this.f = null;
            return this;
        }

        public Builder setServiceUuid(ParcelUuid parcelUuid) {
            this.c = parcelUuid;
            this.d = null;
            return this;
        }

        public Builder setServiceSolicitationUuid(@Nullable ParcelUuid parcelUuid, @Nullable ParcelUuid parcelUuid2) {
            if (parcelUuid2 != null && parcelUuid == null) {
                throw new IllegalArgumentException("SolicitationUuid is null while SolicitationUuidMask is not null!");
            }
            this.e = parcelUuid;
            this.f = parcelUuid2;
            return this;
        }

        public Builder setServiceUuid(ParcelUuid parcelUuid, ParcelUuid parcelUuid2) {
            if (this.d != null && this.c == null) {
                throw new IllegalArgumentException("uuid is null while uuidMask is not null!");
            }
            this.c = parcelUuid;
            this.d = parcelUuid2;
            return this;
        }

        public Builder setManufacturerData(int i, byte[] bArr, byte[] bArr2) {
            if (bArr != null && i < 0) {
                throw new IllegalArgumentException("invalid manufacture id");
            }
            byte[] bArr3 = this.l;
            if (bArr3 != null) {
                byte[] bArr4 = this.k;
                if (bArr4 != null) {
                    if (bArr4.length != bArr3.length) {
                        throw new IllegalArgumentException("size mismatch for manufacturerData and manufacturerDataMask");
                    }
                } else {
                    throw new IllegalArgumentException("manufacturerData is null while manufacturerDataMask is not null");
                }
            }
            this.j = i;
            this.k = bArr;
            this.l = bArr2;
            return this;
        }

        public Builder setServiceData(ParcelUuid parcelUuid, byte[] bArr, byte[] bArr2) {
            if (parcelUuid != null) {
                byte[] bArr3 = this.i;
                if (bArr3 != null) {
                    byte[] bArr4 = this.h;
                    if (bArr4 != null) {
                        if (bArr4.length != bArr3.length) {
                            throw new IllegalArgumentException("size mismatch for service data and service data mask");
                        }
                    } else {
                        throw new IllegalArgumentException("serviceData is null while serviceDataMask is not null");
                    }
                }
                this.g = parcelUuid;
                this.h = bArr;
                this.i = bArr2;
                return this;
            }
            throw new IllegalArgumentException("serviceDataUuid is null");
        }
    }
}
