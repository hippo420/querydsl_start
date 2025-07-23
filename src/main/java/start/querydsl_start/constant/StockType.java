package start.querydsl_start.constant;

public enum StockType {
        KOSPI("A"),
        KOSDAQ("B");

    private final String description;

    // 생성자
    StockType(String description) {
        this.description = description;
    }

    // 메서드
    public String getDescription() {
        return description;
    }
}
