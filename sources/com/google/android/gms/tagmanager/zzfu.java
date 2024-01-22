package com.google.android.gms.tagmanager;
/* loaded from: classes10.dex */
public final class zzfu extends Number implements Comparable<zzfu> {
    private double zza;
    private long zzb;
    private final boolean zzc = false;

    private zzfu(double d) {
        this.zza = d;
    }

    public static zzfu zzc(Double d) {
        return new zzfu(d.doubleValue());
    }

    public static zzfu zzd(long j) {
        return new zzfu(j);
    }

    public static zzfu zze(String str) throws NumberFormatException {
        try {
            try {
                return new zzfu(Long.parseLong(str));
            } catch (NumberFormatException unused) {
                throw new NumberFormatException(String.valueOf(str).concat(" is not a valid TypedNumber"));
            }
        } catch (NumberFormatException unused2) {
            return new zzfu(Double.parseDouble(str));
        }
    }

    @Override // java.lang.Number
    public final byte byteValue() {
        return (byte) zzb();
    }

    @Override // java.lang.Number
    public final double doubleValue() {
        return this.zzc ? this.zzb : this.zza;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof zzfu) && compareTo((zzfu) obj) == 0;
    }

    @Override // java.lang.Number
    public final float floatValue() {
        return (float) doubleValue();
    }

    public final int hashCode() {
        return new Long(zzb()).hashCode();
    }

    @Override // java.lang.Number
    public final int intValue() {
        return (int) zzb();
    }

    @Override // java.lang.Number
    public final long longValue() {
        return zzb();
    }

    @Override // java.lang.Number
    public final short shortValue() {
        return (short) zzb();
    }

    public final String toString() {
        return this.zzc ? Long.toString(this.zzb) : Double.toString(this.zza);
    }

    @Override // java.lang.Comparable
    /* renamed from: zza */
    public final int compareTo(zzfu zzfuVar) {
        if (this.zzc && zzfuVar.zzc) {
            return new Long(this.zzb).compareTo(Long.valueOf(zzfuVar.zzb));
        }
        return Double.compare(doubleValue(), zzfuVar.doubleValue());
    }

    public final long zzb() {
        return this.zzc ? this.zzb : (long) this.zza;
    }

    public final boolean zzf() {
        return !this.zzc;
    }

    public final boolean zzg() {
        return this.zzc;
    }

    private zzfu(long j) {
        this.zzb = j;
    }
}
