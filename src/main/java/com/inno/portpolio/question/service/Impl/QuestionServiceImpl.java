package com.inno.portpolio.question.service.Impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.inno.portpolio.common.enumpkg.ServiceResult;
import com.inno.portpolio.common.file.service.AttachmentFileService;
import com.inno.portpolio.common.file.vo.AttachmentFileVO;
import com.inno.portpolio.paging.vo.PageVO;
import com.inno.portpolio.paging.vo.PaginationInfo;
import com.inno.portpolio.question.mapper.QuestionMapper;
import com.inno.portpolio.question.service.QuestionService;
import com.inno.portpolio.question.vo.QuestionVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
* @author 연구개발 5팀 사원 김재성
* @since 2024. 03. 11.
* @version 1.0
* @see javax.servlet.http.HttpServlet 
* <pre>
* [[개정이력(Modification Information)]]
*    수정일            수정자               수정내용
* ------------     --------    ----------------------
* 2024.03.11.        김재성       최초작성
* Copyright (c) 2024 by INNOVATION-T All right reserved
* </pre>
*/
@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {
	
	private final QuestionMapper questionMapper;
	
	private final AttachmentFileService attachmentFileService;
	
	private File saveFolder;
	
	@Value("file.upload.dir")
	private String saveFolderPath;
	
	// 질문사항 리스트 페이징 처리
	@Override
	public List<QuestionVO> retrieveQuestionList(PageVO pageVO) {
		
		log.info("질문사항 페이징 데이터 체크 : {}", pageVO);
		
		int totalRecord = questionMapper.selectQuestionTotalRecord(pageVO);
		
		pageVO.setCountPerPage(totalRecord);
		
		List<QuestionVO> dataList = questionMapper.selectQuestionList(pageVO);
		
		log.info("질문사항 페이징 DB에서 온 리스트 체크 : {}", dataList);
		
		return dataList;
		
	}
	
	@Override
	public PaginationInfo<QuestionVO> retrieveDifferentQuestionList(PaginationInfo<QuestionVO> paging) {
		
		log.info("질문사항 페이징 데이터 체크 : {}", paging);
		
		int totalRecord = questionMapper.selectDifferentQuestionTotalRecord(paging);
		
		paging.setTotalRecord(totalRecord);
		
		List<QuestionVO> dataList = questionMapper.selectDifferentQuestionList(paging);
		
		log.info("질문사항 페이징 DB에서 온 리스트 체크 : {}", dataList);
		
		paging.setDataList(dataList);
		
		return paging;
	}

	
	@Override
	public int retrieveQuestionTotalRecord(PageVO pageVO) {
		
		int countPerPage = questionMapper.selectQuestionTotalRecord(pageVO);
		
		return countPerPage;
	}

	@Override
	public ServiceResult createQuestion(QuestionVO question){
		
		ServiceResult res =null;
		
		List<AttachmentFileVO> attach = question.getAttachmentFile();
		
		for(AttachmentFileVO atch : attach) {
			try {
				
				log.info("QnA 게시판 등록 시 파일 첨부 개별 정보 : {}", atch);
				
				atch.setAtchmnflStrePath(saveFolderPath);
				attachmentFileService.createAttachmentFile(atch);
				atch.saveTo(saveFolder);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		question.setAtchmnflNo(question.getAtchmnflNo());
		
		int cnt = questionMapper.insertQuestion(question);
		if(cnt > 0) {
			res = ServiceResult.OK;
		}else {
			res = ServiceResult.FAIL;
		}
		return res;
	}

	@Override
	public ServiceResult modifyQuestion(QuestionVO question) {
		ServiceResult res =null;
		return res ;
	}

	@Override
	public ServiceResult removeQuestion(QuestionVO question) {
		ServiceResult res =null;
		return res ;
	}



	
	

}
