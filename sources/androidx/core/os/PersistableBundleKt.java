package androidx.core.os;

import android.os.PersistableBundle;
import androidx.annotation.RequiresApi;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class PersistableBundleKt {
    @RequiresApi(21)
    @NotNull
    public static final PersistableBundle persistableBundleOf(@NotNull Pair<String, ? extends Object>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        PersistableBundle a2 = g.a(pairs.length);
        for (Pair<String, ? extends Object> pair : pairs) {
            g.b(a2, pair.component1(), pair.component2());
        }
        return a2;
    }

    @RequiresApi(21)
    @NotNull
    public static final PersistableBundle toPersistableBundle(@NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        PersistableBundle a2 = g.a(map.size());
        for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
            g.b(a2, entry.getKey(), entry.getValue());
        }
        return a2;
    }

    @RequiresApi(21)
    @NotNull
    public static final PersistableBundle persistableBundleOf() {
        return g.a(0);
    }
}
