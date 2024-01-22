package androidx.lifecycle;

import androidx.arch.core.util.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a>\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0014\b\u0004\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003H\u0086\bø\u0001\u0000\u001aD\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00022\u001a\b\u0004\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00020\u0003H\u0086\bø\u0001\u0000\u001a\u001f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\b"}, d2 = {"X", "Y", "Landroidx/lifecycle/LiveData;", "Lkotlin/Function1;", "transform", "map", "switchMap", "distinctUntilChanged", "lifecycle-livedata-ktx_release"}, k = 2, mv = {1, 4, 1})
/* loaded from: classes.dex */
public final class TransformationsKt {
    @NotNull
    public static final <X> LiveData<X> distinctUntilChanged(@NotNull LiveData<X> distinctUntilChanged) {
        Intrinsics.checkNotNullParameter(distinctUntilChanged, "$this$distinctUntilChanged");
        LiveData<X> distinctUntilChanged2 = Transformations.distinctUntilChanged(distinctUntilChanged);
        Intrinsics.checkNotNullExpressionValue(distinctUntilChanged2, "Transformations.distinctUntilChanged(this)");
        return distinctUntilChanged2;
    }

    @NotNull
    public static final <X, Y> LiveData<Y> map(@NotNull LiveData<X> map, @NotNull final Function1<? super X, ? extends Y> transform) {
        Intrinsics.checkNotNullParameter(map, "$this$map");
        Intrinsics.checkNotNullParameter(transform, "transform");
        LiveData<Y> map2 = Transformations.map(map, new Function<X, Y>() { // from class: androidx.lifecycle.TransformationsKt$map$1
            @Override // androidx.arch.core.util.Function
            public final Y apply(X x) {
                return (Y) Function1.this.invoke(x);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map2, "Transformations.map(this) { transform(it) }");
        return map2;
    }

    @NotNull
    public static final <X, Y> LiveData<Y> switchMap(@NotNull LiveData<X> switchMap, @NotNull final Function1<? super X, ? extends LiveData<Y>> transform) {
        Intrinsics.checkNotNullParameter(switchMap, "$this$switchMap");
        Intrinsics.checkNotNullParameter(transform, "transform");
        LiveData<Y> switchMap2 = Transformations.switchMap(switchMap, new Function<X, LiveData<Y>>() { // from class: androidx.lifecycle.TransformationsKt$switchMap$1
            @Override // androidx.arch.core.util.Function
            public final LiveData<Y> apply(X x) {
                return (LiveData) Function1.this.invoke(x);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.arch.core.util.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply((TransformationsKt$switchMap$1<I, O, X, Y>) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(switchMap2, "Transformations.switchMap(this) { transform(it) }");
        return switchMap2;
    }
}
