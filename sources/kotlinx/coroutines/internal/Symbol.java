package kotlinx.coroutines.internal;

import kotlin.jvm.JvmField;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class Symbol {
    @JvmField
    @NotNull
    public final String symbol;

    public Symbol(@NotNull String str) {
        this.symbol = str;
    }

    @NotNull
    public String toString() {
        return Typography.less + this.symbol + Typography.greater;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> T unbox(@Nullable Object obj) {
        if (obj == this) {
            return null;
        }
        return obj;
    }
}
