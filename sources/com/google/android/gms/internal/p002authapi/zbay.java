package com.google.android.gms.internal.p002authapi;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.identity.zbp;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
/* renamed from: com.google.android.gms.internal.auth-api.zbay  reason: invalid package */
/* loaded from: classes6.dex */
public final class zbay extends GoogleApi implements SignInClient {
    public static final Api.ClientKey k;
    public static final Api.AbstractClientBuilder l;
    public static final Api m;
    public final String j;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        k = clientKey;
        d dVar = new d();
        l = dVar;
        m = new Api("Auth.Api.Identity.SignIn.API", dVar, clientKey);
    }

    public zbay(@NonNull Activity activity, @NonNull zbp zbpVar) {
        super(activity, (Api<zbp>) m, zbpVar, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.j = zbbb.zba();
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<BeginSignInResult> beginSignIn(@NonNull BeginSignInRequest beginSignInRequest) {
        Preconditions.checkNotNull(beginSignInRequest);
        BeginSignInRequest.Builder zba = BeginSignInRequest.zba(beginSignInRequest);
        zba.zba(this.j);
        final BeginSignInRequest build = zba.build();
        return doRead(TaskApiCall.builder().setFeatures(zbba.zba).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api.zbap
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zbay zbayVar = zbay.this;
                BeginSignInRequest beginSignInRequest2 = build;
                ((zbai) ((zbaz) obj).getService()).zbc(new e(zbayVar, (TaskCompletionSource) obj2), (BeginSignInRequest) Preconditions.checkNotNull(beginSignInRequest2));
            }
        }).setAutoResolveMissingFeatures(false).setMethodKey(1553).build());
    }

    public final /* synthetic */ void c(GetPhoneNumberHintIntentRequest getPhoneNumberHintIntentRequest, zbaz zbazVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zbai) zbazVar.getService()).zbd(new h(this, taskCompletionSource), getPhoneNumberHintIntentRequest, this.j);
    }

    public final /* synthetic */ void d(zbaz zbazVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zbai) zbazVar.getService()).zbf(new f(this, taskCompletionSource), this.j);
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final String getPhoneNumberFromIntent(@Nullable Intent intent) throws ApiException {
        if (intent != null) {
            Status status = (Status) SafeParcelableSerializer.deserializeFromIntentExtra(intent, NotificationCompat.CATEGORY_STATUS, Status.CREATOR);
            if (status != null) {
                if (status.isSuccess()) {
                    String stringExtra = intent.getStringExtra("phone_number_hint_result");
                    if (stringExtra != null) {
                        return stringExtra;
                    }
                    throw new ApiException(Status.RESULT_INTERNAL_ERROR);
                }
                throw new ApiException(status);
            }
            throw new ApiException(Status.RESULT_CANCELED);
        }
        throw new ApiException(Status.RESULT_INTERNAL_ERROR);
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<PendingIntent> getPhoneNumberHintIntent(@NonNull final GetPhoneNumberHintIntentRequest getPhoneNumberHintIntentRequest) {
        Preconditions.checkNotNull(getPhoneNumberHintIntentRequest);
        return doRead(TaskApiCall.builder().setFeatures(zbba.zbh).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api.zbas
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zbay.this.c(getPhoneNumberHintIntentRequest, (zbaz) obj, (TaskCompletionSource) obj2);
            }
        }).setMethodKey(1653).build());
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final SignInCredential getSignInCredentialFromIntent(@Nullable Intent intent) throws ApiException {
        if (intent != null) {
            Status status = (Status) SafeParcelableSerializer.deserializeFromIntentExtra(intent, NotificationCompat.CATEGORY_STATUS, Status.CREATOR);
            if (status != null) {
                if (status.isSuccess()) {
                    SignInCredential signInCredential = (SignInCredential) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "sign_in_credential", SignInCredential.CREATOR);
                    if (signInCredential != null) {
                        return signInCredential;
                    }
                    throw new ApiException(Status.RESULT_INTERNAL_ERROR);
                }
                throw new ApiException(status);
            }
            throw new ApiException(Status.RESULT_CANCELED);
        }
        throw new ApiException(Status.RESULT_INTERNAL_ERROR);
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<PendingIntent> getSignInIntent(@NonNull GetSignInIntentRequest getSignInIntentRequest) {
        Preconditions.checkNotNull(getSignInIntentRequest);
        GetSignInIntentRequest.Builder zba = GetSignInIntentRequest.zba(getSignInIntentRequest);
        zba.zba(this.j);
        final GetSignInIntentRequest build = zba.build();
        return doRead(TaskApiCall.builder().setFeatures(zbba.zbf).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api.zbaq
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zbay zbayVar = zbay.this;
                GetSignInIntentRequest getSignInIntentRequest2 = build;
                ((zbai) ((zbaz) obj).getService()).zbe(new g(zbayVar, (TaskCompletionSource) obj2), (GetSignInIntentRequest) Preconditions.checkNotNull(getSignInIntentRequest2));
            }
        }).setMethodKey(1555).build());
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<Void> signOut() {
        getApplicationContext().getSharedPreferences("com.google.android.gms.signin", 0).edit().clear().apply();
        for (GoogleApiClient googleApiClient : GoogleApiClient.getAllClients()) {
            googleApiClient.maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
        return doWrite(TaskApiCall.builder().setFeatures(zbba.zbb).run(new RemoteCall() { // from class: com.google.android.gms.internal.auth-api.zbar
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zbay.this.d((zbaz) obj, (TaskCompletionSource) obj2);
            }
        }).setAutoResolveMissingFeatures(false).setMethodKey(1554).build());
    }

    public zbay(@NonNull Context context, @NonNull zbp zbpVar) {
        super(context, m, zbpVar, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.j = zbbb.zba();
    }
}
