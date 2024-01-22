package androidx.camera.view.transform;

import android.content.ContentResolver;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.view.TransformExperimental;
import androidx.camera.view.TransformUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
@TransformExperimental
/* loaded from: classes.dex */
public final class FileTransformFactory {

    /* renamed from: a  reason: collision with root package name */
    public boolean f825a;

    @NonNull
    public OutputTransform getOutputTransform(@NonNull ContentResolver contentResolver, @NonNull Uri uri) throws IOException {
        InputStream openInputStream = contentResolver.openInputStream(uri);
        try {
            OutputTransform outputTransform = getOutputTransform(openInputStream);
            if (openInputStream != null) {
                openInputStream.close();
            }
            return outputTransform;
        } catch (Throwable th) {
            if (openInputStream != null) {
                try {
                    openInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public boolean isUsingExifOrientation() {
        return this.f825a;
    }

    public void setUsingExifOrientation(boolean z) {
        this.f825a = z;
    }

    @NonNull
    public OutputTransform getOutputTransform(@NonNull File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            OutputTransform outputTransform = getOutputTransform(fileInputStream);
            fileInputStream.close();
            return outputTransform;
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @NonNull
    public OutputTransform getOutputTransform(@NonNull InputStream inputStream) throws IOException {
        Exif createFromInputStream = Exif.createFromInputStream(inputStream);
        Rect rect = new Rect(0, 0, createFromInputStream.getWidth(), createFromInputStream.getHeight());
        Matrix normalizedToBuffer = TransformUtils.getNormalizedToBuffer(rect);
        if (this.f825a) {
            normalizedToBuffer.postConcat(TransformUtils.getExifTransform(createFromInputStream.getOrientation(), createFromInputStream.getWidth(), createFromInputStream.getHeight()));
        }
        return new OutputTransform(normalizedToBuffer, TransformUtils.rectToSize(rect));
    }
}
