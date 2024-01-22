package androidx.camera.view.video;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.camera.core.VideoCapture;
import androidx.camera.view.video.b;
import androidx.core.util.Preconditions;
import com.google.auto.value.AutoValue;
import java.io.File;
@ExperimentalVideo
@AutoValue
/* loaded from: classes.dex */
public abstract class OutputFileOptions {

    /* renamed from: a  reason: collision with root package name */
    public static final Metadata f828a = Metadata.builder().build();

    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class Builder {
        public abstract Builder a(@Nullable ContentResolver contentResolver);

        public abstract Builder b(@Nullable ContentValues contentValues);

        @NonNull
        public abstract OutputFileOptions build();

        public abstract Builder c(@Nullable File file);

        public abstract Builder d(@Nullable ParcelFileDescriptor parcelFileDescriptor);

        public abstract Builder e(@Nullable Uri uri);

        @NonNull
        public abstract Builder setMetadata(@NonNull Metadata metadata);
    }

    @NonNull
    public static Builder builder(@NonNull File file) {
        return new b.C0122b().setMetadata(f828a).c(file);
    }

    @Nullable
    public abstract ContentResolver a();

    @Nullable
    public abstract ContentValues b();

    @Nullable
    public abstract File c();

    @Nullable
    public abstract ParcelFileDescriptor d();

    @Nullable
    public abstract Uri e();

    public final boolean f() {
        return c() != null;
    }

    public final boolean g() {
        return d() != null;
    }

    @NonNull
    public abstract Metadata getMetadata();

    public final boolean h() {
        return (e() == null || a() == null || b() == null) ? false : true;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public VideoCapture.OutputFileOptions toVideoCaptureOutputFileOptions() {
        VideoCapture.OutputFileOptions.Builder builder;
        if (f()) {
            builder = new VideoCapture.OutputFileOptions.Builder((File) Preconditions.checkNotNull(c()));
        } else if (g()) {
            builder = new VideoCapture.OutputFileOptions.Builder(((ParcelFileDescriptor) Preconditions.checkNotNull(d())).getFileDescriptor());
        } else {
            Preconditions.checkState(h());
            builder = new VideoCapture.OutputFileOptions.Builder((ContentResolver) Preconditions.checkNotNull(a()), (Uri) Preconditions.checkNotNull(e()), (ContentValues) Preconditions.checkNotNull(b()));
        }
        VideoCapture.Metadata metadata = new VideoCapture.Metadata();
        metadata.location = getMetadata().getLocation();
        builder.setMetadata(metadata);
        return builder.build();
    }

    @NonNull
    public static Builder builder(@NonNull ParcelFileDescriptor parcelFileDescriptor) {
        Preconditions.checkArgument(Build.VERSION.SDK_INT >= 26, "Using a ParcelFileDescriptor to record a video is only supported for Android 8.0 or above.");
        return new b.C0122b().setMetadata(f828a).d(parcelFileDescriptor);
    }

    @NonNull
    public static Builder builder(@NonNull ContentResolver contentResolver, @NonNull Uri uri, @NonNull ContentValues contentValues) {
        return new b.C0122b().setMetadata(f828a).a(contentResolver).e(uri).b(contentValues);
    }
}
