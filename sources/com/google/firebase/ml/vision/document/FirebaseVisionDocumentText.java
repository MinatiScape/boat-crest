package com.google.firebase.ml.vision.document;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.internal.firebase_ml.zzkm;
import com.google.android.gms.internal.firebase_ml.zzkt;
import com.google.android.gms.internal.firebase_ml.zzle;
import com.google.android.gms.internal.firebase_ml.zzlf;
import com.google.android.gms.internal.firebase_ml.zzlk;
import com.google.android.gms.internal.firebase_ml.zzll;
import com.google.android.gms.internal.firebase_ml.zzln;
import com.google.android.gms.internal.firebase_ml.zzlq;
import com.google.android.gms.internal.firebase_ml.zzms;
import com.google.android.gms.internal.firebase_ml.zzmw;
import com.google.android.gms.internal.firebase_ml.zzrq;
import com.google.android.gms.internal.firebase_ml.zzsy;
import com.google.firebase.ml.vision.text.RecognizedLanguage;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class FirebaseVisionDocumentText {
    public static final FirebaseVisionDocumentText c = new FirebaseVisionDocumentText("", new ArrayList());

    /* renamed from: a  reason: collision with root package name */
    public final String f11446a;
    public final List<Block> b;

    /* loaded from: classes10.dex */
    public static class Block extends a {
        public final List<Paragraph> f;

        public Block(@NonNull List<RecognizedLanguage> list, @Nullable RecognizedBreak recognizedBreak, @Nullable Rect rect, @NonNull List<Paragraph> list2, @NonNull String str, Float f) {
            super(list, recognizedBreak, rect, str, f);
            this.f = list2;
        }

        public static Block a(@NonNull zzkm zzkmVar, float f) {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            List<RecognizedLanguage> zze = zzsy.zze(zzkmVar.zzim());
            if (zzkmVar.getParagraphs() != null) {
                for (zzle zzleVar : zzkmVar.getParagraphs()) {
                    if (zzleVar != null) {
                        Paragraph a2 = Paragraph.a(zzleVar, f);
                        if (sb.length() != 0) {
                            sb.append("\n");
                        }
                        sb.append(a2.getText());
                        arrayList.add(a2);
                    }
                }
            }
            return new Block(zze, new RecognizedBreak(), zzrq.zza(zzkmVar.zzil(), f), arrayList, sb.toString(), zzkmVar.getConfidence());
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        @Nullable
        public /* bridge */ /* synthetic */ Rect getBoundingBox() {
            return super.getBoundingBox();
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        @Nullable
        public /* bridge */ /* synthetic */ Float getConfidence() {
            return super.getConfidence();
        }

        @NonNull
        public List<Paragraph> getParagraphs() {
            return this.f;
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        @Nullable
        public /* bridge */ /* synthetic */ RecognizedBreak getRecognizedBreak() {
            return super.getRecognizedBreak();
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        public /* bridge */ /* synthetic */ List getRecognizedLanguages() {
            return super.getRecognizedLanguages();
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        public /* bridge */ /* synthetic */ String getText() {
            return super.getText();
        }
    }

    /* loaded from: classes10.dex */
    public static class Paragraph extends a {
        public final List<Word> f;

        public Paragraph(@NonNull List<RecognizedLanguage> list, @Nullable RecognizedBreak recognizedBreak, @Nullable Rect rect, @NonNull List<Word> list2, @NonNull String str, @Nullable Float f) {
            super(list, recognizedBreak, rect, str, f);
            this.f = list2;
        }

        public static Paragraph a(@NonNull zzle zzleVar, float f) {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            List<RecognizedLanguage> zze = zzsy.zze(zzleVar.zzim());
            if (zzleVar.getWords() != null) {
                for (zzlq zzlqVar : zzleVar.getWords()) {
                    if (zzlqVar != null) {
                        Word a2 = Word.a(zzlqVar, f);
                        sb.append(a2.getText());
                        sb.append(FirebaseVisionDocumentText.b(a2.getRecognizedBreak()));
                        arrayList.add(a2);
                    }
                }
            }
            return new Paragraph(zze, new RecognizedBreak(), zzrq.zza(zzleVar.zzil(), f), arrayList, sb.toString(), zzleVar.getConfidence());
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        @Nullable
        public /* bridge */ /* synthetic */ Rect getBoundingBox() {
            return super.getBoundingBox();
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        @Nullable
        public /* bridge */ /* synthetic */ Float getConfidence() {
            return super.getConfidence();
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        @Nullable
        public /* bridge */ /* synthetic */ RecognizedBreak getRecognizedBreak() {
            return super.getRecognizedBreak();
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        public /* bridge */ /* synthetic */ List getRecognizedLanguages() {
            return super.getRecognizedLanguages();
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        public /* bridge */ /* synthetic */ String getText() {
            return super.getText();
        }

        @NonNull
        public List<Word> getWords() {
            return this.f;
        }
    }

    /* loaded from: classes10.dex */
    public static class RecognizedBreak {
        public static final int EOL_SURE_SPACE = 3;
        public static final int HYPHEN = 4;
        public static final int LINE_BREAK = 5;
        public static final int SPACE = 1;
        public static final int SURE_SPACE = 2;
        public static final int UNKNOWN = 0;
        @BreakType

        /* renamed from: a  reason: collision with root package name */
        public final int f11447a;
        public final boolean b;

        @Retention(RetentionPolicy.CLASS)
        /* loaded from: classes10.dex */
        public @interface BreakType {
        }

        public RecognizedBreak(@BreakType int i, boolean z) {
            this.f11447a = i;
            this.b = z;
        }

        /* JADX WARN: Removed duplicated region for block: B:40:0x007a  */
        @androidx.annotation.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.RecognizedBreak a(@androidx.annotation.Nullable com.google.android.gms.internal.firebase_ml.zzln r8) {
            /*
                if (r8 == 0) goto L8c
                com.google.android.gms.internal.firebase_ml.zzkq r0 = r8.zzix()
                if (r0 != 0) goto La
                goto L8c
            La:
                com.google.android.gms.internal.firebase_ml.zzkq r0 = r8.zzix()
                java.lang.String r0 = r0.getType()
                r1 = 4
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r0 == 0) goto L6f
                com.google.android.gms.internal.firebase_ml.zzkq r0 = r8.zzix()
                java.lang.String r0 = r0.getType()
                r0.hashCode()
                r6 = -1
                int r7 = r0.hashCode()
                switch(r7) {
                    case -1651884996: goto L59;
                    case -1571028039: goto L4e;
                    case 79100134: goto L43;
                    case 1541383380: goto L38;
                    case 2145946930: goto L2d;
                    default: goto L2c;
                }
            L2c:
                goto L63
            L2d:
                java.lang.String r7 = "HYPHEN"
                boolean r0 = r0.equals(r7)
                if (r0 != 0) goto L36
                goto L63
            L36:
                r6 = r1
                goto L63
            L38:
                java.lang.String r7 = "LINE_BREAK"
                boolean r0 = r0.equals(r7)
                if (r0 != 0) goto L41
                goto L63
            L41:
                r6 = r2
                goto L63
            L43:
                java.lang.String r7 = "SPACE"
                boolean r0 = r0.equals(r7)
                if (r0 != 0) goto L4c
                goto L63
            L4c:
                r6 = r3
                goto L63
            L4e:
                java.lang.String r7 = "EOL_SURE_SPACE"
                boolean r0 = r0.equals(r7)
                if (r0 != 0) goto L57
                goto L63
            L57:
                r6 = r4
                goto L63
            L59:
                java.lang.String r7 = "SURE_SPACE"
                boolean r0 = r0.equals(r7)
                if (r0 != 0) goto L62
                goto L63
            L62:
                r6 = r5
            L63:
                switch(r6) {
                    case 0: goto L6d;
                    case 1: goto L6b;
                    case 2: goto L69;
                    case 3: goto L67;
                    case 4: goto L70;
                    default: goto L66;
                }
            L66:
                goto L6f
            L67:
                r1 = 5
                goto L70
            L69:
                r1 = r4
                goto L70
            L6b:
                r1 = r2
                goto L70
            L6d:
                r1 = r3
                goto L70
            L6f:
                r1 = r5
            L70:
                com.google.android.gms.internal.firebase_ml.zzkq r0 = r8.zzix()
                java.lang.Boolean r0 = r0.zzip()
                if (r0 == 0) goto L86
                com.google.android.gms.internal.firebase_ml.zzkq r8 = r8.zzix()
                java.lang.Boolean r8 = r8.zzip()
                boolean r5 = r8.booleanValue()
            L86:
                com.google.firebase.ml.vision.document.FirebaseVisionDocumentText$RecognizedBreak r8 = new com.google.firebase.ml.vision.document.FirebaseVisionDocumentText$RecognizedBreak
                r8.<init>(r1, r5)
                return r8
            L8c:
                r8 = 0
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.RecognizedBreak.a(com.google.android.gms.internal.firebase_ml.zzln):com.google.firebase.ml.vision.document.FirebaseVisionDocumentText$RecognizedBreak");
        }

        @BreakType
        public int getDetectedBreakType() {
            return this.f11447a;
        }

        public boolean getIsPrefix() {
            return this.b;
        }
    }

    /* loaded from: classes10.dex */
    public static class Symbol extends a {
        public Symbol(@NonNull List<RecognizedLanguage> list, @Nullable RecognizedBreak recognizedBreak, @Nullable Rect rect, @NonNull String str, Float f) {
            super(list, recognizedBreak, rect, str, f);
        }

        public static Symbol a(@NonNull zzll zzllVar, float f) {
            return new Symbol(FirebaseVisionDocumentText.c(zzllVar.zzim()), RecognizedBreak.a(zzllVar.zzim()), zzrq.zza(zzllVar.zzil(), f), zzrq.zzcd(zzllVar.getText()), zzllVar.getConfidence());
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        @Nullable
        public /* bridge */ /* synthetic */ Rect getBoundingBox() {
            return super.getBoundingBox();
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        @Nullable
        public /* bridge */ /* synthetic */ Float getConfidence() {
            return super.getConfidence();
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        @Nullable
        public /* bridge */ /* synthetic */ RecognizedBreak getRecognizedBreak() {
            return super.getRecognizedBreak();
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        public /* bridge */ /* synthetic */ List getRecognizedLanguages() {
            return super.getRecognizedLanguages();
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        public /* bridge */ /* synthetic */ String getText() {
            return super.getText();
        }
    }

    /* loaded from: classes10.dex */
    public static class Word extends a {
        public final List<Symbol> f;

        public Word(@NonNull List<RecognizedLanguage> list, @Nullable RecognizedBreak recognizedBreak, @Nullable Rect rect, @NonNull List<Symbol> list2, @NonNull String str, @Nullable Float f) {
            super(list, recognizedBreak, rect, str, f);
            this.f = list2;
        }

        public static Word a(@NonNull zzlq zzlqVar, float f) {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            List<RecognizedLanguage> zze = zzsy.zze(zzlqVar.zzim());
            RecognizedBreak recognizedBreak = null;
            if (zzlqVar.getSymbols() != null) {
                for (zzll zzllVar : zzlqVar.getSymbols()) {
                    if (zzllVar != null) {
                        Symbol a2 = Symbol.a(zzllVar, f);
                        RecognizedBreak recognizedBreak2 = a2.getRecognizedBreak();
                        sb.append(a2.getText());
                        arrayList.add(Symbol.a(zzllVar, f));
                        recognizedBreak = recognizedBreak2;
                    }
                }
            }
            return new Word(zze, recognizedBreak, zzrq.zza(zzlqVar.zzil(), f), arrayList, sb.toString(), zzlqVar.getConfidence());
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        @Nullable
        public /* bridge */ /* synthetic */ Rect getBoundingBox() {
            return super.getBoundingBox();
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        @Nullable
        public /* bridge */ /* synthetic */ Float getConfidence() {
            return super.getConfidence();
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        @Nullable
        public /* bridge */ /* synthetic */ RecognizedBreak getRecognizedBreak() {
            return super.getRecognizedBreak();
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        public /* bridge */ /* synthetic */ List getRecognizedLanguages() {
            return super.getRecognizedLanguages();
        }

        @NonNull
        public List<Symbol> getSymbols() {
            return this.f;
        }

        @Override // com.google.firebase.ml.vision.document.FirebaseVisionDocumentText.a
        public /* bridge */ /* synthetic */ String getText() {
            return super.getText();
        }
    }

    /* loaded from: classes10.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final List<RecognizedLanguage> f11448a;
        public final RecognizedBreak b;
        public final Rect c;
        public final String d;
        public final Float e;

        public a(@NonNull List<RecognizedLanguage> list, @Nullable RecognizedBreak recognizedBreak, @Nullable Rect rect, @NonNull String str, @Nullable Float f) {
            this.d = str;
            this.f11448a = list;
            this.b = recognizedBreak;
            this.c = rect;
            this.e = f;
        }

        @Nullable
        public Rect getBoundingBox() {
            return this.c;
        }

        @Nullable
        public Float getConfidence() {
            return this.e;
        }

        @Nullable
        public RecognizedBreak getRecognizedBreak() {
            return this.b;
        }

        public List<RecognizedLanguage> getRecognizedLanguages() {
            return this.f11448a;
        }

        public String getText() {
            return this.d;
        }
    }

    public FirebaseVisionDocumentText(@NonNull String str, @NonNull List<Block> list) {
        this.f11446a = str;
        this.b = list;
    }

    public static FirebaseVisionDocumentText a(@Nullable zzlk zzlkVar, float f) {
        if (zzlkVar == null) {
            return c;
        }
        String zzcd = zzrq.zzcd(zzlkVar.getText());
        ArrayList arrayList = new ArrayList();
        if (zzlkVar.getPages() != null) {
            for (zzlf zzlfVar : zzlkVar.getPages()) {
                if (zzlfVar != null) {
                    for (zzkm zzkmVar : zzlfVar.getBlocks()) {
                        if (zzkmVar != null) {
                            arrayList.add(Block.a(zzkmVar, f));
                        }
                    }
                }
            }
        }
        return new FirebaseVisionDocumentText(zzcd, arrayList);
    }

    public static String b(@Nullable RecognizedBreak recognizedBreak) {
        if (recognizedBreak == null) {
            return "";
        }
        int detectedBreakType = recognizedBreak.getDetectedBreakType();
        if (detectedBreakType == 1 || detectedBreakType == 2) {
            return HexStringBuilder.DEFAULT_SEPARATOR;
        }
        if (detectedBreakType != 3) {
            if (detectedBreakType == 4) {
                return "-\n";
            }
            if (detectedBreakType != 5) {
                return "";
            }
        }
        return "\n";
    }

    public static List<RecognizedLanguage> c(@Nullable zzln zzlnVar) {
        if (zzlnVar == null) {
            return zzmw.zzji();
        }
        ArrayList arrayList = new ArrayList();
        if (zzlnVar.zziy() != null) {
            for (zzkt zzktVar : zzlnVar.zziy()) {
                RecognizedLanguage zza = RecognizedLanguage.zza(zzktVar);
                if (zza != null) {
                    arrayList.add(zza);
                }
            }
        }
        return arrayList;
    }

    @NonNull
    public List<Block> getBlocks() {
        return this.b;
    }

    @NonNull
    public String getText() {
        return zzms.zzbb(this.f11446a);
    }
}
