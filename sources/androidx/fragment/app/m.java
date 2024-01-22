package androidx.fragment.app;

import android.util.Log;
import java.io.Writer;
/* loaded from: classes.dex */
public final class m extends Writer {
    public final String h;
    public StringBuilder i = new StringBuilder(128);

    public m(String str) {
        this.h = str;
    }

    public final void a() {
        if (this.i.length() > 0) {
            Log.d(this.h, this.i.toString());
            StringBuilder sb = this.i;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        a();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        a();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c == '\n') {
                a();
            } else {
                this.i.append(c);
            }
        }
    }
}
