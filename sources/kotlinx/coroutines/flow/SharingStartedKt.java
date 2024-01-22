package kotlinx.coroutines.flow;

import kotlin.time.Duration;
import kotlinx.coroutines.flow.SharingStarted;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class SharingStartedKt {
    @NotNull
    /* renamed from: WhileSubscribed-5qebJ5I  reason: not valid java name */
    public static final SharingStarted m760WhileSubscribed5qebJ5I(@NotNull SharingStarted.Companion companion, long j, long j2) {
        return new r(Duration.m613getInWholeMillisecondsimpl(j), Duration.m613getInWholeMillisecondsimpl(j2));
    }

    /* renamed from: WhileSubscribed-5qebJ5I$default  reason: not valid java name */
    public static /* synthetic */ SharingStarted m761WhileSubscribed5qebJ5I$default(SharingStarted.Companion companion, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Duration.Companion.m671getZEROUwyO8pc();
        }
        if ((i & 2) != 0) {
            j2 = Duration.Companion.m669getINFINITEUwyO8pc();
        }
        return m760WhileSubscribed5qebJ5I(companion, j, j2);
    }
}
