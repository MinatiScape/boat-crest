package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
@ViewModelFactoryDsl
/* loaded from: classes.dex */
public final class InitializerViewModelFactoryBuilder {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<ViewModelInitializer<?>> f1382a = new ArrayList();

    public final <T extends ViewModel> void addInitializer(@NotNull KClass<T> clazz, @NotNull Function1<? super CreationExtras, ? extends T> initializer) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        this.f1382a.add(new ViewModelInitializer<>(JvmClassMappingKt.getJavaClass((KClass) clazz), initializer));
    }

    @NotNull
    public final ViewModelProvider.Factory build() {
        Object[] array = this.f1382a.toArray(new ViewModelInitializer[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        ViewModelInitializer[] viewModelInitializerArr = (ViewModelInitializer[]) array;
        return new InitializerViewModelFactory((ViewModelInitializer[]) Arrays.copyOf(viewModelInitializerArr, viewModelInitializerArr.length));
    }
}
