package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzqv;
import com.google.android.libraries.places.internal.zzqw;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes10.dex */
public abstract class zzqw<MessageType extends zzqw<MessageType, BuilderType>, BuilderType extends zzqv<MessageType, BuilderType>> implements zzto {
    public int zza = 0;

    @Override // com.google.android.libraries.places.internal.zzto
    public final zzrb b_() {
        try {
            zzrj zzc = zzrb.zzc(zzg());
            zza(zzc.zzb());
            return zzc.zza();
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(name.length() + 62 + "ByteString".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("ByteString");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zza(int i) {
        throw new UnsupportedOperationException();
    }

    public final byte[] zzd() {
        try {
            byte[] bArr = new byte[zzg()];
            zzrs zza = zzrs.zza(bArr);
            zza(zza);
            zza.zzb();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(name.length() + 62 + "byte array".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("byte array");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int zze() {
        throw new UnsupportedOperationException();
    }

    public static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzsg.zza(iterable);
        if (iterable instanceof zzsz) {
            List<?> zzd = ((zzsz) iterable).zzd();
            zzsz zzszVar = (zzsz) list;
            int size = list.size();
            for (Object obj : zzd) {
                if (obj == null) {
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(zzszVar.size() - size);
                    sb.append(" is null.");
                    String sb2 = sb.toString();
                    for (int size2 = zzszVar.size() - 1; size2 >= size; size2--) {
                        zzszVar.remove(size2);
                    }
                    throw new NullPointerException(sb2);
                } else if (obj instanceof zzrb) {
                    zzszVar.zza((zzrb) obj);
                } else {
                    zzszVar.add((String) obj);
                }
            }
        } else if (iterable instanceof zzub) {
            list.addAll((Collection) iterable);
        } else {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
            }
            int size3 = list.size();
            for (T t : iterable) {
                if (t == null) {
                    StringBuilder sb3 = new StringBuilder(37);
                    sb3.append("Element at index ");
                    sb3.append(list.size() - size3);
                    sb3.append(" is null.");
                    String sb4 = sb3.toString();
                    for (int size4 = list.size() - 1; size4 >= size3; size4--) {
                        list.remove(size4);
                    }
                    throw new NullPointerException(sb4);
                }
                list.add(t);
            }
        }
    }
}
