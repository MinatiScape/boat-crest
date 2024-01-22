package com.clevertap.android.sdk.inapp;

import androidx.fragment.app.FragmentManager;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Utils;
/* loaded from: classes2.dex */
public abstract class CTInAppBasePartialFragment extends CTInAppBaseFragment {
    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFragment
    void b() {
        FragmentManager fragmentManager;
        if (!Utils.isActivityDead(getActivity()) && !this.m.get() && (fragmentManager = getFragmentManager()) != null) {
            try {
                fragmentManager.beginTransaction().remove(this).commit();
            } catch (IllegalStateException unused) {
                fragmentManager.beginTransaction().remove(this).commitAllowingStateLoss();
            }
        }
        this.m.set(true);
    }

    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFragment
    public void f() {
        CleverTapInstanceConfig cleverTapInstanceConfig = this.i;
        if (cleverTapInstanceConfig != null) {
            j(CleverTapAPI.instanceWithConfig(this.j, cleverTapInstanceConfig).getCoreState().getInAppController());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.m.get()) {
            b();
        }
    }
}
