package com.google.android.gms.internal.gtm;

import java.io.IOException;
/* loaded from: classes8.dex */
public class zzvk extends IOException {
    private zzwk zza;
    private boolean zzb;

    public zzvk(IOException iOException) {
        super(iOException.getMessage(), iOException);
        this.zza = null;
    }

    public static zzvj zza() {
        return new zzvj("Protocol message tag had invalid wire type.");
    }

    public static zzvk zzb() {
        return new zzvk("Protocol message end-group tag did not match expected tag.");
    }

    public static zzvk zzc() {
        return new zzvk("Protocol message contained an invalid tag (zero).");
    }

    public static zzvk zzd() {
        return new zzvk("Protocol message had invalid UTF-8.");
    }

    public static zzvk zze() {
        return new zzvk("CodedInputStream encountered a malformed varint.");
    }

    public static zzvk zzf() {
        return new zzvk("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public static zzvk zzg() {
        return new zzvk("Failed to parse the message.");
    }

    public static zzvk zzi() {
        return new zzvk("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    public static zzvk zzj() {
        return new zzvk("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final zzvk zzh(zzwk zzwkVar) {
        this.zza = zzwkVar;
        return this;
    }

    public final void zzk() {
        this.zzb = true;
    }

    public final boolean zzl() {
        return this.zzb;
    }

    public zzvk(String str) {
        super(str);
        this.zza = null;
    }
}
