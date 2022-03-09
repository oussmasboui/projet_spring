package tn.esprit.spring.service;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.SubComment;

public interface ISubCommentService {

public List<SubComment> retrieveAllSubComment();
	
	public SubComment addSubComment(SubComment c, Long idSubComment, Long idUser);
	
	public void deleteSubComment(Long idSubComment, Long idUser);
	
	public SubComment updateSubComment(SubComment c, Long id, Long idUser);
	
	public Optional<SubComment> retrieveSubCommentById(Long idSubComment);
}
