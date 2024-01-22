package androidx.fragment.app;

import android.os.Bundle;
import androidx.annotation.IdRes;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwx.HeaderParameterNames;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a;\u0010\t\u001a\u00020\u0002\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u00022\b\b\u0001\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0086\b\u001a-\u0010\t\u001a\u00020\u0002\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0086\b\u001a;\u0010\n\u001a\u00020\u0002\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u00022\b\b\u0001\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0086\b¨\u0006\u000b"}, d2 = {"Landroidx/fragment/app/Fragment;", WeatherCriteria.UNIT_FARENHEIT, "Landroidx/fragment/app/FragmentTransaction;", "", "containerViewId", "", HeaderParameterNames.AUTHENTICATION_TAG, "Landroid/os/Bundle;", "args", ProductAction.ACTION_ADD, "replace", "fragment-ktx_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class FragmentTransactionKt {
    @NotNull
    public static final /* synthetic */ <F extends Fragment> FragmentTransaction add(@NotNull FragmentTransaction add, @IdRes int i, @Nullable String str, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(add, "$this$add");
        Intrinsics.reifiedOperationMarker(4, WeatherCriteria.UNIT_FARENHEIT);
        FragmentTransaction add2 = add.add(i, Fragment.class, bundle, str);
        Intrinsics.checkExpressionValueIsNotNull(add2, "add(containerViewId, F::class.java, args, tag)");
        return add2;
    }

    public static /* synthetic */ FragmentTransaction add$default(FragmentTransaction add, int i, String str, Bundle bundle, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        if ((i2 & 4) != 0) {
            bundle = null;
        }
        Intrinsics.checkParameterIsNotNull(add, "$this$add");
        Intrinsics.reifiedOperationMarker(4, WeatherCriteria.UNIT_FARENHEIT);
        FragmentTransaction add2 = add.add(i, Fragment.class, bundle, str);
        Intrinsics.checkExpressionValueIsNotNull(add2, "add(containerViewId, F::class.java, args, tag)");
        return add2;
    }

    @NotNull
    public static final /* synthetic */ <F extends Fragment> FragmentTransaction replace(@NotNull FragmentTransaction replace, @IdRes int i, @Nullable String str, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(replace, "$this$replace");
        Intrinsics.reifiedOperationMarker(4, WeatherCriteria.UNIT_FARENHEIT);
        FragmentTransaction replace2 = replace.replace(i, Fragment.class, bundle, str);
        Intrinsics.checkExpressionValueIsNotNull(replace2, "replace(containerViewId, F::class.java, args, tag)");
        return replace2;
    }

    public static /* synthetic */ FragmentTransaction replace$default(FragmentTransaction replace, int i, String str, Bundle bundle, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        if ((i2 & 4) != 0) {
            bundle = null;
        }
        Intrinsics.checkParameterIsNotNull(replace, "$this$replace");
        Intrinsics.reifiedOperationMarker(4, WeatherCriteria.UNIT_FARENHEIT);
        FragmentTransaction replace2 = replace.replace(i, Fragment.class, bundle, str);
        Intrinsics.checkExpressionValueIsNotNull(replace2, "replace(containerViewId, F::class.java, args, tag)");
        return replace2;
    }

    @NotNull
    public static final /* synthetic */ <F extends Fragment> FragmentTransaction add(@NotNull FragmentTransaction add, @NotNull String tag, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(add, "$this$add");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.reifiedOperationMarker(4, WeatherCriteria.UNIT_FARENHEIT);
        FragmentTransaction add2 = add.add(Fragment.class, bundle, tag);
        Intrinsics.checkExpressionValueIsNotNull(add2, "add(F::class.java, args, tag)");
        return add2;
    }

    public static /* synthetic */ FragmentTransaction add$default(FragmentTransaction add, String tag, Bundle bundle, int i, Object obj) {
        if ((i & 2) != 0) {
            bundle = null;
        }
        Intrinsics.checkParameterIsNotNull(add, "$this$add");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.reifiedOperationMarker(4, WeatherCriteria.UNIT_FARENHEIT);
        FragmentTransaction add2 = add.add(Fragment.class, bundle, tag);
        Intrinsics.checkExpressionValueIsNotNull(add2, "add(F::class.java, args, tag)");
        return add2;
    }
}
