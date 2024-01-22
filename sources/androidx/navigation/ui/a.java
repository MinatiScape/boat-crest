package androidx.navigation.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.customview.widget.Openable;
import androidx.navigation.FloatingWindow;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public abstract class a implements NavController.OnDestinationChangedListener {

    /* renamed from: a  reason: collision with root package name */
    public final Context f1468a;
    public final Set<Integer> b;
    @Nullable
    public final WeakReference<Openable> c;
    public DrawerArrowDrawable d;
    public ValueAnimator e;

    public a(@NonNull Context context, @NonNull AppBarConfiguration appBarConfiguration) {
        this.f1468a = context;
        this.b = appBarConfiguration.getTopLevelDestinations();
        Openable openableLayout = appBarConfiguration.getOpenableLayout();
        if (openableLayout != null) {
            this.c = new WeakReference<>(openableLayout);
        } else {
            this.c = null;
        }
    }

    public final void a(boolean z) {
        boolean z2;
        int i;
        if (this.d == null) {
            this.d = new DrawerArrowDrawable(this.f1468a);
            z2 = false;
        } else {
            z2 = true;
        }
        DrawerArrowDrawable drawerArrowDrawable = this.d;
        if (z) {
            i = R.string.nav_app_bar_open_drawer_description;
        } else {
            i = R.string.nav_app_bar_navigate_up_description;
        }
        b(drawerArrowDrawable, i);
        float f = z ? 0.0f : 1.0f;
        if (z2) {
            float progress = this.d.getProgress();
            ValueAnimator valueAnimator = this.e;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.d, "progress", progress, f);
            this.e = ofFloat;
            ofFloat.start();
            return;
        }
        this.d.setProgress(f);
    }

    public abstract void b(Drawable drawable, @StringRes int i);

    public abstract void c(CharSequence charSequence);

    @Override // androidx.navigation.NavController.OnDestinationChangedListener
    public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
        if (navDestination instanceof FloatingWindow) {
            return;
        }
        WeakReference<Openable> weakReference = this.c;
        Openable openable = weakReference != null ? weakReference.get() : null;
        if (this.c != null && openable == null) {
            navController.removeOnDestinationChangedListener(this);
            return;
        }
        CharSequence label = navDestination.getLabel();
        boolean z = true;
        if (label != null) {
            StringBuffer stringBuffer = new StringBuffer();
            Matcher matcher = Pattern.compile("\\{(.+?)\\}").matcher(label);
            while (matcher.find()) {
                String group = matcher.group(1);
                if (bundle != null && bundle.containsKey(group)) {
                    matcher.appendReplacement(stringBuffer, "");
                    stringBuffer.append(bundle.get(group).toString());
                } else {
                    throw new IllegalArgumentException("Could not find " + group + " in " + bundle + " to fill label " + ((Object) label));
                }
            }
            matcher.appendTail(stringBuffer);
            c(stringBuffer);
        }
        boolean d = NavigationUI.d(navDestination, this.b);
        if (openable == null && d) {
            b(null, 0);
            return;
        }
        if (openable == null || !d) {
            z = false;
        }
        a(z);
    }
}
