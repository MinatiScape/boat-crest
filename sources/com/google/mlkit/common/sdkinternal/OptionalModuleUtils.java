package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstall;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.mlkit_common.zzar;
import com.google.android.gms.internal.mlkit_common.zzat;
import com.google.android.gms.internal.mlkit_common.zzau;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Tasks;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
@KeepForSdk
/* loaded from: classes10.dex */
public class OptionalModuleUtils {
    @NonNull
    @KeepForSdk
    public static final String BARCODE = "barcode";
    @NonNull
    @KeepForSdk
    public static final String BARCODE_MODULE_ID = "com.google.android.gms.vision.barcode";
    @NonNull
    @KeepForSdk
    public static final String CUSTOM_ICA = "custom_ica";
    @NonNull
    @KeepForSdk
    public static final String CUSTOM_ICA_MODULE_ID = "com.google.android.gms.vision.custom.ica";
    @NonNull
    @KeepForSdk
    public static final String DEPRECATED_DYNAMITE_MODULE_ID = "com.google.android.gms.vision.dynamite";
    @NonNull
    @KeepForSdk
    public static final String DOCSCAN_CROP_MODULE_ID = "com.google.android.gms.mlkit_docscan_crop";
    @NonNull
    @KeepForSdk
    public static final String DOCSCAN_DETECT_MODULE_ID = "com.google.android.gms.mlkit_docscan_detect";
    @NonNull
    @KeepForSdk
    public static final String DOCSCAN_ENHANCE_MODULE_ID = "com.google.android.gms.mlkit_docscan_enhance";
    @NonNull
    @KeepForSdk
    public static final Feature[] EMPTY_FEATURES = new Feature[0];
    @NonNull
    @KeepForSdk
    public static final String FACE = "face";
    @NonNull
    @KeepForSdk
    public static final String FACE_MODULE_ID = "com.google.android.gms.vision.face";
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_BARCODE;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_CUSTOM_ICA;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_DOCSCAN_CROP;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_DOCSCAN_DETECT;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_DOCSCAN_ENHANCE;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_FACE;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_ICA;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_IMAGE_CAPTION;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_IMAGE_QUALITY_AESTHETIC;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_IMAGE_QUALITY_TECHNICAL;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_LANGID;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_MLKIT_BARCODE_UI;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_NLCLASSIFIER;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_OCR;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_OCR_CHINESE;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_OCR_COMMON;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_OCR_DEVANAGARI;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_OCR_JAPANESE;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_OCR_KOREAN;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_SMART_REPLY;
    @NonNull
    @KeepForSdk
    public static final Feature FEATURE_TFLITE_DYNAMITE;
    @NonNull
    @KeepForSdk
    public static final String ICA = "ica";
    @NonNull
    @KeepForSdk
    public static final String ICA_MODULE_ID = "com.google.android.gms.vision.ica";
    @NonNull
    @KeepForSdk
    public static final String IMAGE_CAPTION_MODULE_ID = "com.google.android.gms.mlkit_image_caption";
    @NonNull
    @KeepForSdk
    public static final String IMAGE_QUALITY_AESTHETIC_MODULE_ID = "com.google.android.gms.mlkit_quality_aesthetic";
    @NonNull
    @KeepForSdk
    public static final String IMAGE_QUALITY_TECHNICAL_MODULE_ID = "com.google.android.gms.mlkit_quality_technical";
    @NonNull
    @KeepForSdk
    public static final String LANGID = "langid";
    @NonNull
    @KeepForSdk
    public static final String LANGID_MODULE_ID = "com.google.android.gms.mlkit.langid";
    @NonNull
    @KeepForSdk
    public static final String MLKIT_BARCODE_UI = "barcode_ui";
    @NonNull
    @KeepForSdk
    public static final String NLCLASSIFIER = "nlclassifier";
    @NonNull
    @KeepForSdk
    public static final String NLCLASSIFIER_MODULE_ID = "com.google.android.gms.mlkit.nlclassifier";
    @NonNull
    @KeepForSdk
    public static final String OCR = "ocr";
    @NonNull
    @KeepForSdk
    public static final String OCR_CHINESE_MODULE_ID = "com.google.android.gms.mlkit_ocr_chinese";
    @NonNull
    @KeepForSdk
    public static final String OCR_COMMON_MODULE_ID = "com.google.android.gms.mlkit_ocr_common";
    @NonNull
    @KeepForSdk
    public static final String OCR_DEVANAGARI_MODULE_ID = "com.google.android.gms.mlkit_ocr_devanagari";
    @NonNull
    @KeepForSdk
    public static final String OCR_JAPANESE_MODULE_ID = "com.google.android.gms.mlkit_ocr_japanese";
    @NonNull
    @KeepForSdk
    public static final String OCR_KOREAN_MODULE_ID = "com.google.android.gms.mlkit_ocr_korean";
    @NonNull
    @KeepForSdk
    public static final String OCR_MODULE_ID = "com.google.android.gms.vision.ocr";
    @NonNull
    @KeepForSdk
    public static final String SMART_REPLY = "smart_reply";
    @NonNull
    @KeepForSdk
    public static final String SMART_REPLY_MODULE_ID = "com.google.android.gms.mlkit_smartreply";
    @NonNull
    @KeepForSdk
    public static final String TFLITE_DYNAMITE = "tflite_dynamite";
    @NonNull
    @KeepForSdk
    public static final String TFLITE_DYNAMITE_MODULE_ID = "com.google.android.gms.tflite_dynamite";

    /* renamed from: a  reason: collision with root package name */
    public static final zzau f11592a;
    public static final zzau b;

    static {
        Feature feature = new Feature("vision.barcode", 1L);
        FEATURE_BARCODE = feature;
        Feature feature2 = new Feature("vision.custom.ica", 1L);
        FEATURE_CUSTOM_ICA = feature2;
        Feature feature3 = new Feature("vision.face", 1L);
        FEATURE_FACE = feature3;
        Feature feature4 = new Feature("vision.ica", 1L);
        FEATURE_ICA = feature4;
        Feature feature5 = new Feature("vision.ocr", 1L);
        FEATURE_OCR = feature5;
        FEATURE_OCR_CHINESE = new Feature("mlkit.ocr.chinese", 1L);
        FEATURE_OCR_COMMON = new Feature("mlkit.ocr.common", 1L);
        FEATURE_OCR_DEVANAGARI = new Feature("mlkit.ocr.devanagari", 1L);
        FEATURE_OCR_JAPANESE = new Feature("mlkit.ocr.japanese", 1L);
        FEATURE_OCR_KOREAN = new Feature("mlkit.ocr.korean", 1L);
        Feature feature6 = new Feature("mlkit.langid", 1L);
        FEATURE_LANGID = feature6;
        Feature feature7 = new Feature("mlkit.nlclassifier", 1L);
        FEATURE_NLCLASSIFIER = feature7;
        Feature feature8 = new Feature(TFLITE_DYNAMITE, 1L);
        FEATURE_TFLITE_DYNAMITE = feature8;
        Feature feature9 = new Feature("mlkit.barcode.ui", 1L);
        FEATURE_MLKIT_BARCODE_UI = feature9;
        Feature feature10 = new Feature("mlkit.smartreply", 1L);
        FEATURE_SMART_REPLY = feature10;
        FEATURE_IMAGE_CAPTION = new Feature("mlkit.image.caption", 1L);
        FEATURE_DOCSCAN_DETECT = new Feature("mlkit.docscan.detect", 1L);
        FEATURE_DOCSCAN_CROP = new Feature("mlkit.docscan.crop", 1L);
        FEATURE_DOCSCAN_ENHANCE = new Feature("mlkit.docscan.enhance", 1L);
        FEATURE_IMAGE_QUALITY_AESTHETIC = new Feature("mlkit.quality.aesthetic", 1L);
        FEATURE_IMAGE_QUALITY_TECHNICAL = new Feature("mlkit.quality.technical", 1L);
        zzat zzatVar = new zzat();
        zzatVar.zza(BARCODE, feature);
        zzatVar.zza(CUSTOM_ICA, feature2);
        zzatVar.zza(FACE, feature3);
        zzatVar.zza(ICA, feature4);
        zzatVar.zza(OCR, feature5);
        zzatVar.zza(LANGID, feature6);
        zzatVar.zza(NLCLASSIFIER, feature7);
        zzatVar.zza(TFLITE_DYNAMITE, feature8);
        zzatVar.zza(MLKIT_BARCODE_UI, feature9);
        zzatVar.zza(SMART_REPLY, feature10);
        f11592a = zzatVar.zzb();
        zzat zzatVar2 = new zzat();
        zzatVar2.zza(BARCODE_MODULE_ID, feature);
        zzatVar2.zza(CUSTOM_ICA_MODULE_ID, feature2);
        zzatVar2.zza(FACE_MODULE_ID, feature3);
        zzatVar2.zza(ICA_MODULE_ID, feature4);
        zzatVar2.zza(OCR_MODULE_ID, feature5);
        zzatVar2.zza(LANGID_MODULE_ID, feature6);
        zzatVar2.zza(NLCLASSIFIER_MODULE_ID, feature7);
        zzatVar2.zza(TFLITE_DYNAMITE_MODULE_ID, feature8);
        zzatVar2.zza(SMART_REPLY_MODULE_ID, feature10);
        b = zzatVar2.zzb();
    }

    public static Feature[] a(Map map, List list) {
        Feature[] featureArr = new Feature[list.size()];
        for (int i = 0; i < list.size(); i++) {
            featureArr[i] = (Feature) Preconditions.checkNotNull((Feature) map.get(list.get(i)));
        }
        return featureArr;
    }

    @KeepForSdk
    @WorkerThread
    @Deprecated
    public static boolean areAllRequiredModulesAvailable(@NonNull Context context, @NonNull List<String> list) {
        if (GoogleApiAvailabilityLight.getInstance().getApkVersion(context) >= 221500000) {
            return areAllRequiredModulesAvailable(context, a(b, list));
        }
        try {
            for (String str : list) {
                DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, str);
            }
            return true;
        } catch (DynamiteModule.LoadingException unused) {
            return false;
        }
    }

    @KeepForSdk
    @Deprecated
    public static void requestDownload(@NonNull Context context, @NonNull String str) {
        requestDownload(context, zzar.zzh(str));
    }

    @KeepForSdk
    @Deprecated
    public static void requestDownload(@NonNull Context context, @NonNull List<String> list) {
        if (GoogleApiAvailabilityLight.getInstance().getApkVersion(context) >= 221500000) {
            requestDownload(context, a(f11592a, list));
            return;
        }
        Intent intent = new Intent();
        intent.setClassName("com.google.android.gms", "com.google.android.gms.vision.DependencyBroadcastReceiverProxy");
        intent.setAction("com.google.android.gms.vision.DEPENDENCY");
        intent.putExtra("com.google.android.gms.vision.DEPENDENCIES", TextUtils.join(com.clevertap.android.sdk.Constants.SEPARATOR_COMMA, list));
        intent.putExtra("requester_app_package", context.getApplicationInfo().packageName);
        context.sendBroadcast(intent);
    }

    @KeepForSdk
    @WorkerThread
    public static boolean areAllRequiredModulesAvailable(@NonNull Context context, @NonNull final Feature[] featureArr) {
        try {
            return ((ModuleAvailabilityResponse) Tasks.await(ModuleInstall.getClient(context).areModulesAvailable(new OptionalModuleApi() { // from class: com.google.mlkit.common.sdkinternal.zzo
                @Override // com.google.android.gms.common.api.OptionalModuleApi
                public final Feature[] getOptionalFeatures() {
                    Feature[] featureArr2 = featureArr;
                    Feature[] featureArr3 = OptionalModuleUtils.EMPTY_FEATURES;
                    return featureArr2;
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.google.mlkit.common.sdkinternal.zzp
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    Log.e("OptionalModuleUtils", "Failed to check feature availability", exc);
                }
            }))).areModulesAvailable();
        } catch (InterruptedException | ExecutionException e) {
            Log.e("OptionalModuleUtils", "Failed to complete the task of features availability check", e);
            return false;
        }
    }

    @KeepForSdk
    public static void requestDownload(@NonNull Context context, @NonNull final Feature[] featureArr) {
        ModuleInstall.getClient(context).installModules(ModuleInstallRequest.newBuilder().addApi(new OptionalModuleApi() { // from class: com.google.mlkit.common.sdkinternal.zzq
            @Override // com.google.android.gms.common.api.OptionalModuleApi
            public final Feature[] getOptionalFeatures() {
                Feature[] featureArr2 = featureArr;
                Feature[] featureArr3 = OptionalModuleUtils.EMPTY_FEATURES;
                return featureArr2;
            }
        }).build()).addOnFailureListener(new OnFailureListener() { // from class: com.google.mlkit.common.sdkinternal.zzr
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                Log.e("OptionalModuleUtils", "Failed to request modules install request", exc);
            }
        });
    }
}
