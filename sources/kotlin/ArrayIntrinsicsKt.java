package kotlin;

import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes12.dex */
public final class ArrayIntrinsicsKt {
    public static final /* synthetic */ <T> T[] emptyArray() {
        Intrinsics.reifiedOperationMarker(0, "T?");
        return (T[]) new Object[0];
    }
}
