import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HotelReservationSystem {
    Scanner fetch = new Scanner(System.in);

    Hotel lakeWood = new Hotel();
    Hotel bridgeWood = new Hotel();
    Hotel ridgeWood = new Hotel();


    public void findCheapestWithTwoDays() {
        lakeWood.setHotelName("LAKE WOOD");
        lakeWood.setWeekdayPrice(110);

        System.out.println("WELCOME TO THE HOTEL RESERVATION");
        System.out.println("WHEN DO YOU PLAN TO CHECK-IN ? \nTYPE YOUR DATE IN THE FORMAT\n\nDD MMM YYYY\n");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

        LocalDate checkInDate = LocalDate.parse(fetch.nextLine(), dateFormat);

        System.out.println("WHEN DO YOU PLAN TO CHECK-OUT ? \nTYPE YOUR DATE IN THE FORMAT\n\nDD MMM YYYY\n");

        LocalDate checkOutDate = LocalDate.parse(fetch.nextLine(), dateFormat);

        int totalFare = (checkOutDate.getDayOfYear() - checkInDate.getDayOfYear() + 1) * lakeWood.getWeekdayPrice();

        System.out.println("THE AVAILABLE HOTEL IS " + lakeWood.getHotelName() + " FOR YOUR STAY , WHOSE TOTAL FARE IS $" + totalFare);
    }


}
