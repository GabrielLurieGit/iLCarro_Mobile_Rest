package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString

public class CarDTO {
    private String serialNumber; //": "string",
    private String manufacture; //": "string",
    private String model; //": "string",
    private String year; //": "string",
    private String fuel; //": "string",
    //Fuel fuel;
    private int seats; //": 0,
    private String carClass; //": "string",
    private double pricePerDay;//": 0,
    private String about; //: "string",
    private String city; //": "string",
    private double lat; //": 0,
    private double lng; //": 0,
    private String image; //": "string",
    private String owner; //": "string",
    private List<BookedDto> bookedPeriods;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarDTO carDTO)) return false;
        return getSeats() == carDTO.getSeats() && Double.compare(getPricePerDay(), carDTO.getPricePerDay()) == 0 && Objects.equals(getSerialNumber(), carDTO.getSerialNumber()) && Objects.equals(getManufacture(), carDTO.getManufacture()) && Objects.equals(getModel(), carDTO.getModel()) && Objects.equals(getYear(), carDTO.getYear()) && Objects.equals(getFuel(), carDTO.getFuel()) && Objects.equals(getCarClass(), carDTO.getCarClass()) && Objects.equals(getAbout(), carDTO.getAbout()) && Objects.equals(getCity(), carDTO.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSerialNumber(), getManufacture(), getModel(), getYear(), getFuel(), getSeats(), getCarClass(), getPricePerDay(), getAbout(), getCity());
    }
}
