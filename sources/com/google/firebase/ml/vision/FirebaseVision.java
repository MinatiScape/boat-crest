package com.google.firebase.ml.vision;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzqf;
import com.google.android.gms.internal.firebase_ml.zzqu;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmarkDetector;
import com.google.firebase.ml.vision.document.FirebaseVisionCloudDocumentRecognizerOptions;
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentTextRecognizer;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.google.firebase.ml.vision.label.FirebaseVisionCloudImageLabelerOptions;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceAutoMLImageLabelerOptions;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceImageLabelerOptions;
import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetector;
import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetectorOptions;
import com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
/* loaded from: classes10.dex */
public class FirebaseVision {
    public static final FirebaseVisionCloudDetectorOptions c = new FirebaseVisionCloudDetectorOptions.Builder().build();
    public static final FirebaseVisionFaceDetectorOptions d = new FirebaseVisionFaceDetectorOptions.Builder().build();
    public static final FirebaseVisionBarcodeDetectorOptions e = new FirebaseVisionBarcodeDetectorOptions.Builder().build();
    public static final FirebaseVisionCloudTextRecognizerOptions f = new FirebaseVisionCloudTextRecognizerOptions.Builder().build();
    public static final FirebaseVisionCloudDocumentRecognizerOptions g = new FirebaseVisionCloudDocumentRecognizerOptions.Builder().build();
    public static final FirebaseVisionOnDeviceImageLabelerOptions h = new FirebaseVisionOnDeviceImageLabelerOptions.Builder().build();
    public static final FirebaseVisionCloudImageLabelerOptions i = new FirebaseVisionCloudImageLabelerOptions.Builder().build();
    public static final FirebaseVisionObjectDetectorOptions j = new FirebaseVisionObjectDetectorOptions.Builder().build();

    /* renamed from: a  reason: collision with root package name */
    public final zzqf f11408a;
    public final zzqu b;

    public FirebaseVision(zzqf zzqfVar) {
        this.f11408a = zzqfVar;
        this.b = zzqu.zzb(zzqfVar);
    }

    @NonNull
    public static FirebaseVision getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @NonNull
    public FirebaseVisionDocumentTextRecognizer getCloudDocumentTextRecognizer(@NonNull FirebaseVisionCloudDocumentRecognizerOptions firebaseVisionCloudDocumentRecognizerOptions) {
        return FirebaseVisionDocumentTextRecognizer.zza(this.f11408a, firebaseVisionCloudDocumentRecognizerOptions);
    }

    @NonNull
    public FirebaseVisionImageLabeler getCloudImageLabeler(@NonNull FirebaseVisionCloudImageLabelerOptions firebaseVisionCloudImageLabelerOptions) {
        return FirebaseVisionImageLabeler.zza(this.f11408a, firebaseVisionCloudImageLabelerOptions);
    }

    @NonNull
    public FirebaseVisionTextRecognizer getCloudTextRecognizer() {
        return FirebaseVisionTextRecognizer.zza(this.f11408a, f, false);
    }

    @NonNull
    public FirebaseVisionImageLabeler getOnDeviceAutoMLImageLabeler(@NonNull FirebaseVisionOnDeviceAutoMLImageLabelerOptions firebaseVisionOnDeviceAutoMLImageLabelerOptions) throws FirebaseMLException {
        return FirebaseVisionImageLabeler.zza(this.f11408a, (FirebaseVisionOnDeviceAutoMLImageLabelerOptions) Preconditions.checkNotNull(firebaseVisionOnDeviceAutoMLImageLabelerOptions, "Please provide a valid FirebaseVisionOnDeviceAutoMLImageLabelerOptions"));
    }

    @NonNull
    public FirebaseVisionImageLabeler getOnDeviceImageLabeler(@NonNull FirebaseVisionOnDeviceImageLabelerOptions firebaseVisionOnDeviceImageLabelerOptions) {
        return FirebaseVisionImageLabeler.zza(this.f11408a, (FirebaseVisionOnDeviceImageLabelerOptions) Preconditions.checkNotNull(firebaseVisionOnDeviceImageLabelerOptions, "Please provide a valid FirebaseVisionOnDeviceImageLabelerOptions"));
    }

    @NonNull
    public FirebaseVisionObjectDetector getOnDeviceObjectDetector(@NonNull FirebaseVisionObjectDetectorOptions firebaseVisionObjectDetectorOptions) {
        return FirebaseVisionObjectDetector.zza(this.f11408a, firebaseVisionObjectDetectorOptions);
    }

    @NonNull
    public FirebaseVisionTextRecognizer getOnDeviceTextRecognizer() {
        return FirebaseVisionTextRecognizer.zza(this.f11408a, null, true);
    }

    @NonNull
    public FirebaseVisionBarcodeDetector getVisionBarcodeDetector(@NonNull FirebaseVisionBarcodeDetectorOptions firebaseVisionBarcodeDetectorOptions) {
        return FirebaseVisionBarcodeDetector.zza(this.f11408a, (FirebaseVisionBarcodeDetectorOptions) Preconditions.checkNotNull(firebaseVisionBarcodeDetectorOptions, "Please provide a valid FirebaseVisionBarcodeDetectorOptions"));
    }

    @NonNull
    public FirebaseVisionCloudLandmarkDetector getVisionCloudLandmarkDetector(@NonNull FirebaseVisionCloudDetectorOptions firebaseVisionCloudDetectorOptions) {
        return FirebaseVisionCloudLandmarkDetector.zza(this.f11408a, firebaseVisionCloudDetectorOptions);
    }

    @NonNull
    public FirebaseVisionFaceDetector getVisionFaceDetector(@NonNull FirebaseVisionFaceDetectorOptions firebaseVisionFaceDetectorOptions) {
        Preconditions.checkNotNull(firebaseVisionFaceDetectorOptions, "Please provide a valid FirebaseVisionFaceDetectorOptions");
        return FirebaseVisionFaceDetector.zza(this.f11408a, firebaseVisionFaceDetectorOptions);
    }

    public boolean isStatsCollectionEnabled() {
        return this.b.zzop();
    }

    public void setStatsCollectionEnabled(boolean z) {
        this.b.zzar(z);
    }

    @NonNull
    public static FirebaseVision getInstance(@NonNull FirebaseApp firebaseApp) {
        Preconditions.checkNotNull(firebaseApp, "MlKitContext can not be null");
        return (FirebaseVision) firebaseApp.get(FirebaseVision.class);
    }

    @NonNull
    public FirebaseVisionDocumentTextRecognizer getCloudDocumentTextRecognizer() {
        return FirebaseVisionDocumentTextRecognizer.zza(this.f11408a, g);
    }

    @NonNull
    public FirebaseVisionImageLabeler getCloudImageLabeler() {
        return FirebaseVisionImageLabeler.zza(this.f11408a, i);
    }

    @NonNull
    public FirebaseVisionTextRecognizer getCloudTextRecognizer(@NonNull FirebaseVisionCloudTextRecognizerOptions firebaseVisionCloudTextRecognizerOptions) {
        return FirebaseVisionTextRecognizer.zza(this.f11408a, firebaseVisionCloudTextRecognizerOptions, false);
    }

    @NonNull
    public FirebaseVisionObjectDetector getOnDeviceObjectDetector() {
        return FirebaseVisionObjectDetector.zza(this.f11408a, j);
    }

    @NonNull
    public FirebaseVisionCloudLandmarkDetector getVisionCloudLandmarkDetector() {
        return FirebaseVisionCloudLandmarkDetector.zza(this.f11408a, c);
    }

    @NonNull
    public FirebaseVisionFaceDetector getVisionFaceDetector() {
        return FirebaseVisionFaceDetector.zza(this.f11408a, d);
    }

    @NonNull
    public FirebaseVisionImageLabeler getOnDeviceImageLabeler() {
        return FirebaseVisionImageLabeler.zza(this.f11408a, h);
    }

    @NonNull
    public FirebaseVisionBarcodeDetector getVisionBarcodeDetector() {
        return FirebaseVisionBarcodeDetector.zza(this.f11408a, e);
    }
}
