package ProgramFunctionality;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {

    private File currentFile;
    public File getCurrentFile() {
        return this.currentFile;
    }
    public void setFilePath(String filePath) {
        this.currentFile = new File(filePath);
    }



    public FileManager(String filePath){
        this.currentFile = new File(filePath);
    }



    public ArrayList<String> getTextFromCurrentFile() {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader bufr = new BufferedReader(new FileReader(this.currentFile))) {
            String line;
            while((line = bufr.readLine()) != null){
                list.add(line);
            }
            return list;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public boolean CheckFileExtension(){
        Pattern pattern = Pattern.compile(".+\\.cnum");
        Matcher matcher = pattern.matcher(this.currentFile.getName());
        return matcher.matches();
    }
}
