package com.clevertap.android.sdk;

import android.content.Context;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.db.BaseDatabaseManager;
import com.clevertap.android.sdk.events.BaseEventQueueManager;
import com.clevertap.android.sdk.events.EventMediator;
import com.clevertap.android.sdk.inapp.InAppController;
import com.clevertap.android.sdk.login.LoginController;
import com.clevertap.android.sdk.network.BaseNetworkManager;
import com.clevertap.android.sdk.product_config.CTProductConfigController;
import com.clevertap.android.sdk.product_config.CTProductConfigFactory;
import com.clevertap.android.sdk.pushnotification.PushProviders;
import com.clevertap.android.sdk.task.MainLooperHandler;
import com.clevertap.android.sdk.validation.ValidationResultStack;
import com.clevertap.android.sdk.variables.CTVariables;
import com.clevertap.android.sdk.variables.Parser;
import com.clevertap.android.sdk.variables.VarCache;
/* loaded from: classes2.dex */
public class CoreState extends m {

    /* renamed from: a  reason: collision with root package name */
    public d f2575a;
    public CleverTapInstanceConfig b;
    public CoreMetaData c;
    public BaseDatabaseManager d;
    public DeviceInfo e;
    public EventMediator f;
    public LocalDataStore g;
    public a h;
    public AnalyticsManager i;
    public BaseEventQueueManager j;
    public CTLockManager k;
    public BaseCallbackManager l;
    public ControllerManager m;
    public InAppController n;
    public LoginController o;
    public SessionManager p;
    public ValidationResultStack q;
    public MainLooperHandler r;
    public BaseNetworkManager s;
    public PushProviders t;
    public VarCache u;
    public Parser v;
    public CryptHandler w;
    public CTVariables x;

    public CoreState(Context context) {
        super(context);
    }

    public d a() {
        return this.f2575a;
    }

    @Deprecated
    public final void b() {
        if (getConfig().isAnalyticsOnly()) {
            getConfig().getLogger().debug(getConfig().getAccountId(), "Product Config is not enabled for this instance");
        } else if (getControllerManager().getCTProductConfigController() == null) {
            getConfig().getLogger().verbose(this.b.getAccountId() + ":async_deviceID", "Initializing Product Config with device Id = " + getDeviceInfo().getDeviceID());
            getControllerManager().setCTProductConfigController(CTProductConfigFactory.getInstance(this.context, getDeviceInfo(), getConfig(), this.i, this.c, this.l));
        }
    }

    public void c(BaseEventQueueManager baseEventQueueManager) {
        this.j = baseEventQueueManager;
    }

    public void d(BaseCallbackManager baseCallbackManager) {
        this.l = baseCallbackManager;
    }

    public void e(CoreMetaData coreMetaData) {
        this.c = coreMetaData;
    }

    public void f(BaseDatabaseManager baseDatabaseManager) {
        this.d = baseDatabaseManager;
    }

    public void g(d dVar) {
        this.f2575a = dVar;
    }

    public a getActivityLifeCycleManager() {
        return this.h;
    }

    public AnalyticsManager getAnalyticsManager() {
        return this.i;
    }

    public BaseEventQueueManager getBaseEventQueueManager() {
        return this.j;
    }

    public CTLockManager getCTLockManager() {
        return this.k;
    }

    public CTVariables getCTVariables() {
        return this.x;
    }

    public BaseCallbackManager getCallbackManager() {
        return this.l;
    }

    public CleverTapInstanceConfig getConfig() {
        return this.b;
    }

    @Override // com.clevertap.android.sdk.m
    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public ControllerManager getControllerManager() {
        return this.m;
    }

    public CoreMetaData getCoreMetaData() {
        return this.c;
    }

    public CryptHandler getCryptHandler() {
        return this.w;
    }

    @Deprecated
    public CTProductConfigController getCtProductConfigController() {
        b();
        return getControllerManager().getCTProductConfigController();
    }

    public BaseDatabaseManager getDatabaseManager() {
        return this.d;
    }

    public DeviceInfo getDeviceInfo() {
        return this.e;
    }

    public EventMediator getEventMediator() {
        return this.f;
    }

    public InAppController getInAppController() {
        return this.n;
    }

    public LocalDataStore getLocalDataStore() {
        return this.g;
    }

    public LoginController getLoginController() {
        return this.o;
    }

    public MainLooperHandler getMainLooperHandler() {
        return this.r;
    }

    public BaseNetworkManager getNetworkManager() {
        return this.s;
    }

    public Parser getParser() {
        return this.v;
    }

    public PushProviders getPushProviders() {
        return this.t;
    }

    public SessionManager getSessionManager() {
        return this.p;
    }

    public ValidationResultStack getValidationResultStack() {
        return this.q;
    }

    public VarCache getVarCache() {
        return this.u;
    }

    public void h(BaseNetworkManager baseNetworkManager) {
        this.s = baseNetworkManager;
    }

    public void setActivityLifeCycleManager(a aVar) {
        this.h = aVar;
    }

    public void setAnalyticsManager(AnalyticsManager analyticsManager) {
        this.i = analyticsManager;
    }

    public void setCTLockManager(CTLockManager cTLockManager) {
        this.k = cTLockManager;
    }

    public void setCTVariables(CTVariables cTVariables) {
        this.x = cTVariables;
    }

    public void setConfig(CleverTapInstanceConfig cleverTapInstanceConfig) {
        this.b = cleverTapInstanceConfig;
    }

    public void setControllerManager(ControllerManager controllerManager) {
        this.m = controllerManager;
    }

    public void setCryptHandler(CryptHandler cryptHandler) {
        this.w = cryptHandler;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.e = deviceInfo;
    }

    public void setEventMediator(EventMediator eventMediator) {
        this.f = eventMediator;
    }

    public void setInAppController(InAppController inAppController) {
        this.n = inAppController;
    }

    public void setLocalDataStore(LocalDataStore localDataStore) {
        this.g = localDataStore;
    }

    public void setLoginController(LoginController loginController) {
        this.o = loginController;
    }

    public void setMainLooperHandler(MainLooperHandler mainLooperHandler) {
        this.r = mainLooperHandler;
    }

    public void setParser(Parser parser) {
        this.v = parser;
    }

    public void setPushProviders(PushProviders pushProviders) {
        this.t = pushProviders;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.p = sessionManager;
    }

    public void setValidationResultStack(ValidationResultStack validationResultStack) {
        this.q = validationResultStack;
    }

    public void setVarCache(VarCache varCache) {
        this.u = varCache;
    }
}
