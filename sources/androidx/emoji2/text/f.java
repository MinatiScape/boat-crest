package androidx.emoji2.text;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.MetaKeyKeyListener;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import androidx.annotation.AnyThread;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.MetadataRepo;
import java.util.Arrays;
@AnyThread
@RequiresApi(19)
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class f {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final EmojiCompat.d f1264a;
    @NonNull
    public final MetadataRepo b;
    @NonNull
    public EmojiCompat.GlyphChecker c;
    public final boolean d;
    @Nullable
    public final int[] e;

    @RequiresApi(19)
    /* loaded from: classes.dex */
    public static final class a {
        public static int a(CharSequence charSequence, int i, int i2) {
            int length = charSequence.length();
            if (i < 0 || length < i || i2 < 0) {
                return -1;
            }
            while (true) {
                boolean z = false;
                while (i2 != 0) {
                    i--;
                    if (i < 0) {
                        return z ? -1 : 0;
                    }
                    char charAt = charSequence.charAt(i);
                    if (z) {
                        if (!Character.isHighSurrogate(charAt)) {
                            return -1;
                        }
                        i2--;
                    } else if (!Character.isSurrogate(charAt)) {
                        i2--;
                    } else if (Character.isHighSurrogate(charAt)) {
                        return -1;
                    } else {
                        z = true;
                    }
                }
                return i;
            }
        }

        public static int b(CharSequence charSequence, int i, int i2) {
            int length = charSequence.length();
            if (i < 0 || length < i || i2 < 0) {
                return -1;
            }
            while (true) {
                boolean z = false;
                while (i2 != 0) {
                    if (i >= length) {
                        if (z) {
                            return -1;
                        }
                        return length;
                    }
                    char charAt = charSequence.charAt(i);
                    if (z) {
                        if (!Character.isLowSurrogate(charAt)) {
                            return -1;
                        }
                        i2--;
                        i++;
                    } else if (!Character.isSurrogate(charAt)) {
                        i2--;
                        i++;
                    } else if (Character.isLowSurrogate(charAt)) {
                        return -1;
                    } else {
                        i++;
                        z = true;
                    }
                }
                return i;
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public int f1265a = 1;
        public final MetadataRepo.a b;
        public MetadataRepo.a c;
        public MetadataRepo.a d;
        public int e;
        public int f;
        public final boolean g;
        public final int[] h;

        public b(MetadataRepo.a aVar, boolean z, int[] iArr) {
            this.b = aVar;
            this.c = aVar;
            this.g = z;
            this.h = iArr;
        }

        public static boolean d(int i) {
            return i == 65039;
        }

        public static boolean f(int i) {
            return i == 65038;
        }

        public int a(int i) {
            MetadataRepo.a a2 = this.c.a(i);
            int i2 = 3;
            if (this.f1265a == 2) {
                if (a2 != null) {
                    this.c = a2;
                    this.f++;
                } else if (f(i)) {
                    i2 = g();
                } else if (!d(i)) {
                    if (this.c.b() != null) {
                        if (this.f == 1) {
                            if (h()) {
                                this.d = this.c;
                                g();
                            } else {
                                i2 = g();
                            }
                        } else {
                            this.d = this.c;
                            g();
                        }
                    } else {
                        i2 = g();
                    }
                }
                i2 = 2;
            } else if (a2 == null) {
                i2 = g();
            } else {
                this.f1265a = 2;
                this.c = a2;
                this.f = 1;
                i2 = 2;
            }
            this.e = i;
            return i2;
        }

        public EmojiMetadata b() {
            return this.c.b();
        }

        public EmojiMetadata c() {
            return this.d.b();
        }

        public boolean e() {
            return this.f1265a == 2 && this.c.b() != null && (this.f > 1 || h());
        }

        public final int g() {
            this.f1265a = 1;
            this.c = this.b;
            this.f = 0;
            return 1;
        }

        public final boolean h() {
            if (this.c.b().isDefaultEmoji() || d(this.e)) {
                return true;
            }
            if (this.g) {
                if (this.h == null) {
                    return true;
                }
                if (Arrays.binarySearch(this.h, this.c.b().getCodepointAt(0)) < 0) {
                    return true;
                }
            }
            return false;
        }
    }

    public f(@NonNull MetadataRepo metadataRepo, @NonNull EmojiCompat.d dVar, @NonNull EmojiCompat.GlyphChecker glyphChecker, boolean z, @Nullable int[] iArr) {
        this.f1264a = dVar;
        this.b = metadataRepo;
        this.c = glyphChecker;
        this.d = z;
        this.e = iArr;
    }

    public static boolean b(@NonNull Editable editable, @NonNull KeyEvent keyEvent, boolean z) {
        EmojiSpan[] emojiSpanArr;
        if (i(keyEvent)) {
            return false;
        }
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        if (!h(selectionStart, selectionEnd) && (emojiSpanArr = (EmojiSpan[]) editable.getSpans(selectionStart, selectionEnd, EmojiSpan.class)) != null && emojiSpanArr.length > 0) {
            for (EmojiSpan emojiSpan : emojiSpanArr) {
                int spanStart = editable.getSpanStart(emojiSpan);
                int spanEnd = editable.getSpanEnd(emojiSpan);
                if ((z && spanStart == selectionStart) || ((!z && spanEnd == selectionStart) || (selectionStart > spanStart && selectionStart < spanEnd))) {
                    editable.delete(spanStart, spanEnd);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean e(@NonNull InputConnection inputConnection, @NonNull Editable editable, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, boolean z) {
        int max;
        int min;
        if (editable != null && inputConnection != null && i >= 0 && i2 >= 0) {
            int selectionStart = Selection.getSelectionStart(editable);
            int selectionEnd = Selection.getSelectionEnd(editable);
            if (h(selectionStart, selectionEnd)) {
                return false;
            }
            if (z) {
                max = a.a(editable, selectionStart, Math.max(i, 0));
                min = a.b(editable, selectionEnd, Math.max(i2, 0));
                if (max == -1 || min == -1) {
                    return false;
                }
            } else {
                max = Math.max(selectionStart - i, 0);
                min = Math.min(selectionEnd + i2, editable.length());
            }
            EmojiSpan[] emojiSpanArr = (EmojiSpan[]) editable.getSpans(max, min, EmojiSpan.class);
            if (emojiSpanArr != null && emojiSpanArr.length > 0) {
                for (EmojiSpan emojiSpan : emojiSpanArr) {
                    int spanStart = editable.getSpanStart(emojiSpan);
                    int spanEnd = editable.getSpanEnd(emojiSpan);
                    max = Math.min(spanStart, max);
                    min = Math.max(spanEnd, min);
                }
                int max2 = Math.max(max, 0);
                int min2 = Math.min(min, editable.length());
                inputConnection.beginBatchEdit();
                editable.delete(max2, min2);
                inputConnection.endBatchEdit();
                return true;
            }
        }
        return false;
    }

    public static boolean f(@NonNull Editable editable, int i, @NonNull KeyEvent keyEvent) {
        boolean b2;
        if (i != 67) {
            b2 = i != 112 ? false : b(editable, keyEvent, true);
        } else {
            b2 = b(editable, keyEvent, false);
        }
        if (b2) {
            MetaKeyKeyListener.adjustMetaAfterKeypress(editable);
            return true;
        }
        return false;
    }

    public static boolean h(int i, int i2) {
        return i == -1 || i2 == -1 || i != i2;
    }

    public static boolean i(@NonNull KeyEvent keyEvent) {
        return !KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState());
    }

    public final void a(@NonNull Spannable spannable, EmojiMetadata emojiMetadata, int i, int i2) {
        spannable.setSpan(this.f1264a.a(emojiMetadata), i, i2, 33);
    }

    public int c(@NonNull CharSequence charSequence) {
        return d(charSequence, this.b.b());
    }

    public int d(@NonNull CharSequence charSequence, int i) {
        b bVar = new b(this.b.c(), this.d, this.e);
        int length = charSequence.length();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            int codePointAt = Character.codePointAt(charSequence, i2);
            int a2 = bVar.a(codePointAt);
            EmojiMetadata b2 = bVar.b();
            if (a2 == 1) {
                i2 += Character.charCount(codePointAt);
                i4 = 0;
            } else if (a2 == 2) {
                i2 += Character.charCount(codePointAt);
            } else if (a2 == 3) {
                b2 = bVar.c();
                if (b2.getCompatAdded() <= i) {
                    i3++;
                }
            }
            if (b2 != null && b2.getCompatAdded() <= i) {
                i4++;
            }
        }
        if (i3 != 0) {
            return 2;
        }
        if (!bVar.e() || bVar.b().getCompatAdded() > i) {
            return i4 == 0 ? 0 : 2;
        }
        return 1;
    }

    public final boolean g(CharSequence charSequence, int i, int i2, EmojiMetadata emojiMetadata) {
        if (emojiMetadata.getHasGlyph() == 0) {
            emojiMetadata.setHasGlyph(this.c.hasGlyph(charSequence, i, i2, emojiMetadata.getSdkAdded()));
        }
        return emojiMetadata.getHasGlyph() == 2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x0125, code lost:
        ((androidx.emoji2.text.SpannableBuilder) r10).endBatchEdit();
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x00a2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0047 A[Catch: all -> 0x012c, TryCatch #0 {all -> 0x012c, blocks: (B:7:0x000d, B:10:0x0012, B:12:0x0016, B:14:0x0025, B:18:0x0036, B:20:0x0040, B:22:0x0043, B:24:0x0047, B:26:0x0053, B:27:0x0056, B:29:0x0063, B:35:0x0072, B:36:0x0080, B:40:0x009b, B:48:0x00ab, B:51:0x00b7, B:52:0x00c1, B:53:0x00cb, B:55:0x00d2, B:56:0x00d7, B:58:0x00e2, B:60:0x00e9, B:64:0x00f3, B:67:0x00ff, B:68:0x0105, B:70:0x010e, B:15:0x002b), top: B:84:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ff A[Catch: all -> 0x012c, TryCatch #0 {all -> 0x012c, blocks: (B:7:0x000d, B:10:0x0012, B:12:0x0016, B:14:0x0025, B:18:0x0036, B:20:0x0040, B:22:0x0043, B:24:0x0047, B:26:0x0053, B:27:0x0056, B:29:0x0063, B:35:0x0072, B:36:0x0080, B:40:0x009b, B:48:0x00ab, B:51:0x00b7, B:52:0x00c1, B:53:0x00cb, B:55:0x00d2, B:56:0x00d7, B:58:0x00e2, B:60:0x00e9, B:64:0x00f3, B:67:0x00ff, B:68:0x0105, B:70:0x010e, B:15:0x002b), top: B:84:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x010e A[Catch: all -> 0x012c, TRY_LEAVE, TryCatch #0 {all -> 0x012c, blocks: (B:7:0x000d, B:10:0x0012, B:12:0x0016, B:14:0x0025, B:18:0x0036, B:20:0x0040, B:22:0x0043, B:24:0x0047, B:26:0x0053, B:27:0x0056, B:29:0x0063, B:35:0x0072, B:36:0x0080, B:40:0x009b, B:48:0x00ab, B:51:0x00b7, B:52:0x00c1, B:53:0x00cb, B:55:0x00d2, B:56:0x00d7, B:58:0x00e2, B:60:0x00e9, B:64:0x00f3, B:67:0x00ff, B:68:0x0105, B:70:0x010e, B:15:0x002b), top: B:84:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00d7 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.CharSequence j(@androidx.annotation.NonNull java.lang.CharSequence r10, @androidx.annotation.IntRange(from = 0) int r11, @androidx.annotation.IntRange(from = 0) int r12, @androidx.annotation.IntRange(from = 0) int r13, boolean r14) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.text.f.j(java.lang.CharSequence, int, int, int, boolean):java.lang.CharSequence");
    }
}
