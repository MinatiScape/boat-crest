package com.google.firebase.messaging.reporting;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.firebase_messaging.zze;
import com.google.android.gms.internal.firebase_messaging.zzz;
import com.google.firebase.encoders.annotations.Encodable;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes10.dex */
public final class MessagingClientEventExtension {
    public static final MessagingClientEventExtension b = new Builder().build();

    /* renamed from: a  reason: collision with root package name */
    public final MessagingClientEvent f11360a;

    /* loaded from: classes10.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public MessagingClientEvent f11361a = null;

        @NonNull
        public MessagingClientEventExtension build() {
            return new MessagingClientEventExtension(this.f11361a);
        }

        @NonNull
        public Builder setMessagingClientEvent(@NonNull MessagingClientEvent messagingClientEvent) {
            this.f11361a = messagingClientEvent;
            return this;
        }
    }

    public MessagingClientEventExtension(MessagingClientEvent messagingClientEvent) {
        this.f11360a = messagingClientEvent;
    }

    @NonNull
    public static MessagingClientEventExtension getDefaultInstance() {
        return b;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }

    @NonNull
    @Encodable.Ignore
    public MessagingClientEvent getMessagingClientEvent() {
        MessagingClientEvent messagingClientEvent = this.f11360a;
        return messagingClientEvent == null ? MessagingClientEvent.getDefaultInstance() : messagingClientEvent;
    }

    @NonNull
    @zzz(zza = 1)
    @Encodable.Field(name = "messagingClientEvent")
    public MessagingClientEvent getMessagingClientEventInternal() {
        return this.f11360a;
    }

    @NonNull
    public byte[] toByteArray() {
        return zze.zza(this);
    }

    public void writeTo(@NonNull OutputStream outputStream) throws IOException {
        zze.zzb(this, outputStream);
    }
}
