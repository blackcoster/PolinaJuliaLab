import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String argue = null, newCommand;
        String command;
        File file = new File("C:\\Users\\fc462t\\Desktop\\Laba\\src\\Posuda");
        ArrayDeque<Posuda> posuda = new ArrayDeque<>();
        Scanner scanner = new Scanner(file);
        Scanner sc = new Scanner(System.in);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.contains("<Name")) {
                String name = line.substring(line.indexOf("<") + 3 + 3, line.lastIndexOf(">") - 6);
                posuda.add(new Posuda(name));
            }
        }

        System.out.println("You can use this commands: " + "\n" + "     add {element} - adds an item to your collection");
        System.out.println("     info - shows type of collection, amount of elements, etc");
        System.out.println("     show - shows all items of collection");
        System.out.println("     remove {element} - deletes this element from your collection");
        System.out.println("     remove_lower {element} - deletes all elements lower than this");
        System.out.println("     clear - clears all elements from your collection");
        System.out.println("     exit - close application");
        System.out.println("     import {path} - import elements from the file");

        label:
        while (true) {

            System.out.println("Enter the command:");
            command = sc.nextLine();
            command = command.replaceAll("\\s+", "");

            if (command.contains("{") & command.contains("}")) {

                argue = command.substring(command.indexOf("{") + 1, command.indexOf("}"));
                newCommand = command.substring(0, command.indexOf("{"));

            } else {
                newCommand = command;
            }

            switch (newCommand) {
                case "add":

                    Commands.add(argue, posuda);
                    break;

                case "show":

                    Commands.show(posuda);
                    break;

                case "info":
                case "Info":

                    Commands.info(posuda);
                    break;

                case "clear":
                case "Clear":

                    Commands.clear(posuda);
                    break;

                case "remove":
                case "Remove":
                case "delete":
                case "Delete":

                    Commands.delete(argue, posuda);
                    break;

                case "remove_lower":

                    Commands.remove_lower(argue, posuda);
                    break;

                case "exit":
                case "Exit":

                    Commands.exit();
                    break label;

                case "import":

                    Commands.importer(argue, posuda);
                    break;

                default:
                    System.out.println("You entered the wrong command! Read the list of commands carefully!");
                    break;
            }


            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try (PrintWriter writer = new PrintWriter(System.getenv("INPUT"))) {
                        writer.write("<Posuda>");
                        writer.write("\n");
                        for (Posuda p : posuda) {
                            writer.write("  <Name>" + p.getName() + "</Name> ");
                            writer.write("\n");
                        }
                        writer.write("</Posuda>");
                        writer.flush();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            });

        }




    }

}