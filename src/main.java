import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

//Test Input
//D:/Coding/Java


public class main {
    //Root path for operations
    private static String rootPath;

    //Function: To display files under current rootPath.
    public static void showFiles(int indent) throws IOException {
        File folder = new File(rootPath);
        String pad = new String(new char[indent]).replace("\0", "  ");
        File[] files = folder.listFiles();
        Arrays.sort(files);
        for (File entry : files) {
            if (entry.isDirectory()) {
                String oldPath = rootPath;
                String newPath = entry.getCanonicalPath();
                setRootPath(newPath);
                System.out.println(pad + "[Folder] " + entry.getName());
                showFiles(indent + 1);
                setRootPath(oldPath);
                //showFiles(entry);
            } else {
                System.out.println(pad + "[File] " + entry.getName());
            }
        }
    }

    //Function: To delete file
    public static void deleteFile(String name) throws IOException {
        Scanner sc = new Scanner(System.in);
        File folder = new File(rootPath);
        for (File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                if (file.getName().equalsIgnoreCase(name)) {
                    System.out.println("Deleting " + file.getName() + " from path " + file.getCanonicalPath());
                    int confirm;
                    System.out.println("Enter\n 1 to Delete\n 0 to Cancel");
                    confirm = sc.nextInt();
                    if(confirm == 1){
                        file.delete();
                        System.out.println("File Deleted!!");
                    }else if(confirm == 0){
                        System.out.println("File delete aborted.");
                    }else{
                        System.out.println("Wrong input. Please try again");
                    }
                    return;
                }
            }
        }
        System.out.println("File not found!!");
    }

    //Function: To search files under current rootPath.
    public static boolean searchFile(String name) throws IOException {
        File folder = new File(rootPath);
        for (File entry : folder.listFiles()) {
            if (entry.isDirectory()) {
                String oldPath = rootPath;
                String newPath = entry.getCanonicalPath();
                setRootPath(newPath);
                searchFile(name);
                setRootPath(oldPath);
            } else {
                if (entry.getName().equalsIgnoreCase(name)) {
                    System.out.println("--------------------------------------------------------");
                    System.out.println("File found!!\nName: " + entry.getName() + "\nPath: " + entry.getCanonicalPath());
                    System.out.println("--------------------------------------------------------");
                    return true;
                }
            }
        }
        return false;
    }

    //Function: To set rootPath
    public static void setRootPath(String path) {
        rootPath = path;
    }

    //Function: To get rootPath
    public static String getRootPath() {
        return rootPath;
    }

    //Function: Entry point to program
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String root;
        System.out.println("Welcome to File Manager");
        System.out.println("Application Developer: Nayan Mehta");
        System.out.println("Enter path to get started:");
        root = sc.next();
        setRootPath(root);
        int option = 0;
        do {
            switch (option) {
                case 0:
                    //Menu
                    System.out.println("1.  Show files");
                    System.out.println("2.  Add File");
                    System.out.println("3.  Delete File");
                    System.out.println("4.  Search File");
                    System.out.println("5.  Show Root Path");
                    System.out.println("6.  Change Root Path");
                    System.out.println("99. Quit");
                    System.out.print("Select a option to execute: ");

                    option = sc.nextInt();
                    System.out.println();
                    break;

                case 1:
                    //Show Files
                    System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");

                    System.out.println("--------------------------------------------------------");
                    showFiles(0);
                    System.out.println("--------------------------------------------------------");

                    System.out.println("0.  Return to menu");
                    System.out.println("99. Exit");
                    System.out.print("Enter option: ");
                    option = sc.nextInt();
                    System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");

                    break;

                case 2:

                    // Add file
                    System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
                    String name;
                    System.out.println("Enter filename.extension: ");
                    name = sc.next();
                    System.out.println("--------------------------------------------------------");
                    File newFile = new File(rootPath + "/" + name);
                    if (newFile.createNewFile()) {
                        System.out.println("File created.");
                        System.out.println("Name: " + newFile.getName());
                        System.out.println("Path: " + newFile.getCanonicalPath());
                    } else {
                        System.out.println("Failed to create new file.");
                    }
                    System.out.println("--------------------------------------------------------");
                    System.out.println("0.  Return to menu");
                    System.out.println("99. Exit");
                    System.out.print("Enter option: ");
                    option = sc.nextInt();
                    System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");

                    break;

                case 3:
                    //Delete file
                    System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
                    String deleteName;
                    System.out.println("Delete File -->");
                    System.out.println("Enter file name: ");
                    deleteName = sc.next();
                    System.out.println("--------------------------------------------------------");
                    deleteFile(deleteName);
                    System.out.println("--------------------------------------------------------");
                    System.out.println("0.  Return to menu");
                    System.out.println("99. Exit");
                    System.out.print("Enter option: ");
                    option = sc.nextInt();
                    System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");

                    break;

                case 4:
                    //Search File
                    System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
                    String searchName;
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Search File -->");
                    System.out.println("Enter file name: ");
                    searchName = sc.next();
                    if (!searchFile(searchName)) {
                        System.out.println("--------------------------------------------------------");
                        System.out.println("FILE NOT FOUND.");
                        System.out.println("--------------------------------------------------------");
                    }
                    System.out.println("--------------------------------------------------------");
                    System.out.println("0.  Return to menu");
                    System.out.println("99. Exit");
                    System.out.print("Enter option: ");
                    option = sc.nextInt();
                    System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
                    break;

                case 5:
                    //Display Root Path
                    System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Current Path: " + getRootPath());
                    System.out.println("--------------------------------------------------------");
                    System.out.println("0.  Return to menu");
                    System.out.println("99. Exit");
                    System.out.print("Enter option: ");
                    option = sc.nextInt();
                    System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
                    break;

                case 6:
                    // Change Root Path
                    System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
                    String newPath;
                    System.out.println("Change Root Path -->");
                    System.out.println("Enter new path: ");
                    newPath = sc.next();
                    setRootPath(newPath);
                    System.out.println("0.  Return to menu");
                    System.out.println("99. Exit");
                    System.out.print("Enter option: ");
                    option = sc.nextInt();
                    System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-");
                    break;

                default:
                    System.out.println("---> Sorry this option does not exist. <---");
                    System.out.println("Returning to main menu...");
                    option = 0;
                    break;
                    
            }
        } while (option != 99);
    }
}