package register.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import register.com.entity.Pad;
import register.com.dao.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void insertPad(String name, String cpuid) {
        Pad pad = new Pad(name, cpuid);
        userMapper.insertPad(pad);
    }

    public String getPadByCPUID(String cpuid) {
        return userMapper.getNameByCPUID(cpuid);
    }

    public String getNameLast(){
        return userMapper.getNameLast();
    }
}
