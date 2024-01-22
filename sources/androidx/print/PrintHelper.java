package androidx.print;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public final class PrintHelper {
    @SuppressLint({"InlinedApi"})
    public static final int COLOR_MODE_COLOR = 2;
    @SuppressLint({"InlinedApi"})
    public static final int COLOR_MODE_MONOCHROME = 1;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    public static final boolean g;
    public static final boolean h;

    /* renamed from: a  reason: collision with root package name */
    public final Context f1567a;
    public BitmapFactory.Options b = null;
    public final Object c = new Object();
    public int d = 2;
    public int e = 2;
    public int f = 1;

    /* loaded from: classes.dex */
    public interface OnPrintFinishCallback {
        void onFinish();
    }

    /* loaded from: classes.dex */
    public class a extends AsyncTask<Void, Void, Throwable> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CancellationSignal f1568a;
        public final /* synthetic */ PrintAttributes b;
        public final /* synthetic */ Bitmap c;
        public final /* synthetic */ PrintAttributes d;
        public final /* synthetic */ int e;
        public final /* synthetic */ ParcelFileDescriptor f;
        public final /* synthetic */ PrintDocumentAdapter.WriteResultCallback g;

        public a(CancellationSignal cancellationSignal, PrintAttributes printAttributes, Bitmap bitmap, PrintAttributes printAttributes2, int i, ParcelFileDescriptor parcelFileDescriptor, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            this.f1568a = cancellationSignal;
            this.b = printAttributes;
            this.c = bitmap;
            this.d = printAttributes2;
            this.e = i;
            this.f = parcelFileDescriptor;
            this.g = writeResultCallback;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a */
        public Throwable doInBackground(Void... voidArr) {
            RectF rectF;
            try {
                if (this.f1568a.isCanceled()) {
                    return null;
                }
                PrintedPdfDocument printedPdfDocument = new PrintedPdfDocument(PrintHelper.this.f1567a, this.b);
                Bitmap a2 = PrintHelper.a(this.c, this.b.getColorMode());
                if (this.f1568a.isCanceled()) {
                    return null;
                }
                PdfDocument.Page startPage = printedPdfDocument.startPage(1);
                boolean z = PrintHelper.h;
                if (z) {
                    rectF = new RectF(startPage.getInfo().getContentRect());
                } else {
                    PrintedPdfDocument printedPdfDocument2 = new PrintedPdfDocument(PrintHelper.this.f1567a, this.d);
                    PdfDocument.Page startPage2 = printedPdfDocument2.startPage(1);
                    RectF rectF2 = new RectF(startPage2.getInfo().getContentRect());
                    printedPdfDocument2.finishPage(startPage2);
                    printedPdfDocument2.close();
                    rectF = rectF2;
                }
                Matrix c = PrintHelper.c(a2.getWidth(), a2.getHeight(), rectF, this.e);
                if (!z) {
                    c.postTranslate(rectF.left, rectF.top);
                    startPage.getCanvas().clipRect(rectF);
                }
                startPage.getCanvas().drawBitmap(a2, c, null);
                printedPdfDocument.finishPage(startPage);
                if (this.f1568a.isCanceled()) {
                    printedPdfDocument.close();
                    ParcelFileDescriptor parcelFileDescriptor = this.f;
                    if (parcelFileDescriptor != null) {
                        try {
                            parcelFileDescriptor.close();
                        } catch (IOException unused) {
                        }
                    }
                    if (a2 != this.c) {
                        a2.recycle();
                    }
                    return null;
                }
                printedPdfDocument.writeTo(new FileOutputStream(this.f.getFileDescriptor()));
                printedPdfDocument.close();
                ParcelFileDescriptor parcelFileDescriptor2 = this.f;
                if (parcelFileDescriptor2 != null) {
                    try {
                        parcelFileDescriptor2.close();
                    } catch (IOException unused2) {
                    }
                }
                if (a2 != this.c) {
                    a2.recycle();
                }
                return null;
            } catch (Throwable th) {
                return th;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b */
        public void onPostExecute(Throwable th) {
            if (this.f1568a.isCanceled()) {
                this.g.onWriteCancelled();
            } else if (th == null) {
                this.g.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
            } else {
                Log.e("PrintHelper", "Error writing printed content", th);
                this.g.onWriteFailed(null);
            }
        }
    }

    @RequiresApi(19)
    /* loaded from: classes.dex */
    public class b extends PrintDocumentAdapter {

        /* renamed from: a  reason: collision with root package name */
        public final String f1569a;
        public final int b;
        public final Bitmap c;
        public final OnPrintFinishCallback d;
        public PrintAttributes e;

        public b(String str, int i, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
            this.f1569a = str;
            this.b = i;
            this.c = bitmap;
            this.d = onPrintFinishCallback;
        }

        @Override // android.print.PrintDocumentAdapter
        public void onFinish() {
            OnPrintFinishCallback onPrintFinishCallback = this.d;
            if (onPrintFinishCallback != null) {
                onPrintFinishCallback.onFinish();
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            this.e = printAttributes2;
            layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.f1569a).setContentType(1).setPageCount(1).build(), !printAttributes2.equals(printAttributes));
        }

        @Override // android.print.PrintDocumentAdapter
        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            PrintHelper.this.g(this.e, this.b, this.c, parcelFileDescriptor, cancellationSignal, writeResultCallback);
        }
    }

    @RequiresApi(19)
    /* loaded from: classes.dex */
    public class c extends PrintDocumentAdapter {

        /* renamed from: a  reason: collision with root package name */
        public final String f1570a;
        public final Uri b;
        public final OnPrintFinishCallback c;
        public final int d;
        public PrintAttributes e;
        public AsyncTask<Uri, Boolean, Bitmap> f;
        public Bitmap g = null;

        /* loaded from: classes.dex */
        public class a extends AsyncTask<Uri, Boolean, Bitmap> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ CancellationSignal f1571a;
            public final /* synthetic */ PrintAttributes b;
            public final /* synthetic */ PrintAttributes c;
            public final /* synthetic */ PrintDocumentAdapter.LayoutResultCallback d;

            /* renamed from: androidx.print.PrintHelper$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public class C0172a implements CancellationSignal.OnCancelListener {
                public C0172a() {
                }

                @Override // android.os.CancellationSignal.OnCancelListener
                public void onCancel() {
                    c.this.a();
                    a.this.cancel(false);
                }
            }

            public a(CancellationSignal cancellationSignal, PrintAttributes printAttributes, PrintAttributes printAttributes2, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback) {
                this.f1571a = cancellationSignal;
                this.b = printAttributes;
                this.c = printAttributes2;
                this.d = layoutResultCallback;
            }

            @Override // android.os.AsyncTask
            /* renamed from: a */
            public Bitmap doInBackground(Uri... uriArr) {
                try {
                    c cVar = c.this;
                    return PrintHelper.this.f(cVar.b);
                } catch (FileNotFoundException unused) {
                    return null;
                }
            }

            @Override // android.os.AsyncTask
            /* renamed from: b */
            public void onCancelled(Bitmap bitmap) {
                this.d.onLayoutCancelled();
                c.this.f = null;
            }

            @Override // android.os.AsyncTask
            /* renamed from: c */
            public void onPostExecute(Bitmap bitmap) {
                PrintAttributes.MediaSize mediaSize;
                super.onPostExecute(bitmap);
                if (bitmap != null && (!PrintHelper.g || PrintHelper.this.f == 0)) {
                    synchronized (this) {
                        mediaSize = c.this.e.getMediaSize();
                    }
                    if (mediaSize != null && mediaSize.isPortrait() != PrintHelper.d(bitmap)) {
                        Matrix matrix = new Matrix();
                        matrix.postRotate(90.0f);
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    }
                }
                c.this.g = bitmap;
                if (bitmap != null) {
                    this.d.onLayoutFinished(new PrintDocumentInfo.Builder(c.this.f1570a).setContentType(1).setPageCount(1).build(), true ^ this.b.equals(this.c));
                } else {
                    this.d.onLayoutFailed(null);
                }
                c.this.f = null;
            }

            @Override // android.os.AsyncTask
            public void onPreExecute() {
                this.f1571a.setOnCancelListener(new C0172a());
            }
        }

        public c(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback, int i) {
            this.f1570a = str;
            this.b = uri;
            this.c = onPrintFinishCallback;
            this.d = i;
        }

        public void a() {
            synchronized (PrintHelper.this.c) {
                BitmapFactory.Options options = PrintHelper.this.b;
                if (options != null) {
                    if (Build.VERSION.SDK_INT < 24) {
                        options.requestCancelDecode();
                    }
                    PrintHelper.this.b = null;
                }
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onFinish() {
            super.onFinish();
            a();
            AsyncTask<Uri, Boolean, Bitmap> asyncTask = this.f;
            if (asyncTask != null) {
                asyncTask.cancel(true);
            }
            OnPrintFinishCallback onPrintFinishCallback = this.c;
            if (onPrintFinishCallback != null) {
                onPrintFinishCallback.onFinish();
            }
            Bitmap bitmap = this.g;
            if (bitmap != null) {
                bitmap.recycle();
                this.g = null;
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            synchronized (this) {
                this.e = printAttributes2;
            }
            if (cancellationSignal.isCanceled()) {
                layoutResultCallback.onLayoutCancelled();
            } else if (this.g != null) {
                layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.f1570a).setContentType(1).setPageCount(1).build(), !printAttributes2.equals(printAttributes));
            } else {
                this.f = new a(cancellationSignal, printAttributes2, printAttributes, layoutResultCallback).execute(new Uri[0]);
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            PrintHelper.this.g(this.e, this.d, this.g, parcelFileDescriptor, cancellationSignal, writeResultCallback);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        g = i < 20 || i > 23;
        h = i != 23;
    }

    public PrintHelper(@NonNull Context context) {
        this.f1567a = context;
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        if (i != 1) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        canvas.setBitmap(null);
        return createBitmap;
    }

    @RequiresApi(19)
    public static PrintAttributes.Builder b(PrintAttributes printAttributes) {
        PrintAttributes.Builder minMargins = new PrintAttributes.Builder().setMediaSize(printAttributes.getMediaSize()).setResolution(printAttributes.getResolution()).setMinMargins(printAttributes.getMinMargins());
        if (printAttributes.getColorMode() != 0) {
            minMargins.setColorMode(printAttributes.getColorMode());
        }
        if (Build.VERSION.SDK_INT >= 23 && printAttributes.getDuplexMode() != 0) {
            minMargins.setDuplexMode(printAttributes.getDuplexMode());
        }
        return minMargins;
    }

    public static Matrix c(int i, int i2, RectF rectF, int i3) {
        float min;
        Matrix matrix = new Matrix();
        float f = i;
        float width = rectF.width() / f;
        if (i3 == 2) {
            min = Math.max(width, rectF.height() / i2);
        } else {
            min = Math.min(width, rectF.height() / i2);
        }
        matrix.postScale(min, min);
        matrix.postTranslate((rectF.width() - (f * min)) / 2.0f, (rectF.height() - (i2 * min)) / 2.0f);
        return matrix;
    }

    public static boolean d(Bitmap bitmap) {
        return bitmap.getWidth() <= bitmap.getHeight();
    }

    public static boolean systemSupportsPrint() {
        return Build.VERSION.SDK_INT >= 19;
    }

    public final Bitmap e(Uri uri, BitmapFactory.Options options) throws FileNotFoundException {
        Context context;
        if (uri != null && (context = this.f1567a) != null) {
            InputStream inputStream = null;
            try {
                InputStream openInputStream = context.getContentResolver().openInputStream(uri);
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream, null, options);
                    if (openInputStream != null) {
                        try {
                            openInputStream.close();
                        } catch (IOException e) {
                            Log.w("PrintHelper", "close fail ", e);
                        }
                    }
                    return decodeStream;
                } catch (Throwable th) {
                    th = th;
                    inputStream = openInputStream;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e2) {
                            Log.w("PrintHelper", "close fail ", e2);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            throw new IllegalArgumentException("bad argument to loadBitmap");
        }
    }

    public Bitmap f(Uri uri) throws FileNotFoundException {
        BitmapFactory.Options options;
        if (uri != null && this.f1567a != null) {
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inJustDecodeBounds = true;
            e(uri, options2);
            int i = options2.outWidth;
            int i2 = options2.outHeight;
            if (i > 0 && i2 > 0) {
                int max = Math.max(i, i2);
                int i3 = 1;
                while (max > 3500) {
                    max >>>= 1;
                    i3 <<= 1;
                }
                if (i3 > 0 && Math.min(i, i2) / i3 > 0) {
                    synchronized (this.c) {
                        options = new BitmapFactory.Options();
                        this.b = options;
                        options.inMutable = true;
                        options.inSampleSize = i3;
                    }
                    try {
                        Bitmap e = e(uri, options);
                        synchronized (this.c) {
                            this.b = null;
                        }
                        return e;
                    } catch (Throwable th) {
                        synchronized (this.c) {
                            this.b = null;
                            throw th;
                        }
                    }
                }
            }
            return null;
        }
        throw new IllegalArgumentException("bad argument to getScaledBitmap");
    }

    @RequiresApi(19)
    public void g(PrintAttributes printAttributes, int i, Bitmap bitmap, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
        new a(cancellationSignal, h ? printAttributes : b(printAttributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build(), bitmap, printAttributes, i, parcelFileDescriptor, writeResultCallback).execute(new Void[0]);
    }

    public int getColorMode() {
        return this.e;
    }

    public int getOrientation() {
        if (Build.VERSION.SDK_INT < 19 || this.f != 0) {
            return this.f;
        }
        return 1;
    }

    public int getScaleMode() {
        return this.d;
    }

    public void printBitmap(@NonNull String str, @NonNull Bitmap bitmap) {
        printBitmap(str, bitmap, (OnPrintFinishCallback) null);
    }

    public void setColorMode(int i) {
        this.e = i;
    }

    public void setOrientation(int i) {
        this.f = i;
    }

    public void setScaleMode(int i) {
        this.d = i;
    }

    public void printBitmap(@NonNull String str, @NonNull Bitmap bitmap, @Nullable OnPrintFinishCallback onPrintFinishCallback) {
        PrintAttributes.MediaSize mediaSize;
        if (Build.VERSION.SDK_INT < 19 || bitmap == null) {
            return;
        }
        PrintManager printManager = (PrintManager) this.f1567a.getSystemService("print");
        if (d(bitmap)) {
            mediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
        } else {
            mediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
        }
        printManager.print(str, new b(str, this.d, bitmap, onPrintFinishCallback), new PrintAttributes.Builder().setMediaSize(mediaSize).setColorMode(this.e).build());
    }

    public void printBitmap(@NonNull String str, @NonNull Uri uri) throws FileNotFoundException {
        printBitmap(str, uri, (OnPrintFinishCallback) null);
    }

    public void printBitmap(@NonNull String str, @NonNull Uri uri, @Nullable OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        c cVar = new c(str, uri, onPrintFinishCallback, this.d);
        PrintManager printManager = (PrintManager) this.f1567a.getSystemService("print");
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setColorMode(this.e);
        int i = this.f;
        if (i == 1 || i == 0) {
            builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
        } else if (i == 2) {
            builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
        }
        printManager.print(str, cVar, builder.build());
    }
}
