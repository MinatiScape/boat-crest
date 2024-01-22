package com.google.android.libraries.places.internal;

import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
final class zzaz {
    @Nullable
    private String description;
    @Nullable
    private Integer distanceMeters;
    @Nullable
    private zza[] matchedSubstrings;
    @Nullable
    private String placeId;
    @Nullable
    private zzb structuredFormatting;
    @Nullable
    private String[] types;

    /* loaded from: classes10.dex */
    public static class zza {
        @Nullable
        public Integer length;
        @Nullable
        public Integer offset;
    }

    /* loaded from: classes10.dex */
    public static class zzb {
        @Nullable
        private String mainText;
        @Nullable
        private zza[] mainTextMatchedSubstrings;
        @Nullable
        private String secondaryText;
        @Nullable
        private zza[] secondaryTextMatchedSubstrings;

        @Nullable
        public final String zza() {
            return this.mainText;
        }

        @Nullable
        public final String zzb() {
            return this.secondaryText;
        }

        @Nullable
        public final zzgi<zza> zzc() {
            zza[] zzaVarArr = this.mainTextMatchedSubstrings;
            if (zzaVarArr != null) {
                return zzgi.zza((Object[]) zzaVarArr);
            }
            return null;
        }

        @Nullable
        public final zzgi<zza> zzd() {
            zza[] zzaVarArr = this.secondaryTextMatchedSubstrings;
            if (zzaVarArr != null) {
                return zzgi.zza((Object[]) zzaVarArr);
            }
            return null;
        }
    }

    @Nullable
    public final String zza() {
        return this.description;
    }

    @Nullable
    public final Integer zzb() {
        return this.distanceMeters;
    }

    @Nullable
    public final String zzc() {
        return this.placeId;
    }

    @Nullable
    public final zzb zzd() {
        return this.structuredFormatting;
    }

    @Nullable
    public final zzgi<String> zze() {
        String[] strArr = this.types;
        if (strArr != null) {
            return zzgi.zza((Object[]) strArr);
        }
        return null;
    }

    @Nullable
    public final zzgi<zza> zzf() {
        zza[] zzaVarArr = this.matchedSubstrings;
        if (zzaVarArr != null) {
            return zzgi.zza((Object[]) zzaVarArr);
        }
        return null;
    }
}
