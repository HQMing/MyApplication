package cn.edu.gdmec.android.myapplication.liereader.News.Model;

import cn.edu.gdmec.android.myapplication.liereader.Bean.NewsBean;

/**
 * Created by apple on 18/5/29.
 */

public interface IOnLoadListener {
    void success(NewsBean newsBean);
    void fail(String error);
}
