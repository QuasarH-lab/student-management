package service;

import entity.Student;

/**
 * 业务逻辑层接口：定义学生管理的业务操作规范
 * 职责：声明业务方法，包含数据校验和逻辑处理要求
 */
public interface StudentService {

    /**
     * 新增学生（包含业务校验）
     * @param student 学生对象
     * @return 操作结果信息（如"添加成功"或具体错误提示）
     */
    String addStudent(Student student);

    /**
     * 按学号查询学生
     * @param studentId 学号
     * @return 学生信息字符串（如存在则返回详情，不存在则返回提示）
     */
    String getStudentByStudentId(String studentId);

    /**
     * 按班级查询学生
     * @param className 班级名称
     * @return 班级学生列表字符串（如存在则返回所有学生，不存在则返回提示）
     */
    String getStudentsByClassName(String className);

    /**
     * 查询所有学生
     * @return 所有学生列表字符串（如存在则返回所有学生，无数据则返回提示）
     */
    String getAllStudents();

    /**
     * 修改学生信息（包含业务校验）
     * @param student 包含更新信息的学生对象（需包含学号）
     * @return 操作结果信息（如"修改成功"或具体错误提示）
     */
    String updateStudent(Student student);

    /**
     * 按学号删除学生（包含业务校验）
     * @param studentId 学号
     * @return 操作结果信息（如"删除成功"或具体错误提示）
     */
    String deleteStudent(String studentId);
}
