package com.google.android.gms.internal.firebase_ml;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.RecognizedLanguage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes7.dex */
public final class zzsy {

    /* renamed from: a  reason: collision with root package name */
    public static final GmsLogger f8809a = new GmsLogger("TextAnnotationConverter", "");

    @Nullable
    @VisibleForTesting
    public static String a(@NonNull zzlq zzlqVar) {
        Preconditions.checkNotNull(zzlqVar, "Input Word can not be null");
        if (zzlqVar.getSymbols() == null || zzlqVar.getSymbols().isEmpty()) {
            return null;
        }
        zzll zzllVar = (zzll) zznb.zzc(zzlqVar.getSymbols());
        if (zzllVar.zzim() == null || zzllVar.zzim().zzix() == null) {
            return null;
        }
        return ((zzll) zznb.zzc(zzlqVar.getSymbols())).zzim().zzix().getType();
    }

    public static FirebaseVisionText b(@Nullable zzlk zzlkVar, float f) {
        Iterator<zzlf> it;
        Iterator<zzkm> it2;
        boolean z;
        FirebaseVisionText.TextBlock textBlock;
        Iterator<zzlf> it3;
        Iterator<zzkm> it4;
        Iterator<zzle> it5;
        boolean z2;
        String sb;
        FirebaseVisionText.Element element;
        if (zzlkVar == null) {
            return FirebaseVisionText.zzbut;
        }
        if (zzlkVar.getPages().size() <= 0) {
            f8809a.d("TextAnnotationConverter", "Text Annotation is null, return empty Vision Text");
            return FirebaseVisionText.zzbut;
        }
        boolean z3 = true;
        if (zzlkVar.getPages().size() > 1) {
            f8809a.d("TextAnnotationConverter", "Text Annotation has more than one page, which should not happen");
        }
        ArrayList arrayList = new ArrayList();
        Iterator<zzlf> it6 = zzlkVar.getPages().iterator();
        while (it6.hasNext()) {
            Iterator<zzkm> it7 = it6.next().getBlocks().iterator();
            while (it7.hasNext()) {
                zzkm next = it7.next();
                Preconditions.checkNotNull(next, "Input block can not be null");
                ArrayList arrayList2 = new ArrayList();
                if (next.getParagraphs() == null) {
                    it = it6;
                    it2 = it7;
                    textBlock = null;
                    z = z3;
                } else {
                    Iterator<zzle> it8 = next.getParagraphs().iterator();
                    while (it8.hasNext()) {
                        zzle next2 = it8.next();
                        if (next2 != null) {
                            Preconditions.checkNotNull(next2, "Input Paragraph can not be null");
                            ArrayList arrayList3 = new ArrayList();
                            ArrayList arrayList4 = new ArrayList();
                            HashSet<RecognizedLanguage> hashSet = new HashSet();
                            StringBuilder sb2 = new StringBuilder();
                            int i = 0;
                            float f2 = 0.0f;
                            while (i < next2.getWords().size()) {
                                zzlq zzlqVar = next2.getWords().get(i);
                                if (zzlqVar != null) {
                                    Preconditions.checkNotNull(zzlqVar, "Input Word can not be null");
                                    Rect zza = zzrq.zza(zzlqVar.zzil(), f);
                                    it3 = it6;
                                    List<RecognizedLanguage> zze = zze(zzlqVar.zzim());
                                    Preconditions.checkNotNull(zzlqVar, "Input Word can not be null");
                                    String str = "";
                                    if (zzlqVar.getSymbols() == null) {
                                        it4 = it7;
                                        sb = "";
                                    } else {
                                        StringBuilder sb3 = new StringBuilder();
                                        for (zzll zzllVar : zzlqVar.getSymbols()) {
                                            sb3.append(zzllVar.getText());
                                            it7 = it7;
                                        }
                                        it4 = it7;
                                        sb = sb3.toString();
                                    }
                                    if (sb.isEmpty()) {
                                        it5 = it8;
                                        element = null;
                                    } else {
                                        it5 = it8;
                                        element = new FirebaseVisionText.Element(sb, zza, zze, zzlqVar.getConfidence());
                                    }
                                    if (element != null) {
                                        arrayList4.add(element);
                                        float zza2 = f2 + zzrq.zza(element.getConfidence());
                                        hashSet.addAll(element.getRecognizedLanguages());
                                        sb2.append(element.getText());
                                        Preconditions.checkNotNull(zzlqVar, "Input word can not be null");
                                        String a2 = a(zzlqVar);
                                        if (a2 != null) {
                                            if (a2.equals("SPACE") || a2.equals("SURE_SPACE")) {
                                                str = HexStringBuilder.DEFAULT_SEPARATOR;
                                            } else if (a2.equals("HYPHEN")) {
                                                str = "-";
                                            }
                                        }
                                        sb2.append(str);
                                        Preconditions.checkNotNull(zzlqVar, "Input word can not be null");
                                        String a3 = a(zzlqVar);
                                        if (a3 != null && (a3.equals("EOL_SURE_SPACE") || a3.equals("LINE_BREAK") || a3.equals("HYPHEN"))) {
                                            z2 = true;
                                        } else {
                                            z2 = true;
                                            if (i != next2.getWords().size() - 1) {
                                                f2 = zza2;
                                            }
                                        }
                                        Preconditions.checkNotNull(arrayList4, "Input elements can not be null");
                                        int size = arrayList4.size();
                                        int i2 = 0;
                                        Rect rect = null;
                                        while (i2 < size) {
                                            Object obj = arrayList4.get(i2);
                                            i2++;
                                            FirebaseVisionText.Element element2 = (FirebaseVisionText.Element) obj;
                                            if (element2.getBoundingBox() != null) {
                                                Rect rect2 = rect == null ? new Rect() : rect;
                                                rect2.union(element2.getBoundingBox());
                                                rect = rect2;
                                            }
                                        }
                                        String sb4 = sb2.toString();
                                        ArrayList arrayList5 = new ArrayList();
                                        for (RecognizedLanguage recognizedLanguage : hashSet) {
                                            if (recognizedLanguage != null && recognizedLanguage.getLanguageCode() != null && !recognizedLanguage.getLanguageCode().isEmpty()) {
                                                arrayList5.add(recognizedLanguage);
                                            }
                                        }
                                        arrayList3.add(new FirebaseVisionText.Line(sb4, rect, arrayList5, arrayList4, Float.compare(zza2, 0.0f) > 0 ? Float.valueOf(zza2 / arrayList4.size()) : null));
                                        ArrayList arrayList6 = new ArrayList();
                                        hashSet.clear();
                                        arrayList4 = arrayList6;
                                        sb2 = new StringBuilder();
                                        f2 = 0.0f;
                                    } else {
                                        z2 = true;
                                    }
                                } else {
                                    it3 = it6;
                                    it4 = it7;
                                    it5 = it8;
                                    z2 = z3;
                                }
                                i++;
                                z3 = z2;
                                it6 = it3;
                                it7 = it4;
                                it8 = it5;
                            }
                            arrayList2.addAll(arrayList3);
                            it7 = it7;
                        }
                    }
                    it = it6;
                    it2 = it7;
                    z = z3;
                    if (arrayList2.isEmpty()) {
                        textBlock = null;
                    } else {
                        StringBuilder sb5 = new StringBuilder();
                        int size2 = arrayList2.size();
                        int i3 = 0;
                        while (i3 < size2) {
                            Object obj2 = arrayList2.get(i3);
                            i3++;
                            sb5.append(((FirebaseVisionText.Line) obj2).getText());
                            sb5.append("\n");
                        }
                        textBlock = new FirebaseVisionText.TextBlock(sb5.toString(), zzrq.zza(next.zzil(), f), zze(next.zzim()), arrayList2, next.getConfidence());
                    }
                }
                if (textBlock != null) {
                    arrayList.add(textBlock);
                }
                z3 = z;
                it6 = it;
                it7 = it2;
            }
        }
        return new FirebaseVisionText(zzlkVar.getText(), arrayList);
    }

    public static List<RecognizedLanguage> zze(@Nullable zzln zzlnVar) {
        ArrayList arrayList = new ArrayList();
        if (zzlnVar != null && zzlnVar.zziy() != null) {
            for (zzkt zzktVar : zzlnVar.zziy()) {
                RecognizedLanguage zza = RecognizedLanguage.zza(zzktVar);
                if (zza != null) {
                    arrayList.add(zza);
                }
            }
        }
        return arrayList;
    }
}
