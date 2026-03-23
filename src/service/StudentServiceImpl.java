package service;


import entity.Student;
import mapper.StudentMapper;

/**
 * 业务逻辑层实现类：实现具体业务逻辑，依赖Mapper层进行数据操作
 * 职责：处理数据校验、业务规则，调用Mapper层方法完成数据操作
 */
public class StudentServiceImpl implements StudentService {

    // 依赖Mapper层（通过构造方法注入，解耦且便于测试）
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public String addStudent(Student student) {
        // 1. 校验必填字段
        if (student.getStudentId() == null || student.getStudentId().trim().isEmpty()) {
            return "新增失败：学号不能为空！";
        }
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            return "新增失败：姓名不能为空！";
        }

        // 2. 校验性别合法性
        if (!"男".equals(student.getGender()) && !"女".equals(student.getGender())) {
            return "新增失败：性别必须为'男'或'女'！";
        }

        // 3. 校验年龄范围
        if (student.getAge() < 18 || student.getAge() > 25) {
            return "新增失败：年龄必须在18-25岁之间！";
        }

        // 4. 调用Mapper层新增，返回结果
        boolean isSuccess = studentMapper.add(student);
        return isSuccess ? "新增成功！" : "新增失败：学号已存在！";
    }

    @Override
    public String getStudentByStudentId(String studentId) {
        //TODO 请在此处补全业务逻辑
        Student student=studentMapper.getId(studentId);
        if(student==null){
            return "学生不存在";
        }
        else{
            return student.toString();
        }
    }

    @Override
    public String getStudentsByClassName(String className) {
        //TODO 请在此处补全业务逻辑
        if (studentMapper.getClass(className).equals("")){
            return "该班级暂无学生";
        }else{
        return studentMapper.getClass(className);}
    }

    @Override
    public String getAllStudents() {
        //TODO 请在此处补全业务逻辑
        if (studentMapper.getAll().equals("")){
            return "暂无学生数据";
        }else{
            return studentMapper.getAll();
        }
    }

    @Override
    public String updateStudent(Student student) {
        //TODO 请在此处补全业务逻辑
        //校验性别
        if (!"男".equals(student.getGender()) && !"女".equals(student.getGender())&&null!=student.getGender()) {
            return "修改失败：性别必须为'男'或'女'！";
        }
        //  校验年龄范围
        if (((student.getAge() < 18 || student.getAge() > 25))&&student.getAge()!=0) {
            return "修改失败：年龄必须在18-25岁之间！";
        }
        boolean isSuccess = studentMapper.update(student);
        return isSuccess ? "修改成功！" : "修改失败：学号不存在！";
    }

    @Override
    public String deleteStudent(String studentId) {
        //TODO 请在此处补全业务逻辑
        boolean isSuccess = studentMapper.delete(studentId);
        return isSuccess ? "删除成功！" : "删除失败：学号不存在！";

    }
}