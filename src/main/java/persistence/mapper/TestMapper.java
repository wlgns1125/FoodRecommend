package persistence.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import persistence.DTO.TestDTO;

import java.util.List;

public interface TestMapper {
    final String getRandom = "SELECT foodName, imgLink from foods order by rand() limit 4;";
    @Select(getRandom)
    @Results(id = "resultSet", value = {
            @Result(property = "foodName", column = "foodName"),
            @Result(property = "imgLink", column = "imgLink"),
    })
    List<TestDTO> getRandom();


//    final String FINDID = "SELECT * FROM 사용자 WHERE ID = #{ID}";
//    @Select(FINDID)
//    @ResultMap("resultSet")
//    public List<UserDTO> selectById(String ID);
}
