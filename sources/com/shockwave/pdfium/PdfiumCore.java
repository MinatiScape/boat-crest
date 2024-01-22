package com.shockwave.pdfium;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.Surface;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.util.Size;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class PdfiumCore {
    public static final String b = "com.shockwave.pdfium.PdfiumCore";
    public static final Class c = FileDescriptor.class;
    public static final Object d;
    public static Field e;

    /* renamed from: a  reason: collision with root package name */
    public int f13692a;

    static {
        try {
            System.loadLibrary("c++_shared");
            System.loadLibrary("modpng");
            System.loadLibrary("modft2");
            System.loadLibrary("modpdfium");
            System.loadLibrary("jniPdfium");
        } catch (UnsatisfiedLinkError e2) {
            Log.e(b, "Native libraries failed to load - " + e2);
        }
        d = new Object();
        e = null;
    }

    public PdfiumCore(Context context) {
        this.f13692a = context.getResources().getDisplayMetrics().densityDpi;
        Log.d(b, "Starting PdfiumAndroid 1.9.0");
    }

    public static int getNumFd(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            if (e == null) {
                Field declaredField = c.getDeclaredField("descriptor");
                e = declaredField;
                declaredField.setAccessible(true);
            }
            return e.getInt(parcelFileDescriptor.getFileDescriptor());
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return -1;
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
            return -1;
        }
    }

    public final void a(List<PdfDocument.Bookmark> list, PdfDocument pdfDocument, long j) {
        PdfDocument.Bookmark bookmark = new PdfDocument.Bookmark();
        bookmark.b = nativeGetBookmarkTitle(j);
        bookmark.c = nativeGetBookmarkDestIndex(pdfDocument.f13688a, j);
        list.add(bookmark);
        Long nativeGetFirstChildBookmark = nativeGetFirstChildBookmark(pdfDocument.f13688a, Long.valueOf(j));
        if (nativeGetFirstChildBookmark != null) {
            a(bookmark.getChildren(), pdfDocument, nativeGetFirstChildBookmark.longValue());
        }
        Long nativeGetSiblingBookmark = nativeGetSiblingBookmark(pdfDocument.f13688a, j);
        if (nativeGetSiblingBookmark != null) {
            a(list, pdfDocument, nativeGetSiblingBookmark.longValue());
        }
    }

    public void closeDocument(PdfDocument pdfDocument) {
        synchronized (d) {
            for (Integer num : pdfDocument.c.keySet()) {
                nativeClosePage(pdfDocument.c.get(num).longValue());
            }
            pdfDocument.c.clear();
            nativeCloseDocument(pdfDocument.f13688a);
            ParcelFileDescriptor parcelFileDescriptor = pdfDocument.b;
            if (parcelFileDescriptor != null) {
                try {
                    parcelFileDescriptor.close();
                } catch (IOException unused) {
                }
                pdfDocument.b = null;
            }
        }
    }

    public PdfDocument.Meta getDocumentMeta(PdfDocument pdfDocument) {
        PdfDocument.Meta meta;
        synchronized (d) {
            meta = new PdfDocument.Meta();
            meta.f13691a = nativeGetDocumentMetaText(pdfDocument.f13688a, "Title");
            meta.b = nativeGetDocumentMetaText(pdfDocument.f13688a, "Author");
            meta.c = nativeGetDocumentMetaText(pdfDocument.f13688a, "Subject");
            meta.d = nativeGetDocumentMetaText(pdfDocument.f13688a, "Keywords");
            meta.e = nativeGetDocumentMetaText(pdfDocument.f13688a, "Creator");
            meta.f = nativeGetDocumentMetaText(pdfDocument.f13688a, "Producer");
            meta.g = nativeGetDocumentMetaText(pdfDocument.f13688a, "CreationDate");
            meta.h = nativeGetDocumentMetaText(pdfDocument.f13688a, "ModDate");
        }
        return meta;
    }

    public int getPageCount(PdfDocument pdfDocument) {
        int nativeGetPageCount;
        synchronized (d) {
            nativeGetPageCount = nativeGetPageCount(pdfDocument.f13688a);
        }
        return nativeGetPageCount;
    }

    public int getPageHeight(PdfDocument pdfDocument, int i) {
        synchronized (d) {
            Long l = pdfDocument.c.get(Integer.valueOf(i));
            if (l != null) {
                return nativeGetPageHeightPixel(l.longValue(), this.f13692a);
            }
            return 0;
        }
    }

    public int getPageHeightPoint(PdfDocument pdfDocument, int i) {
        synchronized (d) {
            Long l = pdfDocument.c.get(Integer.valueOf(i));
            if (l != null) {
                return nativeGetPageHeightPoint(l.longValue());
            }
            return 0;
        }
    }

    public List<PdfDocument.Link> getPageLinks(PdfDocument pdfDocument, int i) {
        long[] nativeGetPageLinks;
        synchronized (d) {
            ArrayList arrayList = new ArrayList();
            Long l = pdfDocument.c.get(Integer.valueOf(i));
            if (l == null) {
                return arrayList;
            }
            for (long j : nativeGetPageLinks(l.longValue())) {
                Integer nativeGetDestPageIndex = nativeGetDestPageIndex(pdfDocument.f13688a, j);
                String nativeGetLinkURI = nativeGetLinkURI(pdfDocument.f13688a, j);
                RectF nativeGetLinkRect = nativeGetLinkRect(j);
                if (nativeGetLinkRect != null && (nativeGetDestPageIndex != null || nativeGetLinkURI != null)) {
                    arrayList.add(new PdfDocument.Link(nativeGetLinkRect, nativeGetDestPageIndex, nativeGetLinkURI));
                }
            }
            return arrayList;
        }
    }

    public Size getPageSize(PdfDocument pdfDocument, int i) {
        Size nativeGetPageSizeByIndex;
        synchronized (d) {
            nativeGetPageSizeByIndex = nativeGetPageSizeByIndex(pdfDocument.f13688a, i, this.f13692a);
        }
        return nativeGetPageSizeByIndex;
    }

    public int getPageWidth(PdfDocument pdfDocument, int i) {
        synchronized (d) {
            Long l = pdfDocument.c.get(Integer.valueOf(i));
            if (l != null) {
                return nativeGetPageWidthPixel(l.longValue(), this.f13692a);
            }
            return 0;
        }
    }

    public int getPageWidthPoint(PdfDocument pdfDocument, int i) {
        synchronized (d) {
            Long l = pdfDocument.c.get(Integer.valueOf(i));
            if (l != null) {
                return nativeGetPageWidthPoint(l.longValue());
            }
            return 0;
        }
    }

    public List<PdfDocument.Bookmark> getTableOfContents(PdfDocument pdfDocument) {
        ArrayList arrayList;
        synchronized (d) {
            arrayList = new ArrayList();
            Long nativeGetFirstChildBookmark = nativeGetFirstChildBookmark(pdfDocument.f13688a, null);
            if (nativeGetFirstChildBookmark != null) {
                a(arrayList, pdfDocument, nativeGetFirstChildBookmark.longValue());
            }
        }
        return arrayList;
    }

    public Point mapPageCoordsToDevice(PdfDocument pdfDocument, int i, int i2, int i3, int i4, int i5, int i6, double d2, double d3) {
        return nativePageCoordsToDevice(pdfDocument.c.get(Integer.valueOf(i)).longValue(), i2, i3, i4, i5, i6, d2, d3);
    }

    public RectF mapRectToDevice(PdfDocument pdfDocument, int i, int i2, int i3, int i4, int i5, int i6, RectF rectF) {
        Point mapPageCoordsToDevice = mapPageCoordsToDevice(pdfDocument, i, i2, i3, i4, i5, i6, rectF.left, rectF.top);
        Point mapPageCoordsToDevice2 = mapPageCoordsToDevice(pdfDocument, i, i2, i3, i4, i5, i6, rectF.right, rectF.bottom);
        return new RectF(mapPageCoordsToDevice.x, mapPageCoordsToDevice.y, mapPageCoordsToDevice2.x, mapPageCoordsToDevice2.y);
    }

    public final native void nativeCloseDocument(long j);

    public final native void nativeClosePage(long j);

    public final native long nativeGetBookmarkDestIndex(long j, long j2);

    public final native String nativeGetBookmarkTitle(long j);

    public final native Integer nativeGetDestPageIndex(long j, long j2);

    public final native String nativeGetDocumentMetaText(long j, String str);

    public final native Long nativeGetFirstChildBookmark(long j, Long l);

    public final native RectF nativeGetLinkRect(long j);

    public final native String nativeGetLinkURI(long j, long j2);

    public final native int nativeGetPageCount(long j);

    public final native int nativeGetPageHeightPixel(long j, int i);

    public final native int nativeGetPageHeightPoint(long j);

    public final native long[] nativeGetPageLinks(long j);

    public final native Size nativeGetPageSizeByIndex(long j, int i, int i2);

    public final native int nativeGetPageWidthPixel(long j, int i);

    public final native int nativeGetPageWidthPoint(long j);

    public final native Long nativeGetSiblingBookmark(long j, long j2);

    public final native long nativeLoadPage(long j, int i);

    public final native long[] nativeLoadPages(long j, int i, int i2);

    public final native long nativeOpenDocument(int i, String str);

    public final native long nativeOpenMemDocument(byte[] bArr, String str);

    public final native Point nativePageCoordsToDevice(long j, int i, int i2, int i3, int i4, int i5, double d2, double d3);

    public final native void nativeRenderPage(long j, Surface surface, int i, int i2, int i3, int i4, int i5, boolean z);

    public final native void nativeRenderPageBitmap(long j, Bitmap bitmap, int i, int i2, int i3, int i4, int i5, boolean z);

    public PdfDocument newDocument(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        return newDocument(parcelFileDescriptor, (String) null);
    }

    public long openPage(PdfDocument pdfDocument, int i) {
        long nativeLoadPage;
        synchronized (d) {
            nativeLoadPage = nativeLoadPage(pdfDocument.f13688a, i);
            pdfDocument.c.put(Integer.valueOf(i), Long.valueOf(nativeLoadPage));
        }
        return nativeLoadPage;
    }

    public void renderPage(PdfDocument pdfDocument, Surface surface, int i, int i2, int i3, int i4, int i5) {
        renderPage(pdfDocument, surface, i, i2, i3, i4, i5, false);
    }

    public void renderPageBitmap(PdfDocument pdfDocument, Bitmap bitmap, int i, int i2, int i3, int i4, int i5) {
        renderPageBitmap(pdfDocument, bitmap, i, i2, i3, i4, i5, false);
    }

    public PdfDocument newDocument(ParcelFileDescriptor parcelFileDescriptor, String str) throws IOException {
        PdfDocument pdfDocument = new PdfDocument();
        pdfDocument.b = parcelFileDescriptor;
        synchronized (d) {
            pdfDocument.f13688a = nativeOpenDocument(getNumFd(parcelFileDescriptor), str);
        }
        return pdfDocument;
    }

    public void renderPage(PdfDocument pdfDocument, Surface surface, int i, int i2, int i3, int i4, int i5, boolean z) {
        synchronized (d) {
            try {
                try {
                    try {
                        nativeRenderPage(pdfDocument.c.get(Integer.valueOf(i)).longValue(), surface, this.f13692a, i2, i3, i4, i5, z);
                    } catch (NullPointerException e2) {
                        e = e2;
                        Log.e(b, "mContext may be null");
                        e.printStackTrace();
                    } catch (Exception e3) {
                        e = e3;
                        Log.e(b, "Exception throw from native");
                        e.printStackTrace();
                    }
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (NullPointerException e4) {
                e = e4;
            } catch (Exception e5) {
                e = e5;
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    public void renderPageBitmap(PdfDocument pdfDocument, Bitmap bitmap, int i, int i2, int i3, int i4, int i5, boolean z) {
        synchronized (d) {
            try {
                try {
                    try {
                        nativeRenderPageBitmap(pdfDocument.c.get(Integer.valueOf(i)).longValue(), bitmap, this.f13692a, i2, i3, i4, i5, z);
                    } catch (NullPointerException e2) {
                        e = e2;
                        Log.e(b, "mContext may be null");
                        e.printStackTrace();
                    } catch (Exception e3) {
                        e = e3;
                        Log.e(b, "Exception throw from native");
                        e.printStackTrace();
                    }
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (NullPointerException e4) {
                e = e4;
            } catch (Exception e5) {
                e = e5;
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    public long[] openPage(PdfDocument pdfDocument, int i, int i2) {
        long[] nativeLoadPages;
        synchronized (d) {
            nativeLoadPages = nativeLoadPages(pdfDocument.f13688a, i, i2);
            for (long j : nativeLoadPages) {
                if (i > i2) {
                    break;
                }
                pdfDocument.c.put(Integer.valueOf(i), Long.valueOf(j));
                i++;
            }
        }
        return nativeLoadPages;
    }

    public PdfDocument newDocument(byte[] bArr) throws IOException {
        return newDocument(bArr, (String) null);
    }

    public PdfDocument newDocument(byte[] bArr, String str) throws IOException {
        PdfDocument pdfDocument = new PdfDocument();
        synchronized (d) {
            pdfDocument.f13688a = nativeOpenMemDocument(bArr, str);
        }
        return pdfDocument;
    }
}
