package com.coveiot.android.dashboard2.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.model.DoMoreWithYourWatchDataModel;
import com.coveiot.android.dashboard2.model.DoMoreWithYourWatchFeatureType;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import java.util.ArrayList;
import java.util.List;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class DoMoreWithYourWatchHelper {
    @NotNull
    public static final DoMoreWithYourWatchHelper INSTANCE = new DoMoreWithYourWatchHelper();

    @DebugMetadata(c = "com.coveiot.android.dashboard2.util.DoMoreWithYourWatchHelper", f = "DoMoreWithYourWatchHelper.kt", i = {0, 0, 1, 1, 1, 1}, l = {42, 48}, m = "isFitnessOngoingPlanAvailable", n = {"context", "isAvailable", "isAvailable", "curDate", "curDateStr", "planStartDate"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3"})
    /* loaded from: classes4.dex */
    public static final class a extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public Object L$3;
        public int label;
        public /* synthetic */ Object result;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DoMoreWithYourWatchHelper.this.isFitnessOngoingPlanAvailable(null, this);
        }
    }

    @NotNull
    public final List<DoMoreWithYourWatchDataModel> getDefaultDoMoreWithYourWatch(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList arrayList = new ArrayList();
        if (!SessionManager.getInstance(context).isShowIndusInd() && isWellnessCrewSupported(context)) {
            arrayList.add(new DoMoreWithYourWatchDataModel(DoMoreWithYourWatchFeatureType.WELLNESS_CREW_SETUP, true, getDoMoreWithYourWatchTitle(context, "WELLNESS_CREW_SETUP"), getDoMoreWithYourWatchInfoText(context, "WELLNESS_CREW_SETUP")));
        }
        if (isSportScoreSupported(context)) {
            arrayList.add(new DoMoreWithYourWatchDataModel(DoMoreWithYourWatchFeatureType.SPORT_SCORE, true, getDoMoreWithYourWatchTitle(context, "SPORT_SCORE"), getDoMoreWithYourWatchInfoText(context, "SPORT_SCORE")));
        }
        if (isBuildFitnessPlanSupported(context)) {
            arrayList.add(new DoMoreWithYourWatchDataModel(DoMoreWithYourWatchFeatureType.BUILD_FITNESS_PLAN, true, getDoMoreWithYourWatchTitle(context, "BUILD_FITNESS_PLAN"), getDoMoreWithYourWatchInfoText(context, "BUILD_FITNESS_PLAN")));
        }
        if (isSetupAlexaConnectSupported(context)) {
            arrayList.add(new DoMoreWithYourWatchDataModel(DoMoreWithYourWatchFeatureType.SETUP_ALEXA_CONNECT, true, getDoMoreWithYourWatchTitle(context, "SETUP_ALEXA_CONNECT"), getDoMoreWithYourWatchInfoText(context, "SETUP_ALEXA_CONNECT")));
        }
        if (isTapAndPaySupported(context)) {
            arrayList.add(new DoMoreWithYourWatchDataModel(DoMoreWithYourWatchFeatureType.TAP_AND_PAY, true, getDoMoreWithYourWatchTitle(context, "TAP_AND_PAY"), getDoMoreWithYourWatchInfoText(context, "TAP_AND_PAY")));
        }
        if (isSosAlertSupported(context)) {
            arrayList.add(new DoMoreWithYourWatchDataModel(DoMoreWithYourWatchFeatureType.SOS_SETTINGS, true, getDoMoreWithYourWatchTitle(context, "SOS_SETTINGS"), getDoMoreWithYourWatchInfoText(context, "SOS_SETTINGS")));
        }
        return arrayList;
    }

    @Nullable
    public final DeviceModelBean getDeviceModelBean(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return SessionManager.getInstance(context).getDeviceModelBean();
    }

    @Nullable
    public final Drawable getDoMoreWithYourWatchBackgroundDrawable(@NotNull Context context, @NotNull String doMoreWithYourWatchType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchType, "doMoreWithYourWatchType");
        switch (doMoreWithYourWatchType.hashCode()) {
            case -1789021807:
                if (doMoreWithYourWatchType.equals("BUILD_FITNESS_PLAN")) {
                    return context.getDrawable(R.drawable.rounded_top_left_blue_border_background_rectangle);
                }
                break;
            case -1498347065:
                if (doMoreWithYourWatchType.equals("SPORT_SCORE")) {
                    return context.getDrawable(R.drawable.rounded_top_left_red_border_background_rectangle);
                }
                break;
            case -1155766972:
                if (doMoreWithYourWatchType.equals("TAP_AND_PAY")) {
                    return context.getDrawable(R.drawable.rounded_top_left_yellow_border_background_rectangle);
                }
                break;
            case 761971289:
                if (doMoreWithYourWatchType.equals("WELLNESS_CREW_SETUP")) {
                    return context.getDrawable(R.drawable.rounded_top_left_red_border_background_rectangle);
                }
                break;
            case 780751339:
                if (doMoreWithYourWatchType.equals("SOS_SETTINGS")) {
                    return context.getDrawable(R.drawable.rounded_top_left_red_border_background_rectangle);
                }
                break;
            case 1961809932:
                if (doMoreWithYourWatchType.equals("SETUP_ALEXA_CONNECT")) {
                    return context.getDrawable(R.drawable.rounded_top_left_blue_border_background_rectangle);
                }
                break;
        }
        return context.getDrawable(R.drawable.rounded_top_left_red_border_background_rectangle);
    }

    @Nullable
    public final Drawable getDoMoreWithYourWatchDrawable(@NotNull Context context, @NotNull String doMoreWithYourWatchType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchType, "doMoreWithYourWatchType");
        switch (doMoreWithYourWatchType.hashCode()) {
            case -1789021807:
                if (doMoreWithYourWatchType.equals("BUILD_FITNESS_PLAN")) {
                    return context.getDrawable(R.drawable.ic_build_fitness_plan);
                }
                break;
            case -1498347065:
                if (doMoreWithYourWatchType.equals("SPORT_SCORE")) {
                    return context.getDrawable(R.drawable.ic_sport_score);
                }
                break;
            case -1155766972:
                if (doMoreWithYourWatchType.equals("TAP_AND_PAY")) {
                    return context.getDrawable(R.drawable.ic_tap_and_pay_dashboard);
                }
                break;
            case 761971289:
                if (doMoreWithYourWatchType.equals("WELLNESS_CREW_SETUP")) {
                    return context.getDrawable(R.drawable.img_wellness_crew);
                }
                break;
            case 780751339:
                if (doMoreWithYourWatchType.equals("SOS_SETTINGS")) {
                    return context.getDrawable(R.drawable.ic_sos_settings_dashboard);
                }
                break;
            case 1961809932:
                if (doMoreWithYourWatchType.equals("SETUP_ALEXA_CONNECT")) {
                    return context.getDrawable(R.drawable.ic_setup_alexa_connect);
                }
                break;
        }
        return context.getDrawable(R.drawable.img_wellness_crew);
    }

    @Nullable
    public final String getDoMoreWithYourWatchInfoText(@NotNull Context context, @NotNull String doMoreWithYourWatchType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchType, "doMoreWithYourWatchType");
        switch (doMoreWithYourWatchType.hashCode()) {
            case -1789021807:
                return !doMoreWithYourWatchType.equals("BUILD_FITNESS_PLAN") ? "" : context.getString(R.string.fitness_plans_tailored);
            case -1498347065:
                return !doMoreWithYourWatchType.equals("SPORT_SCORE") ? "" : context.getString(R.string.stay_on_top_of_the_game);
            case -1155766972:
                doMoreWithYourWatchType.equals("TAP_AND_PAY");
                return "";
            case 761971289:
                return !doMoreWithYourWatchType.equals("WELLNESS_CREW_SETUP") ? "" : context.getString(R.string.build_share_health_progress);
            case 780751339:
                return !doMoreWithYourWatchType.equals("SOS_SETTINGS") ? "" : context.getString(R.string.emergency_sos_desc);
            case 1961809932:
                return !doMoreWithYourWatchType.equals("SETUP_ALEXA_CONNECT") ? "" : context.getString(R.string.link_your_amazon);
            default:
                return "";
        }
    }

    @Nullable
    public final String getDoMoreWithYourWatchTitle(@NotNull Context context, @NotNull String doMoreWithYourWatchType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchType, "doMoreWithYourWatchType");
        switch (doMoreWithYourWatchType.hashCode()) {
            case -1789021807:
                return !doMoreWithYourWatchType.equals("BUILD_FITNESS_PLAN") ? "" : context.getString(R.string.build_a_fitness_plan);
            case -1498347065:
                return !doMoreWithYourWatchType.equals("SPORT_SCORE") ? "" : context.getString(R.string.sport_scores);
            case -1155766972:
                return !doMoreWithYourWatchType.equals("TAP_AND_PAY") ? "" : context.getString(R.string.tap_and_pay);
            case 761971289:
                return !doMoreWithYourWatchType.equals("WELLNESS_CREW_SETUP") ? "" : context.getString(R.string.wellness_crew_setup);
            case 780751339:
                return !doMoreWithYourWatchType.equals("SOS_SETTINGS") ? "" : context.getString(R.string.emergency_sos);
            case 1961809932:
                return !doMoreWithYourWatchType.equals("SETUP_ALEXA_CONNECT") ? "" : context.getString(R.string.setup_alexa_connect);
            default:
                return "";
        }
    }

    public final boolean isBuildFitnessPlanSupported(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b3  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object isFitnessOngoingPlanAvailable(@org.jetbrains.annotations.NotNull android.content.Context r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r12) {
        /*
            Method dump skipped, instructions count: 227
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dashboard2.util.DoMoreWithYourWatchHelper.isFitnessOngoingPlanAvailable(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean isSetupAlexaConnectSupported(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceModelBean deviceModelBean = getDeviceModelBean(context);
        return (deviceModelBean == null || deviceModelBean.getRemoteFrameworkSupported() == null || !Intrinsics.areEqual(deviceModelBean.getRemoteFrameworkSupported(), Boolean.TRUE)) ? false : true;
    }

    public final boolean isSosAlertSupported(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isSosSupported();
    }

    public final boolean isSportScoreSupported(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return DeviceUtils.Companion.isCricketNotificationSupported(context);
    }

    public final boolean isTapAndPaySupported(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceModelBean deviceModelBean = getDeviceModelBean(context);
        return (deviceModelBean == null || deviceModelBean.isTapAndPaySupported() == null || !Intrinsics.areEqual(deviceModelBean.isTapAndPaySupported(), Boolean.TRUE)) ? false : true;
    }

    public final boolean isWellnessCrewSupported(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return true;
    }
}
