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
public final class FragmentStressShare extends Fragment {
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
        public final FragmentStressShare newInstance(@NotNull ShareData shareData) {
            Intrinsics.checkNotNullParameter(shareData, "shareData");
            FragmentStressShare fragmentStressShare = new FragmentStressShare();
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppConstants.SHARE_DATA.getValue(), shareData);
            fragmentStressShare.setArguments(bundle);
            return fragmentStressShare;
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
            Serializable serializable = arguments != null ? arguments.getSerializable(AppConstants.SHARE_DATA.getValue()) : null;
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.model.ShareData");
            this.h = (ShareData) serializable;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ShareData shareData = this.h;
        if (shareData != null && shareData.getStressType() == ShareData.StressType.PERIODIC) {
            return inflater.inflate(R.layout.fragment_share_stress_periodic_new, viewGroup, false);
        }
        return inflater.inflate(R.layout.fragment_share_stress_new, viewGroup, false);
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
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ImageView iv_powered_cove = (ImageView) _$_findCachedViewById(R.id.iv_powered_cove);
        Intrinsics.checkNotNullExpressionValue(iv_powered_cove, "iv_powered_cove");
        ThemesUtils.setPoweredByLogoIcon(requireContext, iv_powered_cove, true);
        updateView();
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
        int i = R.id.stress_value;
        if (((TextView) _$_findCachedViewById(i)) != null) {
            ((TextView) _$_findCachedViewById(i)).setText(this.h.getData());
        }
        int i2 = R.id.tv_min_stress;
        if (((TextView) _$_findCachedViewById(i2)) != null) {
            try {
                ((TextView) _$_findCachedViewById(i2)).setText(String.valueOf(this.h.getStress_min()));
            } catch (Exception unused) {
                ((TextView) _$_findCachedViewById(R.id.tv_min_stress)).setText(this.h.getStress_min());
            }
        }
        int i3 = R.id.tv_max_stress;
        if (((TextView) _$_findCachedViewById(i3)) != null) {
            try {
                ((TextView) _$_findCachedViewById(i3)).setText(String.valueOf(this.h.getStress_max()));
            } catch (Exception unused2) {
                ((TextView) _$_findCachedViewById(R.id.tv_max_stress)).setText(this.h.getStress_max());
            }
        }
        if (m.equals(this.h.getGraphType(), getString(R.string.share_daily), true)) {
            ((TextView) _$_findCachedViewById(R.id.week)).setText(this.h.getDwmValue());
        } else if (m.equals(this.h.getGraphType(), getString(R.string.share_weekly), true)) {
            ((TextView) _$_findCachedViewById(R.id.week)).setText(this.h.getDwmValue());
        } else if (m.equals(this.h.getGraphType(), getString(R.string.share_monthly), true)) {
            ((TextView) _$_findCachedViewById(R.id.week)).setText(this.h.getDwmValue());
        }
        GlideUtils.loadScaledImage(getActivity(), SessionManager.getInstance(getActivity()).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.dashboard.health.fragments.FragmentStressShare$updateView$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Bitmap circleBitmap;
                Intrinsics.checkNotNullParameter(resource, "resource");
                FragmentStressShare fragmentStressShare = FragmentStressShare.this;
                int i4 = R.id.user_pic;
                if (((ImageView) fragmentStressShare._$_findCachedViewById(i4)) == null || (circleBitmap = AppUtils.getCircleBitmap(resource)) == null) {
                    return;
                }
                ((ImageView) FragmentStressShare.this._$_findCachedViewById(i4)).setImageBitmap(circleBitmap);
            }
        });
    }
}
