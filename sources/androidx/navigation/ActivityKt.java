package androidx.navigation;

import android.app.Activity;
import androidx.annotation.IdRes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u0001¨\u0006\u0005"}, d2 = {"Landroid/app/Activity;", "", "viewId", "Landroidx/navigation/NavController;", "findNavController", "navigation-runtime-ktx_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class ActivityKt {
    @NotNull
    public static final NavController findNavController(@NotNull Activity findNavController, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(findNavController, "$this$findNavController");
        NavController findNavController2 = Navigation.findNavController(findNavController, i);
        Intrinsics.checkExpressionValueIsNotNull(findNavController2, "Navigation.findNavController(this, viewId)");
        return findNavController2;
    }
}
