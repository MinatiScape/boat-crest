package com.google.firebase.ml.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzmw;
import com.google.android.gms.vision.text.Text;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes10.dex */
public class FirebaseVisionText {
    public static final FirebaseVisionText zzbut = new FirebaseVisionText("", new ArrayList());

    /* renamed from: a  reason: collision with root package name */
    public final List<TextBlock> f11470a;
    public final String b;

    /* loaded from: classes10.dex */
    public static class Element extends a {
        public Element(@NonNull com.google.android.gms.vision.text.Element element) {
            super(element);
        }

        @Override // com.google.firebase.ml.vision.text.FirebaseVisionText.a
        @Nullable
        public /* bridge */ /* synthetic */ Rect getBoundingBox() {
            return super.getBoundingBox();
        }

        @Override // com.google.firebase.ml.vision.text.FirebaseVisionText.a
        @Nullable
        public /* bridge */ /* synthetic */ Float getConfidence() {
            return super.getConfidence();
        }

        @Override // com.google.firebase.ml.vision.text.FirebaseVisionText.a
        @Nullable
        public /* bridge */ /* synthetic */ Point[] getCornerPoints() {
            return super.getCornerPoints();
        }

        @Override // com.google.firebase.ml.vision.text.FirebaseVisionText.a
        @NonNull
        public /* bridge */ /* synthetic */ List getRecognizedLanguages() {
            return super.getRecognizedLanguages();
        }

        @Override // com.google.firebase.ml.vision.text.FirebaseVisionText.a
        @NonNull
        public /* bridge */ /* synthetic */ String getText() {
            return super.getText();
        }

        public Element(@NonNull String str, @Nullable Rect rect, @NonNull List<RecognizedLanguage> list, @Nullable Float f) {
            super(str, rect, list, f);
        }
    }

    public FirebaseVisionText(@NonNull SparseArray<com.google.android.gms.vision.text.TextBlock> sparseArray) {
        this.f11470a = new ArrayList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sparseArray.size(); i++) {
            com.google.android.gms.vision.text.TextBlock textBlock = sparseArray.get(sparseArray.keyAt(i));
            if (textBlock != null) {
                TextBlock textBlock2 = new TextBlock(textBlock);
                this.f11470a.add(textBlock2);
                if (sb.length() != 0) {
                    sb.append("\n");
                }
                if (textBlock.getValue() != null) {
                    sb.append(textBlock2.getText());
                }
            }
        }
        this.b = sb.toString();
    }

    @NonNull
    public String getText() {
        return this.b;
    }

    @NonNull
    public List<TextBlock> getTextBlocks() {
        return Collections.unmodifiableList(this.f11470a);
    }

    /* loaded from: classes10.dex */
    public static class Line extends a {
        @GuardedBy("this")
        public final List<Element> f;

        public Line(@NonNull com.google.android.gms.vision.text.Line line) {
            super(line);
            this.f = new ArrayList();
            for (Text text : line.getComponents()) {
                if (text instanceof com.google.android.gms.vision.text.Element) {
                    this.f.add(new Element((com.google.android.gms.vision.text.Element) text));
                } else {
                    Log.e("FirebaseVisionText", "A subcomponent of line is should be an element!");
                }
            }
        }

        @Override // com.google.firebase.ml.vision.text.FirebaseVisionText.a
        @Nullable
        public /* bridge */ /* synthetic */ Rect getBoundingBox() {
            return super.getBoundingBox();
        }

        @Override // com.google.firebase.ml.vision.text.FirebaseVisionText.a
        @Nullable
        public /* bridge */ /* synthetic */ Float getConfidence() {
            return super.getConfidence();
        }

        @Override // com.google.firebase.ml.vision.text.FirebaseVisionText.a
        @Nullable
        public /* bridge */ /* synthetic */ Point[] getCornerPoints() {
            return super.getCornerPoints();
        }

        @NonNull
        public synchronized List<Element> getElements() {
            return this.f;
        }

        @Override // com.google.firebase.ml.vision.text.FirebaseVisionText.a
        @NonNull
        public /* bridge */ /* synthetic */ List getRecognizedLanguages() {
            return super.getRecognizedLanguages();
        }

        @Override // com.google.firebase.ml.vision.text.FirebaseVisionText.a
        @NonNull
        public /* bridge */ /* synthetic */ String getText() {
            return super.getText();
        }

        public Line(@NonNull String str, @Nullable Rect rect, @NonNull List<RecognizedLanguage> list, @NonNull List<Element> list2, @Nullable Float f) {
            super(str, rect, list, f);
            this.f = list2;
        }
    }

    /* loaded from: classes10.dex */
    public static class TextBlock extends a {
        @GuardedBy("this")
        public final List<Line> f;

        public TextBlock(@NonNull com.google.android.gms.vision.text.TextBlock textBlock) {
            super(textBlock);
            this.f = new ArrayList();
            for (Text text : textBlock.getComponents()) {
                if (text instanceof com.google.android.gms.vision.text.Line) {
                    this.f.add(new Line((com.google.android.gms.vision.text.Line) text));
                } else {
                    Log.e("FirebaseVisionText", "A subcomponent of textblock is should be a line!");
                }
            }
        }

        @Override // com.google.firebase.ml.vision.text.FirebaseVisionText.a
        @Nullable
        public /* bridge */ /* synthetic */ Rect getBoundingBox() {
            return super.getBoundingBox();
        }

        @Override // com.google.firebase.ml.vision.text.FirebaseVisionText.a
        @Nullable
        public /* bridge */ /* synthetic */ Float getConfidence() {
            return super.getConfidence();
        }

        @Override // com.google.firebase.ml.vision.text.FirebaseVisionText.a
        @Nullable
        public /* bridge */ /* synthetic */ Point[] getCornerPoints() {
            return super.getCornerPoints();
        }

        @NonNull
        public synchronized List<Line> getLines() {
            return this.f;
        }

        @Override // com.google.firebase.ml.vision.text.FirebaseVisionText.a
        @NonNull
        public /* bridge */ /* synthetic */ List getRecognizedLanguages() {
            return super.getRecognizedLanguages();
        }

        @Override // com.google.firebase.ml.vision.text.FirebaseVisionText.a
        @NonNull
        public /* bridge */ /* synthetic */ String getText() {
            return super.getText();
        }

        public TextBlock(@NonNull String str, @Nullable Rect rect, @NonNull List<RecognizedLanguage> list, @NonNull List<Line> list2, @Nullable Float f) {
            super(str, rect, list, f);
            this.f = list2;
        }
    }

    /* loaded from: classes10.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final String f11471a;
        public final Rect b;
        public final Point[] c;
        public final Float d;
        public final List<RecognizedLanguage> e;

        public a(@NonNull Text text) {
            Preconditions.checkNotNull(text, "Text to construct FirebaseVisionText classes can't be null");
            this.d = null;
            this.f11471a = text.getValue();
            this.b = text.getBoundingBox();
            this.c = text.getCornerPoints();
            this.e = zzmw.zzji();
        }

        @Nullable
        public Rect getBoundingBox() {
            return this.b;
        }

        @Nullable
        public Float getConfidence() {
            return this.d;
        }

        @Nullable
        public Point[] getCornerPoints() {
            return this.c;
        }

        @NonNull
        public List<RecognizedLanguage> getRecognizedLanguages() {
            return this.e;
        }

        @NonNull
        public String getText() {
            String str = this.f11471a;
            return str == null ? "" : str;
        }

        public a(@NonNull String str, @Nullable Rect rect, @NonNull List<RecognizedLanguage> list, @Nullable Float f) {
            Preconditions.checkNotNull(str, "Text string cannot be null");
            Preconditions.checkNotNull(list, "Text languages cannot be null");
            this.d = f;
            this.c = null;
            this.f11471a = str;
            this.b = rect;
            this.e = list;
        }
    }

    public FirebaseVisionText(@NonNull String str, @NonNull List<TextBlock> list) {
        ArrayList arrayList = new ArrayList();
        this.f11470a = arrayList;
        this.b = str;
        arrayList.addAll(list);
    }
}
