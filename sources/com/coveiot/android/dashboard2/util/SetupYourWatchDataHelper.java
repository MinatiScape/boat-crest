package com.coveiot.android.dashboard2.util;

import android.content.Context;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.model.SetupYourWatchDataModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class SetupYourWatchDataHelper {
    @NotNull
    public static final SetupYourWatchDataHelper INSTANCE = new SetupYourWatchDataHelper();

    @NotNull
    public final List<SetupYourWatchDataModel> getSetupYourWatchData(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList arrayList = new ArrayList();
        Dashboard2PreferenceManager.Companion companion = Dashboard2PreferenceManager.Companion;
        boolean isUserCheckedWatchfaceStudio = companion.getInstance(context).isUserCheckedWatchfaceStudio();
        if (isDiySupported(context) && !isUserCheckedWatchfaceStudio) {
            SetupYourWatchDataModel setupYourWatchDataModel = new SetupYourWatchDataModel(null, null, null, null, null, null, null, 127, null);
            setupYourWatchDataModel.setOption("WATCH_FACE_STUDIO");
            String string = context.getString(R.string.watch_face_studio);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.watch_face_studio)");
            String upperCase = string.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
            setupYourWatchDataModel.setHeaderTextCenter(upperCase);
            setupYourWatchDataModel.setDrawable(context.getDrawable(R.drawable.ic_dashboard_wf_studio));
            String string2 = context.getString(R.string.try_personalised_watches);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…try_personalised_watches)");
            setupYourWatchDataModel.setInfoText(string2);
            setupYourWatchDataModel.setBackgroundDrawable(context.getDrawable(R.drawable.rounded_gradient_blue_radius_24_background));
            arrayList.add(setupYourWatchDataModel);
        }
        boolean isUserCheckedWatchface = companion.getInstance(context).isUserCheckedWatchface();
        if (!isDiySupported(context) && !isUserCheckedWatchface) {
            SetupYourWatchDataModel setupYourWatchDataModel2 = new SetupYourWatchDataModel(null, null, null, null, null, null, null, 127, null);
            setupYourWatchDataModel2.setOption("WATCH_FACE_CHANGE");
            String string3 = context.getString(R.string.watch_face_caps);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.watch_face_caps)");
            String upperCase2 = string3.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase()");
            setupYourWatchDataModel2.setHeaderTextCenter(upperCase2);
            setupYourWatchDataModel2.setDrawable(context.getDrawable(R.drawable.ic_dashboard_watch_face));
            String string4 = context.getString(R.string.open_watch_faces);
            Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.open_watch_faces)");
            setupYourWatchDataModel2.setInfoText(string4);
            setupYourWatchDataModel2.setBackgroundDrawable(context.getDrawable(R.drawable.rounded_gradient_blue_radius_24_background));
            arrayList.add(setupYourWatchDataModel2);
        }
        if (!companion.getInstance(context).isUserCheckedWatchSettings()) {
            SetupYourWatchDataModel setupYourWatchDataModel3 = new SetupYourWatchDataModel(null, null, null, null, null, null, null, 127, null);
            setupYourWatchDataModel3.setOption("WATCH_SETTINGS");
            String string5 = context.getString(R.string.watch_settings);
            Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.watch_settings)");
            String upperCase3 = string5.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase3, "this as java.lang.String).toUpperCase()");
            setupYourWatchDataModel3.setHeaderTextCenter(upperCase3);
            setupYourWatchDataModel3.setDrawable(context.getDrawable(R.drawable.ic_dashboard_watch_settings));
            String string6 = context.getString(R.string.manage_watch_settings);
            Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.string.manage_watch_settings)");
            setupYourWatchDataModel3.setInfoText(string6);
            setupYourWatchDataModel3.setBackgroundDrawable(context.getDrawable(R.drawable.rounded_gradient_purple_radius_24_background));
            arrayList.add(setupYourWatchDataModel3);
        }
        return arrayList;
    }

    public final boolean isDiySupported(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceModelBean deviceModelBean = SessionManager.getInstance(context).getDeviceModelBean();
        return (deviceModelBean == null || deviceModelBean.getIsDiyWatchFaceSupported() == null || !Intrinsics.areEqual(deviceModelBean.getIsDiyWatchFaceSupported(), Boolean.TRUE)) ? false : true;
    }
}
