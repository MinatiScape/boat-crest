package androidx.navigation;

import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public final class NavAction {
    @IdRes

    /* renamed from: a  reason: collision with root package name */
    public final int f1430a;
    public NavOptions b;
    public Bundle c;

    public NavAction(@IdRes int i) {
        this(i, null);
    }

    @Nullable
    public Bundle getDefaultArguments() {
        return this.c;
    }

    public int getDestinationId() {
        return this.f1430a;
    }

    @Nullable
    public NavOptions getNavOptions() {
        return this.b;
    }

    public void setDefaultArguments(@Nullable Bundle bundle) {
        this.c = bundle;
    }

    public void setNavOptions(@Nullable NavOptions navOptions) {
        this.b = navOptions;
    }

    public NavAction(@IdRes int i, @Nullable NavOptions navOptions) {
        this(i, navOptions, null);
    }

    public NavAction(@IdRes int i, @Nullable NavOptions navOptions, @Nullable Bundle bundle) {
        this.f1430a = i;
        this.b = navOptions;
        this.c = bundle;
    }
}
