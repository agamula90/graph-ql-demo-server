package com.underlake.honey.service;

import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private static final String PUBLIC_PATH_PREFIX = "https://elasticbeanstalk-eu-central-1-306070261283.s3.eu-central-1.amazonaws.com/public/";

    public String getAssetPublicPath(String assetName) {
        return PUBLIC_PATH_PREFIX + assetName;
    }

    public String getAssetNameByPublicPath(String publicPath) {
        if (!publicPath.startsWith(PUBLIC_PATH_PREFIX)) {
            throw new IllegalArgumentException("Wrong path");
        }
        return publicPath.substring(PUBLIC_PATH_PREFIX.length());
    }
}
