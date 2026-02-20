package com.backend.converter;

import com.backend.bean.PageBean;
import com.backend.domain.details.FeedbackDetails;
import com.backend.domain.dto.FeedbackDTO;
import com.backend.domain.entity.Feedback;
import com.backend.domain.excel.FeedbackExcel;
import com.backend.domain.vo.FeedbackVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackConverter {

    Feedback feedbackDTO2feedback(FeedbackDTO feedbackDTO);

    /**
     * 将Feedback对象转换为FeedbackDetails对象
     * 用于转换反馈基本信息
     *
     * @param feedback 源Feedback对象，包含反馈基本信息
     * @return FeedbackDetails对象，包含反馈详细信息
     */
    FeedbackDetails feedback2feedbackDetails(Feedback feedback);

    /**
     * 将PageBean<Feedback>分页对象转换为PageBean<FeedbackVO>分页对象
     * 用于分页数据的类型转换，保持分页结构不变
     *
     * @param feedbackPageBean 源Feedback分页对象，包含反馈信息的分页数据
     * @return FeedbackVO分页对象，包含转换后的反馈VO对象的分页数据
     */
    PageBean<FeedbackVO> feedbackPageBean2feedbackVOPageBean(PageBean<Feedback> feedbackPageBean);

    /**
     * 将Feedback对象转换为FeedbackExcel对象
     * 用于Excel导出反馈信息
     *
     * @param feedback 源Feedback对象，包含反馈基本信息
     * @return FeedbackExcel对象，包含反馈Excel信息
     */
    FeedbackExcel feedback2feedbackExcel(Feedback feedback);

    /**
     * 将Feedback列表转换为FeedbackExcel列表
     * 批量转换反馈信息用于Excel导出
     *
     * @param feedbackList 源Feedback对象列表
     * @return FeedbackExcel对象列表，用于Excel导出
     */
    List<FeedbackExcel> feedbackList2feedbackExcelList(List<Feedback> feedbackList);

    /**
     * 将FeedbackExcel对象转换为Feedback对象
     * 用于从Excel导入反馈信息
     *
     * @param feedbackExcel 源FeedbackExcel对象，包含从Excel导入的反馈信息
     * @return Feedback对象，包含转换后的反馈基本信息
     */
    Feedback feedbackExcel2feedback(FeedbackExcel feedbackExcel);

    /**
     * 将FeedbackExcel列表转换为Feedback列表
     * 批量转换从Excel导入的反馈信息
     *
     * @param feedbackExcelList 源FeedbackExcel对象列表，包含从Excel导入的反馈信息
     * @return Feedback对象列表，包含转换后的反馈基本信息
     */
    List<Feedback> feedbackExcelList2feedbackList(List<FeedbackExcel> feedbackExcelList);

}

