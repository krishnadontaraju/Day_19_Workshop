import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class HotelReservationSystem {
    Scanner fetch = new Scanner(System.in);

    List<Hotel> hotelList = new ArrayList<>();
    String customerType;
    Hotel cheapestBestRatedHotel;
    LocalDate checkInDate;
    LocalDate checkOutDate;

    Hotel lakeWood = new Hotel();
    Hotel bridgeWood = new Hotel();
    Hotel ridgeWood = new Hotel();


    public void findCheapestHotelWithInGivenTimeline() throws NoSuchFieldException, IllegalInputException {
        lakeWood.setHotelName("LAKE WOOD HOTEL");
        lakeWood.setWeekdayPrice(110);
        lakeWood.setWeekendPrice(90);
        lakeWood.setRating(3);
        lakeWood.setRewardWeekdayPrice(80);
        lakeWood.setRewardWeekendPrice(80);

        bridgeWood.setHotelName("BRIDE WOOD RESORT");
        bridgeWood.setWeekdayPrice(150);
        bridgeWood.setWeekendPrice(50);
        bridgeWood.setRating(4);
        bridgeWood.setRewardWeekdayPrice(110);
        bridgeWood.setRewardWeekendPrice(50);

        ridgeWood.setHotelName("RIDGE WOOD LUXURY MANEILA");
        ridgeWood.setWeekdayPrice(220);
        ridgeWood.setWeekendPrice(150);
        ridgeWood.setRating(5);
        ridgeWood.setRewardWeekdayPrice(100);
        ridgeWood.setRewardWeekendPrice(40);

        hotelList.add(lakeWood);
        hotelList.add(bridgeWood);
        hotelList.add(ridgeWood);


        System.out.println("WELCOME TO THE HOTEL RESERVATION");
        System.out.println("WHEN DO YOU PLAN TO CHECK-IN ? \nTYPE YOUR DATE IN THE FORMAT\n\nDD MMM YYYY\n");

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        checkInDate = LocalDate.parse(fetch.next(), dateFormat);

        try {
            if (!(Pattern.matches("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$", checkInDate.toString())))
                throw new IllegalInputException("YOUR CHECK IN DATE IS NOT CORRECT");
            if (checkInDate.toString() == "")
                throw new EmptyInputException("YOUR CHECK IN DATE IS EMPTY");
            if (checkInDate == null)
                throw new NullInputException("YOUR CHECK IN DATE IS NULL");
        } catch (EmptyInputException e) {
            e.printStackTrace();
        } catch (NullInputException e) {
            e.printStackTrace();
        } catch (IllegalInputException e) {
            e.printStackTrace();
        }

        System.out.println("WHEN DO YOU PLAN TO CHECK-OUT ? \nTYPE YOUR DATE IN THE FORMAT\n\nDD MMM YYYY\n");
        checkOutDate = LocalDate.parse(fetch.next(), dateFormat);

        try {
            if (!(Pattern.matches("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$", checkOutDate.toString())))
                throw new IllegalInputException("YOUR CHECKOUT DATE IS NOT CORRECT");
            if (checkOutDate.toString() == "")
                throw new EmptyInputException("YOUR CHECKOUT DATE IS EMPTY");
            if (checkOutDate == null)
                throw new NullInputException("YOUR CHECKOUT DATE IS NULL");
        } catch (EmptyInputException e) {
            e.printStackTrace();
        } catch (NullInputException e) {
            e.printStackTrace();
        } catch (IllegalInputException e) {
            e.printStackTrace();
        }

        System.out.println("WHICH CUSTOMER CATEGORY DO YOU BELONG TO ? \nREWARD OR REGULAR");
        customerType = fetch.next();

        if (!(Pattern.matches("^[A-Za-z]{6,7}$", customerType)))
            try {
                throw new IllegalInputException("YOUR CUSTOMER CATEGORY IS NOT APPROPRIATE");
            } catch (IllegalInputException e) {
                e.printStackTrace();
            }

        if (customerType.toLowerCase(Locale.ROOT).equals("regular")) {
            for (Hotel hotels : hotelList) {
                fareCalculationForRegularCustomer(hotels, checkInDate, checkOutDate);
            }
        } else {
            for (Hotel hotels : hotelList) {
                fareCalculationForRewardCustomer(hotels, checkInDate, checkOutDate);
            }
        }

        Hotel cheapestHotel = hotelList.stream().min(Comparator.comparing(Hotel::getFareForTheStay)).orElseThrow(NoSuchFieldException::new);

        Stream<Hotel> cheapestHotelStream = hotelList.stream().filter(hotel -> hotel.fareForTheStay == cheapestHotel.fareForTheStay);

        cheapestBestRatedHotel = cheapestHotelStream.max(Comparator.comparing(Hotel::getRating)).orElseThrow(NoSuchFieldException::new);

        System.out.println("\n\nTHE CHEAPEST BEST RATED HOTEL FOR YOUR STAY IS");
        System.out.println(cheapestBestRatedHotel);
    }

    public void fareCalculationForRewardCustomer(Hotel parameterHotel, LocalDate startDate, LocalDate endDate) {
        parameterHotel.fareForTheStay = 0;
        for (LocalDate currentDate = startDate; currentDate.getDayOfYear() <= endDate.getDayOfYear(); currentDate = currentDate.plusDays(1)) {
            DayOfWeek currentDay = DayOfWeek.of(currentDate.get(ChronoField.DAY_OF_WEEK));
            switch (currentDay) {

                case SATURDAY:

                case SUNDAY:
                    parameterHotel.fareForTheStay = parameterHotel.fareForTheStay + parameterHotel.getRewardWeekendPrice();
                    break;

                default:
                    parameterHotel.fareForTheStay = parameterHotel.fareForTheStay + parameterHotel.getRewardWeekdayPrice();
                    break;

            }
        }
    }

    public void fareCalculationForRegularCustomer(Hotel parameterHotel, LocalDate startDate, LocalDate endDate) {
        parameterHotel.fareForTheStay = 0;
        for (LocalDate currentDate = startDate; currentDate.getDayOfYear() <= endDate.getDayOfYear(); currentDate = currentDate.plusDays(1)) {
            DayOfWeek currentDay = DayOfWeek.of(currentDate.get(ChronoField.DAY_OF_WEEK));
            switch (currentDay) {

                case SATURDAY:

                case SUNDAY:
                    parameterHotel.fareForTheStay = parameterHotel.fareForTheStay + parameterHotel.getWeekendPrice();
                    break;

                default:
                    parameterHotel.fareForTheStay = parameterHotel.fareForTheStay + parameterHotel.getWeekdayPrice();
                    break;

            }
        }
    }
}
