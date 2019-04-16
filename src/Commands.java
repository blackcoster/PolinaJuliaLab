import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

class Commands {

    private static Date date = new Date();
    private static SimpleDateFormat fDate = new SimpleDateFormat("dd.MM.yyy hh:mm:ss");

    /**
     * Функция добаления элемента в коллекцию
     * @param argue - аргумент
     * @param posuda - экземпляр коллекции
     */
    static void add(String argue, ArrayDeque<Posuda> posuda) {
        if (argue.equals("")) {
            System.out.println("You can't add null element!");
        } else {
            posuda.add(new Posuda(argue));
            System.out.println("Item " + argue + " was successfully added!");
        }
    }

    /**
     * Функция отображения всех элементов коллекции
     * @param posuda - экземпляр коллекции
     */
    static void show(ArrayDeque<Posuda> posuda) {
        if (posuda.size() == 0) {
            System.out.println("This collection is empty :( Put some items!");
        } else {
            System.out.println("Your collection consists of: ");
            for (Posuda p : posuda) {
                System.out.println(p.getName());
            }
        }
    }

    /**
     * Функция отображения информации о коллекции
     * @param posuda - экземпляр коллекции
     */
    static void info(ArrayDeque<Posuda> posuda) {
        System.out.println("Collection type: " + posuda.getClass() + "\n" + "Date of initialization: " + fDate.format(date));
        System.out.println("Number of items: " + posuda.size());
    }

    /**
     * Функция удаления всех элементов коллекции
     * @param posuda - экземпляр коллекции
     */
    static void clear(ArrayDeque<Posuda> posuda) {
        posuda.clear();
        System.out.println("You deleted all items!");
    }


    /**
     * Функция удаления указанного элемента
     * @param argue - аргумент
     * @param posuda - экземпляр коллекции
     */
    static void delete(String argue, ArrayDeque<Posuda> posuda) {
        for (Posuda p : posuda) {

            if (p.getName().equals(argue)) {
                posuda.remove(p);
            }
        }
        System.out.println("Item " + argue + " was deleted!");
    }

    /**
     * Функция удаления всех элементов коллекции, стоящих ранее по алфавиту
     * @param argue - аргумент
     * @param posuda - экземпляр коллекции
     */
    static void remove_lower(String argue, ArrayDeque<Posuda> posuda) {
        for (Posuda p : posuda) {
            if (p.getName().compareTo(argue) < 0) {
                posuda.remove(p);
            }
        }
        System.out.println("All items lower than this were deleted!");
    }

    /**
     * Функция, завершающая работу приложения
     */
    static void exit() {
        System.out.println("See you later :)");
    }

    /**
     * Функция, импортирующая данные из указанного файла
     * @param argue - аргумент (путь к файлу)
     * @param posuda - экземпляр коллекции
     * @throws FileNotFoundException - файл не найден
     */
    static void importer(String argue, ArrayDeque<Posuda> posuda) throws FileNotFoundException {
        File file2 = new File(argue);
        Scanner scanner2 = new Scanner(file2);
        while (scanner2.hasNext()) {
            String line = scanner2.nextLine();
            if (line.contains("<Name")) {
                String name = line.substring(line.indexOf("<") + 6, line.lastIndexOf(">") - 6);
                posuda.add(new Posuda(name));
            }
        }
        System.out.println("The file was imported successfully!");
    }
}
