package org.sab.sampleqa.web.controller.qa;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sab.sampleqa.common.util.ui.PaginationInfo;
import org.sab.sampleqa.data.model.qa.Tag;
import org.sab.sampleqa.service.qa.QAService;
import org.sab.sampleqa.service.qa.TagService;
import org.sab.sampleqa.web.model.qa.QuestionUI;
import org.sab.sampleqa.web.model.qa.TagUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tags")
public class TagController {

	@Autowired
	private QAService qaService;

	@Autowired
	private TagService tagService;

	@RequestMapping(value = "/list")
	public String list(@RequestParam(required = false) String page,
			@RequestParam(required = false) String pageSize, Model model) {

		int pageLimit = pageSize != null ? Integer.parseInt(pageSize) : 20;
		int currentPage = page != null ? Integer.parseInt(page) : 1;
		String listAction = "tags/list";

		Pageable pageable = new PageRequest(currentPage - 1, pageLimit);
		Page<TagUI> tags = tagService.findAll(pageable);
		PaginationInfo pageInfo = new PaginationInfo(currentPage,
				tags.getTotalElements(), pageLimit, listAction);

		model.addAttribute("tags", tags.getContent());
		model.addAttribute("pageInfo", pageInfo);

		return listAction;
	}

	@RequestMapping(value = "/view")
	public String view(@RequestParam(required = false) String id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String page,
			@RequestParam(required = false) String pageSize, Model model,
			Principal principal) throws Exception {

		int pageLimit = pageSize != null ? Integer.parseInt(pageSize) : 20;
		int currentPage = page != null ? Integer.parseInt(page) : 1;
		String listAction = "tags/view";
		Map<String, String> listActionParams = new HashMap<String, String>();
		Tag tag = null;

		if (id != null || name != null) {
			if (id != null) {
				tag = tagService.getQuestionsSliceByTagId(id, currentPage,
						pageLimit);
				listActionParams.put("id", id);
			} else if (name != null) {
				tag = tagService.getQuestionsSliceByTagName(name, currentPage,
						pageLimit);
				listActionParams.put("name", name);
			}

			if (tag != null) {
				List<QuestionUI> questions = qaService.findById(tag
						.getQuestions());

				PaginationInfo pageInfo = new PaginationInfo(currentPage,
						tag.getQuestionCount(), pageLimit, listAction,
						listActionParams);

				model.addAttribute("questions", questions);
				model.addAttribute("pageInfo", pageInfo);
			}
		}

		return "question/list";
	}
}
