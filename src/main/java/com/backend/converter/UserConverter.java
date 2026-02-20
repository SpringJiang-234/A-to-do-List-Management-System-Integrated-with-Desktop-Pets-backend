package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.UserDetails;
import com.backend.domain.dto.UserDTO;
import com.backend.domain.entity.User;
import com.backend.domain.excel.UserExcel;
import com.backend.domain.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserConverter {

    User userDTO2user(UserDTO userDTO);

    /**
     * 将User对象转换为UserDetails对象
     * 用于转换用户基本信息
     *
     * @param user 源User对象，包含用户基本信息
     * @return UserDetails对象，包含用户详细信息
     */
    UserDetails user2userDetails(User user);

    /**
     * 将PageBean<User>分页对象转换为PageBean<UserVO>分页对象
     * 用于分页数据的类型转换，保持分页结构不变
     *
     * @param userPageBean 源User分页对象，包含用户信息的分页数据
     * @return UserVO分页对象，包含转换后的用户VO对象的分页数据
     */
    PageBean<UserVO> userPageBean2userVOPageBean(PageBean<User> userPageBean);

    /**
     * 将User对象转换为UserExcel对象
     * 用于Excel导出用户信息
     *
     * @param user 源User对象，包含用户基本信息
     * @return UserExcel对象，包含用户Excel信息
     */
    UserExcel user2userExcel(User user);

    /**
     * 将User列表转换为UserExcel列表
     * 批量转换用户信息用于Excel导出
     *
     * @param userList 源User对象列表
     * @return UserExcel对象列表，用于Excel导出
     */
    List<UserExcel> userList2userExcelList(List<User> userList);

    /**
     * 将UserExcel对象转换为User对象
     * 用于从Excel导入用户信息
     *
     * @param userExcel 源UserExcel对象，包含从Excel导入的用户信息
     * @return User对象，包含转换后的用户基本信息
     */
    User userExcel2user(UserExcel userExcel);

    /**
     * 将UserExcel列表转换为User列表
     * 批量转换从Excel导入的用户信息
     *
     * @param userExcelList 源UserExcel对象列表，包含从Excel导入的用户信息
     * @return User对象列表，包含转换后的用户基本信息
     */
    List<User> userExcelList2userList(List<UserExcel> userExcelList);

}

