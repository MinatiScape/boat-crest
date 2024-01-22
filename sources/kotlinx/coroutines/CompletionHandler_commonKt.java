package kotlinx.coroutines;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes12.dex */
public final class CompletionHandler_commonKt {
    public static final /* synthetic */ <T> boolean isHandlerOf(Function1<? super Throwable, Unit> function1) {
        Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
        return function1 instanceof Object;
    }
}
