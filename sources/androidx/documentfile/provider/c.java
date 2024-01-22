package androidx.documentfile.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
@RequiresApi(19)
/* loaded from: classes.dex */
public class c extends DocumentFile {
    public Context b;
    public Uri c;

    public c(@Nullable DocumentFile documentFile, Context context, Uri uri) {
        super(documentFile);
        this.b = context;
        this.c = uri;
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean canRead() {
        return a.a(this.b, this.c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean canWrite() {
        return a.b(this.b, this.c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public DocumentFile createDirectory(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public DocumentFile createFile(String str, String str2) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean delete() {
        try {
            return DocumentsContract.deleteDocument(this.b.getContentResolver(), this.c);
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean exists() {
        return a.d(this.b, this.c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    @Nullable
    public String getName() {
        return a.f(this.b, this.c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    @Nullable
    public String getType() {
        return a.h(this.b, this.c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public Uri getUri() {
        return this.c;
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean isDirectory() {
        return a.i(this.b, this.c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean isFile() {
        return a.j(this.b, this.c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean isVirtual() {
        return a.k(this.b, this.c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public long lastModified() {
        return a.l(this.b, this.c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public long length() {
        return a.m(this.b, this.c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public DocumentFile[] listFiles() {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean renameTo(String str) {
        throw new UnsupportedOperationException();
    }
}
