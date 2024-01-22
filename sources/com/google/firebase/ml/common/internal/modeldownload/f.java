package com.google.firebase.ml.common.internal.modeldownload;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzoc;
import com.google.common.net.HttpHeaders;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.modeldownload.FirebaseRemoteModel;
import javax.net.ssl.HttpsURLConnection;
@WorkerThread
/* loaded from: classes10.dex */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    public static final GmsLogger f11381a = new GmsLogger("BaseModelInfoRetriever", "");

    @Nullable
    public static zzaa a(@NonNull FirebaseRemoteModel firebaseRemoteModel, @NonNull zzw zzwVar) throws FirebaseMLException {
        HttpsURLConnection b = zzad.b(String.format("https://mlkit.googleapis.com/_i/v1/1p/m?n=%s", firebaseRemoteModel.getModelNameForBackend()), zzwVar);
        if (b == null) {
            return null;
        }
        String headerField = b.getHeaderField(HttpHeaders.CONTENT_LOCATION);
        String headerField2 = b.getHeaderField(HttpHeaders.ETAG);
        GmsLogger gmsLogger = f11381a;
        String valueOf = String.valueOf(headerField);
        gmsLogger.d("BaseModelInfoRetriever", valueOf.length() != 0 ? "Received download URL: ".concat(valueOf) : new String("Received download URL: "));
        if (headerField == null) {
            return null;
        }
        if (headerField2 != null) {
            if (firebaseRemoteModel.baseModelHashMatches(headerField2)) {
                firebaseRemoteModel.setModelHash(headerField2);
                return new zzaa(firebaseRemoteModel.getUniqueModelNameForPersist(), Uri.parse(headerField), headerField2, zzn.BASE);
            }
            throw new FirebaseMLException("Downloaded model hash doesn't match the expected. ", 13);
        }
        zzwVar.zza(zzoc.MODEL_INFO_DOWNLOAD_NO_HASH, false, zzn.BASE, zzns.zzai.zza.MODEL_INFO_RETRIEVAL_FAILED);
        throw new FirebaseMLException("No hash value for the base model", 13);
    }
}
