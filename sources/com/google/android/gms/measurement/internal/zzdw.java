package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzha;
import com.google.android.gms.internal.measurement.zzhk;
import com.google.android.gms.internal.measurement.zzna;
import com.google.android.gms.internal.measurement.zznd;
import com.google.android.gms.internal.measurement.zzng;
import com.google.android.gms.internal.measurement.zznj;
import com.google.android.gms.internal.measurement.zznm;
import com.google.android.gms.internal.measurement.zznp;
import com.google.android.gms.internal.measurement.zzns;
import com.google.android.gms.internal.measurement.zznv;
import com.google.android.gms.internal.measurement.zzny;
import com.google.android.gms.internal.measurement.zzob;
import com.google.android.gms.internal.measurement.zzoe;
import com.google.android.gms.internal.measurement.zzoh;
import com.google.android.gms.internal.measurement.zzok;
import com.google.android.gms.internal.measurement.zzon;
import com.google.android.gms.internal.measurement.zzoq;
import com.google.android.gms.internal.measurement.zzot;
import com.google.android.gms.internal.measurement.zzow;
import com.google.android.gms.internal.measurement.zzoz;
import com.google.android.gms.internal.measurement.zzpc;
import com.google.android.gms.internal.measurement.zzpf;
import com.google.android.gms.internal.measurement.zzpi;
import com.google.android.gms.internal.measurement.zzpl;
import com.google.android.gms.internal.measurement.zzpo;
import com.google.android.gms.internal.measurement.zzpr;
import com.google.android.gms.internal.measurement.zzpu;
import com.google.android.gms.internal.measurement.zzpx;
import com.google.android.gms.internal.measurement.zzqa;
import com.google.android.gms.internal.measurement.zzqd;
import com.google.android.gms.internal.measurement.zzqg;
import com.google.android.gms.internal.measurement.zzqj;
import com.google.android.gms.internal.measurement.zzqm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzdw {

    /* renamed from: a */
    public static final List<zzdv<?>> f10145a = Collections.synchronizedList(new ArrayList());
    public static final zzdv<Long> zzA;
    public static final zzdv<Integer> zzB;
    public static final zzdv<Long> zzC;
    public static final zzdv<Integer> zzD;
    public static final zzdv<Integer> zzE;
    public static final zzdv<Integer> zzF;
    public static final zzdv<Integer> zzG;
    public static final zzdv<Integer> zzH;
    public static final zzdv<Long> zzI;
    public static final zzdv<Boolean> zzJ;
    public static final zzdv<String> zzK;
    public static final zzdv<Long> zzL;
    public static final zzdv<Integer> zzM;
    public static final zzdv<Double> zzN;
    public static final zzdv<Integer> zzO;
    public static final zzdv<Integer> zzP;
    public static final zzdv<Long> zzQ;
    public static final zzdv<Boolean> zzR;
    public static final zzdv<Boolean> zzS;
    public static final zzdv<Boolean> zzT;
    public static final zzdv<Boolean> zzU;
    public static final zzdv<Boolean> zzV;
    public static final zzdv<Boolean> zzW;
    public static final zzdv<Boolean> zzX;
    public static final zzdv<Boolean> zzY;
    public static final zzdv<Boolean> zzZ;
    public static final zzdv<Long> zza;
    public static final zzdv<Boolean> zzaA;
    public static final zzdv<Boolean> zzaa;
    public static final zzdv<Boolean> zzab;
    public static final zzdv<Boolean> zzac;
    public static final zzdv<Boolean> zzad;
    public static final zzdv<Boolean> zzae;
    public static final zzdv<Boolean> zzaf;
    public static final zzdv<Boolean> zzag;
    public static final zzdv<Boolean> zzah;
    public static final zzdv<Boolean> zzai;
    public static final zzdv<Boolean> zzaj;
    public static final zzdv<Boolean> zzak;
    public static final zzdv<Boolean> zzal;
    public static final zzdv<Boolean> zzam;
    public static final zzdv<Boolean> zzan;
    public static final zzdv<Integer> zzao;
    public static final zzdv<Boolean> zzap;
    public static final zzdv<Boolean> zzaq;
    public static final zzdv<Boolean> zzar;
    public static final zzdv<Boolean> zzas;
    public static final zzdv<Boolean> zzat;
    public static final zzdv<Boolean> zzau;
    public static final zzdv<Boolean> zzav;
    public static final zzdv<Boolean> zzaw;
    public static final zzdv<Boolean> zzax;
    public static final zzdv<Boolean> zzay;
    public static final zzdv<Boolean> zzaz;
    public static final zzdv<Long> zzb;
    public static final zzdv<Long> zzc;
    public static final zzdv<String> zzd;
    public static final zzdv<String> zze;
    public static final zzdv<Integer> zzf;
    public static final zzdv<Integer> zzg;
    public static final zzdv<Integer> zzh;
    public static final zzdv<Integer> zzi;
    public static final zzdv<Integer> zzj;
    public static final zzdv<Integer> zzk;
    public static final zzdv<Integer> zzl;
    public static final zzdv<Integer> zzm;
    public static final zzdv<Integer> zzn;
    public static final zzdv<Integer> zzo;
    public static final zzdv<String> zzp;
    public static final zzdv<Long> zzq;
    public static final zzdv<Long> zzr;
    public static final zzdv<Long> zzs;
    public static final zzdv<Long> zzt;
    public static final zzdv<Long> zzu;
    public static final zzdv<Long> zzv;
    public static final zzdv<Long> zzw;
    public static final zzdv<Long> zzx;
    public static final zzdv<Long> zzy;
    public static final zzdv<Long> zzz;

    static {
        Collections.synchronizedSet(new HashSet());
        zza = a("measurement.ad_id_cache_time", 10000L, 10000L, new k() { // from class: com.google.android.gms.measurement.internal.zzav
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzb());
            }
        });
        zzb = a("measurement.monitoring.sample_period_millis", 86400000L, 86400000L, new k() { // from class: com.google.android.gms.measurement.internal.zzbg
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzl());
            }
        });
        zzc = a("measurement.config.cache_time", 86400000L, 3600000L, new k() { // from class: com.google.android.gms.measurement.internal.zzay
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzd());
            }
        });
        zzd = a("measurement.config.url_scheme", "https", "https", new k() { // from class: com.google.android.gms.measurement.internal.zzbk
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return zznm.zzJ();
            }
        });
        zze = a("measurement.config.url_authority", "app-measurement.com", "app-measurement.com", new k() { // from class: com.google.android.gms.measurement.internal.zzbw
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return zznm.zzI();
            }
        });
        zzf = a("measurement.upload.max_bundles", 100, 100, new k() { // from class: com.google.android.gms.measurement.internal.zzci
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznm.zzv());
            }
        });
        zzg = a("measurement.upload.max_batch_size", 65536, 65536, new k() { // from class: com.google.android.gms.measurement.internal.zzcu
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznm.zzD());
            }
        });
        zzh = a("measurement.upload.max_bundle_size", 65536, 65536, new k() { // from class: com.google.android.gms.measurement.internal.zzdg
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznm.zzu());
            }
        });
        zzi = a("measurement.upload.max_events_per_bundle", 1000, 1000, new k() { // from class: com.google.android.gms.measurement.internal.zzdn
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznm.zzy());
            }
        });
        zzj = a("measurement.upload.max_events_per_day", 100000, 100000, new k() { // from class: com.google.android.gms.measurement.internal.zzdo
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznm.zzz());
            }
        });
        zzk = a("measurement.upload.max_error_events_per_day", 1000, 1000, new k() { // from class: com.google.android.gms.measurement.internal.zzbr
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznm.zzx());
            }
        });
        zzl = a("measurement.upload.max_public_events_per_day", 50000, 50000, new k() { // from class: com.google.android.gms.measurement.internal.zzcc
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznm.zzA());
            }
        });
        zzm = a("measurement.upload.max_conversions_per_day", 10000, 10000, new k() { // from class: com.google.android.gms.measurement.internal.zzcn
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznm.zzw());
            }
        });
        zzn = a("measurement.upload.max_realtime_events_per_day", 10, 10, new k() { // from class: com.google.android.gms.measurement.internal.zzcy
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznm.zzC());
            }
        });
        zzo = a("measurement.store.max_stored_events_per_app", 100000, 100000, new k() { // from class: com.google.android.gms.measurement.internal.zzdj
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznm.zzg());
            }
        });
        zzp = a("measurement.upload.url", "https://app-measurement.com/a", "https://app-measurement.com/a", new k() { // from class: com.google.android.gms.measurement.internal.zzdp
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return zznm.zzK();
            }
        });
        zzq = a("measurement.upload.backoff_period", 43200000L, 43200000L, new k() { // from class: com.google.android.gms.measurement.internal.zzdq
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzr());
            }
        });
        zzr = a("measurement.upload.window_interval", 3600000L, 3600000L, new k() { // from class: com.google.android.gms.measurement.internal.zzdr
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzG());
            }
        });
        zzs = a("measurement.upload.interval", 3600000L, 3600000L, new k() { // from class: com.google.android.gms.measurement.internal.zzaw
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzt());
            }
        });
        zzt = a("measurement.upload.realtime_upload_interval", 10000L, 10000L, new k() { // from class: com.google.android.gms.measurement.internal.zzax
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzm());
            }
        });
        zzu = a("measurement.upload.debug_upload_interval", 1000L, 1000L, new k() { // from class: com.google.android.gms.measurement.internal.zzaz
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zze());
            }
        });
        zzv = a("measurement.upload.minimum_delay", 500L, 500L, new k() { // from class: com.google.android.gms.measurement.internal.zzba
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzk());
            }
        });
        Long valueOf = Long.valueOf((long) Constants.ONE_MIN_IN_MILLIS);
        zzw = a("measurement.alarm_manager.minimum_interval", valueOf, valueOf, new k() { // from class: com.google.android.gms.measurement.internal.zzbb
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzj());
            }
        });
        zzx = a("measurement.upload.stale_data_deletion_interval", 86400000L, 86400000L, new k() { // from class: com.google.android.gms.measurement.internal.zzbc
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzp());
            }
        });
        zzy = a("measurement.upload.refresh_blacklisted_config_interval", 604800000L, 604800000L, new k() { // from class: com.google.android.gms.measurement.internal.zzbd
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzn());
            }
        });
        zzz = a("measurement.upload.initial_upload_delay_time", 15000L, 15000L, new k() { // from class: com.google.android.gms.measurement.internal.zzbe
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzs());
            }
        });
        zzA = a("measurement.upload.retry_time", 1800000L, 1800000L, new k() { // from class: com.google.android.gms.measurement.internal.zzbf
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzF());
            }
        });
        zzB = a("measurement.upload.retry_count", 6, 6, new k() { // from class: com.google.android.gms.measurement.internal.zzbh
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznm.zzE());
            }
        });
        zzC = a("measurement.upload.max_queue_time", 2419200000L, 2419200000L, new k() { // from class: com.google.android.gms.measurement.internal.zzbi
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzB());
            }
        });
        zzD = a("measurement.lifetimevalue.max_currency_tracked", 4, 4, new k() { // from class: com.google.android.gms.measurement.internal.zzbj
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznm.zzf());
            }
        });
        zzE = a("measurement.audience.filter_result_max_count", 200, 200, new k() { // from class: com.google.android.gms.measurement.internal.zzbl
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznm.zzi());
            }
        });
        zzF = a("measurement.upload.max_public_user_properties", 25, 25, null);
        zzG = a("measurement.upload.max_event_name_cardinality", 500, 500, null);
        zzH = a("measurement.upload.max_public_event_params", 25, 25, null);
        zzI = a("measurement.service_client.idle_disconnect_millis", 5000L, 5000L, new k() { // from class: com.google.android.gms.measurement.internal.zzbm
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzo());
            }
        });
        Boolean bool = Boolean.FALSE;
        zzJ = a("measurement.test.boolean_flag", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzbn
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzpi.zzg());
            }
        });
        zzK = a("measurement.test.string_flag", "---", "---", new k() { // from class: com.google.android.gms.measurement.internal.zzbo
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return zzpi.zzf();
            }
        });
        zzL = a("measurement.test.long_flag", -1L, -1L, new k() { // from class: com.google.android.gms.measurement.internal.zzbp
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zzpi.zzd());
            }
        });
        zzM = a("measurement.test.int_flag", -2, -2, new k() { // from class: com.google.android.gms.measurement.internal.zzbq
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zzpi.zzc());
            }
        });
        Double valueOf2 = Double.valueOf(-3.0d);
        zzN = a("measurement.test.double_flag", valueOf2, valueOf2, new k() { // from class: com.google.android.gms.measurement.internal.zzbs
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Double.valueOf(zzpi.zzb());
            }
        });
        zzO = a("measurement.experiment.max_ids", 50, 50, new k() { // from class: com.google.android.gms.measurement.internal.zzbt
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznm.zzh());
            }
        });
        zzP = a("measurement.max_bundles_per_iteration", 100, 100, new k() { // from class: com.google.android.gms.measurement.internal.zzbu
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznm.zzc());
            }
        });
        zzQ = a("measurement.sdk.attribution.cache.ttl", 604800000L, 604800000L, new k() { // from class: com.google.android.gms.measurement.internal.zzbv
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Long.valueOf(zznm.zzq());
            }
        });
        zzR = a("measurement.validation.internal_limits_internal_event_params", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzbx
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzpc.zzc());
            }
        });
        Boolean bool2 = Boolean.TRUE;
        zzS = a("measurement.collection.firebase_global_collection_flag_enabled", bool2, bool2, new k() { // from class: com.google.android.gms.measurement.internal.zzby
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzon.zzc());
            }
        });
        zzT = a("measurement.collection.redundant_engagement_removal_enabled", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzbz
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzok.zzc());
            }
        });
        zzU = a("measurement.collection.log_event_and_bundle_v2", bool2, bool2, new k() { // from class: com.google.android.gms.measurement.internal.zzca
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzpo.zzc());
            }
        });
        zzV = a("measurement.quality.checksum", bool, bool, null);
        zzW = a("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzcb
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzoe.zze());
            }
        });
        zzX = a("measurement.audience.refresh_event_count_filters_timestamp", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzcd
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzoe.zzd());
            }
        });
        zzY = a("measurement.audience.use_bundle_timestamp_for_event_count_filters", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzce
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzoe.zzf());
            }
        });
        zzZ = a("measurement.sdk.collection.retrieve_deeplink_from_bow_2", bool2, bool2, new k() { // from class: com.google.android.gms.measurement.internal.zzcf
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzqg.zzc());
            }
        });
        zzaa = a("measurement.sdk.collection.last_deep_link_referrer_campaign2", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzcg
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzot.zzd());
            }
        });
        zzab = a("measurement.sdk.collection.enable_extend_user_property_size", bool2, bool2, new k() { // from class: com.google.android.gms.measurement.internal.zzch
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzot.zzc());
            }
        });
        zzac = a("measurement.upload.file_lock_state_check", bool2, bool2, new k() { // from class: com.google.android.gms.measurement.internal.zzcj
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzqm.zzc());
            }
        });
        zzad = a("measurement.ga.ga_app_id", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzck
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzoq.zzd());
            }
        });
        zzae = a("measurement.lifecycle.app_in_background_parameter", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzcl
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzow.zzd());
            }
        });
        zzaf = a("measurement.integration.disable_firebase_instance_id", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzcm
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzqd.zzd());
            }
        });
        zzag = a("measurement.lifecycle.app_backgrounded_engagement", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzco
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzow.zzc());
            }
        });
        zzah = a("measurement.collection.service.update_with_analytics_fix", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzcp
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzqj.zzc());
            }
        });
        zzai = a("measurement.client.firebase_feature_rollout.v1.enable", bool2, bool2, new k() { // from class: com.google.android.gms.measurement.internal.zzcq
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzny.zzd());
            }
        });
        zzaj = a("measurement.client.sessions.check_on_reset_and_enable2", bool2, bool2, new k() { // from class: com.google.android.gms.measurement.internal.zzcr
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzoh.zzd());
            }
        });
        zzak = a("measurement.scheduler.task_thread.cleanup_on_exit", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzcs
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzpu.zzc());
            }
        });
        zzal = a("measurement.upload.file_truncate_fix", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzct
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zznv.zzc());
            }
        });
        zzam = a("measurement.collection.synthetic_data_mitigation", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzcv
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzqa.zzc());
            }
        });
        zzan = a("measurement.androidId.delete_feature", bool2, bool2, new k() { // from class: com.google.android.gms.measurement.internal.zzcw
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zznd.zzc());
            }
        });
        zzao = a("measurement.service.storage_consent_support_version", 203600, 203600, new k() { // from class: com.google.android.gms.measurement.internal.zzcx
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Integer.valueOf((int) zznp.zzb());
            }
        });
        zzap = a("measurement.client.properties.non_null_origin", bool2, bool2, new k() { // from class: com.google.android.gms.measurement.internal.zzcz
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzoz.zzc());
            }
        });
        zzaq = a("measurement.client.click_identifier_control.dev", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzda
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzng.zzc());
            }
        });
        zzar = a("measurement.service.click_identifier_control", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzdb
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zznj.zzc());
            }
        });
        zzas = a("measurement.client.reject_blank_user_id", bool2, bool2, new k() { // from class: com.google.android.gms.measurement.internal.zzdc
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzpr.zzc());
            }
        });
        zzat = a("measurement.config.persist_last_modified", bool2, bool2, new k() { // from class: com.google.android.gms.measurement.internal.zzdd
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzpf.zzd());
            }
        });
        zzau = a("measurement.client.consent.suppress_1p_in_ga4f_install", bool2, bool2, new k() { // from class: com.google.android.gms.measurement.internal.zzde
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzob.zzd());
            }
        });
        zzav = a("measurement.module.pixie.ees", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzdf
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzpl.zzd());
            }
        });
        zzaw = a("measurement.euid.client.dev", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzdh
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzns.zzc());
            }
        });
        zzax = a("measurement.euid.service", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzdi
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzns.zzd());
            }
        });
        zzay = a("measurement.adid_zero.service", bool, bool, new k() { // from class: com.google.android.gms.measurement.internal.zzdk
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzna.zzd());
            }
        });
        zzaz = a("measurement.adid_zero.remove_lair_if_adidzero_false", bool2, bool2, new k() { // from class: com.google.android.gms.measurement.internal.zzdl
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzna.zze());
            }
        });
        zzaA = a("measurement.service.refactor.package_side_screen", bool2, bool2, new k() { // from class: com.google.android.gms.measurement.internal.zzdm
            @Override // com.google.android.gms.measurement.internal.k
            public final Object zza() {
                zzdv<Long> zzdvVar = zzdw.zza;
                return Boolean.valueOf(zzpx.zzd());
            }
        });
    }

    @VisibleForTesting
    public static <V> zzdv<V> a(String str, V v, V v2, k<V> kVar) {
        zzdv<V> zzdvVar = new zzdv<>(str, v, v2, kVar, null);
        f10145a.add(zzdvVar);
        return zzdvVar;
    }

    public static Map<String, String> zzc(Context context) {
        zzha zza2 = zzha.zza(context.getContentResolver(), zzhk.zza("com.google.android.gms.measurement"));
        return zza2 == null ? Collections.emptyMap() : zza2.zzc();
    }
}
