package starrail.review.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import starrail.review.domain.FileVO;
import starrail.review.domain.Hash_SearchVO;
import starrail.review.domain.ReviewVO;
import starrail.review.domain.ReviewCriteria;
import starrail.review.domain.ReviewSearchCriteria;
import starrail.review.persistence.ReviewDao;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Inject
	private ReviewDao dao;

	@Transactional
	@Override
	public void register(ReviewVO review) throws Exception {

		int no = dao.selectR_no();
		if (no != 0) {
			review.setR_no(dao.selectR_no() + 1);
		}

		dao.insertReview(review);

		String[] files = review.getFiles();
		int r_no = dao.getR_no();
		System.out.println("num: " + r_no + " files : " + files);
		FileVO fileVO = new FileVO();
		fileVO.setR_no(r_no);

		if (files == null) {
			return;
		}

		for (String fileName : files) {
			fileVO.setRf_fullname(fileName);
			dao.addAttach(fileVO);
		}
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public ReviewVO read(Integer r_no) throws Exception {
		dao.updateR_hit(r_no);
		return dao.selectReview(r_no);
	}

	@Transactional
	@Override
	public void modify(ReviewVO review) throws Exception {
		System.out.println("ì§±ë˜˜  : " + review.toString());
		dao.updateReview(review);

		Integer r_no = review.getR_no();
		dao.deleteAttach(r_no);

		String[] files = review.getFiles();
		if (files == null) {
			return;
		}
		for (String fileName : files) {
			dao.repalceAttach(fileName, r_no);
		}
	}

	@Transactional
	@Override
	public void remove(Integer r_no) throws Exception {
		dao.deleteAttach(r_no);
		dao.deleteReview(r_no);
	}

	@Override
	public List<ReviewVO> list() throws Exception {
		return dao.listReview();
	}

	@Override
	public List<ReviewVO> listCriteria(ReviewCriteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(ReviewCriteria cri) throws Exception {
		return dao.countPaging(cri);
	}

	@Override
	public List<ReviewVO> listSearchCriteria(ReviewSearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(ReviewSearchCriteria cri) throws Exception {
		return dao.listSearchCount(cri);
	}

	@Override
	public List<String> getAttach(Integer r_no) throws Exception {
		return dao.getAttach(r_no);
	}

	@Override
	public int hash_no() throws Exception {
		if (dao.hash_no() == null) {
			return 0;
		} else
			return dao.hash_no();
	}

	@Override
	public void tagAdd(Integer h_no, Integer r_no, String r_hash) throws Exception {
		dao.tagAdd(h_no, r_no, r_hash);
	}

	@Override
	public int selectR_no() throws Exception {
		if (dao.selectR_no() == null) {
			return 0;
		} else {
			return dao.selectR_no();
		}
	}

	@Override
	public void hashtagInsert(ReviewVO review) throws Exception {
		Pattern p = Pattern.compile("\\#([0-9a-zA-Z°¡-ÆR]*)");
		Matcher m = p.matcher(review.getR_content());

		int h_no = 0;
		String r_hash = null;
		while (m.find()) {
			r_hash = specialCharacter_replace(m.group());

			if (r_hash != null) {
				h_no = dao.hash_no() + 1;
				dao.tagAdd(h_no, review.getR_no(), r_hash);
			}
		}

	}

	@Override
	public String specialCharacter_replace(String str) throws Exception {
		str = StringUtils.replace(str, "-_+=!@#$%^&*()[]{}|\\;:'\"<>,.?/~`£© ", "");

		if (str.length() < 1) {
			return null;
		}
		return str;
	}

	@Override
	public List<String> hashSearch() throws Exception {

		return dao.HashSearch();
	}






}
