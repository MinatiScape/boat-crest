package com.coveiot.android.fitnesschallenges.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.fitnesschallenges.ChallengeShareData;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.fitnesschallenges.databinding.FragmentShareChallengeBinding;
import com.coveiot.android.fitnesschallenges.model.FitnessChallengeShareModel;
import com.coveiot.android.fitnesschallenges.utils.ExtensionsKt;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeCleverTapConstants;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeGoalType;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.CircularImageView;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ShareScreen;
import com.google.gson.Gson;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentShareChallenge extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public Bitmap bitmap;
    @Nullable
    public FragmentShareChallengeBinding m;
    @Nullable
    public Uri o;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ChallengeShareData n = new ChallengeShareData();

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ FragmentShareChallenge newInstance$default(Companion companion, ChallengeShareData challengeShareData, Uri uri, int i, Object obj) {
            if ((i & 2) != 0) {
                uri = null;
            }
            return companion.newInstance(challengeShareData, uri);
        }

        @JvmStatic
        @NotNull
        public final FragmentShareChallenge newInstance(@NotNull ChallengeShareData challengeShareData, @Nullable Uri uri) {
            Intrinsics.checkNotNullParameter(challengeShareData, "challengeShareData");
            FragmentShareChallenge fragmentShareChallenge = new FragmentShareChallenge();
            Bundle bundle = new Bundle();
            bundle.putSerializable(FitnessChallengeConstants.Companion.getSHARE_CHALLENGE_DATA(), challengeShareData);
            bundle.putParcelable("PROFILE_URI", uri);
            fragmentShareChallenge.setArguments(bundle);
            return fragmentShareChallenge;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FitnessChallengeGoalType.values().length];
            try {
                iArr[FitnessChallengeGoalType.CALORIE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FitnessChallengeGoalType.DISTANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentShareChallenge newInstance(@NotNull ChallengeShareData challengeShareData, @Nullable Uri uri) {
        return Companion.newInstance(challengeShareData, uri);
    }

    public static final void u(FragmentShareChallenge this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isAdded()) {
            this$0.requireActivity().finish();
        }
    }

    public static final void v(final FragmentShareChallenge this$0, final FragmentShareChallengeBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Handler handler = new Handler();
        BaseFragment.showProgress$default(this$0, false, 1, null);
        Button shareBtn = this_apply.shareBtn;
        Intrinsics.checkNotNullExpressionValue(shareBtn, "shareBtn");
        this$0.gone(shareBtn);
        ImageView ivClose = this_apply.ivClose;
        Intrinsics.checkNotNullExpressionValue(ivClose, "ivClose");
        this$0.gone(ivClose);
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.fitnesschallenges.fragments.f1
            @Override // java.lang.Runnable
            public final void run() {
                FragmentShareChallenge.w(FragmentShareChallenge.this, this_apply);
            }
        }, 500L);
        this$0.t();
    }

    public static final void w(FragmentShareChallenge this$0, FragmentShareChallengeBinding this_apply) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this$0.dismissProgress();
        Bitmap saveScreen = ShareScreen.saveScreen(this_apply.shareFitnessChallengeCard, this$0.getContext());
        Intrinsics.checkNotNullExpressionValue(saveScreen, "saveScreen(shareFitnessChallengeCard, context)");
        this$0.setBitmap(saveScreen);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(com.crrepa.r.a.d);
        intent.putExtra("android.intent.extra.STREAM", this$0.s(this$0.getBitmap()));
        ChallengeShareData challengeShareData = this$0.n;
        if (challengeShareData != null && challengeShareData.getChallengeId() != null && this$0.n.getChallengeType() != null) {
            FitnessChallengeShareModel fitnessChallengeShareModel = new FitnessChallengeShareModel();
            fitnessChallengeShareModel.setChallengeId(this$0.n.getChallengeId());
            fitnessChallengeShareModel.setChallengeType(this$0.n.getChallengeType());
            Gson gson = new Gson();
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            String json = gson.toJson(fitnessChallengeShareModel);
            Intrinsics.checkNotNullExpressionValue(json, "gson.toJson(fitnessChallengeShareModel)");
            String encode = themesUtils.encode(json);
            if (kotlin.text.m.equals("prod", "qa", true)) {
                str = "https://nav.qa.coveiot.com/borrelly/challenge/join?_d=" + encode;
            } else if (kotlin.text.m.equals("prod", "stage", true)) {
                str = "https://nav.stage.coveiot.com/borrelly/challenge/join?_d=" + encode;
            } else {
                str = "https://nav.coveiot.com/borrelly/challenge/join?_d=" + encode;
            }
            intent.putExtra("android.intent.extra.TEXT", "Join Me in the boAt Fitness Challenge! 🔥\nReady to push your fitness limits? Lets compete! \n\nTap to join!\n " + str);
        }
        this$0.startActivity(Intent.createChooser(intent, "Share using"));
        Button shareBtn = this_apply.shareBtn;
        Intrinsics.checkNotNullExpressionValue(shareBtn, "shareBtn");
        this$0.visible(shareBtn);
        ImageView ivClose = this_apply.ivClose;
        Intrinsics.checkNotNullExpressionValue(ivClose, "ivClose");
        this$0.visible(ivClose);
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
    public final Bitmap getBitmap() {
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            return bitmap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bitmap");
        return null;
    }

    @NotNull
    public final ChallengeShareData getChallengeShareData() {
        return this.n;
    }

    @Nullable
    public final Uri getProfileUri() {
        return this.o;
    }

    public final String n(String str, String str2) {
        Locale locale = Locale.ENGLISH;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy", locale);
        Date parse = simpleDateFormat.parse(str);
        Date parse2 = simpleDateFormat.parse(str2);
        String startDay = new SimpleDateFormat("d", locale).format(parse);
        String endDay = new SimpleDateFormat("d", locale).format(parse2);
        StringBuilder sb = new StringBuilder();
        sb.append(startDay);
        Intrinsics.checkNotNullExpressionValue(startDay, "startDay");
        sb.append(r(startDay));
        sb.append(" - ");
        sb.append(endDay);
        Intrinsics.checkNotNullExpressionValue(endDay, "endDay");
        sb.append(r(endDay));
        return sb.toString();
    }

    public final String o(String str) {
        Locale locale = Locale.ENGLISH;
        String format = new SimpleDateFormat("MMM", locale).format(new SimpleDateFormat("dd MMM yyyy", locale).parse(str));
        Intrinsics.checkNotNullExpressionValue(format, "outputFormat.format(dateObj)");
        return format;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Serializable serializable = requireArguments().getSerializable(FitnessChallengeConstants.Companion.getSHARE_CHALLENGE_DATA());
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.fitnesschallenges.ChallengeShareData");
            this.n = (ChallengeShareData) serializable;
            Bundle arguments = getArguments();
            this.o = arguments != null ? (Uri) arguments.getParcelable("PROFILE_URI") : null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentShareChallengeBinding.inflate(inflater, viewGroup, false);
        return p().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    @RequiresApi(26)
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        final FragmentShareChallengeBinding p = p();
        p.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.d1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentShareChallenge.u(FragmentShareChallenge.this, view2);
            }
        });
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ImageView ivPoweredCove = p.ivPoweredCove;
        Intrinsics.checkNotNullExpressionValue(ivPoweredCove, "ivPoweredCove");
        ThemesUtils.setPoweredByLogoIcon(requireContext, ivPoweredCove, true);
        if (this.n.getChallengeGoalType() != null) {
            FitnessChallengeGoalType challengeGoalType = this.n.getChallengeGoalType();
            int i = challengeGoalType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[challengeGoalType.ordinal()];
            if (i == 1) {
                p.goal.icon.setImageResource(R.drawable.ic_calories);
                p.goal.title.setText(String.valueOf(this.n.getChallengeGoal()));
                p.goal.desc.setText(getString(R.string.cal_unit));
            } else if (i == 2) {
                p.goal.icon.setImageResource(R.drawable.ic_disatnce);
                p.goal.title.setText(String.valueOf(this.n.getChallengeGoal()));
                p.goal.desc.setText(getString(R.string.km_small));
            }
        }
        if (this.n.getChallengeStartDate() != null && this.n.getChallengeEndDate() != null) {
            p.duration.icon.setImageResource(R.drawable.ic_duration_clock);
            String challengeStartDate = this.n.getChallengeStartDate();
            Intrinsics.checkNotNull(challengeStartDate);
            String o = o(challengeStartDate);
            String challengeEndDate = this.n.getChallengeEndDate();
            Intrinsics.checkNotNull(challengeEndDate);
            String o2 = o(challengeEndDate);
            if (!Intrinsics.areEqual(o, o2)) {
                o = o + "   " + o2;
            }
            TextView textView = p.duration.title;
            String challengeStartDate2 = this.n.getChallengeStartDate();
            Intrinsics.checkNotNull(challengeStartDate2);
            String challengeEndDate2 = this.n.getChallengeEndDate();
            Intrinsics.checkNotNull(challengeEndDate2);
            textView.setText(n(challengeStartDate2, challengeEndDate2));
            p.duration.desc.setText(o);
        }
        p.participants.icon.setImageResource(R.drawable.ic_participants);
        p.participants.desc.setText(getString(R.string.participants));
        p.participants.title.setText(this.n.getParticipantsInChallenge() != null ? String.valueOf(this.n.getParticipantsInChallenge()) : BleConst.GetDeviceTime);
        if (this.n.getChallengeProgress() != null) {
            Integer challengeProgress = this.n.getChallengeProgress();
            Intrinsics.checkNotNull(challengeProgress);
            int intValue = challengeProgress.intValue();
            p.challengeProgressTv.setText(intValue + getString(R.string.goal_completed));
            ProgressBar progressBar = p.challengePgBar;
            Integer challengeProgress2 = this.n.getChallengeProgress();
            Intrinsics.checkNotNull(challengeProgress2);
            progressBar.setProgress(challengeProgress2.intValue());
        } else {
            ConstraintLayout clProgress = p.clProgress;
            Intrinsics.checkNotNullExpressionValue(clProgress, "clProgress");
            gone(clProgress);
        }
        if (this.n.getChallengetitle() != null) {
            p.challengeTitleTv.setText(this.n.getChallengetitle());
            p.challengeDescTv.setText(this.n.getChallengeDesc());
        }
        if (this.o != null) {
            ImageView defaultPic = p.defaultPic;
            Intrinsics.checkNotNullExpressionValue(defaultPic, "defaultPic");
            gone(defaultPic);
            ConstraintLayout cldefaultuserdetails = p.cldefaultuserdetails;
            Intrinsics.checkNotNullExpressionValue(cldefaultuserdetails, "cldefaultuserdetails");
            gone(cldefaultuserdetails);
            ImageView userPic = p.userPic;
            Intrinsics.checkNotNullExpressionValue(userPic, "userPic");
            visible(userPic);
            TextView tvUserName = p.tvUserName;
            Intrinsics.checkNotNullExpressionValue(tvUserName, "tvUserName");
            visible(tvUserName);
            TextView tvUserDesc = p.tvUserDesc;
            Intrinsics.checkNotNullExpressionValue(tvUserDesc, "tvUserDesc");
            visible(tvUserDesc);
            p.tvUserName.setText(SessionManager.getInstance(getContext()).getUserDetails().getName());
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), this.o);
            if (bitmap != null) {
                p.userPic.setImageBitmap(CircularImageView.getCircleBitmap(bitmap));
            }
        } else {
            ImageView defaultPic2 = p.defaultPic;
            Intrinsics.checkNotNullExpressionValue(defaultPic2, "defaultPic");
            visible(defaultPic2);
            ConstraintLayout cldefaultuserdetails2 = p.cldefaultuserdetails;
            Intrinsics.checkNotNullExpressionValue(cldefaultuserdetails2, "cldefaultuserdetails");
            visible(cldefaultuserdetails2);
            p.userName.setText(SessionManager.getInstance(getContext()).getUserDetails().getName());
            p.week.setText(q());
            GlideUtils.loadScaledImage(getActivity(), SessionManager.getInstance(getActivity()).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.fitnesschallenges.fragments.FragmentShareChallenge$onViewCreated$1$2
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    Bitmap circleBitmap;
                    Intrinsics.checkNotNullParameter(resource, "resource");
                    if (FragmentShareChallengeBinding.this.profileUserPic == null || (circleBitmap = AppUtils.getCircleBitmap(resource)) == null) {
                        return;
                    }
                    FragmentShareChallengeBinding.this.profileUserPic.setImageBitmap(circleBitmap);
                    ImageView imageView = FragmentShareChallengeBinding.this.userPic;
                    if (imageView != null) {
                        imageView.setImageBitmap(circleBitmap);
                    }
                    ImageView imageView2 = FragmentShareChallengeBinding.this.defaultPic;
                    if (imageView2 != null) {
                        imageView2.setImageBitmap(circleBitmap);
                    }
                }
            });
        }
        p.shareBtn.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.e1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentShareChallenge.v(FragmentShareChallenge.this, p, view2);
            }
        });
    }

    public final FragmentShareChallengeBinding p() {
        FragmentShareChallengeBinding fragmentShareChallengeBinding = this.m;
        Intrinsics.checkNotNull(fragmentShareChallengeBinding);
        return fragmentShareChallengeBinding;
    }

    public final String q() {
        String format = new SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(new Date());
        System.out.println((Object) format);
        return "Shared on " + format;
    }

    public final String r(String str) {
        int parseInt = Integer.parseInt(str);
        boolean z = false;
        if (11 <= parseInt && parseInt < 14) {
            z = true;
        }
        if (z) {
            return "th";
        }
        int i = parseInt % 10;
        return i == 1 ? "st" : i == 2 ? "nd" : i == 3 ? "rd" : "th";
    }

    public final Uri s(Bitmap bitmap) {
        try {
            String format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            String insertImage = MediaStore.Images.Media.insertImage(requireActivity().getContentResolver(), bitmap, "Image_" + format + ".jpg", (String) null);
            if (insertImage != null) {
                return Uri.parse(insertImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmap = bitmap;
    }

    public final void setChallengeShareData(@NotNull ChallengeShareData challengeShareData) {
        Intrinsics.checkNotNullParameter(challengeShareData, "<set-?>");
        this.n = challengeShareData;
    }

    public final void setProfileUri(@Nullable Uri uri) {
        this.o = uri;
    }

    public final void t() {
        HashMap<String, Object> hashMap = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        String challengetitle = this.n.getChallengetitle();
        Intrinsics.checkNotNull(challengetitle);
        boolean z = true;
        if (!(challengetitle == null || challengetitle.length() == 0)) {
            String value = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_NAME.getValue();
            String challengetitle2 = this.n.getChallengetitle();
            Intrinsics.checkNotNull(challengetitle2);
            hashMap.put(value, challengetitle2);
        }
        String challengeDesc = this.n.getChallengeDesc();
        if (!(challengeDesc == null || challengeDesc.length() == 0)) {
            String value2 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_DESCRIPTION.getValue();
            String challengeDesc2 = this.n.getChallengeDesc();
            if (challengeDesc2 == null) {
                challengeDesc2 = "";
            }
            hashMap.put(value2, challengeDesc2);
        }
        Integer participantsInChallenge = this.n.getParticipantsInChallenge();
        Intrinsics.checkNotNull(participantsInChallenge);
        if (participantsInChallenge != null) {
            String value3 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_PARTICIPANT_COUNT.getValue();
            Integer participantsInChallenge2 = this.n.getParticipantsInChallenge();
            Intrinsics.checkNotNull(participantsInChallenge2);
            hashMap.put(value3, participantsInChallenge2);
        }
        if (this.n.getChallengeType() != null) {
            if (kotlin.text.m.equals$default(this.n.getChallengeType(), FitnessChallengeConstants.GLOBAL, false, 2, null)) {
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_TYPE.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_SYSTEM.getValue());
            } else {
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_TYPE.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CUSTOM.getValue());
            }
        }
        String createdBy = this.n.getCreatedBy();
        if (!(createdBy == null || createdBy.length() == 0)) {
            if (this.n.isCreator()) {
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_CREATOR.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_SELF.getValue());
            } else {
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_CREATOR.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_USER.getValue());
            }
        } else {
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_CREATOR.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_SYSTEM.getValue());
        }
        FitnessChallengeGoalType challengeGoalType = this.n.getChallengeGoalType();
        Intrinsics.checkNotNull(challengeGoalType);
        if (challengeGoalType == FitnessChallengeGoalType.DISTANCE) {
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_DISTANCE.getValue(), Integer.valueOf(Integer.parseInt(String.valueOf(this.n.getChallengeGoal()))));
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_CALORIE.getValue(), CleverTapConstants.CustomEventValues.NA.getValue());
        } else {
            Integer challengeGoal = this.n.getChallengeGoal();
            if (challengeGoal != null) {
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_CALORIE.getValue(), Integer.valueOf(challengeGoal.intValue()));
            }
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_DISTANCE.getValue(), CleverTapConstants.CustomEventValues.NA.getValue());
        }
        String challengeStartDate = this.n.getChallengeStartDate();
        if (!(challengeStartDate == null || challengeStartDate.length() == 0)) {
            String challengeStartDate2 = this.n.getChallengeStartDate();
            Date parse = challengeStartDate2 != null ? simpleDateFormat.parse(challengeStartDate2) : null;
            if (parse != null) {
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_START_DATE.getValue(), ExtensionsKt.formateDateAndTime(parse, true));
            }
        }
        String challengeEndDate = this.n.getChallengeEndDate();
        if (challengeEndDate != null && challengeEndDate.length() != 0) {
            z = false;
        }
        if (!z) {
            String challengeEndDate2 = this.n.getChallengeEndDate();
            Date parse2 = challengeEndDate2 != null ? simpleDateFormat.parse(challengeEndDate2) : null;
            if (parse2 != null) {
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_END_DATE.getValue(), ExtensionsKt.formateDateAndTime(parse2, false));
            }
        }
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_CHALLENGE_SHARE_SUCCESS.getValue(), hashMap);
    }
}
