import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class HotelReservationTest {
    @Test
    public void givenDateRange_whenAvailable_shouldReturnHotels(){

        HotelReservationSystem newSystem = new HotelReservationSystem();
        newSystem.checkInDate = LocalDate.parse("2020-09-11");
        newSystem.checkOutDate = LocalDate.parse("2020-09-12");
        newSystem.customerType = "reward";

        try {
            newSystem.findCheapestHotelWithInGivenTimeline();
        } catch (NoSuchFieldException | IllegalInputException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(newSystem.cheapestBestRatedHotel,newSystem.ridgeWood);
    }

    @Test
    public void givenIllegalInput_whenIllegal_shouldReturnException() throws IllegalInputException {
        HotelReservationSystem newSystem = new HotelReservationSystem();
        newSystem.checkInDate = LocalDate.parse("2020-09-11");
        newSystem.checkOutDate = LocalDate.parse("2020-09-12");

        newSystem.customerType = "rewardssss";

        try {
            newSystem.findCheapestHotelWithInGivenTimeline();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
