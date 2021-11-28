package com.application.mob4git;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "memoTable")
public class RecyclerItem implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "memo_key")
    private int key;
    @ColumnInfo(name = "memo_text")
    private String textStr ;
    @ColumnInfo(name = "memo_title")
    private String titleStr ;

    public RecyclerItem(String textStr, String titleStr){
        this.textStr=textStr;
        this.titleStr=titleStr;
    }

    protected RecyclerItem(Parcel in) {
        key = in.readInt();
        textStr = in.readString();
        titleStr = in.readString();
    }

    public static final Creator<RecyclerItem> CREATOR = new Creator<RecyclerItem>(){
        @Override
        public RecyclerItem createFromParcel(Parcel in){ return new RecyclerItem(in);}

        @Override
        public RecyclerItem[] newArray(int size){ return new RecyclerItem[size];}
    };

    public void setKey(int key){ this.key=key;}

    public String getTextStr(){ return textStr;}
    public String getTitleStr(){ return titleStr;};
    public void setText(String textStr) {
        this.textStr = textStr ;
    }
    public void setTitle(String titleStr) {
        this.titleStr = titleStr ;
    }

    public int getKey(){ return key;}


    @Override
    public int describeContents(){ return 0;}
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(key);
        dest.writeString(textStr);
        dest.writeString(titleStr);
    }
}
