package com.google.mlkit.vision.text;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.mlkit_vision_text_common.zzbw;
import com.google.android.gms.internal.mlkit_vision_text_common.zzpa;
import com.google.android.gms.internal.mlkit_vision_text_common.zzpc;
import com.google.android.gms.internal.mlkit_vision_text_common.zzpe;
import com.google.android.gms.internal.mlkit_vision_text_common.zzpg;
import com.google.android.gms.internal.mlkit_vision_text_common.zzpi;
import com.google.android.gms.internal.mlkit_vision_text_common.zzu;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.text.Text;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes10.dex */
public class Text {

    /* renamed from: a  reason: collision with root package name */
    public final List f11642a;
    public final String b;

    /* loaded from: classes10.dex */
    public static class Symbol extends a {
        public final float e;
        public final float f;

        public Symbol(@NonNull zzpi zzpiVar, @Nullable Matrix matrix) {
            super(zzpiVar.zzd(), zzpiVar.zzc(), zzpiVar.zze(), "", matrix);
            this.e = zzpiVar.zzb();
            this.f = zzpiVar.zza();
        }

        public float getAngle() {
            return this.f;
        }

        public float getConfidence() {
            return this.e;
        }

        @NonNull
        public String getText() {
            return zza();
        }
    }

    /* loaded from: classes10.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final String f11643a;
        public final Rect b;
        public final Point[] c;
        public final String d;

        public a(String str, Rect rect, List list, String str2, @Nullable Matrix matrix) {
            this.f11643a = str;
            Rect rect2 = new Rect(rect);
            if (matrix != null) {
                CommonConvertUtils.transformRect(rect2, matrix);
            }
            this.b = rect2;
            Point[] pointArr = new Point[list.size()];
            for (int i = 0; i < list.size(); i++) {
                pointArr[i] = new Point((Point) list.get(i));
            }
            if (matrix != null) {
                CommonConvertUtils.transformPointArray(pointArr, matrix);
            }
            this.c = pointArr;
            this.d = str2;
        }

        @Nullable
        public Rect getBoundingBox() {
            return this.b;
        }

        @Nullable
        public Point[] getCornerPoints() {
            return this.c;
        }

        @NonNull
        public String getRecognizedLanguage() {
            return this.d;
        }

        @NonNull
        public final String zza() {
            String str = this.f11643a;
            return str == null ? "" : str;
        }
    }

    public Text(@NonNull zzpg zzpgVar, @Nullable final Matrix matrix) {
        ArrayList arrayList = new ArrayList();
        this.f11642a = arrayList;
        this.b = zzpgVar.zza();
        arrayList.addAll(zzbw.zza(zzpgVar.zzb(), new zzu() { // from class: com.google.mlkit.vision.text.zza
            @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
            public final Object zza(Object obj) {
                return new Text.TextBlock((zzpa) obj, matrix);
            }
        }));
    }

    @NonNull
    public String getText() {
        return this.b;
    }

    @NonNull
    public List<TextBlock> getTextBlocks() {
        return Collections.unmodifiableList(this.f11642a);
    }

    public Text(@NonNull String str, @NonNull List list) {
        ArrayList arrayList = new ArrayList();
        this.f11642a = arrayList;
        arrayList.addAll(list);
        this.b = str;
    }

    /* loaded from: classes10.dex */
    public static class Line extends a {
        @GuardedBy("this")
        public final List e;
        public final float f;
        public final float g;

        public Line(@NonNull zzpe zzpeVar, @Nullable final Matrix matrix, float f, float f2) {
            super(zzpeVar.zze(), zzpeVar.zzc(), zzpeVar.zzf(), zzpeVar.zzd(), matrix);
            this.e = zzbw.zza(zzpeVar.zzg(), new zzu() { // from class: com.google.mlkit.vision.text.zzc
                @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                public final Object zza(Object obj) {
                    return new Text.Element((zzpc) obj, matrix);
                }
            });
            this.f = f;
            this.g = f2;
        }

        public float getAngle() {
            return this.g;
        }

        public float getConfidence() {
            return this.f;
        }

        @NonNull
        public synchronized List<Element> getElements() {
            return this.e;
        }

        @NonNull
        public String getText() {
            return zza();
        }

        public Line(@NonNull String str, @NonNull Rect rect, @NonNull List list, @NonNull String str2, @Nullable Matrix matrix, @NonNull List list2, float f, float f2) {
            super(str, rect, list, str2, matrix);
            this.e = list2;
            this.f = f;
            this.g = f2;
        }
    }

    /* loaded from: classes10.dex */
    public static class TextBlock extends a {
        @GuardedBy("this")
        public final List e;

        public TextBlock(@NonNull zzpa zzpaVar, @Nullable final Matrix matrix) {
            super(zzpaVar.zzc(), zzpaVar.zza(), zzpaVar.zzd(), zzpaVar.zzb(), matrix);
            this.e = zzbw.zza(zzpaVar.zze(), new zzu() { // from class: com.google.mlkit.vision.text.zzd
                @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                public final Object zza(Object obj) {
                    zzpe zzpeVar = (zzpe) obj;
                    return new Text.Line(zzpeVar, matrix, zzpeVar.zzb(), zzpeVar.zza());
                }
            });
        }

        @NonNull
        public synchronized List<Line> getLines() {
            return this.e;
        }

        @NonNull
        public String getText() {
            return zza();
        }

        public TextBlock(@NonNull String str, @NonNull Rect rect, @NonNull List list, @NonNull String str2, @Nullable Matrix matrix, @NonNull List list2) {
            super(str, rect, list, str2, matrix);
            this.e = list2;
        }
    }

    /* loaded from: classes10.dex */
    public static class Element extends a {
        @GuardedBy("this")
        public final List e;
        public final float f;
        public final float g;

        public Element(@NonNull zzpc zzpcVar, @Nullable final Matrix matrix) {
            super(zzpcVar.zze(), zzpcVar.zzc(), zzpcVar.zzf(), zzpcVar.zzd(), matrix);
            this.f = zzpcVar.zzb();
            this.g = zzpcVar.zza();
            List zzg = zzpcVar.zzg();
            this.e = zzbw.zza(zzg == null ? new ArrayList() : zzg, new zzu() { // from class: com.google.mlkit.vision.text.zzb
                @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                public final Object zza(Object obj) {
                    return new Text.Symbol((zzpi) obj, matrix);
                }
            });
        }

        public float getAngle() {
            return this.g;
        }

        public float getConfidence() {
            return this.f;
        }

        @NonNull
        public synchronized List<Symbol> getSymbols() {
            return this.e;
        }

        @NonNull
        public String getText() {
            return zza();
        }

        public Element(@NonNull String str, @NonNull Rect rect, @NonNull List list, @NonNull String str2, @Nullable Matrix matrix, float f, float f2, @NonNull List list2) {
            super(str, rect, list, str2, matrix);
            this.f = f;
            this.g = f2;
            this.e = list2;
        }
    }
}
