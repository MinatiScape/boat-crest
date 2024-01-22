package com.google.android.recaptcha.internal;

import java.io.IOException;
/* loaded from: classes10.dex */
public class zzhp extends IOException {
    private zzip zza;
    private boolean zzb;

    public zzhp(IOException iOException) {
        super(iOException.getMessage(), iOException);
        this.zza = null;
    }

    public static zzho zza() {
        return new zzho("Protocol message tag had invalid wire type.");
    }

    public static zzhp zzb() {
        return new zzhp("Protocol message end-group tag did not match expected tag.");
    }

    public static zzhp zzc() {
        return new zzhp("Protocol message contained an invalid tag (zero).");
    }

    public static zzhp zzd() {
        return new zzhp("Protocol message had invalid UTF-8.");
    }

    public static zzhp zze() {
        return new zzhp("CodedInputStream encountered a malformed varint.");
    }

    public static zzhp zzf() {
        return new zzhp("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public static zzhp zzg() {
        return new zzhp("Failed to parse the message.");
    }

    public static zzhp zzi() {
        return new zzhp("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    public static zzhp zzj() {
        return new zzhp("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final zzhp zzh(zzip zzipVar) {
        this.zza = zzipVar;
        return this;
    }

    public final void zzk() {
        this.zzb = true;
    }

    public final boolean zzl() {
        return this.zzb;
    }

    public zzhp(String str) {
        super(str);
        this.zza = null;
    }
}
