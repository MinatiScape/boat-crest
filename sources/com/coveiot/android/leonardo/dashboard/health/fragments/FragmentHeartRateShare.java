package com.coveiot.android.leonardo.dashboard.health.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentHeartRateShare extends Fragment {
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
        public final FragmentHeartRateShare newInstance(@NotNull ShareData shareData) {
            Intrinsics.checkNotNullParameter(shareData, "shareData");
            FragmentHeartRateShare fragmentHeartRateShare = new FragmentHeartRateShare();
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppConstants.SHARE_DATA.getValue(), shareData);
            fragmentHeartRateShare.setArguments(bundle);
            return fragmentHeartRateShare;
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
        return inflater.inflate(R.layout.fragment_share_heart_rate, viewGroup, false);
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
        if (this.h.getHeartRate() != null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = getString(R.string.hear_rate_spannable);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.hear_rate_spannable)");
            String format = String.format(locale, string, Arrays.copyOf(new Object[]{this.h.getHeartRate()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            ((TextView) _$_findCachedViewById(R.id.heart_rate_value)).setText(Html.fromHtml(format), TextView.BufferType.SPANNABLE);
        }
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        Locale locale2 = Locale.ENGLISH;
        String string2 = getString(R.string.hear_rate_spannable);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.hear_rate_spannable)");
        String min_bpm = this.h.getMin_bpm();
        Intrinsics.checkNotNull(min_bpm);
        String format2 = String.format(locale2, string2, Arrays.copyOf(new Object[]{m.replace$default(min_bpm, "bpm", "", false, 4, (Object) null)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        ((TextView) _$_findCachedViewById(R.id.min_hr_value)).setText(Html.fromHtml(format2), TextView.BufferType.SPANNABLE);
        String string3 = getString(R.string.hear_rate_spannable);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.hear_rate_spannable)");
        String max_bpm = this.h.getMax_bpm();
        Intrinsics.checkNotNull(max_bpm);
        String format3 = String.format(locale2, string3, Arrays.copyOf(new Object[]{m.replace$default(max_bpm, "bpm", "", false, 4, (Object) null)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        ((TextView) _$_findCachedViewById(R.id.max_hr_value)).setText(Html.fromHtml(format3), TextView.BufferType.SPANNABLE);
        if (m.equals(this.h.getGraphType(), getString(R.string.share_daily), true)) {
            ((TextView) _$_findCachedViewById(R.id.week)).setText(this.h.getDwmValue());
        } else if (m.equals(this.h.getGraphType(), getString(R.string.share_weekly), true)) {
            ((TextView) _$_findCachedViewById(R.id.week)).setText(this.h.getDwmValue());
        } else if (m.equals(this.h.getGraphType(), getString(R.string.share_monthly), true)) {
            ((TextView) _$_findCachedViewById(R.id.week)).setText(this.h.getDwmValue());
        }
        GlideUtils.loadScaledImage(getActivity(), SessionManager.getInstance(getActivity()).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.dashboard.health.fragments.FragmentHeartRateShare$updateView$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Bitmap circleBitmap;
                Intrinsics.checkNotNullParameter(resource, "resource");
                FragmentHeartRateShare fragmentHeartRateShare = FragmentHeartRateShare.this;
                int i = R.id.user_pic;
                if (((ImageView) fragmentHeartRateShare._$_findCachedViewById(i)) == null || (circleBitmap = AppUtils.getCircleBitmap(resource)) == null) {
                    return;
                }
                ((ImageView) FragmentHeartRateShare.this._$_findCachedViewById(i)).setImageBitmap(circleBitmap);
            }
        });
    }
}
