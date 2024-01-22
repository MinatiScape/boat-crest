package com.coveiot.android.leonardo.dashboard.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentVitalsBinding;
import com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface;
import com.coveiot.android.leonardo.dashboard.health.fragments.FragmentAmbientSoundLevel;
import com.coveiot.android.leonardo.dashboard.health.fragments.FragmentHeartRate;
import com.coveiot.android.leonardo.dashboard.home.adapters.MainVitalsAdapter;
import com.coveiot.android.leonardo.dashboard.model.VitalsModel;
import com.coveiot.android.leonardo.dashboard.utility.ActivityDashboardNewHelper;
import com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalHRVPeriodicFragment;
import com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalHeartRateFragment;
import com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalStepsFragment;
import com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalTemperatureFragment;
import com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsManualSpo2Fragment;
import com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsManualStressFragment;
import com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsPeriodicSpo2Fragment;
import com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsPeriodicStressFragment;
import com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsSleepFragment;
import com.coveiot.android.respiratoryrate.fragments.VitalRespiratoryRateFragment;
import com.coveiot.android.sleepenergyscore.energymeter.fragments.VitalEnergyMeterFragment;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.utils.ThemesUtils;
import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentVitals extends BaseFragment implements MainVitalsAdapter.VitalsClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String m = "FragmentVitals";
    @Nullable
    public FragmentVitalsBinding n;
    public MainVitalsAdapter o;
    public int p;
    @Nullable
    public Calendar q;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ FragmentVitals newInstance$default(Companion companion, int i, Calendar calendar, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                calendar = null;
            }
            return companion.newInstance(i, calendar);
        }

        @NotNull
        public final FragmentVitals newInstance(int i, @Nullable Calendar calendar) {
            FragmentVitals fragmentVitals = new FragmentVitals();
            Bundle bundle = new Bundle();
            bundle.putInt("vitalTabPosition", i);
            bundle.putSerializable("calender", calendar);
            fragmentVitals.setArguments(bundle);
            return fragmentVitals;
        }
    }

    public static final void o(FragmentVitals this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.p();
    }

    public static final void q(BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        guestOrPairDevicePopup.dismiss();
    }

    public static final void r(FragmentVitals this$0, BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (themesUtils.isGuestUser(requireContext)) {
            if (this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
                FragmentActivity activity = this$0.getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
                ((FragmentHomeCallBackInterface) activity).navigateToLogin();
            }
        } else if (this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity2 = this$0.getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity2).navigateToPairDevice();
        }
        guestOrPairDevicePopup.dismiss();
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

    @Nullable
    public final Calendar getCalender() {
        return this.q;
    }

    @NotNull
    public final String getTAG() {
        return this.m;
    }

    public final FragmentVitalsBinding n() {
        FragmentVitalsBinding fragmentVitalsBinding = this.n;
        Intrinsics.checkNotNull(fragmentVitalsBinding);
        return fragmentVitalsBinding;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            this.p = arguments.getInt("vitalTabPosition");
            Bundle arguments2 = getArguments();
            Intrinsics.checkNotNull(arguments2);
            if (arguments2.getSerializable("calender") != null) {
                Bundle arguments3 = getArguments();
                Intrinsics.checkNotNull(arguments3);
                Serializable serializable = arguments3.getSerializable("calender");
                Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type java.util.Calendar");
                this.q = (Calendar) serializable;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.n = FragmentVitalsBinding.inflate(inflater, viewGroup, false);
        View root = n().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
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
        if (this.p < 0) {
            this.p = 0;
        }
        FragmentVitalsBinding n = n();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        MainVitalsAdapter mainVitalsAdapter = new MainVitalsAdapter(requireContext, this);
        this.o = mainVitalsAdapter;
        n.rvVitals.setAdapter(mainVitalsAdapter);
        n.rvVitals.setLayoutManager(new LinearLayoutManager(requireActivity(), 0, false));
        n.rvVitals.scrollToPosition(this.p);
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        if (!themesUtils.isGuestUser(requireContext2)) {
            Context requireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            if (!themesUtils.isPairDeviceLater(requireContext3)) {
                MainVitalsAdapter mainVitalsAdapter2 = this.o;
                if (mainVitalsAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mainVitalsAdapter");
                    mainVitalsAdapter2 = null;
                }
                ActivityDashboardNewHelper activityDashboardNewHelper = ActivityDashboardNewHelper.INSTANCE;
                Context requireContext4 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                mainVitalsAdapter2.setVitalsList(activityDashboardNewHelper.getVitalsList(requireContext4));
                Context requireContext5 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
                VitalsModel vitalsModel = activityDashboardNewHelper.getVitalsList(requireContext5).get(this.p);
                Intrinsics.checkNotNullExpressionValue(vitalsModel, "ActivityDashboardNewHelp…()).get(vitalTabPosition)");
                selectedVital(vitalsModel, this.p);
                ((ConstraintLayout) _$_findCachedViewById(R.id.clGetStarted)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.e0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        FragmentVitals.o(FragmentVitals.this, view2);
                    }
                });
            }
        }
        ConstraintLayout clVitals = (ConstraintLayout) _$_findCachedViewById(R.id.clVitals);
        Intrinsics.checkNotNullExpressionValue(clVitals, "clVitals");
        gone(clVitals);
        ConstraintLayout clGuest = (ConstraintLayout) _$_findCachedViewById(R.id.clGuest);
        Intrinsics.checkNotNullExpressionValue(clGuest, "clGuest");
        visible(clGuest);
        ((ConstraintLayout) _$_findCachedViewById(R.id.clGetStarted)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.e0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentVitals.o(FragmentVitals.this, view2);
            }
        });
    }

    public final void p() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String string = getString(themesUtils.isGuestUser(requireContext2) ? R.string.login : R.string.pair_device);
        Intrinsics.checkNotNullExpressionValue(string, "if(requireContext().isGu…rd2.R.string.pair_device)");
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        String string2 = getString(themesUtils.isGuestUser(requireContext3) ? R.string.please_login_to_use_this_feature : R.string.please_pair_with_your_bluetooth_device_to_use_this_feature);
        Intrinsics.checkNotNullExpressionValue(string2, "if(requireContext().isGu…vice_to_use_this_feature)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireContext, string, string2, false, 8, null);
        String string3 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(com.coveiot.an…shboard2.R.string.cancel)");
        bottomSheetDialogTwoButtons.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.g0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentVitals.q(BottomSheetDialogTwoButtons.this, view);
            }
        });
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        String string4 = getString(themesUtils.isGuestUser(requireContext4) ? R.string.login_now : R.string.pair_now);
        Intrinsics.checkNotNullExpressionValue(string4, "if(requireContext().isGu…board2.R.string.pair_now)");
        bottomSheetDialogTwoButtons.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentVitals.r(FragmentVitals.this, bottomSheetDialogTwoButtons, view);
            }
        });
        if (bottomSheetDialogTwoButtons.isShowing()) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    public final void replaceFragment(Fragment fragment) {
        getParentFragmentManager().beginTransaction().replace(R.id.vitalContainer, fragment).commit();
    }

    @Override // com.coveiot.android.leonardo.dashboard.home.adapters.MainVitalsAdapter.VitalsClickListener
    public void selectedVital(@NotNull VitalsModel vitalsModel, int i) {
        Intrinsics.checkNotNullParameter(vitalsModel, "vitalsModel");
        MainVitalsAdapter mainVitalsAdapter = this.o;
        if (mainVitalsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainVitalsAdapter");
            mainVitalsAdapter = null;
        }
        mainVitalsAdapter.setLastSelectedPosition(i);
        n().setVitalName(vitalsModel.getVitalName());
        String vitalName = vitalsModel.getVitalName();
        if (Intrinsics.areEqual(vitalName, getString(R.string.steps))) {
            replaceFragment(VitalStepsFragment.Companion.newInstance());
        } else if (Intrinsics.areEqual(vitalName, getString(R.string.heart_rate))) {
            replaceFragment(VitalHeartRateFragment.Companion.newInstance());
        } else if (Intrinsics.areEqual(vitalName, getString(R.string.energy_meter))) {
            replaceFragment(VitalEnergyMeterFragment.Companion.newInstance());
        } else if (Intrinsics.areEqual(vitalName, getString(R.string.hrv_caps))) {
            replaceFragment(VitalHRVPeriodicFragment.Companion.newInstance());
        } else if (Intrinsics.areEqual(vitalName, getString(R.string.temperature))) {
            replaceFragment(VitalTemperatureFragment.Companion.newInstance());
        } else if (Intrinsics.areEqual(vitalName, getString(R.string.spo2))) {
            replaceFragment(FragmentHeartRate.Companion.newInstance());
            if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                replaceFragment(VitalsPeriodicSpo2Fragment.Companion.newInstance());
            } else {
                replaceFragment(VitalsManualSpo2Fragment.Companion.newInstance());
            }
        } else if (Intrinsics.areEqual(vitalName, getString(R.string.sleep))) {
            replaceFragment(VitalsSleepFragment.Companion.newInstance());
        } else if (Intrinsics.areEqual(vitalName, getString(R.string.stress))) {
            if (BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
                replaceFragment(VitalsPeriodicStressFragment.Companion.newInstance());
            } else {
                replaceFragment(VitalsManualStressFragment.Companion.newInstance());
            }
        } else if (Intrinsics.areEqual(vitalName, getString(R.string.ambient_sound_level_txt))) {
            replaceFragment(FragmentAmbientSoundLevel.Companion.newInstance());
        } else if (Intrinsics.areEqual(vitalName, getString(R.string.nightly_breathing_rate))) {
            replaceFragment(VitalRespiratoryRateFragment.Companion.newInstance(this.q));
        }
    }

    public final void setCalender(@Nullable Calendar calendar) {
        this.q = calendar;
    }
}
