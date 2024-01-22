package com.coveiot.android.leonardo.dashboard.utility.fragments;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.coveiot.android.boat.R;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class SyncTroubleshootFragment extends DialogFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String h = "SyncTroubleshootFragment";
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getTAG() {
            return SyncTroubleshootFragment.h;
        }

        @JvmStatic
        @NotNull
        public final SyncTroubleshootFragment newInstance(@Nullable String str, @Nullable String str2) {
            SyncTroubleshootFragment syncTroubleshootFragment = new SyncTroubleshootFragment();
            Bundle bundle = new Bundle();
            bundle.putString("param1", str);
            bundle.putString("param2", str2);
            syncTroubleshootFragment.setArguments(bundle);
            return syncTroubleshootFragment;
        }
    }

    public static final void b(SyncTroubleshootFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    @JvmStatic
    @NotNull
    public static final SyncTroubleshootFragment newInstance(@Nullable String str, @Nullable String str2) {
        return Companion.newInstance(str, str2);
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

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.getString("param1");
            arguments.getString("param2");
        }
        setStyle(1, R.style.DialogTheme);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Window window;
        Window window2;
        Window window3;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        if (getDialog() != null) {
            Dialog dialog = getDialog();
            if ((dialog != null ? dialog.getWindow() : null) != null) {
                Dialog dialog2 = getDialog();
                if (dialog2 != null && (window3 = dialog2.getWindow()) != null) {
                    window3.setBackgroundDrawable(new ColorDrawable(0));
                }
                Dialog dialog3 = getDialog();
                if (dialog3 != null && (window2 = dialog3.getWindow()) != null) {
                    window2.requestFeature(1);
                }
                Dialog dialog4 = getDialog();
                if (dialog4 != null && (window = dialog4.getWindow()) != null) {
                    window.setSoftInputMode(48);
                }
            }
        }
        return inflater.inflate(R.layout.fragment_sync_troubleshoot, viewGroup, false);
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
        if (dialog != null) {
            dialog.setCancelable(true);
        }
        Dialog dialog2 = getDialog();
        if (dialog2 == null || (window = dialog2.getWindow()) == null) {
            return;
        }
        window.setLayout(-1, -2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.coveiot.android.leonardo.dashboard.utility.fragments.SyncTroubleshootFragment$onViewCreated$clickableSpan$1
            @Override // android.text.style.ClickableSpan
            public void onClick(@NotNull View view2) {
                Intrinsics.checkNotNullParameter(view2, "view");
                SyncTroubleshootFragment.this.dismiss();
                AppUtils.openBluetoothSettings(SyncTroubleshootFragment.this.requireContext());
            }
        };
        String string = getString(R.string.bt_toogle_ijnstr1);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bt_toogle_ijnstr1)");
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(clickableSpan, StringsKt__StringsKt.indexOf$default((CharSequence) string, "Bluetooth", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) string, "Bluetooth", 0, false, 6, (Object) null) + 9, 34);
        int i = R.id.bt_instruct1;
        ((TextView) _$_findCachedViewById(i)).setText(spannableString, TextView.BufferType.SPANNABLE);
        ((TextView) _$_findCachedViewById(i)).setMovementMethod(LinkMovementMethod.getInstance());
        setCancelable(true);
        ((Button) _$_findCachedViewById(R.id.btn_settings)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.utility.fragments.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SyncTroubleshootFragment.b(SyncTroubleshootFragment.this, view2);
            }
        });
    }
}
