package cn.edu.gdmec.android.myapplication.liereader.Video.View;

import java.util.List;

import cn.edu.gdmec.android.myapplication.liereader.Bean.TodayContentBean;

/**
 * Created by huang on 2018/6/11.
 */

public interface IVideoView {
    void showVideo(List<TodayContentBean> todayContentBeans,List<String> videoList);
    void hideDialog();
    void showDialog();
    void showErrorMsg(Throwable throwable);
}
