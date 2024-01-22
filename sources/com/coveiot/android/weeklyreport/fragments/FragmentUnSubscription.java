package com.coveiot.android.weeklyreport.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.weeklyreport.R;
import com.coveiot.android.weeklyreport.WeeklyReportData;
import com.coveiot.android.weeklyreport.listeners.OnSuccessListener;
import com.coveiot.android.weeklyreport.utils.ViewModelFactory;
import com.coveiot.android.weeklyreport.utils.WeeklyReportConstant;
import com.coveiot.android.weeklyreport.viewmodel.WeeklyReportViewModel;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class FragmentUnSubscription extends BaseFragment implements OnSuccessListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public NextScreenListener listener;
    public WeeklyReportViewModel m;
    @Nullable
    public BottomSheetDialogImageTitleMessage n;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentUnSubscription newInstance() {
            return new FragmentUnSubscription();
        }
    }

    /* loaded from: classes8.dex */
    public interface NextScreenListener {
        void nextScreen(@NotNull WeeklyReportData weeklyReportData);
    }

    public static final void p(FragmentUnSubscription this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.unSubscribeSucessDialog();
        WeeklyReportViewModel weeklyReportViewModel = this$0.m;
        if (weeklyReportViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            weeklyReportViewModel = null;
        }
        weeklyReportViewModel.getUnSubscribeLiveData().removeObservers(this$0);
    }

    public static final void q(FragmentUnSubscription this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = R.id.spinner_unsubscribe;
        View selectedView = ((Spinner) this$0._$_findCachedViewById(i)).getSelectedView();
        Intrinsics.checkNotNull(selectedView, "null cannot be cast to non-null type android.widget.TextView");
        String obj = StringsKt__StringsKt.trim(((TextView) selectedView).getText().toString()).toString();
        if ((obj.length() > 0) && !kotlin.text.m.equals(obj, this$0.getString(R.string.please_select_reason), true)) {
            this$0.o();
            WeeklyReportViewModel weeklyReportViewModel = null;
            BaseFragment.showProgress$default(this$0, false, 1, null);
            WeeklyReportViewModel weeklyReportViewModel2 = this$0.m;
            if (weeklyReportViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                weeklyReportViewModel = weeklyReportViewModel2;
            }
            weeklyReportViewModel.unSubscribeEmail(((Spinner) this$0._$_findCachedViewById(i)).getSelectedItem().toString());
            return;
        }
        Toast.makeText(this$0.getContext(), this$0.getResources().getString(R.string.please_select_reason), 0).show();
    }

    public static final void r(FragmentUnSubscription this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((Spinner) this$0._$_findCachedViewById(R.id.spinner_unsubscribe)).performClick();
    }

    public static final void s(FragmentUnSubscription this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getListener().nextScreen(new WeeklyReportData(WeeklyReportConstant.FINISH.getValue(), ""));
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.n;
        if (bottomSheetDialogImageTitleMessage != null) {
            bottomSheetDialogImageTitleMessage.dismiss();
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

    @Nullable
    public final BottomSheetDialogImageTitleMessage getBottomSheetDialogImageTitleMessageTwoVerticalBtns() {
        return this.n;
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

    public final void o() {
        WeeklyReportViewModel weeklyReportViewModel = this.m;
        if (weeklyReportViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            weeklyReportViewModel = null;
        }
        MutableLiveData<String> unSubscribeLiveData = weeklyReportViewModel.getUnSubscribeLiveData();
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type androidx.lifecycle.LifecycleOwner");
        unSubscribeLiveData.observe((LifecycleOwner) context, new Observer() { // from class: com.coveiot.android.weeklyreport.fragments.p
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentUnSubscription.p(FragmentUnSubscription.this, (String) obj);
            }
        });
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
        return inflater.inflate(R.layout.fragment_unsubscribe_weekly_report, viewGroup, false);
    }

    @Override // com.coveiot.android.weeklyreport.listeners.OnSuccessListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        dismissProgress();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.weeklyreport.listeners.OnSuccessListener
    public void onSuccess() {
        dismissProgress();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [android.widget.ArrayAdapter, T] */
    /* JADX WARN: Type inference failed for: r6v7, types: [T, java.util.ArrayList] */
    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(WeeklyReportViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…del::class.java\n        )");
        WeeklyReportViewModel weeklyReportViewModel = (WeeklyReportViewModel) viewModel;
        this.m = weeklyReportViewModel;
        WeeklyReportViewModel weeklyReportViewModel2 = null;
        if (weeklyReportViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            weeklyReportViewModel = null;
        }
        weeklyReportViewModel.setOnSuccessListener(this);
        ((TextView) _$_findCachedViewById(R.id.unsubscribe_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.fragments.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentUnSubscription.q(FragmentUnSubscription.this, view2);
            }
        });
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        WeeklyReportViewModel weeklyReportViewModel3 = this.m;
        if (weeklyReportViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            weeklyReportViewModel2 = weeklyReportViewModel3;
        }
        objectRef.element = weeklyReportViewModel2.getUnSubscribeReason();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        FragmentActivity requireActivity = requireActivity();
        int i = R.layout.spinner_view;
        objectRef2.element = new ArrayAdapter(requireActivity, i, (List) objectRef.element);
        int i2 = R.id.spinner_unsubscribe;
        ((Spinner) _$_findCachedViewById(i2)).setAdapter((SpinnerAdapter) objectRef2.element);
        ((ArrayAdapter) objectRef2.element).setDropDownViewResource(i);
        ((LinearLayout) _$_findCachedViewById(R.id.unsubsribe_linear)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.fragments.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentUnSubscription.r(FragmentUnSubscription.this, view2);
            }
        });
        ((Spinner) _$_findCachedViewById(i2)).setOnTouchListener(new View.OnTouchListener() { // from class: com.coveiot.android.weeklyreport.fragments.FragmentUnSubscription$onViewCreated$3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(@NotNull View v, @NotNull MotionEvent event) {
                Resources resources;
                Intrinsics.checkNotNullParameter(v, "v");
                Intrinsics.checkNotNullParameter(event, "event");
                ArrayList<String> arrayList = objectRef.element;
                Context context = this.getContext();
                if (CollectionsKt___CollectionsKt.contains(arrayList, (context == null || (resources = context.getResources()) == null) ? null : resources.getString(R.string.please_select_reason)) && event.getAction() == 1) {
                    objectRef.element.remove(0);
                    objectRef2.element.notifyDataSetChanged();
                }
                return false;
            }
        });
    }

    public final void setBottomSheetDialogImageTitleMessageTwoVerticalBtns(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.n = bottomSheetDialogImageTitleMessage;
    }

    public final void setListener(@NotNull NextScreenListener nextScreenListener) {
        Intrinsics.checkNotNullParameter(nextScreenListener, "<set-?>");
        this.listener = nextScreenListener;
    }

    public final void unSubscribeSucessDialog() {
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage;
        Drawable drawable = getResources().getDrawable(R.drawable.unsubscribe_success_image);
        if (this.n == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            Intrinsics.checkNotNullExpressionValue(drawable, "drawable");
            String string = getResources().getString(R.string.success);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.success)");
            String string2 = getString(R.string.unsubscribe_success);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.unsubscribe_success)");
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = new BottomSheetDialogImageTitleMessage(requireActivity, drawable, string, string2, false);
            this.n = bottomSheetDialogImageTitleMessage2;
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogImageTitleMessage2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.fragments.m
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentUnSubscription.s(FragmentUnSubscription.this, view);
                }
            });
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.n;
        Boolean valueOf = bottomSheetDialogImageTitleMessage3 != null ? Boolean.valueOf(bottomSheetDialogImageTitleMessage3.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (!valueOf.booleanValue() && (bottomSheetDialogImageTitleMessage = this.n) != null) {
            bottomSheetDialogImageTitleMessage.show();
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = this.n;
        if (bottomSheetDialogImageTitleMessage4 != null) {
            bottomSheetDialogImageTitleMessage4.setCancelable(false);
        }
    }
}
