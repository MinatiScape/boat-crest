package androidx.paging;

import androidx.annotation.RestrictTo;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\r\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0017\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0003H&¢\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\t\u001a\u00020\u00038&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u000b\u001a\u00020\u00038&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0016\u0010\r\u001a\u00020\u00038&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\bR\u0016\u0010\u000f\u001a\u00020\u00038&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\b¨\u0006\u0010"}, d2 = {"Landroidx/paging/NullPaddedList;", ExifInterface.GPS_DIRECTION_TRUE, "", "", "localIndex", "getFromStorage", "(I)Ljava/lang/Object;", "getPlaceholdersBefore", "()I", "placeholdersBefore", "getPlaceholdersAfter", "placeholdersAfter", "getSize", "size", "getStorageCount", "storageCount", "paging-common"}, k = 1, mv = {1, 5, 1})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public interface NullPaddedList<T> {
    T getFromStorage(int i);

    int getPlaceholdersAfter();

    int getPlaceholdersBefore();

    int getSize();

    int getStorageCount();
}
