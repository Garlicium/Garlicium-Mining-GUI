package main.view;

import main.Main;

import java.io.*;

import java.util.Map;

// http://www.tutorialspoint.com/java/java_serialization.htm was useful

public class Settings {

    private static String settingsPath = "Settings/Settings.ser";

    @SuppressWarnings("unchecked")  // mapObj will create StacktraceAlert if not correct type
    public static Map<String, String> getSettings() {
        Map<String, String> mapObj = null;
        try {
            FileInputStream fileIn;
            try{
                fileIn = new FileInputStream(new File(settingsPath));
            }catch(IOException e){
                fixer();
                fileIn=new FileInputStream(new File(settingsPath));
            }
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object inObj = in.readObject();
            if (inObj instanceof Map) mapObj = (Map<String, String>) inObj;
            else throw new IOException(settingsPath + ": incorrect file contents");
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            new File(settingsPath).delete();
            fixer();
            try {
                FileInputStream fileIn;
                try{
                    fileIn = new FileInputStream(new File(settingsPath));
                }catch(IOException e1){
                    fixer();
                    fileIn=new FileInputStream(new File(settingsPath));
                }
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Object inObj = in.readObject();
                if (inObj instanceof Map) mapObj = (Map<String, String>) inObj;
                else throw new IOException(settingsPath + ": incorrect file contents");
                in.close();
                fileIn.close();
            } catch (IOException | ClassNotFoundException e2) {
                StacktraceAlert.create("Exception occurred", "Does Settings/Settings.ser exist?", "Exception in Settings.getSettings", e2);
            }
        }

        System.out.println(mapObj.get("firstLaunch").getClass());
        Main.firstLaunch=mapObj.get("firstLaunch");
        System.out.println(mapObj);
        return mapObj;
    }

    public static void fixer(){
        try {
            new File("Settings").mkdir();
            new File(settingsPath).createNewFile();
            InputStream sis = Main.class.getClassLoader().getResourceAsStream("main/settings.clean.ser");
            OutputStream outputStream = new FileOutputStream(new File(settingsPath), false);
            while (sis.available() != 0)
                outputStream.write(sis.read());
            sis.close();
            outputStream.close();
        }catch (IOException e){
            StacktraceAlert.create("Exception occurred","Cannot create directory or file "+settingsPath,"Exception in Settings.fixer",e);
        }
    }

    public static void setSettings(Map<String, String> mapObj) {
        try {
            FileOutputStream fileOut = new FileOutputStream(settingsPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(mapObj);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            StacktraceAlert.create("Exception occurred", "Does Settings/Settings.ser exist?", "Exception in Settings.setSettings", e);
        }
    }
}
