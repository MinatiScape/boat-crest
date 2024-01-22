package com.coveiot.android.leonardo.rateus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.fragment.app.DialogFragment;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.utils.ReviewAndRateUsConstants;
import com.coveiot.covepreferences.SessionManager;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class RateUsDialogFragment extends DialogFragment {
    public Button btn_rate_us_dialog_later;
    public Button btn_rate_us_dialog_rate_now;
    public ImageView iv_rate_us_dialog_close;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public String h = "";

    public static final void d(SessionManager sessionManager, RateUsDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (sessionManager != null) {
            sessionManager.setRateUsDialogShown(true);
        }
        if (this$0.h.equals(ReviewAndRateUsConstants.CREATE_WATCHFACE.getValue())) {
            this$0.requireActivity().finish();
        }
        this$0.dismiss();
    }

    public static final void e(RateUsDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.h.equals(ReviewAndRateUsConstants.CREATE_WATCHFACE.getValue())) {
            this$0.requireActivity().finish();
        }
        this$0.dismiss();
    }

    public static final void f(SessionManager sessionManager, RateUsDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (sessionManager != null) {
            sessionManager.setRateUsDialogShown(true);
        }
        if (this$0.h.equals(ReviewAndRateUsConstants.CREATE_WATCHFACE.getValue())) {
            this$0.requireActivity().finish();
        }
        this$0.dismiss();
        Intent intent = new Intent(this$0.requireContext(), ActivityRateUs.class);
        intent.putExtra("cv_prev_screen_name", this$0.h);
        this$0.startActivity(intent);
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
    public final Button getBtn_rate_us_dialog_later() {
        Button button = this.btn_rate_us_dialog_later;
        if (button != null) {
            return button;
        }
        Intrinsics.throwUninitializedPropertyAccessException("btn_rate_us_dialog_later");
        return null;
    }

    @NotNull
    public final Button getBtn_rate_us_dialog_rate_now() {
        Button button = this.btn_rate_us_dialog_rate_now;
        if (button != null) {
            return button;
        }
        Intrinsics.throwUninitializedPropertyAccessException("btn_rate_us_dialog_rate_now");
        return null;
    }

    @NotNull
    public final ImageView getIv_rate_us_dialog_close() {
        ImageView imageView = this.iv_rate_us_dialog_close;
        if (imageView != null) {
            return imageView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("iv_rate_us_dialog_close");
        return null;
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
        return inflater.inflate(R.layout.fragment_rate_us_dialog, viewGroup, false);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        final SessionManager sessionManager = SessionManager.getInstance(requireContext());
        View findViewById = view.findViewById(R.id.iv_rate_us_dialog_close);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById<ImageV….iv_rate_us_dialog_close)");
        setIv_rate_us_dialog_close((ImageView) findViewById);
        View findViewById2 = view.findViewById(R.id.btn_rate_us_dialog_later);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById<Button…btn_rate_us_dialog_later)");
        setBtn_rate_us_dialog_later((Button) findViewById2);
        View findViewById3 = view.findViewById(R.id.btn_rate_us_dialog_rate_now);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById<Button…_rate_us_dialog_rate_now)");
        setBtn_rate_us_dialog_rate_now((Button) findViewById3);
        getIv_rate_us_dialog_close().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.rateus.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RateUsDialogFragment.d(SessionManager.this, this, view2);
            }
        });
        getBtn_rate_us_dialog_later().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.rateus.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RateUsDialogFragment.e(RateUsDialogFragment.this, view2);
            }
        });
        getBtn_rate_us_dialog_rate_now().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.rateus.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RateUsDialogFragment.f(SessionManager.this, this, view2);
            }
        });
    }

    public final void setBtn_rate_us_dialog_later(@NotNull Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.btn_rate_us_dialog_later = button;
    }

    public final void setBtn_rate_us_dialog_rate_now(@NotNull Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.btn_rate_us_dialog_rate_now = button;
    }

    public final void setIv_rate_us_dialog_close(@NotNull ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.iv_rate_us_dialog_close = imageView;
    }
}
