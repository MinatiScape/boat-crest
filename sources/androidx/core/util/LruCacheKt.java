package androidx.core.util;

import android.util.LruCache;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class LruCacheKt {
    @NotNull
    public static final <K, V> LruCache<K, V> lruCache(int i, @NotNull Function2<? super K, ? super V, Integer> sizeOf, @NotNull Function1<? super K, ? extends V> create, @NotNull Function4<? super Boolean, ? super K, ? super V, ? super V, Unit> onEntryRemoved) {
        Intrinsics.checkNotNullParameter(sizeOf, "sizeOf");
        Intrinsics.checkNotNullParameter(create, "create");
        Intrinsics.checkNotNullParameter(onEntryRemoved, "onEntryRemoved");
        return new LruCacheKt$lruCache$4(i, sizeOf, create, onEntryRemoved);
    }

    public static /* synthetic */ LruCache lruCache$default(int i, Function2 sizeOf, Function1 create, Function4 onEntryRemoved, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            sizeOf = new Function2<K, V, Integer>() { // from class: androidx.core.util.LruCacheKt$lruCache$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function2
                @NotNull
                public final Integer invoke(@NotNull K k, @NotNull V v) {
                    Intrinsics.checkNotNullParameter(k, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(v, "<anonymous parameter 1>");
                    return 1;
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Integer invoke(Object obj2, Object obj3) {
                    return invoke((LruCacheKt$lruCache$1<K, V>) obj2, obj3);
                }
            };
        }
        if ((i2 & 4) != 0) {
            create = new Function1<K, V>() { // from class: androidx.core.util.LruCacheKt$lruCache$2
                @Override // kotlin.jvm.functions.Function1
                @Nullable
                public final V invoke(@NotNull K it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return null;
                }
            };
        }
        if ((i2 & 8) != 0) {
            onEntryRemoved = new Function4<Boolean, K, V, V, Unit>() { // from class: androidx.core.util.LruCacheKt$lruCache$3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Object obj2, Object obj3, Object obj4) {
                    invoke(bool.booleanValue(), (boolean) obj2, obj3, obj4);
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z, @NotNull K k, @NotNull V v, @Nullable V v2) {
                    Intrinsics.checkNotNullParameter(k, "<anonymous parameter 1>");
                    Intrinsics.checkNotNullParameter(v, "<anonymous parameter 2>");
                }
            };
        }
        Intrinsics.checkNotNullParameter(sizeOf, "sizeOf");
        Intrinsics.checkNotNullParameter(create, "create");
        Intrinsics.checkNotNullParameter(onEntryRemoved, "onEntryRemoved");
        return new LruCacheKt$lruCache$4(i, sizeOf, create, onEntryRemoved);
    }
}
