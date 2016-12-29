/******************************************************************************
 * Copyright (C) 2016 ShenZhen xiaoyue Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为许畅个人开发研制。未经本人正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/
package com.easemob.server.example.comm.utils;

import com.easemob.server.example.api.ChatGroupAPI;
import com.easemob.server.example.api.ChatMessageAPI;
import com.easemob.server.example.api.ChatRoomAPI;
import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.EasemobRestAPIFactory;
import com.easemob.server.example.comm.body.ChatGroupBody;
import com.easemob.server.example.comm.body.ChatRoomBody;
import com.easemob.server.example.comm.body.IMUserBody;
import com.easemob.server.example.comm.body.UserNamesBody;
import com.easemob.server.example.comm.wrapper.ResponseWrapper;

/**
 * 环信聊天操作者
 * 
 * @author 许畅
 * @since JDK1.7
 * @version 2016年9月8日 许畅 新建
 */
public final class EmchatOperator {

	/**
	 * 构造方法
	 */
	private EmchatOperator() {
		super();
	}

	/** 单例模式 延迟加载 */
	private static ChatRoomAPI CHATROOM_API = null;

	/** IMUserAPI接口实例 */
	private static IMUserAPI IM_USER_API = null;

	/** 群组API */
	private static ChatGroupAPI CHATGROUP_API = null;

	/** 聊天消息API */
	private static ChatMessageAPI CHATMESSAGE_API = null;

	/** EasemobRestAPIFactory */
	private static final EasemobRestAPIFactory FACTORY = ClientContext
			.getInstance().init(ClientContext.INIT_FROM_PROPERTIES)
			.getAPIFactory();

	/**
	 * 获取ChatRoomAPI实例
	 * 
	 * @return
	 */
	public static synchronized ChatRoomAPI getChatRoomInstance() {
		if (null == CHATROOM_API) {
			CHATROOM_API = (ChatRoomAPI) FACTORY
					.newInstance(EasemobRestAPIFactory.CHATROOM_CLASS);
		}
		return CHATROOM_API;
	}

	/**
	 * 获取聊天消息实例
	 * 
	 * @return
	 */
	public static synchronized ChatMessageAPI getChatMessageInstance() {
		if (null == CHATMESSAGE_API) {
			CHATMESSAGE_API = (ChatMessageAPI) FACTORY
					.newInstance(EasemobRestAPIFactory.MESSAGE_CLASS);
		}
		return CHATMESSAGE_API;
	}

	/**
	 * 获取群组API实例
	 * 
	 * @return
	 */
	public static synchronized ChatGroupAPI getChatGroupInstance() {
		if (CHATGROUP_API == null) {
			CHATGROUP_API = (ChatGroupAPI) FACTORY
					.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);
		}
		return CHATGROUP_API;
	}

	/**
	 * 获取IMUser用户实例
	 * 
	 * @return
	 */
	public static synchronized IMUserAPI getIMUserInstance() {
		if (IM_USER_API == null) {
			IM_USER_API = (IMUserAPI) FACTORY
					.newInstance(EasemobRestAPIFactory.USER_CLASS);

		}
		return IM_USER_API;
	}

	/**
	 * 创建环信用户
	 * 
	 * @return
	 */
	public static ResponseWrapper createIMUser(String userName,
			String password, String nickName) {
		IMUserBody body = new IMUserBody(userName, password, nickName);
		return (ResponseWrapper) getIMUserInstance()
				.createNewIMUserSingle(body);
	}

	/**
	 * 添加为好友
	 * 
	 * @param userName
	 * @param friendName
	 * @return
	 */
	public static ResponseWrapper addFriendSingle(String userName,
			String friendName) {
		return (ResponseWrapper) getIMUserInstance().addFriendSingle(userName,
				friendName);
	}

	/**
	 * 获取用户的朋友信息
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 */
	public static ResponseWrapper getFriends(String userName) {
		return (ResponseWrapper) getIMUserInstance().getFriends(userName);
	}

	/**
	 * 创建聊天室
	 * 
	 * @param name
	 *            聊天室名称
	 * @param desc
	 *            聊天室描述
	 * @param maxUsers
	 *            最大人数
	 * @param owner
	 *            创建者
	 * @param members
	 *            聊天室成员
	 * @return ResponseWrapper
	 */
	public static ResponseWrapper createChatRoom(String name, String desc,
			Long maxUsers, String owner, String[] members) {
		ChatRoomAPI chatroom = getChatRoomInstance();

		ChatRoomBody roomBody = new ChatRoomBody(name, desc, maxUsers, owner,
				members);

		return (ResponseWrapper) chatroom.createChatRoom(roomBody);
	}

	/**
	 * 创建300人聊天群
	 * 
	 * @param groupName
	 *            群名称
	 * @param desc
	 *            群描述
	 * @param owner
	 *            创建者
	 * @param members
	 *            群成员
	 * @return
	 */
	public static ResponseWrapper createChatGroup(String groupName,
			String desc, String owner, String[] members) {
		ChatGroupAPI chatGroupAPI = getChatGroupInstance();
		Boolean approval = Boolean.TRUE;
		Boolean isPublic = Boolean.TRUE;
		Long maxUsers = Long.parseLong("200");
		ChatGroupBody groupBody = new ChatGroupBody(groupName, desc, isPublic,
				maxUsers, approval, owner, members);
		return (ResponseWrapper) chatGroupAPI.createChatGroup(groupBody);
	}

	/**
	 * 修改群信息
	 * 
	 * @param groupId
	 *            群id
	 * @param groupName
	 *            群名称
	 * @param desc
	 *            描述
	 * @return
	 */
	public static ResponseWrapper modifyChatGroup(String groupId,
			String groupName, String desc, String owner, String[] members) {
		ChatGroupAPI chatGroupAPI = getChatGroupInstance();
		Boolean approval = Boolean.TRUE;
		Boolean isPublic = Boolean.TRUE;
		Long maxUsers = Long.parseLong("200");
		ChatGroupBody groupBody = new ChatGroupBody(groupName, desc, isPublic,
				maxUsers, approval, owner, members);
		return (ResponseWrapper) chatGroupAPI.modifyChatGroup(groupId,
				groupBody);
	}

	/**
	 * 获取所有聊天室
	 * 
	 * @return
	 */
	public static ResponseWrapper getAllChatRooms() {
		ChatRoomAPI chatroom = getChatRoomInstance();

		return (ResponseWrapper) chatroom.getAllChatRooms();
	}

	/**
	 * 获取聊天室详细信息
	 * 
	 * @param roomId
	 * @return
	 */
	public static ResponseWrapper getChatRoomDetail(String roomId) {
		ChatRoomAPI chatroom = getChatRoomInstance();

		return (ResponseWrapper) chatroom.getChatRoomDetail(roomId);
	}

	/**
	 * 聊天室中增加群员
	 * 
	 * @param roomId
	 *            聊天室标识
	 * @param userName
	 *            用户ID或用户名
	 * @return
	 */
	public static ResponseWrapper addSingleUserToChatRoom(String roomId,
			String userName) {
		return (ResponseWrapper) getChatRoomInstance().addSingleUserToChatRoom(
				roomId, userName);
	}

	/**
	 * 批量增加聊天室群员
	 * 
	 * @param roomId
	 *            roomId
	 * @param userName
	 *            用户名称
	 * @param password
	 * @return
	 */
	public static ResponseWrapper addBatchUsersToChatRoom(String roomId,
			String userName) {
		UserNamesBody userNames = new UserNamesBody(new String[] { userName });
		return (ResponseWrapper) getChatRoomInstance().addBatchUsersToChatRoom(
				roomId, userNames);
	}

	/**
	 * 批量增加群成员
	 * 
	 * @param groupId
	 *            群Id
	 * @param userName
	 *            用户名称
	 * @return
	 */
	public static ResponseWrapper addBatchUsersToChatGroup(String groupId,
			String userName) {
		UserNamesBody userNames = new UserNamesBody(new String[] { userName });
		return (ResponseWrapper) getChatGroupInstance()
				.addBatchUsersToChatGroup(groupId, userNames);
	}

	/**
	 * 获取群里的成员信息
	 * 
	 * @param groupId
	 *            群Id
	 * @return ResponseWrapper
	 */
	public static ResponseWrapper getChatGroupUsers(String groupId) {

		return (ResponseWrapper) getChatGroupInstance().getChatGroupUsers(
				groupId);
	}

	/**
	 * 获取群聊天记录
	 * 
	 * @param limit
	 * @param cursor
	 * @param query
	 * @return
	 */
	public static ResponseWrapper exportChatMessage(long limit, String cursor,
			String query) {
		return (ResponseWrapper) getChatMessageInstance().exportChatMessages(
				limit, cursor, query);
	}
}
