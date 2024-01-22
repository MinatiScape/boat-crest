package com.coveiot.android.qrtray.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.viewpager2.widget.ViewPager2;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.qrtray.ActivityQRTray;
import com.coveiot.android.qrtray.R;
import com.coveiot.android.qrtray.adapter.ImagePagerAdapter;
import com.coveiot.android.qrtray.databinding.FragmentQrTrayIntroBinding;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.covepreferences.SessionManager;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentQrTrayIntro extends BaseFragment {
    public FragmentQrTrayIntroBinding m;
    public int n;
    public boolean o;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final List<Integer> p = CollectionsKt__CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.drawable.qr_tray_intro1), Integer.valueOf(R.drawable.qr_tray_intro2), Integer.valueOf(R.drawable.qr_tray_intro3)});

    @DebugMetadata(c = "com.coveiot.android.qrtray.fragment.FragmentQrTrayIntro$onViewCreated$1$3$1", f = "FragmentQrTrayIntro.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentActivity activity = FragmentQrTrayIntro.this.getActivity();
                if (activity != null) {
                    activity.finish();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void n(FragmentQrTrayIntro this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        Context requireContext2 = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        hashMap.putAll(companion.getWatchDetails(requireContext2));
        companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_GET_STARTED_TAPPED.getValue(), hashMap);
        SessionManager.getInstance(this$0.requireContext()).setIsQrTrayIntroSeen(true);
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
        if (!(!((ActivityQRTray) requireActivity).getWatchAndServerQRData().getServerAppliedQrCodes().isEmpty())) {
            FragmentActivity requireActivity2 = this$0.requireActivity();
            Intrinsics.checkNotNull(requireActivity2, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
            if (!(!((ActivityQRTray) requireActivity2).getWatchAndServerQRData().getServerUnAppliedQrCodes().isEmpty())) {
                this$0.requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.qrContainer, new FragmentQrTrayUpload()).addToBackStack(new FragmentQrTrayUpload().toString()).commit();
                return;
            }
        }
        this$0.requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.qrContainer, new FragmentQrTraySaved()).addToBackStack(new FragmentQrTraySaved().toString()).commit();
    }

    public static final void o(FragmentQrTrayIntro this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getMain(), null, new a(null), 2, null);
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

    public final FragmentQrTrayIntroBinding m() {
        FragmentQrTrayIntroBinding fragmentQrTrayIntroBinding = this.m;
        if (fragmentQrTrayIntroBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentQrTrayIntroBinding;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentQrTrayIntroBinding inflate = FragmentQrTrayIntroBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.m = inflate;
        View root = m().getRoot();
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
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(requireContext, this.p);
        FragmentQrTrayIntroBinding m = m();
        m.viewPager.setAdapter(imagePagerAdapter);
        q(this.n);
        this.o = true;
        m.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.coveiot.android.qrtray.fragment.FragmentQrTrayIntro$onViewCreated$1$1
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
                boolean z;
                super.onPageSelected(i);
                z = FragmentQrTrayIntro.this.o;
                if (!z) {
                    FragmentQrTrayIntro.this.q(i);
                }
                if (i == 0) {
                    FragmentQrTrayIntro fragmentQrTrayIntro = FragmentQrTrayIntro.this;
                    String string = fragmentQrTrayIntro.getString(R.string.upload_qr_code);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.upload_qr_code)");
                    String string2 = FragmentQrTrayIntro.this.getString(R.string.upload_a_screenshot_of_the_qr_code_to_be_scanned);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.uploa…he_qr_code_to_be_scanned)");
                    fragmentQrTrayIntro.p(string, string2);
                } else if (i == 1) {
                    FragmentQrTrayIntro.this.o = false;
                    FragmentQrTrayIntro.this.q(1);
                    FragmentQrTrayIntro fragmentQrTrayIntro2 = FragmentQrTrayIntro.this;
                    String string3 = fragmentQrTrayIntro2.getString(R.string.view_qr_code_on_your_smartwatch);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.view_…_code_on_your_smartwatch)");
                    String string4 = FragmentQrTrayIntro.this.getString(R.string.qr_code_is_displayed_on_the_smartwatch_as_per_your_selection);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qr_co…ch_as_per_your_selection)");
                    fragmentQrTrayIntro2.p(string3, string4);
                } else if (i != 2) {
                } else {
                    FragmentQrTrayIntro.this.o = false;
                    FragmentQrTrayIntro fragmentQrTrayIntro3 = FragmentQrTrayIntro.this;
                    String string5 = fragmentQrTrayIntro3.getString(R.string.scan_easily);
                    Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.scan_easily)");
                    String string6 = FragmentQrTrayIntro.this.getString(R.string.use_your_smartwatch_to_scan_the_displayed_qr_code_at_metro_or_bus_stations_restaurants_payment_counters_airline_terminals_and_more);
                    Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.use_y…rline_terminals_and_more)");
                    fragmentQrTrayIntro3.p(string5, string6);
                }
            }
        });
        m.btnGetStarted.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentQrTrayIntro.n(FragmentQrTrayIntro.this, view2);
            }
        });
        ((TextView) m().toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.qr_tray));
        ((TextView) m().toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentQrTrayIntro.o(FragmentQrTrayIntro.this, view2);
            }
        });
    }

    public final void p(String str, String str2) {
        m().tvHeader.setText(str);
        m().tvInfo.setText(str2);
    }

    public final void q(int i) {
        LinearLayout linearLayout = m().dotsLayout;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.dotsLayout");
        linearLayout.removeAllViews();
        int size = this.p.size();
        for (int i2 = 0; i2 < size; i2++) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, getResources().getDimensionPixelSize(R.dimen.margin_8dp), 0);
            imageView.setLayoutParams(layoutParams);
            if (i2 == i) {
                imageView.setImageResource(R.drawable.viewpager_selected_indicator);
            } else {
                imageView.setImageResource(R.drawable.viewpager_unselected_indicator);
            }
            linearLayout.addView(imageView);
        }
    }
}
