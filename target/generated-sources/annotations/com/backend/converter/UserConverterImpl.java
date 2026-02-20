package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.UserDetails;
import com.backend.domain.dto.UserDTO;
import com.backend.domain.entity.User;
import com.backend.domain.excel.UserExcel;
import com.backend.domain.vo.UserVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-19T21:47:09+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public User userDTO2user(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setAccount( userDTO.getAccount() );
        user.setPasswordHash( userDTO.getPasswordHash() );
        user.setNickname( userDTO.getNickname() );
        user.setAvatar( userDTO.getAvatar() );
        user.setGender( userDTO.getGender() );
        user.setBirth( userDTO.getBirth() );
        user.setStatus( userDTO.getStatus() );

        return user;
    }

    @Override
    public UserDetails user2userDetails(User user) {
        if ( user == null ) {
            return null;
        }

        UserDetails userDetails = new UserDetails();

        userDetails.setId( user.getId() );
        userDetails.setCreateTime( user.getCreateTime() );
        userDetails.setUpdateTime( user.getUpdateTime() );
        userDetails.setAccount( user.getAccount() );
        userDetails.setPasswordHash( user.getPasswordHash() );
        userDetails.setNickname( user.getNickname() );
        userDetails.setAvatar( user.getAvatar() );
        if ( user.getGender() != null ) {
            userDetails.setGender( String.valueOf( user.getGender() ) );
        }
        userDetails.setBirth( user.getBirth() );
        if ( user.getStatus() != null ) {
            userDetails.setStatus( String.valueOf( user.getStatus() ) );
        }

        return userDetails;
    }

    @Override
    public PageBean<UserVO> userPageBean2userVOPageBean(PageBean<User> userPageBean) {
        if ( userPageBean == null ) {
            return null;
        }

        PageBean<UserVO> pageBean = new PageBean<UserVO>();

        pageBean.setPageNum( userPageBean.getPageNum() );
        pageBean.setPageSize( userPageBean.getPageSize() );
        pageBean.setPages( userPageBean.getPages() );
        pageBean.setRecords( userListToUserVOList( userPageBean.getRecords() ) );

        return pageBean;
    }

    @Override
    public UserExcel user2userExcel(User user) {
        if ( user == null ) {
            return null;
        }

        UserExcel userExcel = new UserExcel();

        userExcel.setId( user.getId() );
        userExcel.setCreateTime( user.getCreateTime() );
        userExcel.setUpdateTime( user.getUpdateTime() );
        userExcel.setAccount( user.getAccount() );
        userExcel.setPasswordHash( user.getPasswordHash() );
        userExcel.setNickname( user.getNickname() );
        userExcel.setAvatar( user.getAvatar() );
        if ( user.getGender() != null ) {
            userExcel.setGender( String.valueOf( user.getGender() ) );
        }
        userExcel.setBirth( user.getBirth() );
        if ( user.getStatus() != null ) {
            userExcel.setStatus( String.valueOf( user.getStatus() ) );
        }

        return userExcel;
    }

    @Override
    public List<UserExcel> userList2userExcelList(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserExcel> list = new ArrayList<UserExcel>( userList.size() );
        for ( User user : userList ) {
            list.add( user2userExcel( user ) );
        }

        return list;
    }

    @Override
    public User userExcel2user(UserExcel userExcel) {
        if ( userExcel == null ) {
            return null;
        }

        User user = new User();

        user.setId( userExcel.getId() );
        user.setCreateTime( userExcel.getCreateTime() );
        user.setUpdateTime( userExcel.getUpdateTime() );
        user.setAccount( userExcel.getAccount() );
        user.setPasswordHash( userExcel.getPasswordHash() );
        user.setNickname( userExcel.getNickname() );
        user.setAvatar( userExcel.getAvatar() );
        if ( userExcel.getGender() != null ) {
            user.setGender( Integer.parseInt( userExcel.getGender() ) );
        }
        user.setBirth( userExcel.getBirth() );
        if ( userExcel.getStatus() != null ) {
            user.setStatus( Integer.parseInt( userExcel.getStatus() ) );
        }

        return user;
    }

    @Override
    public List<User> userExcelList2userList(List<UserExcel> userExcelList) {
        if ( userExcelList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userExcelList.size() );
        for ( UserExcel userExcel : userExcelList ) {
            list.add( userExcel2user( userExcel ) );
        }

        return list;
    }

    protected UserVO userToUserVO(User user) {
        if ( user == null ) {
            return null;
        }

        UserVO userVO = new UserVO();

        userVO.setId( user.getId() );
        userVO.setAccount( user.getAccount() );
        userVO.setPasswordHash( user.getPasswordHash() );
        userVO.setNickname( user.getNickname() );
        userVO.setAvatar( user.getAvatar() );
        if ( user.getGender() != null ) {
            userVO.setGender( String.valueOf( user.getGender() ) );
        }
        userVO.setBirth( user.getBirth() );
        if ( user.getStatus() != null ) {
            userVO.setStatus( String.valueOf( user.getStatus() ) );
        }

        return userVO;
    }

    protected List<UserVO> userListToUserVOList(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<UserVO> list1 = new ArrayList<UserVO>( list.size() );
        for ( User user : list ) {
            list1.add( userToUserVO( user ) );
        }

        return list1;
    }
}
