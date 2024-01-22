package com.coveiot.android.leonardo.more.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.activities.ActivityAboutYourDevice;
import com.coveiot.android.leonardo.more.activities.ActivityBandSettings;
import com.coveiot.android.leonardo.more.activities.ActivityMoreDeviceFeaturesNew;
import com.coveiot.android.leonardo.more.activities.ActivityWatchStatus;
import com.coveiot.android.leonardo.more.adapters.WatchSettingsAdapter;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.WatchDisconnectedStatusListener;
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
public final class WatchSettingsFragmentNew extends BaseFragment implements Observer<ConnectionStatus>, WatchDisconnectedStatusListener, WatchSettingsAdapter.GuestUserListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ViewDataBinding m;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final WatchSettingsFragmentNew newInstance() {
            return new WatchSettingsFragmentNew();
        }
    }

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ConnectionStatus.values().length];
            try {
                iArr[ConnectionStatus.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ConnectionStatus.DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ConnectionStatus.CONNECTING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ConnectionStatus.SCANNING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    @NotNull
    public static final WatchSettingsFragmentNew newInstance() {
        return Companion.newInstance();
    }

    public static final void s(WatchSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                if (BleApiManager.getInstance(this$0.getContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    this$0.startActivity(new Intent(this$0.requireContext(), ActivityAboutYourDevice.class));
                    return;
                } else {
                    this$0.showWatchDisconnectedDialog();
                    return;
                }
            }
        }
        this$0.v();
    }

    public static final void u(WatchSettingsFragmentNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                this$0.startActivity(new Intent(this$0.requireContext(), ActivityWatchStatus.class));
                return;
            }
        }
        this$0.v();
    }

    public static final void w(BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        guestOrPairDevicePopup.dismiss();
    }

    public static final void x(WatchSettingsFragmentNew this$0, BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
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

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
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

    @Override // com.coveiot.android.leonardo.more.adapters.WatchSettingsAdapter.GuestUserListener
    public void isGuestUser() {
    }

    public final String o() {
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                int i = WhenMappings.$EnumSwitchMapping$0[BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus().ordinal()];
                if (i != 1) {
                    if (i != 3) {
                        if (i != 4) {
                            return "Disconnected";
                        }
                        String string = getString(R.string.scanning);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.scanning)");
                        return string;
                    }
                    return "Connecting";
                }
                return "Connected";
            }
        }
        String string2 = getString(R.string.device_not_paired);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.device_not_paired)");
        return string2;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_watch_settings_new, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n            inf…          false\n        )");
        this.m = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        return inflate.getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                BleApiManager.getInstance(requireContext()).getBleApi().registerConnectionStatus().observe(getViewLifecycleOwner(), this);
            }
        }
        t();
        q();
        r();
    }

    @Override // com.coveiot.android.theme.WatchDisconnectedStatusListener
    public void onWatchDisconnected() {
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                showWatchDisconnectedDialog();
                return;
            }
        }
        v();
    }

    public final int p() {
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext)) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext2)) {
                int i = WhenMappings.$EnumSwitchMapping$0[BleApiManager.getInstance(requireContext()).getBleApi().getConnectionStatus().ordinal()];
                return (i == 1 || i == 3) ? R.color.color_state_connected : R.color.color_state_dicconnected;
            }
        }
        return R.color.color_state_dicconnected;
    }

    public final void q() {
        int i = R.id.rcv_settings;
        ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(requireContext()));
        ArrayList arrayList = new ArrayList();
        String string = getString(R.string.watch_features);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.watch_features)");
        arrayList.add(new SettingsListItemModel(R.drawable.watch_features_icon, string, ActivityMoreDeviceFeaturesNew.class, null, null, true, 16, null));
        String string2 = getString(R.string.watch_control);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.watch_control)");
        arrayList.add(new SettingsListItemModel(R.drawable.watch_control_cion, string2, ActivityBandSettings.class, null, null, true));
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        WatchSettingsAdapter watchSettingsAdapter = new WatchSettingsAdapter(requireContext, arrayList, this);
        watchSettingsAdapter.setWatchDisconnectedStatusListner(this);
        ((RecyclerView) _$_findCachedViewById(i)).setAdapter(watchSettingsAdapter);
    }

    public final void r() {
        ViewDataBinding viewDataBinding = this.m;
        ViewDataBinding viewDataBinding2 = null;
        if (viewDataBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            viewDataBinding = null;
        }
        View root = viewDataBinding.getRoot();
        int i = R.id.about_watch_layout;
        ((ImageView) root.findViewById(i).findViewById(R.id.settings_icon)).setImageResource(R.drawable.about_watch_icon);
        ViewDataBinding viewDataBinding3 = this.m;
        if (viewDataBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            viewDataBinding3 = null;
        }
        ((TextView) viewDataBinding3.getRoot().findViewById(i).findViewById(R.id.settings_name)).setText(getString(R.string.about_watch));
        ViewDataBinding viewDataBinding4 = this.m;
        if (viewDataBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            viewDataBinding2 = viewDataBinding4;
        }
        viewDataBinding2.getRoot().findViewById(i).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.j3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WatchSettingsFragmentNew.s(WatchSettingsFragmentNew.this, view);
            }
        });
    }

    public final void t() {
        ViewDataBinding viewDataBinding = this.m;
        ViewDataBinding viewDataBinding2 = null;
        if (viewDataBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            viewDataBinding = null;
        }
        View root = viewDataBinding.getRoot();
        int i = R.id.watch_status_layout;
        ((ImageView) root.findViewById(i).findViewById(R.id.settings_icon)).setImageResource(R.drawable.bluetooth_icon);
        ViewDataBinding viewDataBinding3 = this.m;
        if (viewDataBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            viewDataBinding3 = null;
        }
        ((TextView) viewDataBinding3.getRoot().findViewById(i).findViewById(R.id.status_text)).setText(o());
        ViewDataBinding viewDataBinding4 = this.m;
        if (viewDataBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            viewDataBinding4 = null;
        }
        ((TextView) viewDataBinding4.getRoot().findViewById(i).findViewById(R.id.status_text)).setTextColor(requireContext().getColor(p()));
        ViewDataBinding viewDataBinding5 = this.m;
        if (viewDataBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            viewDataBinding2 = viewDataBinding5;
        }
        viewDataBinding2.getRoot().findViewById(i).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.k3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WatchSettingsFragmentNew.u(WatchSettingsFragmentNew.this, view);
            }
        });
    }

    public final void v() {
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
        bottomSheetDialogTwoButtons.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.m3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WatchSettingsFragmentNew.w(BottomSheetDialogTwoButtons.this, view);
            }
        });
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        String string4 = getString(themesUtils.isGuestUser(requireContext4) ? R.string.login_now : R.string.pair_now);
        Intrinsics.checkNotNullExpressionValue(string4, "if(requireContext().isGu…String(R.string.pair_now)");
        bottomSheetDialogTwoButtons.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.l3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WatchSettingsFragmentNew.x(WatchSettingsFragmentNew.this, bottomSheetDialogTwoButtons, view);
            }
        });
        if (bottomSheetDialogTwoButtons.isShowing()) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ConnectionStatus connectionStatus) {
        if (connectionStatus != null && (connectionStatus == ConnectionStatus.CONNECTING || connectionStatus == ConnectionStatus.CONNECTED)) {
            dismissWatchDisconnectedDialog();
        }
        t();
    }
}
