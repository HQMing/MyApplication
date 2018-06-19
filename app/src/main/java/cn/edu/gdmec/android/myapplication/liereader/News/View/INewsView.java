package cn.edu.gdmec.android.myapplication.liereader.News.View;

import cn.edu.gdmec.android.myapplication.liereader.Bean.NewsBean;

/**
 * Created by apple on 18/5/29.
 */

public interface INewsView {
    void showNews(NewsBean newsBean);
    void showMoreNews(NewsBean newsBean);
    void hideDialog();
    void showDialog();
    void showErrorMsg(String error);

    void showErrorMsg(Throwable throwable);
}
