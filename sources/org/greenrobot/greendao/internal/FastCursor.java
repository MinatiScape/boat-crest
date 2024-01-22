package org.greenrobot.greendao.internal;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
/* loaded from: classes13.dex */
public final class FastCursor implements Cursor {
    public final CursorWindow h;
    public int i;
    public final int j;

    public FastCursor(CursorWindow cursorWindow) {
        this.h = cursorWindow;
        this.j = cursorWindow.getNumRows();
    }

    @Override // android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public void copyStringToBuffer(int i, CharArrayBuffer charArrayBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public void deactivate() {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public byte[] getBlob(int i) {
        return this.h.getBlob(this.i, i);
    }

    @Override // android.database.Cursor
    public int getColumnCount() {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public int getColumnIndex(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public int getColumnIndexOrThrow(String str) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public String getColumnName(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public String[] getColumnNames() {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public int getCount() {
        return this.h.getNumRows();
    }

    @Override // android.database.Cursor
    public double getDouble(int i) {
        return this.h.getDouble(this.i, i);
    }

    @Override // android.database.Cursor
    public Bundle getExtras() {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public float getFloat(int i) {
        return this.h.getFloat(this.i, i);
    }

    @Override // android.database.Cursor
    public int getInt(int i) {
        return this.h.getInt(this.i, i);
    }

    @Override // android.database.Cursor
    public long getLong(int i) {
        return this.h.getLong(this.i, i);
    }

    @Override // android.database.Cursor
    public Uri getNotificationUri() {
        return null;
    }

    @Override // android.database.Cursor
    public int getPosition() {
        return this.i;
    }

    @Override // android.database.Cursor
    public short getShort(int i) {
        return this.h.getShort(this.i, i);
    }

    @Override // android.database.Cursor
    public String getString(int i) {
        return this.h.getString(this.i, i);
    }

    @Override // android.database.Cursor
    public int getType(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public boolean getWantsAllOnMoveCalls() {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public boolean isAfterLast() {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public boolean isBeforeFirst() {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public boolean isClosed() {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public boolean isFirst() {
        return this.i == 0;
    }

    @Override // android.database.Cursor
    public boolean isLast() {
        return this.i == this.j - 1;
    }

    @Override // android.database.Cursor
    public boolean isNull(int i) {
        return this.h.isNull(this.i, i);
    }

    @Override // android.database.Cursor
    public boolean move(int i) {
        return moveToPosition(this.i + i);
    }

    @Override // android.database.Cursor
    public boolean moveToFirst() {
        this.i = 0;
        return this.j > 0;
    }

    @Override // android.database.Cursor
    public boolean moveToLast() {
        int i = this.j;
        if (i > 0) {
            this.i = i - 1;
            return true;
        }
        return false;
    }

    @Override // android.database.Cursor
    public boolean moveToNext() {
        int i = this.i;
        if (i < this.j - 1) {
            this.i = i + 1;
            return true;
        }
        return false;
    }

    @Override // android.database.Cursor
    public boolean moveToPosition(int i) {
        if (i < 0 || i >= this.j) {
            return false;
        }
        this.i = i;
        return true;
    }

    @Override // android.database.Cursor
    public boolean moveToPrevious() {
        int i = this.i;
        if (i > 0) {
            this.i = i - 1;
            return true;
        }
        return false;
    }

    @Override // android.database.Cursor
    public void registerContentObserver(ContentObserver contentObserver) {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public boolean requery() {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public Bundle respond(Bundle bundle) {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public void setNotificationUri(ContentResolver contentResolver, Uri uri) {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public void unregisterContentObserver(ContentObserver contentObserver) {
        throw new UnsupportedOperationException();
    }

    @Override // android.database.Cursor
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        throw new UnsupportedOperationException();
    }
}
