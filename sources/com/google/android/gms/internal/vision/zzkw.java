package com.google.android.gms.internal.vision;
/* loaded from: classes10.dex */
public final class zzkw implements zzku {

    /* renamed from: a  reason: collision with root package name */
    public static final zzbe<Boolean> f10027a;
    public static final zzbe<Boolean> b;

    static {
        zzbk zzf = new zzbk(zzbb.getContentProviderUri("com.google.android.gms.vision.sdk")).zzf("vision.sdk:");
        zzf.zza("OptionalModule__check_alarm_seconds", 10L);
        zzf.zza("OptionalModule__enable_barcode_optional_module", false);
        f10027a = zzf.zza("OptionalModule__enable_barcode_optional_module_v25", false);
        zzf.zza("OptionalModule__enable_face_optional_module", false);
        zzf.zza("OptionalModule__enable_face_optional_module_v25", true);
        zzf.zza("OptionalModule__enable_ica_optional_module", false);
        b = zzf.zza("OptionalModule__enable_ica_optional_module_v25", false);
        zzf.zza("OptionalModule__enable_ocr_optional_module", false);
        zzf.zza("OptionalModule__enable_ocr_optional_module_v25", false);
        zzf.zza("OptionalModule__enable_old_download_path", true);
        zzf.zza("OptionalModule__enable_optional_module_download_retry", false);
        zzf.zza("OptionalModule__enable_progress_listener_for_optional_module_download", false);
        zzf.zza("OptionalModule__listener_timeout_in_minutes", 5L);
        zzf.zza("OptionalModule__max_download_status_pending_count", 5L);
    }

    @Override // com.google.android.gms.internal.vision.zzku
    public final boolean zzjp() {
        return f10027a.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.vision.zzku
    public final boolean zzjq() {
        return b.get().booleanValue();
    }
}
