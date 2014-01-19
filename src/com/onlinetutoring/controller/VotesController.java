package com.onlinetutoring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinetutoring.model.Answers;
import com.onlinetutoring.model.JsonResponse;
import com.onlinetutoring.model.Questions;
import com.onlinetutoring.model.User;
import com.onlinetutoring.model.VotingAnswer;
import com.onlinetutoring.model.VotingQuestion;
import com.onlinetutoring.service.AnswerService;
import com.onlinetutoring.service.QuestionsService;
import com.onlinetutoring.service.UserService;
import com.onlinetutoring.service.VoteService;
import com.onlinetutoring.utills.VoteAnswerDTO;
import com.onlinetutoring.utills.VoteQuestionDTO;
/**
 * 
 * VotesController.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 11, 2014 4:15:07 PM
 */
@Controller
public class VotesController {

	@Autowired
	private QuestionsService questionsService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private UserService userService;

	@Autowired
	private VoteService voteService;  
	/**
	 * 
	 * @param jsnResponse
	 * @param model
	 * @param session
	 * @return
	 * Jan 13, 20142:28:55 PM
	 */
	@RequestMapping(value = "/vote", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody
	JsonResponse processVote(@RequestBody JSONObject jsnResponse, Model model, HttpSession session) {
		System.out.println("klass" + jsnResponse.toString());
		String message="SUCCESS";
		String voteValue=null;
	//	com.onlinetutoring.model.User currentUser=null;
		
		JsonResponse jsonResponse = new JsonResponse();

		String elementName = (String) jsnResponse.get("elementName");// vote_137304_1_q_137304
		String voteStatus = (String) jsnResponse.get("voteStatus");// vote up or
																	// vote down
		Long id = null;
		String type = null;

		if (elementName != null) {

			String[] elements = elementName.split("_");
			if (elements != null && elements.length > 4) {
				id = elements[1] != null ? Long.valueOf(elements[1].trim())
						: null;
				type = elements[3];

			}
		}
		org.springframework.security.core.userdetails.User loggedInUser=null;
		try{ 
			loggedInUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		}catch(Exception e){
			System.out.println(e.getLocalizedMessage());
			message="You must log in to vote";
		}
		User logedInUser=null;
		if (loggedInUser != null) {
			List<User> loggedInUserDetails= userService.getUserByUsername(loggedInUser.getUsername());
			logedInUser=null;
			
			if(loggedInUserDetails.size()>0){
				  logedInUser =loggedInUserDetails.get(loggedInUserDetails.size()-1);
			}
		}
		if (logedInUser != null) {
			
			
			if (voteStatus != null && type != null && id != null) {

				boolean voteUp = voteStatus.trim().equalsIgnoreCase("+") ? true : false;
				
				
				
				if (type.equals("q")) {
					Questions question = questionsService.getQuestionByQuestionId(id);
					voteValue=question.getScore().toString();
					
					if(logedInUser.getUserId().equals(question.getAuthorId())){
						message="Sorry you cannot vote up";
						jsonResponse.setStatus(message);
						jsonResponse.setResult(voteValue);
						return jsonResponse;
					}
					
					User questionAuthor = userService.searchUser(question .getAuthorId());
					
					//change to pass current logged in user id
					List<VoteQuestionDTO>  voteQuestionDtos=(List<VoteQuestionDTO>) voteService.searchVotingQuestion(question.getQuestionId(),logedInUser.getUserId());
					
					VotingQuestion votingQuestion = new VotingQuestion();
					
					boolean isVotedQuestion = false;
					
					if(voteQuestionDtos!=null&& voteQuestionDtos.size()>0){
						
						VoteQuestionDTO voteDto=voteQuestionDtos.get(voteQuestionDtos.size()-1);
						votingQuestion=voteService.searchQuestionVote(voteDto.getVoteId());///;
						
						isVotedQuestion=true;
				
					}
					//  currentUser=loggedInUserDetails.get(loggedInUserDetails.size()-1);
					votingQuestion.setUserId(logedInUser.getUserId());
					
					if (voteUp) {
						if(isVotedQuestion){
							if(votingQuestion.getStatus()==1){
								message="You have already UP voted";
								jsonResponse.setStatus(message);
								
								jsonResponse.setResult(voteValue);
								return jsonResponse;
							}
						}
						questionAuthor.setLoyality(questionAuthor.getReputation() + 1);
						question.setScore(question.getScore() + 1);
						voteValue =question.getScore().toString();
						votingQuestion.setStatus(1);// 1 for vote Up -1 fr vote
													// Down
						// voteUpQuestion();
					} else {
						
						if(isVotedQuestion){
							if(votingQuestion.getStatus()==-1){
								
								message="You have already Down voted";
								jsonResponse.setStatus(message);
								jsonResponse.setResult(voteValue);
								return jsonResponse;
							}
						}
						
						// voteDownQuestion();
						questionAuthor.setLoyality(questionAuthor .getLoyality() - 1);
						question.setScore(question.getScore() - 1);

						votingQuestion.setStatus(-1);
						voteValue=question.getScore().toString();
						jsonResponse.setResult(voteValue);
					}

					votingQuestion.setQuestionId(question);
					
					 
					if(isVotedQuestion){
						voteService.updateQuestionVote(votingQuestion);
					}else{
						voteService.inserVote(votingQuestion);
					}

					questionsService.updateQuestion(question);
					userService.updateUser(questionAuthor);

				} else if (type.equals("a")) {
					Answers answer = answerService.getAnswerByAnswerid(id);
					User answer_author = userService.searchUser(answer .getAuthorId());
					voteValue=answer.getScore().toString();
					
					
					if(logedInUser.getUserId().equals(answer.getAuthorId())){
						message="Sorry you cannot vote up";
						jsonResponse.setStatus(message);
						jsonResponse.setResult(voteValue);
						return jsonResponse;
					}
					
					
					
					//change to pass current logged in user id
					List<VoteAnswerDTO>  voteAnswersDtos=(List<VoteAnswerDTO>) voteService.searchVotingAnswer(answer.getAnswerId(),logedInUser.getUserId());
					
					boolean isVotedAnswer = false;
					VotingAnswer votingAnswer=new VotingAnswer();
					
					if(voteAnswersDtos!=null&& voteAnswersDtos.size()>0){
						VoteAnswerDTO voteDto=voteAnswersDtos.get(voteAnswersDtos.size()-1);
						
						votingAnswer=voteService.searchAnswerVote(voteDto.getVoteId());///;
						
						isVotedAnswer=true;
				
					}
					//set current logged in use id
					votingAnswer.setUserId(logedInUser.getUserId());					
					if (voteUp) {
						if(isVotedAnswer){
							if(votingAnswer.getStatus()==1){
								
								 
								message="You have already UP voted";
								jsonResponse.setStatus(message);
								jsonResponse.setResult(voteValue);
								return jsonResponse;
							}
						}
						answer.setScore(answer.getScore() + 1);
						answer_author.setReputation(answer_author .getReputation() + 1);
						votingAnswer.setStatus(1);
						voteValue=answer.getScore().toString();
						jsonResponse.setResult(voteValue);
					} else {
						if(isVotedAnswer){
							if(votingAnswer.getStatus()==-1){
								
								 
								message="You have already DOWN voted";
								jsonResponse.setStatus(message);
								return jsonResponse;
							}
						}
						
						answer.setScore(answer.getScore() - 1);
						answer_author.setReputation(answer_author.getReputation() - 1);
						// voteDownAnswer();
						votingAnswer.setStatus(-1);
						
						voteValue=answer.getScore().toString();
						jsonResponse.setResult(voteValue);
					}
					votingAnswer.setAnswerId(answer);
					if(isVotedAnswer){
						 voteService.updateAnswerVote(votingAnswer);
					}else{
						voteService.inserVote(votingAnswer);
					}	
					answerService.updateAnswer(answer);
					userService.updateUser(answer_author);
					
				}
			}
			
			jsonResponse.setStatus(message);
			return jsonResponse;

		} else {
			// return DO
			message="ERROR";
			jsonResponse.setStatus(message);
			
			return jsonResponse;
		}
	 
 

	}
	 
}
