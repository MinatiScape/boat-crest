package com.goodix.ble.libble;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseArray;
import java.util.HashMap;
import java.util.UUID;
/* loaded from: classes5.dex */
public class BleUuid {
    public static final UUID CCCD = from(10498);
    public static final UUID GENERIC_ATTRIBUTE_SERVICE = from(6145);
    public static final UUID SERVICE_CHANGED_CHARACTERISTIC = from(10757);

    /* renamed from: a  reason: collision with root package name */
    public static HashMap<String, UUID> f8006a;
    public static SparseArray<UUID> b;

    public static synchronized UUID from(int i) {
        UUID uuid;
        synchronized (BleUuid.class) {
            if (b == null) {
                b = new SparseArray<>(64);
            }
            uuid = b.get(i);
            if (uuid == null) {
                uuid = new UUID((i << 32) | PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, -9223371485494954757L);
                b.put(i, uuid);
            }
        }
        return uuid;
    }

    public static synchronized UUID from(String str) {
        UUID uuid;
        synchronized (BleUuid.class) {
            String upperCase = str.toUpperCase();
            if (f8006a == null) {
                f8006a = new HashMap<>(64);
            }
            uuid = f8006a.get(upperCase);
            if (uuid == null) {
                uuid = UUID.fromString(upperCase);
                f8006a.put(upperCase, uuid);
            }
        }
        return uuid;
    }

    public static int get16bit32bitUuid(UUID uuid) {
        if (uuid != null) {
            return (int) ((uuid.getMostSignificantBits() >> 32) & 4294967295L);
        }
        return 0;
    }

    public static boolean is16bit32bitUuid(UUID uuid) {
        return uuid != null && uuid.getLeastSignificantBits() == -9223371485494954757L && (uuid.getMostSignificantBits() & 4294967295L) == PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
    }
}
