package androidx.fragment.app;

import android.view.View;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0003\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/fragment/app/Fragment;", WeatherCriteria.UNIT_FARENHEIT, "Landroid/view/View;", "findFragment", "(Landroid/view/View;)Landroidx/fragment/app/Fragment;", "fragment-ktx_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class ViewKt {
    @NotNull
    public static final <F extends Fragment> F findFragment(@NotNull View findFragment) {
        Intrinsics.checkParameterIsNotNull(findFragment, "$this$findFragment");
        F f = (F) FragmentManager.findFragment(findFragment);
        Intrinsics.checkExpressionValueIsNotNull(f, "FragmentManager.findFragment(this)");
        return f;
    }
}
