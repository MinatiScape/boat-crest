package com.polidea.rxandroidble2.internal.util;

import android.util.Pair;
import java.util.UUID;
/* loaded from: classes12.dex */
public class CharacteristicNotificationId extends Pair<UUID, Integer> {
    public CharacteristicNotificationId(UUID uuid, Integer num) {
        super(uuid, num);
    }

    @Override // android.util.Pair
    public String toString() {
        return "CharacteristicNotificationId{UUID=" + ((UUID) ((Pair) this).first).toString() + ", instanceId=" + ((Integer) ((Pair) this).second).toString() + '}';
    }
}
