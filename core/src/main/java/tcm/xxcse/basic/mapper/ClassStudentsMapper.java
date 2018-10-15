package tcm.xxcse.basic.mapper;

import com.hand.hap.mybatis.common.Mapper;
import tcm.xxcse.basic.dto.ClassStudents;

import java.util.List;

public interface ClassStudentsMapper extends Mapper<ClassStudents> {
    public List<ClassStudents> queryData(ClassStudents dto);
}