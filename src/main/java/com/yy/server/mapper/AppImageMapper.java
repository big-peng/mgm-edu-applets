package com.yy.server.mapper;

import com.yy.server.model.AppImage;
import com.yy.server.util.MyMapper;

import java.util.List;

public interface AppImageMapper extends MyMapper<AppImage> {

    List<AppImage> getAppImages(AppImage appImage);

    int updateDelImagesByFromId(int fromId);
}