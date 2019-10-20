import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.server.ExportException;
import java.util.Vector;

public class Utilities {
    static Integer find_max_Int(Vector<Integer> vector) {
        Integer max = 0;
        for (Integer x : vector) {
            if (x > max) {
                max = x;
            }
        }
        return max;
    }

    static Vector<Integer> string_array_to_integer_vector(String [] array){
        Vector<Integer> integers_vector = new Vector<>();
        for(String s:array){
            integers_vector.add(Integer.valueOf(s));
        }
        return integers_vector;
    }

    static void append_string_to_file(String data, String file_name){
        File file = new File(file_name);
        try{
            FileWriter fr = new FileWriter(file, true);
            fr.write(data);
            fr.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    static void write_string_to_file(String data, String file_name){
        File file = new File(file_name);
        try{
            FileWriter fr = new FileWriter(file, false);
            fr.write(data);
            fr.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    static String file_to_string(String file_name){
        String data ="";
        try{
            data = new String (Files.readAllBytes(Paths.get(file_name)));
        }catch(Exception e){
            e.printStackTrace();
        }

        return data;
    }
}
