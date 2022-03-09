package tn.esprit.spring.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.SubComment;
import tn.esprit.spring.repository.SubCommentRepository;
import tn.esprit.spring.service.ISubCommentService;


@RestController 
@RequestMapping("/subComment") 
public class SubCommentController {


		
		@Autowired
		ISubCommentService subCommentService;
		@Autowired
		SubCommentRepository subCommentRepo;
		
		@GetMapping("/subCommentMonth") 
		@ResponseBody 
		public List<Object[]> subCommentMonth() {
			Date dateStart = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date dateEnd = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
			return subCommentRepo.countTotalSubCommentsByMonth(dateStart, dateEnd);
		}
		
		@GetMapping("/retrieveAll") 
		@ResponseBody 
		public List<SubComment> retrieveAllSubComment() {
			return subCommentService.retrieveAllSubComment();
		}

		@PostMapping("/add/{idComment}/{idUser}") 
		@ResponseBody 
		public SubComment addSubComment(@RequestBody SubComment c, @PathVariable Long idComment, @PathVariable Long idUser) {
			return subCommentService.addSubComment(c,idComment, idUser);
		}

		@PostMapping("/delete/{idSubComment}/{idUser}") 
		public void deleteSubComment(@PathVariable Long idSubComment,@PathVariable Long idUser) {
			subCommentService.deleteSubComment(idSubComment, idUser);
			
		}

		@PostMapping("/update/{idSubComment}/{idUser}") 
		@ResponseBody 
		public SubComment updateSubComment(@RequestBody SubComment c, @PathVariable Long idSubComment, @PathVariable Long idUser) {
			return subCommentService.updateSubComment(c, idSubComment, idUser);
		}

		@GetMapping("/retrieveBy/{idSubComment}") 
		@ResponseBody 
		public Optional<SubComment> retrieveSubCommentById(@PathVariable Long idSubComment) {
			return subCommentService.retrieveSubCommentById(idSubComment);
		}
}

