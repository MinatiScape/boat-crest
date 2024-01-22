package com.coveiot.android.leonardo.dashboard.home.listeners;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.FragmentNavigator;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Navigator.Name("keep_state_fragment")
/* loaded from: classes3.dex */
public final class KeepStateNavigator extends FragmentNavigator {
    @NotNull
    public final Context e;
    @NotNull
    public final FragmentManager f;
    public final int g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeepStateNavigator(@NotNull Context context, @NotNull FragmentManager manager, int i) {
        super(context, manager, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(manager, "manager");
        this.e = context;
        this.f = manager;
        this.g = i;
    }

    @Override // androidx.navigation.fragment.FragmentNavigator, androidx.navigation.Navigator
    @Nullable
    public NavDestination navigate(@NotNull FragmentNavigator.Destination destination, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Navigator.Extras extras) {
        boolean z;
        Intrinsics.checkNotNullParameter(destination, "destination");
        String valueOf = String.valueOf(destination.getId());
        FragmentTransaction beginTransaction = this.f.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "manager.beginTransaction()");
        Fragment primaryNavigationFragment = this.f.getPrimaryNavigationFragment();
        if (primaryNavigationFragment != null) {
            beginTransaction.detach(primaryNavigationFragment);
            z = false;
        } else {
            z = true;
        }
        Fragment findFragmentByTag = this.f.findFragmentByTag(valueOf);
        if (findFragmentByTag == null) {
            String className = destination.getClassName();
            Intrinsics.checkNotNullExpressionValue(className, "destination.className");
            findFragmentByTag = this.f.getFragmentFactory().instantiate(this.e.getClassLoader(), className);
            beginTransaction.add(this.g, findFragmentByTag, valueOf);
        } else {
            beginTransaction.attach(findFragmentByTag);
        }
        beginTransaction.setPrimaryNavigationFragment(findFragmentByTag);
        beginTransaction.setReorderingAllowed(true);
        beginTransaction.commitNow();
        if (z) {
            return destination;
        }
        return null;
    }
}
