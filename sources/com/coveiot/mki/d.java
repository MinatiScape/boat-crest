package com.coveiot.mki;

import com.coveiot.sdk.ble.api.BleUUID;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
/* loaded from: classes9.dex */
public enum d {
    /* JADX INFO: Fake field, exist only in values array */
    GET_DATE_TIME((byte) 0, (byte) 6, 1, new l() { // from class: com.coveiot.mki.n
        @Override // com.coveiot.mki.l
        public final Object[] a(List<Byte> list) {
            if (list.size() == 10) {
                LocalDateTime of = LocalDateTime.of(list.get(1).byteValue() + (list.get(0).byteValue() * 100), list.get(2).byteValue(), list.get(3).byteValue(), list.get(4).byteValue(), list.get(5).byteValue(), list.get(6).byteValue());
                int byteValue = (list.get(9).byteValue() + (list.get(8).byteValue() * 60)) * 60;
                if (list.get(7).byteValue() == 45) {
                    byteValue *= -1;
                }
                ZoneId ofOffset = ZoneId.ofOffset("UTC", ZoneOffset.ofTotalSeconds(byteValue));
                p.b("Getting timestamp successful", new Object[0]);
                return new Object[]{ZonedDateTime.of(of, ofOffset)};
            }
            throw new Exception("Invalid size");
        }
    }),
    /* JADX INFO: Fake field, exist only in values array */
    SET_DATE_TIME((byte) 0, (byte) -121, 1, new l() { // from class: com.coveiot.mki.m
        @Override // com.coveiot.mki.l
        public final Object[] a(List<Byte> list) {
            if (list.size() == 1) {
                if (list.get(0).byteValue() == 1) {
                    p.b("Success status received", new Object[0]);
                    return null;
                }
                throw new Exception("Failure status received");
            }
            throw new Exception("Invalid size");
        }
    }),
    /* JADX INFO: Fake field, exist only in values array */
    SET_TIMEZONE((byte) 0, BleUUID.CMD_ID_89, 1, new l() { // from class: com.coveiot.mki.m
        @Override // com.coveiot.mki.l
        public final Object[] a(List<Byte> list) {
            if (list.size() == 1) {
                if (list.get(0).byteValue() == 1) {
                    p.b("Success status received", new Object[0]);
                    return null;
                }
                throw new Exception("Failure status received");
            }
            throw new Exception("Invalid size");
        }
    }),
    SEND_UPDATE_FILE((byte) 6, (byte) -123, 3, new C0974r()),
    JIELI_SET_OTA_MODE((byte) 9, (byte) 0, 1, new l() { // from class: com.coveiot.mki.m
        @Override // com.coveiot.mki.l
        public final Object[] a(List<Byte> list) {
            if (list.size() == 1) {
                if (list.get(0).byteValue() == 1) {
                    p.b("Success status received", new Object[0]);
                    return null;
                }
                throw new Exception("Failure status received");
            }
            throw new Exception("Invalid size");
        }
    }),
    JIELI_SEND_OTA((byte) 9, (byte) 1, 1, new l() { // from class: com.coveiot.mki.m
        @Override // com.coveiot.mki.l
        public final Object[] a(List<Byte> list) {
            if (list.size() == 1) {
                if (list.get(0).byteValue() == 1) {
                    p.b("Success status received", new Object[0]);
                    return null;
                }
                throw new Exception("Failure status received");
            }
            throw new Exception("Invalid size");
        }
    });
    

    /* renamed from: a  reason: collision with root package name */
    private final byte f7278a;
    private final byte b;
    private final int c;
    private final l d;

    d(byte b, byte b2, int i, l lVar) {
        this.f7278a = b;
        this.b = b2;
        this.c = i;
        this.d = lVar;
    }

    public final byte a() {
        return this.b;
    }

    public final byte a(boolean z) {
        if (z) {
            byte b = this.f7278a;
            return (byte) ((b & 128) == 0 ? b | 128 : b & Byte.MAX_VALUE);
        }
        return this.f7278a;
    }

    public final Object[] a(List<Byte> list) {
        return this.d.a(list);
    }

    public final int b() {
        return this.c;
    }

    public final boolean c() {
        return this.d instanceof o;
    }
}
