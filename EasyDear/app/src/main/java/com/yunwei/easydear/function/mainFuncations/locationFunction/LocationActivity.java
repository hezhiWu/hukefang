package com.yunwei.easydear.function.mainFuncations.locationFunction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.function.mainFuncations.MainContract;
import com.yunwei.easydear.function.mainFuncations.MainPresenter;
import com.yunwei.easydear.function.mainFuncations.data.soure.MainRemoteRepo;
import com.yunwei.easydear.utils.ILog;
import com.yunwei.easydear.utils.ISpfUtil;
import com.yunwei.easydear.view.MeasuredGridView;
import com.yunwei.easydear.view.MeasuredListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/3/4.
 */
public class LocationActivity extends BaseActivity implements LocationContact.LocationView, MainContract.MainView, AdapterView.OnItemClickListener {

    final String TAG = getClass().getSimpleName();

    @BindView(R.id.location_current_city)
    TextView mCurCityTextView;
    @BindView(R.id.location_district_gridView)
    MeasuredGridView mCurCityDistrictGridView;

    @BindView(R.id.location_locate_city_text)
    TextView mLocateCityText;
    @BindView(R.id.location_locate_city)
    TextView mLocateCityTextView;

    @BindView(R.id.location_hot_city_text)
    TextView mHotCityText;
    @BindView(R.id.location_hot_city_gridView)
    MeasuredGridView mHotCityGridView;

    @BindView(R.id.location_all_city_listView)
    MeasuredListView mGroupedAllCityListView;

    private LocationPresenter mLocationPresenter;
    //    private AMapLocation mCurrentLocation;
    private AMapLocation mLocatedLocation;
    private LocationAdapter mCurCityDistrictAdapter;
    private LocationAdapter mHotCityAdapter;
    private ArrayList<LocationEntity> mCurCityDistrictList;
    private ArrayList<LocationEntity> mHotCityList;
    private ArrayList<LocationEntity> mAllCityList;
    private ArrayList<LocationEntity> mGroupedAllCityList;

    private String mCurrentCity;
    private String mCurrentCityCode;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        setToolbarTitle("选择城市");
        ButterKnife.bind(this);
        initPresenter();
        initCurrentCityValue();
        initCurCityDistrictGridView();
        initLocatedCity();
        initHotCityGridView();
        initAllCityListView();
        requestCurrentCityDistrict();
        requestAllCity();
    }

    private void initPresenter() {
        mLocationPresenter = new LocationPresenter(LocationRemoteRepo.getInstance(), this);
    }

    private void initCurrentCityValue() {
        Intent intent = getIntent();
        if (intent.hasExtra("city")) {
            mCurrentCity = intent.getStringExtra("city");
            mCurCityTextView.setText("当前: " + mCurrentCity);
        }
        if (intent.hasExtra("city_code")) {
            mCurrentCityCode = intent.getStringExtra("city_code");
        }
    }

    private void initCurCityDistrictGridView() {
        mCurCityDistrictAdapter = new LocationAdapter(LocationActivity.this);
        mCurCityDistrictGridView.setAdapter(mCurCityDistrictAdapter);
        mCurCityDistrictGridView.setOnItemClickListener(this);
    }

    private void initLocatedCity() {
        new MainPresenter(MainRemoteRepo.newInstance(), this).startLocation();
    }

    private void initHotCityGridView() {
        mHotCityList = new ArrayList<LocationEntity>();
        mHotCityAdapter = new LocationAdapter(LocationActivity.this);
        mHotCityGridView.setAdapter(mHotCityAdapter);
        mHotCityGridView.setOnItemClickListener(this);
    }

    private void initAllCityListView() {
        mGroupedAllCityListView.setOnItemClickListener(this);
    }

    private void requestAllCity() {
        mLocationPresenter.requestAllCity();
    }

    @OnClick({R.id.location_switch_district, R.id.location_locate_city})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.location_switch_district:
                if (mCurCityDistrictGridView.getVisibility() == View.GONE) {
                    mCurCityDistrictGridView.setVisibility(View.VISIBLE);
                } else {
                    mCurCityDistrictGridView.setVisibility(View.GONE);
                }
                break;
            case R.id.location_locate_city:
                if (mLocatedLocation != null) {
                    ILog.v(TAG, "切换到" + mLocatedLocation.getCity());
                    Intent intent = new Intent();
                    intent.putExtra("city", mLocatedLocation.getCity());
                    intent.putExtra("city_code", mLocatedLocation.getAdCode().substring(0, 4));
                    ISpfUtil.setValue(Constant.AMAP_LOCATION_CITY,mLocatedLocation.getCityCode());
                    ISpfUtil.setValue(Constant.AMAP_LOCATION_ADCODE,mLocatedLocation.getCityCode());
                    setResult(RESULT_OK, intent);
                    finish();
                }
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public String getCityCode() {
        return mCurrentCityCode;
    }

    private void requestCurrentCityDistrict() {
        mLocationPresenter.requestCurrentCityDistrict();
    }

    @Override
    public void onGetDistrictSuccess(List<LocationEntity> entities) {
        if (entities == null) {
            return;
        }
        mCurCityDistrictList = (ArrayList<LocationEntity>) entities;
        mCurCityDistrictAdapter.setLocationList(mCurCityDistrictList);
        mCurCityDistrictAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetDistrictFailure(String msg) {

    }

    @Override
    public void onGetAllCitySuccess(List<LocationEntity> entities) {
        if (entities == null) {
            return;
        }
        mAllCityList = (ArrayList<LocationEntity>) entities;
        setHotCity();
        setAllCityList();
    }

    @Override
    public void onGetAllCityFailure(String msg) {

    }

    @Override
    public void locationSuccess(AMapLocation location) {
        mLocatedLocation = location;
        if (mLocatedLocation != null) {
            mLocateCityText.setVisibility(View.VISIBLE);
            mLocateCityTextView.setVisibility(View.VISIBLE);
            mLocateCityTextView.setText("切换到: " + mLocatedLocation.getCity());
        }
    }

    private void setHotCity() {
        if (mHotCityList == null) {
            mHotCityList = new ArrayList<LocationEntity>();
        } else {
            mHotCityList.clear();
        }
        for (LocationEntity entity : mAllCityList) {
            if ("是".equals(entity.getIshot())) {
                mHotCityList.add(entity);
            }
        }
        if (mHotCityList.size() > 0) {
            mHotCityText.setVisibility(View.VISIBLE);
            mHotCityAdapter.setLocationList(mHotCityList);
            mHotCityAdapter.notifyDataSetChanged();
        }
    }

    private void setAllCityList() {
        if (mGroupedAllCityList == null) {
            mGroupedAllCityList = new ArrayList<LocationEntity>();
        } else {
            mGroupedAllCityList.clear();
        }

        /**
         *  比对 AllCityList 中每个 Entity 的 Ping, 根据 Ping 的值来分组
         *  每一个 Ping 对应的 List 暂时放到 singleGroupList 中
         *  最后再加入到 mGroupedAllCityList
         * */
        ArrayList<LocationEntity> singleGroupList = new ArrayList<LocationEntity>();
        for (int i = 'A'; i <= 'Z'; i++) {
            char c = (char) i;
            String ping = String.valueOf(c);

            singleGroupList.clear();
            for (LocationEntity entity : mAllCityList) {
                if (ping.equals(entity.getPing())) {
                    singleGroupList.add(entity);
                }
            }

            if (singleGroupList.size() > 0) {
                LocationEntity nameEntity = new LocationEntity();
                nameEntity.setName(ping);
                nameEntity.setCode("");
                nameEntity.setPing("");
                nameEntity.setIshot("");
                mGroupedAllCityList.add(nameEntity);
                mGroupedAllCityList.addAll(singleGroupList);
            }
        }

        int size = mGroupedAllCityList.size();
        String[] cityArr = new String[size];
        for (int i = 0; i < size; i++) {
            cityArr[i] = mGroupedAllCityList.get(i).getName();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(LocationActivity.this, R.layout.item_search_list_layout, cityArr);
        mGroupedAllCityListView.setAdapter(arrayAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.v(TAG, "OnItemClick i = " + i);

        LocationEntity clickedLocation = new LocationEntity();
        boolean selectDistrict = false;
        switch (adapterView.getId()) {
            case R.id.location_district_gridView:
                selectDistrict = true;
                clickedLocation = mCurCityDistrictList.get(i);
                break;
            case R.id.location_hot_city_gridView:
                clickedLocation = mHotCityList.get(i);
                break;
            case R.id.location_all_city_listView:
                clickedLocation = mGroupedAllCityList.get(i);
                break;
            default:
                break;
        }
        Log.v(TAG, "Clicked Location Code = " + clickedLocation.getCode());
        Log.v(TAG, "Clicked Location Name = " + clickedLocation.getName());
        if (clickedLocation.getCode() != null && !clickedLocation.getCode().isEmpty()) {
            Intent intent = new Intent();
            if (selectDistrict) {
                intent.putExtra("city", mCurrentCity);
                intent.putExtra("city_code", mCurrentCityCode);
                intent.putExtra("district", clickedLocation.getName());
                intent.putExtra("district_code", clickedLocation.getCode());
            } else {
                intent.putExtra("city", clickedLocation.getName());
                intent.putExtra("city_code", clickedLocation.getCode());
            }
            setResult(RESULT_OK, intent);
        }
        finish();
    }
}
