package cn.com.taiji.service.impl;

import cn.com.taiji.domain.*;
import cn.com.taiji.domain.Blog;
import cn.com.taiji.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    


    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private ChatTeamRepository chatTeamRepository;
    @Autowired
    private CommentReposity commentReposity;
    @Autowired
    private PostRepository postRepository;

    /**
     * @Author 郭兆龙
     * 用于讨论组跳转
     * @return
     */
    public List<ChatTeam> chat(){
        return this.chatTeamRepository.findAll();
    }

    /**
     * @Author 郭兆龙
     * 跳转到博客
     */
    public List<Post> blog(){
        return this.postRepository.findAll();
    }



    /*
    *
     * @Author 胡玉浩
     * @Description //TODO
     * @Date 11:28 2018/12/18
     * @Param
     * @return
     **/
    
    public List<Blog> chatFindBname(String name){
        System.out.println(chatTeamRepository.findByCname(name).getBlogs());
        return chatTeamRepository.findByCname(name).getBlogs();
    }


    public void saveBlog(Blog blog,String chatteam,String username){

        UserInfo byUsername = userInfoRepository.findByUsername(username);
        ChatTeam byCname = chatTeamRepository.findByCname(chatteam);
        System.out.println(byCname.getCname());
        System.out.println(byCname.getCname());
        byUsername.getBlogs().add(blog);
        byCname.getBlogs().add(blog);
        blog.setChatTeam(byCname);
        blog.setUserInfo(byUsername);
        userInfoRepository.saveAndFlush(byUsername);
        chatTeamRepository.saveAndFlush(byCname);
        blogRepository.save(blog);
    }
}
