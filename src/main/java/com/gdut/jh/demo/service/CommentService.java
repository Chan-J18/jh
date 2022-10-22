package com.gdut.jh.demo.service;

import com.gdut.jh.demo.dao.CommentArticleDao;
import com.gdut.jh.demo.dao.CommentDao;
import com.gdut.jh.demo.dao.UserCommentDao;
import com.gdut.jh.demo.pojo.entity.Comment;
import com.gdut.jh.demo.pojo.entity.comment_article;
import com.gdut.jh.demo.pojo.entity.user_comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    CommentDao commentDao;
    @Autowired
    UserCommentDao userCommentDao;
    @Autowired
    CommentArticleDao commentArticleDao;


    public int addComment(Comment comment){
        return commentDao.save(comment).getId();
    }

    public void saveCandU(int cid,int uid){
        user_comment uc = new user_comment();
        uc.setCid(cid);
        uc.setUid(uid);
        userCommentDao.save(uc);
    }

    public void  saveCandA(int cid,int aid){
        comment_article ca = new comment_article();
        ca.setCid(cid);
        ca.setAid(aid);
        commentArticleDao.save(ca);
    }

    public void deleteCandA(List<Integer> aids){
         commentArticleDao.deleteByAidIn(aids);
    }
    public void deleteCandUid(int uid){
        List<Integer> cids = userCommentDao.findByUid(uid).stream()
                .map(user_comment::getCid).collect(Collectors.toList());
        userCommentDao.deleteByUid(uid);
        commentDao.deleteByIdIn(cids);
    }

    public List<Integer> findCommentsByAid(int aid){
        return commentArticleDao.findAllByAid(aid).stream()
                .map(comment_article::getCid).collect(Collectors.toList());
    }

    public List<Comment> findCommentsByCid(List<Integer> cids){
        return commentDao.findAllByIdInOrderByIdDesc(cids);
    }

    public int findUidByCid(int cid){
        return userCommentDao.findByCid(cid).getUid();
    }
}
