package SE_project2023;

import SE_project2023.Regole.Rule;
import java.util.HashSet;

/**
 *
 * @author emanu
 */
public class RuleList {

    private static RuleList ruleList = null;

    private HashSet<Rule> hashRules;

    private RuleList() {
        hashRules = new HashSet<Rule>();
    }

    public static RuleList getRuleList() {
        if (ruleList == null) {
            ruleList = new RuleList();
        }
        return ruleList;
    }

    public HashSet<Rule> getHashRules() {
        return hashRules;
    }

    void add(Rule r) {
        hashRules.add(r);
    }

}
