package com.sh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sh.constant.CrowdConstant;
import com.sh.entity.Admin;
import com.sh.exception.LoginAcctAlreadyInUseException;
import com.sh.exception.LoginFailedException;
import com.sh.mapper.AdminMapper;
import com.sh.service.AdminService;
import com.sh.util.CrowdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created By Sunhu At 2020/6/3 9:46
 * @author Sun
 */
@Service
public class AdminServiceImpl implements AdminService{

    private static final Logger logger=LoggerFactory.getLogger(AdminServiceImpl.class);


    @Resource
    private AdminMapper adminMapper;

   @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Admin record) {

        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createdTime=simpleDateFormat.format(date);

        record.setCreateTime(createdTime);

        String userPswd = record.getUserPswd();
        record.setUserPswd(CrowdUtil.md5(userPswd));
        try {
            adminMapper.insert(record);
        }catch (Exception e){
            e.printStackTrace();
            //捕获异常，是 DuplicateKeyException 类型说明是账号重复导de
            if (e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);

            }
            throw e;

        }

    }

    @Override
    public int insertSelective(Admin record) {
        return adminMapper.insertSelective(record);
    }

    @Override
    public Admin selectByPrimaryKey(Integer id) {
        logger.info("根据id查询");
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        return adminMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return adminMapper.updateByPrimaryKey(record);
    }


    @Override
    public Admin getAdminByLoginAcct(String loginAcct,String userPassword) {

        List<Admin> adminList = adminMapper.getAdminByLoginAcct(loginAcct);

        if (adminList==null || adminList.size()==0){
            throw  new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if (adminList.size()>1){
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        Admin admin = adminList.get(0);

        if (admin==null)
        {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        String passwordDB=admin.getUserPswd();

        String passwordReq= CrowdUtil.md5(userPassword);


        if (!Objects.equals(passwordDB,passwordReq)){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        return admin;



    }




    @Override
    public PageInfo<Admin> getAdminListByKeyWordPaging(String keyword,Integer pageNum,Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);


        List<Admin> adminList = adminMapper.getAdminListByKeyWord(keyword);

        logger.info("=========根据关键字分页获取admin============");

        PageInfo<Admin> pageInfo=new PageInfo<>(adminList);

        return pageInfo;
    }

    /**
     *  全量更新拥有的角色id
     * @param adminId
     * @param roleIdList
     * @return
     */
    @Override
    public int saveAmdinRoleRelation(Integer adminId, List<Integer> roleIdList) {
        //首先删除原有的角色id
        int i=adminMapper.deleteOldRelation(adminId);

        if (roleIdList!=null && roleIdList.size()>0)
        {
            //存储新的角色id
            int n=adminMapper.saveNewRelation(adminId,roleIdList);
        }


        return 1;
    }

    @Override
    public Admin getAdminByLoginAcct(String username) {

        List<Admin> admin = adminMapper.getAdminByLoginAcct(username);
        return admin.get(0);
    }

    @Override
    public void saveAdmin(Admin admin) {

        // 1.密码加密
        String userPswd = admin.getUserPswd();
        // userPswd = CrowdUtil.md5(userPswd);
        userPswd = bCryptPasswordEncoder.encode(userPswd);
        admin.setUserPswd(userPswd);

        // 2.生成创建时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(date);
        admin.setCreateTime(createTime);

        // 3.执行保存
        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();

            logger.info("异常全类名="+e.getClass().getName());

            if(e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
    }
}
