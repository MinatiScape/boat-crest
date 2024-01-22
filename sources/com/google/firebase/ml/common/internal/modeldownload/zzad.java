package com.google.firebase.ml.common.internal.modeldownload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzoc;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.modeldownload.FirebaseRemoteModel;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;
@WorkerThread
/* loaded from: classes10.dex */
public final class zzad {

    /* renamed from: a  reason: collision with root package name */
    public static final GmsLogger f11388a = new GmsLogger("RmModelInfoRetriever", "");

    @Nullable
    @WorkerThread
    public static zzaa a(@NonNull zzqf zzqfVar, @NonNull FirebaseRemoteModel firebaseRemoteModel, @NonNull zzw zzwVar) throws FirebaseMLException {
        zzaa a2;
        if (firebaseRemoteModel.isBaseModel()) {
            a2 = f.a(firebaseRemoteModel, zzwVar);
        } else {
            a2 = j.a(zzqfVar, zzqfVar.zzoh(), firebaseRemoteModel, zzwVar);
        }
        if (a2 != null) {
            zzwVar.zza(zzoc.NO_ERROR, false, a2.zzpm(), zzns.zzai.zza.MODEL_INFO_RETRIEVAL_SUCCEEDED);
        }
        return a2;
    }

    @Nullable
    public static HttpsURLConnection b(@Nullable String str, @NonNull zzw zzwVar) throws FirebaseMLException {
        String str2;
        zzoc zzocVar;
        if (str == null) {
            return null;
        }
        try {
            zzai zzaiVar = new zzai(str);
            f11388a.d("RmModelInfoRetriever", str.length() != 0 ? "Checking model URL: ".concat(str) : new String("Checking model URL: "));
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) zzaiVar.openConnection();
            httpsURLConnection.setConnectTimeout(2000);
            httpsURLConnection.connect();
            int responseCode = httpsURLConnection.getResponseCode();
            if (responseCode == 200 || responseCode == 304) {
                return httpsURLConnection;
            }
            if (responseCode == 408) {
                zzocVar = zzoc.TIME_OUT_FETCHING_MODEL_METADATA;
            } else {
                zzocVar = zzoc.MODEL_INFO_DOWNLOAD_UNSUCCESSFUL_HTTP_STATUS;
            }
            zzwVar.a(zzocVar, httpsURLConnection.getResponseCode());
            InputStream errorStream = httpsURLConnection.getErrorStream();
            throw new FirebaseMLException(String.format(Locale.getDefault(), "Failed to connect to Firebase ML console server with HTTP status code: %d and error message: %s", Integer.valueOf(httpsURLConnection.getResponseCode()), errorStream == null ? "" : new String(IOUtils.readInputStreamFully(errorStream))), 13);
        } catch (SocketTimeoutException e) {
            zzwVar.c(zzoc.TIME_OUT_FETCHING_MODEL_METADATA);
            throw new FirebaseMLException("Failed to get model URL due to time out", 13, e);
        } catch (IOException e2) {
            zzoc zzocVar2 = zzoc.MODEL_INFO_DOWNLOAD_CONNECTION_FAILED;
            if (e2 instanceof UnknownHostException) {
                zzocVar2 = zzoc.NO_NETWORK_CONNECTION;
                str2 = "Failed to retrieve model info due to no internet connection.";
            } else {
                str2 = "Failed to get model URL";
            }
            zzwVar.c(zzocVar2);
            throw new FirebaseMLException(str2, 13, e2);
        }
    }
}
