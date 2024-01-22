package com.mappls.sdk.nearby.plugin.model;

import android.os.Parcelable;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.mappls.sdk.nearby.plugin.model.b;
@AutoValue
@Keep
/* loaded from: classes10.dex */
public abstract class NearbyOption implements Parcelable {

    @Keep
    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        public abstract Builder bounds(String str);

        public abstract NearbyOption build();

        public abstract Builder explain(Boolean bool);

        public abstract Builder filter(String str);

        public abstract Builder pod(String str);

        public abstract Builder radius(@NonNull Integer num);

        public abstract Builder richData(Boolean bool);

        public abstract Builder searchBy(String str);

        public abstract Builder sortBy(String str);

        public abstract Builder userName(String str);
    }

    public static Builder builder() {
        return new b.a().radius(1000);
    }

    @Nullable
    public abstract String bounds();

    @Nullable
    public abstract Boolean explain();

    @Nullable
    public abstract String filter();

    @Nullable
    public abstract String pod();

    @NonNull
    public abstract Integer radius();

    @Nullable
    public abstract Boolean richData();

    @Nullable
    public abstract String searchBy();

    @Nullable
    public abstract String sortBy();

    @Nullable
    public abstract String userName();
}
