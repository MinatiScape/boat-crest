package com.coveiot.android.theme.permissions;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.coveiot.android.theme.R;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class PermissionCheckDialog extends DialogFragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    @NotNull
    public final Context h;
    @NotNull
    public final String i;
    @NotNull
    public final OnItemClickListener j;

    /* loaded from: classes7.dex */
    public interface OnItemClickListener {
        void onCancelItemClickListener();

        void onOKayItemClickListener();
    }

    public PermissionCheckDialog(@NotNull Context mContext, @NotNull String desc, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(desc, "desc");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this._$_findViewCache = new LinkedHashMap();
        this.h = mContext;
        this.i = desc;
        this.j = onItemClickListener;
    }

    public static final void e(PermissionCheckDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.j.onOKayItemClickListener();
        this$0.dismiss();
    }

    public static final void f(PermissionCheckDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.j.onCancelItemClickListener();
        this$0.dismiss();
    }

    public static final void g(PermissionCheckDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.j.onCancelItemClickListener();
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

    public final void d(View view) {
        ((TextView) view.findViewById(R.id.tvDone)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.permissions.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PermissionCheckDialog.e(PermissionCheckDialog.this, view2);
            }
        });
        ((TextView) view.findViewById(R.id.tvCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.permissions.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PermissionCheckDialog.f(PermissionCheckDialog.this, view2);
            }
        });
        ((ImageView) view.findViewById(R.id.close_image)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.permissions.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PermissionCheckDialog.g(PermissionCheckDialog.this, view2);
            }
        });
    }

    @NotNull
    public final String getDesc() {
        return this.i;
    }

    @NotNull
    public final Context getMContext() {
        return this.h;
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.j;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.DialogTheme);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.dialog_check_permission, viewGroup, false);
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
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setLayout(-2, -2);
        }
        Dialog dialog2 = getDialog();
        if (dialog2 != null) {
            dialog2.setCancelable(false);
        }
        Dialog dialog3 = getDialog();
        if (dialog3 != null) {
            dialog3.setCanceledOnTouchOutside(false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ((TextView) _$_findCachedViewById(R.id.tv_sms)).setText(this.i);
        d(view);
    }
}
