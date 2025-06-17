import java.io.*;
import java.util.*;
public class StudentManagementSystem 
{
    static HashMap<String, String> studentMap = new HashMap<>();
    static final String FILE_NAME = "students.txt";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do 
		{
            System.out.println("\n-- Student Management System --");
            System.out.println("1. Add student");
            System.out.println("2. Save to file");
            System.out.println("3. Load from file");
            System.out.println("4. Search by ID");
            System.out.println("5. Remove student");
            System.out.println("6. Display all students");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (choice) 
			{
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    saveToFile();
                    break;
                case 3:
                    loadFromFile();
                    break;
                case 4:
                    searchStudents(sc);
                    break;
                case 5:
                    removeStudents(sc);
                    break;
                case 6:
                    displayAll();
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
        sc.close();
    }
    static void addStudent(Scanner sc)
	{
        System.out.print("Enter student ID: ");
        String id = sc.nextLine();
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        if (studentMap.containsKey(id)) {
            System.out.println("ID already exists!");
        } else {
            studentMap.put(id, name);
            System.out.println("Student added.");
        }
    }
    static void saveToFile()
	{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME)))
		{
            for (Map.Entry<String, String> entry : studentMap.entrySet())
			{
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
            System.out.println("Data saved to file.");
        } catch (IOException e) 
		{
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
    static void loadFromFile()
	{
        studentMap.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME)))
		{
            String line;
            while ((line = br.readLine()) != null)
			{
                String[] parts = line.split(",");
                if (parts.length == 2)
				{
                    String id = parts[0];
                    String name = parts[1];
                    studentMap.put(id, name);
                }
            }
            System.out.println("Data loaded from file.");
        } 
		catch (IOException e)
		{
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
    static void searchStudents(Scanner sc)
	{
        System.out.print("Enter ID to search: ");
        String id = sc.nextLine();
        if (studentMap.containsKey(id))
		{
            System.out.println("Name: " + studentMap.get(id));
        } else
		{
            System.out.println("Student ID not found.");
        }
    }
    static void removeStudents(Scanner sc)
	{
        System.out.print("Enter ID to remove: ");
        String id = sc.nextLine();
        if (studentMap.remove(id) != null)
		{
            System.out.println("Student removed.");
        } 
		else
		{
            System.out.println("Student ID not found.");
        }
    }
    static void displayAll()
	{
        if (studentMap.isEmpty())
		{
            System.out.println("No students to display.");
        } 
		else
		{
            System.out.println("\nStudent List:");
            for (Map.Entry<String, String> entry : studentMap.entrySet())
			{
                System.out.println("ID: " + entry.getKey() + ", Name: " + entry.getValue());
            }
        }
    }
}