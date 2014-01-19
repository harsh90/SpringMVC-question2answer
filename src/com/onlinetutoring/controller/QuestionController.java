 package com.onlinetutoring.controller;
 
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.onlinetutoring.model.Answers;
import com.onlinetutoring.model.JsonResponse;
import com.onlinetutoring.model.QuestionCategory;
import com.onlinetutoring.model.Questions;
import com.onlinetutoring.model.Tags;
import com.onlinetutoring.model.User;
import com.onlinetutoring.model.ViewQuestion;
import com.onlinetutoring.service.AnswerService;
import com.onlinetutoring.service.QuestionsService;
import com.onlinetutoring.service.UserService;
import com.onlinetutoring.utills.AnswersDTO;
import com.onlinetutoring.utills.Criteria;
import com.onlinetutoring.utills.QuestionDTO;
import com.onlinetutoring.utills.Utill;
import com.sun.jmx.snmp.Timestamp;
 /**
 * 
 * QuestionController.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:50:43 AM
 */ 

@Controller
public class QuestionController {
	
	@Autowired
	private QuestionsService questionsService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private AnswerService answersService;
	 
	 /**
	 * This controller method will retrieve the get request and this will return Ask Question form
	 * 
	 * @param model
	 * @return
	 */ 
	@RequestMapping(value="/questionPost",method=RequestMethod.GET)
	public String  setUpQuestionForm(Model model){
		
		QuestionDTO question=new QuestionDTO();
		
		
	 	Map<Long, String> categoriesMap = getSelectListQuestionCategories();
		//JSONArray resQuestionCategories = getSelectListData();
		Date date= new Date();
		Timestamp timestamp= new Timestamp(date.getTime());
		System.out.println("Time stamp "+date.getTime());
		 
		model.addAttribute("username", Utill.getCurrentLoggedInUserName(SecurityContextHolder.getContext()));
		model.addAttribute("question",question);
		model.addAttribute("categories",categoriesMap);
		model.addAttribute("time",new Timestamp(date.getTime()));
		
		return "questionPost";
	}
	/**
	 * 
	 * @return
	 * Jan 13, 20142:27:28 PM
	 */
	private Map<Long, String> getSelectListQuestionCategories() {
		List<QuestionCategory>  categories =questionsService.getAllQuestionCategories();
		
		Map<Long , String> categoriesMap=new HashMap<>();
		
		for (QuestionCategory questionCategory : categories) {
			categoriesMap.put(questionCategory.getQuestionCategoryId(), questionCategory.getQuestionCategory());
		}
		return categoriesMap;
	}


	 /**
	 * This controllet method will post question
	 * 
	 * @param question
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/questionPost",method=RequestMethod.POST)
	public String questionPost(@ModelAttribute("question") QuestionDTO questionDTO,BindingResult result){
		
		
		System.out.println(questionDTO.getQuestion_title());
		
	/*	if(result.hasErrors()){
			return "questionPost";
		}else{*/ 
			Questions question=new Questions();
		
			List<User> users=userService.getUserByUsername(Utill.getCurrentLoggedInUserName(SecurityContextHolder.getContext()));
			
			User user=users.get(0);
			 
			question.setQuestionTitle(questionDTO.getQuestion_title());
			question.setQuestionDescription(questionDTO.getQuestion_description());
			question.setAuthorId(user.getUserId());
			question.setTimeCreated(new Date());
			question.setIsClosed(0);
			question.setScore((long)0);
			question.setViews((long)0);
			System.out.println("	question.getCategoryId() "+	questionDTO.getCategory_id());
			if(questionDTO.getCategory_id()!=null && !questionDTO.getCategory_id().isEmpty()){
				List<QuestionCategory> list=questionsService.findByQuestionCategoryId(Long.valueOf(questionDTO.getCategory_id()));
				if(list!=null){
					QuestionCategory newQuestionCategory=list.get(list.size()-1);
					question.setCategoryId(newQuestionCategory);
				}
			}
			ArrayList<Tags> arrayList=new ArrayList<>(); 
			
			String tagString=questionDTO.getTags();
			
			if(tagString!=null && !tagString.isEmpty()){
				String[] arrTags=StringUtils.split(tagString,",");
				for (String newTagName : arrTags) {
					 Tags tag=new Tags();
					 tag.setTag(newTagName);
					 arrayList.add(tag);
				 
				}
			}
			if(!tagString.contains(",")){
				if(!tagString.isEmpty()){
					Tags newTag=new Tags();
					newTag.setTag(tagString);
					arrayList.add(newTag);
					
				}
			}
			System.out.println(tagString);
			for (Tags tags : arrayList) {
				System.out.println("Sorted Tags "+tags.getTag() +"Tag ID "+tags.getTagId());
			}
			 
			question.setTagsCollection(arrayList);
			
			questionsService.postQuestion(question);
			
			return "advancedSearchQuestion";
		// }
	}
	/**
	 * 
	 * @param questionId
	 * @param model
	 * @param session
	 * @return
	 * Jan 13, 20142:27:35 PM
	 */
	@RequestMapping(value="/questionDelete",method=RequestMethod.GET)
	public ModelAndView deleteQuestion(@RequestParam(required=true,value="questionId") Long questionId,Model model,HttpSession session ){
		ModelAndView modelAndView = new ModelAndView(new RedirectView("/success",true));
	 
		Questions oldQuestion=null;
		String message="";
		if(questionId!=null){
		
			oldQuestion=new Questions();
			oldQuestion.setQuestionId(questionId);
			questionsService.deleteQuestion(oldQuestion);
			message="Question successfully deleted";
			
			modelAndView.addObject("message",message); 
			return modelAndView;
		}else{
			message="Question deletion failed..";
		}
		modelAndView.addObject("message",message); 
		return modelAndView;
		 
	}
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * Jan 13, 20142:27:39 PM
	 */
	@RequestMapping(value="/searchquestions",method=RequestMethod.GET)
	public String showAllQuestions(Model model,HttpServletRequest request,HttpSession session ){
		 
		List<Questions>  resultset=questionsService.getAllQuestions();
		 
		Questions question=resultset.get(0);
		 
		System.out.println(question.getCategoryId().getQuestionCategory());
		
		Collection<Tags> tags=question.getTagsCollection();
		 
		for (Tags tags2 : tags) {
			System.out.println("TAG NAME "+tags2.getTag());;
		}
		 
		model.addAttribute("username", Utill.getCurrentLoggedInUserName(SecurityContextHolder.getContext()));
		model.addAttribute("resSearchResults",resultset);
		//model.addAttribute("jsonObj",jsonObject);
		
		return "welcome";
	}
	/**
	 * 
	 * @param questionID
	 * @param model
	 * @return
	 * Jan 13, 20142:27:42 PM
	 */
	@RequestMapping(value="/updateQuestion",method=RequestMethod.GET)
	public String updateQuestionSetUpForm(@RequestParam(required=true,value="questionId")Long questionID,Model model){
		Questions question=(Questions)questionsService.getQuestionByQuestionId(questionID);
		QuestionDTO dto=null;
		if(question!=null){
			dto=new QuestionDTO();
			
			dto.setQuestion_id(question.getQuestionId().toString());
			dto.setQuestion_title(question.getQuestionTitle());
			dto.setQuestion_description(question.getQuestionDescription());
			
			List<Tags> tags=(List<Tags>) question.getTagsCollection();
			if(tags!=null && tags.size()>0){
				String tagsString="";
				 
				 for (Tags tags2 : tags) {
					 
					 tagsString+=tags2.getTag()+",";
				}
				dto.setTags(tagsString);
			}
			model.addAttribute("question",dto);
		 }
		
		return "editQuestion";
	}
	/**
	 * 
	 * @param question
	 * @param model
	 * @param questionId
	 * @return
	 * Jan 13, 20142:27:46 PM
	 */
	@RequestMapping(value="/updateQuestion/{questionId}",method=RequestMethod.POST)
	public ModelAndView updateQuestionSetUpForm(@ModelAttribute QuestionDTO question,Model model,@PathVariable Long questionId){
		//Question question=(Question)questionsService.getQuestionByQuestionId(questionID);
		System.out.println("Question id from path variable "+questionId);
		System.out.println("question id from model Attribute"+question.getQuestion_id());
		//question.setQuestionId(questionId);
		
		User loggedInUser=getLoggedInUser();
		String message="";
		if(loggedInUser!=null){
		Questions editedQuestion=questionsService.getQuestionByQuestionId(questionId);
		
		editedQuestion.setQuestionTitle(question.getQuestion_title());
		editedQuestion.setQuestionDescription(question.getQuestion_description());
	 
		 
		
		ArrayList<Tags> arrayList=new ArrayList<>(); 
		
		String tagString=question.getTags();
		
		if(tagString!=null && !tagString.isEmpty()){
			String[] arrTags=StringUtils.split(tagString,",");
			for (String newTagName : arrTags) {
				 Tags tag=new Tags();
				 tag.setTag(newTagName);
				 arrayList.add(tag);
			 
			}
		}
		if(!tagString.contains(",")){
			if(!tagString.isEmpty()){
				Tags newTag=new Tags();
				newTag.setTag(tagString);
				arrayList.add(newTag);
				
			}
		}
		System.out.println(tagString);
		for (Tags tags : arrayList) {
			System.out.println("Sorted Tags "+tags.getTag() +"Tag ID "+tags.getTagId());
		}
		 
		editedQuestion.setTagsCollection(arrayList);
		editedQuestion.setTimeEdited(new Date());
		editedQuestion.setEditorId(loggedInUser.getUserId());
		
		
		
		questionsService.updateQuestion(editedQuestion);
			 message="Question was successfully edited.. ";
		}else{
			  message="YOU must loggedin.. ";
		}
		
		//	model.addAttribute("message",message);
		ModelAndView modelAndView = new ModelAndView(new RedirectView("/success",true));
		modelAndView.addObject("message",message); 
		
		//return modelAndView;
		//return "redirect:searchquestions";
		return modelAndView;
	}
	/**
	 * 
	 * @param model
	 * @param message
	 * @return
	 * Jan 13, 20142:27:52 PM
	 */
	@RequestMapping(value="/success",method=RequestMethod.GET)
	public String successPage(Model model,@RequestParam(required=true,value="message") String message){
		System.out.println("caught succes message "+ message);
		//		categoryList
		model.addAttribute("message", message);
		return "success";
	}
	
	/*
	public static void main(String[] args) {
		String s="jaava,javvacript,demo";
		String[] sss=s.split(",");
		
	}*/
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 * Jan 13, 20142:27:58 PM
	 */
	@RequestMapping(value="/advancedSearchQuestion",method=RequestMethod.GET)
	public String searchQuestionSetUpForm(HttpServletRequest request,Model model){
		Map<Long, String> categoriesMap = getSelectListQuestionCategories();
		
		model.addAttribute("categoriesList",categoriesMap);
		
		return "advancedSearchQuestion";
	}
	
	 
	/**
	 * 
	 * @param category
	 * @param title
	 * @param question
	 * @param tags
	 * @param sort
	 * @param model
	 * @return
	 * Jan 13, 20142:28:02 PM
	 */
	@RequestMapping(value="/search/category/{category}/title/{title}/question/{question}/tags/{tags}/sort/{sort}",method=RequestMethod.GET)
	public  @ResponseBody JsonResponse searchQuestion(@PathVariable String category
			,	@PathVariable String  title,@PathVariable String  question
			,	@PathVariable String tags,@PathVariable String  sort,Model model){
	 
		 
		Criteria criteria=new Criteria();
		
		if(!category.equals("&")){
			criteria.setCategory(Utill.validateString(category));
		}
		System.out.println("category" +category);
		
		System.out.println("title" +title);
		if(!title.equals("&")){
			criteria.setTitle(Utill.validateString(title));
		}
		System.out.println("question" +question);
		if(!question.equals("&")){
			criteria.setQuestion(Utill.validateString(question));
		}
		System.out.println("tags" +tags);
		
		if(!tags.equals("&")){
			if(tags.contains(",")){
				criteria.setTags(Utill.validateString(tags));
			}else{
				//String[] tag={tags};
				criteria.setTags(Utill.validateString(tags));
			}
		}
		criteria.setSort(sort);
		
		List<QuestionDTO> dtos=questionsService.searchQuestions(criteria);
		 
		
		for (int i=0;i<dtos.size();i++) {
			
			QuestionDTO questionDTO=dtos.get(i);
	 
			
			if(questionDTO.getAuthor_id()!=null){
				User user=userService.searchUser(Long.valueOf(questionDTO.getAuthor_id()));
				questionDTO.setAuthor_name(user.getUserName());
			}
			
			 
			if(questionDTO.getEditor_id()!=null){
				User editor=userService.searchUser(Long.valueOf(questionDTO.getEditor_id()));
				questionDTO.setEditor_name(editor.getUserName());
			}
			  
		}
		
		
		 
		JsonResponse jsonResponse=new JsonResponse();
		
		 
		jsonResponse.setResult(dtos);
		jsonResponse.setStatus( "SUCCESS");
			 
		 
	 
		return jsonResponse;
	}
	
	/**
	 * 
	 * @param model
	 * @param questionID
	 * @return
	 * Jan 13, 20142:28:07 PM
	 */
	@RequestMapping(value="/viewQuestion/id/{questionID}",method=RequestMethod.GET)
	public String viewQuestion(Model model,@PathVariable Long questionID){
	 
		
		User logedInUser = getLoggedInUser();
		
		boolean hasPriviledgeToClose=false;
		boolean isClosed=false;
		boolean hasPriviledgeEditDelete=false;
		
		if(logedInUser!=null){
			if(logedInUser.getRoleId().getRoleId()==2 || logedInUser.getRoleId().getRoleId()==1){
				hasPriviledgeToClose=true;
				hasPriviledgeEditDelete=true;
			}
		}
		
		
		
		Questions question=questionsService.getQuestionByQuestionId(questionID);
		 
		QuestionDTO dto=null;
		
		if(question!=null){
			
			dto=new QuestionDTO();
			
			dto.setQuestion_id(question.getQuestionId().toString());
			dto.setQuestion_id(question.getQuestionId().toString());
			dto.setAuthor_id(question.getAuthorId().toString());
			if(question.getAuthorId()!=null){
				User user=userService.searchUser(question.getAuthorId());
				
				dto.setAuthor_name(user!=null ?user.getUserName() :"");
			}
			dto.setQuestion_title( question.getQuestionTitle());
			dto.setQuestion_description(question.getQuestionDescription());
			 //set question category !! here
			dto.setCategory_name(question.getCategoryId().toString());
			dto.setScore(question.getScore());
			System.out.println("&&&&&&&&&&&&&&&& question.getTimeCreated() "+question.getTimeCreated());
			dto.setTime_created(question.getTimeCreated()!=null? question.getTimeCreated().toString():"");
			dto.setTime_edited(question.getTimeEdited()!=null?question.getTimeEdited().toString():"");
			dto.setEditor_id(question.getEditorId()!=null ? question.getEditorId().toString():"");
			if(logedInUser!=null){
				System.out.println("if(question.getAuthorId().equals(logedInUser.getUserId())){"+ question.getAuthorId().equals(logedInUser.getUserId()));
				if(question.getAuthorId().equals(logedInUser.getUserId())){
					hasPriviledgeEditDelete=true;
				}
			}
			if(question.getIsClosed()==1){
				isClosed=true;
			}
			if(question.getEditorId()!=null){
				User editor=userService.searchUser(question.getEditorId());
				
				dto.setEditor_name(editor!=null ? editor.getUserName() : "");
			}
			if(logedInUser!=null){
				boolean isViewed=questionsService.searchQuestionView(question.getQuestionId(), logedInUser.getUserId());
				if(!isViewed && !question.getAuthorId().equals(logedInUser.getUserId())){
					question.setViews(question.getViews()+1);
					
					ViewQuestion viewQuestion=new ViewQuestion();
					viewQuestion.setQuestionId(question.getQuestionId());
					viewQuestion.setUserid(logedInUser);
					
					questionsService.insertQuestionView(viewQuestion);
					questionsService.updateQuestion(question);
				} 
			}
			dto.setViews(question.getViews());
			List<AnswersDTO> answersDTOs=getAnswersByQuestionID(question.getQuestionId());
			
			dto.setAnswers(answersDTOs);
			
			if(answersDTOs!=null && answersDTOs.size()>0){
				dto.setNoOfAnswers(Long.valueOf(answersDTOs.size()));
			}  
			//dto.set
			
			//question.setViews(question.getViews()+1);
		}
		 
	 
		 
		JsonResponse jsonObject =new JsonResponse();
		
	 
		
		jsonObject.setResult(dto);
		jsonObject.setStatus("SUCCESS");
		JSONObject obj=JSONObject.fromObject(jsonObject);
		
		model.addAttribute("hasPriviledgeEditDelete",hasPriviledgeEditDelete); 
		model.addAttribute("isClosed",isClosed);  
	 	model.addAttribute("questionDataJson",obj);  
	    model.addAttribute("hasPriviledgeToClose",hasPriviledgeToClose);  
		
		return "viewQuestions";
	}
	/**
	 * 
	 * @return
	 * Jan 13, 20142:28:14 PM
	 */
	private User getLoggedInUser() {
		org.springframework.security.core.userdetails.User loggedInUser=null;
		try{ 
			loggedInUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		}catch(Exception e){
			 
		}
		User logedInUser=null;
		if (loggedInUser != null) {
			List<User> loggedInUserDetails= userService.getUserByUsername(loggedInUser.getUsername());
			logedInUser=null;
			
			if(loggedInUserDetails.size()>0){
				  logedInUser =loggedInUserDetails.get(loggedInUserDetails.size()-1);
			}
		}
		return logedInUser;
	}
	/**
	 * 
	 * @param jsnResponse
	 * @param model
	 * @return
	 * Jan 13, 20142:28:17 PM
	 */
	 @RequestMapping(value="/postAnswer", method=RequestMethod.POST ,headers="Accept=application/json")
	 public @ResponseBody JsonResponse postAnswerForQuestion(@RequestBody JSONObject jsnResponse,Model model) {
		 	 System.out.println("klass"+jsnResponse.toString());
		 	 	User logedInUser = getLoggedInUser();
		 	 
		 	 	JsonResponse jsonResponse=new JsonResponse();
		 	 	if(logedInUser!=null){
			 	 	Long lQuestionId=Long.valueOf((String)jsnResponse.get("questionId"));
			 		String sAnswer=(String) jsnResponse.get("answer");
			 		
			 		Answers answer=new Answers();
			 		answer.setQuestionId(lQuestionId);
			 		answer.setText(sAnswer);
			 		answer.setTimeCreated(new Date());
			 		
			 		List<User> users=userService.getUserByUsername(Utill.getCurrentLoggedInUserName(SecurityContextHolder.getContext()));
					if(users!=null && users.size()>0){
						User user=users.get(0);
						answer.setAuthorId(user.getUserId());
					}
					//comment this line
					
					answer.setAuthorId(logedInUser.getUserId());
					answer.setScore((long)0);
			 		
			 		answersService.postAnswer(answer);
			 		
			 		List<AnswersDTO> answersDTOs = getAnswersByQuestionID(lQuestionId);
			 		QuestionDTO dto=new QuestionDTO();
			 		
			 		dto.setAnswers(answersDTOs);
			 		dto.setNoOfAnswers(Long.valueOf(answersDTOs.size()));
			 		
			 		jsonResponse.setResult(dto);
			 		
			 		jsonResponse.setStatus("SUCCESS");
		 	 	}else{
		 	 		jsonResponse.setStatus("ERROR");
		 	 	}
		 
			 return jsonResponse;
	 }
	 /**
	  * 
	  * @param lQuestionId
	  * @return
	  * Jan 13, 20142:28:22 PM
	  */
	private List<AnswersDTO> getAnswersByQuestionID(Long lQuestionId) {
		List<Answers> answers=answersService.getAllAnswersByQuestionId(lQuestionId);
		
		
		List<AnswersDTO> answersList=new ArrayList<>();
		
		for(Answers ans :answers){
			AnswersDTO asnwerDto=new AnswersDTO();
			
			asnwerDto.setAnswerId(ans.getAnswerId().toString());
			asnwerDto.setAnswerDescription( ans.getText());
			asnwerDto.setAuthorId(ans.getAuthorId().toString());
			 
			if(ans.getAuthorId()!=null){
				User author=userService.searchUser(ans.getAuthorId());
				asnwerDto.setAuthrName( author.getUserName());
			}
			
			asnwerDto.setScore(ans.getScore()!=null ? ans.getScore().toString() :"");
			System.out.println("$$$$$$$$ ans.getTimeCreated() "+ans.getTimeCreated());
			System.out.println("$$$$$$$$ ans.getTimeCreated().toString() "+ans.getTimeCreated().toString());
			
			asnwerDto.setTimeCreated(ans.getTimeCreated().toString());
			asnwerDto.setTimeEdited(ans.getTimeEdited()!=null ? ans.getTimeEdited().toString():"");
			asnwerDto.setEditedById( ans.getEditorId()!=null ?ans.getEditorId().toString():"");
			
			if(ans.getEditorId()!=null){
				User editor=userService.searchUser(ans.getEditorId());
				asnwerDto.setEditedByName(editor.getUserName());
			}
			
			
			if(ans.getEditorId()!=null){
				User editor=userService.searchUser(ans.getEditorId() );
				asnwerDto.setEditedByName(editor.getUserName());
				
			}
			//jsonObject.put("", ans.get);
			
			
			
			answersList.add(asnwerDto);
		}
		
		return answersList;
	}
	
	
	/**
	 * 
	 * @param questionId
	 * @param model
	 * @return
	 * Jan 13, 20142:28:28 PM
	 */
	@RequestMapping(value="/closeQuestion/{questionId}",method=RequestMethod.GET)
	public String closeQuestion(@PathVariable Long questionId,Model model){
		User loggedInUser=getLoggedInUser();
		if(loggedInUser!=null){
			if(loggedInUser.getRoleId().getRoleId()==2 || loggedInUser.getRoleId().getRoleId()==1){
				Questions question=questionsService.getQuestionByQuestionId(questionId);
				model.addAttribute("questionId",question.getQuestionId());
				model.addAttribute("questionTitle",question.getQuestionTitle());
				
			}else{
				return "home";
			}
		} else{
			return "home";
		}
		 
		return "closeQuestion";
	}
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 * Jan 13, 20142:28:34 PM
	 */
	@RequestMapping(value="/closeQuestion",method=RequestMethod.POST)
	public ModelAndView processCloseQuestion(HttpServletRequest request,Model model){
		
		User loggedInUser=getLoggedInUser();
		String message="";
		String questionId=request.getParameter("questionId");
		String reason=request.getParameter("reason");
		 
		
		if(loggedInUser.getRoleId().getRoleId()==2){
			if(questionId!=null && !questionId.trim().isEmpty()){
				Questions question=questionsService.getQuestionByQuestionId(Long.valueOf(questionId.trim()));
				if(question!=null){
					if(question.getIsClosed()==0){
						String questionTitle=question.getQuestionTitle();
						
						question.setQuestionTitle("[CLOSED]-REASON[ "+reason.toUpperCase()+" ]"+questionTitle);
						question.setIsClosed(1);
						
						question.setEditorId(loggedInUser.getUserId());
						question.setTimeEdited(new Date());
						
						questionsService.updateQuestion(question);
						message="Question successfully closed";
						 
					}else{
						message="This question is already closed";
						 
					}
					
					 
				}
			}
		}else{
			message="You do not have privileges to close";
		}
		model.addAttribute("message",message);
		
		ModelAndView modelAndView = new ModelAndView(new RedirectView("/success",true));
		modelAndView.addObject("message",message); 
		
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	
	
	
	 
	
}
 