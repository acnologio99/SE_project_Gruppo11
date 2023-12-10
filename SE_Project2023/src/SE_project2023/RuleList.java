package SE_project2023;

import SE_project2023.Regole.Rule;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La lista è sia Observable che Observer. E' un Observer in quanto riceve tutti
 * i cambiamenti delle regole che essa contiene. E' Observable in quanto
 * comunica tutti i cambiamenti delle regole al suo interno anche all'esterno.
 * La proprietà di Observable viene utilizzata col controller per permettere di
 * aggiornare gli elementi sottostanti nella tableView.
 *
 * E' Serializable per essere salvata su file. E' Iterable in quanto contiene
 * una Lista sottostante che non vogliamo venga restituita esplicitamente.
 *
 * @author emanu
 */
public class RuleList extends Observable implements Observer, Serializable, Iterable<Rule> {

    private static RuleList ruleList = null; //La RuleList viene implementata come Singleton in quanto deve essere l'unico punto
    // d'accesso per manipolare le regole e non deve essere creata ogni volta nuovamente.
    private List<Rule> rules;

    private RuleList() {
        rules = new ArrayList<>();

    }

    public static RuleList getRuleList() {
        if (ruleList == null) {
            ruleList = new RuleList();

        }
        return ruleList;
    }

    public int size() {
        return rules.size();    //I metodi richiamano i metodi della lista sottostante in modo da non doverla restituire esplicitamente.
    }

    public boolean isEmpty() {
        return rules.isEmpty();
    }

    public boolean removeAll(Collection c) {
        Boolean a = rules.removeAll(c);
        this.setChanged();      //Notifica del cambiamento tutti gli Observer
        this.notifyObservers();
        return a;
    }

    public void add(Rule r) {
        rules.add(r);
        r.addObserver(this);
        this.setChanged();
        this.notifyObservers();
    }

    public Rule getLast() {
        return rules.get(rules.size() - 1);
    }

    public void removeLast() {
        rules.remove(rules.size() - 1);
        this.setChanged();
        this.notifyObservers();

    }

    public Rule get(int index) {
        Rule r = rules.get(index);
        this.setChanged();
        this.notifyObservers();

        return r;
    }

    public boolean remove(Rule r) {
        boolean rmv = rules.remove(r);
        this.setChanged();
        this.notifyObservers();
        return rmv;
    }

    public void clear() {
        rules.clear();
        this.setChanged();
        this.notifyObservers();
    }

    //Funzione di salvataggio della lista, se il file non esiste lo crea
    public void saveRules(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile(); // Crea un nuovo file se non esiste
            } catch (IOException ex) {
                Logger.getLogger(RuleList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try (ObjectOutputStream objectOut = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(filename)))) {

            objectOut.writeObject(rules);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RuleList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RuleList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Funzione di caricamento della lista, usa una lista d'appoggio per caricare le regole che poi verranno aggiunte
    //a Rules in modo tale da poterle osservare tutte nuovamente anche dopo il caricamento.
    public void loadRules(String filename) {
        try (ObjectInputStream objectIn = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(filename)))) {
            List<Rule> l = (List<Rule>) objectIn.readObject(); // Leggi la lista delle regole
            for (Rule r : l) {
                this.add(r);
            }
        } catch (FileNotFoundException e) {
            // Se il file non esiste, esce senza fare nulla
            return;
        } catch (EOFException eof) {
            // Gestisci l'EOFException se necessario
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Gestisci altre eccezioni
        }
    }
    
    //Il metodo update comunica agli Observer che la lista si è aggiornata quando questa riceve un aggiornamento dalle regole che osserva.
    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
    }
    
    //L'iteratore restituito è quello di rules, in questo modo è possibile eseguire i cicli iterando direttamente su RuleList.
    @Override
    public Iterator<Rule> iterator() {
        return rules.iterator();
    }

}
