package entity;

/**
 * 学生实体类：封装学生的基本信息
 * 职责：仅作为数据载体，不包含业务逻辑
 */
public class Student {
    // 学号（唯一标识，不可重复）
    private String studentId;
    // 姓名（非空）
    private String name;
    // 性别（仅"男"/"女"）
    private String gender;
    // 年龄（18-25之间）
    private int age;
    // 班级
    private String className;
    // 专业
    private String major;

    // 无参构造方法（用于对象创建后通过setter赋值）
    public Student() {
    }

    // 全参构造方法（用于一次性初始化所有属性）
    public Student(String studentId, String name, String gender, int age, String className, String major) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.className = className;
        this.major = major;
    }

    // getter和setter方法（用于访问和修改私有属性）
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;
    }

    public String getGender() {return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    // 重写toString方法（用于打印学生信息）
    @Override
    public String toString() {
        return "Student{" +
                "学号='" + studentId + '\'' +
                ", 姓名='" + name + '\'' +
                ", 性别='" + gender + '\'' +
                ", 年龄=" + age +
                ", 班级='" + className + '\'' +
                ", 专业='" + major + '\'' +
                '}';
    }
}