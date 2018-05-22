package com.yunwei.easydear.function.business.data;

import java.util.List;

/**
 * Created by Administrator on 2018/5/21.
 */

public class BusinessDetailEntity {
    private String BusinessNo;
    private String Address;
    private String Telephone;
    private String BusinessImagesLast;
    private String IsVip;
    private String Latitude;
    private String AreaAdd;
    private String BrandName;
    private String OpenTime;
    private String ProvinceAdd;
    private String CityAdd;
    private String StreetAdd;
    private String BusinessName;
    private String BusinessImagesNext;
    private String IsHaveAcount;
    private String Longitude;
    private String Logo;
    private String BusinessDescription;
    private String BusinessImages;
    private String MerchantServices;
    private String BusinessTime;
    private List<?> UserCardList;
    private List<?> VipList;
    private List<ActivityListBean> ActivityList;
    private List<CardListBean> CardList;
    private List<ArticleListBean> ArticleList;

    public String getBusinessNo() {
        return BusinessNo;
    }

    public void setBusinessNo(String BusinessNo) {
        this.BusinessNo = BusinessNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }

    public String getBusinessImagesLast() {
        return BusinessImagesLast;
    }

    public void setBusinessImagesLast(String BusinessImagesLast) {
        this.BusinessImagesLast = BusinessImagesLast;
    }

    public String getIsVip() {
        return IsVip;
    }

    public void setIsVip(String IsVip) {
        this.IsVip = IsVip;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    public String getAreaAdd() {
        return AreaAdd;
    }

    public void setAreaAdd(String AreaAdd) {
        this.AreaAdd = AreaAdd;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getOpenTime() {
        return OpenTime;
    }

    public void setOpenTime(String OpenTime) {
        this.OpenTime = OpenTime;
    }

    public String getProvinceAdd() {
        return ProvinceAdd;
    }

    public void setProvinceAdd(String ProvinceAdd) {
        this.ProvinceAdd = ProvinceAdd;
    }

    public String getCityAdd() {
        return CityAdd;
    }

    public void setCityAdd(String CityAdd) {
        this.CityAdd = CityAdd;
    }

    public String getStreetAdd() {
        return StreetAdd;
    }

    public void setStreetAdd(String StreetAdd) {
        this.StreetAdd = StreetAdd;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String BusinessName) {
        this.BusinessName = BusinessName;
    }

    public String getBusinessImagesNext() {
        return BusinessImagesNext;
    }

    public void setBusinessImagesNext(String BusinessImagesNext) {
        this.BusinessImagesNext = BusinessImagesNext;
    }

    public String getIsHaveAcount() {
        return IsHaveAcount;
    }

    public void setIsHaveAcount(String IsHaveAcount) {
        this.IsHaveAcount = IsHaveAcount;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String Logo) {
        this.Logo = Logo;
    }

    public String getBusinessDescription() {
        return BusinessDescription;
    }

    public void setBusinessDescription(String BusinessDescription) {
        this.BusinessDescription = BusinessDescription;
    }

    public String getBusinessImages() {
        return BusinessImages;
    }

    public void setBusinessImages(String BusinessImages) {
        this.BusinessImages = BusinessImages;
    }

    public String getMerchantServices() {
        return MerchantServices;
    }

    public void setMerchantServices(String MerchantServices) {
        this.MerchantServices = MerchantServices;
    }

    public String getBusinessTime() {
        return BusinessTime;
    }

    public void setBusinessTime(String BusinessTime) {
        this.BusinessTime = BusinessTime;
    }

    public List<?> getUserCardList() {
        return UserCardList;
    }

    public void setUserCardList(List<?> UserCardList) {
        this.UserCardList = UserCardList;
    }

    public List<?> getVipList() {
        return VipList;
    }

    public void setVipList(List<?> VipList) {
        this.VipList = VipList;
    }

    public List<ActivityListBean> getActivityList() {
        return ActivityList;
    }

    public void setActivityList(List<ActivityListBean> ActivityList) {
        this.ActivityList = ActivityList;
    }

    public List<CardListBean> getCardList() {
        return CardList;
    }

    public void setCardList(List<CardListBean> CardList) {
        this.CardList = CardList;
    }

    public List<ArticleListBean> getArticleList() {
        return ArticleList;
    }

    public void setArticleList(List<ArticleListBean> ArticleList) {
        this.ArticleList = ArticleList;
    }

    public static class ActivityListBean {
        private String ActivityName;
        private String Title;
        private int Id;

        public String getActivityName() {
            return ActivityName;
        }

        public void setActivityName(String ActivityName) {
            this.ActivityName = ActivityName;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }
    }

    public static class CardListBean {
        private String CardNo;
        private String CardEndTime;
        private String CardName;
        private String IsHaveCard;
        private String CardPrice;
        private String Difference;

        public String getCardNo() {
            return CardNo;
        }

        public void setCardNo(String CardNo) {
            this.CardNo = CardNo;
        }

        public String getCardEndTime() {
            return CardEndTime;
        }

        public void setCardEndTime(String CardEndTime) {
            this.CardEndTime = CardEndTime;
        }

        public String getCardName() {
            return CardName;
        }

        public void setCardName(String CardName) {
            this.CardName = CardName;
        }

        public String getIsHaveCard() {
            return IsHaveCard;
        }

        public void setIsHaveCard(String IsHaveCard) {
            this.IsHaveCard = IsHaveCard;
        }

        public String getCardPrice() {
            return CardPrice;
        }

        public void setCardPrice(String CardPrice) {
            this.CardPrice = CardPrice;
        }

        public String getDifference() {
            return Difference;
        }

        public void setDifference(String Difference) {
            this.Difference = Difference;
        }
    }

    public static class ArticleListBean {
        private String Status;
        private String BusinessNo;
        private String BusinessName;
        private String GoodSize;
        private String Title;
        private String PubTime;
        private String Logo;
        private String IsTop;
        private String Type;
        private String ArticleImage;
        private String Summary;
        private int ArticleId;
        private String ForwordSize;
        private String SubTitle;

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getBusinessNo() {
            return BusinessNo;
        }

        public void setBusinessNo(String BusinessNo) {
            this.BusinessNo = BusinessNo;
        }

        public String getBusinessName() {
            return BusinessName;
        }

        public void setBusinessName(String BusinessName) {
            this.BusinessName = BusinessName;
        }

        public String getGoodSize() {
            return GoodSize;
        }

        public void setGoodSize(String GoodSize) {
            this.GoodSize = GoodSize;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getPubTime() {
            return PubTime;
        }

        public void setPubTime(String PubTime) {
            this.PubTime = PubTime;
        }

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String Logo) {
            this.Logo = Logo;
        }

        public String getIsTop() {
            return IsTop;
        }

        public void setIsTop(String IsTop) {
            this.IsTop = IsTop;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getArticleImage() {
            return ArticleImage;
        }

        public void setArticleImage(String ArticleImage) {
            this.ArticleImage = ArticleImage;
        }

        public String getSummary() {
            return Summary;
        }

        public void setSummary(String Summary) {
            this.Summary = Summary;
        }

        public int getArticleId() {
            return ArticleId;
        }

        public void setArticleId(int ArticleId) {
            this.ArticleId = ArticleId;
        }

        public String getForwordSize() {
            return ForwordSize;
        }

        public void setForwordSize(String ForwordSize) {
            this.ForwordSize = ForwordSize;
        }

        public String getSubTitle() {
            return SubTitle;
        }

        public void setSubTitle(String SubTitle) {
            this.SubTitle = SubTitle;
        }
    }
}
