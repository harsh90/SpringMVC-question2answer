package com.onlinetutoring.dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.onlinetutoring.dao.QuestionsDao;
import com.onlinetutoring.model.QuestionCategory;
import com.onlinetutoring.model.Questions;
import com.onlinetutoring.model.Tags;
import com.onlinetutoring.model.ViewQuestion;
import com.onlinetutoring.utills.Criteria;
import com.onlinetutoring.utills.QuestionDTO;

/**
 * 
 * QuestionsDaoImpl.java
 * 
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:54:06 AM
 */
@Repository("questionsDao")
public class QuestionsDaoImpl implements QuestionsDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DataSource dataSource;

	public SessionFactory getSessionFactory() {

		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Questions> getAllQuestions() {

		return (List<Questions>) sessionFactory.getCurrentSession()
				.createCriteria(Questions.class).list();
	}

	public Questions getQuestionByQuestionId(Long questionId) {
		return (Questions) sessionFactory.getCurrentSession().get(
				Questions.class, questionId);
	}

	public void postQuestion(Questions question) {
		System.out.println("Before Save " + question.getQuestionTitle());
		// sessionFactory.getCurrentSession().beginTransaction();
		sessionFactory.getCurrentSession().save(question);
		// sessionFactory.getCurrentSession().getTransaction().commit();
	}
	public void deleteQuestion(Questions question) {

		System.out.println("############DElETING" + question.getQuestionId());
		final Long questionid=question.getQuestionId();
		try {

			Questions question2 = (Questions) sessionFactory
					.getCurrentSession().load(Questions.class,
							question.getQuestionId());
			if (question2 != null) {
				System.out.println("**************Quetion deleted ");

				sessionFactory.getCurrentSession().delete(question2);
				
				JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
				jdbcTemplate.update("DELETE FROM onlinetutoring.answers WHERE question_id=?", new PreparedStatementSetter() {
				      public void setValues(PreparedStatement ps) throws SQLException {
				          ps.setLong(1, questionid);
				      }
				  });
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void updateQuestion(Questions question) {

		getCurrentSession().update(question);

	}
	@Override
	public List<QuestionCategory> getAllQuestionCategories() {
		return (List<QuestionCategory>) getCurrentSession().createCriteria(QuestionCategory.class).list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List searchQuestions(Criteria criteria) {

		String title, description, category = null;

		if (criteria.getTitle() == null) {
			title = "%";
		} else {
			title = "%" + criteria.getTitle() + "%";
		}

		if (criteria.getQuestion() == null) {
			description = "%";
		} else {
			description = "%" + criteria.getQuestion() + "%";
		}

		if (criteria.getCategory().equalsIgnoreCase("ALL")) {
			category = "%";
		} else {
			category = "%" + criteria.getCategory() + "%";
		}

		String sql = "SELECT * FROM onlinetutoring.questions  where question_title LIKE '"+ title+ "' AND question_description LIKE '"+ description+ "' AND category_id LIKE '" + category + "'";

		if (criteria.getTags() != null) {
			ArrayList<String> list = getQuestionIDsBYtags(criteria.getTags());

			for (String id : list) {
				System.out.println("idaass " + id);
			}

			String commaseperatedIDS = StringUtils.join(list, ',');

			System.out.println(commaseperatedIDS);
			if (list != null && list.size() > 0) {

				sql += " AND question_id in(" + commaseperatedIDS + ")";
			} else {
				sql += " AND question_id in(" + -1 + ")";
			}
		}

		if (criteria.getSort() != null) {
			if (criteria.getSort().equalsIgnoreCase("NEW")) {
				sql += " ORDER BY time_created DESC";
			}
			if (criteria.getSort().equalsIgnoreCase("OLD")) {
				sql += " ORDER BY time_created ASC";
			}

		}

		System.out.println("SQL " + sql);

		final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		 
		@SuppressWarnings("rawtypes")
		List collection = (List) jdbcTemplate.query(sql,
				new ResultSetExtractor() {
					public Object extractData(final ResultSet rs)
							throws SQLException, DataAccessException {
						final Collection questionList = new ArrayList();
						while (rs.next()) {
							QuestionDTO dto=new QuestionDTO();
						 
							dto.setQuestion_id( rs.getString("question_id"));
							dto.setQuestion_title(rs.getString("question_title"));
							dto.setQuestion_description(rs.getString("question_description"));
							dto.setAuthor_id(rs.getString("author_id"));
							dto.setTime_created(rs.getString("time_created"));
							dto.setEditor_id( rs.getString("editor_id"));
							dto.setTime_edited(rs.getString("time_edited"));
							dto.setIs_closed( rs.getString("is_closed"));
							dto.setCategory_id( rs.getString("category_id"));
							List<QuestionCategory> category=findByQuestionCategoryId(rs.getLong("category_id"));
							if(category!=null){
									dto.setCategory_name(category.get(category.size()-1).getQuestionCategory());
							}
							dto.setScore(rs.getLong("score"));
							dto.setViews(rs.getLong("views"));
							Questions question=getQuestionByQuestionId(rs.getLong("question_id"));
							Collection<Tags> tags=question.getTagsCollection();
							String tagName="";
							for (Tags tag : tags) {
								tagName+=tag.getTag()+",";
							}
							dto.setTags(tagName);
							 
							Long count=getAnswerCountByQuestionId(rs.getLong("question_id"),jdbcTemplate);
							dto.setNoOfAnswers(count);
						 
							questionList.add(dto);
							System.out.println("TIME STAMPP "+rs.getTimestamp("time_created"));
							System.out.println("TIME STAMPP "+rs.getDate("time_created"));
							
							System.out.println("questionID " + dto.getQuestion_id()+ "questionDesc " + dto.getQuestion_description());
						}
						return questionList;
					}

					
				});

		return collection;
	}
	
	public Long getAnswerCountByQuestionId(long long1,JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		String sql="SELECT count(*) FROM onlinetutoring.answers where question_id=?";
		
		Long count=jdbcTemplate.queryForLong(sql,long1) ;
		 
		return count;
	}
	/**
	 * 
	 * @param tags
	 * @return
	 * Jan 11, 20145:56:56 PM
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getQuestionIDsBYtags(String tags) {
		String sql = "SELECT question_id FROM onlinetutoring.question_tags where tag_id in(SELECT tag_id FROM onlinetutoring.tags where tag ";
		System.out.println("Inside Searc for tags methd ###" + tags);
		sql=generateINStatement(sql,tags);
		
		System.out.println("TAG SEARCG SQL" + sql);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		@SuppressWarnings("rawtypes")
		ArrayList collection = (ArrayList) jdbcTemplate.query(sql,
				new ResultSetExtractor() {
					public Object extractData(final ResultSet rs)
							throws SQLException, DataAccessException {
						final ArrayList questioIdList = new ArrayList();
						while (rs.next()) {
							String questionID = rs.getString("question_id");

							questioIdList.add(questionID);
							System.out.println("searched questionIDs by TAG "+ questionID);
						}
						return questioIdList;
					}
				});

		return collection;
	}
	/**
	 * 
	 * @param query
	 * @param params
	 * @return
	 * Jan 11, 20145:56:50 PM
	 */
	public static String generateINStatement(String query, String params) {

		if (params != null) {
			String[] idArr = params.split(",");
			if (idArr.length > 0) {
				query += "IN(";
				for (String string : idArr) {
					System.out.println(string);
					query += "'" + string + "',";
				}
			}

			query = query.substring(0, query.lastIndexOf(","));
			query += "))";

		}
		return query;
	}

	@Override
	public void insertQuestionView(ViewQuestion viewQuestion) {
		getCurrentSession().save(viewQuestion);
		
	}
	@Override
	public boolean searchQuestionView(Long questionid,
			Long loggedinuserId) {
		boolean found=false;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String sql = "SELECT count(*) as count FROM onlinetutoring.view_question where question_id=? AND userid=? ";
	  
 
		try {
			dbConnection = dataSource.getConnection();
			
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setLong( 1, questionid);
			preparedStatement.setLong(2, loggedinuserId);
			
			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			 
			
			while (rs.next()) {
				long count=rs.getLong("count");
				if(count>0){
					found=true;
				}
			}
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
		}
		return found;
	}

	@Override
	public List<QuestionCategory> findByQuestionCategoryId(Long categoryID) {
		// TODO Auto-generated method stub
		return getCurrentSession().getNamedQuery("QuestionCategory.findByQuestionCategoryId").setLong("questionCategoryId", categoryID).list();
	}

	@Override
	public List<Tags> findByTagsname(String tagName) {
		// TODO Auto-generated method stub
		 
		return getCurrentSession().getNamedQuery("Tags.findByTag").setString("tag", tagName).list();
		
	}

	@Override
	public List searchQuestions(String searchKey) {
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		final List questionList = new ArrayList();
		if(searchKey==null){
			searchKey="%";
		}else{
			searchKey="%"+searchKey+"%";
		}
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String sql = "SELECT * FROM onlinetutoring.questions where question_title LIKE ? ";
	  
 
		try {
			dbConnection = dataSource.getConnection();
			
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString( 1, searchKey);
		 
			
			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				QuestionDTO dto=new QuestionDTO();
				 
				dto.setQuestion_id( rs.getString("question_id"));
				dto.setQuestion_title(rs.getString("question_title"));
				dto.setQuestion_description(rs.getString("question_description"));
				dto.setAuthor_id(rs.getString("author_id"));
				dto.setTime_created(rs.getString("time_created"));
				dto.setEditor_id( rs.getString("editor_id"));
				dto.setTime_edited(rs.getString("time_edited"));
				dto.setIs_closed( rs.getString("is_closed"));
				dto.setCategory_id( rs.getString("category_id"));
				List<QuestionCategory> category=findByQuestionCategoryId(rs.getLong("category_id"));
				if(category!=null){
						dto.setCategory_name(category.get(category.size()-1).getQuestionCategory());
				}
				dto.setScore(rs.getLong("score"));
				dto.setViews(rs.getLong("views"));
				Questions question=getQuestionByQuestionId(rs.getLong("question_id"));
				Collection<Tags> tags=question.getTagsCollection();
				String tagName="";
				for (Tags tag : tags) {
					tagName+=tag.getTag()+",";
				}
				dto.setTags(tagName);
				 
				Long count=getAnswerCountByQuestionId(rs.getLong("question_id"),jdbcTemplate);
				dto.setNoOfAnswers(count);
			 
				questionList.add(dto);
				 
			}
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
		}
		return  questionList;
	}
}
