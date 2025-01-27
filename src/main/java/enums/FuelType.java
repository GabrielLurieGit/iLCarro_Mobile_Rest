package enums;

public enum FuelType {
    GAS("Gas"),
    DIESEL("Diesel"),
    HYBRID("Hybrid"),
    ELECTRIC("Electric");

    private final String fuel;

    FuelType(String fuel) {
        this.fuel = fuel;
    }

    public String getFuel() {
        return fuel;
    }
}

