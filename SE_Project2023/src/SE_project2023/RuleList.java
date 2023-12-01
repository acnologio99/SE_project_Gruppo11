package SE_project2023;

import SE_project2023.Regole.Rule;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emanu
 */
public class RuleList {

    private static RuleList ruleList = null;

    private List<Rule> rules;

    private RuleList() {
        rules = new ArrayList<Rule>();
    }

    public static RuleList getRuleList() {
        if (ruleList == null) {
            ruleList = new RuleList();
        }
        return ruleList;
    }

    public List<Rule> getArrayList() {
        return rules;
    }

    public void add(Rule r) {
        rules.add(r);
    }

    public Rule getLast() {
        return rules.get(rules.size()-1);
    }

    public void removeLast() {
        rules.remove(rules.size()-1);
    }

    

}
