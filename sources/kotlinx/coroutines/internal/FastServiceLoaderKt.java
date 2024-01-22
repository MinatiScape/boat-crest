package kotlinx.coroutines.internal;

import kotlin.Result;
import kotlin.ResultKt;
/* loaded from: classes12.dex */
public final class FastServiceLoaderKt {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f14175a;

    static {
        Object m123constructorimpl;
        try {
            Result.Companion companion = Result.Companion;
            m123constructorimpl = Result.m123constructorimpl(Class.forName("android.os.Build"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m123constructorimpl = Result.m123constructorimpl(ResultKt.createFailure(th));
        }
        f14175a = Result.m130isSuccessimpl(m123constructorimpl);
    }

    public static final boolean getANDROID_DETECTED() {
        return f14175a;
    }
}
