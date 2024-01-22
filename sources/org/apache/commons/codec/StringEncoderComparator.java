package org.apache.commons.codec;

import java.util.Comparator;
/* loaded from: classes12.dex */
public class StringEncoderComparator implements Comparator {
    public final StringEncoder h;

    @Deprecated
    public StringEncoderComparator() {
        this.h = null;
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        try {
            return ((Comparable) this.h.encode(obj)).compareTo((Comparable) this.h.encode(obj2));
        } catch (EncoderException unused) {
            return 0;
        }
    }

    public StringEncoderComparator(StringEncoder stringEncoder) {
        this.h = stringEncoder;
    }
}
