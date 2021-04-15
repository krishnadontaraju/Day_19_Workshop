public class Hotel {
    public String hotelName;
    public int rating;
    public int weekdayPrice;
    public int weekendPrice;
    public int rewardWeekdayPrice;
    public int rewardWeekendPrice;
    public int fareForTheStay;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getWeekdayPrice() {
        return weekdayPrice;
    }

    public void setWeekdayPrice(int weekdayPrice) {
        this.weekdayPrice = weekdayPrice;
    }

    public int getWeekendPrice() {
        return weekendPrice;
    }

    public void setWeekendPrice(int weekendPrice) {
        this.weekendPrice = weekendPrice;
    }

    public int getRewardWeekdayPrice() {
        return rewardWeekdayPrice;
    }

    public void setRewardWeekdayPrice(int rewardWeekdayPrice) {
        this.rewardWeekdayPrice = rewardWeekdayPrice;
    }

    public int getRewardWeekendPrice() {
        return rewardWeekendPrice;
    }

    public void setRewardWeekendPrice(int rewardWeekendPrice) {
        this.rewardWeekendPrice = rewardWeekendPrice;
    }

    public int getFareForTheStay() {
        return fareForTheStay;
    }

    @Override
    public String toString() {
        return  "Hotel Name ='" + hotelName + '\'' +
                ", fareForTheStay =" + fareForTheStay +
                ", Rating ="+rating;
    }
}
