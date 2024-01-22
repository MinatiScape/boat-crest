package org.bouncycastle.math.ec.endo;

import org.bouncycastle.math.ec.ECPointMap;
/* loaded from: classes13.dex */
public interface ECEndomorphism {
    ECPointMap getPointMap();

    boolean hasEfficientPointMap();
}
