package com.mappls.sdk.nearby.plugin.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.mappls.sdk.nearby.plugin.fragment.NearbyResultCallback;
import com.mappls.sdk.nearby.plugin.fragment.b;
import com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class b extends FragmentStateAdapter {
    @NotNull
    public final NearbyResultViewOption p;
    @NotNull
    public final NearbyResultCallback q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(@NotNull FragmentActivity activity, @NotNull NearbyResultViewOption viewOption, @NotNull NearbyResultCallback callback) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(viewOption, "viewOption");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.p = viewOption;
        this.q = callback;
    }

    public static void a(@NotNull NearbyResultCallback callback, @Nullable Fragment fragment) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (fragment != null) {
            if (fragment instanceof com.mappls.sdk.nearby.plugin.fragment.a) {
                ((com.mappls.sdk.nearby.plugin.fragment.a) fragment).a(callback);
            } else if (fragment instanceof com.mappls.sdk.nearby.plugin.fragment.b) {
                ((com.mappls.sdk.nearby.plugin.fragment.b) fragment).a(callback);
            }
        }
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    @NotNull
    public final Fragment createFragment(int i) {
        if (i == 0) {
            com.mappls.sdk.nearby.plugin.fragment.a aVar = new com.mappls.sdk.nearby.plugin.fragment.a();
            aVar.a(this.q);
            return aVar;
        }
        int i2 = com.mappls.sdk.nearby.plugin.fragment.b.f;
        com.mappls.sdk.nearby.plugin.fragment.b a2 = b.a.a(this.p);
        a2.a(this.q);
        return a2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return 2;
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public final long getItemId(int i) {
        return i;
    }
}
