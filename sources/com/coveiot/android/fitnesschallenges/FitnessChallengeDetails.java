package com.coveiot.android.fitnesschallenges;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.fitnesschallenges.adpter.PageAdapterChallengeHome;
import com.coveiot.android.fitnesschallenges.databinding.ActivityFitnessDetailsBinding;
import com.coveiot.android.fitnesschallenges.fragments.FragmentShareChallenge;
import com.coveiot.android.fitnesschallenges.model.FitnessChallengeShareModel;
import com.coveiot.android.fitnesschallenges.utils.ExtensionsKt;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeCleverTapConstants;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeGoalType;
import com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeDetailsViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.BottomSheetDialogTwoButtonsWithBg;
import com.coveiot.android.theme.BottomSheetThemeDialogOneButtonTitleMessage;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.CommonMessageDialog;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.JoinChallengeReq;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.google.gson.Gson;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FitnessChallengeDetails extends BaseActivity implements SuccessResultListener {
    public FitnessChallengeDetailsViewModel B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    @Nullable
    public BuddiesChallengeDetail I;
    @Nullable
    public String J;
    @Nullable
    public FitnessChallengeShareModel K;
    public boolean L;
    public boolean M;
    public double N;
    public int P;
    @Nullable
    public BottomSheetDialogTwoButtons Q;
    @Nullable
    public BottomSheetThemeDialogOneButtonTitleMessage R;
    public PageAdapterChallengeHome adapter;
    public Bitmap bitmap;
    public String mCurrentPhotoPath;
    public ActivityFitnessDetailsBinding p;
    @Nullable
    public String q;
    @Nullable
    public String r;
    public boolean s;
    @Nullable
    public BottomSheetDialogTwoButtonsWithBg t;
    @Nullable
    public BottomSheetDialogTwoButtons u;
    @Nullable
    public Uri w;
    @Nullable
    public Uri x;
    @Nullable
    public String[] z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int v = 123;
    public final int y = 2;
    @NotNull
    public ChallengeShareData A = new ChallengeShareData();
    public boolean H = true;
    public double O = 1.0d;

    /* JADX WARN: Removed duplicated region for block: B:100:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x038f  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x03b3  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:160:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void D0(com.coveiot.android.fitnesschallenges.FitnessChallengeDetails r16, com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail r17) {
        /*
            Method dump skipped, instructions count: 981
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.fitnesschallenges.FitnessChallengeDetails.D0(com.coveiot.android.fitnesschallenges.FitnessChallengeDetails, com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail):void");
    }

    public static final void F0(CommonMessageDialog commonMessageDialog, boolean z, FitnessChallengeDetails this$0) {
        Intrinsics.checkNotNullParameter(commonMessageDialog, "$commonMessageDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        commonMessageDialog.dismiss();
        if (z) {
            return;
        }
        this$0.dismissProgress();
        this$0.finish();
    }

    public static final void H0(BottomSheetDialogTwoButtons guestOrPairDevicePopup, FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        guestOrPairDevicePopup.dismiss();
        this$0.finish();
    }

    public static final void I0(FitnessChallengeDetails this$0, BottomSheetDialogTwoButtons guestOrPairDevicePopup, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(guestOrPairDevicePopup, "$guestOrPairDevicePopup");
        if (!ThemesUtils.INSTANCE.isLoggedIn(this$0)) {
            Intent intent = new Intent();
            intent.setClassName(this$0, "com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation");
            intent.putExtra("is_modify_phone_no", false);
            intent.putExtra("phone_number", "");
            this$0.startActivity(intent);
            this$0.finish();
        } else {
            SessionManager.getInstance(this$0).setIsLoggedIn(true);
            this$0.getIntent().setClassName(this$0, "com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
            this$0.startActivity(this$0.getIntent());
            this$0.finish();
        }
        guestOrPairDevicePopup.dismiss();
    }

    public static final void K0(FitnessChallengeDetails this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openAppSettings(this$0, -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void M0(FitnessChallengeDetails this$0, View view) {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.R();
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this$0.Q;
        if (bottomSheetDialogTwoButtons2 != null) {
            boolean z = true;
            if (bottomSheetDialogTwoButtons2 == null || !bottomSheetDialogTwoButtons2.isShowing()) {
                z = false;
            }
            if (!z || (bottomSheetDialogTwoButtons = this$0.Q) == null) {
                return;
            }
            bottomSheetDialogTwoButtons.dismiss();
        }
    }

    public static final void N0(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.Q;
        if (bottomSheetDialogTwoButtons != null) {
            boolean z = true;
            if (bottomSheetDialogTwoButtons == null || !bottomSheetDialogTwoButtons.isShowing()) {
                z = false;
            }
            if (z) {
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this$0.Q;
                if (bottomSheetDialogTwoButtons2 != null) {
                    bottomSheetDialogTwoButtons2.dismiss();
                }
                this$0.h0();
            }
        }
    }

    public static final void O0(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.Q;
        if (bottomSheetDialogTwoButtons != null) {
            bottomSheetDialogTwoButtons.dismiss();
        }
    }

    public static final void T(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage = this$0.R;
        Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage);
        bottomSheetThemeDialogOneButtonTitleMessage.dismiss();
    }

    public static final void W(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg = this$0.t;
        if (bottomSheetDialogTwoButtonsWithBg != null) {
            bottomSheetDialogTwoButtonsWithBg.dismiss();
        }
    }

    public static final void X(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg = this$0.t;
        if (bottomSheetDialogTwoButtonsWithBg != null) {
            bottomSheetDialogTwoButtonsWithBg.dismiss();
        }
        String str = this$0.r;
        if (str != null) {
            FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel = this$0.B;
            if (fitnessChallengeDetailsViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fitnessChallengeDetailsViewModel = null;
            }
            fitnessChallengeDetailsViewModel.deleteCreatedChallenge(str);
        }
    }

    public static final void Z(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg = this$0.t;
        if (bottomSheetDialogTwoButtonsWithBg != null) {
            bottomSheetDialogTwoButtonsWithBg.dismiss();
        }
    }

    public static final void a0(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.g0();
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg = this$0.t;
        if (bottomSheetDialogTwoButtonsWithBg != null) {
            bottomSheetDialogTwoButtonsWithBg.dismiss();
        }
    }

    public static final void b0(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void d0(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.u;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void e0(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.u;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void f0(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.u;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
        this$0.showProgress();
        FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel = this$0.B;
        if (fitnessChallengeDetailsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fitnessChallengeDetailsViewModel = null;
        }
        fitnessChallengeDetailsViewModel.leaveFitnessChallenge(new JoinChallengeReq(this$0.r, this$0.q));
        this$0.D = true;
    }

    public static /* synthetic */ void k0(FitnessChallengeDetails fitnessChallengeDetails, CleverTapConstants.EventName eventName, CleverTapConstants.CustomEventValues customEventValues, int i, Object obj) {
        if ((i & 2) != 0) {
            customEventValues = null;
        }
        fitnessChallengeDetails.j0(eventName, customEventValues);
    }

    public static final void l0(final FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding = null;
            if (this$0.L) {
                ActivityFitnessDetailsBinding activityFitnessDetailsBinding2 = this$0.p;
                if (activityFitnessDetailsBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityFitnessDetailsBinding = activityFitnessDetailsBinding2;
                }
                View view2 = activityFitnessDetailsBinding.shareChallengeError;
                Intrinsics.checkNotNullExpressionValue(view2, "binding.shareChallengeError");
                this$0.visible(view2);
                new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.fitnesschallenges.s0
                    @Override // java.lang.Runnable
                    public final void run() {
                        FitnessChallengeDetails.m0(FitnessChallengeDetails.this);
                    }
                }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                return;
            }
            this$0.L0();
            k0(this$0, CleverTapConstants.EventName.BC_CHALLENGE_SHARE_REQUEST, null, 2, null);
            return;
        }
        this$0.showNoInternetMessage();
    }

    public static final void m0(FitnessChallengeDetails this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding = this$0.p;
        if (activityFitnessDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding = null;
        }
        View view = activityFitnessDetailsBinding.shareChallengeError;
        Intrinsics.checkNotNullExpressionValue(view, "binding.shareChallengeError");
        this$0.gone(view);
    }

    public static final void n0(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            BuddiesChallengeDetail buddiesChallengeDetail = this$0.I;
            if (buddiesChallengeDetail != null) {
                Intrinsics.checkNotNull(buddiesChallengeDetail);
                if (buddiesChallengeDetail.getCreator() != null) {
                    BuddiesChallengeDetail buddiesChallengeDetail2 = this$0.I;
                    Intrinsics.checkNotNull(buddiesChallengeDetail2);
                    Boolean creator = buddiesChallengeDetail2.getCreator();
                    Intrinsics.checkNotNullExpressionValue(creator, "buddiesChallengeDetail!!.creator");
                    if (creator.booleanValue()) {
                        ActivityFitnessDetailsBinding activityFitnessDetailsBinding = null;
                        if (!this$0.s) {
                            this$0.s = true;
                            ActivityFitnessDetailsBinding activityFitnessDetailsBinding2 = this$0.p;
                            if (activityFitnessDetailsBinding2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityFitnessDetailsBinding2 = null;
                            }
                            View root = activityFitnessDetailsBinding2.myChallengeEdit.getRoot();
                            Intrinsics.checkNotNullExpressionValue(root, "binding.myChallengeEdit.root");
                            this$0.visible(root);
                            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
                            BuddiesChallengeDetail buddiesChallengeDetail3 = this$0.I;
                            Intrinsics.checkNotNull(buddiesChallengeDetail3);
                            String startDate = buddiesChallengeDetail3.getStartDate();
                            Intrinsics.checkNotNullExpressionValue(startDate, "buddiesChallengeDetail!!.startDate");
                            Date dateFromString = themesUtils.getDateFromString(startDate, "yyyy-MM-dd");
                            if (dateFromString == null || !dateFromString.before(Calendar.getInstance().getTime())) {
                                return;
                            }
                            BuddiesChallengeDetail buddiesChallengeDetail4 = this$0.I;
                            Intrinsics.checkNotNull(buddiesChallengeDetail4);
                            Integer totalParticipants = buddiesChallengeDetail4.getTotalParticipants();
                            if (totalParticipants != null && totalParticipants.intValue() == 1) {
                                ActivityFitnessDetailsBinding activityFitnessDetailsBinding3 = this$0.p;
                                if (activityFitnessDetailsBinding3 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityFitnessDetailsBinding3 = null;
                                }
                                TextView textView = activityFitnessDetailsBinding3.myChallengeEdit.tvEdit;
                                Intrinsics.checkNotNullExpressionValue(textView, "binding.myChallengeEdit.tvEdit");
                                this$0.gone(textView);
                                ActivityFitnessDetailsBinding activityFitnessDetailsBinding4 = this$0.p;
                                if (activityFitnessDetailsBinding4 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    activityFitnessDetailsBinding = activityFitnessDetailsBinding4;
                                }
                                View view2 = activityFitnessDetailsBinding.myChallengeEdit.view1;
                                Intrinsics.checkNotNullExpressionValue(view2, "binding.myChallengeEdit.view1");
                                this$0.gone(view2);
                                return;
                            }
                            return;
                        }
                        this$0.s = false;
                        ActivityFitnessDetailsBinding activityFitnessDetailsBinding5 = this$0.p;
                        if (activityFitnessDetailsBinding5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityFitnessDetailsBinding = activityFitnessDetailsBinding5;
                        }
                        View root2 = activityFitnessDetailsBinding.myChallengeEdit.getRoot();
                        Intrinsics.checkNotNullExpressionValue(root2, "binding.myChallengeEdit.root");
                        this$0.gone(root2);
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        this$0.showNoInternetMessage();
    }

    public static final void o0(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.L) {
            String string = this$0.getString(R.string.max_participants_reached);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.max_participants_reached)");
            String string2 = this$0.getString(R.string.the_challenge_has_already_reached_its_maximium);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.the_c…ady_reached_its_maximium)");
            this$0.S(string, string2);
        } else if (AppUtils.isNetConnected(this$0)) {
            Intent intent = new Intent(this$0, AddParticipantsActivity.class);
            BuddiesChallengeDetail buddiesChallengeDetail = this$0.I;
            Intrinsics.checkNotNull(buddiesChallengeDetail);
            intent.putExtra("challengeId", buddiesChallengeDetail.getChallengeId().toString());
            intent.putExtra(FitnessChallengeConstants.CHALLENGE_ADD_PARTICIPANT_TYPE, 2);
            intent.putExtra("buddiesChallengeDetails", this$0.I);
            this$0.startActivity(intent);
        } else {
            this$0.showNoInternetMessage();
        }
    }

    public static final void p0(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding = this$0.p;
        if (activityFitnessDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding = null;
        }
        View root = activityFitnessDetailsBinding.myChallengeEdit.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.myChallengeEdit.root");
        this$0.gone(root);
        if (AppUtils.isNetConnected(this$0)) {
            this$0.Y();
        } else {
            this$0.showNoInternetMessage();
        }
    }

    public static final void q0(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding = this$0.p;
        if (activityFitnessDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding = null;
        }
        View root = activityFitnessDetailsBinding.myChallengeEdit.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.myChallengeEdit.root");
        this$0.gone(root);
        if (AppUtils.isNetConnected(this$0)) {
            this$0.V();
        } else {
            this$0.showNoInternetMessage();
        }
    }

    public static final void r0(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            this$0.c0();
        } else {
            this$0.showNoInternetMessage();
        }
        k0(this$0, CleverTapConstants.EventName.BC_CHALLENGE_LEAVE_CHALLENGE_REQUEST, null, 2, null);
    }

    public static final void s0(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.L) {
            String string = this$0.getString(R.string.challenge_full);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.challenge_full)");
            String string2 = this$0.getString(R.string.sorry_this_challenge_is_full_desc);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.sorry…s_challenge_is_full_desc)");
            this$0.S(string, string2);
            return;
        }
        this$0.showProgress();
        FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel = this$0.B;
        if (fitnessChallengeDetailsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fitnessChallengeDetailsViewModel = null;
        }
        fitnessChallengeDetailsViewModel.joinFitnessChallenge(new JoinChallengeReq(this$0.r, this$0.q));
        this$0.C = true;
    }

    public static final void t0(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            this$0.i0();
        } else {
            this$0.showNoInternetMessage();
        }
        k0(this$0, CleverTapConstants.EventName.BC_CHALLENGE_VIEW_LEADERBOARD, null, 2, null);
    }

    public static final void u0(FitnessChallengeDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0, ActivityViewAllParticipantsDetails.class);
        intent.putExtra("buddiesChallengeDetails", this$0.I);
        this$0.startActivity(intent);
    }

    public final void A0(BuddiesChallengeDetail buddiesChallengeDetail) {
        this.A.setChallengeId(this.r);
        this.A.setChallengeType(this.q);
        this.A.setChallengetitle(buddiesChallengeDetail.getName());
        this.A.setChallengeDesc(buddiesChallengeDetail.getDescription());
        if (buddiesChallengeDetail.getCreator() != null) {
            ChallengeShareData challengeShareData = this.A;
            Boolean creator = buddiesChallengeDetail.getCreator();
            Intrinsics.checkNotNullExpressionValue(creator, "buddiesChallengeDetail.creator");
            challengeShareData.setCreator(creator.booleanValue());
        }
        if (buddiesChallengeDetail.getCreatedBy() != null) {
            this.A.setCreatedBy(buddiesChallengeDetail.getCreatedBy());
        }
        if (buddiesChallengeDetail.getChallengeType() != null) {
            this.A.setChallengeType(buddiesChallengeDetail.getChallengeType());
        }
        if (!buddiesChallengeDetail.getStatus().equals("PENDING")) {
            this.A.setChallengeProgress(Integer.valueOf(this.P));
        }
        if (buddiesChallengeDetail.getTargetBaseUnits().equals(FitnessChallengeConstants.METERS)) {
            this.A.setChallengeGoalType(FitnessChallengeGoalType.DISTANCE);
            ChallengeShareData challengeShareData2 = this.A;
            Number target = buddiesChallengeDetail.getTarget();
            if (target == null) {
                target = Double.valueOf(0.0d);
            }
            challengeShareData2.setChallengeGoal(Integer.valueOf((int) (target.doubleValue() / 1000.0d)));
        } else {
            this.A.setChallengeGoalType(FitnessChallengeGoalType.CALORIE);
            ChallengeShareData challengeShareData3 = this.A;
            int target2 = buddiesChallengeDetail.getTarget();
            if (target2 == null) {
                target2 = 0;
            }
            challengeShareData3.setChallengeGoal(target2);
        }
        ChallengeShareData challengeShareData4 = this.A;
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        challengeShareData4.setChallengeStartDate(String.valueOf(themesUtils.formattedDate(buddiesChallengeDetail.getStartDate(), "dd MMM YYYY")));
        this.A.setChallengeEndDate(String.valueOf(themesUtils.formattedDate(buddiesChallengeDetail.getEndDate(), "dd MMM YYYY")));
        ChallengeShareData challengeShareData5 = this.A;
        int totalParticipants = buddiesChallengeDetail.getTotalParticipants();
        if (totalParticipants == null) {
            totalParticipants = 0;
        }
        challengeShareData5.setParticipantsInChallenge(totalParticipants);
    }

    public final void B0(List<? extends BuddiesChallengeRes.Item.TopParticipant> list, int i) {
        if (list != null) {
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding = this.p;
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding2 = null;
            if (activityFitnessDetailsBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFitnessDetailsBinding = null;
            }
            TextView textView = activityFitnessDetailsBinding.tvNoParticipant;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvNoParticipant");
            gone(textView);
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding3 = this.p;
            if (activityFitnessDetailsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFitnessDetailsBinding3 = null;
            }
            activityFitnessDetailsBinding3.fitnessChallengeParticipants.topParticipantLayout.setVisibility(0);
            if (!list.isEmpty()) {
                ActivityFitnessDetailsBinding activityFitnessDetailsBinding4 = this.p;
                if (activityFitnessDetailsBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityFitnessDetailsBinding4 = null;
                }
                activityFitnessDetailsBinding4.fitnessChallengeParticipants.topParticipant1.setVisibility(0);
                ActivityFitnessDetailsBinding activityFitnessDetailsBinding5 = this.p;
                if (activityFitnessDetailsBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityFitnessDetailsBinding5 = null;
                }
                activityFitnessDetailsBinding5.fitnessChallengeParticipants.topParticipant2.setVisibility(8);
                ActivityFitnessDetailsBinding activityFitnessDetailsBinding6 = this.p;
                if (activityFitnessDetailsBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityFitnessDetailsBinding6 = null;
                }
                activityFitnessDetailsBinding6.fitnessChallengeParticipants.topParticipant3.setVisibility(8);
                ActivityFitnessDetailsBinding activityFitnessDetailsBinding7 = this.p;
                if (activityFitnessDetailsBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityFitnessDetailsBinding7 = null;
                }
                ImageView imageView = activityFitnessDetailsBinding7.fitnessChallengeParticipants.topParticipant1;
                Intrinsics.checkNotNullExpressionValue(imageView, "binding.fitnessChallenge…ticipants.topParticipant1");
                setImage(imageView, list.get(0).getDpUrl(), false);
                ActivityFitnessDetailsBinding activityFitnessDetailsBinding8 = this.p;
                if (activityFitnessDetailsBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityFitnessDetailsBinding8 = null;
                }
                activityFitnessDetailsBinding8.fitnessChallengeParticipants.tvParticipantCount.setText(getString(R.string.participant));
                if (list.size() > 1) {
                    ActivityFitnessDetailsBinding activityFitnessDetailsBinding9 = this.p;
                    if (activityFitnessDetailsBinding9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityFitnessDetailsBinding9 = null;
                    }
                    activityFitnessDetailsBinding9.fitnessChallengeParticipants.topParticipant2.setVisibility(0);
                    ActivityFitnessDetailsBinding activityFitnessDetailsBinding10 = this.p;
                    if (activityFitnessDetailsBinding10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityFitnessDetailsBinding10 = null;
                    }
                    activityFitnessDetailsBinding10.fitnessChallengeParticipants.topParticipant3.setVisibility(8);
                    ActivityFitnessDetailsBinding activityFitnessDetailsBinding11 = this.p;
                    if (activityFitnessDetailsBinding11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityFitnessDetailsBinding11 = null;
                    }
                    ImageView imageView2 = activityFitnessDetailsBinding11.fitnessChallengeParticipants.topParticipant2;
                    Intrinsics.checkNotNullExpressionValue(imageView2, "binding.fitnessChallenge…ticipants.topParticipant2");
                    setImage(imageView2, list.get(1).getDpUrl(), false);
                    ActivityFitnessDetailsBinding activityFitnessDetailsBinding12 = this.p;
                    if (activityFitnessDetailsBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityFitnessDetailsBinding12 = null;
                    }
                    TextView textView2 = activityFitnessDetailsBinding12.fitnessChallengeParticipants.tvParticipantCount;
                    int i2 = R.string.participants;
                    textView2.setText(getString(i2));
                    if (list.size() > 2) {
                        ActivityFitnessDetailsBinding activityFitnessDetailsBinding13 = this.p;
                        if (activityFitnessDetailsBinding13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityFitnessDetailsBinding13 = null;
                        }
                        activityFitnessDetailsBinding13.fitnessChallengeParticipants.topParticipant3.setVisibility(0);
                        ActivityFitnessDetailsBinding activityFitnessDetailsBinding14 = this.p;
                        if (activityFitnessDetailsBinding14 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityFitnessDetailsBinding14 = null;
                        }
                        ImageView imageView3 = activityFitnessDetailsBinding14.fitnessChallengeParticipants.topParticipant3;
                        Intrinsics.checkNotNullExpressionValue(imageView3, "binding.fitnessChallenge…ticipants.topParticipant3");
                        setImage(imageView3, list.get(2).getDpUrl(), false);
                        ActivityFitnessDetailsBinding activityFitnessDetailsBinding15 = this.p;
                        if (activityFitnessDetailsBinding15 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityFitnessDetailsBinding15 = null;
                        }
                        activityFitnessDetailsBinding15.fitnessChallengeParticipants.tvParticipantCount.setText(getString(i2));
                        if (list.size() > 3) {
                            ActivityFitnessDetailsBinding activityFitnessDetailsBinding16 = this.p;
                            if (activityFitnessDetailsBinding16 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityFitnessDetailsBinding2 = activityFitnessDetailsBinding16;
                            }
                            TextView textView3 = activityFitnessDetailsBinding2.fitnessChallengeParticipants.tvParticipantCount;
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            Locale locale = Locale.ENGLISH;
                            String string = getString(R.string.plus_participants);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.plus_participants)");
                            String format = String.format(locale, string, Arrays.copyOf(new Object[]{String.valueOf(i - 3)}, 1));
                            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                            textView3.setText(format);
                        }
                    }
                }
            }
        }
    }

    public final void C0() {
        FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel = this.B;
        if (fitnessChallengeDetailsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fitnessChallengeDetailsViewModel = null;
        }
        fitnessChallengeDetailsViewModel.getChallengeDetailsData().observe(this, new Observer() { // from class: com.coveiot.android.fitnesschallenges.r0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FitnessChallengeDetails.D0(FitnessChallengeDetails.this, (BuddiesChallengeDetail) obj);
            }
        });
    }

    public final void E0(final boolean z, String str) {
        Window window;
        final CommonMessageDialog commonMessageDialog = new CommonMessageDialog(this, str, false, true);
        commonMessageDialog.show(getSupportFragmentManager(), "");
        Dialog dialog = commonMessageDialog.getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setGravity(80);
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.fitnesschallenges.t0
            @Override // java.lang.Runnable
            public final void run() {
                FitnessChallengeDetails.F0(CommonMessageDialog.this, z, this);
            }
        }, 1000L);
    }

    public final void G0() {
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        String string = getString(!themesUtils.isLoggedIn(this) ? R.string.login : R.string.pair_device);
        Intrinsics.checkNotNullExpressionValue(string, "if(!isLoggedIn())getStri…ing(R.string.pair_device)");
        String string2 = getString(!themesUtils.isLoggedIn(this) ? R.string.please_login_to_use_this_feature : R.string.please_pair_with_your_bluetooth_device_to_use_this_feature);
        Intrinsics.checkNotNullExpressionValue(string2, "if(!isLoggedIn()) getStr…vice_to_use_this_feature)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
        bottomSheetDialogTwoButtons.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.q0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessChallengeDetails.H0(BottomSheetDialogTwoButtons.this, this, view);
            }
        });
        String string4 = getString(!themesUtils.isLoggedIn(this) ? R.string.login_now : R.string.pair_now);
        Intrinsics.checkNotNullExpressionValue(string4, "if(!isLoggedIn())getStri…String(R.string.pair_now)");
        bottomSheetDialogTwoButtons.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.p0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessChallengeDetails.I0(FitnessChallengeDetails.this, bottomSheetDialogTwoButtons, view);
            }
        });
        bottomSheetDialogTwoButtons.setCancelable(false);
        if (bottomSheetDialogTwoButtons.isShowing()) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    public final void J0(String str) {
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, str);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.o0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessChallengeDetails.K0(FitnessChallengeDetails.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void L0() {
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons;
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.Q;
        if (bottomSheetDialogTwoButtons2 != null) {
            if ((bottomSheetDialogTwoButtons2 != null && bottomSheetDialogTwoButtons2.isShowing()) && (bottomSheetDialogTwoButtons = this.Q) != null) {
                bottomSheetDialogTwoButtons.dismiss();
            }
            this.Q = null;
        }
        String string = getString(R.string.take_picture);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.take_picture)");
        String string2 = getString(R.string.capture_a_picture_before_sharing);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.captu…a_picture_before_sharing)");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = new BottomSheetDialogTwoButtons(this, string, string2, true);
        this.Q = bottomSheetDialogTwoButtons3;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
        String string3 = getString(R.string.yes);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
        bottomSheetDialogTwoButtons3.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.j0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessChallengeDetails.M0(FitnessChallengeDetails.this, view);
            }
        });
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.Q;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
        String string4 = getString(R.string.f4491no);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
        bottomSheetDialogTwoButtons4.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessChallengeDetails.N0(FitnessChallengeDetails.this, view);
            }
        });
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.Q;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons5);
        bottomSheetDialogTwoButtons5.setCrossButtonVisible();
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons6 = this.Q;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons6);
        bottomSheetDialogTwoButtons6.setCrossButton(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.z0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessChallengeDetails.O0(FitnessChallengeDetails.this, view);
            }
        });
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons7 = this.Q;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons7);
        bottomSheetDialogTwoButtons7.setCancelable(false);
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons8 = this.Q;
        if (bottomSheetDialogTwoButtons8 != null) {
            bottomSheetDialogTwoButtons8.show();
        }
    }

    public final void P0() {
        File file = null;
        this.w = null;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            try {
                file = U();
            } catch (IOException unused) {
            }
            if (file != null) {
                Uri uriForFile = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);
                this.w = uriForFile;
                intent.putExtra("output", uriForFile);
                startActivityForResult(intent, this.y);
            }
        }
    }

    public final void R() {
        String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.CAMERA"});
        this.z = checkPermissionsHasGranted;
        Intrinsics.checkNotNull(checkPermissionsHasGranted);
        if (!(checkPermissionsHasGranted.length == 0)) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA")) {
                String[] strArr = this.z;
                Intrinsics.checkNotNull(strArr);
                ActivityCompat.requestPermissions(this, strArr, 0);
                return;
            }
            String string = getString(R.string.camera_permission_text);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.camera_permission_text)");
            J0(string);
            return;
        }
        P0();
    }

    public final void S(String str, String str2) {
        if (this.R == null) {
            BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage = new BottomSheetThemeDialogOneButtonTitleMessage(this, str, str2);
            this.R = bottomSheetThemeDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage);
            String string = getString(com.coveiot.android.theme.R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.android.theme.R.string.ok)");
            bottomSheetThemeDialogOneButtonTitleMessage.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.y0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeDetails.T(FitnessChallengeDetails.this, view);
                }
            });
        }
        BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage2 = this.R;
        Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage2);
        if (bottomSheetThemeDialogOneButtonTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetThemeDialogOneButtonTitleMessage bottomSheetThemeDialogOneButtonTitleMessage3 = this.R;
        Intrinsics.checkNotNull(bottomSheetThemeDialogOneButtonTitleMessage3);
        bottomSheetThemeDialogOneButtonTitleMessage3.show();
    }

    public final File U() throws IOException {
        String formatDate = AppUtils.formatDate(new Date(), "yyyyMMdd_HHmmss");
        File image = File.createTempFile("JPEG_" + formatDate + '_', ".jpg", getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        String absolutePath = image.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "image.absolutePath");
        setMCurrentPhotoPath(absolutePath);
        Intrinsics.checkNotNullExpressionValue(image, "image");
        return image;
    }

    public final void V() {
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg;
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg2;
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg3 = this.t;
        if (bottomSheetDialogTwoButtonsWithBg3 != null) {
            Boolean valueOf = bottomSheetDialogTwoButtonsWithBg3 != null ? Boolean.valueOf(bottomSheetDialogTwoButtonsWithBg3.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (bottomSheetDialogTwoButtonsWithBg2 = this.t) != null) {
                bottomSheetDialogTwoButtonsWithBg2.dismiss();
            }
            this.t = null;
        }
        String string = getString(R.string.delete_question);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.delete_question)");
        String string2 = getString(R.string.delete_message);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.delete_message)");
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg4 = new BottomSheetDialogTwoButtonsWithBg(this, string, string2);
        this.t = bottomSheetDialogTwoButtonsWithBg4;
        String string3 = getString(R.string.f4491no);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.no)");
        bottomSheetDialogTwoButtonsWithBg4.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.a1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessChallengeDetails.W(FitnessChallengeDetails.this, view);
            }
        });
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg5 = this.t;
        if (bottomSheetDialogTwoButtonsWithBg5 != null) {
            String string4 = getString(R.string.yes);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.yes)");
            bottomSheetDialogTwoButtonsWithBg5.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.g0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeDetails.X(FitnessChallengeDetails.this, view);
                }
            });
        }
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg6 = this.t;
        Boolean valueOf2 = bottomSheetDialogTwoButtonsWithBg6 != null ? Boolean.valueOf(bottomSheetDialogTwoButtonsWithBg6.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue() || (bottomSheetDialogTwoButtonsWithBg = this.t) == null) {
            return;
        }
        bottomSheetDialogTwoButtonsWithBg.show();
    }

    public final void Y() {
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg;
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg2;
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg3 = this.t;
        if (bottomSheetDialogTwoButtonsWithBg3 != null) {
            Boolean valueOf = bottomSheetDialogTwoButtonsWithBg3 != null ? Boolean.valueOf(bottomSheetDialogTwoButtonsWithBg3.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (bottomSheetDialogTwoButtonsWithBg2 = this.t) != null) {
                bottomSheetDialogTwoButtonsWithBg2.dismiss();
            }
            this.t = null;
        }
        String string = getString(R.string.edit_question);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.edit_question)");
        String string2 = getString(R.string.edit_message);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.edit_message)");
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg4 = new BottomSheetDialogTwoButtonsWithBg(this, string, string2);
        this.t = bottomSheetDialogTwoButtonsWithBg4;
        String string3 = getString(R.string.f4491no);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.no)");
        bottomSheetDialogTwoButtonsWithBg4.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.u0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessChallengeDetails.Z(FitnessChallengeDetails.this, view);
            }
        });
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg5 = this.t;
        if (bottomSheetDialogTwoButtonsWithBg5 != null) {
            String string4 = getString(R.string.yes);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.yes)");
            bottomSheetDialogTwoButtonsWithBg5.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.n0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeDetails.a0(FitnessChallengeDetails.this, view);
                }
            });
        }
        BottomSheetDialogTwoButtonsWithBg bottomSheetDialogTwoButtonsWithBg6 = this.t;
        Boolean valueOf2 = bottomSheetDialogTwoButtonsWithBg6 != null ? Boolean.valueOf(bottomSheetDialogTwoButtonsWithBg6.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue() || (bottomSheetDialogTwoButtonsWithBg = this.t) == null) {
            return;
        }
        bottomSheetDialogTwoButtonsWithBg.show();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final void c0() {
        if (this.u == null) {
            String string = getString(R.string.leave_challenge);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.leave_challenge)");
            String string2 = getString(R.string.leave_challenge_message);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.leave_challenge_message)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, true);
            this.u = bottomSheetDialogTwoButtons;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
            String string3 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.cancel)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.k0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeDetails.d0(FitnessChallengeDetails.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.u;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
            bottomSheetDialogTwoButtons2.setCrossButtonVisible();
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.u;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
            bottomSheetDialogTwoButtons3.setCrossButton(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.b1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeDetails.e0(FitnessChallengeDetails.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.u;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
            String string4 = getString(R.string.leave);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.leave)");
            bottomSheetDialogTwoButtons4.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.l0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeDetails.f0(FitnessChallengeDetails.this, view);
                }
            });
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons5 = this.u;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons5);
        if (bottomSheetDialogTwoButtons5.isShowing()) {
            return;
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons6 = this.u;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons6);
        bottomSheetDialogTwoButtons6.show();
    }

    public final void g0() {
        Intent intent = new Intent(this, ActivityCreateChallenge.class);
        intent.putExtra("isEditChallenge", true);
        intent.putExtra("buddiesChallengeDetails", this.I);
        startActivity(intent);
    }

    @NotNull
    public final PageAdapterChallengeHome getAdapter() {
        PageAdapterChallengeHome pageAdapterChallengeHome = this.adapter;
        if (pageAdapterChallengeHome != null) {
            return pageAdapterChallengeHome;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        return null;
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

    @Nullable
    public final Uri getCropedPhotoUri() {
        return this.x;
    }

    @NotNull
    public final String getMCurrentPhotoPath() {
        String str = this.mCurrentPhotoPath;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mCurrentPhotoPath");
        return null;
    }

    @Nullable
    public final String[] getPermission_array() {
        return this.z;
    }

    @Nullable
    public final Uri getPhotoURI() {
        return this.w;
    }

    public final int getProgressValue() {
        return this.P;
    }

    public final void h0() {
        getSupportFragmentManager().beginTransaction().replace(R.id.leaderboardContainer, FragmentShareChallenge.Companion.newInstance(this.A, this.x)).addToBackStack("").commitAllowingStateLoss();
    }

    public final void i0() {
        Intent intent = new Intent(this, ActivityViewLeaderboard.class);
        intent.putExtra("buddiesChallengeDetails", this.I);
        intent.putExtra(FitnessChallengeConstants.ISFROMACHIEVEMENTS, this.M);
        startActivity(intent);
    }

    public final void initToolbar() {
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding = this.p;
        if (activityFitnessDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding = null;
        }
        TextView textView = (TextView) activityFitnessDetailsBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding2 = this.p;
        if (activityFitnessDetailsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding2 = null;
        }
        TextView textView2 = (TextView) activityFitnessDetailsBinding2.toolbar.findViewById(R.id.toolbar_back_arrow);
        BuddiesChallengeDetail buddiesChallengeDetail = this.I;
        textView.setText(getString(kotlin.text.m.equals$default(buddiesChallengeDetail != null ? buddiesChallengeDetail.getStatus() : null, "PENDING", false, 2, null) ? R.string.join_challenge : R.string.my_challenges));
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessChallengeDetails.b0(FitnessChallengeDetails.this, view);
            }
        });
    }

    public final void j0(CleverTapConstants.EventName eventName, CleverTapConstants.CustomEventValues customEventValues) {
        HashMap<String, Object> hashMap = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-DD", Locale.ENGLISH);
        if (eventName == CleverTapConstants.EventName.BC_CHALLENGE_DETAILSPAGE_VIEWED || eventName == CleverTapConstants.EventName.BC_CHALLENGE_JOIN_SUCCESS || eventName == CleverTapConstants.EventName.BC_CHALLENGE_LEAVE_CHALLENGE_SUCCESS) {
            String value = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_ID.getValue();
            BuddiesChallengeDetail buddiesChallengeDetail = this.I;
            Intrinsics.checkNotNull(buddiesChallengeDetail);
            hashMap.put(value, buddiesChallengeDetail.getChallengeId());
        }
        BuddiesChallengeDetail buddiesChallengeDetail2 = this.I;
        Intrinsics.checkNotNull(buddiesChallengeDetail2);
        String name = buddiesChallengeDetail2.getName();
        boolean z = true;
        if (!(name == null || name.length() == 0)) {
            String value2 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_NAME.getValue();
            BuddiesChallengeDetail buddiesChallengeDetail3 = this.I;
            Intrinsics.checkNotNull(buddiesChallengeDetail3);
            hashMap.put(value2, buddiesChallengeDetail3.getName());
        }
        BuddiesChallengeDetail buddiesChallengeDetail4 = this.I;
        Intrinsics.checkNotNull(buddiesChallengeDetail4);
        String description = buddiesChallengeDetail4.getDescription();
        if (!(description == null || description.length() == 0)) {
            String value3 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_DESCRIPTION.getValue();
            BuddiesChallengeDetail buddiesChallengeDetail5 = this.I;
            Intrinsics.checkNotNull(buddiesChallengeDetail5);
            hashMap.put(value3, buddiesChallengeDetail5.getDescription());
        }
        BuddiesChallengeDetail buddiesChallengeDetail6 = this.I;
        Intrinsics.checkNotNull(buddiesChallengeDetail6);
        if (buddiesChallengeDetail6.getTotalParticipants() != null) {
            String value4 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_PARTICIPANT_COUNT.getValue();
            BuddiesChallengeDetail buddiesChallengeDetail7 = this.I;
            Intrinsics.checkNotNull(buddiesChallengeDetail7);
            hashMap.put(value4, buddiesChallengeDetail7.getTotalParticipants());
        }
        BuddiesChallengeDetail buddiesChallengeDetail8 = this.I;
        Intrinsics.checkNotNull(buddiesChallengeDetail8);
        String challengeType = buddiesChallengeDetail8.getChallengeType();
        if (!(challengeType == null || challengeType.length() == 0)) {
            BuddiesChallengeDetail buddiesChallengeDetail9 = this.I;
            Intrinsics.checkNotNull(buddiesChallengeDetail9);
            if (buddiesChallengeDetail9.getChallengeType().equals(FitnessChallengeConstants.GLOBAL)) {
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_TYPE.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_SYSTEM.getValue());
            } else {
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_TYPE.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CUSTOM.getValue());
            }
        }
        BuddiesChallengeDetail buddiesChallengeDetail10 = this.I;
        Intrinsics.checkNotNull(buddiesChallengeDetail10);
        String createdBy = buddiesChallengeDetail10.getCreatedBy();
        if (!(createdBy == null || createdBy.length() == 0)) {
            BuddiesChallengeDetail buddiesChallengeDetail11 = this.I;
            Intrinsics.checkNotNull(buddiesChallengeDetail11);
            Boolean creator = buddiesChallengeDetail11.getCreator();
            Intrinsics.checkNotNullExpressionValue(creator, "buddiesChallengeDetail!!.creator");
            if (creator.booleanValue()) {
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_CREATOR.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_SELF.getValue());
            } else {
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_CREATOR.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_USER.getValue());
            }
        } else {
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_CREATOR.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_SYSTEM.getValue());
        }
        BuddiesChallengeDetail buddiesChallengeDetail12 = this.I;
        Intrinsics.checkNotNull(buddiesChallengeDetail12);
        if (buddiesChallengeDetail12.getTargetBaseUnits().equals(FitnessChallengeConstants.METERS)) {
            String value5 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_DISTANCE.getValue();
            BuddiesChallengeDetail buddiesChallengeDetail13 = this.I;
            Intrinsics.checkNotNull(buddiesChallengeDetail13);
            Integer target = buddiesChallengeDetail13.getTarget();
            hashMap.put(value5, Integer.valueOf((target != null ? target.intValue() : 0) / 1000));
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_CALORIE.getValue(), CleverTapConstants.CustomEventValues.NA.getValue());
        } else {
            String value6 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_CALORIE.getValue();
            BuddiesChallengeDetail buddiesChallengeDetail14 = this.I;
            Intrinsics.checkNotNull(buddiesChallengeDetail14);
            Integer target2 = buddiesChallengeDetail14.getTarget();
            hashMap.put(value6, Integer.valueOf(target2 == null ? 0 : target2.intValue()));
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_DISTANCE.getValue(), CleverTapConstants.CustomEventValues.NA.getValue());
        }
        BuddiesChallengeDetail buddiesChallengeDetail15 = this.I;
        Intrinsics.checkNotNull(buddiesChallengeDetail15);
        String startDate = buddiesChallengeDetail15.getStartDate();
        if (!(startDate == null || startDate.length() == 0)) {
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            BuddiesChallengeDetail buddiesChallengeDetail16 = this.I;
            Intrinsics.checkNotNull(buddiesChallengeDetail16);
            String formattedDate = themesUtils.formattedDate(buddiesChallengeDetail16.getStartDate(), "yyyy-MMM-DD");
            Date parse = formattedDate != null ? simpleDateFormat.parse(formattedDate) : null;
            Intrinsics.checkNotNull(parse, "null cannot be cast to non-null type java.util.Date");
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_START_DATE.getValue(), ExtensionsKt.formateDateAndTime(parse, true));
        }
        BuddiesChallengeDetail buddiesChallengeDetail17 = this.I;
        Intrinsics.checkNotNull(buddiesChallengeDetail17);
        String endDate = buddiesChallengeDetail17.getEndDate();
        if (endDate != null && endDate.length() != 0) {
            z = false;
        }
        if (!z) {
            ThemesUtils themesUtils2 = ThemesUtils.INSTANCE;
            BuddiesChallengeDetail buddiesChallengeDetail18 = this.I;
            Intrinsics.checkNotNull(buddiesChallengeDetail18);
            String formattedDate2 = themesUtils2.formattedDate(buddiesChallengeDetail18.getEndDate(), "yyyy-MMM-DD");
            Date parse2 = formattedDate2 != null ? simpleDateFormat.parse(formattedDate2) : null;
            Intrinsics.checkNotNull(parse2, "null cannot be cast to non-null type java.util.Date");
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_END_DATE.getValue(), ExtensionsKt.formateDateAndTime(parse2, false));
        }
        if (customEventValues != null) {
            if (customEventValues.equals(CleverTapConstants.CustomEventValues.FITNESS_CHALLENGE_PERCENT_OF_CHALLENGE_COMPLETED)) {
                BuddiesChallengeDetail buddiesChallengeDetail19 = this.I;
                Intrinsics.checkNotNull(buddiesChallengeDetail19);
                if (buddiesChallengeDetail19.getTargetAchieved() != null) {
                    String value7 = customEventValues.getValue();
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.P);
                    sb.append('%');
                    hashMap.put(value7, sb.toString());
                }
            }
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_JOINED_LOCATION.getValue(), customEventValues.getValue());
        }
        DeviceUtils.Companion.logAnalyticsEvent(eventName.getValue(), hashMap);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.v && i2 == -1) {
            Serializable serializableExtra = intent != null ? intent.getSerializableExtra(FitnessChallengeConstants.Companion.getSELECTED_CONTACTS()) : null;
            if (serializableExtra instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) serializableExtra;
            }
        }
        if (i == this.y) {
            Uri uri = this.w;
            if (uri == null || i2 != -1) {
                return;
            }
            startActivityForResult(CropImage.activity(uri).setCropShape(CropImageView.CropShape.OVAL).setAspectRatio(1, 1).getIntent(this), 203);
        } else if (i == 203) {
            CropImage.ActivityResult activityResult = CropImage.getActivityResult(intent);
            if (i2 != -1) {
                return;
            }
            Uri uri2 = activityResult.getUri();
            if (uri2 != null) {
                this.x = uri2;
                h0();
            }
            k0(this, CleverTapConstants.EventName.BC_CHALLENGE_SHARE_PROFILE_PICADDED, null, 2, null);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @RequiresApi(26)
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityFitnessDetailsBinding inflate = ActivityFitnessDetailsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isLoggedIn(this)) {
            G0();
        } else if (!themesUtils.isDevicePaired(this)) {
            G0();
        } else {
            Uri data = getIntent().getData();
            if (data != null) {
                this.J = data.getQueryParameter("_d");
                Gson gson = new Gson();
                String str = this.J;
                Intrinsics.checkNotNull(str);
                this.K = (FitnessChallengeShareModel) gson.fromJson(themesUtils.decode(str), (Class<Object>) FitnessChallengeShareModel.class);
            }
            if (getIntent().hasExtra(FitnessChallengeConstants.ISFROMACHIEVEMENTS)) {
                this.M = getIntent().getBooleanExtra(FitnessChallengeConstants.ISFROMACHIEVEMENTS, false);
            }
            if (getIntent().hasExtra(FitnessChallengeConstants.ISJOINEDFROMHP)) {
                this.E = getIntent().getBooleanExtra(FitnessChallengeConstants.ISJOINEDFROMHP, false);
            }
            if (getIntent().hasExtra(FitnessChallengeConstants.ISJOINEDFROMFROMFITNESSPAGE)) {
                this.F = getIntent().getBooleanExtra(FitnessChallengeConstants.ISJOINEDFROMFROMFITNESSPAGE, false);
            }
            if (getIntent().hasExtra(FitnessChallengeConstants.ISJOINEDFROMLISTINGPAGE)) {
                this.G = getIntent().getBooleanExtra(FitnessChallengeConstants.ISJOINEDFROMLISTINGPAGE, false);
            }
            getIntent().getBooleanExtra(ThemeConstants.INTENT_EXTRA_FROM_NOTIFICATION.getValue(), false);
            getIntent().getStringExtra(ThemeConstants.NOTIFY_IDENTIFIER.getValue());
            FitnessChallengeShareModel fitnessChallengeShareModel = this.K;
            if (fitnessChallengeShareModel != null) {
                Intrinsics.checkNotNull(fitnessChallengeShareModel);
                this.r = fitnessChallengeShareModel.getChallengeId();
                FitnessChallengeShareModel fitnessChallengeShareModel2 = this.K;
                Intrinsics.checkNotNull(fitnessChallengeShareModel2);
                this.q = fitnessChallengeShareModel2.getChallengeType();
            } else {
                this.r = getIntent().getStringExtra(FitnessChallengeConstants.CHALLENGE_ID);
                this.q = getIntent().getStringExtra(FitnessChallengeConstants.CHALLENGE_TYPE);
            }
            if (this.r == null || this.q == null) {
                finish();
            }
            FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel = new FitnessChallengeDetailsViewModel(this);
            this.B = fitnessChallengeDetailsViewModel;
            fitnessChallengeDetailsViewModel.setMListener(this);
            showProgress();
            if (kotlin.text.m.equals(this.q, FitnessChallengeConstants.GLOBAL, true)) {
                if (this.r != null) {
                    FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel2 = this.B;
                    if (fitnessChallengeDetailsViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fitnessChallengeDetailsViewModel2 = null;
                    }
                    String str2 = this.r;
                    Intrinsics.checkNotNull(str2);
                    fitnessChallengeDetailsViewModel2.getGlobalChallengeDetails(str2);
                }
            } else if (this.r != null) {
                FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel3 = this.B;
                if (fitnessChallengeDetailsViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fitnessChallengeDetailsViewModel3 = null;
                }
                String str3 = this.r;
                Intrinsics.checkNotNull(str3);
                fitnessChallengeDetailsViewModel3.getBuddyChallengeDetails(str3);
            }
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding2 = this.p;
            if (activityFitnessDetailsBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFitnessDetailsBinding2 = null;
            }
            activityFitnessDetailsBinding2.ivShare.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.v0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeDetails.l0(FitnessChallengeDetails.this, view);
                }
            });
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding3 = this.p;
            if (activityFitnessDetailsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFitnessDetailsBinding3 = null;
            }
            activityFitnessDetailsBinding3.ivEdit.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.d0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeDetails.n0(FitnessChallengeDetails.this, view);
                }
            });
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding4 = this.p;
            if (activityFitnessDetailsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFitnessDetailsBinding4 = null;
            }
            activityFitnessDetailsBinding4.btnAddParticipants.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.m0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeDetails.o0(FitnessChallengeDetails.this, view);
                }
            });
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding5 = this.p;
            if (activityFitnessDetailsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFitnessDetailsBinding5 = null;
            }
            activityFitnessDetailsBinding5.myChallengeEdit.tvEdit.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.h0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeDetails.p0(FitnessChallengeDetails.this, view);
                }
            });
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding6 = this.p;
            if (activityFitnessDetailsBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFitnessDetailsBinding6 = null;
            }
            activityFitnessDetailsBinding6.myChallengeEdit.tvDelete.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.w0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeDetails.q0(FitnessChallengeDetails.this, view);
                }
            });
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding7 = this.p;
            if (activityFitnessDetailsBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFitnessDetailsBinding7 = null;
            }
            activityFitnessDetailsBinding7.btnLeaveChallenge.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.e0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeDetails.r0(FitnessChallengeDetails.this, view);
                }
            });
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding8 = this.p;
            if (activityFitnessDetailsBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFitnessDetailsBinding8 = null;
            }
            activityFitnessDetailsBinding8.btnJoinChallenge.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.c0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeDetails.s0(FitnessChallengeDetails.this, view);
                }
            });
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding9 = this.p;
            if (activityFitnessDetailsBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFitnessDetailsBinding9 = null;
            }
            activityFitnessDetailsBinding9.tvLeaderboard.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.i0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeDetails.t0(FitnessChallengeDetails.this, view);
                }
            });
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding10 = this.p;
            if (activityFitnessDetailsBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityFitnessDetailsBinding = activityFitnessDetailsBinding10;
            }
            activityFitnessDetailsBinding.tvViewAllParticipants.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.x0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeDetails.u0(FitnessChallengeDetails.this, view);
                }
            });
            C0();
        }
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
        if (isFinishing()) {
            return;
        }
        dismissProgress();
        if (str != null) {
            if (str.equals(Integer.valueOf(R.string.no_internet_connection))) {
                showNoInternetMessage();
            } else {
                BaseActivity.showCommonMessageDialog$default(this, str, false, 2, null);
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (!(grantResults.length == 0)) {
            String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.CAMERA"});
            Intrinsics.checkNotNullExpressionValue(checkPermissionsHasGranted, "checkPermissionsHasGrant…      )\n                )");
            if (!(checkPermissionsHasGranted.length == 0)) {
                return;
            }
            P0();
            return;
        }
        super.onRequestPermissionsResult(i, permissions, grantResults);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        showProgress();
        FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel = null;
        if (kotlin.text.m.equals(this.q, FitnessChallengeConstants.GLOBAL, true)) {
            if (this.r != null) {
                FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel2 = this.B;
                if (fitnessChallengeDetailsViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    fitnessChallengeDetailsViewModel = fitnessChallengeDetailsViewModel2;
                }
                String str = this.r;
                Intrinsics.checkNotNull(str);
                fitnessChallengeDetailsViewModel.getGlobalChallengeDetails(str);
            }
        } else if (this.r != null) {
            FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel3 = this.B;
            if (fitnessChallengeDetailsViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fitnessChallengeDetailsViewModel = fitnessChallengeDetailsViewModel3;
            }
            String str2 = this.r;
            Intrinsics.checkNotNull(str2);
            fitnessChallengeDetailsViewModel.getBuddyChallengeDetails(str2);
        }
        super.onResume();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
        if (isFinishing()) {
            return;
        }
        dismissProgress();
        if (this.C) {
            String string = getString(R.string.woah_challenge_joined);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.woah_challenge_joined)");
            E0(true, string);
            j0(CleverTapConstants.EventName.BC_CHALLENGE_JOIN_SUCCESS, CleverTapConstants.CustomEventValues.FITNESS_CHALLENGE_DETAILS_PAGE);
            FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel = null;
            if (kotlin.text.m.equals(this.q, FitnessChallengeConstants.GLOBAL, true)) {
                FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel2 = this.B;
                if (fitnessChallengeDetailsViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    fitnessChallengeDetailsViewModel = fitnessChallengeDetailsViewModel2;
                }
                String str = this.r;
                Intrinsics.checkNotNull(str);
                fitnessChallengeDetailsViewModel.getGlobalChallengeDetails(str);
            } else {
                FitnessChallengeDetailsViewModel fitnessChallengeDetailsViewModel3 = this.B;
                if (fitnessChallengeDetailsViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    fitnessChallengeDetailsViewModel = fitnessChallengeDetailsViewModel3;
                }
                String str2 = this.r;
                Intrinsics.checkNotNull(str2);
                fitnessChallengeDetailsViewModel.getBuddyChallengeDetails(str2);
            }
            initToolbar();
            this.C = false;
        } else if (this.D) {
            this.D = false;
            String string2 = getString(R.string.leave_challenge_desc);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.leave_challenge_desc)");
            E0(false, string2);
            j0(CleverTapConstants.EventName.BC_CHALLENGE_LEAVE_CHALLENGE_SUCCESS, CleverTapConstants.CustomEventValues.FITNESS_CHALLENGE_PERCENT_OF_CHALLENGE_COMPLETED);
        } else {
            String string3 = getString(R.string.oops_sorry_to_see_you_go);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.oops_sorry_to_see_you_go)");
            E0(false, string3);
        }
    }

    public final void setAdapter(@NotNull PageAdapterChallengeHome pageAdapterChallengeHome) {
        Intrinsics.checkNotNullParameter(pageAdapterChallengeHome, "<set-?>");
        this.adapter = pageAdapterChallengeHome;
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmap = bitmap;
    }

    public final void setCropedPhotoUri(@Nullable Uri uri) {
        this.x = uri;
    }

    public final void setImage(@NotNull ImageView imageView, @Nullable String str, boolean z) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        if (z) {
            RequestBuilder<Drawable> m30load = Glide.with(imageView).m30load(str);
            int i = R.drawable.system_fittness_challenge_banner_bg1;
            m30load.error(i).placeholder(i).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
            return;
        }
        int i2 = R.drawable.default_avatar;
        Glide.with(imageView).m30load(str).override(300, 300).error(i2).placeholder(i2).diskCacheStrategy(DiskCacheStrategy.ALL).circleCrop().into(imageView);
    }

    public final void setMCurrentPhotoPath(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mCurrentPhotoPath = str;
    }

    public final void setPermission_array(@Nullable String[] strArr) {
        this.z = strArr;
    }

    public final void setPhotoURI(@Nullable Uri uri) {
        this.w = uri;
    }

    public final void setProgressValue(int i) {
        this.P = i;
    }

    public final void v0(BuddiesChallengeDetail buddiesChallengeDetail) {
        ImageView imageView;
        int i;
        String format;
        Integer totalBuddies;
        Integer totalParticipants;
        String format2;
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding = this.p;
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding2 = null;
        if (activityFitnessDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding = null;
        }
        ConstraintLayout constraintLayout = activityFitnessDetailsBinding.clCompletionProgress;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clCompletionProgress");
        gone(constraintLayout);
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding3 = this.p;
        if (activityFitnessDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding3 = null;
        }
        TextView textView = activityFitnessDetailsBinding3.tvLeaderboard;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvLeaderboard");
        gone(textView);
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding4 = this.p;
        if (activityFitnessDetailsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding4 = null;
        }
        Button button = activityFitnessDetailsBinding4.btnAddParticipants;
        Intrinsics.checkNotNullExpressionValue(button, "binding.btnAddParticipants");
        gone(button);
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding5 = this.p;
        if (activityFitnessDetailsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding5 = null;
        }
        Button button2 = activityFitnessDetailsBinding5.btnLeaveChallenge;
        Intrinsics.checkNotNullExpressionValue(button2, "binding.btnLeaveChallenge");
        gone(button2);
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding6 = this.p;
        if (activityFitnessDetailsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding6 = null;
        }
        Button button3 = activityFitnessDetailsBinding6.btnJoinChallenge;
        Intrinsics.checkNotNullExpressionValue(button3, "binding.btnJoinChallenge");
        visible(button3);
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding7 = this.p;
        if (activityFitnessDetailsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding7 = null;
        }
        ImageView imageView2 = activityFitnessDetailsBinding7.ivEdit;
        Intrinsics.checkNotNullExpressionValue(imageView2, "binding.ivEdit");
        gone(imageView2);
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding8 = this.p;
        if (activityFitnessDetailsBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding8 = null;
        }
        activityFitnessDetailsBinding8.infoDetails.tvInfoText.setText(String.valueOf(getString(R.string.join_challenge_desc)));
        if (this.L) {
            imageView = (ImageView) _$_findCachedViewById(R.id.ivShare);
            i = R.drawable.ic_disable_share;
        } else {
            imageView = (ImageView) _$_findCachedViewById(R.id.ivShare);
            i = R.drawable.ic_share_with_circle_bg;
        }
        imageView.setImageDrawable(getDrawable(i));
        if (kotlin.text.m.equals$default(this.q, FitnessChallengeConstants.GLOBAL, false, 2, null)) {
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding9 = this.p;
            if (activityFitnessDetailsBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFitnessDetailsBinding9 = null;
            }
            ImageView imageView3 = activityFitnessDetailsBinding9.ivShare;
            Intrinsics.checkNotNullExpressionValue(imageView3, "binding.ivShare");
            visible(imageView3);
            if (buddiesChallengeDetail.getTotalParticipants() != null && (((totalParticipants = buddiesChallengeDetail.getTotalParticipants()) == null || totalParticipants.intValue() != 0) && buddiesChallengeDetail.getTotalBuddies() != null)) {
                ActivityFitnessDetailsBinding activityFitnessDetailsBinding10 = this.p;
                if (activityFitnessDetailsBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityFitnessDetailsBinding2 = activityFitnessDetailsBinding10;
                }
                TextView textView2 = activityFitnessDetailsBinding2.tvFitCrew;
                Integer totalBuddies2 = buddiesChallengeDetail.getTotalBuddies();
                if (totalBuddies2 != null && totalBuddies2.intValue() == 0) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    Locale locale = Locale.ENGLISH;
                    String string = getString(R.string.fit_crew_members);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fit_crew_members)");
                    format2 = String.format(locale, string, Arrays.copyOf(new Object[]{"--"}, 1));
                } else {
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    Locale locale2 = Locale.ENGLISH;
                    String string2 = getString(R.string.fit_crew_members);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.fit_crew_members)");
                    format2 = String.format(locale2, string2, Arrays.copyOf(new Object[]{String.valueOf(buddiesChallengeDetail.getTotalBuddies())}, 1));
                }
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                textView2.setText(format2);
                return;
            }
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding11 = this.p;
            if (activityFitnessDetailsBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityFitnessDetailsBinding2 = activityFitnessDetailsBinding11;
            }
            TextView textView3 = activityFitnessDetailsBinding2.tvFitCrew;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvFitCrew");
            gone(textView3);
            return;
        }
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding12 = this.p;
        if (activityFitnessDetailsBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityFitnessDetailsBinding2 = activityFitnessDetailsBinding12;
        }
        TextView textView4 = activityFitnessDetailsBinding2.tvFitCrew;
        if (buddiesChallengeDetail.getTotalBuddies() == null || ((totalBuddies = buddiesChallengeDetail.getTotalBuddies()) != null && totalBuddies.intValue() == 0)) {
            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
            Locale locale3 = Locale.ENGLISH;
            String string3 = getString(R.string.fit_crew_members);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.fit_crew_members)");
            format = String.format(locale3, string3, Arrays.copyOf(new Object[]{"--"}, 1));
        } else {
            StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
            Locale locale4 = Locale.ENGLISH;
            String string4 = getString(R.string.fit_crew_members);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.fit_crew_members)");
            format = String.format(locale4, string4, Arrays.copyOf(new Object[]{String.valueOf(buddiesChallengeDetail.getTotalBuddies())}, 1));
        }
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        textView4.setText(format);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x023c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void w0(com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail r11) {
        /*
            Method dump skipped, instructions count: 640
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.fitnesschallenges.FitnessChallengeDetails.w0(com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail):void");
    }

    public final void x0(BuddiesChallengeDetail buddiesChallengeDetail) {
        ImageView imageView;
        int i;
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding = this.p;
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding2 = null;
        if (activityFitnessDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding = null;
        }
        Button button = activityFitnessDetailsBinding.btnAddParticipants;
        Intrinsics.checkNotNullExpressionValue(button, "binding.btnAddParticipants");
        gone(button);
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding3 = this.p;
        if (activityFitnessDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding3 = null;
        }
        Button button2 = activityFitnessDetailsBinding3.btnJoinChallenge;
        Intrinsics.checkNotNullExpressionValue(button2, "binding.btnJoinChallenge");
        gone(button2);
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding4 = this.p;
        if (activityFitnessDetailsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding4 = null;
        }
        Button button3 = activityFitnessDetailsBinding4.btnLeaveChallenge;
        Intrinsics.checkNotNullExpressionValue(button3, "binding.btnLeaveChallenge");
        gone(button3);
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding5 = this.p;
        if (activityFitnessDetailsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding5 = null;
        }
        View root = activityFitnessDetailsBinding5.infoDetails.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.infoDetails.root");
        gone(root);
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding6 = this.p;
        if (activityFitnessDetailsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding6 = null;
        }
        ImageView imageView2 = activityFitnessDetailsBinding6.ivEdit;
        Intrinsics.checkNotNullExpressionValue(imageView2, "binding.ivEdit");
        gone(imageView2);
        if (this.L) {
            imageView = (ImageView) _$_findCachedViewById(R.id.ivShare);
            i = R.drawable.ic_disable_share;
        } else {
            imageView = (ImageView) _$_findCachedViewById(R.id.ivShare);
            i = R.drawable.ic_share_with_circle_bg;
        }
        imageView.setImageDrawable(getDrawable(i));
        if (buddiesChallengeDetail.getChallengeType().equals(FitnessChallengeConstants.GLOBAL) && buddiesChallengeDetail.getStatus().equals(FitnessChallengeConstants.JOINED)) {
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding7 = this.p;
            if (activityFitnessDetailsBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFitnessDetailsBinding7 = null;
            }
            ImageView imageView3 = activityFitnessDetailsBinding7.ivShare;
            Intrinsics.checkNotNullExpressionValue(imageView3, "binding.ivShare");
            visible(imageView3);
        } else {
            if (buddiesChallengeDetail.getShareable() != null) {
                Boolean shareable = buddiesChallengeDetail.getShareable();
                Intrinsics.checkNotNullExpressionValue(shareable, "buddiesChallengeDetail.shareable");
                if (shareable.booleanValue()) {
                    ActivityFitnessDetailsBinding activityFitnessDetailsBinding8 = this.p;
                    if (activityFitnessDetailsBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityFitnessDetailsBinding8 = null;
                    }
                    ImageView imageView4 = activityFitnessDetailsBinding8.ivShare;
                    Intrinsics.checkNotNullExpressionValue(imageView4, "binding.ivShare");
                    visible(imageView4);
                }
            }
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding9 = this.p;
            if (activityFitnessDetailsBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFitnessDetailsBinding9 = null;
            }
            ImageView imageView5 = activityFitnessDetailsBinding9.ivShare;
            Intrinsics.checkNotNullExpressionValue(imageView5, "binding.ivShare");
            gone(imageView5);
        }
        if (buddiesChallengeDetail.getStatus().equals(FitnessChallengeConstants.LEFT)) {
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding10 = this.p;
            if (activityFitnessDetailsBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFitnessDetailsBinding10 = null;
            }
            TextView textView = activityFitnessDetailsBinding10.tvDaysLeft;
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.challenge_left_on));
            sb.append(' ');
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            String leftDate = buddiesChallengeDetail.getLeftDate();
            Intrinsics.checkNotNullExpressionValue(leftDate, "buddiesChallengeDetail.leftDate");
            sb.append(themesUtils.formatDateWithSuffix(leftDate));
            textView.setText(sb.toString());
        }
        if (buddiesChallengeDetail.getStatus().equals(FitnessChallengeConstants.ENDED)) {
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding11 = this.p;
            if (activityFitnessDetailsBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityFitnessDetailsBinding2 = activityFitnessDetailsBinding11;
            }
            TextView textView2 = activityFitnessDetailsBinding2.tvLeaderboard;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvLeaderboard");
            gone(textView2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void y0(com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail r7) {
        /*
            r6 = this;
            com.coveiot.android.theme.utils.ThemesUtils r0 = com.coveiot.android.theme.utils.ThemesUtils.INSTANCE
            java.lang.String r1 = r7.getStartDate()
            java.lang.String r2 = "buddiesChallengeDetail.startDate"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r2 = "yyyy-MM-dd"
            java.util.Date r0 = r0.getDateFromString(r1, r2)
            java.lang.Boolean r1 = r7.getShareable()
            java.lang.String r2 = "binding.ivShare"
            r3 = 0
            java.lang.String r4 = "binding"
            if (r1 == 0) goto L3c
            java.lang.Boolean r1 = r7.getShareable()
            java.lang.String r5 = "buddiesChallengeDetail.shareable"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r5)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L3c
            com.coveiot.android.fitnesschallenges.databinding.ActivityFitnessDetailsBinding r1 = r6.p
            if (r1 != 0) goto L33
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r1 = r3
        L33:
            android.widget.ImageView r1 = r1.ivShare
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r6.visible(r1)
            goto L4c
        L3c:
            com.coveiot.android.fitnesschallenges.databinding.ActivityFitnessDetailsBinding r1 = r6.p
            if (r1 != 0) goto L44
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r1 = r3
        L44:
            android.widget.ImageView r1 = r1.ivShare
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r6.gone(r1)
        L4c:
            java.lang.String r1 = "binding.ivEdit"
            if (r0 == 0) goto L5e
            java.util.Calendar r2 = java.util.Calendar.getInstance()
            java.util.Date r2 = r2.getTime()
            boolean r0 = r0.after(r2)
            if (r0 != 0) goto L72
        L5e:
            java.lang.Integer r0 = r7.getTotalParticipants()
            if (r0 == 0) goto L92
            java.lang.Integer r7 = r7.getTotalParticipants()
            r0 = 1
            if (r7 != 0) goto L6c
            goto L92
        L6c:
            int r7 = r7.intValue()
            if (r7 != r0) goto L92
        L72:
            com.coveiot.android.fitnesschallenges.databinding.ActivityFitnessDetailsBinding r7 = r6.p
            if (r7 != 0) goto L7a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r7 = r3
        L7a:
            android.widget.ImageView r7 = r7.ivEdit
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r1)
            r6.visible(r7)
            com.coveiot.android.fitnesschallenges.databinding.ActivityFitnessDetailsBinding r7 = r6.p
            if (r7 != 0) goto L8a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r7 = r3
        L8a:
            android.widget.ImageView r7 = r7.ivEdit
            int r0 = com.coveiot.android.fitnesschallenges.R.drawable.ic_option_menu
            r7.setImageResource(r0)
            goto La2
        L92:
            com.coveiot.android.fitnesschallenges.databinding.ActivityFitnessDetailsBinding r7 = r6.p
            if (r7 != 0) goto L9a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r7 = r3
        L9a:
            android.widget.ImageView r7 = r7.ivEdit
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r1)
            r6.gone(r7)
        La2:
            com.coveiot.android.fitnesschallenges.databinding.ActivityFitnessDetailsBinding r7 = r6.p
            if (r7 != 0) goto Laa
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r7 = r3
        Laa:
            android.widget.Button r7 = r7.btnAddParticipants
            java.lang.String r0 = "binding.btnAddParticipants"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
            r6.visible(r7)
            com.coveiot.android.fitnesschallenges.databinding.ActivityFitnessDetailsBinding r7 = r6.p
            if (r7 != 0) goto Lbc
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r7 = r3
        Lbc:
            android.widget.Button r7 = r7.btnLeaveChallenge
            java.lang.String r0 = "binding.btnLeaveChallenge"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
            r6.gone(r7)
            com.coveiot.android.fitnesschallenges.databinding.ActivityFitnessDetailsBinding r7 = r6.p
            if (r7 != 0) goto Lce
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            goto Lcf
        Lce:
            r3 = r7
        Lcf:
            com.coveiot.android.theme.databinding.InfoDetailsBinding r7 = r3.infoDetails
            android.widget.TextView r7 = r7.tvInfoText
            int r0 = com.coveiot.android.fitnesschallenges.R.string.my_challenge_edit_desc
            java.lang.String r0 = r6.getString(r0)
            r7.setText(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.fitnesschallenges.FitnessChallengeDetails.y0(com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail):void");
    }

    public final void z0(BuddiesChallengeDetail buddiesChallengeDetail) {
        ImageView imageView;
        int i;
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding = this.p;
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding2 = null;
        if (activityFitnessDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding = null;
        }
        Button button = activityFitnessDetailsBinding.btnAddParticipants;
        Intrinsics.checkNotNullExpressionValue(button, "binding.btnAddParticipants");
        gone(button);
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding3 = this.p;
        if (activityFitnessDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding3 = null;
        }
        Button button2 = activityFitnessDetailsBinding3.btnJoinChallenge;
        Intrinsics.checkNotNullExpressionValue(button2, "binding.btnJoinChallenge");
        gone(button2);
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding4 = this.p;
        if (activityFitnessDetailsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding4 = null;
        }
        Button button3 = activityFitnessDetailsBinding4.btnLeaveChallenge;
        Intrinsics.checkNotNullExpressionValue(button3, "binding.btnLeaveChallenge");
        visible(button3);
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding5 = this.p;
        if (activityFitnessDetailsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding5 = null;
        }
        ImageView imageView2 = activityFitnessDetailsBinding5.ivEdit;
        Intrinsics.checkNotNullExpressionValue(imageView2, "binding.ivEdit");
        gone(imageView2);
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding6 = this.p;
        if (activityFitnessDetailsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFitnessDetailsBinding6 = null;
        }
        activityFitnessDetailsBinding6.infoDetails.tvInfoText.setText(getString(R.string.to_ensure_rankings_info));
        if (this.L) {
            imageView = (ImageView) _$_findCachedViewById(R.id.ivShare);
            i = R.drawable.ic_disable_share;
        } else {
            imageView = (ImageView) _$_findCachedViewById(R.id.ivShare);
            i = R.drawable.ic_share_with_circle_bg;
        }
        imageView.setImageDrawable(getDrawable(i));
        if (kotlin.text.m.equals(buddiesChallengeDetail.getType(), FitnessChallengeConstants.GLOBAL, true)) {
            ActivityFitnessDetailsBinding activityFitnessDetailsBinding7 = this.p;
            if (activityFitnessDetailsBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityFitnessDetailsBinding2 = activityFitnessDetailsBinding7;
            }
            ImageView imageView3 = activityFitnessDetailsBinding2.ivShare;
            Intrinsics.checkNotNullExpressionValue(imageView3, "binding.ivShare");
            visible(imageView3);
            return;
        }
        if (buddiesChallengeDetail.getShareable() != null) {
            Boolean shareable = buddiesChallengeDetail.getShareable();
            Intrinsics.checkNotNullExpressionValue(shareable, "buddiesChallengeDetail.shareable");
            if (shareable.booleanValue()) {
                ActivityFitnessDetailsBinding activityFitnessDetailsBinding8 = this.p;
                if (activityFitnessDetailsBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityFitnessDetailsBinding2 = activityFitnessDetailsBinding8;
                }
                ImageView imageView4 = activityFitnessDetailsBinding2.ivShare;
                Intrinsics.checkNotNullExpressionValue(imageView4, "binding.ivShare");
                visible(imageView4);
                return;
            }
        }
        ActivityFitnessDetailsBinding activityFitnessDetailsBinding9 = this.p;
        if (activityFitnessDetailsBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityFitnessDetailsBinding2 = activityFitnessDetailsBinding9;
        }
        ImageView imageView5 = activityFitnessDetailsBinding2.ivShare;
        Intrinsics.checkNotNullExpressionValue(imageView5, "binding.ivShare");
        gone(imageView5);
    }
}
