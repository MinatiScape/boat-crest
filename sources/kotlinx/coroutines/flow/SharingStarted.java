package kotlinx.coroutines.flow;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface SharingStarted {
    @NotNull
    public static final Companion Companion = Companion.f14159a;

    /* loaded from: classes12.dex */
    public static final class Companion {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ Companion f14159a = new Companion();
        @NotNull
        public static final SharingStarted b = new p();
        @NotNull
        public static final SharingStarted c = new q();

        public static /* synthetic */ SharingStarted WhileSubscribed$default(Companion companion, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = 0;
            }
            if ((i & 2) != 0) {
                j2 = Long.MAX_VALUE;
            }
            return companion.WhileSubscribed(j, j2);
        }

        @NotNull
        public final SharingStarted WhileSubscribed(long j, long j2) {
            return new r(j, j2);
        }

        @NotNull
        public final SharingStarted getEagerly() {
            return b;
        }

        @NotNull
        public final SharingStarted getLazily() {
            return c;
        }
    }

    @NotNull
    Flow<SharingCommand> command(@NotNull StateFlow<Integer> stateFlow);
}
