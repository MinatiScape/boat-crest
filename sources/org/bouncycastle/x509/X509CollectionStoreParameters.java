package org.bouncycastle.x509;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
/* loaded from: classes13.dex */
public class X509CollectionStoreParameters implements X509StoreParameters {
    public Collection h;

    public X509CollectionStoreParameters(Collection collection) {
        Objects.requireNonNull(collection, "collection cannot be null");
        this.h = collection;
    }

    public Object clone() {
        return new X509CollectionStoreParameters(this.h);
    }

    public Collection getCollection() {
        return new ArrayList(this.h);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("X509CollectionStoreParameters: [\n");
        stringBuffer.append("  collection: " + this.h + "\n");
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
