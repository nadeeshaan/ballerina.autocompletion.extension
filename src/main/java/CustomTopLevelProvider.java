import org.ballerinalang.annotation.JavaSPIService;
import org.ballerinalang.langserver.compiler.LSContext;
import org.ballerinalang.langserver.completions.providers.scopeproviders.TopLevelScopeProvider;
import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionItemKind;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@JavaSPIService("org.ballerinalang.langserver.completions.spi.LSCompletionProvider")
public class CustomTopLevelProvider extends TopLevelScopeProvider {
    
    private static String LINE_SEPARATOR = System.lineSeparator();
    public CustomTopLevelProvider() {
        super();
    }

    @Override
    public List<CompletionItem> getCompletions(LSContext ctx) {
        List<CompletionItem> completions = super.getCompletions(ctx);
        completions.addAll(getCustomCompletionItems());
        
        return completions;
    }
    
    private List<CompletionItem> getCustomCompletionItems() {
        String insertText = "public function name() {" + LINE_SEPARATOR + "\t" + LINE_SEPARATOR + "}";
        CompletionItem completionItem = new CompletionItem();
        completionItem.setDetail("Public Function");
        completionItem.setLabel("Public Function");
        completionItem.setInsertText(insertText);
        completionItem.setKind(CompletionItemKind.Snippet);
        
        return Collections.singletonList(completionItem);
    }

    @Override
    public Precedence getPrecedence() {
        return Precedence.HIGH;
    }
}
