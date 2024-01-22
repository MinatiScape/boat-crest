package androidx.navigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigator;
@Navigator.Name("navigation")
/* loaded from: classes.dex */
public class NavGraphNavigator extends Navigator<NavGraph> {

    /* renamed from: a  reason: collision with root package name */
    public final NavigatorProvider f1448a;

    public NavGraphNavigator(@NonNull NavigatorProvider navigatorProvider) {
        this.f1448a = navigatorProvider;
    }

    @Override // androidx.navigation.Navigator
    public boolean popBackStack() {
        return true;
    }

    @Override // androidx.navigation.Navigator
    @NonNull
    public NavGraph createDestination() {
        return new NavGraph(this);
    }

    @Override // androidx.navigation.Navigator
    @Nullable
    public NavDestination navigate(@NonNull NavGraph navGraph, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Navigator.Extras extras) {
        int startDestination = navGraph.getStartDestination();
        if (startDestination != 0) {
            NavDestination g = navGraph.g(startDestination, false);
            if (g != null) {
                return this.f1448a.getNavigator(g.getNavigatorName()).navigate(g, g.a(bundle), navOptions, extras);
            }
            String h = navGraph.h();
            throw new IllegalArgumentException("navigation destination " + h + " is not a direct child of this NavGraph");
        }
        throw new IllegalStateException("no start destination defined via app:startDestination for " + navGraph.getDisplayName());
    }
}
