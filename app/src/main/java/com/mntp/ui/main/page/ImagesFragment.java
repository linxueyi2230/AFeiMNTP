package com.mntp.ui.main.page;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.administrator.afeimntp.R;
import com.mntp.entity.ImageInfo;
import com.mntp.ui.base.BaseFragment;
import com.mntp.utils.RecyclerViewUtils;
import com.mntp.widget.custom.rv.SpacesItemDecoration;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by afei on 2017/4/27 0027.
 */
public class ImagesFragment extends BaseFragment<ImagesPresenter> implements ImagesContract.View {

    @BindView(R.id.id_images_rv)
    RecyclerView imagesRv;//图片显示控件

    ImagesAdapter imagesAdapter;//图片数据源

    int ImgType; //默认全部显示

    public ImagesFragment() {
    }

    public ImagesFragment(int ImgType) {
        this.ImgType = ImgType;
    }

    /**
     * 子类必须实现的初始化
     */
    @Override
    public int getLayoutId() {
        return R.layout.fragment_images;
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        imagesRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        imagesRv.addOnScrollListener(scrollListener);
        int SDh = AutoUtils.getPercentHeightSizeBigger(16);
        SpacesItemDecoration decoration = new SpacesItemDecoration(SDh);
        imagesRv.addItemDecoration(decoration);
    }

    /**
     * loading界面
     */
    @Override
    public void loading() {

    }

    /**
     * 内容显示界面
     */
    @Override
    public void showContent() {

    }

    /**
     * 提示
     *
     * @param msg
     */
    @Override
    public void toastShow(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 设置图片列表
     *
     * @param list
     */
    @Override
    public void setImsData(List<ImageInfo> list) {
        imagesAdapter = new ImagesAdapter(getActivity(), list);
        imagesRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        imagesRv.setAdapter(imagesAdapter);
        imagesAdapter.setOnItemClickListener(ItemOnClick);
    }

    /**
     * 图片点击事件
     */
    ImagesAdapter.ItemOnClickListener ItemOnClick = (view, position) -> {

    };

    /**
     * 上拉
     */
    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (RecyclerViewUtils.isSlideToBottom(recyclerView)) {
                mPresenter.refreshImgs();
            }
        }
    };
}
