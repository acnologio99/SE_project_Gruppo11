package SE_project2023;

import SE_project2023.Regole.Rule;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author emanu
 */
public class RuleList {

    private static RuleList ruleList = null;

    private ObservableList<Rule> rules;

    private RuleList() {
        rules = FXCollections.observableArrayList();
    }

    public static RuleList getRuleList() {
        if (ruleList == null) {
            ruleList = new RuleList();
        }
        return ruleList;
    }

    public ObservableList<Rule> getArrayList() {
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

    public void iterator() {
        for (Rule r : rules) {
            if(r.ruleIsValid()){
                Platform.runLater(() -> {
                        if (r.isVerifiedRule()) {
                                        r.fire();
                                    }
                                });
                            }
        }
    }

}
