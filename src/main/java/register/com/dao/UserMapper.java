package register.com.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import register.com.entity.Pad;

@Mapper
@Component
public interface UserMapper {
    /**
     * 插入pad数据
     * @param pad pad对象
     */
    void insertPad(Pad pad);

    /**
     * 根据cpuid查看对应的名称
     * @param cpuid pad的24位的cpuid
     * @return 分页数据
     */
    String getNameByCPUID(String cpuid);

    /**
     * 查询数据表的最后一条记录，获取其中的名称数据
     * @return pad的名称
     */
    String getNameLast();
}
