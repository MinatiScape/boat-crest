package io.shipbook.shipbooksdk.Events;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.shipbook.shipbooksdk.InnerLog;
import io.shipbook.shipbooksdk.LogManager;
import io.shipbook.shipbooksdk.Models.ConfigEvent;
import io.shipbook.shipbooksdk.Models.Orientation;
import io.shipbook.shipbooksdk.Networking.SessionManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u0006B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\u0007"}, d2 = {"Lio/shipbook/shipbooksdk/Events/EventManager;", "", "", "start", "<init>", "()V", "ComponentCallbacks", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class EventManager {
    public static final EventManager INSTANCE = new EventManager();

    /* renamed from: a  reason: collision with root package name */
    public static final String f14016a = EventManager.class.getSimpleName();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u0012\u0010\u0006\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\f"}, d2 = {"Lio/shipbook/shipbooksdk/Events/EventManager$ComponentCallbacks;", "Landroid/content/ComponentCallbacks2;", "", "onLowMemory", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "", FirebaseAnalytics.Param.LEVEL, "onTrimMemory", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class ComponentCallbacks implements ComponentCallbacks2 {
        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(@Nullable Configuration configuration) {
            Orientation orientation;
            if (configuration == null) {
                return;
            }
            InnerLog innerLog = InnerLog.INSTANCE;
            EventManager eventManager = EventManager.INSTANCE;
            String TAG = EventManager.access$getTAG$p(eventManager);
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            InnerLog.i$default(innerLog, TAG, "configuration changed " + configuration, null, 4, null);
            int i = configuration.orientation;
            if (i == 1) {
                orientation = Orientation.Portrait;
            } else if (i != 2) {
                orientation = Orientation.Undefined;
            } else {
                orientation = Orientation.Landscape;
            }
            ConfigEvent configEvent = new ConfigEvent(orientation, 0, null, null, 14, null);
            String TAG2 = EventManager.access$getTAG$p(eventManager);
            Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
            InnerLog.v$default(innerLog, TAG2, "added config event: " + configEvent, null, 4, null);
            LogManager.INSTANCE.push(configEvent);
        }

        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
            InnerLog innerLog = InnerLog.INSTANCE;
            String TAG = EventManager.access$getTAG$p(EventManager.INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            InnerLog.w$default(innerLog, TAG, "low memory", null, 4, null);
        }

        @Override // android.content.ComponentCallbacks2
        public void onTrimMemory(int i) {
            InnerLog innerLog = InnerLog.INSTANCE;
            String TAG = EventManager.access$getTAG$p(EventManager.INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            InnerLog.w$default(innerLog, TAG, "trim memory on level: " + i, null, 4, null);
        }
    }

    public static final /* synthetic */ String access$getTAG$p(EventManager eventManager) {
        return f14016a;
    }

    public final void start() {
        Resources resources;
        SessionManager sessionManager = SessionManager.INSTANCE;
        Application application = sessionManager.getApplication();
        if (application != null) {
            application.registerActivityLifecycleCallbacks(ActivityEventCallbacks.INSTANCE);
        }
        Application application2 = sessionManager.getApplication();
        if (application2 != null) {
            application2.registerComponentCallbacks(new ComponentCallbacks());
        }
        InnerLog innerLog = InnerLog.INSTANCE;
        String TAG = f14016a;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        StringBuilder sb = new StringBuilder();
        sb.append("Current configuration: ");
        Context appContext = sessionManager.getAppContext();
        sb.append((appContext == null || (resources = appContext.getResources()) == null) ? null : resources.getConfiguration());
        InnerLog.i$default(innerLog, TAG, sb.toString(), null, 4, null);
    }
}
