package com.polidea.rxandroidble2.internal.util;

import android.util.Pair;
import java.util.Arrays;
import java.util.UUID;
/* loaded from: classes12.dex */
public class CharacteristicChangedEvent extends CharacteristicNotificationId {
    public final byte[] data;

    public CharacteristicChangedEvent(UUID uuid, Integer num, byte[] bArr) {
        super(uuid, num);
        this.data = bArr;
    }

    @Override // android.util.Pair
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CharacteristicChangedEvent)) {
            return (obj instanceof CharacteristicNotificationId) && super.equals(obj);
        } else if (super.equals(obj)) {
            return Arrays.equals(this.data, ((CharacteristicChangedEvent) obj).data);
        } else {
            return false;
        }
    }

    @Override // android.util.Pair
    public int hashCode() {
        return (super.hashCode() * 31) + Arrays.hashCode(this.data);
    }

    @Override // com.polidea.rxandroidble2.internal.util.CharacteristicNotificationId, android.util.Pair
    public String toString() {
        return "CharacteristicChangedEvent{UUID=" + ((UUID) ((Pair) this).first).toString() + ", instanceId=" + ((Integer) ((Pair) this).second).toString() + ", data=" + Arrays.toString(this.data) + '}';
    }
}
