package com.google.android.gms.internal.measurement;

import java.io.IOException;
/* loaded from: classes8.dex */
public class zzkj extends IOException {
    public zzkj(String str) {
        super(str);
    }

    public static zzki zza() {
        return new zzki("Protocol message tag had invalid wire type.");
    }

    public static zzkj zzb() {
        return new zzkj("Protocol message contained an invalid tag (zero).");
    }

    public static zzkj zzc() {
        return new zzkj("Protocol message had invalid UTF-8.");
    }

    public static zzkj zzd() {
        return new zzkj("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public static zzkj zze() {
        return new zzkj("Failed to parse the message.");
    }

    public static zzkj zzf() {
        return new zzkj("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }
}
