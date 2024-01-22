package com.coveiot.android.leonardo.rateus;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import androidx.fragment.app.DialogFragment;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class PlayStoreReviewDialogFragment extends DialogFragment {
    public Button btn_play_store_dialog_later;
    public Button btn_play_store_dialog_rate_now;
    public ImageView iv_play_store_dialog_close;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public String h = "";

    public static final void f(PlayStoreReviewDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.e(this$0.h, "cross");
        this$0.dismiss();
    }

    public static final void g(PlayStoreReviewDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.e(this$0.h, "later");
        this$0.i();
    }

    public static final void h(PlayStoreReviewDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.e(this$0.h, "rate_now");
        PayUtils payUtils = PayUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        payUtils.redirectToPlaystore(requireContext);
        this$0.dismiss();
    }

    public static final void j(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, PlayStoreReviewDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.dismiss();
        this$0.requireActivity().finish();
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

    /* JADX WARN: Removed duplicated region for block: B:11:0x0010 A[Catch: Exception -> 0x000b, TryCatch #0 {Exception -> 0x000b, blocks: (B:3:0x0002, B:11:0x0010, B:12:0x0016), top: B:16:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e(java.lang.String r3, java.lang.String r4) {
        /*
            r2 = this;
            if (r3 == 0) goto Ld
            int r0 = r3.length()     // Catch: java.lang.Exception -> Lb
            if (r0 != 0) goto L9
            goto Ld
        L9:
            r0 = 0
            goto Le
        Lb:
            r3 = move-exception
            goto L4b
        Ld:
            r0 = 1
        Le:
            if (r0 == 0) goto L16
            com.coveiot.android.theme.FirebaseEventParams$ScreenName r3 = com.coveiot.android.theme.FirebaseEventParams.ScreenName.PROFILE     // Catch: java.lang.Exception -> Lb
            java.lang.String r3 = r3.getValue()     // Catch: java.lang.Exception -> Lb
        L16:
            com.coveiot.analytics.AnalyticsLog r0 = new com.coveiot.analytics.AnalyticsLog     // Catch: java.lang.Exception -> Lb
            r0.<init>()     // Catch: java.lang.Exception -> Lb
            com.coveiot.android.theme.FirebaseEventParams$EventName r1 = com.coveiot.android.theme.FirebaseEventParams.EventName.CV_RATE_APP     // Catch: java.lang.Exception -> Lb
            java.lang.String r1 = r1.getValue()     // Catch: java.lang.Exception -> Lb
            r0.setEventName(r1)     // Catch: java.lang.Exception -> Lb
            r0.setCVPrevScreenName(r3)     // Catch: java.lang.Exception -> Lb
            com.coveiot.android.theme.FirebaseEventParams$ScreenName r3 = com.coveiot.android.theme.FirebaseEventParams.ScreenName.RATE_US     // Catch: java.lang.Exception -> Lb
            java.lang.String r3 = r3.getValue()     // Catch: java.lang.Exception -> Lb
            r0.setCVScreenName(r3)     // Catch: java.lang.Exception -> Lb
            java.util.HashMap r3 = new java.util.HashMap     // Catch: java.lang.Exception -> Lb
            r3.<init>()     // Catch: java.lang.Exception -> Lb
            com.coveiot.android.theme.FirebaseEventParams$Description r1 = com.coveiot.android.theme.FirebaseEventParams.Description.CV_value     // Catch: java.lang.Exception -> Lb
            java.lang.String r1 = r1.getValue()     // Catch: java.lang.Exception -> Lb
            r3.put(r1, r4)     // Catch: java.lang.Exception -> Lb
            r0.setMapData(r3)     // Catch: java.lang.Exception -> Lb
            com.coveiot.analytics.CoveAnalyticsManager$Companion r3 = com.coveiot.analytics.CoveAnalyticsManager.Companion     // Catch: java.lang.Exception -> Lb
            com.coveiot.analytics.CoveAnalyticsManager r3 = r3.getInstance()     // Catch: java.lang.Exception -> Lb
            r3.logEvent(r0)     // Catch: java.lang.Exception -> Lb
            goto L4e
        L4b:
            r3.printStackTrace()
        L4e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.rateus.PlayStoreReviewDialogFragment.e(java.lang.String, java.lang.String):void");
    }

    @NotNull
    public final Button getBtn_play_store_dialog_later() {
        Button button = this.btn_play_store_dialog_later;
        if (button != null) {
            return button;
        }
        Intrinsics.throwUninitializedPropertyAccessException("btn_play_store_dialog_later");
        return null;
    }

    @NotNull
    public final Button getBtn_play_store_dialog_rate_now() {
        Button button = this.btn_play_store_dialog_rate_now;
        if (button != null) {
            return button;
        }
        Intrinsics.throwUninitializedPropertyAccessException("btn_play_store_dialog_rate_now");
        return null;
    }

    @NotNull
    public final ImageView getIv_play_store_dialog_close() {
        ImageView imageView = this.iv_play_store_dialog_close;
        if (imageView != null) {
            return imageView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("iv_play_store_dialog_close");
        return null;
    }

    public final void i() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getResources().getString(R.string.rating_submitted);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.rating_submitted)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(requireContext, string);
        String string2 = getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …    R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.rateus.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayStoreReviewDialogFragment.j(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.DialogTheme);
        if (getArguments() != null) {
            String string = requireArguments().getString("cv_prev_screen_name", "");
            Intrinsics.checkNotNullExpressionValue(string, "requireArguments().getSt…\"cv_prev_screen_name\",\"\")");
            this.h = string;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        setCancelable(false);
        return inflater.inflate(R.layout.fragment_play_store_review_dialog, viewGroup, false);
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
        View findViewById = view.findViewById(R.id.iv_play_store_dialog_close);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById<ImageV…_play_store_dialog_close)");
        setIv_play_store_dialog_close((ImageView) findViewById);
        View findViewById2 = view.findViewById(R.id.btn_play_store_dialog_later);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById<Button…_play_store_dialog_later)");
        setBtn_play_store_dialog_later((Button) findViewById2);
        View findViewById3 = view.findViewById(R.id.btn_play_store_dialog_rate_now);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById<Button…ay_store_dialog_rate_now)");
        setBtn_play_store_dialog_rate_now((Button) findViewById3);
        getIv_play_store_dialog_close().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.rateus.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PlayStoreReviewDialogFragment.f(PlayStoreReviewDialogFragment.this, view2);
            }
        });
        getBtn_play_store_dialog_later().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.rateus.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PlayStoreReviewDialogFragment.g(PlayStoreReviewDialogFragment.this, view2);
            }
        });
        getBtn_play_store_dialog_rate_now().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.rateus.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PlayStoreReviewDialogFragment.h(PlayStoreReviewDialogFragment.this, view2);
            }
        });
    }

    public final void setBtn_play_store_dialog_later(@NotNull Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.btn_play_store_dialog_later = button;
    }

    public final void setBtn_play_store_dialog_rate_now(@NotNull Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.btn_play_store_dialog_rate_now = button;
    }

    public final void setIv_play_store_dialog_close(@NotNull ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.iv_play_store_dialog_close = imageView;
    }
}
