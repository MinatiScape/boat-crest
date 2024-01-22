package com.szabh.androiddfu.goodix;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes12.dex */
public class FileSelectionHelper implements View.OnClickListener {
    public File defaultPath;
    @Nullable
    public TextView h;
    @Nullable
    public View i;
    @NonNull
    public final Activity j;
    public int k;
    public String mimeType;
    public String selectedFileName;
    public InputStream selectedFileStream;
    public Uri selectedFileUri;
    public String title;

    public FileSelectionHelper(@NonNull Activity activity) {
        this.j = activity;
    }

    public boolean closeInputStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Nullable
    public String getSelectedFileName() {
        return this.selectedFileName;
    }

    @Nullable
    public InputStream getSelectedFileStream() {
        return this.selectedFileStream;
    }

    @Nullable
    public Uri getSelectedFileUri() {
        return this.selectedFileUri;
    }

    @SuppressLint({"SetTextI18n"})
    public boolean onActivityResult(int i, int i2, @Nullable Intent intent) {
        TextView textView;
        InputStream openInputStream;
        String str;
        int i3 = this.k;
        if (i3 == 0 || i != i3) {
            return false;
        }
        this.k = 0;
        if (intent != null) {
            Uri data = intent.getData();
            if (data != null) {
                try {
                    ContentResolver contentResolver = this.j.getContentResolver();
                    openInputStream = contentResolver.openInputStream(data);
                    Cursor query = contentResolver.query(data, new String[]{"_display_name"}, null, null, null);
                    if (query != null) {
                        str = query.moveToNext() ? query.getString(0) : null;
                        query.close();
                    } else {
                        str = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    TextView textView2 = this.h;
                    if (textView2 != null) {
                        textView2.setText(e.getMessage());
                        return false;
                    }
                    return false;
                }
            } else {
                openInputStream = null;
                str = null;
            }
            this.selectedFileName = str;
            TextView textView3 = this.h;
            if (textView3 != null) {
                if (str != null) {
                    textView3.setText(str);
                } else {
                    textView3.setText("Failed to read file name.");
                }
            }
            if (openInputStream != null) {
                this.selectedFileUri = data;
                this.selectedFileStream = openInputStream;
                return true;
            }
            this.selectedFileUri = null;
            TextView textView4 = this.h;
            if (textView4 != null) {
                textView4.setText("Failed to read file.");
                return false;
            }
            return false;
        } else if (this.selectedFileUri != null || (textView = this.h) == null) {
            return false;
        } else {
            textView.setText("No File");
            return false;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.i) {
            show();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0018  */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.io.InputStream openInputStream() {
        /*
            r2 = this;
            android.net.Uri r0 = r2.selectedFileUri
            if (r0 == 0) goto L15
            android.app.Activity r0 = r2.j
            android.content.ContentResolver r0 = r0.getContentResolver()
            android.net.Uri r1 = r2.selectedFileUri     // Catch: java.lang.Exception -> L11
            java.io.InputStream r0 = r0.openInputStream(r1)     // Catch: java.lang.Exception -> L11
            goto L16
        L11:
            r0 = move-exception
            r0.printStackTrace()
        L15:
            r0 = 0
        L16:
            if (r0 == 0) goto L1a
            r2.selectedFileStream = r0
        L1a:
            java.io.InputStream r0 = r2.selectedFileStream
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szabh.androiddfu.goodix.FileSelectionHelper.openInputStream():java.io.InputStream");
    }

    public void show() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        String str = this.mimeType;
        if (str != null) {
            intent.setType(str);
        } else {
            intent.setType("*/*");
        }
        String str2 = this.title;
        if (str2 != null) {
            intent.putExtra("android.intent.extra.TITLE", str2);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            File file = this.defaultPath;
            if (file != null) {
                intent.putExtra("android.provider.extra.INITIAL_URI", Uri.fromFile(file));
            } else {
                intent.putExtra("android.provider.extra.INITIAL_URI", Uri.fromFile(this.j.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)));
            }
        }
        int currentTimeMillis = ((int) (System.currentTimeMillis() & WebSocketProtocol.PAYLOAD_SHORT_MAX)) | 5393;
        this.k = currentTimeMillis;
        this.j.startActivityForResult(intent, currentTimeMillis, null);
    }

    public FileSelectionHelper(@NonNull Activity activity, @Nullable TextView textView, @Nullable View view) {
        this(activity);
        this.h = textView;
        this.i = view;
        if (view != null) {
            view.setOnClickListener(this);
        }
    }
}
