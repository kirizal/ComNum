package ProgramFunctionality;
import java.io.FileInputStream;

public class FileManager {

    private String FilePath;
    public String getFilePath() {
        return FilePath;
    }
    public void setFilePath(String filePath) {
        FilePath = filePath;
    }
    public FileManager(String filePath){
        this.FilePath = filePath;
    }



    public byte[] getDataFromFile() {
        byte[] buffer = new byte[0];
        try (FileInputStream fileInputStream = new FileInputStream(this.FilePath)) {
            int fileSize = fileInputStream.available();
            buffer = new byte[fileSize];
            fileInputStream.read(buffer, 0, fileSize);
            return buffer;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return buffer;
    }
}
