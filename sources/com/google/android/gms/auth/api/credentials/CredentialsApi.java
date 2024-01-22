package com.google.android.gms.auth.api.credentials;

import android.app.PendingIntent;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
@Deprecated
/* loaded from: classes6.dex */
public interface CredentialsApi {
    public static final int ACTIVITY_RESULT_ADD_ACCOUNT = 1000;
    public static final int ACTIVITY_RESULT_NO_HINTS_AVAILABLE = 1002;
    public static final int ACTIVITY_RESULT_OTHER_ACCOUNT = 1001;
    public static final int CREDENTIAL_PICKER_REQUEST_CODE = 2000;

    @NonNull
    PendingResult<Status> delete(@NonNull GoogleApiClient googleApiClient, @NonNull Credential credential);

    @NonNull
    PendingResult<Status> disableAutoSignIn(@NonNull GoogleApiClient googleApiClient);

    @NonNull
    PendingIntent getHintPickerIntent(@NonNull GoogleApiClient googleApiClient, @NonNull HintRequest hintRequest);

    @NonNull
    PendingResult<CredentialRequestResult> request(@NonNull GoogleApiClient googleApiClient, @NonNull CredentialRequest credentialRequest);

    @NonNull
    PendingResult<Status> save(@NonNull GoogleApiClient googleApiClient, @NonNull Credential credential);
}
