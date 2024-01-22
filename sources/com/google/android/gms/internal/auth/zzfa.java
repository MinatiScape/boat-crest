package com.google.android.gms.internal.auth;

import java.io.IOException;
/* loaded from: classes6.dex */
public final class zzfa extends IOException {
    private zzfw zza;

    public zzfa(IOException iOException) {
        super(iOException.getMessage(), iOException);
        this.zza = null;
    }

    public static zzfa zza() {
        return new zzfa("Protocol message contained an invalid tag (zero).");
    }

    public static zzfa zzb() {
        return new zzfa("Protocol message had invalid UTF-8.");
    }

    public static zzfa zzc() {
        return new zzfa("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public static zzfa zzd() {
        return new zzfa("Failed to parse the message.");
    }

    public static zzfa zzf() {
        return new zzfa("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final zzfa zze(zzfw zzfwVar) {
        this.zza = zzfwVar;
        return this;
    }

    public zzfa(String str) {
        super(str);
        this.zza = null;
    }
}
