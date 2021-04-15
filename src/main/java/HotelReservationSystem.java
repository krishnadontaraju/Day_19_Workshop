import java.util.Scanner;

public class HotelReservationSystem {
    Scanner fetch =  new Scanner(System.in);

    Hotel lakeWood = new Hotel();
    Hotel bridgeWood = new Hotel();
    Hotel ridgeWood = new Hotel();


    public void findCheapestWithTwoDays(){
        lakeWood.setHotelName("LAKE WOOD");
        lakeWood.setPrice(220);

        System.out.println("WELCOME TO THE HOTEL RESERVATION");
        System.out.println("WHICH DAY DO YOU WANT TO BOOK ?" +
                            "1.10 September 2020 \n2.11 September 2020");

        switch (fetch.nextInt()){
            case 1:
                System.out.println("THE CHEAPEST HOTEL IS "+lakeWood.getHotelName()+"WHICH IS ONLY $"+lakeWood.getPrice());
                break;
            case 2:
                System.out.println("THE CHEAPEST HOTEL IS "+lakeWood.getHotelName()+"WHICH IS ONLY $"+lakeWood.getPrice());
                break;
            default:
                System.out.println("NOT CORRECT INPUT");
                break;
        }
    }
}
