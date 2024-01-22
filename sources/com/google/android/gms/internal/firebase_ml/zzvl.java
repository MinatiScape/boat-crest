package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzvk;
import com.google.android.gms.internal.firebase_ml.zzvl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes7.dex */
public abstract class zzvl<MessageType extends zzvl<MessageType, BuilderType>, BuilderType extends zzvk<MessageType, BuilderType>> implements zzyk {
    public int zzchd = 0;

    public static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzxd.a(iterable);
        if (iterable instanceof zzxv) {
            List<?> zzvn = ((zzxv) iterable).zzvn();
            zzxv zzxvVar = (zzxv) list;
            int size = list.size();
            for (Object obj : zzvn) {
                if (obj == null) {
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(zzxvVar.size() - size);
                    sb.append(" is null.");
                    String sb2 = sb.toString();
                    for (int size2 = zzxvVar.size() - 1; size2 >= size; size2--) {
                        zzxvVar.remove(size2);
                    }
                    throw new NullPointerException(sb2);
                } else if (obj instanceof zzvv) {
                    zzxvVar.zze((zzvv) obj);
                } else {
                    zzxvVar.add((String) obj);
                }
            }
        } else if (iterable instanceof v7) {
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

    public void a(int i) {
        throw new UnsupportedOperationException();
    }

    public int b() {
        throw new UnsupportedOperationException();
    }

    public final byte[] toByteArray() {
        try {
            byte[] bArr = new byte[zzuo()];
            zzwi zzg = zzwi.zzg(bArr);
            zzb(zzg);
            zzg.zztz();
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

    @Override // com.google.android.gms.internal.firebase_ml.zzyk
    public final zzvv zztg() {
        try {
            f6 zzcy = zzvv.zzcy(zzuo());
            zzb(zzcy.b());
            return zzcy.a();
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
}
