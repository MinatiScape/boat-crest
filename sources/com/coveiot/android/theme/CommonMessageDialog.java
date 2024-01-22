package com.coveiot.android.theme;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.fragment.app.DialogFragment;
import com.coveiot.android.theme.databinding.CommonErrorMessageDialogBinding;
import com.coveiot.android.theme.databinding.CommonMessageDialogBinding;
import com.coveiot.android.theme.databinding.CommonMessageDialogWithoutDrawableBinding;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class CommonMessageDialog extends DialogFragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    @NotNull
    public final Context h;
    @NotNull
    public final String i;
    public final boolean j;
    public final boolean k;
    public Object l;

    public CommonMessageDialog(@NotNull Context mContext, @NotNull String message, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(message, "message");
        this._$_findViewCache = new LinkedHashMap();
        this.h = mContext;
        this.i = message;
        this.j = z;
        this.k = z2;
    }

    public static final void d(CommonMessageDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public static final void e(CommonMessageDialog this$0, View view) {
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

    public final Object c() {
        Object obj = this.l;
        if (obj == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return Unit.INSTANCE;
        }
        return obj;
    }

    @NotNull
    public final Context getMContext() {
        return this.h;
    }

    @NotNull
    public final String getMessage() {
        return this.i;
    }

    public final boolean isError() {
        return this.j;
    }

    public final boolean isFromFitnessChallenge() {
        return this.k;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.DialogThemeFitnessBuddies);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        if (!this.j) {
            if (!this.k) {
                CommonMessageDialogBinding inflate = CommonMessageDialogBinding.inflate(inflater, viewGroup, false);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
                this.l = inflate;
                Object c = c();
                Intrinsics.checkNotNull(c, "null cannot be cast to non-null type com.coveiot.android.theme.databinding.CommonMessageDialogBinding");
                return ((CommonMessageDialogBinding) c).getRoot();
            }
            CommonMessageDialogWithoutDrawableBinding inflate2 = CommonMessageDialogWithoutDrawableBinding.inflate(inflater, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(inflater, container,false)");
            this.l = inflate2;
            Object c2 = c();
            Intrinsics.checkNotNull(c2, "null cannot be cast to non-null type com.coveiot.android.theme.databinding.CommonMessageDialogWithoutDrawableBinding");
            return ((CommonMessageDialogWithoutDrawableBinding) c2).getRoot();
        }
        CommonErrorMessageDialogBinding inflate3 = CommonErrorMessageDialogBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate3, "inflate(inflater, container, false)");
        this.l = inflate3;
        Object c3 = c();
        Intrinsics.checkNotNull(c3, "null cannot be cast to non-null type com.coveiot.android.theme.databinding.CommonErrorMessageDialogBinding");
        return ((CommonErrorMessageDialogBinding) c3).getRoot();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        Window window2;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && (window2 = dialog.getWindow()) != null) {
            window2.setLayout(-1, -2);
        }
        Dialog dialog2 = getDialog();
        if (dialog2 != null && (window = dialog2.getWindow()) != null) {
            window.setGravity(80);
        }
        Dialog dialog3 = getDialog();
        if (dialog3 != null) {
            dialog3.setCancelable(false);
        }
        Dialog dialog4 = getDialog();
        if (dialog4 != null) {
            dialog4.setCanceledOnTouchOutside(false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (!this.j) {
            if (this.k) {
                Object c = c();
                Intrinsics.checkNotNull(c, "null cannot be cast to non-null type com.coveiot.android.theme.databinding.CommonMessageDialogWithoutDrawableBinding");
                ((CommonMessageDialogWithoutDrawableBinding) c).tvMessage.setText(this.i);
                return;
            }
            Object c2 = c();
            Intrinsics.checkNotNull(c2, "null cannot be cast to non-null type com.coveiot.android.theme.databinding.CommonMessageDialogBinding");
            ((CommonMessageDialogBinding) c2).tvMessage.setText(this.i);
            Object c3 = c();
            Intrinsics.checkNotNull(c3, "null cannot be cast to non-null type com.coveiot.android.theme.databinding.CommonMessageDialogBinding");
            ((CommonMessageDialogBinding) c3).ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.t
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    CommonMessageDialog.d(CommonMessageDialog.this, view2);
                }
            });
            return;
        }
        Object c4 = c();
        Intrinsics.checkNotNull(c4, "null cannot be cast to non-null type com.coveiot.android.theme.databinding.CommonErrorMessageDialogBinding");
        ((CommonErrorMessageDialogBinding) c4).tvMessage.setText(this.i);
        Object c5 = c();
        Intrinsics.checkNotNull(c5, "null cannot be cast to non-null type com.coveiot.android.theme.databinding.CommonErrorMessageDialogBinding");
        ((CommonErrorMessageDialogBinding) c5).ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CommonMessageDialog.e(CommonMessageDialog.this, view2);
            }
        });
    }

    public /* synthetic */ CommonMessageDialog(Context context, String str, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2);
    }
}
