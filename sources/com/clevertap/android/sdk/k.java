package com.clevertap.android.sdk;

import android.content.Context;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.cryption.CryptUtils;
import com.clevertap.android.sdk.db.DBManager;
import com.clevertap.android.sdk.events.EventMediator;
import com.clevertap.android.sdk.events.EventQueueManager;
import com.clevertap.android.sdk.featureFlags.CTFeatureFlagsFactory;
import com.clevertap.android.sdk.inapp.InAppController;
import com.clevertap.android.sdk.login.LoginController;
import com.clevertap.android.sdk.network.NetworkManager;
import com.clevertap.android.sdk.pushnotification.PushProviders;
import com.clevertap.android.sdk.pushnotification.work.CTWorkManager;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.MainLooperHandler;
import com.clevertap.android.sdk.validation.ValidationResultStack;
import com.clevertap.android.sdk.validation.Validator;
import com.clevertap.android.sdk.variables.CTVariables;
import com.clevertap.android.sdk.variables.Parser;
import com.clevertap.android.sdk.variables.VarCache;
import java.util.concurrent.Callable;
/* loaded from: classes2.dex */
public class k {

    /* loaded from: classes2.dex */
    public class a implements Callable<Void> {
        public final /* synthetic */ CoreState h;
        public final /* synthetic */ ControllerManager i;
        public final /* synthetic */ CleverTapInstanceConfig j;
        public final /* synthetic */ Context k;

        public a(CoreState coreState, ControllerManager controllerManager, CleverTapInstanceConfig cleverTapInstanceConfig, Context context) {
            this.h = coreState;
            this.i = controllerManager;
            this.j = cleverTapInstanceConfig;
            this.k = context;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            if (this.h.getDeviceInfo() == null || this.h.getDeviceInfo().getDeviceID() == null || this.i.getInAppFCManager() != null) {
                return null;
            }
            this.h.getConfig().getLogger().verbose(this.j.getAccountId() + ":async_deviceID", "Initializing InAppFC with device Id = " + this.h.getDeviceInfo().getDeviceID());
            this.i.setInAppFCManager(new InAppFCManager(this.k, this.j, this.h.getDeviceInfo().getDeviceID()));
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Callable<Void> {
        public final /* synthetic */ Context h;
        public final /* synthetic */ ControllerManager i;
        public final /* synthetic */ CleverTapInstanceConfig j;
        public final /* synthetic */ DeviceInfo k;
        public final /* synthetic */ BaseCallbackManager l;
        public final /* synthetic */ AnalyticsManager m;

        public b(Context context, ControllerManager controllerManager, CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo, BaseCallbackManager baseCallbackManager, AnalyticsManager analyticsManager) {
            this.h = context;
            this.i = controllerManager;
            this.j = cleverTapInstanceConfig;
            this.k = deviceInfo;
            this.l = baseCallbackManager;
            this.m = analyticsManager;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            k.c(this.h, this.i, this.j, this.k, this.l, this.m);
            return null;
        }
    }

    public static CoreState b(final Context context, CleverTapInstanceConfig cleverTapInstanceConfig, String str) {
        CoreState coreState = new CoreState(context);
        CoreMetaData coreMetaData = new CoreMetaData();
        coreState.e(coreMetaData);
        Validator validator = new Validator();
        ValidationResultStack validationResultStack = new ValidationResultStack();
        coreState.setValidationResultStack(validationResultStack);
        CTLockManager cTLockManager = new CTLockManager();
        coreState.setCTLockManager(cTLockManager);
        MainLooperHandler mainLooperHandler = new MainLooperHandler();
        coreState.setMainLooperHandler(mainLooperHandler);
        final CleverTapInstanceConfig cleverTapInstanceConfig2 = new CleverTapInstanceConfig(cleverTapInstanceConfig);
        coreState.setConfig(cleverTapInstanceConfig2);
        final DBManager dBManager = new DBManager(cleverTapInstanceConfig2, cTLockManager);
        coreState.f(dBManager);
        final CryptHandler cryptHandler = new CryptHandler(cleverTapInstanceConfig2.getEncryptionLevel(), CryptHandler.EncryptionAlgorithm.AES, cleverTapInstanceConfig2.getAccountId());
        coreState.setCryptHandler(cryptHandler);
        CTExecutorFactory.executors(cleverTapInstanceConfig2).postAsyncSafelyTask().execute("migratingEncryptionLevel", new Callable() { // from class: com.clevertap.android.sdk.j
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void d;
                d = k.d(context, cleverTapInstanceConfig2, cryptHandler, dBManager);
                return d;
            }
        });
        EventMediator eventMediator = new EventMediator(context, cleverTapInstanceConfig2, coreMetaData);
        coreState.setEventMediator(eventMediator);
        LocalDataStore localDataStore = new LocalDataStore(context, cleverTapInstanceConfig2, cryptHandler);
        coreState.setLocalDataStore(localDataStore);
        DeviceInfo deviceInfo = new DeviceInfo(context, cleverTapInstanceConfig2, str, coreMetaData);
        coreState.setDeviceInfo(deviceInfo);
        CTPreferenceCache.getInstance(context, cleverTapInstanceConfig2);
        CallbackManager callbackManager = new CallbackManager(cleverTapInstanceConfig2, deviceInfo);
        coreState.d(callbackManager);
        SessionManager sessionManager = new SessionManager(cleverTapInstanceConfig2, coreMetaData, validator, localDataStore);
        coreState.setSessionManager(sessionManager);
        ControllerManager controllerManager = new ControllerManager(context, cleverTapInstanceConfig2, cTLockManager, callbackManager, deviceInfo, dBManager);
        coreState.setControllerManager(controllerManager);
        CTExecutorFactory.executors(cleverTapInstanceConfig2).ioTask().execute("initFCManager", new a(coreState, controllerManager, cleverTapInstanceConfig2, context));
        NetworkManager networkManager = new NetworkManager(context, cleverTapInstanceConfig2, deviceInfo, coreMetaData, validationResultStack, controllerManager, dBManager, callbackManager, cTLockManager, validator, localDataStore);
        coreState.h(networkManager);
        EventQueueManager eventQueueManager = new EventQueueManager(dBManager, context, cleverTapInstanceConfig2, eventMediator, sessionManager, callbackManager, mainLooperHandler, deviceInfo, validationResultStack, networkManager, coreMetaData, cTLockManager, localDataStore, controllerManager, cryptHandler);
        coreState.c(eventQueueManager);
        AnalyticsManager analyticsManager = new AnalyticsManager(context, cleverTapInstanceConfig2, eventQueueManager, validator, validationResultStack, coreMetaData, localDataStore, deviceInfo, callbackManager, controllerManager, cTLockManager);
        coreState.setAnalyticsManager(analyticsManager);
        InAppController inAppController = new InAppController(context, cleverTapInstanceConfig2, mainLooperHandler, controllerManager, callbackManager, analyticsManager, coreMetaData, deviceInfo);
        coreState.setInAppController(inAppController);
        coreState.getControllerManager().setInAppController(inAppController);
        CTExecutorFactory.executors(cleverTapInstanceConfig2).ioTask().execute("initFeatureFlags", new b(context, controllerManager, cleverTapInstanceConfig2, deviceInfo, callbackManager, analyticsManager));
        coreState.g(new n(context, cleverTapInstanceConfig2, coreMetaData, eventQueueManager));
        PushProviders load = PushProviders.load(context, cleverTapInstanceConfig2, dBManager, validationResultStack, analyticsManager, controllerManager, new CTWorkManager(context, cleverTapInstanceConfig2));
        coreState.setPushProviders(load);
        coreState.setActivityLifeCycleManager(new com.clevertap.android.sdk.a(context, cleverTapInstanceConfig2, analyticsManager, coreMetaData, sessionManager, load, callbackManager, inAppController, eventQueueManager));
        coreState.setLoginController(new LoginController(context, cleverTapInstanceConfig2, deviceInfo, validationResultStack, eventQueueManager, analyticsManager, coreMetaData, controllerManager, sessionManager, localDataStore, callbackManager, dBManager, cTLockManager, cryptHandler));
        VarCache varCache = new VarCache(cleverTapInstanceConfig2, context);
        coreState.setVarCache(varCache);
        CTVariables cTVariables = new CTVariables(varCache);
        coreState.setCTVariables(cTVariables);
        coreState.getControllerManager().setCtVariables(cTVariables);
        coreState.setParser(new Parser(cTVariables));
        cTVariables.init();
        return coreState;
    }

    public static void c(Context context, ControllerManager controllerManager, CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo, BaseCallbackManager baseCallbackManager, AnalyticsManager analyticsManager) {
        cleverTapInstanceConfig.getLogger().verbose(cleverTapInstanceConfig.getAccountId() + ":async_deviceID", "Initializing Feature Flags with device Id = " + deviceInfo.getDeviceID());
        if (cleverTapInstanceConfig.isAnalyticsOnly()) {
            cleverTapInstanceConfig.getLogger().debug(cleverTapInstanceConfig.getAccountId(), "Feature Flag is not enabled for this instance");
            return;
        }
        controllerManager.setCTFeatureFlagsController(CTFeatureFlagsFactory.getInstance(context, deviceInfo.getDeviceID(), cleverTapInstanceConfig, baseCallbackManager, analyticsManager));
        cleverTapInstanceConfig.getLogger().verbose(cleverTapInstanceConfig.getAccountId() + ":async_deviceID", "Feature Flags initialized");
    }

    public static /* synthetic */ Void d(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, CryptHandler cryptHandler, DBManager dBManager) throws Exception {
        CryptUtils.migrateEncryptionLevel(context, cleverTapInstanceConfig, cryptHandler, dBManager.loadDBAdapter(context));
        return null;
    }
}
