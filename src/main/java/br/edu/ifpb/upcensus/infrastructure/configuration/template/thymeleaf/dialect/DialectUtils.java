package br.edu.ifpb.upcensus.infrastructure.configuration.template.thymeleaf.dialect;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

import br.edu.ifpb.upcensus.infrastructure.util.StringUtils;
import br.edu.ifpb.upcensus.infrastructure.util.TimeUtils;

@Component
public class DialectUtils extends AbstractDialect implements IExpressionObjectDialect {

    private static final Map<String, Object> EXPRESSIONS = new HashMap<String, Object>()
    {
    	private static final long serialVersionUID = 1L;
	{
        put("string", StringUtils.getInstance());
        put("time", TimeUtils.getInstance());
    }};

    public DialectUtils() {
        super("Utils Dialect");
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return new IExpressionObjectFactory() {

            @Override
            public Set<String> getAllExpressionObjectNames() {
                return EXPRESSIONS.keySet();
            }

            @Override
            public Object buildObject(IExpressionContext context, String expressionObjectName) {
                return EXPRESSIONS.get(expressionObjectName);
            }

            @Override
            public boolean isCacheable(String expressionObjectName) {
                return true;
            }
        };
	}
}