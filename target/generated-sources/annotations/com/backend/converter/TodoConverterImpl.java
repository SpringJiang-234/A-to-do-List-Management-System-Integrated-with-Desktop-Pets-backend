package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.TodoDetails;
import com.backend.domain.dto.TodoDTO;
import com.backend.domain.entity.Todo;
import com.backend.domain.excel.TodoExcel;
import com.backend.domain.vo.TodoVO;
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
public class TodoConverterImpl implements TodoConverter {

    @Override
    public Todo todoDTO2todo(TodoDTO todoDTO) {
        if ( todoDTO == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setId( todoDTO.getId() );
        todo.setUserId( todoDTO.getUserId() );
        todo.setTitle( todoDTO.getTitle() );
        todo.setContent( todoDTO.getContent() );
        todo.setCategoryId( todoDTO.getCategoryId() );
        todo.setPriority( todoDTO.getPriority() );
        todo.setStartTime( todoDTO.getStartTime() );
        todo.setEndTime( todoDTO.getEndTime() );
        todo.setStatus( todoDTO.getStatus() );
        todo.setFinishTime( todoDTO.getFinishTime() );
        todo.setIsDelete( todoDTO.getIsDelete() );
        todo.setIsTop( todoDTO.getIsTop() );

        return todo;
    }

    @Override
    public TodoDetails todo2todoDetails(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        TodoDetails todoDetails = new TodoDetails();

        todoDetails.setId( todo.getId() );
        todoDetails.setCreateTime( todo.getCreateTime() );
        todoDetails.setUpdateTime( todo.getUpdateTime() );
        todoDetails.setUserId( todo.getUserId() );
        todoDetails.setTitle( todo.getTitle() );
        todoDetails.setContent( todo.getContent() );
        todoDetails.setCategoryId( todo.getCategoryId() );
        if ( todo.getPriority() != null ) {
            todoDetails.setPriority( String.valueOf( todo.getPriority() ) );
        }
        todoDetails.setStartTime( todo.getStartTime() );
        todoDetails.setEndTime( todo.getEndTime() );
        if ( todo.getStatus() != null ) {
            todoDetails.setStatus( String.valueOf( todo.getStatus() ) );
        }
        todoDetails.setFinishTime( todo.getFinishTime() );
        if ( todo.getIsDelete() != null ) {
            todoDetails.setIsDelete( String.valueOf( todo.getIsDelete() ) );
        }
        if ( todo.getIsTop() != null ) {
            todoDetails.setIsTop( String.valueOf( todo.getIsTop() ) );
        }

        return todoDetails;
    }

    @Override
    public PageBean<TodoVO> todoPageBean2todoVOPageBean(PageBean<Todo> todoPageBean) {
        if ( todoPageBean == null ) {
            return null;
        }

        PageBean<TodoVO> pageBean = new PageBean<TodoVO>();

        pageBean.setPageNum( todoPageBean.getPageNum() );
        pageBean.setPageSize( todoPageBean.getPageSize() );
        pageBean.setPages( todoPageBean.getPages() );
        pageBean.setRecords( todoListToTodoVOList( todoPageBean.getRecords() ) );

        return pageBean;
    }

    @Override
    public TodoExcel todo2todoExcel(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        TodoExcel todoExcel = new TodoExcel();

        todoExcel.setId( todo.getId() );
        todoExcel.setCreateTime( todo.getCreateTime() );
        todoExcel.setUpdateTime( todo.getUpdateTime() );
        todoExcel.setUserId( todo.getUserId() );
        todoExcel.setTitle( todo.getTitle() );
        todoExcel.setContent( todo.getContent() );
        todoExcel.setCategoryId( todo.getCategoryId() );
        if ( todo.getPriority() != null ) {
            todoExcel.setPriority( String.valueOf( todo.getPriority() ) );
        }
        todoExcel.setStartTime( todo.getStartTime() );
        todoExcel.setEndTime( todo.getEndTime() );
        if ( todo.getStatus() != null ) {
            todoExcel.setStatus( String.valueOf( todo.getStatus() ) );
        }
        todoExcel.setFinishTime( todo.getFinishTime() );
        if ( todo.getIsDelete() != null ) {
            todoExcel.setIsDelete( String.valueOf( todo.getIsDelete() ) );
        }
        if ( todo.getIsTop() != null ) {
            todoExcel.setIsTop( String.valueOf( todo.getIsTop() ) );
        }

        return todoExcel;
    }

    @Override
    public List<TodoExcel> todoList2todoExcelList(List<Todo> todoList) {
        if ( todoList == null ) {
            return null;
        }

        List<TodoExcel> list = new ArrayList<TodoExcel>( todoList.size() );
        for ( Todo todo : todoList ) {
            list.add( todo2todoExcel( todo ) );
        }

        return list;
    }

    @Override
    public Todo todoExcel2todo(TodoExcel todoExcel) {
        if ( todoExcel == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setId( todoExcel.getId() );
        todo.setCreateTime( todoExcel.getCreateTime() );
        todo.setUpdateTime( todoExcel.getUpdateTime() );
        todo.setUserId( todoExcel.getUserId() );
        todo.setTitle( todoExcel.getTitle() );
        todo.setContent( todoExcel.getContent() );
        todo.setCategoryId( todoExcel.getCategoryId() );
        if ( todoExcel.getPriority() != null ) {
            todo.setPriority( Integer.parseInt( todoExcel.getPriority() ) );
        }
        todo.setStartTime( todoExcel.getStartTime() );
        todo.setEndTime( todoExcel.getEndTime() );
        if ( todoExcel.getStatus() != null ) {
            todo.setStatus( Integer.parseInt( todoExcel.getStatus() ) );
        }
        todo.setFinishTime( todoExcel.getFinishTime() );
        if ( todoExcel.getIsDelete() != null ) {
            todo.setIsDelete( Integer.parseInt( todoExcel.getIsDelete() ) );
        }
        if ( todoExcel.getIsTop() != null ) {
            todo.setIsTop( Integer.parseInt( todoExcel.getIsTop() ) );
        }

        return todo;
    }

    @Override
    public List<Todo> todoExcelList2todoList(List<TodoExcel> todoExcelList) {
        if ( todoExcelList == null ) {
            return null;
        }

        List<Todo> list = new ArrayList<Todo>( todoExcelList.size() );
        for ( TodoExcel todoExcel : todoExcelList ) {
            list.add( todoExcel2todo( todoExcel ) );
        }

        return list;
    }

    protected TodoVO todoToTodoVO(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        TodoVO todoVO = new TodoVO();

        todoVO.setId( todo.getId() );
        todoVO.setUserId( todo.getUserId() );
        todoVO.setStartTime( todo.getStartTime() );
        todoVO.setEndTime( todo.getEndTime() );
        if ( todo.getStatus() != null ) {
            todoVO.setStatus( String.valueOf( todo.getStatus() ) );
        }

        return todoVO;
    }

    protected List<TodoVO> todoListToTodoVOList(List<Todo> list) {
        if ( list == null ) {
            return null;
        }

        List<TodoVO> list1 = new ArrayList<TodoVO>( list.size() );
        for ( Todo todo : list ) {
            list1.add( todoToTodoVO( todo ) );
        }

        return list1;
    }
}
