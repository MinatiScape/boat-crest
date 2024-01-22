package kotlinx.android.extensions;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public enum CacheImplementation {
    SPARSE_ARRAY,
    HASH_MAP,
    NO_CACHE;
    
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final CacheImplementation DEFAULT;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final CacheImplementation getDEFAULT() {
            return CacheImplementation.DEFAULT;
        }
    }

    static {
        DEFAULT = r0;
    }
}
