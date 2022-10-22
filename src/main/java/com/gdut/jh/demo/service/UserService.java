package com.gdut.jh.demo.service;

import com.gdut.jh.demo.dao.*;
import com.gdut.jh.demo.pojo.entity.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    BrowseDao browseDao;
    @Autowired
    FeatureUserDao featureUserDao;
    @Autowired
    FeatureDao featureDao;
    @Autowired
    ArticleDao articleDao;
    @Autowired
    TopicArticleDao topicArticleDao;
    @Autowired
    UserRoleDao userRoleDao;
    @Autowired
    PublishDao publishDao;
    @Autowired
    CommentService commentService;

    public void delete(int id){
        userDao.deleteById(id);//删除用户
        featureDao.deleteById(featureUserDao.findByUid(id).getFid());// 删除用户特征项
        featureUserDao.deleteByUid(id);//删除用户特征关系表项
        browseDao.deleteByUid(id);//删除用户浏览记录
        List<Integer> aids = publishDao.findByUid(id).stream().map(Publish::getAid).collect(Collectors.toList());
        publishDao.deleteByUid(id);//删除发布关系表项
        articleDao.deleteByIdIn(aids);//删除发布的文章
        commentService.deleteCandA(aids);//删除发布每个文章下的文章评论关系表项，发的评论没删，发评论的用户可以查看自己的评论
        commentService.deleteCandUid(id);//删除用户评论关系表项及用户发布的评论
        userRoleDao.deleteByUid(id);//删除用户角色
    }

    public List<User> list(){ return  userDao.findAll();}

    public User getUserByUsername(String username){
        return userDao.findByUsername(username);
    }
    public User getUserByUid(int uid) {return userDao.findById(uid);}
    public void updateInfo(User u){
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userDao.findByUsername(username);
        user.setUsername(u.getUsername());
        user.setEmail(u.getEmail());
        user.setMotto(u.getMotto());
        userDao.save(user);
    }

    public void addUser(User user){
        int uid = userDao.save(user).getId();
        int fid = featureDao.save(new Feature()).getId();
        feature_user fu = new feature_user();
        fu.setFid(fid);
        fu.setUid(uid);
        featureUserDao.save(fu);
        user_role ur = new user_role();
        ur.setUid(uid);
        ur.setRid(1);
        userRoleDao.save(ur);
    }
    //记录用户浏览记录
    public void addUserRecord(int uid,int aid){
        Browse browse = new Browse();
        browse.setAid(aid);
        browse.setUid(uid);
        browseDao.save(browse);
    }
    //获取用户浏览记录中的文章id
    public List<Integer> getUserRecord(int uid){
        return  browseDao.findByUid(uid).stream()
                .map(Browse::getAid).collect(Collectors.toList());
    }
    //获取相似用户id
    public List<Integer> getSimilarUser(int uid) {
        int fid = featureUserDao.findByUid(uid).getFid();
        Feature user_feature = featureDao.findById(fid);
        List<Feature> list = featureDao.findByOrderByLen();//默认降序
        int size = list.size();
        float maxLen = list.get(0).getLen();
        float minlen = list.get(size-1).getLen();
        float tmp_len = (user_feature.getLen()-minlen)/(maxLen-minlen);

        List<Integer> fids = new ArrayList<>();
        for(Feature feature:list){
            float a=Math.abs(user_feature.getAmusement() - feature.getAmusement());
            float b=Math.abs(user_feature.getMilitary() - feature.getMilitary());
            float c=Math.abs(user_feature.getLife() - feature.getLife());
            float d = Math.abs(tmp_len-(feature.getLen()-minlen)/(maxLen-minlen));
            double similar = Math.sqrt(a*a+b*b+c*c+d*d);
            if (similar < 0.5) fids.add(feature.getId());
        }
        return featureUserDao.findByFidIn(fids).stream()
                .map(feature_user::getUid).collect(Collectors.toList());
    }
    //更新用户特征
    public void updateUserFeature(int uid,List<Integer> aids){
        int fid =featureUserDao.findByUid(uid).getFid();
        List<Article> articles = articleDao.findByIdIn(aids);

        Feature s = new Feature();
        s.setId(fid);
        int Len = 0;
        int size =articles.size();
        float c1,c2,c3;c1=c2=c3=0;

        for(Article article: articles){ Len += article.getLen(); }
        Len /= size;
        s.setLen(Len);

        List<Integer> types = topicArticleDao.findByAidIn(aids).stream().map(topic_article::getTid).collect(Collectors.toList());
        for(int t: types){
            if(t == 1) c1++;
            if(t == 2) c2++;
            if(t == 3) c3++;
        }
        c1/=size;c2/=size;c3/=size;
        s.setMilitary(c1);s.setLife(c2);s.setAmusement(c3);
        featureDao.save(s);
    }


}
