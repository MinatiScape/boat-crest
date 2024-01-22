package com.jieli.jl_filebrowse.bean;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class Folder extends File {
    private final List<File> childs = new ArrayList();

    public void addChild(File file) {
        this.childs.add(file);
    }

    @Override // com.jieli.jl_filebrowse.bean.File
    public void clean() {
        super.clean();
        for (File file : this.childs) {
            file.clean();
        }
        this.childs.clear();
        setLoadFinished(false);
    }

    public int getChildCount() {
        List<File> list = this.childs;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public File getChildFile(int i) {
        for (File file : this.childs) {
            if (file.getFileStruct().getCluster() == i) {
                return file;
            }
        }
        return null;
    }

    public List<FileStruct> getChildFileStructs() {
        ArrayList arrayList = new ArrayList();
        for (File file : this.childs) {
            arrayList.add(file.getFileStruct());
        }
        return arrayList;
    }

    @Override // com.jieli.jl_filebrowse.bean.File
    public int getStartIndex() {
        return this.childs.size() + 1;
    }

    @Override // com.jieli.jl_filebrowse.bean.File
    public boolean isLoadFinished(boolean z) {
        if (!z) {
            return super.isLoadFinished(z);
        }
        for (File file : this.childs) {
            if (!file.isLoadFinished(z)) {
                return false;
            }
        }
        return true;
    }

    public void removeChild(File file) {
        this.childs.remove(file);
    }

    public void addChild(List<File> list) {
        this.childs.addAll(list);
    }
}
