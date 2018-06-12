package cn.edu.gdmec.android.myapplication.liereader.Video.Model;

import java.util.List;

import cn.edu.gdmec.android.myapplication.liereader.Bean.TodayContentBean;
import cn.edu.gdmec.android.myapplication.liereader.Bean.VideoUrlBean;

/**
 * Created by huang on 2018/6/11.
 */

public interface IVideoLoadListener {

    void videoUrlSuccess(List<VideoUrlBean> videoUrlBeans, List<TodayContentBean> contentBeans);
    void fail(Throwable throwable);
}
