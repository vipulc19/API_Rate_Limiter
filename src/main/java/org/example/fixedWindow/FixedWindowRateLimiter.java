package org.example.fixedWindow;

import org.example.fixedWindow.model.Window;

import java.util.HashMap;
import java.util.Map;

public class FixedWindowRateLimiter {

    private final int maxRequests;
    private final long windowSizeInSec;

    private final Map<String, Window> userRequestMap;

    public FixedWindowRateLimiter(int maxRequests, long windowSizeInSec) {
        this.userRequestMap = new HashMap<>();
        this.maxRequests = maxRequests;
        this.windowSizeInSec = windowSizeInSec;
    }

    public boolean allowRequest(String userId) {

        long currentTimeInSec = System.currentTimeMillis() / 1000;

        if(!userRequestMap.containsKey(userId)) {
            userRequestMap.put(userId, new Window(0, currentTimeInSec));
        }

        Window window = userRequestMap.get(userId);

        // resetting when window exceed 1 sec
        if(currentTimeInSec - window.getWindowStart() >= windowSizeInSec) {
            window.setWindowStart(currentTimeInSec);
            window.setRequestCount(0);
        }

        long existingCount = window.getRequestCount();

        if (existingCount >= maxRequests)
            return false;

        window.setRequestCount(window.getRequestCount() + 1);

        return true;
    }


    public static void main(String[] args) throws InterruptedException {
        FixedWindowRateLimiter fixedWindowRateLimiter = new FixedWindowRateLimiter(2, 1);

        System.out.println(fixedWindowRateLimiter.allowRequest("X"));
        System.out.println(fixedWindowRateLimiter.allowRequest("X"));
        System.out.println(fixedWindowRateLimiter.allowRequest("X"));
        Thread.sleep(2000);
        System.out.println(fixedWindowRateLimiter.allowRequest("X"));
        System.out.println(fixedWindowRateLimiter.allowRequest("X"));

    }
}
