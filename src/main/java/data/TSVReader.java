package data;
import java.util.*;
import java.io.*;
/**
 * TSVReader: pure File IO tsv reader
 */
public class TSVReader {
    private boolean ready;
    private boolean hasSource;
    private String filename;
    private Vector<Vector<String>> tableContent;
    public TSVReader(String file){
        if (file == null || file == "") {
            this.hasSource = false;
        } else {
            this.hasSource = true;
            this.filename = file;
        }
        this.ready = false;
    }
    public void Parse() {
        if (hasSource) {
            InputStream fileInput;
            try {
                fileInput = getClass().getClassLoader().getResourceAsStream
                        (this.filename);
                Reader isr = new InputStreamReader(fileInput);
                BufferedReader br = new BufferedReader(isr);
                this.tableContent = new Vector<>();
                for (String line = br.readLine(); line != null; ) {
                    StringTokenizer tokens = new StringTokenizer(line, "\t");
                    Vector<String> vector = new Vector<>();
                    while (tokens.hasMoreTokens()) {
                        vector.add(tokens.nextToken().intern());
                    }
                    line = br.readLine();
                    tableContent.add(vector);
                }
                br.close();
                this.ready = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Vector<Vector<String>> Content() throws IllegalStateException{
        if (ready){
            return this.tableContent;
        }
        throw new IllegalStateException("Reader has not parsed inputSource " +
                "yet.");
    }
}
