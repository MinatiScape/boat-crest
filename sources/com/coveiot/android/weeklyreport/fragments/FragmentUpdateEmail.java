package com.coveiot.android.weeklyreport.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.weeklyreport.R;
import com.coveiot.android.weeklyreport.WeeklyReportData;
import com.coveiot.android.weeklyreport.utils.ViewModelFactory;
import com.coveiot.android.weeklyreport.utils.WeeklyReportConstant;
import com.coveiot.android.weeklyreport.viewmodel.WeeklyReportViewModel;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class FragmentUpdateEmail extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public NextScreenListener listener;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentUpdateEmail newInstance() {
            return new FragmentUpdateEmail();
        }
    }

    /* loaded from: classes8.dex */
    public interface NextScreenListener {
        void nextScreen(@NotNull WeeklyReportData weeklyReportData);
    }

    public static final void l(FragmentUpdateEmail this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = R.id.email_edittext;
        if (StringsKt__StringsKt.trim(((EditText) this$0._$_findCachedViewById(i)).getText().toString()).toString().length() > 0) {
            if (this$0.listener != null) {
                if (AppUtils.isNetConnected(this$0.requireActivity())) {
                    this$0.getListener().nextScreen(new WeeklyReportData(WeeklyReportConstant.VERIFY.getValue(), StringsKt__StringsKt.trim(((EditText) this$0._$_findCachedViewById(i)).getText().toString()).toString()));
                    return;
                } else {
                    Toast.makeText(this$0.requireActivity(), this$0.getResources().getString(R.string.please_check_your_internet), 1).show();
                    return;
                }
            }
            return;
        }
        Toast.makeText(this$0.getContext(), this$0.getResources().getString(R.string.please_enter_email), 0).show();
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
        return inflater.inflate(R.layout.update_email_id_frag, viewGroup, false);
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
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(WeeklyReportViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…del::class.java\n        )");
        WeeklyReportViewModel weeklyReportViewModel = (WeeklyReportViewModel) viewModel;
        ((Button) _$_findCachedViewById(R.id.btn_submit)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.fragments.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentUpdateEmail.l(FragmentUpdateEmail.this, view2);
            }
        });
    }

    public final void setListener(@NotNull NextScreenListener nextScreenListener) {
        Intrinsics.checkNotNullParameter(nextScreenListener, "<set-?>");
        this.listener = nextScreenListener;
    }
}
