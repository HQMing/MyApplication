package cn.edu.gdmec.android.myapplication.liereader.Movie.View;

import cn.edu.gdmec.android.myapplication.liereader.Bean.MoviesBean;

/**
 * Created by apple on 18/5/29.
 */

public interface IMoviesView {
    void showNews(MoviesBean moviesBean);
    void hideDialog();
    void showDialog();
    void showErrorMsg(Throwable throwable);
}
