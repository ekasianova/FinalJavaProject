import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            FileParser fp;
            fp = new FileParser("/Users/ekasianova/IdeaProjects/FinalJavaProject/src/Students");
            List<Student> students = fp.Parse();
            Report report = new Report();
            if(args.length > 0)
                if(Integer.parseInt(args[0]) == 0)
                    report.longReport(students);
                else
                    System.out.println("Wrong arguments");
            else report.shortReport(students);
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
