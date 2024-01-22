package com.mappls.sdk.maps.session;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.work.PeriodicWorkRequest;
import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.maps.BuildConfig;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.session.create.MapplsCreateSession;
import com.mappls.sdk.services.api.session.create.model.SessionRequestModel;
import com.mappls.sdk.services.api.session.create.model.SessionResponse;
import com.mappls.sdk.services.api.session.delete.MapplsDeleteSession;
import com.mappls.sdk.services.api.session.endsession.MapplsEndSession;
import com.mappls.sdk.services.api.session.endsession.MapplsEndSessionManager;
import com.mappls.sdk.services.api.session.update.MapplsUpdateSession;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes11.dex */
public class SessionHelper {
    public static int CONNECTION_ERROR = 0;
    public static int PERMANENT_ERROR = 2;
    public static int TEMPORARY_ERROR = 1;

    /* renamed from: a  reason: collision with root package name */
    public Context f12831a;
    public SessionResponse b;
    public long c;

    /* loaded from: classes11.dex */
    public class a implements Callback<SessionResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ InitializationListener f12832a;

        public a(InitializationListener initializationListener) {
            this.f12832a = initializationListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SessionResponse> call, Throwable th) {
            AuthenticationError authenticationError;
            Exception exc;
            SessionHelper.this.b = null;
            if (SessionHelper.getFailureType(th) != SessionHelper.CONNECTION_ERROR && SessionHelper.getFailureType(th) != SessionHelper.TEMPORARY_ERROR) {
                authenticationError = ErrorType.SERVER_ERROR;
                exc = new Exception(th);
            } else {
                authenticationError = ErrorType.NETWORK_ERROR;
                exc = new Exception(th);
            }
            InitializationListener initializationListener = this.f12832a;
            if (initializationListener != null) {
                initializationListener.onFailure(authenticationError, exc);
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {
            if (response.code() != 200 && response.code() != 201) {
                AuthenticationError authenticationError = new AuthenticationError(response.code(), response.headers().get(Constants.KEY_MESSAGE));
                SessionHelper.this.b = null;
                InitializationListener initializationListener = this.f12832a;
                if (initializationListener != null) {
                    initializationListener.onFailure(authenticationError, null);
                    return;
                }
                return;
            }
            SessionHelper.this.b = response.body();
            InitializationListener initializationListener2 = this.f12832a;
            if (initializationListener2 != null) {
                initializationListener2.onSuccess();
            }
            SessionHelper.this.c = System.currentTimeMillis();
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Callback<SessionResponse> {
        public b() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SessionResponse> call, Throwable th) {
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {
            SessionHelper.this.b = response.body();
            SessionHelper.this.c = System.currentTimeMillis();
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Callback<SessionResponse> {
        public c() {
        }

        @Override // retrofit2.Callback
        public void onFailure(@NonNull Call<SessionResponse> call, @NonNull Throwable th) {
            if (call.isCanceled()) {
                return;
            }
            SessionHelper.this.b = null;
        }

        @Override // retrofit2.Callback
        public void onResponse(@NonNull Call<SessionResponse> call, @NonNull Response<SessionResponse> response) {
            SessionHelper.this.b = null;
        }
    }

    /* loaded from: classes11.dex */
    public class d implements OnResponseCallback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ IStopSession f12835a;

        public d(IStopSession iStopSession) {
            this.f12835a = iStopSession;
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        /* renamed from: a */
        public void onSuccess(Void r2) {
            SessionHelper.this.b = null;
            IStopSession iStopSession = this.f12835a;
            if (iStopSession != null) {
                iStopSession.onSuccess();
            }
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public void onError(int i, String str) {
            if (i == 204 || i == 410) {
                SessionHelper.this.b = null;
                IStopSession iStopSession = this.f12835a;
                if (iStopSession != null) {
                    iStopSession.onSuccess();
                }
            } else if (i != 0) {
                SessionHelper.this.b = null;
                IStopSession iStopSession2 = this.f12835a;
                if (iStopSession2 != null) {
                    iStopSession2.onFailure();
                }
            }
        }
    }

    public SessionHelper(Context context) {
        this.f12831a = context;
    }

    public static int getFailureType(Throwable th) {
        if (!(th instanceof UnknownHostException) && !(th instanceof SocketException) && !(th instanceof ProtocolException) && !(th instanceof SSLException)) {
            if (th instanceof InterruptedIOException) {
                return TEMPORARY_ERROR;
            }
            return PERMANENT_ERROR;
        }
        return CONNECTION_ERROR;
    }

    public SessionRequestModel c() {
        SessionRequestModel sessionRequestModel = new SessionRequestModel();
        sessionRequestModel.setDeviceFingerprint(SessionConstants.deviceFingerPrint(this.f12831a));
        sessionRequestModel.setSdkVersion(BuildConfig.MAPPLS_SDK_VERSION);
        sessionRequestModel.setOsName("Android");
        sessionRequestModel.setRequestedTTL(Integer.valueOf((int) TypedValues.Custom.TYPE_INT));
        sessionRequestModel.setDeviceAlias(MapplsAccountManager.getInstance().getDeviceAlias());
        sessionRequestModel.setOsVersion(Build.VERSION.SDK_INT + "");
        sessionRequestModel.setAssociationId(MapplsAccountManager.getInstance().getAssociationId());
        return sessionRequestModel;
    }

    public void deleteNavigationSession(@Nullable IStopSession iStopSession) {
        MapplsEndSessionManager.newInstance(MapplsEndSession.builder().clusterId(MapplsAccountManager.getInstance().getClusterId()).sessionType("global").build()).call(new d(iStopSession));
    }

    public void endSession() {
        SessionResponse sessionResponse = this.b;
        if (sessionResponse == null) {
            return;
        }
        String str = sessionResponse.passportLink;
        SessionRequestModel sessionRequestModel = new SessionRequestModel();
        sessionRequestModel.setDeviceFingerprint(SessionConstants.deviceFingerPrint(this.f12831a));
        sessionRequestModel.setSdkVersion(BuildConfig.VERSION_NAME);
        MapplsDeleteSession.builder().hyperlink(str).build().enqueueCall(new c());
    }

    public boolean isSessionActive() {
        return this.b != null;
    }

    public void startGlobalSession(@Nullable InitializationListener initializationListener) {
        if (this.b != null) {
            if (initializationListener != null) {
                initializationListener.onSuccess();
            }
            if (this.c + PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS < System.currentTimeMillis()) {
                updateGlobalSession(MapplsAccountManager.getInstance().getClusterId());
                return;
            }
            return;
        }
        MapplsCreateSession.builder().clusterId(MapplsAccountManager.getInstance().getClusterId()).sessionType("global").sessionRequest(c()).build().enqueueCall(new a(initializationListener));
    }

    public void updateGlobalSession(String str) {
        SessionResponse sessionResponse = this.b;
        if (sessionResponse == null) {
            return;
        }
        MapplsUpdateSession.builder().clusterId(str).hyperlink(sessionResponse.passportLink).sessionRequest(c()).build().enqueueCall(new b());
    }
}
