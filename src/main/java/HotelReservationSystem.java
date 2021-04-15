import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HotelReservationSystem {
    Scanner fetch = new Scanner(System.in);

    List<Hotel> hotelList = new ArrayList<>();
    List<Hotel> cheapestHotelList;
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
            fareCalculation(hotels,checkInDate,checkOutDate);
        }

        Hotel cheapestHotel = hotelList.stream().min(Comparator.comparing(Hotel::getFareForTheStay)).orElseThrow(NoSuchFieldException::new);

        Stream<Hotel> cheapestHotelStream = hotelList.stream().filter(hotel -> hotel.fareForTheStay == cheapestHotel.fareForTheStay);

        cheapestHotelList = cheapestHotelStream.collect(Collectors.toList());

        System.out.println("\n\nTHE CHEAPEST HOTELS FOR YOUR STAY ARE : \n");
        cheapestHotelList.stream().forEach(hotel -> System.out.println(hotel));
    }

    public void fareCalculation(Hotel parameterHotel, LocalDate startDate , LocalDate endDate){
        parameterHotel.fareForTheStay = 0;
        for (LocalDate currentDate = startDate; currentDate.getDayOfYear() <= endDate.getDayOfYear() ; currentDate = currentDate.plusDays(1)) {
            DayOfWeek currentDay = DayOfWeek.of(currentDate.get(ChronoField.DAY_OF_WEEK));
            switch (currentDay){

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
