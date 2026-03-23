package mapper;

import entity.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据访问层实现类：用HashMap模拟数据库，实现StudentMapper接口的方法
 * 职责：仅处理数据存储和读取，不做任何业务校验（校验交给Service层）
 */
public class StudentMapperImpl implements StudentMapper {

    // 用HashMap存储学生数据：key=学号（唯一），value=学生对象
    private final Map<String, Student> studentMap = new HashMap<>();

    @Override
    public boolean add(Student student) {
        // 若学号已存在，新增失败（返回false）；否则存入map（返回true）
        String studentId = student.getStudentId();
        if (studentMap.containsKey(studentId)) {
            return false;
        }
        studentMap.put(studentId, student);
        return true;
    }
    //TODO 请在此处补全操作HashMap数据库所需的方法的实现
    public Student getId(String studentId) { //按学号
        if (studentMap.containsKey(studentId)) {
            {
                return studentMap.get(studentId);
            }
        }
        return null;//查询失败
    }
    public String getClass(String className){  //按班级
        StringBuilder sb = new StringBuilder();
        for(Student student: studentMap.values()){
            if(student.getClassName().contains(className)) sb.append(student).append("\n");
        }
        return sb.toString();
    }
    public String getAll(){ //全部
        StringBuilder sb = new StringBuilder();
        for(Student student: studentMap.values()){
            sb.append(student).append("\n");
        }
        return sb.toString();
    }
    public boolean update(Student student) {
        String studentId = student.getStudentId();
        if (studentMap.containsKey(studentId)) {
            Student value=studentMap.get(studentId);
            if (student.getName() != null) value.setName(student.getName());
            if(student.getAge()!=0) value.setAge(student.getAge());
            if(student.getGender()!=null) value.setGender(student.getGender());
            if(student.getClassName()!=null) value.setClassName(student.getClassName());
            if(student.getMajor()!=null) value.setMajor(student.getMajor());
            studentMap.put(studentId,value);
            return true;
        }
            return false;

    }
    public boolean delete(String studentId){
        if (studentMap.containsKey(studentId)) {
            studentMap.remove(studentId);
            return true;
        }
        return false;
    }
}