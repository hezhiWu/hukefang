package com.yunwei.easydear.function.mainFuncations.membershipFuncation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.exception.WriterException;
import com.yunwei.easydear.BuildConfig;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseFragment;
import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.function.account.data.UserInfoEntity;
import com.yunwei.easydear.function.mainFuncations.mycardlistFunction.MyCardActivity;
import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.BusinessContract;
import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.MemberBusinessPresenter;
import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.MyMemberActivity;
import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.MyMemberAdapter;
import com.yunwei.easydear.function.mainFuncations.mymemberlistFunction.data.BusinessEntity;
import com.yunwei.easydear.function.mainFuncations.myorderlistFunction.MyOrderActivity;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.utils.IUtil;
import com.yunwei.easydear.utils.QRCodeWriter;
import com.yunwei.easydear.view.PullToRefreshRecyclerView;
import com.yunwei.easydear.view.RoundedBitmapImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Describe:会员码
 * Author: hezhiWu
 * Date: 2017-01-08
 * Time: 13:46
 * Version:1.0
 */

public class MembershipCodeFragment extends BaseFragment implements PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener, BusinessContract.BusinessView {

    private static MembershipCodeFragment fagment;
    @BindView(R.id.MembershiFrgment_bg_iv)
    ImageView MembershiFrgmentBgIv;
    @BindView(R.id.MembershipFragment_headView_iv)
    ImageView headView;
    @BindView(R.id.MembershipFragment_Name_textView)
    TextView userName;
    @BindView(R.id.MembershipFragment_qr_iv)
    ImageView qrImageView;
    @BindView((R.id.MembershipFragment_card_count_textView))
    TextView cardCountTextView;
    @BindView((R.id.MembershipFragment_business_count_textView))
    TextView businessCountTextView;
    @BindView(R.id.MembershipFragment_bill_count_textView)
    TextView billCountTextView;
    @BindView(R.id.MembershiFrgment_RecyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    private BillPresenter billPresenter;

    private MyMemberAdapter adapter;

    private MemberBusinessPresenter memberBusinessPresenter;

    private int defaultPageSize = 1;

    public static MembershipCodeFragment newInstance() {
        if (fagment == null) {
            fagment = new MembershipCodeFragment();
        }
        return fagment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_frgment_membership, null);
        ButterKnife.bind(this, rootView);
//        initUI();
//        initCountUI();


       memberBusinessPresenter = new MemberBusinessPresenter(this);

        initRecyclerView();

        return rootView;
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        adapter = new MyMemberAdapter(getActivity());
        mRecyclerView.setPullToRefreshListener(this);
        mRecyclerView.setRecyclerViewAdapter(adapter);

        mRecyclerView.startUpRefresh();

    }

    private void initUI() {
        UserInfoEntity entity = DataApplication.getInstance().getUserInfoEntity();
        if (entity != null) {
            userName.setText(entity.getNickName());
            QRCodeWriter writer=new QRCodeWriter();
            try {
                BitMatrix bitMatrix=writer.encode(entity.getUserNo(),120,120);
                qrImageView.setImageBitmap(IUtil.bitMatrix2Bitmap(bitMatrix));
            } catch (WriterException e) {
                e.printStackTrace();
            }
//            qrImageView.setImageBitmap(IUtil.createQRImage(entity.getUserNo(), 120, 120));
            Glide.with(getActivity()).load(BuildConfig.DOMAI+entity.getImagery()).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(headView));
        } else {
            Glide.with(getContext()).load("http://www.wendu.com/upload/12-02-06/dizhiqr.jpeg").into(qrImageView);
            Glide.with(getContext()).load("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg").asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(headView));
        }
        Glide.with(getContext()).load("http://www.popoffices.com/design/pin/opn/006.jpg").into(MembershiFrgmentBgIv);
    }

    private void initCountUI() {
        billPresenter = new BillPresenter(new BillContract.CardView() {
            @Override
            public void onCardSuccess(int size) {
                cardCountTextView.setText(size + "");
            }

            @Override
            public void onCardFalire(String error) {

            }

            @Override
            public String getUserNo() {
                return null;
            }
        });
        billPresenter.reqCardAction();

        billPresenter = new BillPresenter(new BillContract.BusinessView() {
            @Override
            public void onBusinessSuccess(int size) {
                businessCountTextView.setText(size + "");
            }

            @Override
            public void onBusinessFalire(String error) {

            }

            @Override
            public String getUserNo() {
                return null;
            }
        });
        billPresenter.reqBusinessAction();

        billPresenter = new BillPresenter(new BillContract.BillssView() {
            @Override
            public void onBillSuccess(int size) {
                billCountTextView.setText(size + "");
            }

            @Override
            public void onBillFalire(String error) {

            }

            @Override
            public String getUserNo() {
                return null;
            }
        });
        billPresenter.reqBillAction();
    }

    @OnClick({R.id.MembershipFragment_order_layout, R.id.MembershipFragment_block_layout, R.id.MembershipFragment_ll_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.MembershipFragment_ll_layout:
                ISkipActivityUtil.startIntent(getActivity(), MyCardActivity.class);
                break;
            case R.id.MembershipFragment_block_layout:
                ISkipActivityUtil.startIntent(getActivity(), MyMemberActivity.class);
                break;
            case R.id.MembershipFragment_order_layout:
                ISkipActivityUtil.startIntent(getActivity(), MyOrderActivity.class);
                break;
        }
    }

    @Override
    public void onDownRefresh() {
        defaultPageSize = 1;
        memberBusinessPresenter.reqBusinessListAction();
    }

    @Override
    public void onPullRefresh() {
        defaultPageSize++;
        memberBusinessPresenter.reqBusinessListAction();
    }

    @Override
    public void onBusinessStart() {

    }

    @Override
    public void onBusinessEnd() {
        mRecyclerView.closeDownRefresh();
        mRecyclerView.onLoadMoreFinish();
    }

    @Override
    public void onBusinessSuccess(List<BusinessEntity> list) {
        adapter.addItems(list);
    }

    @Override
    public void onBusinessFaliure(String error) {
//        showToast(error);
        mRecyclerView.setEmptyTextView();
    }

    @Override
    public String getUserNo() {
        return DataApplication.getInstance().getUserInfoEntity().getUserNo();
    }

    @Override
    public int getPageSize() {
        return defaultPageSize;
    }

    @Override
    public int getPageCount() {
        return 20;
    }
}
