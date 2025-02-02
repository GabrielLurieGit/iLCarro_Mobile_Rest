package interfaces;

public interface ValidateSearchAddCar {
    String SEARCH_EMPTY_CITY = "City can't be empty";
    String TO_BEFORE_FROM = "To date can't be before from date";
    String FROM_BEFORE_TO = "From date can't be before today";
    String MISSING_SERIAL_MANUFACT_MODEL_CITY_PRICE = "Fields: Serial number, Manufacture, Model, City, Price per day is required!";
    String CAR_CLASS_EMPTY = "{carClass=must not be blank}";
    String YEAR_EMPTY = "{year=must not be blank}";
    String INVALID_SEATS_NUMBER = "Wrong seats count format! Seats count should be number";


}
