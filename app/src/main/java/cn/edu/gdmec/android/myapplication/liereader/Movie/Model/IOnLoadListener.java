package cn.edu.gdmec.android.myapplication.liereader.Movie.Model;

import cn.edu.gdmec.android.myapplication.liereader.Bean.MoviesBean;

/**
 * Created by apple on 18/5/29.
 */

public interface IOnLoadListener {
    void success(MoviesBean moviesBean);
    void fail(Throwable throwable);
}
