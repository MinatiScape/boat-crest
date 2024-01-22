package androidx.navigation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public final class Navigation {

    /* loaded from: classes.dex */
    public class a implements View.OnClickListener {
        public final /* synthetic */ int h;
        public final /* synthetic */ Bundle i;

        public a(int i, Bundle bundle) {
            this.h = i;
            this.i = bundle;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Navigation.findNavController(view).navigate(this.h, this.i);
        }
    }

    /* loaded from: classes.dex */
    public class b implements View.OnClickListener {
        public final /* synthetic */ NavDirections h;

        public b(NavDirections navDirections) {
            this.h = navDirections;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Navigation.findNavController(view).navigate(this.h);
        }
    }

    @Nullable
    public static NavController a(@NonNull View view) {
        while (view != null) {
            NavController b2 = b(view);
            if (b2 != null) {
                return b2;
            }
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return null;
    }

    @Nullable
    public static NavController b(@NonNull View view) {
        Object tag = view.getTag(R.id.nav_controller_view_tag);
        if (tag instanceof WeakReference) {
            return (NavController) ((WeakReference) tag).get();
        }
        if (tag instanceof NavController) {
            return (NavController) tag;
        }
        return null;
    }

    @NonNull
    public static View.OnClickListener createNavigateOnClickListener(@IdRes int i) {
        return createNavigateOnClickListener(i, null);
    }

    @NonNull
    public static NavController findNavController(@NonNull Activity activity, @IdRes int i) {
        NavController a2 = a(ActivityCompat.requireViewById(activity, i));
        if (a2 != null) {
            return a2;
        }
        throw new IllegalStateException("Activity " + activity + " does not have a NavController set on " + i);
    }

    public static void setViewNavController(@NonNull View view, @Nullable NavController navController) {
        view.setTag(R.id.nav_controller_view_tag, navController);
    }

    @NonNull
    public static View.OnClickListener createNavigateOnClickListener(@IdRes int i, @Nullable Bundle bundle) {
        return new a(i, bundle);
    }

    @NonNull
    public static View.OnClickListener createNavigateOnClickListener(@NonNull NavDirections navDirections) {
        return new b(navDirections);
    }

    @NonNull
    public static NavController findNavController(@NonNull View view) {
        NavController a2 = a(view);
        if (a2 != null) {
            return a2;
        }
        throw new IllegalStateException("View " + view + " does not have a NavController set");
    }
}
