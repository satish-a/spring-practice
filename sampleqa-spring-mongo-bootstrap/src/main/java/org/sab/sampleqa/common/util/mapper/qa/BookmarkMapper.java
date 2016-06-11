package org.sab.sampleqa.common.util.mapper.qa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sab.sampleqa.data.model.qa.Bookmark;
import org.sab.sampleqa.web.model.qa.BookmarkUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class BookmarkMapper {

	public BookmarkUI toUIBean(Bookmark pBean) {
		BookmarkUI uiBean = null;

		if (pBean != null) {
			uiBean = new BookmarkUI();

			uiBean.setCreatedDate(pBean.getCreatedDate());
			uiBean.setId(pBean.getId());
			uiBean.setUser(pBean.getUser());
			uiBean.setQuestionId(pBean.getQuestionId());
		}

		return uiBean;
	}

	public List<BookmarkUI> toUIBean(List<Bookmark> bookmarks) {
		List<BookmarkUI> uiBeans = new ArrayList<BookmarkUI>();

		for (Bookmark bookmark : bookmarks) {
			uiBeans.add(toUIBean(bookmark));
		}
		return uiBeans;
	}

	public List<BookmarkUI> toUIBean(Map<String, Bookmark> bookmarks) {
		List<BookmarkUI> uiBeans = new ArrayList<BookmarkUI>();

		for (String key : bookmarks.keySet()) {
			uiBeans.add(toUIBean(bookmarks.get(key)));
		}

		return uiBeans;
	}

	public Page<BookmarkUI> toUIBean(Page<Bookmark> bookmarks, Pageable pageable) {
		Page<BookmarkUI> uiBeans = new PageImpl<BookmarkUI>(
				toUIBean(bookmarks.getContent()), pageable,
				bookmarks.getTotalElements());

		return uiBeans;
	}

	public Bookmark toPersistenceBean(BookmarkUI uiBean) {
		Bookmark bookmark = null;

		if (uiBean != null) {
			bookmark = new Bookmark();

			bookmark.setCreatedDate(uiBean.getCreatedDate());
			bookmark.setId(uiBean.getId());
			bookmark.setUser(uiBean.getUser());
			bookmark.setQuestionId(uiBean.getQuestionId());
		}
		return bookmark;
	}

	public List<Bookmark> toPersistenceBean(List<BookmarkUI> uiBookmarks) {
		List<Bookmark> bookmarks = new ArrayList<Bookmark>();

		for (BookmarkUI uiBean : uiBookmarks) {
			bookmarks.add(toPersistenceBean(uiBean));
		}
		return bookmarks;
	}

	public Map<String, Bookmark> toPersistenceBeanMap(
			List<BookmarkUI> uiBookmarks) {
		Map<String, Bookmark> bookmarks = new HashMap<String, Bookmark>();

		for (BookmarkUI uiBean : uiBookmarks) {
			bookmarks.put(uiBean.getId(), toPersistenceBean(uiBean));
		}

		return bookmarks;
	}
}
