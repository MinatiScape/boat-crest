package com.coveiot.android.activitymodes.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.models.ActivityShareData;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.utils.utility.ShareScreen;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentBestActivityShare extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public Bitmap bitmap;
    public boolean o;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ActivityShareData m = new ActivityShareData();
    @NotNull
    public final SimpleDateFormat n = new SimpleDateFormat("dd MMMM hh:mm a", Locale.ENGLISH);

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentBestActivityShare newInstance(@NotNull ActivityShareData activityShareData) {
            Intrinsics.checkNotNullParameter(activityShareData, "activityShareData");
            FragmentBestActivityShare fragmentBestActivityShare = new FragmentBestActivityShare();
            Bundle bundle = new Bundle();
            bundle.putSerializable("share_data", activityShareData);
            fragmentBestActivityShare.setArguments(bundle);
            return fragmentBestActivityShare;
        }
    }

    public static final void n(final FragmentBestActivityShare this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Handler handler = new Handler();
        BaseFragment.showProgress$default(this$0, false, 1, null);
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.activitymodes.fragments.s
            @Override // java.lang.Runnable
            public final void run() {
                FragmentBestActivityShare.o(FragmentBestActivityShare.this);
            }
        }, 500L);
    }

    public static final void o(FragmentBestActivityShare this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissProgress();
        Bitmap saveScreen = ShareScreen.saveScreen((ConstraintLayout) this$0._$_findCachedViewById(R.id.root_layout), this$0.requireContext());
        Intrinsics.checkNotNullExpressionValue(saveScreen, "saveScreen(root_layout, requireContext())");
        this$0.setBitmap(saveScreen);
        ShareScreen.shareScreenCommom(this$0.getBitmap(), this$0.requireContext());
    }

    public static final void p() {
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

    @NotNull
    public final ActivityShareData getActivityShareData() {
        return this.m;
    }

    @NotNull
    public final Bitmap getBitmap() {
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            return bitmap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bitmap");
        return null;
    }

    @NotNull
    public final SimpleDateFormat getSdf2() {
        return this.n;
    }

    public final boolean isUnitInMile() {
        return this.o;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Serializable serializable = requireArguments().getSerializable("share_data");
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.activitymodes.models.ActivityShareData");
            this.m = (ActivityShareData) serializable;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_share_best_activity, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
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
        ((TextView) requireActivity().findViewById(R.id.share_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentBestActivityShare.n(FragmentBestActivityShare.this, view2);
            }
        });
    }

    public final void setActivityShareData(@NotNull ActivityShareData activityShareData) {
        Intrinsics.checkNotNullParameter(activityShareData, "<set-?>");
        this.m = activityShareData;
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmap = bitmap;
    }

    public final void setUnitInMile(boolean z) {
        this.o = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:66:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateView() {
        /*
            Method dump skipped, instructions count: 608
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.fragments.FragmentBestActivityShare.updateView():void");
    }
}
