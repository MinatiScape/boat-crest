package kotlin.io;

import java.io.BufferedReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class LinesSequence$iterator$1 implements Iterator<String>, KMappedMarker {
    @Nullable
    public String h;
    public boolean i;
    public final /* synthetic */ LinesSequence j;

    public LinesSequence$iterator$1(LinesSequence linesSequence) {
        this.j = linesSequence;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        BufferedReader bufferedReader;
        if (this.h == null && !this.i) {
            bufferedReader = this.j.f14070a;
            String readLine = bufferedReader.readLine();
            this.h = readLine;
            if (readLine == null) {
                this.i = true;
            }
        }
        return this.h != null;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Iterator
    @NotNull
    public String next() {
        if (hasNext()) {
            String str = this.h;
            this.h = null;
            Intrinsics.checkNotNull(str);
            return str;
        }
        throw new NoSuchElementException();
    }
}
