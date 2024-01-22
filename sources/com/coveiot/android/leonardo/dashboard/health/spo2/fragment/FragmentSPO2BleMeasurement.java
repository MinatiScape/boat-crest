package com.coveiot.android.leonardo.dashboard.health.spo2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2Result;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.Spo2DataResponse;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.Spo2DeviceType;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.Spo2ResponseType;
import com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel.FragmentSPO2BleMeasurementViewModel;
import com.coveiot.android.leonardo.dashboard.home.adapters.RemotePagerAdapter;
import com.coveiot.android.leonardo.utils.PagerContainer;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.compundview.AnimatedDotsView;
import com.coveiot.utils.utility.LogHelper;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentSPO2BleMeasurement extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public FragmentSPO2BleMeasurementViewModel n;
    @Nullable
    public FragmentSPO2Result.OnLoadSPO2ResultListener q;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String m = "FragmentSPO2BleMeasurement";
    @NotNull
    public final int[] o = {R.string.spo2_bluetooth_instruction_text_1};
    @NotNull
    public final int[] p = {R.drawable.ic_spo2_bluetooth_instruction_1};
    @NotNull
    public final Observer<Spo2DataResponse> r = new Observer() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.n
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            FragmentSPO2BleMeasurement.t(FragmentSPO2BleMeasurement.this, (Spo2DataResponse) obj);
        }
    };
    @NotNull
    public final Observer<String> s = new Observer() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.o
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            FragmentSPO2BleMeasurement.v(FragmentSPO2BleMeasurement.this, (String) obj);
        }
    };

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentSPO2BleMeasurement newInstance() {
            return new FragmentSPO2BleMeasurement();
        }
    }

    /* loaded from: classes3.dex */
    public interface OnLoadSPO2BleMeasurementListener {
        void loadBluetoothMeasurementFragment();

        void showFailureDialog(@NotNull String str, @NotNull View.OnClickListener onClickListener);
    }

    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Spo2ResponseType.values().length];
            try {
                iArr[Spo2ResponseType.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Spo2ResponseType.FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void r(FragmentSPO2BleMeasurement this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        activity.onBackPressed();
    }

    public static final void s(FragmentSPO2BleMeasurement this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        activity.onBackPressed();
    }

    public static final void t(final FragmentSPO2BleMeasurement this$0, Spo2DataResponse spo2DataResponse) {
        FragmentSPO2Result.OnLoadSPO2ResultListener onLoadSPO2ResultListener;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = WhenMappings.$EnumSwitchMapping$0[spo2DataResponse.getSpo2ResponseType().ordinal()];
        if (i != 1) {
            if (i == 2 && (onLoadSPO2ResultListener = this$0.q) != null) {
                String string = this$0.getString(R.string.measurement_failed);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.measurement_failed)");
                onLoadSPO2ResultListener.showFailureDialog(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.m
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentSPO2BleMeasurement.u(FragmentSPO2BleMeasurement.this, view);
                    }
                });
                return;
            }
            return;
        }
        FragmentSPO2Result.OnLoadSPO2ResultListener onLoadSPO2ResultListener2 = this$0.q;
        if (onLoadSPO2ResultListener2 != null) {
            onLoadSPO2ResultListener2.loadBluetoothResultFragment(Spo2DeviceType.BLUETOOTH, spo2DataResponse.getSpo2Value());
        }
        FragmentSPO2BleMeasurementViewModel fragmentSPO2BleMeasurementViewModel = this$0.n;
        if (fragmentSPO2BleMeasurementViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSPO2BleMeasurementViewModel = null;
        }
        fragmentSPO2BleMeasurementViewModel.stopSpo2Reading();
    }

    public static final void u(FragmentSPO2BleMeasurement this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentSPO2BleMeasurementViewModel fragmentSPO2BleMeasurementViewModel = this$0.n;
        if (fragmentSPO2BleMeasurementViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSPO2BleMeasurementViewModel = null;
        }
        fragmentSPO2BleMeasurementViewModel.stopSpo2Reading();
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    public static final void v(final FragmentSPO2BleMeasurement this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (str != null && kotlin.text.m.equals(str, FragmentSPO2BleMeasurementViewModel.TIMERSTOPPED, true)) {
            ((AnimatedDotsView) this$0._$_findCachedViewById(R.id.measureProgressDots)).stopAnimation();
            FragmentSPO2BleMeasurementViewModel fragmentSPO2BleMeasurementViewModel = this$0.n;
            if (fragmentSPO2BleMeasurementViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentSPO2BleMeasurementViewModel = null;
            }
            fragmentSPO2BleMeasurementViewModel.stopSpo2Reading();
            FragmentSPO2Result.OnLoadSPO2ResultListener onLoadSPO2ResultListener = this$0.q;
            if (onLoadSPO2ResultListener != null) {
                String string = this$0.getString(R.string.measurement_failed);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.measurement_failed)");
                onLoadSPO2ResultListener.showFailureDialog(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.j
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentSPO2BleMeasurement.w(FragmentSPO2BleMeasurement.this, view);
                    }
                });
            }
        } else if (Intrinsics.areEqual(str, FragmentSPO2BleMeasurementViewModel.TIMER_STOPPED_WITH_VALUE)) {
        } else {
            ((TextView) this$0._$_findCachedViewById(R.id.measure_count_down_timer_tv)).setText(str);
        }
    }

    public static final void w(FragmentSPO2BleMeasurement this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
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

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        LogHelper.d(this.m, "onCreate: called");
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        LogHelper.d(this.m, "onCreateView: called");
        return inflater.inflate(R.layout.fragment_spo2_bluetooth_measurement, viewGroup, false);
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
        LogHelper.d(this.m, "onViewCreated: called");
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setVisibility(0);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSPO2BleMeasurement.r(FragmentSPO2BleMeasurement.this, view2);
            }
        });
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.save)).setVisibility(0);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.save)).setText(getString(R.string.close));
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.measuring_title));
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        this.n = (FragmentSPO2BleMeasurementViewModel) ViewModelProviders.of(this, new ViewModelFactory(context)).get(FragmentSPO2BleMeasurementViewModel.class);
        q();
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.save)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSPO2BleMeasurement.s(FragmentSPO2BleMeasurement.this, view2);
            }
        });
        FragmentSPO2BleMeasurementViewModel fragmentSPO2BleMeasurementViewModel = this.n;
        FragmentSPO2BleMeasurementViewModel fragmentSPO2BleMeasurementViewModel2 = null;
        if (fragmentSPO2BleMeasurementViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSPO2BleMeasurementViewModel = null;
        }
        fragmentSPO2BleMeasurementViewModel.getTimerText().observe(this, this.s);
        ((AnimatedDotsView) _$_findCachedViewById(R.id.measureProgressDots)).startAnimation();
        FragmentSPO2BleMeasurementViewModel fragmentSPO2BleMeasurementViewModel3 = this.n;
        if (fragmentSPO2BleMeasurementViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSPO2BleMeasurementViewModel3 = null;
        }
        fragmentSPO2BleMeasurementViewModel3.startTimer();
        FragmentSPO2BleMeasurementViewModel fragmentSPO2BleMeasurementViewModel4 = this.n;
        if (fragmentSPO2BleMeasurementViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSPO2BleMeasurementViewModel4 = null;
        }
        fragmentSPO2BleMeasurementViewModel4.getSpo2ResponseLiveData().observe(this, this.r);
        FragmentSPO2BleMeasurementViewModel fragmentSPO2BleMeasurementViewModel5 = this.n;
        if (fragmentSPO2BleMeasurementViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentSPO2BleMeasurementViewModel2 = fragmentSPO2BleMeasurementViewModel5;
        }
        fragmentSPO2BleMeasurementViewModel2.startMeasureSpo2();
    }

    public final void q() {
        PagerContainer pagerContainer = (PagerContainer) _$_findCachedViewById(R.id.viewpager_spo2_bluetooth_connect_instruction);
        Intrinsics.checkNotNull(pagerContainer, "null cannot be cast to non-null type com.coveiot.android.leonardo.utils.PagerContainer");
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        pagerContainer.setAdapter(new RemotePagerAdapter(childFragmentManager, activity, this.o, this.p));
        pagerContainer.setAnimationEnabled(true);
        pagerContainer.setFadeEnabled(true);
        pagerContainer.setFadeFactor(0.6f);
        int i = R.id.circlePageIndicator_spo2_bluetooth_measure_instruction;
        ((CirclePageIndicator) _$_findCachedViewById(i)).setViewPager(pagerContainer);
        ((CirclePageIndicator) _$_findCachedViewById(i)).setVisibility(0);
    }

    public final void setListener(@NotNull FragmentSPO2Result.OnLoadSPO2ResultListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.q = listener;
    }
}
