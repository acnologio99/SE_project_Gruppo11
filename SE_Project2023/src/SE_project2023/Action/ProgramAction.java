/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Action;

/**
 *
 * @author cauro
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProgramAction implements Action {
    private String programPath;
    private List<String> commandLineArgs;
    private boolean isFired = false;
    private String output;
    public ProgramAction(String programPath, List<String> commandLineArgs) {
        this.programPath = programPath;
        this.commandLineArgs = commandLineArgs;
    }

    @Override
    public boolean isFired() {
        return isFired;
    }


    public void fire() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = new ArrayList<>(commandLineArgs);
        command.add(0, programPath);

        processBuilder.command(command);

         try {
        Process process = processBuilder.start();
        isFired = true;

        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder outputBuilder = new StringBuilder(); // Salva l'output del processo in una stringa
        String line;
        while ((line = reader.readLine()) != null) {
            outputBuilder.append(line).append("\n");
        }
        output = outputBuilder.toString(); // Memorizza l'output nel membro output della classe

        System.out.println("Program executed successfully.");
        System.out.println("Program output: " + output); // Stampa l'output per verificare se è stato catturato correttamente

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public String getOutput() {
        return output;
    }

   public String getProgramPath() {
        return programPath;
    }

    public void setProgramPath(String programPath) {
        this.programPath = programPath;
    }


    @Override
    public void add() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Action getChild() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
