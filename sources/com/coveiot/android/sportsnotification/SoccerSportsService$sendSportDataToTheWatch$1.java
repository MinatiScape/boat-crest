package com.coveiot.android.sportsnotification;

import android.os.Handler;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.android.bleabstract.request.SportsNotificationRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.sportsnotification.SoccerSportsService;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class SoccerSportsService$sendSportDataToTheWatch$1 implements SettingsResultListener {
    public final /* synthetic */ SoccerSportsService h;
    public final /* synthetic */ SportsNotificationRequest i;
    public final /* synthetic */ SoccerSportsService.SportsSettingsResultListener j;
    public final /* synthetic */ ArrayList<DynamicSportsField> k;

    public SoccerSportsService$sendSportDataToTheWatch$1(SoccerSportsService soccerSportsService, SportsNotificationRequest sportsNotificationRequest, SoccerSportsService.SportsSettingsResultListener sportsSettingsResultListener, ArrayList<DynamicSportsField> arrayList) {
        this.h = soccerSportsService;
        this.i = sportsNotificationRequest;
        this.j = sportsSettingsResultListener;
        this.k = arrayList;
    }

    public static final void c(SoccerSportsService this$0, SportsNotificationRequest sportsNotificationRequest, SoccerSportsService.SportsSettingsResultListener listener, ArrayList dynamicSportsFieldList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(sportsNotificationRequest, "$sportsNotificationRequest");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(dynamicSportsFieldList, "$dynamicSportsFieldList");
        this$0.s(sportsNotificationRequest, listener, dynamicSportsFieldList);
    }

    public static final void d(SoccerSportsService this$0, SportsNotificationRequest sportsNotificationRequest, SoccerSportsService.SportsSettingsResultListener listener, ArrayList dynamicSportsFieldList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(sportsNotificationRequest, "$sportsNotificationRequest");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(dynamicSportsFieldList, "$dynamicSportsFieldList");
        this$0.s(sportsNotificationRequest, listener, dynamicSportsFieldList);
    }

    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
    public void onSettingsError(@NotNull BleBaseError error) {
        Handler handler;
        Intrinsics.checkNotNullParameter(error, "error");
        handler = this.h.p;
        if (handler != null) {
            final SoccerSportsService soccerSportsService = this.h;
            final SportsNotificationRequest sportsNotificationRequest = this.i;
            final SoccerSportsService.SportsSettingsResultListener sportsSettingsResultListener = this.j;
            final ArrayList<DynamicSportsField> arrayList = this.k;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.sportsnotification.c
                @Override // java.lang.Runnable
                public final void run() {
                    SoccerSportsService$sendSportDataToTheWatch$1.c(SoccerSportsService.this, sportsNotificationRequest, sportsSettingsResultListener, arrayList);
                }
            }, 500L);
        }
    }

    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
    public void onSettingsResponse(@NotNull BleBaseResponse response) {
        Handler handler;
        Intrinsics.checkNotNullParameter(response, "response");
        handler = this.h.p;
        if (handler != null) {
            final SoccerSportsService soccerSportsService = this.h;
            final SportsNotificationRequest sportsNotificationRequest = this.i;
            final SoccerSportsService.SportsSettingsResultListener sportsSettingsResultListener = this.j;
            final ArrayList<DynamicSportsField> arrayList = this.k;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.sportsnotification.d
                @Override // java.lang.Runnable
                public final void run() {
                    SoccerSportsService$sendSportDataToTheWatch$1.d(SoccerSportsService.this, sportsNotificationRequest, sportsSettingsResultListener, arrayList);
                }
            }, 500L);
        }
    }
}
