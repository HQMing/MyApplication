package cn.edu.gdmec.android.myapplication.liereader.Video.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.myapplication.liereader.Bean.TodayBean;
import cn.edu.gdmec.android.myapplication.liereader.Bean.TodayContentBean;
import cn.edu.gdmec.android.myapplication.liereader.Bean.VideoUrlBean;
import cn.edu.gdmec.android.myapplication.liereader.Http.Api;
import cn.edu.gdmec.android.myapplication.liereader.Http.RetrofitHelper;
import cn.edu.gdmec.android.myapplication.liereader.Video.Presenter.VideoPresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by huang on 2018/6/11.
 */

public class VideoModel implements IVideoModel{
    private String TAG = "VideoModel";
    @Override
    public void loadVideo(String category, final IVideoLoadListener iVideoLoadListener) {
        final List<VideoUrlBean> videoList = new ArrayList<>();
        final List<TodayContentBean> contentBeans = new ArrayList<>();
        final RetrofitHelper retrofitHelper = new RetrofitHelper(Api.TODAY_HOST);

        retrofitHelper.getToday(category)
                .flatMap(new Func1<TodayBean, Observable<VideoUrlBean>>() {
                    @Override
                    public Observable<VideoUrlBean> call(TodayBean todayBean) {
                        return Observable.from(todayBean.getData())
                                .flatMap(new Func1<TodayBean.DataBean, Observable<VideoUrlBean>>() {
                                    @Override
                                    public Observable<VideoUrlBean> call(TodayBean.DataBean dataBean) {
                                        String content = dataBean.getContent();
                                        TodayContentBean contentBean = VideoPresenter.getTodayContentBean(content);
                                        contentBeans.add(contentBean);
                                        String api = VideoPresenter.getVideoContentApi(contentBean.getVideo_id());
                                        Log.i(TAG, "call: "+api);
                                        return retrofitHelper.getVideoUrl(api);
                                    }
                                });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<VideoUrlBean>() {
                    @Override
                    public void onCompleted() {
                        iVideoLoadListener.videoUrlSuccess(videoList, contentBeans);
                        //Log.i(TAG, "onCompleted: " + videoList.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        iVideoLoadListener.fail(e);
                    }

                    @Override
                    public void onNext(VideoUrlBean videoUrlBean) {
                        videoList.add(videoUrlBean);
                    }
                });

    }
}
