package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.PlaybackStateCompat;
import com.clevertap.android.sdk.Constants;
/* loaded from: classes8.dex */
public final class zzno implements zznn {
    public static final zzhu<Long> zzA;
    public static final zzhu<Long> zzB;
    public static final zzhu<Long> zzC;
    public static final zzhu<Long> zzD;
    public static final zzhu<Long> zzE;
    public static final zzhu<Long> zzF;
    public static final zzhu<Long> zzG;
    public static final zzhu<Long> zzH;
    public static final zzhu<Long> zzI;
    public static final zzhu<Long> zzJ;
    public static final zzhu<String> zzK;
    public static final zzhu<Long> zzL;
    public static final zzhu<Long> zza;
    public static final zzhu<Long> zzb;
    public static final zzhu<Long> zzc;
    public static final zzhu<String> zzd;
    public static final zzhu<String> zze;
    public static final zzhu<String> zzf;
    public static final zzhu<Long> zzg;
    public static final zzhu<Long> zzh;
    public static final zzhu<Long> zzi;
    public static final zzhu<Long> zzj;
    public static final zzhu<Long> zzk;
    public static final zzhu<Long> zzl;
    public static final zzhu<Long> zzm;
    public static final zzhu<Long> zzn;
    public static final zzhu<Long> zzo;
    public static final zzhu<Long> zzp;
    public static final zzhu<Long> zzq;
    public static final zzhu<Long> zzr;
    public static final zzhu<String> zzs;
    public static final zzhu<Long> zzt;
    public static final zzhu<Long> zzu;
    public static final zzhu<Long> zzv;
    public static final zzhu<Long> zzw;
    public static final zzhu<Long> zzx;
    public static final zzhu<Long> zzy;
    public static final zzhu<Long> zzz;

    static {
        zzhr zzhrVar = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhrVar.zzc("measurement.ad_id_cache_time", 10000L);
        zzb = zzhrVar.zzc("measurement.max_bundles_per_iteration", 100L);
        zzc = zzhrVar.zzc("measurement.config.cache_time", 86400000L);
        zzd = zzhrVar.zzd("measurement.log_tag", "FA");
        zze = zzhrVar.zzd("measurement.config.url_authority", "app-measurement.com");
        zzf = zzhrVar.zzd("measurement.config.url_scheme", "https");
        zzg = zzhrVar.zzc("measurement.upload.debug_upload_interval", 1000L);
        zzh = zzhrVar.zzc("measurement.lifetimevalue.max_currency_tracked", 4L);
        zzi = zzhrVar.zzc("measurement.store.max_stored_events_per_app", 100000L);
        zzj = zzhrVar.zzc("measurement.experiment.max_ids", 50L);
        zzk = zzhrVar.zzc("measurement.audience.filter_result_max_count", 200L);
        zzl = zzhrVar.zzc("measurement.alarm_manager.minimum_interval", Constants.ONE_MIN_IN_MILLIS);
        zzm = zzhrVar.zzc("measurement.upload.minimum_delay", 500L);
        zzn = zzhrVar.zzc("measurement.monitoring.sample_period_millis", 86400000L);
        zzo = zzhrVar.zzc("measurement.upload.realtime_upload_interval", 10000L);
        zzp = zzhrVar.zzc("measurement.upload.refresh_blacklisted_config_interval", 604800000L);
        zzq = zzhrVar.zzc("measurement.config.cache_time.service", 3600000L);
        zzr = zzhrVar.zzc("measurement.service_client.idle_disconnect_millis", 5000L);
        zzs = zzhrVar.zzd("measurement.log_tag.service", "FA-SVC");
        zzt = zzhrVar.zzc("measurement.upload.stale_data_deletion_interval", 86400000L);
        zzu = zzhrVar.zzc("measurement.sdk.attribution.cache.ttl", 604800000L);
        zzv = zzhrVar.zzc("measurement.upload.backoff_period", 43200000L);
        zzw = zzhrVar.zzc("measurement.upload.initial_upload_delay_time", 15000L);
        zzx = zzhrVar.zzc("measurement.upload.interval", 3600000L);
        zzy = zzhrVar.zzc("measurement.upload.max_bundle_size", PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
        zzz = zzhrVar.zzc("measurement.upload.max_bundles", 100L);
        zzA = zzhrVar.zzc("measurement.upload.max_conversions_per_day", 500L);
        zzB = zzhrVar.zzc("measurement.upload.max_error_events_per_day", 1000L);
        zzC = zzhrVar.zzc("measurement.upload.max_events_per_bundle", 1000L);
        zzD = zzhrVar.zzc("measurement.upload.max_events_per_day", 100000L);
        zzE = zzhrVar.zzc("measurement.upload.max_public_events_per_day", 50000L);
        zzF = zzhrVar.zzc("measurement.upload.max_queue_time", 2419200000L);
        zzG = zzhrVar.zzc("measurement.upload.max_realtime_events_per_day", 10L);
        zzH = zzhrVar.zzc("measurement.upload.max_batch_size", PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
        zzI = zzhrVar.zzc("measurement.upload.retry_count", 6L);
        zzJ = zzhrVar.zzc("measurement.upload.retry_time", 1800000L);
        zzK = zzhrVar.zzd("measurement.upload.url", "https://app-measurement.com/a");
        zzL = zzhrVar.zzc("measurement.upload.window_interval", 3600000L);
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzA() {
        return zzF.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzB() {
        return zzG.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzC() {
        return zzH.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzD() {
        return zzI.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzE() {
        return zzJ.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzF() {
        return zzL.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final String zzG() {
        return zze.zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final String zzH() {
        return zzf.zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final String zzI() {
        return zzK.zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zza() {
        return zza.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzb() {
        return zzb.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzc() {
        return zzc.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzd() {
        return zzg.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zze() {
        return zzh.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzf() {
        return zzi.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzg() {
        return zzj.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzh() {
        return zzk.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzi() {
        return zzl.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzj() {
        return zzm.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzk() {
        return zzn.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzl() {
        return zzo.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzm() {
        return zzp.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzn() {
        return zzr.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzo() {
        return zzt.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzp() {
        return zzu.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzq() {
        return zzv.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzr() {
        return zzw.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzs() {
        return zzx.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzt() {
        return zzy.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzu() {
        return zzz.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzv() {
        return zzA.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzw() {
        return zzB.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzx() {
        return zzC.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzy() {
        return zzD.zzb().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznn
    public final long zzz() {
        return zzE.zzb().longValue();
    }
}
