package com.inno.portpolio.question.service;

import java.util.List;

import com.inno.portpolio.common.enumpkg.ServiceResult;
import com.inno.portpolio.paging.vo.PageVO;
import com.inno.portpolio.paging.vo.PaginationInfo;
import com.inno.portpolio.question.vo.QuestionVO;

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

public interface QuestionService {
	
	/**
	 * Qna 질문사항 리스트
	 * 페이징 처리
	 * @return
	 */
	public List<QuestionVO> retrieveQuestionList(PageVO pageVO);
	
	/**
	 * 총 레코드수 연산
	 * @param paging
	 * @return
	 */
	public int retrieveQuestionTotalRecord(PageVO pageVO);
	
	/**
	 * 다른 버전 - Qna 질문사항 리스트
	 * 페이징 처리
	 * @return
	 */
	public PaginationInfo<QuestionVO> retrieveDifferentQuestionList(PaginationInfo<QuestionVO> paging);
	
	/**
	 * 질문사항 등록
	 * @param question
	 * @return
	 */
	public ServiceResult createQuestion(QuestionVO question);
	
	/**
	 * 질문사항 수정
	 * @param question
	 * @return
	 */
	public ServiceResult modifyQuestion(QuestionVO question);
	
	/**
	 * 질문사항 삭제
	 * @param question
	 * @return
	 */
	public ServiceResult removeQuestion(QuestionVO question);
	
}
