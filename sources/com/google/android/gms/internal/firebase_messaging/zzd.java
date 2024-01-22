package com.google.android.gms.internal.firebase_messaging;

import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.messaging.reporting.MessagingClientEvent;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;
/* loaded from: classes7.dex */
public final class zzd implements Configurator {
    public static final Configurator zza = new zzd();

    @Override // com.google.firebase.encoders.config.Configurator
    public final void configure(EncoderConfig<?> encoderConfig) {
        encoderConfig.registerEncoder(zze.class, g.f8647a);
        encoderConfig.registerEncoder(MessagingClientEventExtension.class, f.f8646a);
        encoderConfig.registerEncoder(MessagingClientEvent.class, a.f8641a);
    }
}
