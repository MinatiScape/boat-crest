package com.google.android.libraries.places.api.model;

import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
abstract class zzl extends Period {
    private final TimeOfWeek zza;
    private final TimeOfWeek zzb;

    public zzl(@Nullable TimeOfWeek timeOfWeek, @Nullable TimeOfWeek timeOfWeek2) {
        this.zza = timeOfWeek;
        this.zzb = timeOfWeek2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Period) {
            Period period = (Period) obj;
            TimeOfWeek timeOfWeek = this.zza;
            if (timeOfWeek != null ? timeOfWeek.equals(period.getOpen()) : period.getOpen() == null) {
                TimeOfWeek timeOfWeek2 = this.zzb;
                if (timeOfWeek2 != null ? timeOfWeek2.equals(period.getClose()) : period.getClose() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.Period
    @Nullable
    public TimeOfWeek getClose() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.Period
    @Nullable
    public TimeOfWeek getOpen() {
        return this.zza;
    }

    public int hashCode() {
        TimeOfWeek timeOfWeek = this.zza;
        int hashCode = ((timeOfWeek == null ? 0 : timeOfWeek.hashCode()) ^ 1000003) * 1000003;
        TimeOfWeek timeOfWeek2 = this.zzb;
        return hashCode ^ (timeOfWeek2 != null ? timeOfWeek2.hashCode() : 0);
    }

    public String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(valueOf.length() + 21 + valueOf2.length());
        sb.append("Period{open=");
        sb.append(valueOf);
        sb.append(", close=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
