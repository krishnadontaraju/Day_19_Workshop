
public class Main {
    public static void main(String[] args) {
        System.out.println("WELCOME TO HOTEL RESERVATION SYSTEM");
        HotelReservationSystem newSystem = new HotelReservationSystem();
        try {
            newSystem.findCheapestHotelWithInGivenTimeline();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}