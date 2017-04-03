package no_clay.blogclient.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import no_clay.blogclient.AbstactClass.MVPBaseFragment;
import no_clay.blogclient.Presenters.TopTenPresenter;
import no_clay.blogclient.R;
import no_clay.blogclient.Views.TopTenInterface;

/**
 * Created by no_clay on 2017/4/3.
 */

public class TopTenFragment extends MVPBaseFragment<TopTenInterface, TopTenPresenter>
        implements TopTenInterface{

    View mView;
    @BindView(R.id.chanelTab)
    TabLayout mChanelTab;
    @BindView(R.id.addChannel)
    ImageView mAddChannel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_top_ten, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void initTabLayout() {

    }

    @Override
    public void notifyTabLayout() {

    }

    @Override
    public void showFragment() {

    }

    @Override
    protected TopTenPresenter createPresenter() {
        return new TopTenPresenter();
    }
}
