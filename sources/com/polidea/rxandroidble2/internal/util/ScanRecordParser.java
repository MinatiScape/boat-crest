package com.polidea.rxandroidble2.internal.util;

import android.os.ParcelUuid;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/* loaded from: classes12.dex */
public class ScanRecordParser {
    public static final UUID BASE_UUID = UUID.fromString("00000000-0000-1000-8000-00805F9B34FB");
    public static final int UUID_BYTES_128_BIT = 16;
    public static final int UUID_BYTES_16_BIT = 2;
    public static final int UUID_BYTES_32_BIT = 4;

    public static ParcelUuid d(byte[] bArr) {
        long j;
        if (bArr != null) {
            int length = bArr.length;
            if (length != 2 && length != 4 && length != 16) {
                throw new IllegalArgumentException("uuidBytes length invalid - " + length);
            } else if (length == 16) {
                ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
                return new ParcelUuid(new UUID(order.getLong(8), order.getLong(0)));
            } else {
                if (length == 2) {
                    j = (bArr[0] & 255) + ((bArr[1] & 255) << 8);
                } else {
                    j = ((bArr[3] & 255) << 24) + (bArr[0] & 255) + ((bArr[1] & 255) << 8) + ((bArr[2] & 255) << 16);
                }
                UUID uuid = BASE_UUID;
                return new ParcelUuid(new UUID(uuid.getMostSignificantBits() + (j << 32), uuid.getLeastSignificantBits()));
            }
        }
        throw new IllegalArgumentException("uuidBytes cannot be null");
    }

    public final byte[] a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public final int b(byte[] bArr, int i, int i2, int i3, List<ParcelUuid> list) {
        while (i2 > 0) {
            list.add(d(a(bArr, i, i3)));
            i2 -= i3;
            i += i3;
        }
        return i;
    }

    public final int c(byte[] bArr, int i, int i2, int i3, List<ParcelUuid> list) {
        while (i2 > 0) {
            list.add(d(a(bArr, i, i3)));
            i2 -= i3;
            i += i3;
        }
        return i;
    }

    public List<UUID> extractUUIDs(byte[] bArr) {
        List<ParcelUuid> serviceUuids = parseFromBytes(bArr).getServiceUuids();
        if (serviceUuids == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (ParcelUuid parcelUuid : serviceUuids) {
            arrayList.add(parcelUuid.getUuid());
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00fe  */
    @androidx.annotation.RestrictTo({androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.polidea.rxandroidble2.scan.ScanRecord parseFromBytes(byte[] r19) {
        /*
            Method dump skipped, instructions count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.polidea.rxandroidble2.internal.util.ScanRecordParser.parseFromBytes(byte[]):com.polidea.rxandroidble2.scan.ScanRecord");
    }
}
