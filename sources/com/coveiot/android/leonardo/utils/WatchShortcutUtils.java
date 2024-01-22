package com.coveiot.android.leonardo.utils;

import android.content.Context;
import com.coveiot.android.boat.R;
import com.coveiot.coveaccess.model.server.ActiveDisplaysEnum;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ShowHideData;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class WatchShortcutUtils {
    @NotNull
    public static final WatchShortcutUtils INSTANCE = new WatchShortcutUtils();

    @NotNull
    public final List<String> watchShortcuts(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        List<ShowHideData> shortcutsList = UserDataManager.getInstance(context).getShortcutsList();
        ArrayList arrayList = new ArrayList();
        if (shortcutsList != null && shortcutsList.size() > 0) {
            for (ShowHideData showHideData : shortcutsList) {
                if (kotlin.text.m.equals(showHideData.getTypeName(), context.getResources().getString(R.string.heart_rate), true)) {
                    String activeDisplayType = ActiveDisplaysEnum.HR.getActiveDisplayType();
                    Intrinsics.checkNotNullExpressionValue(activeDisplayType, "HR.activeDisplayType");
                    arrayList.add(activeDisplayType);
                } else if (kotlin.text.m.equals(showHideData.getTypeName(), context.getResources().getString(R.string.steps), true)) {
                    String activeDisplayType2 = ActiveDisplaysEnum.STEPS.getActiveDisplayType();
                    Intrinsics.checkNotNullExpressionValue(activeDisplayType2, "STEPS.activeDisplayType");
                    arrayList.add(activeDisplayType2);
                } else if (kotlin.text.m.equals(showHideData.getTypeName(), context.getResources().getString(R.string.sleep), true)) {
                    String activeDisplayType3 = ActiveDisplaysEnum.SLEEP.getActiveDisplayType();
                    Intrinsics.checkNotNullExpressionValue(activeDisplayType3, "SLEEP.activeDisplayType");
                    arrayList.add(activeDisplayType3);
                } else if (kotlin.text.m.equals(showHideData.getTypeName(), context.getResources().getString(R.string.spo2), true)) {
                    String activeDisplayType4 = ActiveDisplaysEnum.SPO2.getActiveDisplayType();
                    Intrinsics.checkNotNullExpressionValue(activeDisplayType4, "SPO2.activeDisplayType");
                    arrayList.add(activeDisplayType4);
                } else if (kotlin.text.m.equals(showHideData.getTypeName(), context.getResources().getString(R.string.stress), true)) {
                    String activeDisplayType5 = ActiveDisplaysEnum.STRESS.getActiveDisplayType();
                    Intrinsics.checkNotNullExpressionValue(activeDisplayType5, "STRESS.activeDisplayType");
                    arrayList.add(activeDisplayType5);
                } else if (kotlin.text.m.equals(showHideData.getTypeName(), context.getResources().getString(R.string.sports), true)) {
                    String activeDisplayType6 = ActiveDisplaysEnum.ACTIVITIES.getActiveDisplayType();
                    Intrinsics.checkNotNullExpressionValue(activeDisplayType6, "ACTIVITIES.activeDisplayType");
                    arrayList.add(activeDisplayType6);
                } else if (kotlin.text.m.equals(showHideData.getTypeName(), context.getResources().getString(R.string.weather), true)) {
                    String activeDisplayType7 = ActiveDisplaysEnum.WEATHER.getActiveDisplayType();
                    Intrinsics.checkNotNullExpressionValue(activeDisplayType7, "WEATHER.activeDisplayType");
                    arrayList.add(activeDisplayType7);
                } else if (kotlin.text.m.equals(showHideData.getTypeName(), context.getResources().getString(R.string.music), true)) {
                    String activeDisplayType8 = ActiveDisplaysEnum.MUSIC.getActiveDisplayType();
                    Intrinsics.checkNotNullExpressionValue(activeDisplayType8, "MUSIC.activeDisplayType");
                    arrayList.add(activeDisplayType8);
                }
            }
        }
        return arrayList;
    }
}
