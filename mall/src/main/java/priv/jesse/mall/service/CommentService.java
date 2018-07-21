package priv.jesse.mall.service;

import priv.jesse.mall.entity.Comment;
import java.util.List;

public interface CommentService {
    int create(Comment comment);
    void update(Comment comment);
    List<Comment> getCommentList();
       Comment findCommentById(int id);
        void save(Comment comment);
    Comment findById(int id);
}
