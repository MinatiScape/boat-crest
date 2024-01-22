package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
@SafeParcelable.Class(creator = "RegisterRequestParamsCreator")
@SafeParcelable.Reserved({1})
@Deprecated
/* loaded from: classes6.dex */
public class RegisterRequestParams extends RequestParams {
    @NonNull
    public static final Parcelable.Creator<RegisterRequestParams> CREATOR = new zzh();
    public static final int MAX_DISPLAY_HINT_LENGTH = 80;
    @SafeParcelable.Field(getter = "getRequestId", id = 2)
    public final Integer h;
    @SafeParcelable.Field(getter = "getTimeoutSeconds", id = 3)
    public final Double i;
    @SafeParcelable.Field(getter = "getAppId", id = 4)
    public final Uri j;
    @SafeParcelable.Field(getter = "getRegisterRequests", id = 5)
    public final List k;
    @SafeParcelable.Field(getter = "getRegisteredKeys", id = 6)
    public final List l;
    @SafeParcelable.Field(getter = "getChannelIdValue", id = 7)
    public final ChannelIdValue m;
    @SafeParcelable.Field(getter = "getDisplayHint", id = 8)
    public final String n;
    public Set o;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Integer f8418a;
        public Double b;
        public Uri c;
        public List d;
        public List e;
        public ChannelIdValue f;
        public String g;

        @NonNull
        public RegisterRequestParams build() {
            return new RegisterRequestParams(this.f8418a, this.b, this.c, this.d, this.e, this.f, this.g);
        }

        @NonNull
        public Builder setAppId(@NonNull Uri uri) {
            this.c = uri;
            return this;
        }

        @NonNull
        public Builder setChannelIdValue(@NonNull ChannelIdValue channelIdValue) {
            this.f = channelIdValue;
            return this;
        }

        @NonNull
        public Builder setDisplayHint(@NonNull String str) {
            this.g = str;
            return this;
        }

        @NonNull
        public Builder setRegisterRequests(@NonNull List<RegisterRequest> list) {
            this.d = list;
            return this;
        }

        @NonNull
        public Builder setRegisteredKeys(@NonNull List<RegisteredKey> list) {
            this.e = list;
            return this;
        }

        @NonNull
        public Builder setRequestId(@NonNull Integer num) {
            this.f8418a = num;
            return this;
        }

        @NonNull
        public Builder setTimeoutSeconds(@NonNull Double d) {
            this.b = d;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public RegisterRequestParams(@SafeParcelable.Param(id = 2) Integer num, @SafeParcelable.Param(id = 3) Double d, @SafeParcelable.Param(id = 4) Uri uri, @SafeParcelable.Param(id = 5) List list, @SafeParcelable.Param(id = 6) List list2, @SafeParcelable.Param(id = 7) ChannelIdValue channelIdValue, @SafeParcelable.Param(id = 8) String str) {
        this.h = num;
        this.i = d;
        this.j = uri;
        boolean z = false;
        Preconditions.checkArgument((list == null || list.isEmpty()) ? false : true, "empty list of register requests is provided");
        this.k = list;
        this.l = list2;
        this.m = channelIdValue;
        HashSet hashSet = new HashSet();
        if (uri != null) {
            hashSet.add(uri);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            RegisterRequest registerRequest = (RegisterRequest) it.next();
            Preconditions.checkArgument((uri == null && registerRequest.getAppId() == null) ? false : true, "register request has null appId and no request appId is provided");
            if (registerRequest.getAppId() != null) {
                hashSet.add(Uri.parse(registerRequest.getAppId()));
            }
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            RegisteredKey registeredKey = (RegisteredKey) it2.next();
            Preconditions.checkArgument((uri == null && registeredKey.getAppId() == null) ? false : true, "registered key has null appId and no request appId is provided");
            if (registeredKey.getAppId() != null) {
                hashSet.add(Uri.parse(registeredKey.getAppId()));
            }
        }
        this.o = hashSet;
        Preconditions.checkArgument((str == null || str.length() <= 80) ? true : z, "Display Hint cannot be longer than 80 characters");
        this.n = str;
    }

    public boolean equals(@NonNull Object obj) {
        List list;
        List list2;
        if (this == obj) {
            return true;
        }
        if (obj instanceof RegisterRequestParams) {
            RegisterRequestParams registerRequestParams = (RegisterRequestParams) obj;
            return Objects.equal(this.h, registerRequestParams.h) && Objects.equal(this.i, registerRequestParams.i) && Objects.equal(this.j, registerRequestParams.j) && Objects.equal(this.k, registerRequestParams.k) && (((list = this.l) == null && registerRequestParams.l == null) || (list != null && (list2 = registerRequestParams.l) != null && list.containsAll(list2) && registerRequestParams.l.containsAll(this.l))) && Objects.equal(this.m, registerRequestParams.m) && Objects.equal(this.n, registerRequestParams.n);
        }
        return false;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    @NonNull
    public Set<Uri> getAllAppIds() {
        return this.o;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    @NonNull
    public Uri getAppId() {
        return this.j;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    @NonNull
    public ChannelIdValue getChannelIdValue() {
        return this.m;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    @NonNull
    public String getDisplayHint() {
        return this.n;
    }

    @NonNull
    public List<RegisterRequest> getRegisterRequests() {
        return this.k;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    @NonNull
    public List<RegisteredKey> getRegisteredKeys() {
        return this.l;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    @NonNull
    public Integer getRequestId() {
        return this.h;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    @NonNull
    public Double getTimeoutSeconds() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.j, this.i, this.k, this.l, this.m, this.n);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntegerObject(parcel, 2, getRequestId(), false);
        SafeParcelWriter.writeDoubleObject(parcel, 3, getTimeoutSeconds(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, getAppId(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 5, getRegisterRequests(), false);
        SafeParcelWriter.writeTypedList(parcel, 6, getRegisteredKeys(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, getChannelIdValue(), i, false);
        SafeParcelWriter.writeString(parcel, 8, getDisplayHint(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
