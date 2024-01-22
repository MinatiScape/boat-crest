package com.coveiot.android.leonardo.locationbreach;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import androidx.core.content.ContextCompat;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.utils.utility.AppUtils;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class LocationBreachSyncService extends Service {
    @NotNull
    public final String h = "LocationBreachSyncService";

    @DebugMetadata(c = "com.coveiot.android.leonardo.locationbreach.LocationBreachSyncService$onStartCommand$1", f = "LocationBreachSyncService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void a() {
        Notification build;
        NotificationInfo data = BleApiUtils.getData();
        if (data.getAppColor() == 0) {
            BleApiManager.getInstance(this);
            data = BleApiUtils.getData();
            if (data.getAppColor() == 0) {
                BleApiUtils.getMetadata(this);
                data = BleApiUtils.getData();
            }
        }
        if (data.getAppColor() == 0) {
            BleApiManager.getInstance(this);
            data = BleApiUtils.getData();
        }
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(data.getAppLauncherActivity()), 0);
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            Object systemService = getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            StringBuilder sb = new StringBuilder();
            String appName = data.getAppName();
            Intrinsics.checkNotNull(appName);
            sb.append(appName);
            sb.append(" is running");
            NotificationChannel notificationChannel = new NotificationChannel("101", sb.toString(), 2);
            notificationChannel.enableLights(false);
            ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
            Notification.Builder builder = new Notification.Builder(this, "101");
            StringBuilder sb2 = new StringBuilder();
            String appName2 = data.getAppName();
            Intrinsics.checkNotNull(appName2);
            sb2.append(appName2);
            sb2.append(" is running");
            build = builder.setContentTitle(sb2.toString()).setColor(ContextCompat.getColor(this, data.getAppColor())).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this, \"101\")\n   …\n                .build()");
        } else if (i >= 21) {
            Notification.Builder builder2 = new Notification.Builder(this);
            StringBuilder sb3 = new StringBuilder();
            String appName3 = data.getAppName();
            Intrinsics.checkNotNull(appName3);
            sb3.append(appName3);
            sb3.append(" is running");
            build = builder2.setContentTitle(sb3.toString()).setSmallIcon(data.getAppIcon()).setColor(ContextCompat.getColor(this, data.getAppColor())).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this)\n          …                 .build()");
        } else {
            Notification.Builder builder3 = new Notification.Builder(this);
            StringBuilder sb4 = new StringBuilder();
            String appName4 = data.getAppName();
            Intrinsics.checkNotNull(appName4);
            sb4.append(appName4);
            sb4.append(" is running");
            build = builder3.setContentTitle(sb4.toString()).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this)\n          …                 .build()");
        }
        startForeground(101, build);
    }

    @NotNull
    public final String getTAG() {
        return this.h;
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(@NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        a();
        if (AppUtils.isNetConnected(this)) {
            try {
                e.e(GlobalScope.INSTANCE, null, null, new a(null), 3, null);
            } catch (Exception e) {
                e.printStackTrace();
                stopSelf();
            }
        }
        return super.onStartCommand(intent, i, i2);
    }
}
