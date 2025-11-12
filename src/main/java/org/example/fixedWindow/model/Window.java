package org.example.fixedWindow.model;

public class Window {

    private int requestCount;
    private long windowStart;

    public Window(int requestCount, long windowStart) {
        this.requestCount = requestCount;
        this.windowStart = windowStart;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public long getWindowStart() {
        return windowStart;
    }

    public void setRequestCount(int count) {
        this.requestCount = count;
    }

    public void setWindowStart(long windowStart){
        this.windowStart = windowStart;
    }

}
