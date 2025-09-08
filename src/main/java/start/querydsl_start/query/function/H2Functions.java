package start.querydsl_start.query.function;

public class H2Functions {
    // 나이(age)에 따라 등급 반환
    public static String getGrade(Integer age) {
        if (age == null) return null;

        if (age >= 40) return "A";
        if (age >= 30) return "B";
        return "C";
    }
}
