package com.google.firebase.ml.common.internal.modeldownload;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzoc;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.modeldownload.FirebaseRemoteModel;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;
@WorkerThread
/* loaded from: classes10.dex */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    public static final GmsLogger f11384a = new GmsLogger("ModelInfoRetriever", "");

    @Nullable
    public static zzaa a(@NonNull zzqf zzqfVar, @NonNull FirebaseApp firebaseApp, @NonNull FirebaseRemoteModel firebaseRemoteModel, @NonNull zzw zzwVar) throws FirebaseMLException {
        JSONObject jSONObject;
        HttpsURLConnection b = zzad.b(b(firebaseApp, firebaseRemoteModel.getModelNameForBackend(), zzwVar), zzwVar);
        if (b == null) {
            return null;
        }
        String headerField = b.getHeaderField(HttpHeaders.CONTENT_LOCATION);
        String headerField2 = b.getHeaderField(HttpHeaders.ETAG);
        GmsLogger gmsLogger = f11384a;
        String valueOf = String.valueOf(headerField);
        gmsLogger.d("ModelInfoRetriever", valueOf.length() != 0 ? "Received download URL: ".concat(valueOf) : new String("Received download URL: "));
        if (headerField == null) {
            return null;
        }
        if (headerField2 != null) {
            firebaseRemoteModel.setModelHash(headerField2);
            try {
                String str = new String(IOUtils.readInputStreamFully(b.getInputStream()));
                if (TextUtils.isEmpty(str)) {
                    str = "{}";
                }
                JSONObject jSONObject2 = new JSONObject(str);
                zzn zznVar = jSONObject2.has("inferenceInfo") ? zzn.AUTOML : zzn.CUSTOM;
                if (zznVar.equals(zzn.AUTOML) && (jSONObject = jSONObject2.getJSONObject("inferenceInfo")) != null) {
                    JSONArray jSONArray = jSONObject.getJSONArray("labels");
                    if (jSONArray != null && jSONArray.length() != 0) {
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            arrayList.add(jSONArray.getString(i));
                        }
                        zza.zza(zzqfVar, firebaseRemoteModel.getUniqueModelNameForPersist(), arrayList);
                    } else {
                        throw new FirebaseMLException("Cannot parse AutoML model's labels from model downloading backend.", 13);
                    }
                }
                return new zzaa(firebaseRemoteModel.getUniqueModelNameForPersist(), Uri.parse(headerField), headerField2, zznVar);
            } catch (IOException | JSONException e) {
                throw new FirebaseMLException("Failed to parse the model backend response message", 13, e);
            }
        }
        zzwVar.zza(zzoc.MODEL_INFO_DOWNLOAD_NO_HASH, false, zzn.UNKNOWN, zzns.zzai.zza.MODEL_INFO_RETRIEVAL_FAILED);
        throw new FirebaseMLException("No hash value for the custom model", 13);
    }

    @Nullable
    @VisibleForTesting
    public static String b(FirebaseApp firebaseApp, String str, @NonNull zzw zzwVar) throws FirebaseMLException {
        String str2;
        String gcmSenderId = firebaseApp.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            FirebaseInstanceId firebaseInstanceId = FirebaseInstanceId.getInstance(firebaseApp);
            if (firebaseInstanceId == null) {
                f11384a.w("ModelInfoRetriever", "Cannot get a valid instance of FirebaseInstanceId. Cannot retrieve model info.");
                return null;
            }
            String id = firebaseInstanceId.getId();
            if (id == null) {
                f11384a.w("ModelInfoRetriever", "Firebase instance id is null. Cannot retrieve model info.");
                return null;
            }
            try {
                String token = firebaseInstanceId.getToken(gcmSenderId, Marker.ANY_MARKER);
                if (token == null) {
                    f11384a.w("ModelInfoRetriever", "Firebase instance token is null. Cannot retrieve model info.");
                    return null;
                }
                return String.format("https://mlkit.googleapis.com/v1beta1/projects/%s/models/%s/versions/active?key=%s&app_instance_id=%s&app_instance_token=%s", firebaseApp.getOptions().getProjectId(), str, firebaseApp.getOptions().getApiKey(), id, token);
            } catch (IOException e) {
                zzoc zzocVar = zzoc.MODEL_INFO_DOWNLOAD_CONNECTION_FAILED;
                if (e instanceof UnknownHostException) {
                    zzocVar = zzoc.NO_NETWORK_CONNECTION;
                    str2 = "Failed to retrieve model info due to no internet connection.";
                } else {
                    str2 = "Failed to get model URL";
                }
                zzwVar.zza(zzocVar, false, zzn.UNKNOWN, zzns.zzai.zza.MODEL_INFO_RETRIEVAL_FAILED);
                throw new FirebaseMLException(str2, 13, e);
            }
        }
        throw new FirebaseMLException("GCM sender id cannot be null in FirebaseOptions. Please configure FirebaseApp properly.", 9);
    }
}
