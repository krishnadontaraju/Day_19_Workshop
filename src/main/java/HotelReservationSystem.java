import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class HotelReservationSystem {
    Scanner fetch = new Scanner(System.in);

    List<Hotel> hotelList = new ArrayList<>();
    Hotel bestRatedHotel;
    Hotel cheapestBestRatedHotel;
    LocalDate checkInDate;
    LocalDate checkOutDate;

    Hotel lakeWood = new Hotel();
    Hotel bridgeWood = new Hotel();
    Hotel ridgeWood = new Hotel();


    public void findCheapestHotelWithInGivenTimeline() throws NoSuchFieldException {
        lakeWood.setHotelName("LAKE WOOD HOTEL");
        lakeWood.setWeekdayPrice(110);
        lakeWood.setWeekendPrice(90);
        lakeWood.setRating(3);

        bridgeWood.setHotelName("BRIDE WOOD RESORT");
        bridgeWood.setWeekdayPrice(150);
        bridgeWood.setWeekendPrice(50);
        bridgeWood.setRating(4);

        ridgeWood.setHotelName("RIDGE WOOD LUXURY MANEILA");
        ridgeWood.setWeekdayPrice(220);
        ridgeWood.setWeekendPrice(150);
        ridgeWood.setRating(5);

        hotelList.add(lakeWood);
        hotelList.add(bridgeWood);
        hotelList.add(ridgeWood);

        System.out.println("WELCOME TO THE HOTEL RESERVATION");
        System.out.println("WHEN DO YOU PLAN TO CHECK-IN ? \nTYPE YOUR DATE IN THE FORMAT\n\nDD MMM YYYY\n");

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        checkInDate = LocalDate.parse(fetch.next(), dateFormat);

        System.out.println("WHEN DO YOU PLAN TO CHECK-OUT ? \nTYPE YOUR DATE IN THE FORMAT\n\nDD MMM YYYY\n");
        checkOutDate = LocalDate.parse(fetch.next(), dateFormat);

        for (Hotel hotels : hotelList) {
            fareCalculation(hotels, checkInDate, checkOutDate);
        }

        bestRatedHotel = hotelList.stream().max(Comparator.comparing(Hotel::getRating)).orElseThrow(NoSuchFieldException::new);

        System.out.println("\n\nTHE BEST RATED HOTEL FOR YOUR STAY IS");
        System.out.println(bestRatedHotel);
    }

    public void fareCalculation(Hotel parameterHotel, LocalDate startDate, LocalDate endDate) {
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
