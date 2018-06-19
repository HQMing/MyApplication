package cn.edu.gdmec.android.myapplication.liereader.News.Model;

import cn.edu.gdmec.android.myapplication.liereader.Bean.NewsBean;
import cn.edu.gdmec.android.myapplication.liereader.Http.Api;
import cn.edu.gdmec.android.myapplication.liereader.Http.RetrofitHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 18/5/29.
 */

public class NewsModel implements INewsModel{
    @Override
    public void loadNews(String hostType, final int startPage, String id, final INewsLoadListener iNewsLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.NEWS_HOST);
        retrofitHelper.getNews(hostType,id,startPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iNewsLoadListener.fail(e.getMessage());
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        if (startPage!=0){
                            iNewsLoadListener.loadMoreSuccess(newsBean);
                        }else{
                            iNewsLoadListener.success(newsBean);
                        }
                    }
                });
    }
}
