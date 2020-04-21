package cn.edu.scujcc.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import cn.edu.scujcc.dao.ChannelRepository;
import cn.edu.scujcc.model.Channel;
/**
 * 获取所有频道数据
 * @author 86133
 *
 */
@Service
public class ChannelService {
	@Autowired
	private ChannelRepository repo;
	
	/**
	 * ��ȡ����Ƶ��
	 * @return
	 */
	public List<Channel> getAllChannels(){
//		List<String> qs = new ArrayList<>();
//		qs.add("720p");
//		qs.add("1080p");//查询清晰度为720p或者为1080p的
//		return repo.findAll();
//		Page<Channel> page = repo.findByQualityContaining("中央", PageRequest.of(0,10));
//		return page.toList();
		return repo.findAll();
	}
	
	/**
	 * ��ȡһ��Ƶ��
	 * @param id
	 * @return
	 */
	
	public Channel getChannel(String channelId) {
		Optional<Channel> result = repo.findById(channelId);
		if(result.isPresent()) {
			return result.get();
		}else {
			return null;
		}
	}
	
	
	/**
	 * ɾ��ָ��Ƶ��
	 * @param id
	 * @return
	 */
	
	public boolean deleteChannel(String channelId) {
		boolean result = true;
		repo.deleteById(channelId);
		return result;
	}
	
	/**
	 * ����Ƶ��
	 * @param c
	 * @return
	 */
	@PostMapping
	public Channel creatChannel(Channel c) {
		return repo.save(c);
	}
	/**
	 * ����һ��Ƶ��
	 * @param c �����µ�Ƶ��
	 * @return ���º��Ƶ��
	 */
	@PostMapping
	public Channel updateChannel(Channel c) {
		Channel saved = getChannel(c.getId());
		if(saved != null) {
			//方法一:自己一个一个的复制数据
			if(c.getTitle() != null) {
				saved.setTitle(c.getTitle());
			}
			if(c.getQuality() != null) {
				saved.setQuality(c.getQuality());	
			}
			if(c.getUrl() != null) {
				saved.setUrl(c.getUrl());
			}
			if(c.getComments() != null) {
				saved.getComments().addAll(c.getComments());
			}else {
				saved.setComments(c.getComments());
			}
			//方法二,批量复制
			
		}
			return repo.save(saved);
		}
	
	/**
	 * ��������
	 * @param title
	 * @param quality
	 * @return
	 */
	public List<Channel> searchTitle(String title){
		return repo.findByTitle(title);
	}
	public List<Channel> searchQuality(String quality){
		return repo.findByQuality(quality);
	}
	/**
	 * 找出今天有评论的频道
	 * @return
	 */
	public List<Channel> getLateestCommentsChannel(){
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime today = LocalDateTime.of(now.getYear(),now.getMonthValue(),now.getDayOfMonth(),0,0);
		return repo.findByCommentsDtAfter(today);
	}
}



