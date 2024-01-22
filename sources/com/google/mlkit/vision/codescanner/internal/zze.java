package com.google.mlkit.vision.codescanner.internal;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstall;
import com.google.android.gms.internal.mlkit_code_scanner.zziv;
import com.google.android.gms.internal.mlkit_code_scanner.zzka;
import com.google.android.gms.internal.mlkit_code_scanner.zzkb;
import com.google.android.gms.internal.mlkit_code_scanner.zzkc;
import com.google.android.gms.internal.mlkit_code_scanner.zzny;
import com.google.android.gms.internal.mlkit_code_scanner.zzoa;
import com.google.android.gms.internal.mlkit_code_scanner.zzob;
import com.google.android.gms.internal.mlkit_code_scanner.zzoj;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes10.dex */
public final class zze implements GmsBarcodeScanner {
    @VisibleForTesting
    public static final AtomicReference l = new AtomicReference();
    public static final Object m = new Object();
    public static boolean n;
    public static final /* synthetic */ int zzb = 0;
    public final Context h;
    public final GmsBarcodeScannerOptions i;
    public final zzny j;
    public final zzoa k;

    public zze(Context context, GmsBarcodeScannerOptions gmsBarcodeScannerOptions) {
        zzoa zza = zzoa.zza(context);
        this.j = zzoj.zzb("play-services-code-scanner");
        this.h = context;
        this.i = gmsBarcodeScannerOptions;
        this.k = zza;
    }

    public static void c(@Nullable Barcode barcode, int i) {
        Pair pair = (Pair) l.getAndSet(null);
        if (pair == null) {
            Log.e("GmsBarcodeScannerImpl", "Scanning task source doesn't exist when setting back result.");
        } else if (barcode != null) {
            ((TaskCompletionSource) pair.first).setResult(barcode);
        } else if (i == 201) {
            ((CancellationTokenSource) pair.second).cancel();
        } else {
            ((TaskCompletionSource) pair.first).setException(new MlKitException("Failed to scan code.", i));
        }
    }

    public final /* synthetic */ Task a(ModuleAvailabilityResponse moduleAvailabilityResponse) throws Exception {
        Task addOnCompleteListener;
        boolean z = false;
        if (moduleAvailabilityResponse.areModulesAvailable()) {
            if (new Intent().setPackage("com.google.android.gms").setAction("com.google.android.gms.mlkit.ACTION_SCAN_BARCODE").resolveActivity(this.h.getApplicationContext().getPackageManager()) != null) {
                z = true;
            }
        }
        synchronized (m) {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final long currentTimeMillis = System.currentTimeMillis();
            if (!z) {
                if (!n) {
                    OptionalModuleUtils.requestDownload(this.h, OptionalModuleUtils.MLKIT_BARCODE_UI);
                    n = true;
                }
                b(200, elapsedRealtime, currentTimeMillis);
                addOnCompleteListener = Tasks.forException(new MlKitException("Waiting for the Barcode UI module to be downloaded.", 200));
            } else {
                AtomicReference atomicReference = l;
                Pair pair = (Pair) atomicReference.getAndSet(null);
                if (pair != null) {
                    ((CancellationTokenSource) pair.second).cancel();
                }
                CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
                TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.getToken());
                atomicReference.set(new Pair(taskCompletionSource, cancellationTokenSource));
                Intent intent = new Intent(this.h, GmsBarcodeScanningDelegateActivity.class);
                intent.putExtra("extra_supported_formats", this.i.zza());
                intent.putExtra("extra_allow_manual_input", this.i.zzc());
                intent.putExtra("extra_enable_auto_zoom", this.i.zzb());
                intent.setFlags(268435456);
                this.h.startActivity(intent);
                addOnCompleteListener = taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: com.google.mlkit.vision.codescanner.internal.zzb
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public final void onComplete(Task task) {
                        int errorCode;
                        zze zzeVar = zze.this;
                        long j = elapsedRealtime;
                        long j2 = currentTimeMillis;
                        if (task.isCanceled()) {
                            errorCode = 201;
                        } else {
                            errorCode = !task.isSuccessful() ? ((MlKitException) Preconditions.checkNotNull((MlKitException) task.getException())).getErrorCode() : 0;
                        }
                        zzeVar.b(errorCode, j, j2);
                    }
                });
            }
        }
        return addOnCompleteListener;
    }

    @VisibleForTesting
    public final void b(int i, long j, long j2) {
        zzka zzkaVar;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long currentTimeMillis = System.currentTimeMillis();
        zzny zznyVar = this.j;
        zzkc zzkcVar = new zzkc();
        zziv zzivVar = new zziv();
        zzivVar.zzd(Integer.valueOf(this.i.zza()));
        zzivVar.zza(Boolean.valueOf(this.i.zzc()));
        zzivVar.zzb(Long.valueOf(elapsedRealtime - j));
        if (i == 0) {
            zzkaVar = zzka.NO_ERROR;
        } else if (i != 207) {
            switch (i) {
                case 200:
                    zzkaVar = zzka.CODE_SCANNER_UNAVAILABLE;
                    break;
                case 201:
                    zzkaVar = zzka.CODE_SCANNER_CANCELLED;
                    break;
                case 202:
                    zzkaVar = zzka.CODE_SCANNER_CAMERA_PERMISSION_NOT_GRANTED;
                    break;
                case 203:
                    zzkaVar = zzka.CODE_SCANNER_APP_NAME_UNAVAILABLE;
                    break;
                case 204:
                    zzkaVar = zzka.CODE_SCANNER_TASK_IN_PROGRESS;
                    break;
                case 205:
                    zzkaVar = zzka.CODE_SCANNER_PIPELINE_INITIALIZATION_ERROR;
                    break;
                default:
                    zzkaVar = zzka.UNKNOWN_ERROR;
                    break;
            }
        } else {
            zzkaVar = zzka.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD;
        }
        zzivVar.zzc(zzkaVar);
        zzkcVar.zzc(zzivVar.zze());
        zznyVar.zzc(zzob.zze(zzkcVar), zzkb.CODE_SCANNER_SCAN_API);
        this.k.zzc(24323, i, j2, currentTimeMillis);
    }

    @Override // com.google.android.gms.common.api.OptionalModuleApi
    public final Feature[] getOptionalFeatures() {
        return new Feature[]{OptionalModuleUtils.FEATURE_MLKIT_BARCODE_UI};
    }

    @Override // com.google.mlkit.vision.codescanner.GmsBarcodeScanner
    public final Task<Barcode> startScan() {
        if (GoogleApiAvailabilityLight.getInstance().getApkVersion(this.h) < 221500000) {
            b(MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD, SystemClock.elapsedRealtime(), System.currentTimeMillis());
            return Tasks.forException(new MlKitException("Code scanner module is not supported on current Google Play Services version, please upgrade.", MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD));
        }
        return ModuleInstall.getClient(this.h).areModulesAvailable(new OptionalModuleApi() { // from class: com.google.mlkit.vision.codescanner.internal.zzc
            @Override // com.google.android.gms.common.api.OptionalModuleApi
            public final Feature[] getOptionalFeatures() {
                int i = zze.zzb;
                return new Feature[]{OptionalModuleUtils.FEATURE_BARCODE};
            }
        }).onSuccessTask(new SuccessContinuation() { // from class: com.google.mlkit.vision.codescanner.internal.zzd
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return zze.this.a((ModuleAvailabilityResponse) obj);
            }
        });
    }
}
