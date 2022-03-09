package tn.esprit.spring.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Dislikess;
import tn.esprit.spring.entities.Likess;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.DislikessRepository;
import tn.esprit.spring.repository.LikessRepository;
import tn.esprit.spring.repository.PostRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class PostService implements IPostService {
	
	@Autowired 
	PostRepository postRepository;
	@Autowired 
	UserRepository userRepository;
	@Autowired 
	UserService userService;
	@Autowired 
	LikessRepository likessRepository;
	@Autowired 
	DislikessRepository dislikessRepository;
	@Autowired 
	CommentService commentService;
	@Autowired 
	SubCommentService subCommentService;
	
	@Override
	public List<Post> retrieveAllPost() {
		return (List<Post>) postRepository.findAll();
	}

	@Override
	public Post addPost(Post p, Long idUser) {
		User user = userRepository.getById(idUser);
		p.setUser(user);
		p.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		return postRepository.save(p);
	}

	@Override
	public void deletePost(Long idPost, Long idUser) {
		Post post = postRepository.getById(idPost);
		if(post.getUser().getIdUser()==idUser)
		{
			postRepository.deleteById(idPost);
		}
		
		
	}

	@Override
	public Post updatePost(Post p, Long id, Long idUser) {
		Post postMain = postRepository.getById(id);
		if(postMain.getUser().getIdUser()==idUser)
		{
		Post post = postRepository.getById(id);
		post.setContent(p.getContent());
		post.setSubject(p.getSubject());
		post.setLikes(p.getLikes());
		post.setDislikes(p.getDislikes());
		post.setViews(p.getViews());
		return postRepository.save(post);
		}else{
			return postMain;
		}
		
	}

	@Override
	public Post retrievePostById(Long idPost) {
		Post postMain = postRepository.getById(idPost);
		postMain.setViews(postMain.getViews()+1);
		return postRepository.save(postMain);
	
	}

	@Override
	public void likePost(Long idPost, long idUser) {
		User user = userRepository.getById(idUser);
		Post post = postRepository.getById(idPost);
		Likess l = likessRepository.findByPostAndUser(post, user);
		Dislikess d = dislikessRepository.findByPostAndUser(post, user);
		
		if(l==null){
			if(d==null){
				//make like
				Likess newLike = new Likess();
				newLike.setPost(post);
				newLike.setUser(user);
				newLike.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				likessRepository.save(newLike);
				post.setLikes(post.getLikes()+1);
				postRepository.save(post);
			}
			else{
				//remove dislike
				dislikessRepository.deleteById(d.getIdDislike());
				post.setDislikes(post.getDislikes()-1);
				postRepository.save(post);
				//make like
				Likess newLike = new Likess();
				newLike.setPost(post);
				newLike.setUser(user);
				newLike.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				likessRepository.save(newLike);
				post.setLikes(post.getLikes()+1);
				postRepository.save(post);
			}
			
		}else{
			//remove like
			likessRepository.deleteById(l.getIdLike());
			post.setLikes(post.getLikes()-1);
			postRepository.save(post);
		}
		
	}

	//dislike
	
	@Override
	public void dislikePost(Long idPost, long idUser) {
		User user = userRepository.getById(idUser);
		Post post = postRepository.getById(idPost);
		Dislikess d = dislikessRepository.findByPostAndUser(post, user);
		Likess l = likessRepository.findByPostAndUser(post, user);
		
		if(d==null){
			if(l==null){
				//make dislike
				Dislikess newDislike = new Dislikess();
				newDislike.setPost(post);
				newDislike.setUser(user);
				newDislike.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				dislikessRepository.save(newDislike);
				post.setDislikes(post.getDislikes()+1);
				postRepository.save(post);
			}
			else{
				//remove like
				likessRepository.deleteById(l.getIdLike());
				post.setLikes(post.getLikes()-1);
				postRepository.save(post);
				//make dislike
				Dislikess newDislike = new Dislikess();
				newDislike.setPost(post);
				newDislike.setUser(user);
				newDislike.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				dislikessRepository.save(newDislike);
				post.setDislikes(post.getDislikes()+1);
				postRepository.save(post);
			}
		}else{
			//remove dislike
			dislikessRepository.deleteById(d.getIdDislike());
			post.setDislikes(post.getDislikes()-1);
			postRepository.save(post);
		}
		
	}
	
	
	public List<Post> filterPost(String str){
		List<Post> l1 = postRepository.filterByContent(str);
		List<Post> l2 = postRepository.filterBySubject(str);
		
		for(Post p1:l1){
			for(Post p2:l2){
				if(p1.getIdPost()!=p2.getIdPost()){
					l2.add(p1);
				}
			
			}
		}
		return l1;
	}
	
	//employee of the month
	
	public List<Object[]> postMonth() {
		Date dateStart = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date dateEnd = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		return postRepository.countTotalPostsByMonth(dateStart, dateEnd);
	}
	
	public List<Object[]> likesMonth() {
		Date dateStart = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date dateEnd = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		return likessRepository.countTotalLikesByMonth(dateStart, dateEnd);
	}
	
	public List<Object[]> dislikesMonth() {
		Date dateStart = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date dateEnd = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		return dislikessRepository.countTotalDislikesByMonth(dateStart, dateEnd);
	}
	
	@Scheduled(cron = "0 0 0 1 * *")
	public List<Object> employeeOfTheMonth(){
		List<Object[]> postMonth = limitFiveWithScore(postMonth(), 10);
		List<Object[]> commentMonth = limitFiveWithScore(commentService.commentMonth(),8);
		List<Object[]> subCommentMonth = limitFiveWithScore(subCommentService.subCommentMonth(),6);
		List<Object[]> likeMonth = limitFiveWithScore(likesMonth(),4);
		List<Object[]> dislikeMonth = limitFiveWithScore(dislikesMonth(), 2);
		
		List<Object[]> l = new ArrayList<Object[]>();
		l=mergeScores(postMonth, commentMonth, subCommentMonth, likeMonth, dislikeMonth);
		
		long test = 0;
		List<Long> employeeOfTheMonth = new ArrayList<>();
		for(Object[] o:l){
			if(Long.valueOf(o[1].toString())>test){
				test =Long.valueOf(o[1].toString());
				employeeOfTheMonth.clear();
				employeeOfTheMonth.add(Long.valueOf(o[0].toString()));
				employeeOfTheMonth.add(Long.valueOf(o[1].toString()));
			}
		}
		List<Object> result = new ArrayList <Object>();
		result.add(employeeOfTheMonth.get(1));
		result.add(userService.retrieveUser(employeeOfTheMonth.get(0)));
		
		return result;
	}
	
	
	public List<Object[]> limitFiveWithScore(List<Object[]> l, int n){
		List<Object[]> list = new ArrayList<Object[]>();
		int i = 0;
		for(Object[] o:l){
			if(i<5){
				list.add(o);
				o[1]=Integer.valueOf(o[1].toString())*n;
				i++;
			}
			}
		return list;
	}
	
	public List<Object[]> mergeScores(List<Object[]> l1, List<Object[]> l2, List<Object[]> l3, List<Object[]> l4, List<Object[]> l5){
		List<Object[]> l = new ArrayList<Object[]>();
		l=l1;
		combineTwoLists(l2,l);
		combineTwoLists(l3,l);
		combineTwoLists(l4,l);
		combineTwoLists(l5,l);
		
		return l;
	}
	
	public void combineTwoLists(List<Object[]> list,List<Object[]> l){
		for(Object[] o:list){
			if(testEqual(l,Integer.valueOf(o[0].toString()))){
				for(Object[] ob:l){
					if(ob[0]==o[0]){
						ob[1]=Integer.valueOf(ob[1].toString())+Integer.valueOf(o[1].toString()) ;
					}
				}
			}else
			l.add(o);
		}
	}
	
	public boolean testEqual (List<Object[]> l, int obj){
		for(Object[] o:l){
			if(obj==Integer.valueOf(o[0].toString())){
				return true;
			}
		}
		return false;
	}
	
	

}
