package cn.zcp.bgmange.frame.dialect;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.HashSet;
import java.util.Set;

@Component
public class TiccDialect extends AbstractProcessorDialect {

    private static final String DIALECT_NAME = "Ticc Dialect";//定义方言名称

    public TiccDialect() {
        // 我们将设置此方言与“方言处理器”优先级相同
        // 标准方言, 以便处理器执行交错。
        super(DIALECT_NAME, "ticc", StandardDialect.PROCESSOR_PRECEDENCE);
    }
    /*
     *
     * 元素处理器：“matter”标签。
     */
    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        Set<IProcessor> processors = new HashSet<>();
        processors.add(new DatagridTagProcessor(dialectPrefix));//添加我们定义的标签
        processors.add(new TreegridTagProcessor(dialectPrefix));//添加我们定义的标签


        processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
        return processors;
    }
}
