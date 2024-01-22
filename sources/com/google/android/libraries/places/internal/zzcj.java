package com.google.android.libraries.places.internal;

import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
final class zzcj {
    @Nullable
    private zzb[] addressComponents;
    @Nullable
    private String businessStatus;
    @Nullable
    private String formattedAddress;
    @Nullable
    private zza geometry;
    @Nullable
    private String icon;
    @Nullable
    private String internationalPhoneNumber;
    @Nullable
    private String name;
    @Nullable
    private zzc openingHours;
    @Nullable
    private zzd[] photos;
    @Nullable
    private String placeId;
    @Nullable
    private zze plusCode;
    @Nullable
    private Integer priceLevel;
    @Nullable
    private Double rating;
    @Nullable
    private String[] types;
    @Nullable
    private Integer userRatingsTotal;
    @Nullable
    private Integer utcOffset;
    @Nullable
    private String website;

    /* loaded from: classes10.dex */
    public static class zza {
        @Nullable
        private zzb location;
        @Nullable
        private C0395zza viewport;

        /* renamed from: com.google.android.libraries.places.internal.zzcj$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public static class C0395zza {
            @Nullable
            private zzb northeast;
            @Nullable
            private zzb southwest;

            @Nullable
            public final zzb zza() {
                return this.northeast;
            }

            @Nullable
            public final zzb zzb() {
                return this.southwest;
            }
        }

        /* loaded from: classes10.dex */
        public static class zzb {
            @Nullable
            private Double lat;
            @Nullable
            private Double lng;

            @Nullable
            public final Double zza() {
                return this.lat;
            }

            @Nullable
            public final Double zzb() {
                return this.lng;
            }
        }

        @Nullable
        public final zzb zza() {
            return this.location;
        }

        @Nullable
        public final C0395zza zzb() {
            return this.viewport;
        }
    }

    /* loaded from: classes10.dex */
    public static class zzb {
        @Nullable
        private String longName;
        @Nullable
        private String shortName;
        @Nullable
        private String[] types;

        @Nullable
        public final String zza() {
            return this.longName;
        }

        @Nullable
        public final String zzb() {
            return this.shortName;
        }

        @Nullable
        public final zzgi<String> zzc() {
            String[] strArr = this.types;
            if (strArr != null) {
                return zzgi.zza((Object[]) strArr);
            }
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public static class zzc {
        @Nullable
        private zza[] periods;
        @Nullable
        private String[] weekdayText;

        /* loaded from: classes10.dex */
        public static class zza {
            @Nullable
            private zzb close;
            @Nullable
            private zzb open;

            @Nullable
            public final zzb zza() {
                return this.close;
            }

            @Nullable
            public final zzb zzb() {
                return this.open;
            }
        }

        /* loaded from: classes10.dex */
        public static class zzb {
            @Nullable
            private Integer day;
            @Nullable
            private String time;

            @Nullable
            public final Integer zza() {
                return this.day;
            }

            @Nullable
            public final String zzb() {
                return this.time;
            }
        }

        @Nullable
        public final zzgi<zza> zza() {
            zza[] zzaVarArr = this.periods;
            if (zzaVarArr != null) {
                return zzgi.zza((Object[]) zzaVarArr);
            }
            return null;
        }

        @Nullable
        public final zzgi<String> zzb() {
            String[] strArr = this.weekdayText;
            if (strArr != null) {
                return zzgi.zza((Object[]) strArr);
            }
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public static class zzd {
        @Nullable
        private Integer height;
        @Nullable
        private String[] htmlAttributions;
        @Nullable
        private String photoReference;
        @Nullable
        private Integer width;

        @Nullable
        public final Integer zza() {
            return this.height;
        }

        @Nullable
        public final Integer zzb() {
            return this.width;
        }

        @Nullable
        public final String zzc() {
            return this.photoReference;
        }

        @Nullable
        public final zzgi<String> zzd() {
            String[] strArr = this.htmlAttributions;
            if (strArr != null) {
                return zzgi.zza((Object[]) strArr);
            }
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public static class zze {
        @Nullable
        private String compoundCode;
        @Nullable
        private String globalCode;

        @Nullable
        public final String zza() {
            return this.compoundCode;
        }

        @Nullable
        public final String zzb() {
            return this.globalCode;
        }
    }

    @Nullable
    public final zzgi<zzb> zza() {
        zzb[] zzbVarArr = this.addressComponents;
        if (zzbVarArr != null) {
            return zzgi.zza((Object[]) zzbVarArr);
        }
        return null;
    }

    @Nullable
    public final String zzb() {
        return this.businessStatus;
    }

    @Nullable
    public final String zzc() {
        return this.formattedAddress;
    }

    @Nullable
    public final zza zzd() {
        return this.geometry;
    }

    @Nullable
    public final String zze() {
        return this.internationalPhoneNumber;
    }

    @Nullable
    public final String zzf() {
        return this.name;
    }

    @Nullable
    public final zzc zzg() {
        return this.openingHours;
    }

    @Nullable
    public final zzgi<zzd> zzh() {
        zzd[] zzdVarArr = this.photos;
        if (zzdVarArr != null) {
            return zzgi.zza((Object[]) zzdVarArr);
        }
        return null;
    }

    @Nullable
    public final String zzi() {
        return this.placeId;
    }

    @Nullable
    public final zze zzj() {
        return this.plusCode;
    }

    @Nullable
    public final Integer zzk() {
        return this.priceLevel;
    }

    @Nullable
    public final Double zzl() {
        return this.rating;
    }

    @Nullable
    public final zzgi<String> zzm() {
        String[] strArr = this.types;
        if (strArr != null) {
            return zzgi.zza((Object[]) strArr);
        }
        return null;
    }

    @Nullable
    public final Integer zzn() {
        return this.userRatingsTotal;
    }

    @Nullable
    public final Integer zzo() {
        return this.utcOffset;
    }

    @Nullable
    public final String zzp() {
        return this.website;
    }
}
