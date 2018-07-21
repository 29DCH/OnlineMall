package priv.jesse.mall.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import priv.jesse.mall.entity.Comment;
import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Integer> {
  List<Comment> findAll();
  Comment findById(int id);
}
