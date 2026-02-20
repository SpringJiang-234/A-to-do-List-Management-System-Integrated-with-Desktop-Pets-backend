package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.FeedbackDetails;
import com.backend.domain.dto.FeedbackDTO;
import com.backend.domain.entity.Feedback;
import com.backend.domain.excel.FeedbackExcel;
import com.backend.domain.vo.FeedbackVO;
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
public class FeedbackConverterImpl implements FeedbackConverter {

    @Override
    public Feedback feedbackDTO2feedback(FeedbackDTO feedbackDTO) {
        if ( feedbackDTO == null ) {
            return null;
        }

        Feedback feedback = new Feedback();

        feedback.setId( feedbackDTO.getId() );
        feedback.setUserId( feedbackDTO.getUserId() );
        feedback.setTitle( feedbackDTO.getTitle() );
        feedback.setContent( feedbackDTO.getContent() );
        feedback.setStatus( feedbackDTO.getStatus() );
        feedback.setReplyContent( feedbackDTO.getReplyContent() );

        return feedback;
    }

    @Override
    public FeedbackDetails feedback2feedbackDetails(Feedback feedback) {
        if ( feedback == null ) {
            return null;
        }

        FeedbackDetails feedbackDetails = new FeedbackDetails();

        feedbackDetails.setId( feedback.getId() );
        feedbackDetails.setCreateTime( feedback.getCreateTime() );
        feedbackDetails.setUpdateTime( feedback.getUpdateTime() );
        feedbackDetails.setUserId( feedback.getUserId() );
        feedbackDetails.setTitle( feedback.getTitle() );
        feedbackDetails.setContent( feedback.getContent() );
        if ( feedback.getStatus() != null ) {
            feedbackDetails.setStatus( String.valueOf( feedback.getStatus() ) );
        }
        feedbackDetails.setReplyContent( feedback.getReplyContent() );

        return feedbackDetails;
    }

    @Override
    public PageBean<FeedbackVO> feedbackPageBean2feedbackVOPageBean(PageBean<Feedback> feedbackPageBean) {
        if ( feedbackPageBean == null ) {
            return null;
        }

        PageBean<FeedbackVO> pageBean = new PageBean<FeedbackVO>();

        pageBean.setPageNum( feedbackPageBean.getPageNum() );
        pageBean.setPageSize( feedbackPageBean.getPageSize() );
        pageBean.setPages( feedbackPageBean.getPages() );
        pageBean.setRecords( feedbackListToFeedbackVOList( feedbackPageBean.getRecords() ) );

        return pageBean;
    }

    @Override
    public FeedbackExcel feedback2feedbackExcel(Feedback feedback) {
        if ( feedback == null ) {
            return null;
        }

        FeedbackExcel feedbackExcel = new FeedbackExcel();

        feedbackExcel.setId( feedback.getId() );
        feedbackExcel.setCreateTime( feedback.getCreateTime() );
        feedbackExcel.setUpdateTime( feedback.getUpdateTime() );
        feedbackExcel.setUserId( feedback.getUserId() );
        feedbackExcel.setTitle( feedback.getTitle() );
        feedbackExcel.setContent( feedback.getContent() );
        if ( feedback.getStatus() != null ) {
            feedbackExcel.setStatus( String.valueOf( feedback.getStatus() ) );
        }
        feedbackExcel.setReplyContent( feedback.getReplyContent() );

        return feedbackExcel;
    }

    @Override
    public List<FeedbackExcel> feedbackList2feedbackExcelList(List<Feedback> feedbackList) {
        if ( feedbackList == null ) {
            return null;
        }

        List<FeedbackExcel> list = new ArrayList<FeedbackExcel>( feedbackList.size() );
        for ( Feedback feedback : feedbackList ) {
            list.add( feedback2feedbackExcel( feedback ) );
        }

        return list;
    }

    @Override
    public Feedback feedbackExcel2feedback(FeedbackExcel feedbackExcel) {
        if ( feedbackExcel == null ) {
            return null;
        }

        Feedback feedback = new Feedback();

        feedback.setId( feedbackExcel.getId() );
        feedback.setCreateTime( feedbackExcel.getCreateTime() );
        feedback.setUpdateTime( feedbackExcel.getUpdateTime() );
        feedback.setUserId( feedbackExcel.getUserId() );
        feedback.setTitle( feedbackExcel.getTitle() );
        feedback.setContent( feedbackExcel.getContent() );
        if ( feedbackExcel.getStatus() != null ) {
            feedback.setStatus( Integer.parseInt( feedbackExcel.getStatus() ) );
        }
        feedback.setReplyContent( feedbackExcel.getReplyContent() );

        return feedback;
    }

    @Override
    public List<Feedback> feedbackExcelList2feedbackList(List<FeedbackExcel> feedbackExcelList) {
        if ( feedbackExcelList == null ) {
            return null;
        }

        List<Feedback> list = new ArrayList<Feedback>( feedbackExcelList.size() );
        for ( FeedbackExcel feedbackExcel : feedbackExcelList ) {
            list.add( feedbackExcel2feedback( feedbackExcel ) );
        }

        return list;
    }

    protected FeedbackVO feedbackToFeedbackVO(Feedback feedback) {
        if ( feedback == null ) {
            return null;
        }

        FeedbackVO feedbackVO = new FeedbackVO();

        feedbackVO.setId( feedback.getId() );
        feedbackVO.setUserId( feedback.getUserId() );
        feedbackVO.setTitle( feedback.getTitle() );
        feedbackVO.setContent( feedback.getContent() );
        if ( feedback.getStatus() != null ) {
            feedbackVO.setStatus( String.valueOf( feedback.getStatus() ) );
        }

        return feedbackVO;
    }

    protected List<FeedbackVO> feedbackListToFeedbackVOList(List<Feedback> list) {
        if ( list == null ) {
            return null;
        }

        List<FeedbackVO> list1 = new ArrayList<FeedbackVO>( list.size() );
        for ( Feedback feedback : list ) {
            list1.add( feedbackToFeedbackVO( feedback ) );
        }

        return list1;
    }
}
