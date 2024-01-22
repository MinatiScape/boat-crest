package com.coveiot.android.leonardo.dashboard.socialshare.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentSocialShareCardBinding;
import com.coveiot.android.leonardo.dashboard.socialshare.adapters.SocialShareGridviewAdapter;
import com.coveiot.android.leonardo.dashboard.socialshare.model.ShareData;
import com.coveiot.android.leonardo.dashboard.socialshare.viewmodel.DashboardSocialShareViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.utils.utility.ShareScreen;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FragmentSocialShareCard extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public FragmentSocialShareCardBinding m;
    public DashboardSocialShareViewModel p;
    @Nullable
    public SocialShareGridviewAdapter q;
    @Nullable
    public Bitmap r;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public List<ShareData> n = new ArrayList();
    @NotNull
    public final String o = "FragmentSocialShareCard";

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentSocialShareCard newInstance() {
            return new FragmentSocialShareCard();
        }
    }

    /* loaded from: classes4.dex */
    public static final class a extends Lambda implements Function1<List<ShareData>, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<ShareData> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<ShareData> it) {
            FragmentSocialShareCard fragmentSocialShareCard = FragmentSocialShareCard.this;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            fragmentSocialShareCard.setShareDataList(it);
            if (FragmentSocialShareCard.this.m != null) {
                FragmentSocialShareCard.this.r();
                FragmentSocialShareCard.this.n().recyclerViewSs.setLayoutManager(new GridLayoutManager(FragmentSocialShareCard.this.requireContext(), 2));
                FragmentSocialShareCard fragmentSocialShareCard2 = FragmentSocialShareCard.this;
                List<ShareData> shareDataList = fragmentSocialShareCard2.getShareDataList();
                Context requireContext = FragmentSocialShareCard.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                fragmentSocialShareCard2.q = new SocialShareGridviewAdapter(shareDataList, requireContext);
                FragmentSocialShareCard.this.n().recyclerViewSs.setAdapter(FragmentSocialShareCard.this.q);
                FragmentSocialShareCard.this.n().fsshareconstraintlayout.setVisibility(0);
            }
            FragmentSocialShareCard.this.dismissProgress();
        }
    }

    public static final void o(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void p(final FragmentSocialShareCard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Handler handler = new Handler();
        BaseFragment.showProgress$default(this$0, false, 1, null);
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.socialshare.fragments.c
            @Override // java.lang.Runnable
            public final void run() {
                FragmentSocialShareCard.q(FragmentSocialShareCard.this);
            }
        }, 500L);
    }

    public static final void q(FragmentSocialShareCard this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissProgress();
        Bitmap saveScreen = this$0.saveScreen();
        this$0.r = saveScreen;
        ShareScreen.shareScreenCommom(saveScreen, this$0.requireContext());
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
    public final List<ShareData> getShareDataList() {
        return this.n;
    }

    @NotNull
    public final String getTAG() {
        return this.o;
    }

    public final FragmentSocialShareCardBinding n() {
        FragmentSocialShareCardBinding fragmentSocialShareCardBinding = this.m;
        Intrinsics.checkNotNull(fragmentSocialShareCardBinding);
        return fragmentSocialShareCardBinding;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentSocialShareCardBinding.inflate(inflater, viewGroup, false);
        return n().getRoot();
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
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ImageView imageView = n().ivsocialSharePoweredCove;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivsocialSharePoweredCove");
        ThemesUtils.setPoweredByLogoIcon(requireContext, imageView, true);
        n().fsshareconstraintlayout.setVisibility(4);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        DashboardSocialShareViewModel dashboardSocialShareViewModel = (DashboardSocialShareViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireActivity)).get(DashboardSocialShareViewModel.class);
        this.p = dashboardSocialShareViewModel;
        DashboardSocialShareViewModel dashboardSocialShareViewModel2 = null;
        if (dashboardSocialShareViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dashboardSocialShareViewModel = null;
        }
        dashboardSocialShareViewModel.setLifecycleOwner(this);
        BaseFragment.showProgress$default(this, false, 1, null);
        DashboardSocialShareViewModel dashboardSocialShareViewModel3 = this.p;
        if (dashboardSocialShareViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dashboardSocialShareViewModel3 = null;
        }
        dashboardSocialShareViewModel3.fetchSocialShareData();
        DashboardSocialShareViewModel dashboardSocialShareViewModel4 = this.p;
        if (dashboardSocialShareViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            dashboardSocialShareViewModel2 = dashboardSocialShareViewModel4;
        }
        MutableLiveData<List<ShareData>> liveDataSocialShare = dashboardSocialShareViewModel2.getLiveDataSocialShare();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final a aVar = new a();
        liveDataSocialShare.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.socialshare.fragments.b
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentSocialShareCard.o(Function1.this, obj);
            }
        });
        ((TextView) requireActivity().findViewById(R.id.share_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.socialshare.fragments.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentSocialShareCard.p(FragmentSocialShareCard.this, view2);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0207  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void r() {
        /*
            Method dump skipped, instructions count: 836
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.socialshare.fragments.FragmentSocialShareCard.r():void");
    }

    @Nullable
    public final Bitmap saveScreen() {
        int height = n().fssharescrollview.getChildAt(0).getHeight();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 30) {
            Display display = requireContext().getDisplay();
            if (display != null) {
                display.getRealMetrics(displayMetrics);
            }
        } else {
            requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        n().fssharescrollview.measure(View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, 1073741824), height);
        n().fssharescrollview.layout(0, 0, displayMetrics.widthPixels, height);
        Bitmap createBitmap = Bitmap.createBitmap(n().fssharescrollview.getMeasuredWidth(), height, Bitmap.Config.ARGB_8888);
        n().fssharescrollview.draw(new Canvas(createBitmap));
        Bitmap.createScaledBitmap(createBitmap, displayMetrics.widthPixels, height, true);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(requireContext().getFilesDir().getAbsolutePath().toString(), "my_screen.jpg"));
            createBitmap.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return createBitmap;
    }

    public final void setShareDataList(@NotNull List<ShareData> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.n = list;
    }
}
