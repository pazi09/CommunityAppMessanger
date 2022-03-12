package com.example.CommunityAppMessanger.utils;

import java.util.HashMap;
import java.util.Map;

public class CityHolder {

    private static final Map<Long,Long> cityCtx=new HashMap<>();

    public static synchronized Long getCityByUserId(Long userId){
        return cityCtx.get(userId);
    }

    public static synchronized void saveCityForUser(Long userId,Long cityId){
        cityCtx.put(userId,cityId);
    }

    public static synchronized void deleteCityForUser(Long userId){
        cityCtx.remove(userId);
    }
}
