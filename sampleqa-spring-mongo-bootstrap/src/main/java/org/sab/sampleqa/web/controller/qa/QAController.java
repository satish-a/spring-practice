package org.sab.sampleqa.web.controller.qa;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.sab.sampleqa.common.util.ui.PaginationInfo;
import org.sab.sampleqa.service.qa.QAService;
import org.sab.sampleqa.web.model.qa.AnswerUI;
import org.sab.sampleqa.web.model.qa.CommentUI;
import org.sab.sampleqa.web.model.qa.QuestionUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/question")
public class QAController {

	@Autowired
	QAService qaService;

	private Logger logger = Logger.getLogger(QAController.class);

	@RequestMapping
	public String getStartPage() {
		return "question/list";
	}

	@RequestMapping(value = "/list")
	public String list(@RequestParam(required = false) String page,
			@RequestParam(required = false) String pageSize, Model model) {

		int pageLimit = pageSize != null ? Integer.parseInt(pageSize) : 20;
		int currentPage = page != null ? Integer.parseInt(page) : 1;
		String listAction = "question/list";

		Pageable pageable = new PageRequest(currentPage - 1, pageLimit);
		Page<QuestionUI> questions = qaService.findAll(pageable);
		PaginationInfo pageInfo = new PaginationInfo(currentPage,
				questions.getTotalElements(), pageLimit, listAction);

		model.addAttribute("questions", questions.getContent());
		model.addAttribute("pageInfo", pageInfo);

		logger.debug("Pageable :: total: " + questions.getTotalElements()
				+ " questions: " + questions.getContent());

		return listAction;
	}

	@RequestMapping(value = "/view/{id}")
	public String view(@PathVariable String id, Model model, Principal principal)
			throws Exception {

		QuestionUI question = qaService.findById(id, principal);
		model.addAttribute("question", question);

		logger.debug(question);
		return "question/view";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() throws Exception {
		return "question/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(QuestionUI data, Model model, Principal principal) {
		String resultPage = "question/list";

		if (qaService.findBySubject(data.getSubject()) == null) {
			QuestionUI savedQuestion = qaService.create(data, principal);
			if (savedQuestion != null) {
				logger.debug("Question Created Succesfully");
				resultPage = "redirect:list";
			} else {
				logger.debug("Error trying to create account.");
				resultPage = "add";
			}
		} else {
			logger.debug("Question already exists");
		}

		return resultPage;
	}

	@RequestMapping(value = "/answer/add", method = RequestMethod.POST)
	public String addAnswer(AnswerUI data, Model model, Principal principal) {
		String resultPage = "question/view";

		QuestionUI question = qaService.addAnswer(data, principal);
		model.addAttribute("question", question);

		return resultPage;
	}

	@RequestMapping(value = "/comment/add", method = RequestMethod.POST)
	public String addQuestionComment(CommentUI data, Model model,
			Principal principal) {
		String resultPage = "question/view";

		QuestionUI question = qaService.addQuestionComment(data, principal);
		model.addAttribute("question", question);

		return resultPage;
	}

	@RequestMapping(value = "/answer/comment/add", method = RequestMethod.POST)
	public String addAnswerComment(CommentUI data, Model model,
			Principal principal) {
		String resultPage = "question/view";

		QuestionUI question = qaService.addAnswerComment(data, principal);
		model.addAttribute("question", question);

		return resultPage;
	}

	@RequestMapping(value = "/vote")
	public String addVote(Model model, String questionId, Principal principal) {
		String resultPage = "question/view";

		QuestionUI question = qaService.addVote(questionId, principal);
		model.addAttribute("question", question);

		return resultPage;
	}

	@RequestMapping(value = "/bookmark")
	public String addBookmark(Model model, String questionId,
			Principal principal) {
		String resultPage = "question/view";

		QuestionUI question = qaService.addBookmark(questionId, principal);
		model.addAttribute("question", question);

		return resultPage;
	}
}