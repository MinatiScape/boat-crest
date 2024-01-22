package com.mappls.sdk.gestures;

import android.util.Pair;
import androidx.annotation.Keep;
@Keep
/* loaded from: classes11.dex */
public class PointerDistancePair extends Pair<Integer, Integer> {
    public PointerDistancePair(Integer num, Integer num2) {
        super(num, num2);
    }

    @Override // android.util.Pair
    public boolean equals(Object obj) {
        if (obj instanceof PointerDistancePair) {
            PointerDistancePair pointerDistancePair = (PointerDistancePair) obj;
            if (((Integer) ((Pair) this).first).equals(((Pair) pointerDistancePair).first) && ((Integer) ((Pair) this).second).equals(((Pair) pointerDistancePair).second)) {
                return true;
            }
            return ((Integer) ((Pair) this).first).equals(((Pair) pointerDistancePair).second) && ((Integer) ((Pair) this).second).equals(((Pair) pointerDistancePair).first);
        }
        return false;
    }
}
