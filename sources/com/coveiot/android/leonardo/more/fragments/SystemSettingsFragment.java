package com.coveiot.android.leonardo.more.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.activities.ActivityAboutUs;
import com.coveiot.android.leonardo.more.activities.ActivityHelpFeedback;
import com.coveiot.android.leonardo.more.activities.ActivityLinkedApplications;
import com.coveiot.android.leonardo.more.adapters.WatchSettingsAdapter;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.permissionsandsettings.AppConfigurationsActivity;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.model.SettingsListItemModel;
import com.coveiot.android.theme.utils.ThemesUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SystemSettingsFragment extends Fragment implements WatchSettingsAdapter.GuestUserListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final SystemSettingsFragment newInstance() {
            return new SystemSettingsFragment();
        }
    }

    public static final void e(BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        guestOrPairDevicePopup.dismiss();
    }

    public static final void f(SystemSettingsFragment this$0, BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (themesUtils.isGuestUser(requireContext)) {
            AppNavigator.Companion companion = AppNavigator.Companion;
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            companion.navigateToLogin(requireContext2);
        } else {
            AppNavigator.Companion companion2 = AppNavigator.Companion;
            Context requireContext3 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            companion2.navigateToPairYourDevice(requireContext3);
        }
        guestOrPairDevicePopup.dismiss();
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    @JvmStatic
    @NotNull
    public static final SystemSettingsFragment newInstance() {
        return Companion.newInstance();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public final void c() {
        int i = R.id.rcv_system_settings1;
        ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(requireContext()));
        ArrayList arrayList = new ArrayList();
        String string = getString(R.string.linked_applications);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.linked_applications)");
        arrayList.add(new SettingsListItemModel(R.drawable.linked_applications_icon, string, ActivityLinkedApplications.class, null, null, false, 48, null));
        String string2 = getString(R.string.app_configurations);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.app_configurations)");
        arrayList.add(new SettingsListItemModel(R.drawable.app_config_icon, string2, AppConfigurationsActivity.class, null, null, false, 48, null));
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ((RecyclerView) _$_findCachedViewById(i)).setAdapter(new WatchSettingsAdapter(requireContext, arrayList, this));
        int i2 = R.id.rcv_system_settings2;
        ((RecyclerView) _$_findCachedViewById(i2)).setLayoutManager(new LinearLayoutManager(requireContext()));
        ArrayList arrayList2 = new ArrayList();
        String string3 = getString(R.string.help_support);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.help_support)");
        arrayList2.add(new SettingsListItemModel(R.drawable.help_support_icon, string3, ActivityHelpFeedback.class, null, null, false, 48, null));
        String string4 = getString(R.string.about);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.about)");
        arrayList2.add(new SettingsListItemModel(R.drawable.about_icon_new, string4, ActivityAboutUs.class, null, null, false, 48, null));
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        ((RecyclerView) _$_findCachedViewById(i2)).setAdapter(new WatchSettingsAdapter(requireContext2, arrayList2, this));
    }

    public final void d() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String string = getString(themesUtils.isGuestUser(requireContext2) ? R.string.login : R.string.pair_device);
        Intrinsics.checkNotNullExpressionValue(string, "if(requireContext().isGu…ing(R.string.pair_device)");
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        String string2 = getString(themesUtils.isGuestUser(requireContext3) ? R.string.please_login_to_use_this_feature : R.string.please_pair_with_your_bluetooth_device_to_use_this_feature);
        Intrinsics.checkNotNullExpressionValue(string2, "if(requireContext().isGu…vice_to_use_this_feature)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireContext, string, string2, false, 8, null);
        String string3 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
        bottomSheetDialogTwoButtons.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.e2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SystemSettingsFragment.e(BottomSheetDialogTwoButtons.this, view);
            }
        });
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        String string4 = getString(themesUtils.isGuestUser(requireContext4) ? R.string.login_now : R.string.pair_now);
        Intrinsics.checkNotNullExpressionValue(string4, "if(requireContext().isGu…String(R.string.pair_now)");
        bottomSheetDialogTwoButtons.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.d2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SystemSettingsFragment.f(SystemSettingsFragment.this, bottomSheetDialogTwoButtons, view);
            }
        });
        if (bottomSheetDialogTwoButtons.isShowing()) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    @Override // com.coveiot.android.leonardo.more.adapters.WatchSettingsAdapter.GuestUserListener
    public void isGuestUser() {
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                return;
            }
        }
        d();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_system_settings, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        c();
    }
}
