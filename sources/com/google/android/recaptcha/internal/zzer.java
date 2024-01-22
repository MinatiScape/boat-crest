package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.internal.zzeq;
import com.google.android.recaptcha.internal.zzer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes10.dex */
public abstract class zzer<MessageType extends zzer<MessageType, BuilderType>, BuilderType extends zzeq<MessageType, BuilderType>> implements zzip {
    public int zza = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public static void zzc(Iterable iterable, List list) {
        byte[] bArr = zzhn.zzd;
        Objects.requireNonNull(iterable);
        if (iterable instanceof zzhx) {
            List zzh = ((zzhx) iterable).zzh();
            zzhx zzhxVar = (zzhx) list;
            int size = list.size();
            for (Object obj : zzh) {
                if (obj == null) {
                    String str = "Element at index " + (zzhxVar.size() - size) + " is null.";
                    int size2 = zzhxVar.size();
                    while (true) {
                        size2--;
                        if (size2 < size) {
                            break;
                        }
                        zzhxVar.remove(size2);
                    }
                    throw new NullPointerException(str);
                } else if (obj instanceof zzfi) {
                    zzhxVar.zzi((zzfi) obj);
                } else {
                    zzhxVar.add((String) obj);
                }
            }
        } else if (!(iterable instanceof zzix)) {
            if (list instanceof ArrayList) {
                ((ArrayList) list).ensureCapacity(list.size() + iterable.size());
            }
            int size3 = list.size();
            for (Object obj2 : iterable) {
                if (obj2 == null) {
                    String str2 = "Element at index " + (list.size() - size3) + " is null.";
                    int size4 = list.size();
                    while (true) {
                        size4--;
                        if (size4 < size3) {
                            break;
                        }
                        list.remove(size4);
                    }
                    throw new NullPointerException(str2);
                }
                list.add(obj2);
            }
        } else {
            list.addAll(iterable);
        }
    }

    public int zza(zzjc zzjcVar) {
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzip
    public final zzfi zzb() {
        try {
            int zzn = zzn();
            zzfi zzfiVar = zzfi.zzb;
            byte[] bArr = new byte[zzn];
            zzft zzA = zzft.zzA(bArr, 0, zzn);
            zze(zzA);
            zzA.zzB();
            return new zzff(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    public final byte[] zzd() {
        try {
            int zzn = zzn();
            byte[] bArr = new byte[zzn];
            zzft zzA = zzft.zzA(bArr, 0, zzn);
            zze(zzA);
            zzA.zzB();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
