package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "AuthenticationExtensionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes6.dex */
public class AuthenticationExtensions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<AuthenticationExtensions> CREATOR = new zzd();
    @Nullable
    @SafeParcelable.Field(getter = "getFidoAppIdExtension", id = 2)
    public final FidoAppIdExtension h;
    @Nullable
    @SafeParcelable.Field(getter = "getCableAuthenticationExtension", id = 3)
    public final zzs i;
    @Nullable
    @SafeParcelable.Field(getter = "getUserVerificationMethodExtension", id = 4)
    public final UserVerificationMethodExtension j;
    @Nullable
    @SafeParcelable.Field(getter = "getGoogleMultiAssertionExtension", id = 5)
    public final zzz k;
    @Nullable
    @SafeParcelable.Field(getter = "getGoogleSessionIdExtension", id = 6)
    public final zzab l;
    @Nullable
    @SafeParcelable.Field(getter = "getGoogleSilentVerificationExtension", id = 7)
    public final zzad m;
    @Nullable
    @SafeParcelable.Field(getter = "getDevicePublicKeyExtension", id = 8)
    public final zzu n;
    @Nullable
    @SafeParcelable.Field(getter = "getGoogleTunnelServerIdExtension", id = 9)
    public final zzag o;
    @Nullable
    @SafeParcelable.Field(getter = "getGoogleThirdPartyPaymentExtension", id = 10)
    public final GoogleThirdPartyPaymentExtension p;
    @Nullable
    @SafeParcelable.Field(getter = "getPrfExtension", id = 11)
    public final zzai q;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public FidoAppIdExtension f8395a;
        public UserVerificationMethodExtension b;
        public zzs c;
        public zzz d;
        public zzab e;
        public zzad f;
        public zzu g;
        public zzag h;
        public GoogleThirdPartyPaymentExtension i;
        public zzai j;

        public Builder() {
        }

        public Builder(@Nullable AuthenticationExtensions authenticationExtensions) {
            if (authenticationExtensions != null) {
                this.f8395a = authenticationExtensions.getFidoAppIdExtension();
                this.b = authenticationExtensions.getUserVerificationMethodExtension();
                this.c = authenticationExtensions.zza();
                this.d = authenticationExtensions.zzc();
                this.e = authenticationExtensions.zzd();
                this.f = authenticationExtensions.zze();
                this.g = authenticationExtensions.zzb();
                this.h = authenticationExtensions.zzg();
                this.i = authenticationExtensions.zzf();
                this.j = authenticationExtensions.zzh();
            }
        }

        @NonNull
        public AuthenticationExtensions build() {
            return new AuthenticationExtensions(this.f8395a, this.c, this.b, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
        }

        @NonNull
        public Builder setFido2Extension(@Nullable FidoAppIdExtension fidoAppIdExtension) {
            this.f8395a = fidoAppIdExtension;
            return this;
        }

        @NonNull
        public Builder setGoogleThirdPartyPaymentExtension(@Nullable GoogleThirdPartyPaymentExtension googleThirdPartyPaymentExtension) {
            this.i = googleThirdPartyPaymentExtension;
            return this;
        }

        @NonNull
        public Builder setUserVerificationMethodExtension(@Nullable UserVerificationMethodExtension userVerificationMethodExtension) {
            this.b = userVerificationMethodExtension;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public AuthenticationExtensions(@Nullable @SafeParcelable.Param(id = 2) FidoAppIdExtension fidoAppIdExtension, @Nullable @SafeParcelable.Param(id = 3) zzs zzsVar, @Nullable @SafeParcelable.Param(id = 4) UserVerificationMethodExtension userVerificationMethodExtension, @Nullable @SafeParcelable.Param(id = 5) zzz zzzVar, @Nullable @SafeParcelable.Param(id = 6) zzab zzabVar, @Nullable @SafeParcelable.Param(id = 7) zzad zzadVar, @Nullable @SafeParcelable.Param(id = 8) zzu zzuVar, @Nullable @SafeParcelable.Param(id = 9) zzag zzagVar, @Nullable @SafeParcelable.Param(id = 10) GoogleThirdPartyPaymentExtension googleThirdPartyPaymentExtension, @Nullable @SafeParcelable.Param(id = 11) zzai zzaiVar) {
        this.h = fidoAppIdExtension;
        this.j = userVerificationMethodExtension;
        this.i = zzsVar;
        this.k = zzzVar;
        this.l = zzabVar;
        this.m = zzadVar;
        this.n = zzuVar;
        this.o = zzagVar;
        this.p = googleThirdPartyPaymentExtension;
        this.q = zzaiVar;
    }

    public boolean equals(@NonNull Object obj) {
        if (obj instanceof AuthenticationExtensions) {
            AuthenticationExtensions authenticationExtensions = (AuthenticationExtensions) obj;
            return Objects.equal(this.h, authenticationExtensions.h) && Objects.equal(this.i, authenticationExtensions.i) && Objects.equal(this.j, authenticationExtensions.j) && Objects.equal(this.k, authenticationExtensions.k) && Objects.equal(this.l, authenticationExtensions.l) && Objects.equal(this.m, authenticationExtensions.m) && Objects.equal(this.n, authenticationExtensions.n) && Objects.equal(this.o, authenticationExtensions.o) && Objects.equal(this.p, authenticationExtensions.p) && Objects.equal(this.q, authenticationExtensions.q);
        }
        return false;
    }

    @Nullable
    public FidoAppIdExtension getFidoAppIdExtension() {
        return this.h;
    }

    @Nullable
    public UserVerificationMethodExtension getUserVerificationMethodExtension() {
        return this.j;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getFidoAppIdExtension(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.i, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getUserVerificationMethodExtension(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.k, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.l, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.m, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.n, i, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.o, i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.p, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.q, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final zzs zza() {
        return this.i;
    }

    @Nullable
    public final zzu zzb() {
        return this.n;
    }

    @Nullable
    public final zzz zzc() {
        return this.k;
    }

    @Nullable
    public final zzab zzd() {
        return this.l;
    }

    @Nullable
    public final zzad zze() {
        return this.m;
    }

    @Nullable
    public final GoogleThirdPartyPaymentExtension zzf() {
        return this.p;
    }

    @Nullable
    public final zzag zzg() {
        return this.o;
    }

    @Nullable
    public final zzai zzh() {
        return this.q;
    }
}
