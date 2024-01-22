package com.coveiot.android.theme;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.coveiot.android.theme.databinding.FragmentResendOtpInfoDialogBinding;
import com.coveiot.android.theme.utils.ThemesUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ResendOtpInfoDialogFragment extends DialogFragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    public FragmentResendOtpInfoDialogBinding binding;
    @NotNull
    public String h;
    @NotNull
    public String i;
    @NotNull
    public String j;

    /* loaded from: classes7.dex */
    public static final class a extends Lambda implements Function0<Unit> {
        public static final a INSTANCE = new a();

        public a() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
        }
    }

    public ResendOtpInfoDialogFragment(@NotNull String dialogTitle, @NotNull String dialogMessage, @NotNull String specialMessage) {
        Intrinsics.checkNotNullParameter(dialogTitle, "dialogTitle");
        Intrinsics.checkNotNullParameter(dialogMessage, "dialogMessage");
        Intrinsics.checkNotNullParameter(specialMessage, "specialMessage");
        this._$_findViewCache = new LinkedHashMap();
        this.h = dialogTitle;
        this.i = dialogMessage;
        this.j = specialMessage;
    }

    public static final void c(ResendOtpInfoDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public static final void d(ResendOtpInfoDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
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

    @NotNull
    public final FragmentResendOtpInfoDialogBinding getBinding() {
        FragmentResendOtpInfoDialogBinding fragmentResendOtpInfoDialogBinding = this.binding;
        if (fragmentResendOtpInfoDialogBinding != null) {
            return fragmentResendOtpInfoDialogBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.DialogTheme);
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        setCancelable(false);
        FragmentResendOtpInfoDialogBinding inflate = FragmentResendOtpInfoDialogBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        setBinding(inflate);
        View root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        double d = getResources().getDisplayMetrics().widthPixels * 0.9d;
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setLayout((int) d, -2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        getBinding().tvResendOtpInfoDialogTitle.setText(this.h);
        TextView textView = getBinding().tvResendOtpInfoDialogMessage;
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        String str = this.i;
        String str2 = this.j;
        a aVar = a.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        textView.setText(themesUtils.styled(str, str2, -1, false, true, aVar, requireContext));
        getBinding().tvResendOtpInfoDialogMessage.setMovementMethod(LinkMovementMethod.getInstance());
        getBinding().ivResendOtpInfoDialogClose.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.b1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ResendOtpInfoDialogFragment.c(ResendOtpInfoDialogFragment.this, view2);
            }
        });
        getBinding().btnResendOtpInfoDialogRateNow.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.c1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ResendOtpInfoDialogFragment.d(ResendOtpInfoDialogFragment.this, view2);
            }
        });
    }

    public final void setBinding(@NotNull FragmentResendOtpInfoDialogBinding fragmentResendOtpInfoDialogBinding) {
        Intrinsics.checkNotNullParameter(fragmentResendOtpInfoDialogBinding, "<set-?>");
        this.binding = fragmentResendOtpInfoDialogBinding;
    }
}
