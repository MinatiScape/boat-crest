package com.coveiot.android.navigation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.ViewModelFactory;
import com.coveiot.android.navigation.databinding.FragmentDisclaimerBinding;
import com.coveiot.android.navigation.interfaces.NavigationFTUScreenChangeListener;
import com.coveiot.android.navigation.models.NavigationDisclaimerData;
import com.coveiot.android.navigation.models.ScreenType;
import com.coveiot.android.navigation.utils.NavigationConstants;
import com.coveiot.android.navigation.viewModels.ActivityNavigationFTUViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.coveaccess.onboarding.model.AcceptLegalTermsReq;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentDisclaimer extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public FragmentDisclaimerBinding m;
    public ActivityNavigationFTUViewModel n;
    @Nullable
    public NavigationFTUScreenChangeListener o;
    public boolean p;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentDisclaimer newInstance(boolean z) {
            FragmentDisclaimer fragmentDisclaimer = new FragmentDisclaimer();
            Bundle bundle = new Bundle();
            bundle.putBoolean("isToView", z);
            fragmentDisclaimer.setArguments(bundle);
            return fragmentDisclaimer;
        }
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function2<Boolean, String, Unit> {
        public a() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
            invoke(bool.booleanValue(), str);
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z, @NotNull String msg) {
            Intrinsics.checkNotNullParameter(msg, "msg");
            if (z) {
                FragmentDisclaimer.this.dismissProgress();
                NavigationFTUScreenChangeListener navigationFTUScreenChangeListener = FragmentDisclaimer.this.o;
                if (navigationFTUScreenChangeListener != null) {
                    navigationFTUScreenChangeListener.onFragmentChanged(ScreenType.NAVIGATION_MAIN);
                    return;
                }
                return;
            }
            FragmentDisclaimer.this.dismissProgress();
            FragmentDisclaimer fragmentDisclaimer = FragmentDisclaimer.this;
            String string = fragmentDisclaimer.requireContext().getString(R.string.some_thing_went_wrong);
            Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri…ng.some_thing_went_wrong)");
            fragmentDisclaimer.p(string);
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentDisclaimer newInstance(boolean z) {
        return Companion.newInstance(z);
    }

    public static final void o(FragmentDisclaimer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0.requireContext())) {
            if (this$0.p) {
                NavigationFTUScreenChangeListener navigationFTUScreenChangeListener = this$0.o;
                if (navigationFTUScreenChangeListener != null) {
                    navigationFTUScreenChangeListener.onFragmentChanged(ScreenType.NAVIGATION_MAIN);
                    return;
                }
                return;
            }
            this$0.l();
            return;
        }
        Toast.makeText(this$0.requireContext(), this$0.requireContext().getText(R.string.no_internet), 1).show();
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

    public final void l() {
        ActivityNavigationFTUViewModel activityNavigationFTUViewModel = null;
        BaseFragment.showProgress$default(this, false, 1, null);
        Type type = new TypeToken<NavigationDisclaimerData>() { // from class: com.coveiot.android.navigation.fragments.FragmentDisclaimer$acceptDisclaimer$navigationDisclaimerDataType$1
        }.getType();
        Object fromJson = new Gson().fromJson(SessionManager.getInstance(requireContext()).getNavigationDiscliamerData(), type);
        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(navigati…gationDisclaimerDataType)");
        AcceptLegalTermsReq acceptLegalTermsReq = new AcceptLegalTermsReq();
        acceptLegalTermsReq.type = NavigationConstants.MAP_NAV_DISCLAIMER;
        acceptLegalTermsReq.version = ((NavigationDisclaimerData) fromJson).getVersion();
        if (isAdded()) {
            ActivityNavigationFTUViewModel activityNavigationFTUViewModel2 = this.n;
            if (activityNavigationFTUViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                activityNavigationFTUViewModel = activityNavigationFTUViewModel2;
            }
            activityNavigationFTUViewModel.acceptDisclaimer(acceptLegalTermsReq, new a());
        }
    }

    public final FragmentDisclaimerBinding m() {
        FragmentDisclaimerBinding fragmentDisclaimerBinding = this.m;
        Intrinsics.checkNotNull(fragmentDisclaimerBinding);
        return fragmentDisclaimerBinding;
    }

    public final void n() {
        if (isAdded()) {
            e.e(LifecycleOwnerKt.getLifecycleScope(this), null, null, new FragmentDisclaimer$loadDisclaimer$1(this, null), 3, null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof NavigationFTUScreenChangeListener) {
            this.o = (NavigationFTUScreenChangeListener) context;
            return;
        }
        throw new ClassCastException(context + " must implement FragmentChangeListener");
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentDisclaimerBinding.inflate(inflater, viewGroup, false);
        return m().getRoot();
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
        this.n = (ActivityNavigationFTUViewModel) new ViewModelProvider(this, new ViewModelFactory(requireContext)).get(ActivityNavigationFTUViewModel.class);
        Bundle arguments = getArguments();
        Boolean valueOf = arguments != null ? Boolean.valueOf(arguments.getBoolean("isToView")) : null;
        Intrinsics.checkNotNull(valueOf);
        this.p = valueOf.booleanValue();
        BaseFragment.showProgress$default(this, false, 1, null);
        n();
        if (this.p) {
            m().btnAccept.setText(getString(R.string.exit));
        }
        m().btnAccept.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.fragments.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentDisclaimer.o(FragmentDisclaimer.this, view2);
            }
        });
    }

    public final void p(String str) {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(requireContext, str);
        String string = getString(com.coveiot.android.theme.R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.android.theme.R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.navigation.fragments.FragmentDisclaimer$showErrorDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                this.requireActivity().finish();
            }
        });
        if (bottomSheetDialogOneButtonOneTitle.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.show();
    }
}
