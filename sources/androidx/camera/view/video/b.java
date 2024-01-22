package androidx.camera.view.video;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.video.OutputFileOptions;
import java.io.File;
import java.util.Objects;
/* loaded from: classes.dex */
public final class b extends OutputFileOptions {
    public final File b;
    public final ParcelFileDescriptor c;
    public final ContentResolver d;
    public final Uri e;
    public final ContentValues f;
    public final Metadata g;

    /* renamed from: androidx.camera.view.video.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0122b extends OutputFileOptions.Builder {

        /* renamed from: a  reason: collision with root package name */
        public File f831a;
        public ParcelFileDescriptor b;
        public ContentResolver c;
        public Uri d;
        public ContentValues e;
        public Metadata f;

        @Override // androidx.camera.view.video.OutputFileOptions.Builder
        public OutputFileOptions.Builder a(@Nullable ContentResolver contentResolver) {
            this.c = contentResolver;
            return this;
        }

        @Override // androidx.camera.view.video.OutputFileOptions.Builder
        public OutputFileOptions.Builder b(@Nullable ContentValues contentValues) {
            this.e = contentValues;
            return this;
        }

        @Override // androidx.camera.view.video.OutputFileOptions.Builder
        public OutputFileOptions build() {
            String str = "";
            if (this.f == null) {
                str = " metadata";
            }
            if (str.isEmpty()) {
                return new b(this.f831a, this.b, this.c, this.d, this.e, this.f);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // androidx.camera.view.video.OutputFileOptions.Builder
        public OutputFileOptions.Builder c(@Nullable File file) {
            this.f831a = file;
            return this;
        }

        @Override // androidx.camera.view.video.OutputFileOptions.Builder
        public OutputFileOptions.Builder d(@Nullable ParcelFileDescriptor parcelFileDescriptor) {
            this.b = parcelFileDescriptor;
            return this;
        }

        @Override // androidx.camera.view.video.OutputFileOptions.Builder
        public OutputFileOptions.Builder e(@Nullable Uri uri) {
            this.d = uri;
            return this;
        }

        @Override // androidx.camera.view.video.OutputFileOptions.Builder
        public OutputFileOptions.Builder setMetadata(Metadata metadata) {
            Objects.requireNonNull(metadata, "Null metadata");
            this.f = metadata;
            return this;
        }
    }

    @Override // androidx.camera.view.video.OutputFileOptions
    @Nullable
    public ContentResolver a() {
        return this.d;
    }

    @Override // androidx.camera.view.video.OutputFileOptions
    @Nullable
    public ContentValues b() {
        return this.f;
    }

    @Override // androidx.camera.view.video.OutputFileOptions
    @Nullable
    public File c() {
        return this.b;
    }

    @Override // androidx.camera.view.video.OutputFileOptions
    @Nullable
    public ParcelFileDescriptor d() {
        return this.c;
    }

    @Override // androidx.camera.view.video.OutputFileOptions
    @Nullable
    public Uri e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof OutputFileOptions) {
            OutputFileOptions outputFileOptions = (OutputFileOptions) obj;
            File file = this.b;
            if (file != null ? file.equals(outputFileOptions.c()) : outputFileOptions.c() == null) {
                ParcelFileDescriptor parcelFileDescriptor = this.c;
                if (parcelFileDescriptor != null ? parcelFileDescriptor.equals(outputFileOptions.d()) : outputFileOptions.d() == null) {
                    ContentResolver contentResolver = this.d;
                    if (contentResolver != null ? contentResolver.equals(outputFileOptions.a()) : outputFileOptions.a() == null) {
                        Uri uri = this.e;
                        if (uri != null ? uri.equals(outputFileOptions.e()) : outputFileOptions.e() == null) {
                            ContentValues contentValues = this.f;
                            if (contentValues != null ? contentValues.equals(outputFileOptions.b()) : outputFileOptions.b() == null) {
                                if (this.g.equals(outputFileOptions.getMetadata())) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    @Override // androidx.camera.view.video.OutputFileOptions
    @NonNull
    public Metadata getMetadata() {
        return this.g;
    }

    public int hashCode() {
        File file = this.b;
        int hashCode = ((file == null ? 0 : file.hashCode()) ^ 1000003) * 1000003;
        ParcelFileDescriptor parcelFileDescriptor = this.c;
        int hashCode2 = (hashCode ^ (parcelFileDescriptor == null ? 0 : parcelFileDescriptor.hashCode())) * 1000003;
        ContentResolver contentResolver = this.d;
        int hashCode3 = (hashCode2 ^ (contentResolver == null ? 0 : contentResolver.hashCode())) * 1000003;
        Uri uri = this.e;
        int hashCode4 = (hashCode3 ^ (uri == null ? 0 : uri.hashCode())) * 1000003;
        ContentValues contentValues = this.f;
        return ((hashCode4 ^ (contentValues != null ? contentValues.hashCode() : 0)) * 1000003) ^ this.g.hashCode();
    }

    public String toString() {
        return "OutputFileOptions{file=" + this.b + ", fileDescriptor=" + this.c + ", contentResolver=" + this.d + ", saveCollection=" + this.e + ", contentValues=" + this.f + ", metadata=" + this.g + "}";
    }

    public b(@Nullable File file, @Nullable ParcelFileDescriptor parcelFileDescriptor, @Nullable ContentResolver contentResolver, @Nullable Uri uri, @Nullable ContentValues contentValues, Metadata metadata) {
        this.b = file;
        this.c = parcelFileDescriptor;
        this.d = contentResolver;
        this.e = uri;
        this.f = contentValues;
        this.g = metadata;
    }
}
