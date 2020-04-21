package cn.edu.scujcc.dao;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import cn.edu.scujcc.model.Channel;
@Repository
public interface ChannelRepository extends MongoRepository<Channel,String>{
	public List<Channel> findByTitle(String t);
	public List<Channel> findByQuality(String q);
	//public List<Channel> findByQualityIn(Collection qs);//查找清晰度在某个区间
	public Page<Channel> findByQualityContaining(String t,Pageable p);
	//找出评论时间在指定日期之后的所有频道
	public List<Channel> findByCommentsDtAfter(LocalDateTime theDt);
}
