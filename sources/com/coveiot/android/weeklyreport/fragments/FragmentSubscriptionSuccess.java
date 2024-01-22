package com.coveiot.android.weeklyreport.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogTwoButtonsOneTitle;
import com.coveiot.android.weeklyreport.R;
import com.coveiot.android.weeklyreport.WeeklyReportData;
import com.coveiot.android.weeklyreport.utils.ViewModelFactory;
import com.coveiot.android.weeklyreport.utils.WeeklyReportConstant;
import com.coveiot.android.weeklyreport.viewmodel.WeeklyReportViewModel;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class FragmentSubscriptionSuccess extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public NextScreenListener listener;
    @Nullable
    public BottomSheetDialogTwoButtonsOneTitle m;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentSubscriptionSuccess newInstance() {
            return new FragmentSubscriptionSuccess();
        }
    }

    /* loaded from: classes8.dex */
    public interface NextScreenListener {
        void nextScreen(@NotNull WeeklyReportData weeklyReportData);
    }

    public static final void o(FragmentSubscriptionSuccess this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getListener().nextScreen(new WeeklyReportData(WeeklyReportConstant.UNSUBSCRIBE.getValue(), ""));
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle = this$0.m;
        if (bottomSheetDialogTwoButtonsOneTitle != null) {
            bottomSheetDialogTwoButtonsOneTitle.dismiss();
        }
    }

    public static final void p(FragmentSubscriptionSuccess this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle = this$0.m;
        if (bottomSheetDialogTwoButtonsOneTitle != null) {
            bottomSheetDialogTwoButtonsOneTitle.dismiss();
        }
    }

    public static final void q(FragmentSubscriptionSuccess this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().finish();
    }

    public static final void r(FragmentSubscriptionSuccess this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.listener != null) {
            this$0.confirmationDialog();
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

    public final void confirmationDialog() {
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle;
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle2;
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle3 = this.m;
        if (bottomSheetDialogTwoButtonsOneTitle3 != null) {
            Boolean valueOf = bottomSheetDialogTwoButtonsOneTitle3 != null ? Boolean.valueOf(bottomSheetDialogTwoButtonsOneTitle3.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (bottomSheetDialogTwoButtonsOneTitle2 = this.m) != null) {
                bottomSheetDialogTwoButtonsOneTitle2.dismiss();
            }
            this.m = null;
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = getString(R.string.unsubscribe_confirmation);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unsubscribe_confirmation)");
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle4 = new BottomSheetDialogTwoButtonsOneTitle(requireActivity, string);
        this.m = bottomSheetDialogTwoButtonsOneTitle4;
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogTwoButtonsOneTitle4.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.fragments.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentSubscriptionSuccess.o(FragmentSubscriptionSuccess.this, view);
            }
        });
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle5 = this.m;
        if (bottomSheetDialogTwoButtonsOneTitle5 != null) {
            String string3 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
            bottomSheetDialogTwoButtonsOneTitle5.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.fragments.k
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentSubscriptionSuccess.p(FragmentSubscriptionSuccess.this, view);
                }
            });
        }
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle6 = this.m;
        Boolean valueOf2 = bottomSheetDialogTwoButtonsOneTitle6 != null ? Boolean.valueOf(bottomSheetDialogTwoButtonsOneTitle6.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue() || (bottomSheetDialogTwoButtonsOneTitle = this.m) == null) {
            return;
        }
        bottomSheetDialogTwoButtonsOneTitle.show();
    }

    @Nullable
    public final BottomSheetDialogTwoButtonsOneTitle getBottomSheetDialogTwoButtonsOneTitle() {
        return this.m;
    }

    @NotNull
    public final NextScreenListener getListener() {
        NextScreenListener nextScreenListener = this.listener;
        if (nextScreenListener != null) {
            return nextScreenListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onAttach(activity);
        if (activity instanceof NextScreenListener) {
            setListener((NextScreenListener) activity);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_subscription_success, viewGroup, false);
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
        ((TextView) _$_findCachedViewById(R.id.ok_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.fragments.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSubscriptionSuccess.q(FragmentSubscriptionSuccess.this, view2);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_unsubscribe)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.fragments.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSubscriptionSuccess.r(FragmentSubscriptionSuccess.this, view2);
            }
        });
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(WeeklyReportViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…del::class.java\n        )");
        WeeklyReportViewModel weeklyReportViewModel = (WeeklyReportViewModel) viewModel;
    }

    public final void setBottomSheetDialogTwoButtonsOneTitle(@Nullable BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle) {
        this.m = bottomSheetDialogTwoButtonsOneTitle;
    }

    public final void setListener(@NotNull NextScreenListener nextScreenListener) {
        Intrinsics.checkNotNullParameter(nextScreenListener, "<set-?>");
        this.listener = nextScreenListener;
    }
}
