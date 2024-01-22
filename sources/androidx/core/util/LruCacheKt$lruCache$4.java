package androidx.core.util;

import android.util.LruCache;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes.dex */
public final class LruCacheKt$lruCache$4<K, V> extends LruCache<K, V> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Function2<K, V, Integer> f1112a;
    public final /* synthetic */ Function1<K, V> b;
    public final /* synthetic */ Function4<Boolean, K, V, V, Unit> c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LruCacheKt$lruCache$4(int i, Function2<? super K, ? super V, Integer> function2, Function1<? super K, ? extends V> function1, Function4<? super Boolean, ? super K, ? super V, ? super V, Unit> function4) {
        super(i);
        this.f1112a = function2;
        this.b = function1;
        this.c = function4;
    }

    @Override // android.util.LruCache
    @Nullable
    public V create(@NotNull K key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.b.invoke(key);
    }

    @Override // android.util.LruCache
    public void entryRemoved(boolean z, @NotNull K key, @NotNull V oldValue, @Nullable V v) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(oldValue, "oldValue");
        this.c.invoke(Boolean.valueOf(z), key, oldValue, v);
    }

    @Override // android.util.LruCache
    public int sizeOf(@NotNull K key, @NotNull V value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return this.f1112a.invoke(key, value).intValue();
    }
}
