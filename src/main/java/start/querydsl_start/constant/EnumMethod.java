package start.querydsl_start.constant;

public enum EnumMethod {
    PLUS {
        public int apply(int x, int y) { return x + y; }
    },
    MINUS {
        public int apply(int x, int y) { return x - y; }
    },
    TIMES {
        public int apply(int x, int y) { return x * y; }
    },
    DIVIDE {
        public int apply(int x, int y) { return x / y; }
    };

    public abstract int apply(int x, int y);
}
