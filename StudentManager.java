
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {

    /*
      1. 출력 문장을 사용하여 기본 인터페이스 준비를 완료합니다. 
      2. 스캐너를 사용하여 키보드 입력 데이터를 실현합니다. 
      3. 스위치 문을 사용하여 작업 선택을 완료합니다. 
      4. 루프 완료와 함께 다시 메인 인터페이스로 돌아갑니다. 
    */

    public static void main(String[] args) {

        //학생 데이터를 저장할 컬렉션 개체를 만듭니다. 
        ArrayList<Student> array = new ArrayList<Student>();

        //루프 완료와 함께 다시 메인 인터페이스로 돌아갑니다. 
        while (true) {
            // 1. 출력 문장을 사용하여 기본 인터페이스 준비를 완료합니다. 
            System.out.println("---------Welcoem------------");
            System.out.println("1 add student");
            System.out.println("2 delete student");
            System.out.println("3 rewrite student");
            System.out.println("4 check all the student");
            System.out.println("5 quit");
            System.out.println("-------------------------------------");

            // 2. 스캐너를 사용하여 키보드 입력 데이터를 실현합니다. 
            Scanner scanner = new Scanner(System.in);
            int line = scanner.nextInt();


            //3. 	스위치 문을 사용하여 작업 선택을 완료합니다. 
            switch (line) {
                case 1:
                    //System.out.println("add sutendt");
                    addStudent(array);
                    break;
                case 2:
                    //System.out.println("delete student");
                    deleteStudent(array);
                    break;
                case 3:
                    //System.out.println("rewrite student");
                    updataStudent(array);
                    break;
                case 4:
                    //System.out.println("check all the student");
                    findAllStudent(array);
                    break;
                case 5:
                    System.out.println("thanks for using");
                    System.exit(0);
            }
        }
    }


    //학생 정보를 추가하는 방법을 정의합니다. 
    public static void addStudent(ArrayList<Student> array) {

        //키보드를 사용하여 입력하고 선택하여 학생을 추가하면 어떤 종류의 정보를 입력할지 묻는 프롬프트 메시지가 표시됩니다. 
        Scanner sc = new Scanner(System.in);

        //while 루프 외부에서 sid에 액세스 할 수 있도록하기 위해 루프 외부에서 정의합니다. 
        String sid;

        //프로그램이 여기로 돌아올 수 있도록 우리는 재활용을 사용합니다. 
        while (true) {
            System.out.println("enter studentID：");
            sid = sc.nextLine();

            boolean flag = isUsed(array, sid);
            if (flag) {
                System.out.println("invalid ID,enter again");
            } else {
                break;
            }
        }

        System.out.println("enter studentName：");
        String name = sc.nextLine();
        System.out.println("enter sutdentAge：");
        String age = sc.nextLine();
        System.out.println("enter sudentAddress：");
        String address = sc.nextLine();

        //학생 객체를 만들고 키보드로 입력 한 데이터를 학생 객체의 멤버 변수에 할당합니다. 
        Student s = new Student();
        s.setSid(sid);
        s.setName(name);
        s.setAge(age);
        s.setAddress(address);

        //컬렉션에 학생 개체를 추가합니다. （save）
        array.add(s);

        //성공적인 추가에 대한 프롬프트가 제공됩니다. 
        System.out.println("adding student success");

    }

    //학생 ID가 점유되었는지 확인하는 방법을 정의합니다. 
    public static boolean isUsed(ArrayList<Student> array, String sid) {
        //세트의 학생증과 같은 경우 ，return true,  else，return false

        boolean flag = false;

        for (int i = 0; i < array.size(); i++) {
            Student s = array.get(i);
            if (s.getSid().equals(sid)) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    //	학생 정보를 보는 방법을 정의합니다. 
    public static void findAllStudent(ArrayList<Student> array) {
        //헤더 정보를 표시합니다. 
        //   '\t'  
        
        if (array.size() == 0) {
            System.out.println("no info,please add info first");
            //프로그램이 계속 실행되는 것을 방지하기 위해. 
            return;
        }

        System.out.println("ID\t\t\t Name\t\t Age\t\t Address");

        //컬렉션에서 데이터를 꺼내 해당 형식에 따라 학생 정보를 표시합니다. 연령 표시 보충 ”age“
        for (int i = 0; i < array.size(); i++) {
            Student s = array.get(i);
            System.out.println(s.getSid() + "\t\t" + s.getName() + "\t\t" + s.getAge() + "Age\t\t" + s.getAddress());
        }
    }

    //학생 정보 삭제 방법을 정의합니다. 
    public static void deleteStudent(ArrayList<Student> array) {

        // 삭제할 학생 ID를 입력하면 프롬프트 정보가 표시됩니다. 

        Scanner sc = new Scanner(System.in);
        System.out.println("enter the ID you want to delete");
        String sid = sc.nextLine();

        //학생 오퍼레이션을 수정하기 전에 학생 ID가 존재하는지 확인하십시오. 
        //존재하지 않는 경우 프롬프트 메시지가 표시됩니다. 
        //존재하는 경우 수정 작업을 수행하십시오. 
        int index = -1;

        // 컬렉션을 탐색하여 컬렉션에서 해당 학생 개체를 삭제합니다. 
        for (int i = 0; i < array.size(); i++) {
            Student s = array.get(i);
            if (s.getSid().equals(sid)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("invalid ID,enter again");
        } else {
            array.remove(index);
            System.out.println("ID delete success");
        }
    }

    //학생 정보를 수정하는 방법을 정의합니다. 
    public static void updataStudent(ArrayList<Student> array) {
        // 수정할 학생 ID를 입력하면 프롬프트 정보가 표시됩니다. 

        Scanner sc = new Scanner(System.in);
        System.out.println("enter the ID you want to rewrite");
        String sid = sc.nextLine();

        //학생 오퍼레이션을 수정하기 전에 학생 ID가 존재하는지 확인하십시오. 
        //존재하지 않는 경우 프롬프트 메시지가 표시됩니다. 
        //존재하는 경우 수정 작업을 수행하십시오. 
        int index = -1;


        // 컬렉션을 탐색하여 해당 학생 정보를 수정합니다. 
        for (int i = 0; i < array.size(); i++) {
            Student s = array.get(i);
            if (s.getSid().equals(sid)) {
                index = i;
                break;
            }
        }


        if (index == -1) {
            System.out.println("invalid ID,enter again");
        } else {
            for (int j = 0; j < array.size(); j++) {
                Student student = array.get(j);
                if (student.getSid().equals(sid)) {

                    // 수정할 학생 정보를 입력하십시오. 
                    System.out.println("enter student name");
                    String name = sc.nextLine();
                    System.out.println("enter student age");
                    String age = sc.nextLine();
                    System.out.println("enter student address");
                    String address = sc.nextLine();

                    // 학생 개체를 만듭니다. 
                    Student s = new Student();
                    s.setSid(sid);
                    s.setName(name);
                    s.setAge(age);
                    s.setAddress(address);
                    array.set(j, s);

                    // 성공적인 수정에 대한 프롬프트가 제공됩니다. 
                    System.out.println("success");
                    break;
                }
            }
        }
    }
}
