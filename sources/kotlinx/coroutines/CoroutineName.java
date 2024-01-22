package kotlinx.coroutines;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class CoroutineName extends AbstractCoroutineContextElement {
    @NotNull
    public static final Key Key = new Key(null);
    @NotNull
    public final String h;

    /* loaded from: classes12.dex */
    public static final class Key implements CoroutineContext.Key<CoroutineName> {
        public Key() {
        }

        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CoroutineName(@NotNull String str) {
        super(Key);
        this.h = str;
    }

    public static /* synthetic */ CoroutineName copy$default(CoroutineName coroutineName, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = coroutineName.h;
        }
        return coroutineName.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.h;
    }

    @NotNull
    public final CoroutineName copy(@NotNull String str) {
        return new CoroutineName(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CoroutineName) && Intrinsics.areEqual(this.h, ((CoroutineName) obj).h);
    }

    @NotNull
    public final String getName() {
        return this.h;
    }

    public int hashCode() {
        return this.h.hashCode();
    }

    @NotNull
    public String toString() {
        return "CoroutineName(" + this.h + HexStringBuilder.COMMENT_END_CHAR;
    }
}
