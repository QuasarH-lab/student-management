import controller.StudentController;
import mapper.StudentMapper;
import mapper.StudentMapperImpl;
import service.StudentService;
import service.StudentServiceImpl;

public class Main {
    public static void main(String[] args) {
        // 初始化各层对象（依赖关系：Controller → Service → Mapper）
        StudentMapper mapper = new StudentMapperImpl();
        StudentService service = new StudentServiceImpl(mapper);
        StudentController controller = new StudentController(service);
        // 启动系统
        controller.start();
    }
}