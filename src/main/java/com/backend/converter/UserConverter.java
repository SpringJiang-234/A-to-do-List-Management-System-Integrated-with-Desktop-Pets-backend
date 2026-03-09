package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.UserDetails;
import com.backend.domain.info.UserInfo;
import com.backend.domain.dto.UserDTO;
import com.backend.domain.entity.User;
import com.backend.domain.enums.UserGender;
import com.backend.domain.enums.UserStatus;
import com.backend.domain.enums.UserType;
import com.backend.domain.excel.UserExcel;
import com.backend.domain.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserConverter {

    User userDTO2user(UserDTO userDTO);

    /**
     * 将User对象转换为UserInfo对象
     * 用于登录时返回用户信息
     *
     * @param user 源User对象，包含用户基本信息
     * @return UserInfo对象，包含用户登录信息
     */
    @Mapping(source = "nickname", target = "username")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "birth", target = "birth")
    UserInfo user2userInfo(User user);

    /**
     * 将User对象转换为UserVO对象
     * 用于转换用户基本信息
     *
     * @param user 源User对象，包含用户基本信息
     * @return UserVO对象，包含用户VO信息
     */
    @Mapping(source = "type", target = "type", qualifiedByName = "typeToText")
    @Mapping(source = "status", target = "status", qualifiedByName = "statusToText")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "genderToText")
    @Mapping(source = "avatar", target = "avatar")
    UserVO user2userVO(User user);

    /**
     * 将User对象转换为UserDetails对象
     * 用于转换用户基本信息
     *
     * @param user 源User对象，包含用户基本信息
     * @return UserDetails对象，包含用户详细信息
     */
    @Mapping(source = "type", target = "type", qualifiedByName = "typeToText")
    @Mapping(source = "status", target = "status", qualifiedByName = "statusToText")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "genderToText")
    @Mapping(source = "avatar", target = "avatar")
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
    @Mapping(source = "type", target = "type", qualifiedByName = "typeToText")
    @Mapping(source = "status", target = "status", qualifiedByName = "statusToText")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "genderToText")
    @Mapping(source = "avatar", target = "avatar")
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
    @Mapping(source = "type", target = "type", qualifiedByName = "textToType")
    @Mapping(source = "status", target = "status", qualifiedByName = "textToStatus")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "textToGender")
    User userExcel2user(UserExcel userExcel);

    /**
     * 将UserExcel列表转换为User列表
     * 批量转换从Excel导入的用户信息
     *
     * @param userExcelList 源UserExcel对象列表，包含从Excel导入的用户信息
     * @return User对象列表，包含转换后的用户基本信息
     */
    List<User> userExcelList2userList(List<UserExcel> userExcelList);

    /**
     * 将Integer类型的type转换为String类型的文本
     * 用于导出Excel时显示中文
     *
     * @param type 用户类型的数字编码
     * @return 用户类型的中文文本
     */
    @Named("typeToText")
    default String typeToText(Integer type) {
        return UserType.getTextByCode(type);
    }

    /**
     * 将String类型的文本转换为Integer类型的type
     * 用于导入Excel时解析中文
     *
     * @param text 用户类型的中文文本
     * @return 用户类型的数字编码
     */
    @Named("textToType")
    default Integer textToType(String text) {
        return UserType.getCodeByText(text);
    }

    /**
     * 将Integer类型的status转换为String类型的文本
     * 用于导出Excel时显示中文
     *
     * @param status 用户状态的数字编码
     * @return 用户状态的中文文本
     */
    @Named("statusToText")
    default String statusToText(Integer status) {
        return UserStatus.getTextByCode(status);
    }

    /**
     * 将String类型的文本转换为Integer类型的status
     * 用于导入Excel时解析中文
     *
     * @param text 用户状态的中文文本
     * @return 用户状态的数字编码
     */
    @Named("textToStatus")
    default Integer textToStatus(String text) {
        return UserStatus.getCodeByText(text);
    }

    /**
     * 将Integer类型的gender转换为String类型的文本
     * 用于导出Excel时显示中文
     *
     * @param gender 用户性别的数字编码
     * @return 用户性别的中文文本
     */
    @Named("genderToText")
    default String genderToText(Integer gender) {
        return UserGender.getTextByCode(gender);
    }

    /**
     * 将String类型的文本转换为Integer类型的gender
     * 用于导入Excel时解析中文
     *
     * @param text 用户性别的中文文本
     * @return 用户性别的数字编码
     */
    @Named("textToGender")
    default Integer textToGender(String text) {
        return UserGender.getCodeByText(text);
    }

}

