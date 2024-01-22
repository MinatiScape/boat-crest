package com.coveiot.android.leonardo.dashboard.health.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentHrvShare extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ShareData h = new ShareData();

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentHrvShare newInstance(@NotNull ShareData shareData) {
            Intrinsics.checkNotNullParameter(shareData, "shareData");
            FragmentHrvShare fragmentHrvShare = new FragmentHrvShare();
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppConstants.SHARE_DATA.getValue(), shareData);
            fragmentHrvShare.setArguments(bundle);
            return fragmentHrvShare;
        }
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
    public final ShareData getShareData() {
        return this.h;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            Serializable serializable = arguments.getSerializable(AppConstants.SHARE_DATA.getValue());
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.model.ShareData");
            this.h = (ShareData) serializable;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_hrv_share_new, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        updateView();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ImageView iv_powered_cove = (ImageView) _$_findCachedViewById(R.id.iv_powered_cove);
        Intrinsics.checkNotNullExpressionValue(iv_powered_cove, "iv_powered_cove");
        ThemesUtils.setPoweredByLogoIcon(requireContext, iv_powered_cove, true);
        String string = getString(R.string.medical_disclaimer_info);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.medical_disclaimer_info)");
        SpannableString spannableString = new SpannableString(string);
        Context context = getContext();
        Integer valueOf = context != null ? Integer.valueOf(context.getColor(R.color.color_b3b3b3)) : null;
        Intrinsics.checkNotNull(valueOf);
        spannableString.setSpan(new ForegroundColorSpan(valueOf.intValue()), 0, 12, 18);
        ((TextView) _$_findCachedViewById(R.id.disclaimer_info)).setText(spannableString);
    }

    public final void setShareData(@NotNull ShareData shareData) {
        Intrinsics.checkNotNullParameter(shareData, "<set-?>");
        this.h = shareData;
    }

    public final void updateView() {
        ((TextView) _$_findCachedViewById(R.id.user_name)).setText(SessionManager.getInstance(getContext()).getUserDetails().getName());
        ((TextView) _$_findCachedViewById(R.id.hrv_value)).setText(this.h.getData());
        if (m.equals(this.h.getGraphType(), getString(R.string.share_daily), true)) {
            ((TextView) _$_findCachedViewById(R.id.week)).setText(this.h.getDwmValue());
        } else if (m.equals(this.h.getGraphType(), getString(R.string.share_weekly), true)) {
            ((TextView) _$_findCachedViewById(R.id.week)).setText(this.h.getDwmValue());
        } else if (m.equals(this.h.getGraphType(), getString(R.string.share_monthly), true)) {
            ((TextView) _$_findCachedViewById(R.id.week)).setText(this.h.getDwmValue());
        }
        GlideUtils.loadScaledImage(getActivity(), SessionManager.getInstance(getActivity()).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.dashboard.health.fragments.FragmentHrvShare$updateView$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Bitmap circleBitmap;
                Intrinsics.checkNotNullParameter(resource, "resource");
                FragmentHrvShare fragmentHrvShare = FragmentHrvShare.this;
                int i = R.id.user_pic;
                if (((ImageView) fragmentHrvShare._$_findCachedViewById(i)) == null || (circleBitmap = AppUtils.getCircleBitmap(resource)) == null) {
                    return;
                }
                ((ImageView) FragmentHrvShare.this._$_findCachedViewById(i)).setImageBitmap(circleBitmap);
            }
        });
        if (m.equals$default(this.h.getDataType(), "Last Measured", false, 2, null)) {
            ((TextView) _$_findCachedViewById(R.id.hrv_title)).setText(getString(R.string.last_measured_value));
        } else if (m.equals$default(this.h.getDataType(), "AVG", false, 2, null)) {
            ((TextView) _$_findCachedViewById(R.id.hrv_title)).setText(getString(R.string.avg_hrv_value));
        }
    }
}
