package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Hex;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes10.dex */
public final class PrimitiveSet<P> {

    /* renamed from: a  reason: collision with root package name */
    public final ConcurrentMap<b, List<Entry<P>>> f10828a = new ConcurrentHashMap();
    public Entry<P> b;
    public final Class<P> c;

    /* loaded from: classes10.dex */
    public static final class Entry<P> {

        /* renamed from: a  reason: collision with root package name */
        public final P f10829a;
        public final byte[] b;
        public final KeyStatusType c;
        public final OutputPrefixType d;
        public final int e;

        public Entry(P p, byte[] bArr, KeyStatusType keyStatusType, OutputPrefixType outputPrefixType, int i) {
            this.f10829a = p;
            this.b = Arrays.copyOf(bArr, bArr.length);
            this.c = keyStatusType;
            this.d = outputPrefixType;
            this.e = i;
        }

        public final byte[] getIdentifier() {
            byte[] bArr = this.b;
            if (bArr == null) {
                return null;
            }
            return Arrays.copyOf(bArr, bArr.length);
        }

        public int getKeyId() {
            return this.e;
        }

        public OutputPrefixType getOutputPrefixType() {
            return this.d;
        }

        public P getPrimitive() {
            return this.f10829a;
        }

        public KeyStatusType getStatus() {
            return this.c;
        }
    }

    /* loaded from: classes10.dex */
    public static class b implements Comparable<b> {
        public final byte[] h;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(b bVar) {
            int i;
            int i2;
            byte[] bArr = this.h;
            int length = bArr.length;
            byte[] bArr2 = bVar.h;
            if (length != bArr2.length) {
                i = bArr.length;
                i2 = bArr2.length;
            } else {
                int i3 = 0;
                while (true) {
                    byte[] bArr3 = this.h;
                    if (i3 >= bArr3.length) {
                        return 0;
                    }
                    char c = bArr3[i3];
                    byte[] bArr4 = bVar.h;
                    if (c != bArr4[i3]) {
                        i = bArr3[i3];
                        i2 = bArr4[i3];
                        break;
                    }
                    i3++;
                }
            }
            return i - i2;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                return Arrays.equals(this.h, ((b) obj).h);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.h);
        }

        public String toString() {
            return Hex.encode(this.h);
        }

        public b(byte[] bArr) {
            this.h = Arrays.copyOf(bArr, bArr.length);
        }
    }

    public PrimitiveSet(Class<P> cls) {
        this.c = cls;
    }

    public static <P> PrimitiveSet<P> newPrimitiveSet(Class<P> cls) {
        return new PrimitiveSet<>(cls);
    }

    public Entry<P> addPrimitive(P p, Keyset.Key key) throws GeneralSecurityException {
        if (key.getStatus() == KeyStatusType.ENABLED) {
            Entry<P> entry = new Entry<>(p, CryptoFormat.getOutputPrefix(key), key.getStatus(), key.getOutputPrefixType(), key.getKeyId());
            ArrayList arrayList = new ArrayList();
            arrayList.add(entry);
            b bVar = new b(entry.getIdentifier());
            List<Entry<P>> put = this.f10828a.put(bVar, Collections.unmodifiableList(arrayList));
            if (put != null) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(put);
                arrayList2.add(entry);
                this.f10828a.put(bVar, Collections.unmodifiableList(arrayList2));
            }
            return entry;
        }
        throw new GeneralSecurityException("only ENABLED key is allowed");
    }

    public Collection<List<Entry<P>>> getAll() {
        return this.f10828a.values();
    }

    public Entry<P> getPrimary() {
        return this.b;
    }

    public List<Entry<P>> getPrimitive(byte[] bArr) {
        List<Entry<P>> list = this.f10828a.get(new b(bArr));
        return list != null ? list : Collections.emptyList();
    }

    public Class<P> getPrimitiveClass() {
        return this.c;
    }

    public List<Entry<P>> getRawPrimitives() {
        return getPrimitive(CryptoFormat.RAW_PREFIX);
    }

    public void setPrimary(Entry<P> entry) {
        if (entry != null) {
            if (entry.getStatus() == KeyStatusType.ENABLED) {
                if (!getPrimitive(entry.getIdentifier()).isEmpty()) {
                    this.b = entry;
                    return;
                }
                throw new IllegalArgumentException("the primary entry cannot be set to an entry which is not held by this primitive set");
            }
            throw new IllegalArgumentException("the primary entry has to be ENABLED");
        }
        throw new IllegalArgumentException("the primary entry must be non-null");
    }

    public List<Entry<P>> getPrimitive(Keyset.Key key) throws GeneralSecurityException {
        return getPrimitive(CryptoFormat.getOutputPrefix(key));
    }
}
