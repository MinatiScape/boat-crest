package com.mappls.sdk.maps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.mappls.sdk.maps.exceptions.MapplsConfigurationException;
import com.mappls.sdk.maps.module.http.HttpRequestImpl;
import com.mappls.sdk.maps.net.ConnectivityReceiver;
import com.mappls.sdk.maps.session.AuthenticationError;
import com.mappls.sdk.maps.session.IStopSession;
import com.mappls.sdk.maps.session.InitializationListener;
import com.mappls.sdk.maps.session.SessionHelper;
import com.mappls.sdk.maps.storage.FileSource;
import com.mappls.sdk.maps.util.TileServerOptions;
import com.mappls.sdk.maps.utils.ThreadUtils;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.utils.MapplsUtils;
@Keep
@SuppressLint({"StaticFieldLeak"})
@UiThread
/* loaded from: classes11.dex */
public final class Mappls {
    private static Mappls INSTANCE = null;
    private static final String TAG = "Mbgl-Mappls";
    private static ModuleProvider moduleProvider;
    @Nullable
    private String accessToken;
    private Context context;
    private SessionHelper sessionHelper;
    private MapplsStylesHelper stylesHelper;
    @Nullable
    private TileServerOptions tileServerOptions;

    /* loaded from: classes11.dex */
    public class a implements InitializationListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ InitializationListener f12640a;

        /* renamed from: com.mappls.sdk.maps.Mappls$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class C0621a implements IStopSession {
            public C0621a() {
            }

            @Override // com.mappls.sdk.maps.session.IStopSession
            public void onFailure() {
                InitializationListener initializationListener = a.this.f12640a;
                if (initializationListener != null) {
                    initializationListener.onFailure(new AuthenticationError(5, "Something went wrong"), null);
                }
            }

            @Override // com.mappls.sdk.maps.session.IStopSession
            public void onSuccess() {
                Mappls.initialize(a.this.f12640a);
            }
        }

        public a(InitializationListener initializationListener) {
            this.f12640a = initializationListener;
        }

        @Override // com.mappls.sdk.maps.session.InitializationListener
        public void onFailure(AuthenticationError authenticationError, Exception exc) {
            if (authenticationError.errorCode == 409) {
                Mappls.getSessionHelper().deleteNavigationSession(new C0621a());
                return;
            }
            InitializationListener initializationListener = this.f12640a;
            if (initializationListener != null) {
                initializationListener.onFailure(authenticationError, exc);
            }
        }

        @Override // com.mappls.sdk.maps.session.InitializationListener
        public void onSuccess() {
            InitializationListener initializationListener = this.f12640a;
            if (initializationListener != null) {
                initializationListener.onSuccess();
            }
        }
    }

    public Mappls(@NonNull Context context, @Nullable String str) {
        this.context = context;
        this.accessToken = str;
        MapplsAccountManager.getInstance().init(context);
        MapplsUtils.setText(BuildConfig.MAPPLS_SDK_VERSION);
        this.sessionHelper = new SessionHelper(context);
        this.stylesHelper = new MapplsStylesHelper(context);
    }

    @Nullable
    public static String getAccessToken() {
        validateMappls();
        return INSTANCE.accessToken;
    }

    @NonNull
    public static Context getApplicationContext() {
        validateMappls();
        return INSTANCE.context;
    }

    private static AssetManager getAssetManager() {
        return getApplicationContext().getResources().getAssets();
    }

    @NonNull
    @UiThread
    public static synchronized Mappls getInstance(@NonNull Context context) {
        Mappls mappls;
        synchronized (Mappls.class) {
            ThreadUtils.init(context);
            ThreadUtils.checkThread(TAG);
            if (INSTANCE == null) {
                Context applicationContext = context.getApplicationContext();
                FileSource.initializeFileDirsPaths(applicationContext);
                INSTANCE = new Mappls(applicationContext, MapplsAccountManager.getInstance().getMapSDKKey());
                ConnectivityReceiver.instance(applicationContext);
                MapplsAccountManager.getInstance().init(context);
            }
            TileServerOptions tileServerOptions = TileServerOptions.get(WellKnownTileServer.MAPPLS);
            Mappls mappls2 = INSTANCE;
            mappls2.tileServerOptions = tileServerOptions;
            mappls2.accessToken = null;
            FileSource fileSource = FileSource.getInstance(context);
            fileSource.setTileServerOptions(tileServerOptions);
            fileSource.setApiKey(null);
            mappls = INSTANCE;
        }
        return mappls;
    }

    @NonNull
    public static ModuleProvider getModuleProvider() {
        if (moduleProvider == null) {
            moduleProvider = new ModuleProviderImpl();
        }
        return moduleProvider;
    }

    public static SessionHelper getSessionHelper() {
        return INSTANCE.sessionHelper;
    }

    public static MapplsStylesHelper getStyleHelper() {
        return INSTANCE.stylesHelper;
    }

    @Nullable
    public static TileServerOptions getTileServerOptions() {
        validateMappls();
        return INSTANCE.tileServerOptions;
    }

    public static boolean hasInstance() {
        return INSTANCE != null;
    }

    public static void initialize(InitializationListener initializationListener) {
        getSessionHelper().startGlobalSession(new a(initializationListener));
    }

    public static boolean isAccessTokenValid(@Nullable String str) {
        return str != null;
    }

    public static synchronized Boolean isConnected() {
        Boolean valueOf;
        synchronized (Mappls.class) {
            validateMappls();
            valueOf = Boolean.valueOf(ConnectivityReceiver.instance(INSTANCE.context).isConnected());
        }
        return valueOf;
    }

    public static void setAccessToken(String str) {
        validateMappls();
        throwIfAccessTokenInvalid(str);
        INSTANCE.accessToken = str;
        FileSource.getInstance(getApplicationContext()).setApiKey(str);
    }

    public static synchronized void setConnected(Boolean bool) {
        synchronized (Mappls.class) {
            validateMappls();
            ConnectivityReceiver.instance(INSTANCE.context).setConnected(bool);
        }
    }

    public static void setProxy(String str, int i) {
        HttpRequestImpl.setProxy(str, i);
    }

    public static void throwIfAccessTokenInvalid(@Nullable String str) {
        if (isAccessTokenValid(str)) {
            return;
        }
        throw new MapplsConfigurationException("A valid maps sdk key parameter is required when using a Mappls service.Currently provided key is: " + str);
    }

    private static void validateMappls() {
        if (INSTANCE == null) {
            throw new MapplsConfigurationException();
        }
    }
}
