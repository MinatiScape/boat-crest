package kotlinx.coroutines;

import kotlin.jvm.JvmField;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class JobSupportKt {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Symbol f14141a = new Symbol("COMPLETING_ALREADY");
    @JvmField
    @NotNull
    public static final Symbol COMPLETING_WAITING_CHILDREN = new Symbol("COMPLETING_WAITING_CHILDREN");
    @NotNull
    public static final Symbol b = new Symbol("COMPLETING_RETRY");
    @NotNull
    public static final Symbol c = new Symbol("TOO_LATE_TO_CANCEL");
    @NotNull
    public static final Symbol d = new Symbol("SEALED");
    @NotNull
    public static final o e = new o(false);
    @NotNull
    public static final o f = new o(true);

    @Nullable
    public static final Object boxIncomplete(@Nullable Object obj) {
        return obj instanceof Incomplete ? new p((Incomplete) obj) : obj;
    }

    public static /* synthetic */ void getCOMPLETING_WAITING_CHILDREN$annotations() {
    }

    @Nullable
    public static final Object unboxState(@Nullable Object obj) {
        Incomplete incomplete;
        p pVar = obj instanceof p ? (p) obj : null;
        return (pVar == null || (incomplete = pVar.f14192a) == null) ? obj : incomplete;
    }
}
