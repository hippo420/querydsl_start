package start.querydsl_start.query.function;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class CustomDialect extends MySQLDialect {
    public CustomDialect() {
        super();
    }

    @Override
    public void contributeFunctions(FunctionContributions functionContributions) {
        super.contributeFunctions(functionContributions);

        // get_grade 함수 등록
        functionContributions.getFunctionRegistry().register(
                "get_grade",
                new StandardSQLFunction("get_grade", StandardBasicTypes.STRING)
        );
    }
}
