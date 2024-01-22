package com.shockwave.pdfium;

import android.graphics.RectF;
import android.os.ParcelFileDescriptor;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public class PdfDocument {

    /* renamed from: a  reason: collision with root package name */
    public long f13688a;
    public ParcelFileDescriptor b;
    public final Map<Integer, Long> c = new ArrayMap();

    /* loaded from: classes12.dex */
    public static class Bookmark {

        /* renamed from: a  reason: collision with root package name */
        public List<Bookmark> f13689a = new ArrayList();
        public String b;
        public long c;

        public List<Bookmark> getChildren() {
            return this.f13689a;
        }

        public long getPageIdx() {
            return this.c;
        }

        public String getTitle() {
            return this.b;
        }

        public boolean hasChildren() {
            return !this.f13689a.isEmpty();
        }
    }

    /* loaded from: classes12.dex */
    public static class Link {

        /* renamed from: a  reason: collision with root package name */
        public RectF f13690a;
        public Integer b;
        public String c;

        public Link(RectF rectF, Integer num, String str) {
            this.f13690a = rectF;
            this.b = num;
            this.c = str;
        }

        public RectF getBounds() {
            return this.f13690a;
        }

        public Integer getDestPageIdx() {
            return this.b;
        }

        public String getUri() {
            return this.c;
        }
    }

    /* loaded from: classes12.dex */
    public static class Meta {

        /* renamed from: a  reason: collision with root package name */
        public String f13691a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;

        public String getAuthor() {
            return this.b;
        }

        public String getCreationDate() {
            return this.g;
        }

        public String getCreator() {
            return this.e;
        }

        public String getKeywords() {
            return this.d;
        }

        public String getModDate() {
            return this.h;
        }

        public String getProducer() {
            return this.f;
        }

        public String getSubject() {
            return this.c;
        }

        public String getTitle() {
            return this.f13691a;
        }
    }

    public boolean hasPage(int i) {
        return this.c.containsKey(Integer.valueOf(i));
    }
}
