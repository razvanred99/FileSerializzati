import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class FileManager {

    private static final String FILE_NAME = "dati.dat";
    private static final String TEMP_FILE = "temp.dat";

    public static void insertOne(@NotNull final Object o) throws Exception {

        final File file = new File(FILE_NAME);
        final ObjectOutputStream oOUT;
        final FileOutputStream fOUT;

        if (file.exists()) {
            fOUT = new FileOutputStream(file, true);
            oOUT = new AppendObjectOutputStream(fOUT);
        } else {
            fOUT = new FileOutputStream(file);
            oOUT = new ObjectOutputStream(fOUT);
        }

        oOUT.writeObject(o);
        oOUT.flush();

        oOUT.close();
        fOUT.close();

    }

    public static @NotNull ArrayList<Dog> readAll() throws Exception {

        final ArrayList<Dog> list = new ArrayList<>();
        final File file = new File(FILE_NAME);

        if (file.exists()) {

            final FileInputStream fIN = new FileInputStream(file);
            final ObjectInputStream oIN = new ObjectInputStream(fIN);

            while (true) {
                try {
                    list.add((Dog) oIN.readObject());
                } catch (final Exception exc) {
                    break;
                }
            }

            fIN.close();
            oIN.close();
        }

        return list;
    }

    public static void remove(@NotNull final Dog dog) throws Exception {

        final File file = new File(FILE_NAME);

        if (file.exists()) {
            final FileInputStream fIN = new FileInputStream(file);
            final ObjectInputStream oIN = new ObjectInputStream(fIN);

            final File temp = new File(TEMP_FILE);

            final FileOutputStream fOUT = new FileOutputStream(temp);
            final ObjectOutputStream oOUT = new ObjectOutputStream(fOUT);

            while (true) {
                try {
                    final Dog o = (Dog) oIN.readObject();
                    if (!dog.equals(o)) {
                        oOUT.writeObject(o);
                    }
                } catch (final Exception exc) {
                    break;
                }
            }

            oOUT.close();
            fOUT.close();

            fIN.close();
            oIN.close();

            file.delete();
            temp.renameTo(file);
        }

    }

}
