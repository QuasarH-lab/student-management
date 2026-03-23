package controller;


import entity.Student;
import service.StudentService;

import java.util.Scanner;

/**
 * 控制层：负责接收用户输入，调用Service层处理，展示结果
 * 不包含任何业务逻辑，仅做交互和调用转发
 */
public class StudentController {
    // 依赖Service层（通过构造方法注入，实际运行时需传入Service实现类对象）
    private final StudentService studentService;
    private final Scanner scanner = new Scanner(System.in);

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 启动系统菜单
    public void start() {
        System.out.println("===== 学生管理系统 =====");
        while (true) {
            printMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    queryStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.out.println("系统已退出，再见！");
                    return;
                default:
                    System.out.println("无效操作，请重新选择！");
            }
        }
    }

    // 打印菜单
    private void printMenu() {
        System.out.println("\n请选择操作：");
        System.out.println("1. 新增学生");
        System.out.println("2. 查询学生");
        System.out.println("3. 修改学生信息");
        System.out.println("4. 删除学生");
        System.out.println("5. 退出系统");
        System.out.print("请输入操作编号：");
    }

    // 获取用户选择（处理非数字输入）
    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // 返回无效值，触发default处理
        }
    }

    // 新增学生
    private void addStudent() {
        System.out.println("\n===== 新增学生 =====");
        Student student = new Student();

        System.out.print("请输入学号：");
        student.setStudentId(scanner.nextLine().trim());

        System.out.print("请输入姓名：");
        student.setName(scanner.nextLine().trim());

        System.out.print("请输入性别（男/女）：");
        student.setGender(scanner.nextLine().trim());

        System.out.print("请输入年龄（18-25）：");
        try {
            student.setAge(Integer.parseInt(scanner.nextLine().trim()));
        } catch (NumberFormatException e) {
            System.out.println("年龄格式错误，新增失败！");
            return;
        }

        System.out.print("请输入班级：");
        student.setClassName(scanner.nextLine().trim());

        System.out.print("请输入专业：");
        student.setMajor(scanner.nextLine().trim());

        // 调用Service层处理，接收结果并打印
        String result = studentService.addStudent(student);
        System.out.println(result);
    }

    // 查询学生（子菜单）
    private void queryStudent() {
        System.out.println("\n===== 查询学生 =====");
        System.out.println("1. 按学号查询");
        System.out.println("2. 按班级查询");
        System.out.println("3. 查询所有学生");
        System.out.print("请选择查询方式：");

        int queryChoice = getChoice();
        String result;
        switch (queryChoice) {
            case 1:
                System.out.print("请输入学号：");
                String studentId = scanner.nextLine().trim();
                result = studentService.getStudentByStudentId(studentId);
                break;
            case 2:
                System.out.print("请输入班级：");
                String className = scanner.nextLine().trim();
                result = studentService.getStudentsByClassName(className);
                break;
            case 3:
                result = studentService.getAllStudents();
                break;
            default:
                result = "无效的查询方式！";
        }
        System.out.println(result);
    }

    // 修改学生信息（仅允许修改非学号字段）
    private void updateStudent() {
        System.out.println("\n===== 修改学生信息 =====");
        System.out.print("请输入要修改的学生学号：");
        String studentId = scanner.nextLine().trim();

        // 先查询学生是否存在
        Student student = null;
        String queryResult = studentService.getStudentByStudentId(studentId);
        if (queryResult.contains("不存在")) {
            System.out.println(queryResult);
            return;
        }

        // 若存在，创建新对象封装修改后的数据（学号不可改）
        student = new Student();
        student.setStudentId(studentId); // 学号固定

        System.out.print("请输入新姓名（不修改请直接回车）：");
        String name = scanner.nextLine().trim();
        student.setName(name.isEmpty() ? null : name); // 空输入表示不修改

        System.out.print("请输入新性别（男/女，不修改请直接回车）：");
        String gender = scanner.nextLine().trim();
        student.setGender(gender.isEmpty() ? null : gender);

        System.out.print("请输入新年龄（18-25，不修改请直接回车）：");
        String ageStr = scanner.nextLine().trim();
        if (!ageStr.equals("0")) {    //进行修改，避免输入为0时无反馈
            if (!ageStr.isEmpty()) {
                try {
                    student.setAge(Integer.parseInt(ageStr));
                } catch (NumberFormatException e) {
                    System.out.println("年龄格式错误，修改失败！");
                    return;
                }
            }
        }else System.out.println("修改失败：年龄必须在18-25岁之间！");



        System.out.print("请输入新班级（不修改请直接回车）：");
        String className = scanner.nextLine().trim();
        student.setClassName(className.isEmpty() ? null : className);

        System.out.print("请输入新专业（不修改请直接回车）：");
        String major = scanner.nextLine().trim();
        student.setMajor(major.isEmpty() ? null : major);

        // 调用Service层处理
        String result = studentService.updateStudent(student);
        System.out.println(result);
    }

    // 删除学生
    private void deleteStudent() {
        System.out.println("\n===== 删除学生 =====");
        System.out.print("请输入要删除的学生学号：");
        String studentId = scanner.nextLine().trim();

        String result = studentService.deleteStudent(studentId);
        System.out.println(result);
    }
}