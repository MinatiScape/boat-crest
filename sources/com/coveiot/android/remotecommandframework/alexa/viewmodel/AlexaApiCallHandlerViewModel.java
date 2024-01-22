package com.coveiot.android.remotecommandframework.alexa.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.remotecommandframework.SingletonHolder;
import com.coveiot.android.remotecommandframework.alexa.model.AccountLinkData;
import com.coveiot.android.remotecommandframework.alexa.model.AppToAppLinkingResponse;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.alexa.CoveAlexaApiManager;
import com.coveiot.coveaccess.alexa.model.EnablementData;
import com.coveiot.coveaccess.alexa.model.request.ActivateAlexaReq;
import com.coveiot.coveaccess.alexa.model.response.ActivateAlexaRes;
import com.coveiot.coveaccess.alexa.model.response.DeactivateAlexaRes;
import com.coveiot.coveaccess.alexa.model.response.GetStatusAlexaRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.AlexaDetails;
import com.coveiot.covepreferences.data.AlexaLocale;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class AlexaApiCallHandlerViewModel extends ViewModel {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5644a;
    @NotNull
    public MutableLiveData<AppToAppLinkingResponse> b;
    @NotNull
    public MutableLiveData<AppToAppLinkingResponse> c;
    @NotNull
    public MutableLiveData<AppToAppLinkingResponse> d;

    /* loaded from: classes6.dex */
    public static final class Companion extends SingletonHolder<AlexaApiCallHandlerViewModel, Context> {

        /* loaded from: classes6.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, AlexaApiCallHandlerViewModel> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, AlexaApiCallHandlerViewModel.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final AlexaApiCallHandlerViewModel invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new AlexaApiCallHandlerViewModel(p0);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AlexaApiCallHandlerViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5644a = context;
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
    }

    public final void callAlexaAccountUnlinkingApi() {
        CoveAlexaApiManager.deactivateAlexaAccountLinking(new CoveApiListener<DeactivateAlexaRes, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.viewmodel.AlexaApiCallHandlerViewModel$callAlexaAccountUnlinkingApi$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                AlexaApiCallHandlerViewModel.this.getAccountUnlinkingResponseLiveData().postValue(new AppToAppLinkingResponse(false, coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null, null, 0, 12, null));
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable DeactivateAlexaRes deactivateAlexaRes) {
                AlexaApiCallHandlerViewModel.this.getAccountUnlinkingResponseLiveData().postValue(new AppToAppLinkingResponse(true, null, null, 0, 12, null));
            }
        });
    }

    public final void callAlexaAppToAppLinkingApiWithAuthCode(@NotNull String authCode) {
        Intrinsics.checkNotNullParameter(authCode, "authCode");
        ActivateAlexaReq activateAlexaReq = new ActivateAlexaReq();
        activateAlexaReq.setAmazonCode(authCode);
        String userDeviceID = PreferenceManager.getInstance().getUserDeviceID();
        Intrinsics.checkNotNullExpressionValue(userDeviceID, "getInstance().userDeviceID");
        activateAlexaReq.setUserDeviceId(Long.valueOf(Long.parseLong(userDeviceID)));
        CoveAlexaApiManager.activateAlexaAccountLinking(activateAlexaReq, new CoveApiListener<ActivateAlexaRes, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.viewmodel.AlexaApiCallHandlerViewModel$callAlexaAppToAppLinkingApiWithAuthCode$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                AlexaApiCallHandlerViewModel.this.getAppToAppLinkingResponseLiveData().postValue(new AppToAppLinkingResponse(false, coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null, null, 0, 12, null));
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable ActivateAlexaRes activateAlexaRes) {
                EnablementData data;
                AccountLinkData accountLinkData;
                String str;
                com.coveiot.android.remotecommandframework.alexa.model.EnablementData enablementData = null;
                if (activateAlexaRes != null && (data = activateAlexaRes.getData()) != null) {
                    Intrinsics.checkNotNullExpressionValue(data, "data");
                    com.coveiot.coveaccess.alexa.model.AccountLinkData accountLink = data.getAccountLink();
                    if (accountLink != null) {
                        Intrinsics.checkNotNullExpressionValue(accountLink, "accountLink");
                        accountLinkData = new AccountLinkData(accountLink.getStatus());
                    } else {
                        accountLinkData = null;
                    }
                    String status = data.getStatus();
                    if (status != null) {
                        Intrinsics.checkNotNullExpressionValue(status, "status");
                        str = status;
                    } else {
                        str = null;
                    }
                    enablementData = new com.coveiot.android.remotecommandframework.alexa.model.EnablementData(accountLinkData, str, null, 0L, 12, null);
                }
                AlexaApiCallHandlerViewModel.this.getAppToAppLinkingResponseLiveData().postValue(new AppToAppLinkingResponse(true, null, enablementData, 0, 8, null));
            }
        });
    }

    public final void callGetAlexaAccountLinkingStatusApi() {
        CoveAlexaApiManager.getStatusAlexaAccountLinking(new CoveApiListener<GetStatusAlexaRes, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.viewmodel.AlexaApiCallHandlerViewModel$callGetAlexaAccountLinkingStatusApi$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                AlexaApiCallHandlerViewModel.this.getAlexaAccountLinkingStatusLiveData().postValue(new AppToAppLinkingResponse(false, coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null, null, coveApiErrorModel != null ? coveApiErrorModel.getCode() : 0));
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetStatusAlexaRes getStatusAlexaRes) {
                EnablementData data;
                AccountLinkData accountLinkData;
                String str;
                com.coveiot.android.remotecommandframework.alexa.model.EnablementData enablementData = null;
                if (getStatusAlexaRes != null && (data = getStatusAlexaRes.getData()) != null) {
                    Intrinsics.checkNotNullExpressionValue(data, "data");
                    com.coveiot.coveaccess.alexa.model.AccountLinkData accountLink = data.getAccountLink();
                    if (accountLink != null) {
                        Intrinsics.checkNotNullExpressionValue(accountLink, "accountLink");
                        accountLinkData = new AccountLinkData(accountLink.getStatus());
                    } else {
                        accountLinkData = null;
                    }
                    String status = data.getStatus();
                    if (status != null) {
                        Intrinsics.checkNotNullExpressionValue(status, "status");
                        str = status;
                    } else {
                        str = null;
                    }
                    String skillEnabledFrom = data.getSkillEnabledFrom();
                    Long userDeviceId = data.getUserDeviceId();
                    Intrinsics.checkNotNullExpressionValue(userDeviceId, "d.userDeviceId");
                    enablementData = new com.coveiot.android.remotecommandframework.alexa.model.EnablementData(accountLinkData, str, skillEnabledFrom, userDeviceId.longValue());
                }
                AlexaApiCallHandlerViewModel.this.getAlexaAccountLinkingStatusLiveData().postValue(new AppToAppLinkingResponse(true, null, enablementData, 0, 8, null));
            }
        });
    }

    @NotNull
    public final MutableLiveData<AppToAppLinkingResponse> getAccountUnlinkingResponseLiveData() {
        return this.c;
    }

    @NotNull
    public final MutableLiveData<AppToAppLinkingResponse> getAlexaAccountLinkingStatusLiveData() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<AppToAppLinkingResponse> getAppToAppLinkingResponseLiveData() {
        return this.b;
    }

    @NotNull
    public final Context getContext() {
        return this.f5644a;
    }

    @Nullable
    public final HashMap<String, List<String>> getLanguages() {
        List<AlexaLocale> locales;
        AlexaDetails alexaDetails = SessionManager.getInstance(this.f5644a).getAlexaDetails();
        HashMap<String, List<String>> hashMap = null;
        if (alexaDetails != null && (locales = alexaDetails.getLocales()) != null) {
            hashMap = new HashMap<>();
            for (AlexaLocale alexaLocale : locales) {
                String label = alexaLocale.getLabel();
                Intrinsics.checkNotNull(label);
                List<String> examplePhrases = alexaLocale.getExamplePhrases();
                Intrinsics.checkNotNull(examplePhrases);
                hashMap.put(label, examplePhrases);
            }
        }
        return hashMap;
    }

    @NotNull
    public final List<String> getUtterances(@NotNull String language, @NotNull HashMap<String, List<String>> utterance) {
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(utterance, "utterance");
        ArrayList arrayList = new ArrayList();
        List<String> list = utterance.get(language);
        if (list != null) {
            arrayList.addAll(list);
        }
        return arrayList;
    }

    public final void setAccountUnlinkingResponseLiveData(@NotNull MutableLiveData<AppToAppLinkingResponse> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setAlexaAccountLinkingStatusLiveData(@NotNull MutableLiveData<AppToAppLinkingResponse> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setAppToAppLinkingResponseLiveData(@NotNull MutableLiveData<AppToAppLinkingResponse> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }
}
