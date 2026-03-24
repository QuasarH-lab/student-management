package mapper;

import entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据访问层接口：定义学生数据的CRUD操作规范
 * 职责：仅声明数据操作方法，不涉及业务逻辑和实现
 */
public interface StudentMapper {

    /**
     * 新增学生
     * @param student 学生对象（包含学号等信息）
     * @return 新增成功返回true，失败返回false（如学号已存在时返回false）
     */
    boolean add(Student student);

    //TODO 请在此处补全操作HashMap所需的方法
    //查询学生
    String getId(String studentId);
    String getClass(String className);
    String getAll();
    boolean update(Student student);
    boolean delete(String studentId);

}