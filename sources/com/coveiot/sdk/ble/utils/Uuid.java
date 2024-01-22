package com.coveiot.sdk.ble.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/* loaded from: classes9.dex */
public class Uuid {
    public static List<UUID> parseFromAdvertisementData(byte[] bArr) {
        byte b;
        ArrayList arrayList = new ArrayList();
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        while (order.remaining() > 2 && (b = order.get()) != 0) {
            byte b2 = order.get();
            if (b2 == 2 || b2 == 3) {
                while (b >= 2) {
                    arrayList.add(UUID.fromString(String.format("%08x-0000-1000-8000-00805f9b34fb", Short.valueOf(order.getShort()))));
                    b = (byte) (b - 2);
                }
            } else if (b2 == 6 || b2 == 7) {
                while (b >= 16) {
                    arrayList.add(new UUID(order.getLong(), order.getLong()));
                    b = (byte) (b - 16);
                }
            } else {
                order.position((order.position() + b) - 1);
            }
        }
        return arrayList;
    }
}
