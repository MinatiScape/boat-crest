package com.coveiot.android.fitnesschallenges.fragments;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.fitnesschallenges.adpter.FitnessChallengeLeaderboardRankingPagingAdapter;
import com.coveiot.android.fitnesschallenges.adpter.LoaderAdapter;
import com.coveiot.android.fitnesschallenges.databinding.FragmentFCLeaderboardBinding;
import com.coveiot.android.fitnesschallenges.utils.ExtensionsKt;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail;
import com.coveiot.coveaccess.fitnesschallenge.model.GetAllParticipantsFitnessChallengeRes;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.common.net.HttpHeaders;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
/* loaded from: classes2.dex */
public final class FragmentFCLeaderboard extends BaseFragment implements FitnessChallengeLeaderboardRankingPagingAdapter.ChallengeClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public boolean n;
    @Nullable
    public FragmentFCLeaderboardBinding o;
    public FitnessChallengeListViewModel p;
    @Nullable
    public Date r;
    public FitnessChallengeLeaderboardRankingPagingAdapter s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public BuddiesChallengeDetail m = new BuddiesChallengeDetail();
    @NotNull
    public GetAllParticipantsFitnessChallengeRes q = new GetAllParticipantsFitnessChallengeRes();

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentFCLeaderboard newInstance(@NotNull BuddiesChallengeDetail buddiesChallengeDetail, boolean z) {
            Intrinsics.checkNotNullParameter(buddiesChallengeDetail, "buddiesChallengeDetail");
            FragmentFCLeaderboard fragmentFCLeaderboard = new FragmentFCLeaderboard();
            Bundle bundle = new Bundle();
            bundle.putSerializable("param1", buddiesChallengeDetail);
            bundle.putBoolean("param2", z);
            fragmentFCLeaderboard.setArguments(bundle);
            return fragmentFCLeaderboard;
        }
    }

    /* loaded from: classes2.dex */
    public static final class a extends Lambda implements Function1<PagingData<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails>, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(PagingData<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails> pagingData) {
            invoke2(pagingData);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(PagingData<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails> pagingData) {
            if (FragmentFCLeaderboard.this.isAdded()) {
                FitnessChallengeLeaderboardRankingPagingAdapter fitnessChallengeLeaderboardRankingPagingAdapter = FragmentFCLeaderboard.this.s;
                if (fitnessChallengeLeaderboardRankingPagingAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeLeaderboardRankingPagingAdapter");
                    fitnessChallengeLeaderboardRankingPagingAdapter = null;
                }
                Lifecycle lifecycle = FragmentFCLeaderboard.this.getLifecycle();
                Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
                Intrinsics.checkNotNullExpressionValue(pagingData, "pagingData");
                fitnessChallengeLeaderboardRankingPagingAdapter.submitData(lifecycle, pagingData);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class b extends Lambda implements Function1<Boolean, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean bool) {
            if (FragmentFCLeaderboard.this.isAdded()) {
                FragmentFCLeaderboard.this.dismissProgress();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class c extends Lambda implements Function1<GetAllParticipantsFitnessChallengeRes, Unit> {
        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GetAllParticipantsFitnessChallengeRes getAllParticipantsFitnessChallengeRes) {
            invoke2(getAllParticipantsFitnessChallengeRes);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(GetAllParticipantsFitnessChallengeRes it) {
            if (FragmentFCLeaderboard.this.isAdded()) {
                FragmentFCLeaderboard fragmentFCLeaderboard = FragmentFCLeaderboard.this;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                fragmentFCLeaderboard.q = it;
                GetAllParticipantsFitnessChallengeRes getAllParticipantsFitnessChallengeRes = FragmentFCLeaderboard.this.q;
                List<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails> participants = getAllParticipantsFitnessChallengeRes != null ? getAllParticipantsFitnessChallengeRes.getParticipants() : null;
                if (participants == null || participants.isEmpty()) {
                    FragmentFCLeaderboard fragmentFCLeaderboard2 = FragmentFCLeaderboard.this;
                    ConstraintLayout constraintLayout = fragmentFCLeaderboard2.t().clHeaders;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clHeaders");
                    fragmentFCLeaderboard2.gone(constraintLayout);
                } else {
                    FragmentFCLeaderboard fragmentFCLeaderboard3 = FragmentFCLeaderboard.this;
                    ConstraintLayout constraintLayout2 = fragmentFCLeaderboard3.t().clHeaders;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clHeaders");
                    fragmentFCLeaderboard3.visible(constraintLayout2);
                }
                GetAllParticipantsFitnessChallengeRes getAllParticipantsFitnessChallengeRes2 = FragmentFCLeaderboard.this.q;
                if ((getAllParticipantsFitnessChallengeRes2 != null ? getAllParticipantsFitnessChallengeRes2.getCurrentUser() : null) != null) {
                    FragmentFCLeaderboard fragmentFCLeaderboard4 = FragmentFCLeaderboard.this;
                    ConstraintLayout constraintLayout3 = fragmentFCLeaderboard4.t().clMyRank;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.clMyRank");
                    fragmentFCLeaderboard4.visible(constraintLayout3);
                } else {
                    FragmentFCLeaderboard fragmentFCLeaderboard5 = FragmentFCLeaderboard.this;
                    ConstraintLayout constraintLayout4 = fragmentFCLeaderboard5.t().clMyRank;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.clMyRank");
                    fragmentFCLeaderboard5.gone(constraintLayout4);
                }
                FragmentFCLeaderboard.this.F(it);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class d extends Lambda implements Function1<Boolean, Unit> {
        public d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean shouldShowProgress) {
            if (FragmentFCLeaderboard.this.isAdded()) {
                Intrinsics.checkNotNullExpressionValue(shouldShowProgress, "shouldShowProgress");
                if (shouldShowProgress.booleanValue()) {
                    FragmentFCLeaderboard.this.showProgress(true);
                } else {
                    FragmentFCLeaderboard.this.dismissProgress();
                }
            }
        }
    }

    public static final void A(FragmentFCLeaderboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Date date = this$0.r;
        if (date != null) {
            Intrinsics.checkNotNull(date);
            if (date.after(Calendar.getInstance().getTime())) {
                Context requireContext = this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                String string = this$0.getString(R.string.you_can_download_the_report_when_the_challenge_starts);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.you_c…hen_the_challenge_starts)");
                this$0.toast(requireContext, string);
                return;
            }
        }
        this$0.G();
    }

    public static final void C(FragmentFCLeaderboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent();
        intent.setClassName(this$0.requireContext(), "com.coveiot.leaderboard.views.activities.ActivityAchievementsHome");
        intent.putExtra("INTENT_EXTRA_BADGES", 1);
        this$0.startActivity(intent);
    }

    public static final void H(BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        bottomSheetDialogImageTitleMessage.dismiss();
    }

    public static final void I(final FragmentFCLeaderboard this$0, BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        if (AppUtils.isNetConnected(this$0.requireContext())) {
            this$0.showProgress(false);
            FitnessChallengeListViewModel fitnessChallengeListViewModel = this$0.p;
            if (fitnessChallengeListViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fitnessChallengeListViewModel = null;
            }
            Object challengeId = this$0.m.getChallengeId();
            Intrinsics.checkNotNullExpressionValue(challengeId, "buddiesChallengeDetail.challengeId");
            fitnessChallengeListViewModel.downloadLeaderboardReport(challengeId, new FitnessChallengeListViewModel.FileDownload() { // from class: com.coveiot.android.fitnesschallenges.fragments.FragmentFCLeaderboard$showDownloadPopUp$2$1
                @Override // com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel.FileDownload
                public void downloadFailure(@NotNull String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    FragmentFCLeaderboard.this.dismissProgress();
                    FragmentFCLeaderboard fragmentFCLeaderboard = FragmentFCLeaderboard.this;
                    Context requireContext = fragmentFCLeaderboard.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    String string = FragmentFCLeaderboard.this.getString(R.string.downloading_failed);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.downloading_failed)");
                    fragmentFCLeaderboard.toast(requireContext, string);
                    LogHelper.d("FragmentFCLeaderboard", "Downloading Legal doc failed");
                }

                @Override // com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel.FileDownload
                public void downloadedSuccessfully(@NotNull Response<ResponseBody> response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    String str = response.headers().get(HttpHeaders.CONTENT_DISPOSITION);
                    String substringAfter$default = str != null ? StringsKt__StringsKt.substringAfter$default(str, "filename=", (String) null, 2, (Object) null) : null;
                    if (Build.VERSION.SDK_INT >= 29) {
                        FragmentFCLeaderboard fragmentFCLeaderboard = FragmentFCLeaderboard.this;
                        ResponseBody body = response.body();
                        Intrinsics.checkNotNull(body);
                        InputStream byteStream = body.byteStream();
                        Intrinsics.checkNotNullExpressionValue(byteStream, "response.body()!!.byteStream()");
                        fragmentFCLeaderboard.D(byteStream, substringAfter$default);
                        return;
                    }
                    FragmentFCLeaderboard fragmentFCLeaderboard2 = FragmentFCLeaderboard.this;
                    ResponseBody body2 = response.body();
                    Intrinsics.checkNotNull(body2);
                    InputStream byteStream2 = body2.byteStream();
                    Intrinsics.checkNotNullExpressionValue(byteStream2, "response.body()!!.byteStream()");
                    fragmentFCLeaderboard2.saveFile(byteStream2, substringAfter$default);
                }
            });
        } else {
            this$0.showNoInternetDialog();
        }
        bottomSheetDialogImageTitleMessage.dismiss();
    }

    @JvmStatic
    @NotNull
    public static final FragmentFCLeaderboard newInstance(@NotNull BuddiesChallengeDetail buddiesChallengeDetail, boolean z) {
        return Companion.newInstance(buddiesChallengeDetail, z);
    }

    public static final void u(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void v(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void w(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void x(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void z(FragmentFCLeaderboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().onBackPressed();
    }

    public final boolean B(Intent intent) {
        List<ResolveInfo> queryIntentActivities = requireContext().getPackageManager().queryIntentActivities(intent, 65536);
        Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "packageManager.queryInte…nager.MATCH_DEFAULT_ONLY)");
        return !queryIntentActivities.isEmpty();
    }

    @RequiresApi(29)
    public final void D(InputStream inputStream, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str);
        contentValues.put("mime_type", "text/csv");
        contentValues.put("relative_path", Environment.DIRECTORY_DOWNLOADS);
        ContentResolver contentResolver = requireContext().getContentResolver();
        Uri insert = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
        if (insert != null) {
            try {
                OutputStream openOutputStream = contentResolver.openOutputStream(insert);
                Intrinsics.checkNotNull(openOutputStream);
                ByteStreamsKt.copyTo(inputStream, openOutputStream, 8192);
                dismissProgress();
                Intent intent = new Intent("android.intent.action.VIEW", insert);
                intent.addFlags(1);
                if (B(intent)) {
                    startActivity(intent);
                } else {
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    String string = getString(R.string.report_downloaded_successfully);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.report_downloaded_successfully)");
                    toast(requireContext, string);
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(openOutputStream, null);
            } catch (Exception e) {
                e.printStackTrace();
                dismissProgress();
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                String string2 = getString(R.string.exception_while_saving_file);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.exception_while_saving_file)");
                toast(requireContext2, string2);
            }
        }
    }

    public final void E(FragmentFCLeaderboardBinding fragmentFCLeaderboardBinding) {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String targetBaseUnits = this.m.getTargetBaseUnits();
        Intrinsics.checkNotNullExpressionValue(targetBaseUnits, "buddiesChallengeDetail.targetBaseUnits");
        this.s = new FitnessChallengeLeaderboardRankingPagingAdapter(requireContext, this, targetBaseUnits);
        fragmentFCLeaderboardBinding.rvParticipantList.setLayoutManager(new LinearLayoutManager(requireActivity(), 1, false));
        fragmentFCLeaderboardBinding.rvParticipantList.setHasFixedSize(true);
        RecyclerView recyclerView = fragmentFCLeaderboardBinding.rvParticipantList;
        FitnessChallengeLeaderboardRankingPagingAdapter fitnessChallengeLeaderboardRankingPagingAdapter = this.s;
        if (fitnessChallengeLeaderboardRankingPagingAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeLeaderboardRankingPagingAdapter");
            fitnessChallengeLeaderboardRankingPagingAdapter = null;
        }
        recyclerView.setAdapter(fitnessChallengeLeaderboardRankingPagingAdapter.withLoadStateFooter(new LoaderAdapter()));
    }

    public final void F(GetAllParticipantsFitnessChallengeRes getAllParticipantsFitnessChallengeRes) {
        GetAllParticipantsFitnessChallengeRes.ParticipantsDetails currentUser;
        GetAllParticipantsFitnessChallengeRes.ParticipantsDetails currentUser2;
        GetAllParticipantsFitnessChallengeRes.ParticipantsDetails currentUser3;
        GetAllParticipantsFitnessChallengeRes.ParticipantsDetails currentUser4;
        Number valueOf;
        GetAllParticipantsFitnessChallengeRes.ParticipantsDetails currentUser5;
        GetAllParticipantsFitnessChallengeRes.ParticipantsDetails currentUser6;
        Integer rank;
        t().tvMyRank.setText((getAllParticipantsFitnessChallengeRes == null || (currentUser6 = getAllParticipantsFitnessChallengeRes.getCurrentUser()) == null || (rank = currentUser6.getRank()) == null) ? "--" : String.valueOf(rank));
        t().tvMyName.setText((getAllParticipantsFitnessChallengeRes == null || (currentUser5 = getAllParticipantsFitnessChallengeRes.getCurrentUser()) == null || (r1 = currentUser5.getName()) == null) ? "" : "");
        boolean z = false;
        String str = null;
        if (Intrinsics.areEqual(this.m.getTargetBaseUnits(), FitnessChallengeConstants.METERS)) {
            TextView textView = t().tvMyCalories;
            StringBuilder sb = new StringBuilder();
            BuddiesChallengeDetail buddiesChallengeDetail = this.m;
            if (buddiesChallengeDetail == null || (valueOf = buddiesChallengeDetail.getTargetAchieved()) == null) {
                valueOf = Double.valueOf(0.0d);
            }
            sb.append(ExtensionsKt.formatToTwoDecimalPlace((float) (valueOf.doubleValue() / 1000.0d)));
            sb.append(' ');
            sb.append(requireContext().getString(R.string.km_unit));
            textView.setText(sb.toString());
        } else {
            TextView textView2 = t().tvMyCalories;
            StringBuilder sb2 = new StringBuilder();
            BuddiesChallengeDetail buddiesChallengeDetail2 = this.m;
            Integer targetAchieved = buddiesChallengeDetail2 != null ? buddiesChallengeDetail2.getTargetAchieved() : null;
            sb2.append(targetAchieved == null ? 0 : targetAchieved.intValue());
            sb2.append(' ');
            sb2.append(requireContext().getString(R.string.cal_unit));
            textView2.setText(sb2.toString());
        }
        if (((getAllParticipantsFitnessChallengeRes == null || (currentUser4 = getAllParticipantsFitnessChallengeRes.getCurrentUser()) == null) ? null : currentUser4.getTargetAchievedDate()) != null) {
            TextView textView3 = t().tvRankAchieveDate;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvRankAchieveDate");
            visible(textView3);
            TextView textView4 = t().tvRankAchieveDate;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(requireContext().getString(R.string.goal_achieved_on));
            sb3.append('\n');
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            String targetAchievedDate = (getAllParticipantsFitnessChallengeRes == null || (currentUser3 = getAllParticipantsFitnessChallengeRes.getCurrentUser()) == null) ? null : currentUser3.getTargetAchievedDate();
            Intrinsics.checkNotNull(targetAchievedDate);
            sb3.append(themesUtils.formatDateWithSuffix(targetAchievedDate));
            textView4.setText(sb3.toString());
        } else {
            TextView textView5 = t().tvRankAchieveDate;
            Intrinsics.checkNotNullExpressionValue(textView5, "binding.tvRankAchieveDate");
            gone(textView5);
        }
        String dpUrl = (getAllParticipantsFitnessChallengeRes == null || (currentUser2 = getAllParticipantsFitnessChallengeRes.getCurrentUser()) == null) ? null : currentUser2.getDpUrl();
        if ((dpUrl == null || dpUrl.length() == 0) ? true : true) {
            return;
        }
        ImageView imageView = t().ivMyProfilePic;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivMyProfilePic");
        if (getAllParticipantsFitnessChallengeRes != null && (currentUser = getAllParticipantsFitnessChallengeRes.getCurrentUser()) != null) {
            str = currentUser.getDpUrl();
        }
        setImage(imageView, str);
    }

    public final void G() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Drawable drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.failure_image_img);
        Intrinsics.checkNotNull(drawable);
        String string = getString(R.string.download_leaderboard_info);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.download_leaderboard_info)");
        final BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(requireContext, drawable, "", string, false);
        bottomSheetDialogImageTitleMessage.showCrossIcon();
        bottomSheetDialogImageTitleMessage.showBigIcon();
        bottomSheetDialogImageTitleMessage.setCrossButton(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.k0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentFCLeaderboard.H(BottomSheetDialogImageTitleMessage.this, view);
            }
        });
        String string2 = getString(R.string.proceed);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.proceed)");
        bottomSheetDialogImageTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.j0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentFCLeaderboard.I(FragmentFCLeaderboard.this, bottomSheetDialogImageTitleMessage, view);
            }
        });
        if (bottomSheetDialogImageTitleMessage.isShowing()) {
            return;
        }
        bottomSheetDialogImageTitleMessage.show();
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

    @Override // com.coveiot.android.fitnesschallenges.adpter.FitnessChallengeLeaderboardRankingPagingAdapter.ChallengeClickListener
    public void getTopParticipants(@NotNull ArrayList<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails> participants) {
        Object obj;
        Intrinsics.checkNotNullParameter(participants, "participants");
        Intrinsics.checkNotNullExpressionValue(getString(Intrinsics.areEqual(this.m.getTargetBaseUnits(), FitnessChallengeConstants.METERS) ? R.string.km_unit : R.string.cal_unit), "if (buddiesChallengeDeta…String(R.string.cal_unit)");
        if (participants.size() <= 0 || participants.get(0) == null) {
            return;
        }
        t().challengeRankData.tvRankOneUserName.setText(participants.get(0).getName());
        if (Intrinsics.areEqual(this.m.getTargetBaseUnits(), FitnessChallengeConstants.METERS)) {
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.goal_value);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.goal_value)");
            Number targetAchieved = participants.get(0).getTargetAchieved();
            if (targetAchieved == null) {
                targetAchieved = Double.valueOf(0.0d);
            }
            String str = ExtensionsKt.formatToTwoDecimalPlace((float) (targetAchieved.doubleValue() / 1000.0d)).toString();
            TextView textView = t().challengeRankData.tvRankOneUserCalories;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.challengeRankData.tvRankOneUserCalories");
            themesUtils.makeTextViewBoldForFitnessChallengesLeaderboard(requireContext, string, str, "", textView);
        } else {
            ThemesUtils themesUtils2 = ThemesUtils.INSTANCE;
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            String string2 = getString(R.string.goal_value);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.goal_value)");
            Integer targetAchieved2 = participants.get(0).getTargetAchieved();
            String valueOf = String.valueOf(targetAchieved2 == null ? 0 : targetAchieved2.intValue());
            TextView textView2 = t().challengeRankData.tvRankOneUserCalories;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.challengeRankData.tvRankOneUserCalories");
            themesUtils2.makeTextViewBoldForFitnessChallengesLeaderboard(requireContext2, string2, valueOf, "", textView2);
        }
        if (participants.get(0).getTargetAchievedDate() != null) {
            TextView textView3 = t().challengeRankData.tvRankOneUserAchievedDate;
            textView3.setText(requireContext().getString(R.string.goal_achieved_on) + '\n' + ThemesUtils.INSTANCE.formatDateWithSuffix(participants.get(0).getTargetAchievedDate().toString()));
        }
        if (participants.get(0).getDpUrl() != null) {
            ImageView imageView = t().challengeRankData.ivRankOneUserProfilePic;
            Intrinsics.checkNotNullExpressionValue(imageView, "binding.challengeRankData.ivRankOneUserProfilePic");
            setImage(imageView, participants.get(0).getDpUrl());
        }
        TextView textView4 = t().challengeRankData.tvRankOne;
        Integer rank = participants.get(0).getRank();
        if (rank == null) {
            rank = "--";
        }
        textView4.setText(String.valueOf(rank));
        if (participants.size() <= 1 || participants.get(1) == null) {
            return;
        }
        t().challengeRankData.tvRankTwoUserName.setText(participants.get(1).getName());
        if (Intrinsics.areEqual(this.m.getTargetBaseUnits(), FitnessChallengeConstants.METERS)) {
            ThemesUtils themesUtils3 = ThemesUtils.INSTANCE;
            Context requireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            String string3 = getString(R.string.goal_value);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.goal_value)");
            Number targetAchieved3 = participants.get(1).getTargetAchieved();
            if (targetAchieved3 == null) {
                targetAchieved3 = Double.valueOf(0.0d);
            }
            double doubleValue = targetAchieved3.doubleValue();
            obj = FitnessChallengeConstants.METERS;
            String str2 = ExtensionsKt.formatToTwoDecimalPlace((float) (doubleValue / 1000.0d)).toString();
            TextView textView5 = t().challengeRankData.tvRankTwoUserCalories;
            Intrinsics.checkNotNullExpressionValue(textView5, "binding.challengeRankData.tvRankTwoUserCalories");
            themesUtils3.makeTextViewBoldForFitnessChallengesLeaderboard(requireContext3, string3, str2, "", textView5);
        } else {
            obj = FitnessChallengeConstants.METERS;
            ThemesUtils themesUtils4 = ThemesUtils.INSTANCE;
            Context requireContext4 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
            String string4 = getString(R.string.goal_value);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.goal_value)");
            Integer targetAchieved4 = participants.get(1).getTargetAchieved();
            String valueOf2 = String.valueOf(targetAchieved4 == null ? 0 : targetAchieved4.intValue());
            TextView textView6 = t().challengeRankData.tvRankTwoUserCalories;
            Intrinsics.checkNotNullExpressionValue(textView6, "binding.challengeRankData.tvRankTwoUserCalories");
            themesUtils4.makeTextViewBoldForFitnessChallengesLeaderboard(requireContext4, string4, valueOf2, "", textView6);
        }
        if (participants.get(1).getTargetAchievedDate() != null) {
            TextView textView7 = t().challengeRankData.tvRankTwoUserAchievedDate;
            textView7.setText(requireContext().getString(R.string.goal_achieved_on) + ' ' + ThemesUtils.INSTANCE.formatDateWithSuffix(participants.get(1).getTargetAchievedDate().toString()));
        }
        if (participants.get(1).getDpUrl() != null) {
            ImageView imageView2 = t().challengeRankData.ivRankTwoUserProfilePic;
            Intrinsics.checkNotNullExpressionValue(imageView2, "binding.challengeRankData.ivRankTwoUserProfilePic");
            setImage(imageView2, participants.get(1).getDpUrl());
        }
        TextView textView8 = t().challengeRankData.tvRankTwo;
        Integer rank2 = participants.get(1).getRank();
        if (rank2 == null) {
            rank2 = "--";
        }
        textView8.setText(String.valueOf(rank2));
        if (participants.size() <= 2 || participants.get(2) == null) {
            return;
        }
        t().challengeRankData.tvRankThreeUserName.setText(participants.get(2).getName());
        if (Intrinsics.areEqual(this.m.getTargetBaseUnits(), obj)) {
            ThemesUtils themesUtils5 = ThemesUtils.INSTANCE;
            Context requireContext5 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
            String string5 = getString(R.string.goal_value);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.goal_value)");
            Number targetAchieved5 = participants.get(2).getTargetAchieved();
            if (targetAchieved5 == null) {
                targetAchieved5 = Double.valueOf(0.0d);
            }
            String str3 = ExtensionsKt.formatToTwoDecimalPlace((float) (targetAchieved5.doubleValue() / 1000.0d)).toString();
            TextView textView9 = t().challengeRankData.tvRankThreeUserCalories;
            Intrinsics.checkNotNullExpressionValue(textView9, "binding.challengeRankData.tvRankThreeUserCalories");
            themesUtils5.makeTextViewBoldForFitnessChallengesLeaderboard(requireContext5, string5, str3, "", textView9);
        } else {
            ThemesUtils themesUtils6 = ThemesUtils.INSTANCE;
            Context requireContext6 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
            String string6 = getString(R.string.goal_value);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.goal_value)");
            Integer targetAchieved6 = participants.get(2).getTargetAchieved();
            String valueOf3 = String.valueOf(targetAchieved6 == null ? 0 : targetAchieved6.intValue());
            TextView textView10 = t().challengeRankData.tvRankThreeUserCalories;
            Intrinsics.checkNotNullExpressionValue(textView10, "binding.challengeRankData.tvRankThreeUserCalories");
            themesUtils6.makeTextViewBoldForFitnessChallengesLeaderboard(requireContext6, string6, valueOf3, "", textView10);
        }
        if (participants.get(2).getTargetAchievedDate() != null) {
            TextView textView11 = t().challengeRankData.tvRankThreeUserAchievedDate;
            textView11.setText(requireContext().getString(R.string.goal_achieved_on) + ' ' + ThemesUtils.INSTANCE.formatDateWithSuffix(participants.get(2).getTargetAchievedDate().toString()));
        }
        if (participants.get(2).getDpUrl() != null) {
            ImageView imageView3 = t().challengeRankData.ivRankThreeUserProfilePic;
            Intrinsics.checkNotNullExpressionValue(imageView3, "binding.challengeRankDat…ivRankThreeUserProfilePic");
            setImage(imageView3, participants.get(2).getDpUrl());
        }
        TextView textView12 = t().challengeRankData.tvRankThree;
        Integer rank3 = participants.get(2).getRank();
        textView12.setText(String.valueOf(rank3 != null ? rank3 : "--"));
    }

    public final void initObservers() {
        FitnessChallengeListViewModel fitnessChallengeListViewModel = this.p;
        FitnessChallengeListViewModel fitnessChallengeListViewModel2 = null;
        if (fitnessChallengeListViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fitnessChallengeListViewModel = null;
        }
        MutableLiveData<PagingData<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails>> leaderboardRankingFitnessChallengeList = fitnessChallengeListViewModel.getLeaderboardRankingFitnessChallengeList();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final a aVar = new a();
        leaderboardRankingFitnessChallengeList.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.m0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentFCLeaderboard.v(Function1.this, obj);
            }
        });
        FitnessChallengeListViewModel fitnessChallengeListViewModel3 = this.p;
        if (fitnessChallengeListViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fitnessChallengeListViewModel3 = null;
        }
        MutableLiveData<Boolean> showEmptyLayout = fitnessChallengeListViewModel3.getShowEmptyLayout();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        final b bVar = new b();
        showEmptyLayout.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.n0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentFCLeaderboard.w(Function1.this, obj);
            }
        });
        FitnessChallengeListViewModel fitnessChallengeListViewModel4 = this.p;
        if (fitnessChallengeListViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fitnessChallengeListViewModel4 = null;
        }
        MutableLiveData<GetAllParticipantsFitnessChallengeRes> userDetailMutableLiveData = fitnessChallengeListViewModel4.getUserDetailMutableLiveData();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        final c cVar = new c();
        userDetailMutableLiveData.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.l0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentFCLeaderboard.x(Function1.this, obj);
            }
        });
        FitnessChallengeListViewModel fitnessChallengeListViewModel5 = this.p;
        if (fitnessChallengeListViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fitnessChallengeListViewModel2 = fitnessChallengeListViewModel5;
        }
        MutableLiveData<Boolean> shouldShowProgress = fitnessChallengeListViewModel2.getShouldShowProgress();
        LifecycleOwner viewLifecycleOwner4 = getViewLifecycleOwner();
        final d dVar = new d();
        shouldShowProgress.observe(viewLifecycleOwner4, new Observer() { // from class: com.coveiot.android.fitnesschallenges.fragments.o0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentFCLeaderboard.u(Function1.this, obj);
            }
        });
    }

    @Override // com.coveiot.android.fitnesschallenges.adpter.FitnessChallengeLeaderboardRankingPagingAdapter.ChallengeClickListener
    public void isDataLoaded(boolean z) {
        if (isAdded() && z) {
            dismissProgress();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable("param1");
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail");
            this.m = (BuddiesChallengeDetail) serializable;
            this.n = arguments.getBoolean("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.o = FragmentFCLeaderboardBinding.inflate(inflater, viewGroup, false);
        View root = t().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0154  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onViewCreated(@org.jetbrains.annotations.NotNull android.view.View r5, @org.jetbrains.annotations.Nullable android.os.Bundle r6) {
        /*
            Method dump skipped, instructions count: 360
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.fitnesschallenges.fragments.FragmentFCLeaderboard.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    @NotNull
    public final Intent openFile(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        Context requireContext = requireContext();
        Intent intent = new Intent("android.intent.action.VIEW", FileProvider.getUriForFile(requireContext, requireContext().getPackageName() + ".provider", file));
        intent.addFlags(1);
        return intent;
    }

    public final void saveFile(@NotNull InputStream inputStream, @Nullable String str) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), str);
            Log.d("fileCheck", "File Path: " + file.getAbsolutePath());
            if (file.exists()) {
                boolean delete = file.delete();
                Log.d("fileCheck", "File Deletion Status: " + delete);
            } else {
                Log.d("fileCheck", "File Writing Status: Failed");
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read < 0) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.flush();
            dismissProgress();
            if (B(openFile(file))) {
                startActivity(openFile(file));
            } else {
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                String string = getString(R.string.report_downloaded_successfully);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.report_downloaded_successfully)");
                toast(requireContext, string);
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, null);
            CloseableKt.closeFinally(inputStream, null);
        } catch (Exception unused) {
            dismissProgress();
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            String string2 = getString(R.string.exception_while_saving_file);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.exception_while_saving_file)");
            toast(requireContext2, string2);
        }
    }

    public final void setImage(@NotNull ImageView imageView, @Nullable String str) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        int i = R.drawable.default_avatar;
        Glide.with(imageView).m30load(str).override(300, 300).error(i).placeholder(i).diskCacheStrategy(DiskCacheStrategy.ALL).circleCrop().into(imageView);
    }

    public final FragmentFCLeaderboardBinding t() {
        FragmentFCLeaderboardBinding fragmentFCLeaderboardBinding = this.o;
        Intrinsics.checkNotNull(fragmentFCLeaderboardBinding);
        return fragmentFCLeaderboardBinding;
    }

    public final void y(FragmentFCLeaderboardBinding fragmentFCLeaderboardBinding) {
        ((TextView) fragmentFCLeaderboardBinding.toolbar.findViewById(com.coveiot.android.fitnessbuddies.R.id.toolbar_title)).setText(this.m.getName());
        ((TextView) fragmentFCLeaderboardBinding.toolbar.findViewById(com.coveiot.android.fitnessbuddies.R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.h0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentFCLeaderboard.z(FragmentFCLeaderboard.this, view);
            }
        });
        if (this.m.getCreator() != null) {
            Boolean creator = this.m.getCreator();
            Intrinsics.checkNotNullExpressionValue(creator, "buddiesChallengeDetail.creator");
            if (creator.booleanValue()) {
                ImageView imageView = (ImageView) fragmentFCLeaderboardBinding.toolbar.findViewById(R.id.ivButton);
                Date date = this.r;
                if (date != null) {
                    Intrinsics.checkNotNull(date);
                    if (date.after(Calendar.getInstance().getTime())) {
                        imageView.setImageResource(R.drawable.ic_circular_download_disabled);
                        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.i0
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                FragmentFCLeaderboard.A(FragmentFCLeaderboard.this, view);
                            }
                        });
                    }
                }
                imageView.setImageResource(R.drawable.ic_circular_download);
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.i0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentFCLeaderboard.A(FragmentFCLeaderboard.this, view);
                    }
                });
            }
        }
    }
}
