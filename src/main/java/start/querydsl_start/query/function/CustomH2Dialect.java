package start.querydsl_start.query.function;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class CustomH2Dialect extends H2Dialect {
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
