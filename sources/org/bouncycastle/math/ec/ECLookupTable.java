package org.bouncycastle.math.ec;
/* loaded from: classes13.dex */
public interface ECLookupTable {
    int getSize();

    ECPoint lookup(int i);
}
