package androidx.camera.view.video;

import android.location.Location;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.video.a;
import com.google.auto.value.AutoValue;
@ExperimentalVideo
@AutoValue
/* loaded from: classes.dex */
public abstract class Metadata {

    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class Builder {
        @NonNull
        public abstract Metadata build();

        @NonNull
        public abstract Builder setLocation(@Nullable Location location);
    }

    @NonNull
    public static Builder builder() {
        return new a.b();
    }

    @Nullable
    public abstract Location getLocation();
}
