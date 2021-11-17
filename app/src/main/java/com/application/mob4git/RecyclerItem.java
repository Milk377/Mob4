package com.application.mob4git;

public class RecyclerItem {
    private String titleStr ;
    private String timeStr ;

    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setTime(String desc) {
        timeStr = desc ;
    }

    public String getTitle() {
        return this.titleStr ;
    }
    public String getTime() {
        return this.timeStr ;
    }
}
